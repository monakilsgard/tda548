package exercises;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

/*
 * Count number of words in some text
 *
 * See:
 * - UseCharacter
 * - UseString
 * - ShortForLoop
 */
public class Ex1WordCount {

    public static void main(String[] args) {
        new Ex1WordCount().program();
    }

    void program() {


        out.println(countWords("") == 0);

        out.println(countWords("hello") == 1);

        out.println(countWords(" hello ") == 1);

        out.println(countWords("hello world") == 2);
        /*
        out.println(countWords("hello        world") == 2);
        out.println(countWords("   hello        world  ") == 2);
        String s = "Education is what remains after one has forgotten what one has learned in school.";
        out.println(countWords(s) == 14);
        */
    }

    int countWords(String str) {       // Calculate words in a text
        int wordCount = 0;
        int letterCount = 0;
        if (str.isEmpty()) {
            return 0;
        }

        for (char ch : str.toCharArray()) {
            boolean isEmptySpace = (ch == ' ');
            if (str.indexOf(ch) >= 0 && !isEmptySpace) {
                letterCount++;

            } else if (letterCount > 0 && isEmptySpace) {
                wordCount++;
                letterCount = 0;
            }


        }
        if (letterCount > 0) {
            wordCount++;
        }

        return wordCount;

    }

}
