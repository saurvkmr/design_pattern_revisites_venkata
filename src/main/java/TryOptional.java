package main.java;

import java.util.*;

/**
 * Make the code obvious
 * null is smell
 * Effective Java - We should return null, instead return an empty object eg - empty conllection
 * If we have single value and not a collection - return Optional<T>
 */

 /*
  * Use Optional when value may or may not be present.
  If value is always going to be present, avoid using optional.
  */

public class TryOptional {
    public static void main(String[] args) {
        var name = getName();
        System.out.println(name);
        System.out.println(name.orElse("not found"));
    }

    public static Optional<String> getName() {
        if (Math.random() > 0.5)
            return Optional.of("Joe");
        // return null; // bad idea
        return Optional.empty();
    }

    /*
     * Don't use Optional<T> as parameter to methods
     * Instead use overloading.
     */

    public static void setName(Optional<String> name) {
        if (name.isPresent()) {System.out.println(name);}
        else {System.out.println("Joe");}
    }

    // setName(Optional.of("Sara")); // everytime developer has to wrap object around Optional which is developer friendly code
    // setName(Optional.empty());

    // Instead use overloading
    public static void setName() {
        System.out.println("Joe");
    }

    public static void setName(String name) {
        System.out.println(name);
    }
    public static List<String> getNames() {
        // instead of returning null return empty collections
        return List.of();
    }
}
