package fei.tuke.sk.stmlang;

import java.io.*;
public class Main {
    public static void main(String[] args) {
        try (Reader reader = new FileReader("zadanie3/input.txt");
             Writer writer = new FileWriter("zadanie3/output.c")
        ) {
            Lexer lexer = new Lexer(reader);
            StateMachineDefinition stateMachineDefinition = new Parser(lexer).stateMachine();
            new Generator(stateMachineDefinition, writer).generate();
        } catch (StateMachineException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}