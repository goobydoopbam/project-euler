package euler;
/*
	+-----------------------------------+
	|	Largest Palindromic Product		|
 	+-----------------------------------+
	https://projecteuler.net/problem=4

	A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.

	Find the largest palindrome made from the product of two 3-digit numbers.

	+-----------------------------------+
	|	Strategy 						|
 	+-----------------------------------+
	Written 6/23/2020. @author Gooby

 	Brute force could work; simply test every pair of numbers between 100 and 999 and check if the number is a palindrome (comparing / and % 10s or string manipulation)

 	Better would be to do a bit of mathematical reasoning to eliminate some of the numbers.

	In the simplest case for a palindromic number where n = 1, we'd be looking for something of the form aa, where a is a specific digit.
	It is easy to see that this is not possible; no two single digit numbers can be multiplied to produce a result of the form 11 * a.

	For n = 2, like in the given example, we are considering numbers between 10^2 and 99^2, so they must be of the form abba, where a,b are digits.
	In another form, 1001 * a + 110 * b = 11 (91a + 10b).

 	If we think about the n = 3 case, they are of the form abccba, where a,b,c are all single digits. In other terms,
 	for n = 3 we could write this as 100000 * a + 10000 * b + 1000 * c + 100 * c + 10 * b + a = 100001a + 10010b + 1100c = 11 * (9091a + 910b + 100c)

 	Similarly, for n = 4 this would amount to 11 * (909091a + 90910b + 9100c + 1000d).

 	So we are looking for numbers between 100^2 and 999^2 that are the product of two numbers (x * y) that satisfy this formula.
 	Solving the formula for every combination of numbers is a tough sell performance-wise, but we can take a hint from it to 
 	reduce our search space: we only need to consider a pair if one (and only one) of the two numbers is a multiple of 11.

 	Now our nested for loop can decrement one of the counters by 11 instead of 1, and we can further improve things by only checking numbers 
*/

public class Problem4 {
	
	public static boolean isPalindrome(int num) {
		int lo = 0;
		int hi = num;

		while (hi > 0) {
			lo = lo * 10 + hi % 10; // reverses num
			hi = hi / 10;
			if (lo == hi) return true; // can only be true at the halfway point
		}
		return lo == num; // return false here is just as correct; if the numbers don't match at the halfway point, they can't be the same later
	}

	// finds the largest product of two n-digit factors
	public static int palindromicProduct(int n) {
		// find upper and lower bounds
		int upper = 9;
		int lower = 1;
		for (int i = 1; i < n; i++) {
			upper = upper * 10 + 9;
			lower *= 10;
		}

		int x = upper;
		while (x % 11 != 0) // find the highest multiple of 11 within our bounds
			x--;

		int max = 0;

		// for every multiple of 11, check it against every other number
		for (; x > lower - 1; x -= 11) {
			int product = 0;
			for (int y = upper; y > lower - 1; y--) {
				// we could check y's divisibility by 11 and skip if it == 0, but that is a very marginal improvement
				product = x * y;
				if (product > max && isPalindrome(product)) {
					max = product;
					break; // no other product will be greater than this for our combination of numbers
				}
				else if (product < max) break; // if the current product is too low, decreasing the value of the factors cannot increase it
			}
		}

		return max;
	}

	public static void main(String[] args) {
		System.out.println(palindromicProduct(3));
	}
}
