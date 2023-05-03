package fei.tuke.sk.stmlang;

import java.io.IOException;
import java.io.Writer;

public class Generator {
    private final StateMachineDefinition stateMachine;
    private final Writer writer;
    private final StringBuilder sb = new StringBuilder();

    public Generator(StateMachineDefinition stateMachine, Writer writer) {
        this.stateMachine = stateMachine;
        this.writer = writer;
    }

    public void generate(){
        sb.append("#include \"common.h\"\n\n");
        generateMethods();

        stateMachine.getStates().forEach(this::writeState);
        sb.append("\n");

        String key = stateMachine.getStates().entrySet().iterator().next().getKey();
        appendIndentedLine( 0, "void main() {");
        appendIndentedText( 1, "state_");
            sb.append(key);
            sb.append("();\n");
        appendIndentedLine( 0, "}");
        try {
            writer.write(sb.toString());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    private void generateMethods() {
        stateMachine.getStates().keySet().forEach(stateName -> {
            sb.append("void state_");
            sb.append(stateName);
            sb.append("();\n");
        });
        sb.append("\n");
    }

    private void generateActions(StateDefinition a) {
        a.getActions().forEach(action ->{
            appendIndentedText(1,"send_event('");
            Character character = stateMachine.getEvents().get(action);
            sb.append(character);
            sb.append("');\n");
        });
    }

    private void generateTransitionOrCommand(Character commandChar, String targetName, int indentLevel) {
        appendIndentedText(indentLevel, "case '");
        sb.append(commandChar);
        sb.append("':\n");
        appendIndentedText(indentLevel + 1, "return state_");
        sb.append(targetName);
        sb.append("();\n");
    }

    private void generateTransitions(StateDefinition a) {
        a.getTransitions().forEach(transition -> {
            Character character = stateMachine.getCommands().get(transition.commandName());
            generateTransitionOrCommand(character, transition.targetName(), 2);
        });
    }

    private void generateCommands() {
        stateMachine.getResetCommands().forEach(command -> {
            Character character = stateMachine.getCommands().get(command);
            String key = stateMachine.getStates().entrySet().iterator().next().getKey();
            generateTransitionOrCommand(character, key, 2);
        });
    }

    private void writeState(String name, StateDefinition state) {
        sb.append("void state_");
        sb.append(name);
        sb.append("() {\n");
        generateActions(state);
        appendIndentedLine( 1, "char ev;");
        appendIndentedLine( 1, "while (ev = read_command()) {");
        appendIndentedLine( 2, "switch (ev) {");
        generateTransitions(state);
        generateCommands();
        appendIndentedLine( 2, "}");
        appendIndentedLine( 1, "}");
        appendIndentedLine( 0, "}");
    }

    private void appendIndentedLine( int numTabs, String line) {
        sb.append("\t".repeat(Math.max(0, numTabs)));
        sb.append(line);
        sb.append("\n");
    }
    private void appendIndentedText( int numTabs, String line) {
        sb.append("\t".repeat(Math.max(0, numTabs)));
        sb.append(line);
    }
}