package calc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static java.lang.System.out;

/**
 * This is a test program for the Calculator
 * It should output true for everything
 *
 * Right click and run ...
 */
public class Test {

    public static void main(String[] args) {
        new Test().test();
    }

    final Calculator calculator = new Calculator();

    void test() {

        // Here you could write your own test for "small" helper methods

        /*
        out.println( call some helper method );

        */

        // Uncomment below line by line to test (also must uncomment helper methods, last)

        // ------- Tokenization ---------------------------

        t("1 + 10", "1 + 10");  // Arguments are input and expected output
        t("1+ 10", "1 + 10");   // Expected is in fact a list [ "1", "+", "10"]
        t("1 +10", "1 + 10");
        t("1+10", "1 + 10");
        t("(1+10) ", "( 1 + 10 )");  // List is [ "(", "1", "+", "10", ")" ]
        t("2 *( 1+10) ", "2 * ( 1 + 10 )");
        t("(1 +2) /2 *( 1+10) ", "( 1 + 2 ) / 2 * ( 1 + 10 )");

        // Infix to postfix -----------------------
      /*
        i2p("1+10", "1 10 +");
        i2p("1+2+3", "1 2 + 3 +");
        i2p("1+2-3", "1 2 + 3 -");
        i2p("3-2-1", "3 2 - 1 -");
        i2p("1 + 2 * 3", "1 2 3 * +");
        i2p("1 / 2 + 3", "1 2 / 3 +");
        i2p("20/4/2", "20 4 / 2 /");
        i2p("4^3^2", "4 3 2 ^ ^");
        i2p("4^3*2", "4 3 ^ 2 *");
        i2p("(1+2)*3", "1 2 + 3 *");
        i2p("2^(1+1)", "2 1 1 + ^");
      */

        // Evaluation ------------------------------
       /*
        // A value
        e("123", 123);

        // Basic operations
        e("1 + 10", 11);
        e("1 + 0", 1);
        e("1 - 10", -9);  // Input may not be negative but output may
        e("10 - 1", 9);
        e("60 * 10", 600);
        e("60 * 0", 0);
        e("3 / 2", 1.5);  // See exception for div by zero
        e("1 / 2", 0.5);
        e("2 ^ 4 ", 16);
        e("2 ^ 0 ", 1);

        // Associativity
        e("10 - 5 - 2", 3);  // (10-5)-2
        e("20 / 2 / 2", 5);  // (20/2)/2
        e("4 ^ 2 ^ 2", 256);  // 4^(2^2)

        // Precedence
        e("3 * 10 + 2", 32);
        e("3 + 10 * 2", 23);
        e("30 / 3 + 2", 12);
        e("1 + 30 / 3", 11);
        e("3 * 2 ^ 2", 12);
        e("3 ^ 2 * 2", 18);

        // Parentheses
        e("10 - (5 - 2)", 7);
        e("20 / (10 / 2)", 4);
        e("(3 ^ 2) ^ 2", 81);
        e("3 * (10 + 2)", 36);
        e("30 / (3 + 2)", 6);
        e("(3 + 2) ^ 2", 25);
        e(" 2 ^ (1 + 1)", 4);
        e(" ((((1 + 1))) * 2)", 4);

        // Mix priority and right and left associativity
        e(" 1 ^ 1 ^ 1 ^ 1  - 1", 0);
        e(" 4 - 2 - 1 ^ 2 ", 1);

        // Exceptions -----------------------------------
        try {
            e("1 / 0 ", 0);   // 0 just a dummy
        } catch (IllegalArgumentException e) {
            out.println(e.getMessage().equals(Calculator.DIV_BY_ZERO));
        }
        try {
            e("1 + 2 + ", 0);
        } catch (IllegalArgumentException e) {
            out.println(e.getMessage().equals(Calculator.MISSING_OPERAND));
        }
        try {
            e("12 3", 0);
        } catch (IllegalArgumentException e) {
            out.println(e.getMessage().equals(Calculator.MISSING_OPERATOR));
        }
        try {
            e("1 + 2)", 0);
        } catch (IllegalArgumentException e) {
            out.println(e.getMessage().equals(Calculator.MISSING_OPERATOR));
        }
        */
    }

    // -----   Below are helper test methods, don't bother ...  --------------

    // t for tokenize, a very short name, lazy, avoid typing ...
    void t(String expr, String expected) {
        List<String> list = calculator.tokenize(expr);
        String result = String.join(" ", list);
        out.println(result.equals(expected));
    }

    /*
    // Infix 2 postfix
    void i2p(String infix, String expected) {
        List<String> tokens = calculator.tokenize(infix);
        List<String> postfix = calculator.infix2Postfix(tokens);
        String result = String.join(" ", postfix);
        out.println(result.equals(expected));
    }
    */
    /*
    // Evaluation
    void e(String infix, double expected) {
        List<String> tokens = calculator.tokenize(infix);
        List<String> postfix = calculator.infix2Postfix(tokens);
        double result = calculator.evalPostfix(postfix);
        out.println(result == expected);
    }*/

}
