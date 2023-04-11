import fei.tuke.sk.Lexer;
import fei.tuke.sk.Parser;
import org.junit.jupiter.api.Test;
import fei.tuke.sk.CalculatorException;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testEmptyInput() {
        Lexer lexer = new Lexer(new StringReader(""));
        Parser parser = new Parser(lexer);
        assertEquals(0, parser.statement());
    }
    @Test
    public void testSpaceCharactersInput() {
        Lexer lexer = new Lexer(new StringReader("                   "));
        Parser parser = new Parser(lexer);
        assertEquals(0, parser.statement());
    }

    @Test
    public void testSimpleAddition() {
        Lexer lexer = new Lexer(new StringReader("5+2"));
        Parser parser = new Parser(lexer);
        assertEquals(7, parser.statement());
    }

    @Test
    public void testSimpleSubtraction() {
        Lexer lexer = new Lexer(new StringReader("8-4"));
        Parser parser = new Parser(lexer);
        assertEquals(4, parser.statement());
    }

    @Test
    public void testSimpleMultiplication() {
        Lexer lexer = new Lexer(new StringReader("9*9"));
        
        Parser parser = new Parser(lexer);
        assertEquals(81, parser.statement());
    }

    @Test
    public void testSimpleDivision() {
        Lexer lexer = new Lexer(new StringReader("5/5"));
        Parser parser = new Parser(lexer);
        assertEquals(1, parser.statement());
    }

    @Test
    public void testSimplePower() {
        Lexer lexer = new Lexer(new StringReader("2^5"));
        Parser parser = new Parser(lexer);
        assertEquals(32, parser.statement());
    }

    @Test
    public void testSimpleAdditionAndSubtraction() {
        Lexer lexer = new Lexer(new StringReader("1+2-3"));
        Parser parser = new Parser(lexer);
        assertEquals(0, parser.statement());
    }

    @Test
    public void testSimpleAdditionAndMultiplication() {
        Lexer lexer = new Lexer(new StringReader("1+2*3"));
        Parser parser = new Parser(lexer);
        assertEquals(7, parser.statement());
    }

    @Test
    public void testSimpleAdditionAndDivision() {
        Lexer lexer = new Lexer(new StringReader("1+2/3"));
        Parser parser = new Parser(lexer);
        assertEquals(1, parser.statement());
    }

    @Test
    public void testSimpleAdditionAndPower() {
        Lexer lexer = new Lexer(new StringReader("1+2^3"));
        Parser parser = new Parser(lexer);
        assertEquals(9, parser.statement());
    }

    @Test
    public void testSimpleSubtractionAndMultiplication() {
        Lexer lexer = new Lexer(new StringReader("1-2*3"));
        Parser parser = new Parser(lexer);
        assertEquals(-5, parser.statement());
    }

    @Test
    public void testSimpleSubtractionAndDivision() {
        Lexer lexer = new Lexer(new StringReader("1-2/3"));
        Parser parser = new Parser(lexer);
        assertEquals(1, parser.statement());
    }

    @Test
    public void testSimpleSubtractionAndPower() {
        Lexer lexer = new Lexer(new StringReader("1-2^3"));
        Parser parser = new Parser(lexer);
        assertEquals(-7, parser.statement());
    }

    @Test
    public void testSimpleMultiplicationAndDivision() {
        Lexer lexer = new Lexer(new StringReader("1*2/3"));
        Parser parser = new Parser(lexer);
        assertEquals(0, parser.statement());
    }

    @Test
    public void testSimpleMultiplicationAndPower() {
        Lexer lexer = new Lexer(new StringReader("1*2^3"));
        Parser parser = new Parser(lexer);
        assertEquals(8, parser.statement());
    }

    @Test
    public void testSimpleDivisionAndPower() {
        Lexer lexer = new Lexer(new StringReader("1/2^3"));
        Parser parser = new Parser(lexer);
        assertEquals(0, parser.statement());
    }

    @Test
    public void testSimpleAdditionAndSubtractionAndMultiplication() {
        Lexer lexer = new Lexer(new StringReader("1+2-3*4"));
        Parser parser = new Parser(lexer);
        assertEquals(-9, parser.statement());
    }

    @Test
    public void testSequenceOfUPOperators() {
        Lexer lexer = new Lexer(new StringReader("1UP2UP3"));
        Parser parser = new Parser(lexer);
        assertEquals(1, parser.statement());
    }

    @Test
    public void testParentheses() {
        Lexer lexer = new Lexer(new StringReader("(1+2)*3"));
        Parser parser = new Parser(lexer);
        assertEquals(9, parser.statement());
    }

    @Test
    public void testExtremeParentheses() {
        Lexer lexer = new Lexer(new StringReader("((1+2)*3)"));
        Parser parser = new Parser(lexer);
        assertEquals(9, parser.statement());
    }

    @Test
    public void testExtremeParentheses2() {
        Lexer lexer = new Lexer(new StringReader("((1+2)*3)"));
        Parser parser = new Parser(lexer);
        assertEquals(9, parser.statement());
    }

    @Test
    public void testExtremeParentheses3() {
        Lexer lexer = new Lexer(new StringReader("((1+2)*3)"));
        Parser parser = new Parser(lexer);
        assertEquals(9, parser.statement());
    }

    @Test
    public void testExtremeParentheses4() {
        Lexer lexer = new Lexer(new StringReader("((1+2)*3)"));
        Parser parser = new Parser(lexer);
        assertEquals(9, parser.statement());
    }

    @Test
    public void testExtremeParentheses5() {
        Lexer lexer = new Lexer(new StringReader("((1+2)*3)"));
        Parser parser = new Parser(lexer);
        assertEquals(9, parser.statement());
    }

    @Test
    public void testUnaryMinus() {
        Lexer lexer = new Lexer(new StringReader("-1"));
        Parser parser = new Parser(lexer);
        assertEquals(-1, parser.statement());
    }

    @Test
    public void testUnaryMinus2() {
        Lexer lexer = new Lexer(new StringReader("-1+2"));
        Parser parser = new Parser(lexer);
        assertEquals(1, parser.statement());
    }

    @Test
    public void testUnaryMinus3() {
        Lexer lexer = new Lexer(new StringReader("1+-2"));
        Parser parser = new Parser(lexer);
        assertEquals(-1, parser.statement());
    }

    @Test
    public void testUnaryMinus4() {
        Lexer lexer = new Lexer(new StringReader("1+(-2)"));
        Parser parser = new Parser(lexer);
        assertEquals(-1, parser.statement());
    }

    @Test
    public void testComplexExpressionWithParentheses() {
        Lexer lexer = new Lexer(new StringReader("(2 2 + (2 * 6)* 2 2)"));
        Parser parser = new Parser(lexer);
        assertEquals(286, parser.statement());
    }

    @Test
    public void testComplexExpressionWithParentheses2() {
        Lexer lexer = new Lexer(new StringReader("2*(2* (2*2))"));
        Parser parser = new Parser(lexer);
        assertEquals(16, parser.statement());
    }

    @Test
    public void testComplexExpressionWithBadParentheses() {
        Lexer lexer = new Lexer(new StringReader("((2*(2* (2*2))"));
        Parser parser = new Parser(lexer);
        assertThrows(CalculatorException.class, parser::statement);
    }

    @Test
    public void testComplexExpressionWithBadParentheses2() {
        Lexer lexer = new Lexer(new StringReader("2*(2* (2*2))))))))"));
        Parser parser = new Parser(lexer);
        assertThrows(CalculatorException.class, parser::statement);
    }

    @Test
    public void testComplexExpressionWithBadParentheses3() {
        Lexer lexer = new Lexer(new StringReader("2*(2*((((())))))) (2*2))))))))"));
        Parser parser = new Parser(lexer);
        assertThrows(CalculatorException.class, parser::statement);
    }

    @Test
    public void testInvalidExpression() {
        Lexer lexer = new Lexer(new StringReader("2*2*"));
        Parser parser = new Parser(lexer);
        assertThrows(CalculatorException.class, parser::statement);
    }
}

