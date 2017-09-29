package samples;

import static java.lang.System.out;

/**
 * Build Strings using StringBuilder (avoid copying)
 */
public class UseStringBuilder {

    public static void main(String[] args) {
        new UseStringBuilder().program();
    }

    void program() {

        // Use a String builder
        StringBuilder sb = new StringBuilder();
        // Will not create new objects (using '+' with String will crete objects)
        sb.append('h').append('e').append('c').append('r');
        // Convert to String
        String s = sb.toString();
        out.println(s);

        out.println(generateLongString('X', 500));
    }

    // Below we use a StringBuilder to add chars to a text.
    String generateLongString(char c, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(c);   // Much more efficient, no copying, just add last.
        }
        return sb.toString();
    }

    /*
      This would have been bad
      String s = "";
      for( .... ){
         s = s + ch;    // BAD! New string created then copy all chars in each iteration!
      }

    */

}
