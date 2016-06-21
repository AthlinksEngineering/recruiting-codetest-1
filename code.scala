import scala.collection.immutable.Map

object Code {
    // Returns "Hello World!"
    def helloWorld() : String = "Hello World!"

    // Take a single-spaced <sentence>, and capitalize every <n> word starting with <offset>.
    def capitalizeEveryNthWord(sentence:String, offset:Integer, n:Integer) : String = {
        sentence.split(" ")
                .zipWithIndex
                .map {
                  case (value, key) if ((key >= offset) && (key % n == 0)) => value.capitalize
                  case (value, key) => value
                }
                .mkString(" ")
    }
    
    // Determine if a number is prime
    def isPrime(n:Integer) : Boolean = {
      def updateSieve(sieve:Map[Integer, Integer], current: Integer) : Map[Integer, Integer] = {
        val newSieve = sieve map {
          case(key, value) if value < current => key -> ((value + key): Integer)
          case(key, value) => key -> (value)
        }
        if (!newSieve.values.exists(_ == current)) (newSieve + (current -> current))
        else                                       newSieve
      }
      def isPrime(n:Integer, sieve:Map[Integer, Integer], current:Integer) : Boolean = {
        if (current > n) sieve.contains(n)
        else             isPrime(n, updateSieve(sieve, current), current + 1)
      }
      isPrime(n, Map(), 2)
    }
    
    // Calculate the golden ratio.
    // Given two numbers a and b with a > b > 0, the ratio is b / a.
    // Let c = a + b, then the ratio c / b is closer to the golden ratio.
    // Let d = b + c, then the ratio d / c is closer to the golden ratio. 
    // Let e = c + d, then the ratio e / d is closer to the golden ratio.
    // If you continue this process, the result will trend towards the golden ratio.
    def goldenRatio(a:Double, b:Double) : Double = {
      def goldenRatio(a:Double, b:Double, epsilon:Double) : Double = {
        if (Math.abs((b / a) - ((a + b) / b)) > epsilon) goldenRatio(b, a + b, epsilon)
        else                                             ((a + b) / b)
      }
      goldenRatio(a, b, 0.00005)
    }

    // Give the nth Fibonacci number
    // Starting with 0, 1, 1, 2, ... a Fibonacci number is the sum of the previous two.
    def fibonacci(n:Integer) : Integer = {
      lazy val fibs:Stream[Int] = 0 #:: fibs.scanLeft(1)(_ + _)
      fibs.drop(n).head
    }
    
    // Give the square root of a number
    // Using a binary search algorithm, search for the square root of a given number.
    // Do not use the built-in square root function.
    def squareRoot(n:Double) : Double = {
      def iterate(low:Double, high:Double, epsilon:Double) : Double = {
        if ((high - low) > epsilon) {
          val mid = low + ((high - low) / 2.0)
          if ((mid * mid) < n) iterate(mid, high, epsilon)
          else                 iterate(low, mid, epsilon)
        }
        else high
      }
      iterate(0, n, 10e-16)
    }
}
