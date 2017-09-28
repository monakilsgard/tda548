package samples;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 *  Motivate use of interface
 *
 *  If using an interface as a method argument the exact type
 *  of the object at method call doesn't matter as long
 *  as object implements (fulfills) the interface (contract).
 *
 *  Possible to send in different objects to methods
 */
public class WhyInterface {

    public static void main(String[] args) {
        new WhyInterface().program();
    }

    void program() {
        // Two kinds of lists
        LinkedList<Integer> ll = new LinkedList<>();
        ArrayList<Integer> al = new ArrayList<>();

        // List<Integer> ll = new ArrayList<>();  this is better , interface type for variable

        //Comment/Uncomment different combinations to see
        doIt(ll);   // The call!
        doIt(al);
    }

    // Argument is interface type
    void doIt(List<Integer> list) {
        // Will work for any! Both implements List i.e. fulfills contract
    }

    /*
    // Parameter is fixed
    void doIt(LinkedList<Integer> ll) {
        // Won't work for ArrayList argument
    }

    // Parameter is fixed
    void doIt(ArrayList<Integer> al) {
        // Won't work for LinkedList argument
    }*/

}
