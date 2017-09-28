package samples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.*;

/*
   Using a short for loop.

   If elements has primitive types can't modify them (loop uses a copy).
   If references possible to modify object.

   Also bad to remove an element in a collection while traversing

   If you need an index for some reason, you can't use short for-loop
 */
public class ShortForLoop {

    public static void main(String[] args) {
        new ShortForLoop().program();
    }

    private void program() {
        int[] ia = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer[] iia = {1, 2, 3};
        String strs[] = {"a", "b", "c", "d", "e", "f"};

        for (int i : iia) {  // NOTE: No index just each element in order
            out.print(i);
            // i++;          // Senseless i local variable
        }
        out.println();

        for (String s : strs) {
            out.print(s);
        }
        out.println();

        // Can't assign variable
        for (String s : strs) {
            out.print(s);
            s = "X";       // s is a local variable. If assigned will not affect original array element
            out.print(s);
        }
        out.println();
        out.println(Arrays.toString(strs));

        // Can't assign loop variable
        for( int i : iia){
            i = 0;    // No use
        }
        out.println(Arrays.toString(iia));


        // NOTE : If using array with objects possible to change object with short for loop
        // See week 6


        List<Integer> il = Arrays.asList(iia); // See UseAList
        // BAD! Concurrent modification exception if using short for loop and remove
        /*for (Integer i : il) {
            if (i < 2) {
                il.remove(i);    // Can't remove while traversing
            }
        }*/

    }

}
