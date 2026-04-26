package lambda_streams;

@FunctionalInterface
interface Calculator {

    int calculate(int a, int b);

    /* Just to show it can have multiple static or default methods */
    static void print() {
        System.out.println("Printing from calculator");
    }

    default void nameOfInterface() {
        System.out.println("Name of interface is caluclator..");
    }
}

public class BasicLamdaWithCustomFI {
    public static void main(String[] args) {

        Calculator add = (a, b) -> a + b;
        Calculator multiply = (a, b) -> a * b;
        /*
         * if not lambda ,then I need to create class add that implements calculator and
         * then implement
         * unimplemented methods like calculate.
         */

        System.out.println("Add : " + add.calculate(10, 20));
        System.out.println("Multiply : " + multiply.calculate(10, 20));

    }
}
