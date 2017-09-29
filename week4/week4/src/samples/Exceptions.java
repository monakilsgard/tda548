package samples;

import java.nio.file.FileAlreadyExistsException;

import static java.lang.System.out;

/**
 * Using Exceptions, see tests for lab 3 Calculator
 * Possibly: Run in debugger!
 */
public class Exceptions {

    public static void main(String[] args) {
        new Exceptions().program();
    }

    private void program() {
        doA(3);    // No problems

        //doA(-1);  // Unhandled exception, program will halt

        try {
            doA(-1);     // Handled exception, if ok skip catch and continue, else ...
            out.println("Will this show up?");
        } catch (IllegalArgumentException e) {
            out.println(e.getMessage());  // ... do this and continue
        }

        out.println("Program ended");
    }

    public void doA(int i) {
        doB(i);
    }

    public void doB(int i) {
        doC(i);
    }

    public void doC(int i) {
        if (i < 0) {
            // Unchecked exception (don't need too add throws to method head)
            throw new IllegalArgumentException("No negatives");
            // Checked exception
            //throw new FileAlreadyExistsException("File exists");
        }
    }

}
