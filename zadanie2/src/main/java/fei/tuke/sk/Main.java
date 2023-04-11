package fei.tuke.sk;

import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.print("Enter your expression: ");
            Reader reader = new StringReader(new Scanner(System.in).nextLine());
            Lexer lexer = new Lexer(reader);
            int result = new Parser(lexer).statement();
            System.out.println("Result: " + result);
            reader.close();
        } catch (CalculatorException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}