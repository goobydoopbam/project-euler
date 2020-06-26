package euler;
/*
	+-----------------------------------+
	|	Special Pythagorean Triplet		|
 	+-----------------------------------+
	https://projecteuler.net/problem=9

	A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
		a2 + b2 = c2

	For example, 32 + 42 = 9 + 16 = 25 = 52.

	There exists exactly one Pythagorean triplet for which a + b + c = 1000.
	Find the product abc.

	+-----------------------------------+
	|	Strategy 						|
 	+-----------------------------------+
	Written 6/24/2020. @author Gooby

	This is a bit of algebra to solve and -- given the special characteristics of 
	this specific triplet -- not necessarily easy to generalize. 

	Solving this problem rests on determining the values of a, b, and c. Brute force
	would involve checking every combination of numbers a,b up to 1000 and verifying
	them one at a time. 

	Improvements on that aren't difficult to imagine. Since a < b, for each value of
	a that we check, we start b at a + 1. We can then calculate the value of c, and
	while b + a is less than sum - b - a, we can check if our a,b,c triplet satisfies
	the Pythagorean formula. Since a + b + c = n, and a < b < c, we know a must be 
	less than n / 3 (otherwise b = a or b = c, which cannot be true). So we have
	an upper bound for a and b.
*/

public class Problem9 {
	
	public static int pythagoreanTriple(int n) {
		for (int a = 1; a < n / 3; a++) {
			for (int b = a + 1; b < n - b - a; b++) {
				int c = n - b - a;
				if (a * a + b * b == c * c) { // if this satisfies the Pythagorean
					System.out.println("A: " + a + " B: " + b + " C: " + c);
					return a * b * c;
				}
			}
		}
		return -1; // signifies that no Pythagorean triple for this sum exists
	}

	public static void main(String[] args) {
		System.out.println(pythagoreanTriple(1000));
	}
}
