package samples;

import static java.lang.System.out;

/**
 * A switch statement is a selection (like if) but only use equality (== or equals)
 * to match
 */
public class SwitchStatement {

    public static void main(String[] args) {
        new SwitchStatement().program();
    }

    void program() {

        int i = 4;
        // Switch statement
        switch (i) {          // Switch with int
            case 0:                // If match 0 ...
                out.println("match 0"); // ... run this
                break;            // IMPORTANT, else will run "case 1" also
            case 1:
                out.println("match 1");
                break;
            case 2:
                out.println("match 2");
                break;
            case 3:
                out.println("match 3");
                break;
            case 4:
                out.println("match 4");
                break;
            default:
               out.println("no match");  // If no match
        }


        String s = "qqq";
        // Another switch
        switch (s) {               // Switch with String
            case "aaa":
                out.println("match aaa");
                break;
            case "bbb":
                out.println("match bbb");
                break;
            default:
                out.println("no match");
        }

        Color c = Color.BLACK;
        // And a last switch
        switch (c) {               // Switch with enum (classes not allowed)
            case WHITE:
                out.println("white");
                break;
            case BLACK:
                out.println("black");
                break;
            default:
                out.println("no match");
        }

        int j = 0;
        final int k = 1, l = 2;   // Variables allowed if final
        switch (j) {
            case k:
                out.println(1);
                break;
            case l:
                out.println(2);
                break;
            default:
                out.println("no match");
        }

    }


    enum Color {
        BLACK, WHITE;
    }

}
