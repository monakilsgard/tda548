package samples;

import static java.lang.System.out;

/*
 * String is a Java standard API to work with texts.
 *
 * Some usable String handling
 * You don't need to learn all the methods by heart,
 * if allowed to use on exam you'll get a list.
 */
public class UseString {

    public static void main(java.lang.String[] args) {
        new UseString().program();
    }

    void program() {
        String str = "abcdef";
        //String str = new String("abcdef");  // This is bad will create unnecessary objects

        // Compare strings
        out.println(str.equals("abcdef"));
        out.println(!str.equals("qwerty"));

        // Inspecting a string
        out.println(str.isEmpty());
        out.println(str.length());
        out.println(str.charAt(3));
        out.println(str.charAt(str.length()-1));  // Last char!

        // Search inside a string
        str = "abcdefabcdef";
        out.println(str.contains("cd"));
        out.println(str.startsWith("abc"));
        out.println(str.endsWith("def"));
        out.println(str.lastIndexOf("a") == 6);
        out.println(str.indexOf('a') == 0);
        out.println(str.indexOf('X') == -1);     // Not found -1
        out.println(str.indexOf("fab") == 5);  // Matches String


        // Manipulate Strings (will create new Strings)
        str = "Success consists of going from failure" +
                " to failure without loss of enthusiasm";

        out.println(str.substring(0, 4));  // NOTE: 0-3
        out.println(str);  // Original not changed, substring creates new string!

        out.println(str.substring(4));     // NOTE: 4-length
        out.println("   abcde    ".trim().equals("abcde"));  // Remove leading/ending space
        out.println(str.replace("failure", "icecream"));
        out.println(str.replaceFirst("failure", "icecream"));
        out.println("abcdefaböab".replaceAll("ab", "X"));


        // Convert to array and back
        char[] arr = str.toCharArray();
        str = new String(arr);

        // Work with a single char at the time
        for( char ch: str.toCharArray()){
            if( Character.isDigit(ch)){
                // Do something
            }
        }

        // Split into words (Strings)
        String[] strs = str.split(" ");  // " " matches one space.
        for( String s : strs){
            out.println( s );
        }

        // From primitive to String ...
        String s = String.valueOf(45);
        s = String.valueOf(true);
        s = String.valueOf(1.45);
        s = String.valueOf('X');

        // From String to primitive
        int i = Integer.valueOf("678");
        double d = Double.valueOf("4.57");
        boolean b = Boolean.valueOf("false");

    }


}
