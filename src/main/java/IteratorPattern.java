package main.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Ex
 */
public class IteratorPattern {
    public static void main(String[] args) {
        var names = List.of("Amitabh", "Amit", "Shakti", "Swapan", "Jana");
        printAllNamesGreaterThan5Char(names);
        printFirst2NamesGreaterThan5Char(names);

        sharedMutability(names);

    }

    public static void printAllNamesGreaterThan5Char(List<String> names) {
        // external iterator
        for (int i = 0; i < names.size(); i++) {

        }

        for (String name : names) {
            if (name.length() >= 5)
                System.out.println(name.toUpperCase());
        }

        System.out.println("-------");
        // Internal Iterator
        names.stream()
                .filter(name -> name.length() >= 5)
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    public static void printFirst2NamesGreaterThan5Char(List<String> names) {
        // external iterator
        for (int i = 0; i < names.size(); i++) {

        }
        int count = 0;
        for (String name : names) {
            if (name.length() >= 5) {
                count++;
                System.out.println(name.toUpperCase());
            }
            if (count == 2) break;
        }

        System.out.println("-------");
        // Internal Iterator
        names.stream()
                .filter(name -> name.length() >= 5)
                .limit(2) // java 8
                .map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println("-------");
        // Internal Iterator
        names.stream()
                // .filter(name -> name.length() >= 5)
                .takeWhile(name -> name.length() >= 5)
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    /**
     * What is a pure function:
     * It is a idempotent. it returns the same result for same input and does not have any side effect
     * Two properties
     * 1. It does not change nay state that is visible outside
     * 2. It does not depend on anything outside that may possibly change
     *
     *
     */
    public static void sharedMutability(List<String> names) {
        // Imperative style
        var result1 = new ArrayList<String>();
        for (String name : names) {
            if (name.length() > 5)
                result1.add(name.toUpperCase());
        }
        System.out.println(result1);
        System.out.println("-------");
        // Functional Style
        var result2 = new ArrayList<String>();
        // This functional pipeline is *not* pure
        // We are doing shared mutability.
        // The result may be unpredictable if we ever change this code to run in parallel
        // by adding .parallel() or by changing .stream() to .parallelStream()
        names.stream()
                .parallel()
                .filter(name -> name.length() > 5)
                .map(String::toUpperCase)
                .forEach(name -> result2.add(name));

        System.out.println(result2);

        // so what to do
        var result = names.parallelStream()
                .filter(name -> name.length() > 5)
                .map(String::toUpperCase)
                .toList();

        System.out.println(result);
    }
}
