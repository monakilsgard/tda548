package samples;
import static java.lang.System.*;
/*
 * Useful static methods in Character class
 */
public class UseCharacter {


    public static void main(String[] args) {
        new UseCharacter().program();
    }

    void program() {
       /* out.println(Character.isWhitespace(' '));
        out.println(Character.isDigit('1'));
        out.println(Character.isLetter('X'));
        out.println(Character.isLetterOrDigit('2'));
        out.println(Character.isLowerCase('c'));
        out.println(Character.toString('Z').equals("Z"));
        */
        char[] strarray = {'A', 'B'};

        if (Character.isDigit(strarray[0])) {
            out.println(true);

        } else {
            out.println(false);
        }
    }

}
