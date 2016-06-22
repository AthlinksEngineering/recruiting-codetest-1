import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Code {

    // Returns "Hello World!"
    public static String helloWorld() {
        return "Hello World!";
    }

    // Take a single-spaced <sentence>, and capitalize every <n> word starting with <offset>.
    public static String capitalizeEveryNthWord(String sentence, Integer offset, Integer n) {
        // I'm making simplifyig assumptions here that words are separated by single
        // spaces, that offset is relative to the number or words found not the number
        // of nth matches, and that n is counted from the beginning of the sentence not from
        // the offset.  In a real situation, I would propose tests to clarify these
        // requirements.  I would probably also include apache commons for upcasing a word.
        List<String> words = Arrays.asList(sentence.split(" "));
        return IntStream.range(0, words.size())
                        .mapToObj(i -> (i >= offset) && (i % n == 0) ? ucFirst(words.get(i)) : words.get(i))
                        .collect(Collectors.joining(" "));
    }

    private static String ucFirst(String s) {
        return s.substring(0,1).toUpperCase() + s.substring(1);
    }

    // Determine if a number is prime
    public static Boolean isPrime(Integer n) {
        Map<Integer, Integer> sieve = new HashMap<>();
        for (int i = 2; i <= n; i++) {
            updateSieve(sieve, i);
        }
        return sieve.containsKey(n);
    }

    private static void updateSieve(Map<Integer, Integer> sieve, Integer i) {
        for (Integer key : sieve.keySet())
            if (sieve.get(key) < i)
                sieve.put(key, sieve.get(key) + key);
        if (!sieve.containsValue(i))
            sieve.put(i, i);
    }

    // Calculate the golden ratio.
    // Given two numbers a and b with a > b > 0, the ratio is b / a.
    // Let c = a + b, then the ratio c / b is closer to the golden ratio.
    // Let d = b + c, then the ratio d / c is closer to the golden ratio.
    // Let e = c + d, then the ratio e / d is closer to the golden ratio.
    // If you continue this process, the result will trend towards the golden ratio.
    public static Double goldenRatio(Double a, Double b) {
        // Just a note, since it doesn't support TCO, I avoid recursion in java unless I
        // know that it will terminate in some small number of calls. In the general case
        // here, I'm not sure that I could prove that so I'm just iterating.
        Double epsilon = 0.00005;
        while (Math.abs((b / a) - ((a + b) / b)) > epsilon) {
            b = a + b;
            a = b - a; // the original b
        }
        return ((a + b) / b);
    }

    // Give the nth Fibionacci number
    // Starting with 1 and 1, a Fibionacci number is the sum of the previous two.
    public static Integer fibionacci(Integer n) {
        List<Integer> fibs = new ArrayList<>(Arrays.asList(0, 1, 1));
        while (fibs.size() <= n)
            fibs.add(fibs.get(fibs.size() - 2) + fibs.get(fibs.size() - 1));
        return fibs.get(n);
    }

    // Give the square root of a number
    // Using a binary search algorithm, search for the square root of a given number.
    // Do not use the built-in square root function.
    public static Double squareRoot(Double n) {
        // The test fails for 25.0 because it is doing an exact match on 5.0.  I could fudge this to
        // make the test pass but doubles are inexact by definition, so that does not seem to me
        // to be the correct thing to do.
        if (n < 0) throw new IllegalArgumentException("Not handling imaginary numbers");

        // Same comment on recursion.
        Double epsilon = 10e-16, low = 0.0, high = n;
        while ((high - low) > epsilon) {
            Double mid = low + (high-low) / 2.0;
            if ((mid * mid) < n)
                low = mid;
            else
                high = mid;
        }
        return high;
    }
}
