package euler;
/*
	+-----------------------------------+
	|	Lattice paths					|
 	+-----------------------------------+
	https://projecteuler.net/problem=15

	Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are exactly 6 routes to the bottom right corner.

	How many such routes are there through a 20×20 grid?

	+-----------------------------------+
	|	Strategy 						|
 	+-----------------------------------+
	Written 6/29/2020. @author Gooby

	They're talking about a 2x2 grid, traversing the edges, so really a 3x3 grid.
	This happens to be the LeetCode daily challenge for today, so the same strat
	applies. (For problem, see: https://leetcode.com/problems/unique-paths/)

	We're counting paths using dynamic programming, nothing really surprising
	here. Only odd notes are the 20x20 becomes 21x21 (edges vs faces) AND the
	size of the grid can (and does) make our answer very big. Where the LC 
	problem guarantees your result will fit in an int, this problem (wisely)
	makes no such assertion. Our result of 137,846,528,820 is well above 2e9.
*/

public class Problem15 {
	
	public static long countPaths(int m, int n) {
		long[][] dp = new long[m + 1][];
		for (int i = 0; i <= m; i++)
			dp[i] = new long[n + 1];

		dp[m - 1][n] = 1; // dp[m][n-1] works too

		for (int i = m - 1; i >= 0; i--)
			for (int j = n - 1; j >= 0; j--)
				dp[i][j] = dp[i + 1][j] + dp[i][j + 1];

		return dp[0][0];
	}
	
	public static void main(String[] args) {
		System.out.println(countPaths(21, 21));
	}
}
