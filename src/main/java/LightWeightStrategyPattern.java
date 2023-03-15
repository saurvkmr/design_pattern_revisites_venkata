package main.java;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class LightWeightStrategyPattern {
    private static void naiveCode(List<Integer> numbers) {
        System.out.println(sumAllTheNumbers(numbers));
        System.out.println(sumAllEvenNumbers(numbers));
        System.out.println(sumAllOddNumbers(numbers));
    }

    private static void myFunctionalCode(List<Integer> numbers) {
        Predicate<Integer> noCondition = ignore -> true;
        System.out.println(sumNumbersMySolution(numbers, noCondition));
        System.out.println(sumNumbersMySolution(numbers, num -> num % 2 != 0));
        System.out.println(sumNumbersMySolution(numbers, LightWeightStrategyPattern::isOdd));
    }

    private static boolean isOdd(Integer number) {
        return number % 2 != 0;
    }

    public static void main(String[] args) {
        var numbers = IntStream.rangeClosed(1, 10).boxed().toList();
        naiveCode(numbers);
        myFunctionalCode(numbers);
    }

    private static long sumNumbersMySolution(List<Integer> numbers, Predicate<Integer> condition) {
        return numbers.stream()
                .filter(condition)
                .mapToLong(num -> ((Integer) num).longValue())
                .sum();
    }

    private static int sumAllTheNumbers(List<Integer> numbers) {
        int total = 0;
        for (Integer num : numbers) {
            total += num;
        }
        return total;
    }

    private static int sumAllEvenNumbers(List<Integer> numbers) {
        int total = 0;
        for (Integer num : numbers) {
            if (num % 2 == 0) total += num;
        }
        return total;
    }

    private static int sumAllOddNumbers(List<Integer> numbers) {
        int total = 0;
        for (Integer num : numbers) {
            if (num % 2 != 0) total += num;
        }
        return total;
    }
}
