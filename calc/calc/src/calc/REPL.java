package calc;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

/**
 *  Read Eval Print Loop for Calculator
 *  To be able to use the calculator interactively
 *
 *  To run calculator run this.
 *
 */
public class REPL {

    public static void main(String[] args) {
        new REPL().program();
    }

    final Scanner scan = new Scanner(in);
    final Calculator calculator = new Calculator();

    void program() {

        while (true) {
            out.print("> ");
            String input = scan.nextLine();
            try {
                double result = calculator.eval(input);
                out.println(result);
            }catch( Exception e){
                out.println(e.getMessage());
            }
        }
    }


}
