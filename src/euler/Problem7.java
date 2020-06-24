package euler;
/*
	+-----------------------------------+
	|	10001st Prime					|
 	+-----------------------------------+
	https://projecteuler.net/problem=7

	By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.

	What is the 10 001st prime number?

	+-----------------------------------+
	|	Strategy 						|
 	+-----------------------------------+
	Written 6/24/2020. @author Gooby

 	Again, no fancy shortcuts or clever thinking here: this is just looping through every number, checking
 	it's prime-ness, and incrementing as needed. Our only time-saving improvements come in the way we check
 	the prime-ness of a given number; e.g. do we loop from 1 to n - 1, or can we terminate early?. The best
 	we can do here is check numbers only up to the square root of n; if none at or below the square root
 	evenly divide into n.

 	We're using ints since the first several million primes are all well within the bounds of 2^31 - 1.

 	Consideration: for especially large cases, we could consider the Prime Number Theorem, which puts an upper
 	and lower bound on the n-th prime like so:

 		n (log(n) + log(log(n)) - 1) < p_n < n (log(n) + log(log(n))), for n >= 6

 	However, there is no guarantee that *only* the n-th prime is within these bounds. We would therefore
 	need a good approximation of the prime-counting function up to the lower bound to have any sort of
 	certainty that we were returning the right number, and for integer n this may be overkill.

 	Still, for very large n, we could at least reliably look up the number of primes up to the power of 10
 	closest to our lower bound, and calculate from there.
*/

public class Problem7 {

	// determines if a given n is prime or not
	public static boolean isPrime(int n) {
		if (n < 2) return false;
		if (n == 2) return true;
		for (int i = 2; i < 1 + Math.pow(n, 0.5); i++) {
			if (n % i == 0) return false;
		}
		return true;
	}

	// finds the n-th prime number
	public static int findPrime(int n) {
		int numPrimes = 1; // start at 2
		int prime = 2;

		while (numPrimes < n) {
			prime++; // a truly robust solution would check for overflow and throw an exception for very large n
			if (isPrime(prime))
				numPrimes++;
		}
		return prime;
	}

	public static void main(String[] args) {
		System.out.println(findPrime(10001));
	}
}
