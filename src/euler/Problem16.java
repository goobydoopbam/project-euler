package euler;

import java.math.BigInteger;
/*
	+-----------------------------------+
	|	Power Digit Sum					|
 	+-----------------------------------+
	https://projecteuler.net/problem=16

	2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.

	What is the sum of the digits of the number 2^1000?

	+-----------------------------------+
	|	Strategy 						|
 	+-----------------------------------+
	Written 6/29/2020. @author Gooby

	No clever relation here, I'm afraid, just actually calculating that value
	and summing the digits. BigInteger to the rescue again.
*/

public class Problem16 {
	
	public static int sumPowerDigits(int n) {
		BigInteger pow = BigInteger.ONE.shiftLeft(n);
		int sum = 0;
		while (pow.compareTo(BigInteger.ZERO) > 0) {
			sum += pow.remainder(BigInteger.TEN).intValue();
			pow = pow.divide(BigInteger.TEN);
		}

		return sum;
	}
	
	public static void main(String[] args) {
		System.out.println(sumPowerDigits(1000));
	}
}
