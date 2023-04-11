package fei.tuke.sk;


public class Parser {
    private final Lexer lexer;
    private Token symbol;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public int statement() {
        return plusMinus();
    }

    private int plusMinus() {
        consumeToken();
        if (symbol == Token.EOF)return 0;
        int result = divide();
        while (symbol == Token.PLUS || symbol == Token.MINUS) {
            if (symbol == Token.PLUS) {
                consumeToken();
                result += divide();
            } else {
                consumeToken();
                result -= divide();
            }
        }
        return result;
    }

    private int divide() {
        int result = multiply();
        while (symbol == Token.DIV) {
            consumeToken();
            result /= multiply();
        }
        return result;
    }

    private int multiply() {
        int result = minusAtBeginning();
        while (symbol == Token.MUL) {
            consumeToken();
            result *= minusAtBeginning();
        }
        return result;
    }

    private int minusAtBeginning() {
        if (symbol == Token.MINUS) {
            consumeToken();
            return -powerOf();
        }
        return powerOf();
    }

    private int powerOf() {
        int result = endOfInput();
        while (symbol == Token.POW) {
            consumeToken();
            result = (int) Math.pow(result, endOfInput());
        }
        return result;
    }

    private int endOfInput() {
        int result;
        switch(symbol){
            case NUMBER-> result = lexer.getValue();
            case LPAREN->{
                result = plusMinus();
                numberOfBracketsMatch();
            }
            default->throw new CalculatorException("Unexpected token: " + symbol);
        }
        consumeToken();
        return result;
    }

    private void numberOfBracketsMatch() {
        if (symbol != Token.RPAREN)throw new CalculatorException("Unexpected token: " + symbol);
    }

    private void consumeToken() {
        symbol = lexer.nextToken();
    }
}
