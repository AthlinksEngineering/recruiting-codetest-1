import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;

public class Code {

    // Returns "Hello World!"
    public static String helloWorld() {
        return "Hello World!";
    }

    // Take a single-spaced <sentence>, and capitalize every <n> word starting
    // with <offset>.
    public static String capitalizeEveryNthWord(String sentence, Integer offset,
            Integer n) {
        //  break apart
        String[] words = sentence.split(" ");
        for (int i = offset; i < words.length; i += n) {
            words[i] = StringUtils.capitalize(words[i]);
        }
        StringBuilder returnSentence = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            //  put back together
            returnSentence.append(words[i]).append(" ");
        }
        return returnSentence.toString().trim(); // trim trailing blank
    }

    // Determine if a number is prime
    public static Boolean isPrime(Integer n) {
        if (BigInteger.valueOf(n).isProbablePrime(1)) {
            // Only loop if it is a probable prime.
            for (int i = 3; i < (int) Math.sqrt(n) + 1; i += 2) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    // Calculate the golden ratio.
    // Given two numbers a and b with a > b > 0, the ratio is b / a.
    // Let c = a + b, then the ratio c / b is closer to the golden ratio.
    // Let d = b + c, then the ratio d / c is closer to the golden ratio.
    // Let e = c + d, then the ratio e / d is closer to the golden ratio.
    // If you continue this process, the result will trend towards the golden
    // ratio.
    public static Double goldenRatio(Double a, Double b) {
        //  there is a relationship between golden ration and Fibionacci
        double[] gold = new double[30];
        // stopping value = 30 iterations.
        gold[0] = a;
        gold[1] = b;
        double previousValue = 1d;
        double ratio = 0d;
        for (int i = 2; i < gold.length; i++) {
            gold[i] = gold[i - 2] + gold[i - 1];
            ratio = gold[i] / gold[i - 1];
            // another stopping value 1E-8
            if (Math.abs(ratio - previousValue) < 1E-8) {
                break;
            }
            previousValue = ratio;
        }
        return ratio;
    }

    // Give the nth Fibionacci number
    // Starting with 1 and 1, a Fibionacci number is the sum of the previous
    // two.
    public static Integer fibionacci(Integer n) {
        if (n < 0) {
            return -1;
        }
        if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        }
        // n > 2
        int fibCount = n;
        int[] fib = new int[fibCount + 1];
        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i <= fibCount; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }

    // Give the square root of a number
    // Using a binary search algorithm, search for the square root of a given
    // number.
    // Do not use the built-in square root function.
    public static Double squareRoot(final Double n) {
        double low = 0d;
        double high = n / 2d;
        // edge case numbers less than 1 squared get smaller
        if (high <= 1d) {
            low = high;
            high = n;
        }
        double root = (high + low) / 2d;
        double square = n;
        while (square != (root * root)) {
            // stopping criteria based on error difference.
            if (Math.abs(square - (root * root)) < 1E-15) {
                break;
            }
            if (root * root > square) {
                high = root;
                root = (root + low) / 2d;
            } else {
                low = root;
                root = (root + high) / 2d;
            }
        }
        return root;
    }
}
