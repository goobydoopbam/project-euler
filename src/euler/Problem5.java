package euler;
/*
	+-----------------------------------+
	|	Smallest Multiple				|
 	+-----------------------------------+
	https://projecteuler.net/problem=5

	2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.

	What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?

	+-----------------------------------+
	|	Strategy 						|
 	+-----------------------------------+
	Written 6/23/2020. @author Gooby

 	2520 is divisible by 10, 9, 8, 7, 6 (already b/c 10 and 9), 5 (already, b/c of 10), 4 (already, b/c of 8), 3 (already, b/c 6 and 9), 
 	and 2 (b/c 10 and 8 and 6).

 	The clear trend from above is that the result simply needs to be evenly divisible by all the factors of the numbers from 1 to n. The
 	question then becomes how do we programmatically generate this list of factors? How do we determine the smallest multiple from said
 	list?

 	Brute force involves simply counting up and verifying that we are checking every number's divisibility. This is an atrocious idea
 	from a runtime perspective, as this would require n checks for every number between 1 and our result (almost certainly orders of
 	magnitude greater than n).

 	A sleeker solution involves factoring our numbers 1..n and multiplying the factors together to get our result.

 	To explain, let's consider the given example, where n = 10:
 		10 factors into 2 and 5
 		9  factors into 3 and 3
 		8  factors into 2 and 2 and 2
 		7  factors into 7
 		6  factors into 2 and 3
 		5  factors into 5
 		4  factors into 2 and 2
 		3  factors into 3
 		2  factors into 2

 	Since we see a max of three factors of 2, two factors of 3, and one of 5 and 7, we multiply those all together (2 * 2 * 2 * 3 * 3 * 5 * 7)
 	and behold! Our result is 2520, the guaranteed smallest multiple of all our input factors.

 	Our strategy is solid, all that remains is a reliable way to factor the inputs.
*/

public class Problem5 {

	// finds the smallest multiple of all numbers 1..n
	public static int smallestMultiple(int n) {
		int[] counts = new int[n + 1];

		// let's adapt our logic from Problem3 to count how many times we encounter each factor in each number
		for (int i = 2; i <= n; i++) {
			int num = i;
			for (int factor = 2; factor <= num; factor++) {
				int count = 0;
				while (num % factor == 0) {
					num /= factor; 
					count++;
				}
				if (counts[factor] < count)
					counts[factor] = count; // save the greater of the two values
			}
		}

		int res = 1;
		for (int i = 2; i <= n; i++) {
			while (counts[i] > 0) {
				res *= i;
				counts[i]--;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		System.out.println(smallestMultiple(10));
		System.out.println(smallestMultiple(20));
	}
}
