import java.util.*;

public class Main {

    private static Scanner scn = new Scanner(System.in);
    private static boolean[] primes;
    private static ArrayList<Integer> semiPrimes;
    private static int largestInput;

    public static void main(String[] args) {
        //helu there
        int t = scn.nextInt();
        String[] results = new String[t];

        int[] semiPrimeInputs = new int[t];

        for (int i = 0; i < t; i++) {
            semiPrimeInputs[i] = scn.nextInt();
        }

        largestInput = maxOfArray(semiPrimeInputs);
       //returns a list of semi primes
        semiPrimes = new ArrayList<>();
        primes = new boolean[(int) Math.sqrt(largestInput) + 1];

        fillSieveOfEratosthenes(primes.length);
        primes[0] = false;

        generateSemiPrimes();

        for (int i = 0; i < t; i++) {
            results[i] = chefAndSemiPrimes(semiPrimeInputs[i]);
        }

        for (int i = 0; i < t; i++) {
            System.out.println(results[i]);
        }
    }

    private static int qualPreElim() {
        int n = scn.nextInt();
        int k = scn.nextInt();
        int qual = k;

        int[] scores = new int[n];
        for (int i = 0; i < n; i++) {
            scores[i] = scn.nextInt();
        }

        Arrays.sort(scores);
        int val = scores[n - k];
        for (int i = n - k - 1; i >= 0; i--) {
            if (scores[i] == val) {
                qual++;
            } else {
                break;
            }
        }

        return qual;
    }

    private static void generateSemiPrimes() {
        int semiPrime;

        for (int i = 1; i < primes.length; i++) {
            for (int j = i + 1; j < primes.length; j++) {
                if (primes[i - 1] && primes[j - 1]) {
                    semiPrime = i * j;
                    if (!semiPrimes.contains(semiPrime)) {
                        semiPrimes.add(semiPrime);
                    }
                }
            }
        }
    }

    private static String chefAndSemiPrimes(int num) {

        if (num < 12) {
            return "NO";
        }

        int sum;
        for (int sp1 : semiPrimes) {
            for (int sp2 : semiPrimes) {
                sum = sp1 + sp2;
                if (sum == num) {
                    return "YES";
                } else if (sum > num) {
                    break;
                }
            }
        }
        return "NO";
    }

    private static int maxOfArray(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            if (max < num) {
                max = num;
            }
        }

        return max;
    }

    private static void fillSieveOfEratosthenes(int n) {
        for (int i = 0; i < n; i++) {
            primes[i] = true;
        }

        for (int p = 2; p * p <= n; p++) {
            // If prime[p] is not changed, then it is a prime
            if (primes[p - 1] == true) {
                // Update all multiples of p
                for (int i = p * 2; i <= n; i += p)
                    primes[i - 1] = false;
            }
        }
    }

}
