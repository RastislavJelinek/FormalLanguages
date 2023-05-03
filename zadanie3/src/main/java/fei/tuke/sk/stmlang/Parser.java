package fei.tuke.sk.stmlang;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
Grammar:
        * StateMachine  -> { Statement }
        * Statement     -> Commands | ResetCommands | Events | State
        * Commands      -> "commands" "{" { NAME CHAR } "}"
        * Events        -> "events" "{" { NAME CHAR } "}"
        * ResetCommands -> "resetCommands" "{" { NAME } "}"
        * State         -> "state" "{" [Actions] { Transition } "}"
        * Actions       -> "actions" "{" { NAME } "}"
        * Transition    -> NAME "->" NAME
 */

public class Parser {
    private final Lexer lexer;
    private Token symbol;
    private StateMachineDefinition definition;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public StateMachineDefinition stateMachine() {
        definition = new StateMachineDefinition();
        var first = Set.of(
                TokenType.COMMANDS,
                TokenType.EVENTS,
                TokenType.RESET_COMMANDS,
                TokenType.STATE);

        consume();
        while (symbol.tokenType() != TokenType.EOF) {
                switch (symbol.tokenType()) {
                    case COMMANDS -> commands();
                    case EVENTS -> events();
                    case RESET_COMMANDS -> resetCommands();
                    case STATE -> state();
                    default -> throw new StateMachineException("wrong command");
                }
            consume();
        }

        return definition;
    }

    private void commands() {
        consume();
        if(symbol.tokenType() != TokenType.LBRACKET) throw new StateMachineException("incorrect syntax after commands keyword, missing {");
        consume();
        while (symbol.tokenType() != TokenType.RBRACKET && symbol.tokenType() != TokenType.EOF) {
            if (symbol.tokenType() != TokenType.NAME)
                throw new StateMachineException("error in commands, there should be name");
            String name = lexer.getWord();
            consume();
            if (symbol.tokenType() != TokenType.QUOTE)
                throw new StateMachineException("incorrect name-key binging in commands, there should be key bind starting with quotes ''");
            consume();
            String value = lexer.getWord();
            if (value.length() > 1) throw new StateMachineException("too many keys to bind in commands");
            definition.addCommand(name, value.charAt(0));
            consume();
            if (symbol.tokenType() != TokenType.QUOTE)
                throw new StateMachineException("incorrect name-key binging in commands, there should be key bind ending with quotes ''");
            consume();
        }
        if(symbol.tokenType() == TokenType.EOF){
            throw new StateMachineException("incorrect syntax after at end of commands, missing }");
        }
    }

    private void events() {
        consume();
        if(symbol.tokenType() != TokenType.LBRACKET) throw new StateMachineException("incorrect syntax after events keyword, missing {");
        consume();
        while (symbol.tokenType() != TokenType.RBRACKET && symbol.tokenType() != TokenType.EOF) {
            if (symbol.tokenType() != TokenType.NAME)
                throw new StateMachineException("error in events, there should be name");
            String name = lexer.getWord();
            consume();
            if (symbol.tokenType() != TokenType.QUOTE)
                throw new StateMachineException("incorrect name-key binging in events, there should be key bind in quotes ''");
            consume();
            String value = lexer.getWord();
            if (value.length() > 1) throw new StateMachineException("too many keys to bind");
            definition.addEvent(name, value.charAt(0));
            consume();
            if (symbol.tokenType() != TokenType.QUOTE)
                throw new StateMachineException("incorrect name-key binging in events, there should be key bind in quotes ''");
            consume();
        }
        if(symbol.tokenType() == TokenType.EOF){
            throw new StateMachineException("incorrect syntax at end of events, missing }");
        }
    }

    private void resetCommands() {
        consume();
        if(symbol.tokenType() != TokenType.LBRACKET) throw new StateMachineException("incorrect syntax after resetCommands keyword, missing {");
        consume();
        while (symbol.tokenType() != TokenType.RBRACKET && symbol.tokenType() != TokenType.EOF) {
            if (symbol.tokenType() != TokenType.NAME)
                throw new StateMachineException("error in resetCommands, there should be name");
            definition.addResetCommands(lexer.getWord());
            consume();
        }
        if(symbol.tokenType() == TokenType.EOF){
            throw new StateMachineException("incorrect syntax at end of resetCommands, missing }");
        }
    }

    private void state() {
        consume();
        if (symbol.tokenType() != TokenType.NAME)
            throw new StateMachineException("error in state, there should be name of state");
        String stateName = lexer.getWord();
        consume();
        if(symbol.tokenType() != TokenType.LBRACKET) throw new StateMachineException("incorrect syntax after state keyword, missing {");
        consume();
        StateDefinition state = new StateDefinition();
        while (symbol.tokenType() != TokenType.RBRACKET && symbol.tokenType() != TokenType.EOF) {
            switch (symbol.tokenType()) {
                case ACTIONS ->{
                    consume();
                    if(symbol.tokenType() != TokenType.LBRACKET) throw new StateMachineException("incorrect syntax after state keyword, missing {");
                    consume();
                    while (symbol.tokenType() != TokenType.RBRACKET && symbol.tokenType() != TokenType.EOF) {
                        if (symbol.tokenType() != TokenType.NAME)
                            throw new StateMachineException("error in state, there should be name in actions");
                        if (!definition.getEvents().containsKey(lexer.getWord()))
                            throw new StateMachineException("error in state, event does not exist");
                        state.addAction(lexer.getWord());
                        consume();
                    }
                    if(symbol.tokenType() == TokenType.EOF){
                        throw new StateMachineException("incorrect syntax at end of state, missing }");
                    }

                }
                case NAME -> {
                    if (!definition.getCommands().containsKey(lexer.getWord()))
                        throw new StateMachineException("error in state, command does not exist");
                    String command = lexer.getWord();
                    consume();
                    if(symbol.tokenType() != TokenType.ASSIGN) throw new StateMachineException("incorrect syntax in state after command, no assigment =>");
                    consume();
                    if (symbol.tokenType() != TokenType.NAME)
                        throw new StateMachineException("error in state, there should be name of state");
                    String commandState = lexer.getWord();
                    state.addTransition(new TransitionDefinition(command,commandState));
                }
                default -> throw new StateMachineException("error in state, there should be command or actions");
            }
            definition.addState(stateName,state);
            consume();
        }
        if(symbol.tokenType() == TokenType.EOF){
            throw new StateMachineException("incorrect syntax after state, missing }");
        }
    }

    private void consume() {
        symbol = lexer.nextToken();
    }
}
