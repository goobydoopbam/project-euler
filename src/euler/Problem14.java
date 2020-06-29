package euler;

import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;
/*
	+-------------------------------------------+
	|	Longest Collatz Sequence 				|
 	+-------------------------------------------+
	https://projecteuler.net/problem=14

	The following iterative sequence is defined for the set of positive integers:

		n → n/2 (n is even)
		n → 3n + 1 (n is odd)

	Using the rule above and starting with 13, we generate the following sequence:

		13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1

	It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms.
	Although it has not been proved yet (Collatz Problem), it is thought that all starting
	numbers finish at 1.

	Which starting number, under one million, produces the longest chain?

	NOTE: Once the chain starts the terms are allowed to go above one million.

	+-----------------------------------+
	|	Strategy 						|
 	+-----------------------------------+
	Written 6/28/2020. @author Gooby

	Brute force would be simple: compute the Collatz sequence of all numbers
	1 to 1000000 and check lengths. We can improve a bit on that by memoizing
	solutions to lower problems. E.g. whenever we encounter 16, we know that
	the remainder of the sequence will be 16 -> 8 -> 4 -> 2 -> 1 (length + 5).

	So map numbers to sequence length as we go, remembering the longest.

	Big considerations here are the terrifying size of the thing. Overflow is 
	the enemy, and we'll reach it sooner than we think. Relying on BigInts is a
	safe bet to guarantee we can get arbitrarily large before we find a power 
	of 2 and our value is repeatedly divided down to 1. 
*/

public class Problem14 {

	// finds longest Collatz sequence starting at values up to n
	public static int longestCollatzSequence(int n) {
		Map<BigInteger, Integer> sequences = new HashMap<>(); // map a value to the sequence length it leads to
		int maxLen = 0;
		int max = 1;
		for (int i = 1; i < n; i++) {
			int len = 1;
			BigInteger val = BigInteger.valueOf(i);
			while (!val.equals(BigInteger.ONE)) { // we'll be bold and assume that Collatz was correct
				if (val.and(BigInteger.ONE).equals(BigInteger.ZERO)) 
					val = val.divide(BigInteger.valueOf(2));
				else 
					val = val.multiply(BigInteger.valueOf(3)).add(BigInteger.ONE);

				if (sequences.containsKey(val)) { // if we've seen this number before 
					len += sequences.get(val);
					break;
				}
				else len++;
			}

			sequences.put(BigInteger.valueOf(i), len);
			if (len > maxLen) {
				maxLen = len;
				max = i;
			}
		}
		return max;
	}

	public static void printCollatzSequence(int n) {
		int lineLength = 80;
		int printCount = 0;
		BigInteger val = BigInteger.valueOf(n);
		while (!val.equals(BigInteger.ONE)) { // we'll be bold and assume that Collatz was correct
			if (val.and(BigInteger.ONE).equals(BigInteger.ZERO)) 
				val = val.divide(BigInteger.valueOf(2));
			else 
				val = val.multiply(BigInteger.valueOf(3)).add(BigInteger.ONE);
			System.out.print(val.toString() + "->");
			printCount += val.toString().length();
			if (printCount >= lineLength) { 
				System.out.println();
				printCount = 0;
			}
		}
		
		System.out.println(1); // again, assuming Collatz was right about all 
								// sequences ending at 1
	}

	public static void main(String[] args) {
		int start = longestCollatzSequence(1000000);
		System.out.println("The longest sequence starts at " + start + ": ");
		printCollatzSequence(start);
	}
}
