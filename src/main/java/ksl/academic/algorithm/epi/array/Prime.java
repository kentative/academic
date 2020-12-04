package ksl.academic.algorithm.epi.array;

import java.util.ArrayList;
import java.util.List;

public class Prime {

    public static void main(String[] args) {

        int n = 10;
        List<Integer> findPrime = findPrime(n);
        findPrime.stream().forEach(x -> System.out.println(x));

        System.out.println();
        System.out.println();

        List<Integer> p2 = prime2(n);
        p2.stream().forEach(x -> System.out.println(x));

        System.out.println();
        System.out.println();

        List<Integer> p3 = sieve(n);
        p3.stream().forEach(x -> System.out.println(x));

    }

    static List<Integer> findPrime(int n) {

        boolean[] result = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            result[i] = isPrime(i);
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 0; i < result.length; i++) {
            if (result[i])
                primes.add(i);
        }
        return primes;
    }

    static boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if ((n % i) == 0)
                return false;
        }
        return true;
    }

    static List<Integer> prime2(int n) {

        // initially assume all integers are prime
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        int count = 0;
        // mark non-primes <= n using Sieve of Eratosthenes
        for (int factor = 2; factor * factor <= n; factor++) {

            count++;
            // if factor is prime, then mark multiples of factor as nonprime
            // suffices to consider mutiples factor, factor+1, ..., n/factor
            if (isPrime[factor]) {
                for (int j = factor; factor * j <= n; j++) {
                    isPrime[factor * j] = false;
                    count++;
                }
            }
        }

        System.out.println("prime2 count " + count);

        List<Integer> primes = new ArrayList<>();
        for (int i = 0; i < isPrime.length; i++) {
            if (isPrime[i])
                primes.add(i);
        }
        return primes;
    }

    static List<Integer> sieve(int n) {

        // false = prime
        // true  = multiple of primes (i.e. not prime)
        boolean[] marked = new boolean[n + 1];

        for (int x = 2; x <= Math.sqrt(n); x++) {
            if (!marked[x]) {
                for (int j = x * x; j <= n; j += x) {
                    marked[j] = true;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            if (!marked[i]) primes.add(i);
        }
        return primes;
    }

}
