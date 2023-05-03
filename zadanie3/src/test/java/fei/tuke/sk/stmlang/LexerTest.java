package fei.tuke.sk.stmlang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

public class LexerTest {

    @Test
    void testNextTokenCommands() {
        String input = "commands";
        Lexer lexer = new Lexer(new StringReader(input));
        Token token = lexer.nextToken();
        Assertions.assertEquals(TokenType.COMMANDS, token.tokenType());
        token = lexer.nextToken();
        Assertions.assertEquals(TokenType.EOF, token.tokenType());
    }

    @Test
    void testNextTokenResetCommands() {
        String input = "resetCommands";
        Lexer lexer = new Lexer(new StringReader(input));
        Token token = lexer.nextToken();
        Assertions.assertEquals(TokenType.RESET_COMMANDS, token.tokenType());
        token = lexer.nextToken();
        Assertions.assertEquals(TokenType.EOF, token.tokenType());
    }

    @Test
    void testNextTokenEvents() {
        String input = "events";
        Lexer lexer = new Lexer(new StringReader(input));
        Token token = lexer.nextToken();
        Assertions.assertEquals(TokenType.EVENTS, token.tokenType());
        token = lexer.nextToken();
        Assertions.assertEquals(TokenType.EOF, token.tokenType());
    }

    @Test
    void testNextTokenState() {
        String input = "state";
        Lexer lexer = new Lexer(new StringReader(input));
        Token token = lexer.nextToken();
        Assertions.assertEquals(TokenType.STATE, token.tokenType());
        token = lexer.nextToken();
        Assertions.assertEquals(TokenType.EOF, token.tokenType());
    }

    @Test
    void testNextTokenActions() {
        String input = "actions";
        Lexer lexer = new Lexer(new StringReader(input));
        Token token = lexer.nextToken();
        Assertions.assertEquals(TokenType.ACTIONS, token.tokenType());
        token = lexer.nextToken();
        Assertions.assertEquals(TokenType.EOF, token.tokenType());
    }

    @Test
    void testNextTokenQuote() {
        String input = "'";
        Lexer lexer = new Lexer(new StringReader(input));
        Token token = lexer.nextToken();
        Assertions.assertEquals(TokenType.QUOTE, token.tokenType());
        token = lexer.nextToken();
        Assertions.assertEquals(TokenType.EOF, token.tokenType());
    }

    @Test
    void testNextTokenLBracket() {
        String input = "{";
        Lexer lexer = new Lexer(new StringReader(input));
        Token token = lexer.nextToken();
        Assertions.assertEquals(TokenType.LBRACKET, token.tokenType());
        token = lexer.nextToken();
        Assertions.assertEquals(TokenType.EOF, token.tokenType());
    }

    @Test
    void testNextTokenRBracket() {
        String input = "{}";
        Lexer lexer = new Lexer(new StringReader(input));
        lexer.nextToken();
        Token token = lexer.nextToken();
        Assertions.assertEquals(TokenType.RBRACKET, token.tokenType());
        token = lexer.nextToken();
        Assertions.assertEquals(TokenType.EOF, token.tokenType());
    }
    @Test
    void testUnmatchedRBracket() {
        String input = "}";
        Lexer lexer = new Lexer(new StringReader(input));
        Assertions.assertThrows(StateMachineException.class, lexer::nextToken);
    }

    @Test
    void testNextTokenAssign() {
        String input = "=>";
        Lexer lexer = new Lexer(new StringReader(input));
        Token token = lexer.nextToken();
        Assertions.assertEquals(TokenType.ASSIGN, token.tokenType());
        token = lexer.nextToken();
        Assertions.assertEquals(TokenType.EOF, token.tokenType());
    }

    @Test
    void testNextTokenAssignError() {
        String input = "=+";
        Lexer lexer = new Lexer(new StringReader(input));
        Assertions.assertThrows(StateMachineException.class, lexer::nextToken);
    }

    @Test
    void testNextTokenName() {
        String input = "test";
        Lexer lexer = new Lexer(new StringReader(input));
        Token token = lexer.nextToken();
        Assertions.assertEquals(TokenType.NAME, token.tokenType());
        Assertions.assertEquals("test", lexer.getWord());
        token = lexer.nextToken();
        Assertions.assertEquals(TokenType.EOF, token.tokenType());
    }

    @Test
    void testNextTokenNameUnexpectedChar() {
        String input = "_test";
        Lexer lexer = new Lexer(new StringReader(input));
        Assertions.assertThrows(StateMachineException.class, lexer::nextToken);
    }

}