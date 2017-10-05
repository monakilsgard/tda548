package calc;

import java.util.*;

import static java.lang.Double.NaN;
import static java.lang.Math.pow;


/*
 *   A calculator for rather simple arithmetic expressions
 *
 *   NOTE:
 *   - No negative numbers implemented
 */
public class Calculator {

    // Error messages (more on static later)
    final static String MISSING_OPERAND = "Missing or bad operand";
    final static String DIV_BY_ZERO = "Division with 0";

    // Definition of operators
    final String OPERATORS = "+-*/^";
    final String PARENTHESES = "()";

    // Method used in REPL
    double eval(String expr) {
        if (expr.length() == 0) {
            return NaN;
        }
        // List<String> tokens = tokenize(expr);       <---------------- HERE are the methods!!!!
        // List<String> postfix = infix2Postfix(tokens);
        return 0; // 0 just for now, should be: return evalPostfix(postfix);
    }

    // ------  Evaluate RPN expression -------------------

    // TODO

    double applyOperator(String op, double d1, double d2) {
        switch (op) {
            case "+":
                return d1 + d2;
            case "-":
                return d2 - d1;
            case "*":
                return d1 * d2;
            case "/":
                if (d1 == 0) {
                    throw new IllegalArgumentException(DIV_BY_ZERO);
                }
                return d2 / d1;
            case "^":
                return pow(d2, d1);
        }
        throw new RuntimeException(OP_NOT_FOUND);
    }

    // ------- Infix 2 Postfix ------------------------

    // Error messages
    final static String MISSING_OPERATOR = "Missing operator or parenthesis";
    final static String OP_NOT_FOUND = "Operator not found";

    // TODO

    int getPrecedence(String op) {
        if ("+-".contains(op)) {
            return 2;
        } else if ("*/".contains(op)) {
            return 3;
        } else if ("^".contains(op)) {
            return 4;
        } else {
            throw new RuntimeException(OP_NOT_FOUND);
        }
    }

    Assoc getAssociativity(String op) {
        if ("+-*/".contains(op)) {
            return Assoc.LEFT;
        } else if ("^".contains(op)) {
            return Assoc.RIGHT;
        } else {
            throw new RuntimeException(OP_NOT_FOUND);
        }
    }

    enum Assoc {
        LEFT,
        RIGHT
    }

    // ---------- Tokenize -----------------------

    List<String> tokenize(String expr) {
        List<String> tokenList = new ArrayList<>();
        char[] exprArr = expr.toCharArray();
        for (int i = 0; i < exprArr.length; ) {
            if (Character.isDigit(exprArr[i])) {
                i = readNumber(tokenList, expr, i);
            } else if (OPERATORS.indexOf(exprArr[i]) >= 0) {
                tokenList.add(Character.toString(exprArr[i]));
                i++;
            } else if (PARENTHESES.indexOf(exprArr[i]) >= 0) {
                tokenList.add(Character.toString(exprArr[i]));
                i++;
            } else {
                i++;
            }
        }

        return tokenList;
    }

    //------- ReadNumbers------------------------------

    int readNumber(List<String> list, String expr, int startIndex) {

        StringBuilder sbString = new StringBuilder();
        int lastIndex = startIndex;
        while (lastIndex < expr.length() && (Character.isDigit(expr.charAt(lastIndex)))) {
            sbString.append(expr.charAt(lastIndex));
            lastIndex++;
        }
        String number = sbString.toString();
        list.add(number);

        return lastIndex;

    }

    //----------- CheckParentheses --------------------------------

    boolean checkParentheses(String expr) {

        Deque<Character> stack = new ArrayDeque<>(); //Create stack

        for (char ch : expr.toCharArray()) {   //Loop through string
            if ("{[(".indexOf(ch) >= 0) {
                stack.push(ch);
            } else if ("}])".indexOf(ch) >= 0) {

                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.peek();

                if (matching(ch) == top) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return false;
    }

//----------- Matching ---------------------------------------

    char matching(char ch) {

        switch (ch) {
            case ')':
                return '(';  // c = '('
            case ']':
                return '[';
            case '}':
                return '{';
            default:
                throw new IllegalArgumentException("No match found");
        }
    }
}
