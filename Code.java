import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *   https://github.com/AthlinksEngineering/recruiting-codetest-1/
 *
 *   "recruiting-codetest" submission from Nick Ramirez - nick.ramirez@gmail.com
 */
public class Code {

    // Returns "Hello World!"
    public static String helloWorld() {
        return "Hello World!";
    }

    // Take a single-spaced <sentence>, and capitalize every <n> word starting with <offset>.
    public static String capitalizeEveryNthWord(String sentence, Integer offset, Integer n) {
        if (sentence == null) {
            throw new IllegalArgumentException("sentence may not be null");
        }

        String[] split = sentence.split(" ");

        StringBuilder sb = new StringBuilder(sentence.length());
        for (int i = 0; i < split.length; i++) {
            if (i > 0) sb.append(" ");
            String curWord = split[i];
            if (i >= offset && i % n == 0 && Character.isLowerCase(curWord.charAt(0))) {
                curWord = Character.toUpperCase(curWord.charAt(0)) + curWord.substring(1);
            }
            sb.append(curWord);
        }

        return sb.toString();
    }

    // Determine if a number is prime
    public static Boolean isPrime(Integer n) {

        if (n <= 1) {
            return false;
        }
        if (n != 2 && n % 2 == 0) {
            return false;
        }
        for (int i = n - 1; i > 1; i--) {
            //System.out.println("n="+n+ " i="+i+ " -> " + n % i);
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Calculate the golden ratio.
    // Given two numbers a and b with a > b > 0, the ratio is b / a.
    // Let c = a + b, then the ratio c / b is closer to the golden ratio.
    // Let d = b + c, then the ratio d / c is closer to the golden ratio.
    // Let e = c + d, then the ratio e / d is closer to the golden ratio.
    // If you continue this process, the result will trend towards the golden ratio.

    private static final double ONE_TRILLION = 1000000000000.0;

    public static Double goldenRatio(Double a, Double b) {
        //System.out.println("a="+ a + " b="+ b);
        double ratio = b / a;
        //System.out.println("ratio="+ ratio);
        if (a < ONE_TRILLION && b < ONE_TRILLION && Double.isFinite(a+b)) {
            return goldenRatio(b, a + b);
        } else {
            return ratio;
        }
    }

    // Give the nth Fibionacci number
    // Starting with 1 and 1, a Fibionacci number is the sum of the previous two.
    public static Integer fibionacci(Integer n) {

        if (n <= 0) {
            return 0;
        } else if (n <= 2) {
            // n=1 or n=2 are the first two 1's of the sequence
            return 1;
        }

        int fibIdx = 3; // idx 1 and 2 are the first two 1's, using idx's based at 1  { 1, 1, 2, 3, 5, 8, ... }
        int oneAgoFib = 1;
        int twoAgoFib = 1;
        int curFib;

        do {
            curFib = oneAgoFib + twoAgoFib;
            //System.out.println("fibIdx="+fibIdx+" curFib="+curFib);
            twoAgoFib = oneAgoFib;
            oneAgoFib = curFib;
            fibIdx++;
        } while (fibIdx <= n);

        return curFib;
    }

    // Give the square root of a number
    // Using a binary search algorithm, search for the square root of a given number.
    // Do not use the built-in square root function.
    public static Double squareRoot(Double n) {

        double start = 0;
        double end = n;

        double mid;
        double diff;
        do {
            // find "middle" for binary search
            mid = (start + end) / 2;
            double curSquare = mid * mid;
            diff = curSquare - n;
            //System.out.println("start=" + start + " mid=" + mid + " end=" + end + " curSquare=" + curSquare + " diff=" + diff);

            if (diff > 0) {
                end = mid;
            } else {
                start = mid;
            }

        } while (Math.abs(diff) > 0.00001); // Get within reasonable precision

        // Round to 4 decimal places
        BigDecimal bd = new BigDecimal(mid).setScale(4, RoundingMode.HALF_EVEN);
        //System.out.println("n="+n+ " sqrt="+bd.doubleValue());
        return bd.doubleValue();
    }
}
