package samples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


import static java.lang.System.out;

/*
  Basic use of ArrayList

  ArrayList is like an dynamic array that grows and shrinks. Empty at start (i.e. no positions what so ever)
  An array is an object thus has methods.

  See: https://en.wikipedia.org/wiki/List_(abstract_data_type)

  ArrayList is generic i.e. it can handle any typ of references.
  When declaration a reference variable to an ArrayList, must specify the element type in angle brackets

  ArrayList is an *implementation* of the List interface (an interface is a contract, a collection of methods).
  ArrayList implements List (i.e. all methods in List are guaranteed to be in ArrayList,
  ArrayList fulfills the contract, we can call methods from List on an ArrayList object)

  Both List and Array list are supplied by Java
 */

public class UseAList {


    public static void main(String[] args) {
        new UseAList().program();
    }

    void program() {
        // It's possible to have a variable of an interface type
        // We prefer variables of interface type because then we can change the implementation
        // without changing the rest of the program (not applicable here, but in general)
        // See: WhyInterface sample
        List<Integer> list = new ArrayList<>();

        out.println(list.isEmpty());
        list.add(100);   // Put last in list
        list.add(200);
        list.add(300);
        out.println(list);                 // List has a toString method, easy to print out
        out.println(list.size() == 3);
        out.println(list.get(2));   // Can't use [ ]. Use get() (0-indexed)
        out.println(list.indexOf(300) == 2);

        list.set(0, 500);  // Will overwrite
        out.println(!list.contains(100));
        out.println(list);

        for (Integer i : list) {   // Use short for-loop. NOTE Can't remove using this loop ...
            out.print(i);          // See ShortForLoop
        }


        list.add(1, 400);  // Values moved to right, then inserted at 1
        out.println(list);
        // Index 50 doesn't exist, exception!
        //list.add(50, 555);

        list.remove(1);      // Remove using index
        out.println(list);

        List<Integer> l2 = list.subList(1, 3); // This is hazardous, sublist just a view of original!
        out.println(l2);

        list.clear();
        out.println(list.isEmpty());


        //list.add(null); // Very very BAAD!

    }


}
