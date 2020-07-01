package euler;

import java.util.Map;
import java.util.HashMap;
/*
	+-----------------------------------+
	|	Number Letter Counts			|
	+-----------------------------------+
	https://projecteuler.net/problem=17

	If the numbers 1 to 5 are written out in words: one, two, three, four,
	five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.

	If all the numbers from 1 to 1000 (one thousand) inclusive were written out
	in words, how many letters would be used?

	NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and
	forty-two) contains 23 letters and 115 (one hundred and fifteen) contains
	20 letters. The use of "and" when writing out numbers is in compliance with
	British usage.

	+-----------------------------------+
	|	Strategy 						|
	+-----------------------------------+
	Written 6/30/2020. @author Gooby

	We need a map of numbers to their respective words and a system for pulling
	out the power of ten. So when we come across 967, we need to write out:

		nine (hundred), six(ty), seven

	plus the 'and' to connect the hundreds place to the tens and below, &c.

	Of course, we don't actually need to count the chars, we can just save the
	number of chars in our map.

	So for every number, we look up each digit (with its power of ten), and sum
	those lengths. If we have a digit in the 1000s or 100s AND a digit in the
	10s or 1s, we add an "and" (or three characters).

	e.g. for 964, we'd check 900, 60, 4 and add 3 for the 'and'

	For arbitrarily large inputs, we could basically repeat this process (sans
	the 'and') for each group of three digits. 

	e.g. for 245943, we'd check 900, 40, 3 (+ 'and') and then consier
					 200, 40, 5 + ('thousand') 

	We could also try a recursive solution, but that's probably less efficient.
	The major effiency gain here would be to switch strings for saving the 
	char count for each word, but that isn't as easily reusable.

	We could also save each count we calculate (e.g. save 94 as 10 chars) and
	always check if we've seen a number before (so we only calc 94 once, then
	look it up for 194, 294, 394, etc...). However, gains for n=1000 are likely
	negligible.
*/

public class Problem17 {
	
	private static String[] digits = {
		"", "one", "two", "three", 
		"four", "five", "six", 
		"seven", "eight", "nine"
	};

	private static String[] tens = {
		"", "", "twenty", "thirty", "forty", "fifty", "sixty",
		"seventy", "eighty", "ninety"
	};

	private static String[] teens = {
		"ten", "eleven", "twelve", "thirteen", "fourteen",
		"fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
	};

	private static String[] powers = {
		"", "", "hundred", "thousand", "million", "billion"
	};

	private static String numToWord(int n) {
		if (n == 0) return "";
		if (n > 9 && n < 20) return teens[n - 10];
		if (n > 19 && n < 100) return tens[n / 10] + numToWord(n % 10);
		int pow = 0;
		int d = n;
		while (d / 10 > 0){
			pow++;
			d /= 10;
		}
		n -= d * Math.pow(10, pow);
		if (n != 0 && pow == 2)
			return digits[d] + powers[pow] + "and" + numToWord(n);
		else if (n != 0)
			return digits[d] + powers[pow] + numToWord(n);
		else return digits[d] + powers[pow];
	}

	public static int letterCountsRecursive(int n) {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += numToWord(i).length();
		}
		return sum;
	}

	///////////////////////////////////////////////////////////////////////////
	//
	//	the above is a recursive solution that builds the string representation
	//	of each word, then sums their lengths. It is probably reusable, but is
	//	not terribly efficient. Also not a fan of the global variables...
	//
	//	Below is the map-based approach described above, which is much faster
	//	and tidier.
	//
	///////////////////////////////////////////////////////////////////////////

	public static int letterCountsMap(int n) {
		Map<Integer, String> powers = new HashMap<>();
		powers.put(100, "hundred");
		powers.put(1000, "thousand");
		powers.put(1000000, "million");
		powers.put(1000000000, "billion");

		Map<Integer, String> counts = new HashMap<>();
		counts.put(0, "");
		counts.put(1, "one");
		counts.put(2, "two");
		counts.put(3, "three");
		counts.put(4, "four");
		counts.put(5, "five");
		counts.put(6, "six");
		counts.put(7, "seven");
		counts.put(8, "eight");
		counts.put(9, "nine");
		counts.put(10, "ten");
		counts.put(11, "eleven");
		counts.put(12, "twelve");
		counts.put(13, "thirteen");
		counts.put(14, "fourteen");
		counts.put(15, "fifteen");
		counts.put(16, "sixteen");
		counts.put(17, "seventeen");
		counts.put(18, "eighteen");
		counts.put(19, "nineteen");
		counts.put(20, "twenty");
		counts.put(30, "thirty");
		counts.put(40, "forty");
		counts.put(50, "fifty");
		counts.put(60, "sixty");
		counts.put(70, "seventy");
		counts.put(80, "eighty");
		counts.put(90, "ninety");

		int pow = 1;
		int sum = 0;

		for (int i = 1; i < 20; i++) { // unique cases
			sum += counts.get(i).length();
		}

		for (int i = 20; i <= n; i++) {
			int num = i;
			pow = 1;
			while (num / (10 * pow) > 0)
				pow *= 10;
			while (num > 0) {
				if (num < 20) {
					sum += counts.get(num).length(); // 1-19 are all unique
					break;
				}
				else if (num >= 20 && num < 100) {
					sum += counts.get((num / 10) * 10).length(); // only add first digit
					sum += counts.get((num % 10)).length();
					break;
				}
				else if (num >= 100 && num < 1000 && num % 100 > 0)
					sum += 3; // add three for 'and' to join hundreds and tens

				sum += counts.get(num / pow).length() + powers.get(pow).length();
				num %= pow;
				pow /= 10;
			}
		}

		return sum;
	}
	
	public static void main(String[] args) {
		System.out.println(letterCountsMap(1000));
		System.out.println(letterCountsRecursive(1000));
	}
}
