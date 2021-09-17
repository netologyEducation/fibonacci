import java.math.BigInteger;
import java.util.stream.Stream;

public class Fibonacci {

    public long getFibonacciNumber(int n) {
        return Stream.iterate(new long[]{0, 1}, t -> new long[]{t[1], t[0] + t[1]})
                .limit(n)
                .map(t -> t[0])
                .mapToLong(Long::longValue)
                .sum();
    }
}
