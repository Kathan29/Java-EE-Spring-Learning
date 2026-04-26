package lambda_streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BuiltIn_FunctionalInterface {

    public static void main(String[] args) {

        // Predicate : T -> boolean
        Predicate<Integer> isEven = x -> x % 2 == 0;
        // Predicate<Integer> isEven2 = (Integer y) -> {return y%2==0;};
        System.out.println("is Even? : " + isEven.test(60));
        Predicate<Integer> multipleOf5 = x -> x % 5 == 0;

        // we can do chaining here like
        boolean result = isEven.and(multipleOf5).test(60);
        System.out.println("Result for 60 is :" + result);
        result = isEven.and(multipleOf5).test(65);
        System.out.println("Result for 65 is : " + result);

        // Function : T -> R
        Function<String, Integer> getLength = s -> s.length();
        System.out.println("String length is : " + getLength.apply("java"));

        // Consumer : T -> ()
        Consumer<String> print = s -> System.out.println(s);
        print.accept("object oriented programming");

        List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry");

        // Consumer takes a string and prints it
        Consumer<String> printUpperCase = (s) -> System.out.println(s.toUpperCase());

        // forEach takes a Consumer as an argument!
        fruits.forEach(printUpperCase);

        // Supplier : () -> T
        Supplier<Double> getRandomNumber = () -> Math.random();
        System.out.println("Get random number : " + getRandomNumber.get());
    }

}
