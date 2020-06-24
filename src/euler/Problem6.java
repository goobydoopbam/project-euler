package euler;
/*
	+-----------------------------------+
	|	Sum Square Difference			|
 	+-----------------------------------+
	https://projecteuler.net/problem=6

	The sum of the squares of the first ten natural numbers is,

		1^2 + 2^2 + ... + 10^2 = 385

	The square of the sum of the first ten natural numbers is,

		(1 + 2 + ... + 10)^2 = 552 = 3025

	Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.

	Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.

	+-----------------------------------+
	|	Strategy 						|
 	+-----------------------------------+
	Written 6/24/2020. @author Gooby

 	Basic strategy here is simple: loop from 1 to n, summing squares and the running sum. Then square the sum
 	and take the difference of the squared sum and summed squares.

 	Nothing too fancy, except a consideration for overflow, which is fairly tolerable if we use longs vs ints.
*/

public class Problem6 {

	// finds the difference of sum squares of all numbers 1..n
	public static long diffSumSquare(int n) {
		long sum = 0;
		long squares = 0;

		for (long i = 1; i <= n; i++) {
			sum += i;
			squares += i * i;
		}
		return sum * sum - squares;
	}

	public static void main(String[] args) {
		System.out.println(diffSumSquare(100));
	}
}
