import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BasicExercises2 {

    public static void main(String[] args) {


        //Tough exercises: On Binary
        //https://www.w3resource.com/java-exercises/basic/java-basic-exercise-17.php
        //https://www.w3resource.com/java-exercises/basic/java-basic-exercise-18.php
        //https://www.w3resource.com/java-exercises/basic/java-basic-exercise-19.php
        // converting types --> up to exercise 30.

        //31. Check if Java is installed
        System.out.println("java home is: " + System.getProperty("java.home"));
        System.out.println("java.version is: " + System.getProperty("java.version\n"));

        //33. Calculate number of digits in an Integer
        calculateSumOfDigits();

        interestingMathMethods();

        //36. Distance between 2 points ---> latitude(equator, lines going from east-west), longitude(north and south)

        //37. Reverse a String
        reverseAString();
    }

    private static void reverseAString() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a name and we will reverse the order of it for you!");
        String name = in.nextLine();
        //in.nextLine().toCharArray(); ---> is even better as it creates the array for us!

        //TODO Jimmy -> Try various approaches using String list vs String array
        List<String> arrayOfLetters = new ArrayList<>();
        String[] arrayOfLetters2 = new String[100];

        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            arrayOfLetters.add(String.valueOf(c));
            arrayOfLetters2[i] = String.valueOf(i);
        }

        StringBuilder reversedWord = new StringBuilder();
        for (int i = arrayOfLetters.size() - 1; i > 0; i--) {
            reversedWord.append(arrayOfLetters.get(i));
        }

        System.out.println("The reversed word should be Jimmy and is: " + reversedWord);

    }

    private static void interestingMathMethods() {
        // (6 * s^2) ---> 6*(s*s)
        // (4*tan(π/6) ---> (4*Math.tan(Math.PI/6)
        Math.tan(123);
        Math.cos(23);
        Math.sin(12312);
        Math.toRadians(23432); // For longitude/latitude
        //Hexagon = 6
        //polygon = many
    }

    private static void calculateSumOfDigits() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a number and we'll tell you the sum of the overall digits!");
        int numberInputted = in.nextInt();

        //1. So basically divide by 10 to works from far right of digits.
        //2. Then do calculation on each digit be iterating through to next one
        int sum = 0;
        while (numberInputted != 0) {
            int temp = numberInputted;
            System.out.println("The number is currently: " + numberInputted);
            sum += numberInputted % 10;//modulus 3/10 ---> will still return 3
            System.out.println("The variable to add to sum is: " + temp % 10);
            numberInputted /= 10; // /3/10 ---> will return 0
            System.out.println("NumberInputted divided by 10 is: " + (temp /= 10));
        }

        System.out.println("Sum of digits is: " + sum);
        in.close();
    }

}
