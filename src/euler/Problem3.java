package euler;
/*
	+-----------------------------------+
	|	Largest Prime Factor			|
 	+-----------------------------------+
	https://projecteuler.net/problem=3

	The prime factors of 13195 are 5, 7, 13 and 29.

	What is the largest prime factor of the number 600851475143?

	+-----------------------------------+
	|	Strategy 						|
 	+-----------------------------------+
	Written 6/22/2020. @author Gooby

 	Some interesting points here: our input is larger than a 32-bit int, so we're working with longs (or larger in a general case).

 	Brute force says check all prime numbers less than the number and check which is the largest to divide evenly into our given n.

 	Any solution involves a way to find primes up to n, or simply start from lower numbers (e.g. n % 4 won't be 0 if we've already 
 	factored out 2). 
*/

public class Problem3 {
	
	public static long largestFactor(long n) {
		long factor;
		for (factor = 2; factor <= n; factor++) {
			while (n % factor == 0)
				n /= factor;
		}
		return factor - 1; // negate the factor++ for the largest number (e.g. 6 % 2 = 0 so 6 / 2 = 3 and 2++; 3 % 3 = 0 so 3 / 3 = 1 and 3++; 4 > 1 so we exit the loop)
	}

	public static void main(String[] args) {
		System.out.println(largestFactor(600851475143L));
	}
}
