package euler;

import euler.util.Primes;

/*
	+-----------------------------------+
	|	Summation of Primes				|
 	+-----------------------------------+
	https://projecteuler.net/problem=10

	The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.

	Find the sum of all the primes below two million.

	+-----------------------------------+
	|	Strategy 						|
 	+-----------------------------------+
	Written 6/24/2020. @author Gooby

 	Obvious strategy involves looping through all numbers 1 .. n and if they are prime adding them to the
 	sum. This is obviously expensive (O(n^3/2) worst case) but unless we have a source of primes in memory,
 	dynamically computing them is our best bet.
*/

public class Problem10 {

	public static long sumOfPrimes(int n) {
		long sum = 0;
		for (int i = 2; i <= n; i++) {
			if (euler.util.Primes.isPrime(i))
				sum += i;
		}
		return sum;
	}

	public static void main(String[] args) {
		System.out.println(sumOfPrimes(2000000));
	}
}
