package fei.tuke.sk;

import java.io.Reader;


public class Lexer {
    private int current;
    private int value;
    private final Reader input;
    private int bracketCount;

    public Lexer(Reader reader) {
        this.input = reader;
    }

    public Token nextToken() {
        if(current == 0)consume();
        boolean leftoverChar = false;
        Token token = switch (current) {
            case '+' -> Token.PLUS;
            case '-' -> Token.MINUS;
            case '*' -> Token.MUL;
            case 'x' -> {
                    for (int i = 0; i < 2; ++i) {
                        consume();
                        if (current != 'x')throw new CalculatorException("only valid use of 'x' is in format 'xxx': " + (char) current);
                    }
                    yield Token.MUL;
                }
            case '/'->Token.DIV;
            case 'D'-> {
                consume();
                if (current != 'I')throw new CalculatorException("only valid use of 'I' is in format 'DIV': " + (char) current);
                consume();
                if (current != 'V')throw new CalculatorException("only valid use of 'V' is in format 'DIV': " + (char) current);
                yield Token.DIV;
            }
            case '^'->Token.POW;
            case 'U'->{
                consume();
                if (current != 'P')throw new CalculatorException("only valid use of 'P' is in format 'UP': " + (char) current);
                yield  Token.POW;
            }
            case '('->{
                bracketCount++;
                yield Token.LPAREN;
            }
            case ')'->{
                if (bracketCount == 0)throw new CalculatorException("Unmatched right bracket");
                bracketCount--;
                yield  Token.RPAREN;
            }
            case -1->{
                if (bracketCount != 0)throw new CalculatorException("Unmatched left bracket");
                yield Token.EOF;
            }

            default-> {
                if (!Character.isDigit((char) current))throw new CalculatorException("Unexpected character: " + (char) current);
                value = 0;
                while (Character.isDigit((char) current)) {
                    value = value * 10 + Character.digit((char) current, 10);
                    consume();
                    leftoverChar = true;
                }
                yield Token.NUMBER;
            }
        };
        if(!leftoverChar) consume();
        return token;
    }

    private void consume() {
        try {
            do{
                current = input.read();
            }while (Character.isWhitespace(current));
        } catch (Exception e) {
            throw new CalculatorException("Error while reading input", e);
        }
    }

    public int getValue() {
        return value;
    }
}
