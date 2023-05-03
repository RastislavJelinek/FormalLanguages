package fei.tuke.sk.stmlang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.Map;

public class ParserTest {

    @Test
    public void testParsingCommands() {
        String input = """
            commands {
              FORWARD 'f'
              LEFT 'l'
              RIGHT 'r'
            }
            """;

        Lexer lexer = new Lexer(new StringReader(input));
        Parser parser = new Parser(lexer);

        StateMachineDefinition stateMachineDefinition = parser.stateMachine();

        Assertions.assertEquals(3, stateMachineDefinition.getCommands().size());
        Assertions.assertEquals('f', stateMachineDefinition.getCommands().get("FORWARD"));
        Assertions.assertEquals('l', stateMachineDefinition.getCommands().get("LEFT"));
        Assertions.assertEquals('r', stateMachineDefinition.getCommands().get("RIGHT"));
    }

    @Test
    public void testParsingEvents() {
        String input = """
            events {
              leftSensor 'l'
              rightSensor 'r'
            }
            """;

        Lexer lexer = new Lexer(new StringReader(input));
        Parser parser = new Parser(lexer);

        StateMachineDefinition stateMachineDefinition = parser.stateMachine();

        Assertions.assertEquals(2, stateMachineDefinition.getEvents().size());
        Assertions.assertEquals('l', stateMachineDefinition.getEvents().get("leftSensor"));
        Assertions.assertEquals('r', stateMachineDefinition.getEvents().get("rightSensor"));
    }

    @Test
    public void testParsingResetCommands() {
        String input = """
            resetCommands {}
            """;

        Lexer lexer = new Lexer(new StringReader(input));
        Parser parser = new Parser(lexer);

        StateMachineDefinition stateMachineDefinition = parser.stateMachine();

        Assertions.assertEquals(0, stateMachineDefinition.getResetCommands().size());
    }

    @Test
    public void testParsingStateMachineDefinition() {
        String input = """
            commands {
                 doorClosed   'd'
             }
            state idle {
                 doorClosed => active
             }
            """;


        Lexer lexer = new Lexer(new StringReader(input));
        Parser parser = new Parser(lexer);

        StateMachineDefinition stateMachineDefinition = parser.stateMachine();

        // Verify that the parsed state machine definition has one state
        Assertions.assertEquals(1, stateMachineDefinition.getStates().size());


        // Verify that the first state is called "idle"
        Assertions.assertTrue(stateMachineDefinition.getStates().containsKey("idle"));
    }

}
