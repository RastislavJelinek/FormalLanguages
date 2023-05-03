package fei.tuke.sk.stmlang;

import java.io.Reader;
import java.util.Map;

/**
 * Lexical analyzer of the state machine language.
 */
public class Lexer {
    private static final Map<String, TokenType> keywords = Map.of(
            "commands", TokenType.COMMANDS,
            "resetCommands", TokenType.RESET_COMMANDS,
            "events", TokenType.EVENTS,
            "state", TokenType.STATE,
            "actions", TokenType.ACTIONS
    );

    private final Reader input;
    private int current;
    private boolean leftover;
    private int bracketCount;

    private String word;

    public String getWord() {
        return word;
    }
    public Lexer(Reader input) {
        this.input = input;
    }

    public Token nextToken() {
        if(!leftover) consume();
        leftover = false;
            return switch (current) {
                case '\'' -> new Token(TokenType.QUOTE);

                case '{' -> {
                    ++bracketCount;
                    yield new Token(TokenType.LBRACKET);
                }
                case '}' -> {
                    if (bracketCount == 0) throw new StateMachineException("Unmatched right bracket");
                    --bracketCount;
                    yield new Token(TokenType.RBRACKET);
                }
                case '=' -> {
                    consume();
                    if (current != '>')
                        throw new StateMachineException("only valid use of '=' is in format '=>': " + (char) current);
                    yield new Token(TokenType.ASSIGN);
                }
                case -1 -> new Token(TokenType.EOF);
                default -> {
                    if (!Character.isLetterOrDigit(current))
                        throw new StateMachineException("Unexpected character: " + current);
                    yield readNameOrKeyword();
                }
            };
    }

    private Token readNameOrKeyword() {
        StringBuilder sb = new StringBuilder();
        while (Character.isLetterOrDigit(current)) {
            sb.append((char) current);
            try {
                current = input.read();
            } catch (Exception e) {
                throw new StateMachineException("Error while reading input", e);
            }
        }
        if(!Character.isWhitespace(current))
            leftover = true;
        word = sb.toString();
        TokenType type = keywords.get(word);

        if (type != null) {
            return new Token(type);
        }
        return new Token(TokenType.NAME);
    }

    private void consume() {
        try {
            do {
                current = input.read();
            } while (Character.isWhitespace(current));
        } catch (Exception e) {
            throw new StateMachineException("Error while reading input", e);
        }
    }
}