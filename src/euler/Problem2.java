package euler;
/*
	+-----------------------------------+
	|	Even Fibonacci Numbers			|
 	+-----------------------------------+
	https://projecteuler.net/problem=2

	Each new term in the Fibonacci sequence is generated by adding the previous two terms. By starting with 1 and 2, the first 10 terms will be:

		1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...

	By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.

	+-----------------------------------+
	|	Strategy 						|
 	+-----------------------------------+
	Written 6/22/2020. @author Gooby

 	Again we could brute force through every Fibonacci number, checking mod 2 and summing the evens. A more efficient
 	solution involves determining a relationship between the even Fibonacci numbers. A basic examination of the sequence
 	shows us that every 3rd number in the Fibonacci sequence is even (the sum of two odds is even):

 		0 1 1 2 3 5 8 13 21 34 55 89 144 ...

 	So we work to establish a relation between the last two even numbers and the next.

 	The obvious relation here is 4 * fib_even(n - 1) + fib_even(n - 2); e.g. 4 * 2 + 0 = 8, 4 * 8 + 2 = 34, ...

	The solution then is 4613730.
*/

public class Problem2 {
	
	public static int sum(int upperBound) {
		int n1 = 2;
		int n0 = 0;

		int sum = 2;

		while (4 * n1 + n0 <= upperBound) {
			int temp = n1;
			n1 = 4 * n1 + n0;
			n0 = temp;
			sum += n1;
		}

		return sum;
	}

	public static void main(String[] args) {
		System.out.println(sum(4000000));
	}
}
