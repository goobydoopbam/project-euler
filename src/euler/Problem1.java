package euler;
/*
	+-----------------------------------+
	|	Multiples of 3 and 5			|
 	+-----------------------------------+
	https://projecteuler.net/problem=1

	If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.

	Find the sum of all the multiples of 3 or 5 below 1000.

	+-----------------------------------+
	|	Strategy 						|
 	+-----------------------------------+
	Written 6/22/2020. @author Gooby

 	Brute force would be to loop through all numbers up to 1000, check mod 3 and mod 5 and add if they were multiples.
 	We can shave this down by counting up from n = 0 while n * 3 is still less than 1000 and add n * 3 and n * 5 until n * 5 > 1000.
 	We also need a check to ensure that we don't double count multiples of 3 AND 5.

 	A sneakier, non iterative strategy could rely on the fact that 1 + 2 + 3 + ... + N = N * (N + 1) / 2.
 	Then we're just calculating the sum of all multiples of 3, all multiples of 5, and all their shared multiples (15 * n),
 	and calculating m3 + m5 - m15.

 	Both of these solutions yield the same result for total=1000: 233168.
*/

public class Problem1 {
	
	public static int iterative(int total) {
		int sum = 0;

		int n = 1;

		while (n * 3 < 1000) {
			if ((n * 3) % 5 != 0) 
				sum += n * 3;
			if ((n * 5) < 1000)
				sum += n * 5;
			n++;
		}
		return sum;
	}

	public static int triangular(int total) {
		int n3 = (total - 1) / 3;
		int m3 = 3 * (n3 * (n3 + 1) / 2);

		int n5 = (total - 1) / 5;
		int m5 = 5 * (n5 * (n5 + 1) / 2);

		int n15 = (total - 1) / 15;
		int m15 = 15 * (n15 * (n15 + 1) / 2);

		return m3 + m5 - m15;
	}

	public static void main(String[] args) {
		System.out.println(iterative(1000));
		System.out.println(triangular(1000));
	}
}
