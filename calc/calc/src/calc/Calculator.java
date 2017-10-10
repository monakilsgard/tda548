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

    double eval(List<String> postfix){
        double result;
        
        
        return result;
    }

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

    List<String> infix2Postfix(List<String> infixStr) {

        Deque<String> operatorStack = new ArrayDeque<>();
        List<String> outputList = new ArrayList<>();


        for (int i = 0; i < infixStr.size(); i++) {      // while there are tokens to be read
            String token = infixStr.get(i);              // put string at index i in token

            char firstChar = token.charAt(0);            // put first character in string in firstChar
            if (Character.isDigit(firstChar)) {          // if firsChar is a number, add token to the list
                outputList.add(token);
            }


            if (OPERATORS.contains(token)) {
                while (!operatorStack.isEmpty()  && !PARENTHESES.contains(operatorStack.peek()) && checkPrio(operatorStack.peek(), token) ) {
                    outputList.add(operatorStack.pop());
                }
                operatorStack.push(token);

            }
            if (token.equals("(")) {
                operatorStack.push(token);

            }
            if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    outputList.add(operatorStack.pop());
                }

                if (!operatorStack.isEmpty()) {
                    operatorStack.pop();
                } else {
                    throw new IllegalArgumentException(MISSING_OPERATOR);
                }
            }

        }                                                   //if there are no more tokens to read:
        while (!(operatorStack.isEmpty())) {                 //while there are still operator tokens on the stack:
            if (operatorStack.peek().equals(PARENTHESES)) {          //if the operator token on the top of the stack is a bracket, then...
                throw new IllegalArgumentException(MISSING_OPERATOR); //there are mismatched parentheses.
            } else outputList.add(operatorStack.pop());
        }
        return outputList;
    }


      /*
        pop the operator onto the output queue. */


    // Error messages
    final static String MISSING_OPERATOR = "Missing operator or parenthesis";
    final static String OP_NOT_FOUND = "Operator not found";

    //------------GetPrecedence-----------------------------------
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

    //--------------------GetAssociativity---------------------------
    Assoc getAssociativity(String op) {
        if ("+-*/".contains(op)) {
            return Assoc.LEFT;
        } else if ("^".contains(op)) {
            return Assoc.RIGHT;
        } else {
            throw new RuntimeException(OP_NOT_FOUND);
        }
    }

    //------------------Assoc-----------------------------------
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
                }

            }

        }
        return false;
    }
//------------CheckPrio--------------------------------------------

    boolean checkPrio(String topOfStack, String token) {
        return (getPrecedence(token) <= getPrecedence(topOfStack) && getAssociativity(token) == Assoc.LEFT);
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
