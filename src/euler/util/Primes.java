package euler.util;

public class Primes {
	// determines if a given n is prime or not
	public static boolean isPrime(int n) {
		if (n < 2) return false;
		if (n == 2) return true;
		for (int i = 2; i < 1 + Math.pow(n, 0.5); i++) {
			if (n % i == 0) return false;
		}
		return true;
	}
}