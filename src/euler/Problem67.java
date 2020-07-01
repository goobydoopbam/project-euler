package euler;

import java.io.BufferedReader;
import java.io.FileReader;
/*
	+-----------------------------------+
	|	Maximum path sum II				|
	+-----------------------------------+
	https://projecteuler.net/problem=67

	By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.

		   3
		  7 4
		 2 4 6
		8 5 9 3

	That is, 3 + 7 + 4 + 9 = 23.

	Find the maximum total from top to bottom in triangle.txt (right click and 
	'Save Link/Target As...'), a 15K text file containing a triangle with 
	one-hundred rows.

	NOTE: This is a much more difficult version of Problem 18. It is not 
	possible try every route to solve this problem, as there are 2^99 
	altogether! If you could check one trillion (1012) routes every second it
	would take over twenty billion years to check them all. There is an 
	efficient algorithm to solve it. ;o)

	+-----------------------------------+
	|	Strategy 						|
	+-----------------------------------+
	Written 6/30/2020. @author Gooby

	This is just a copy of our solution for Problem 18, which we designed to be
	a solution to this problem as well.

	Only change is an adapter to read the input from a file, as 100 rows is a
	fair bit to manually input.
*/

public class Problem67 {
	
	public static int maxPathSum(int[][] input) {
		for (int i = input.length - 2; i >= 0; i--) 
			for (int j = 0; j < input[i].length; j++) 
				input[i][j] += Math.max(input[i + 1][j], input[i + 1][j + 1]);

			
		return input[0][0];
	}
	
	public static int[][] buildInput(String path)  {
		int[][] input = new int[100][];
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			int row = 0;
			while((line = br.readLine()) != null) {
				String[] entries = line.split(" ");
				input[row] = new int[entries.length];
				for (int col = 0; col < entries.length; col++) {
					input[row][col] = Integer.parseInt(entries[col]);
				}
				row++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return input;
	}

	public static void main(String[] args) {

		// fancy input makes more sense now, since this is a lot to format
		String path = System.getProperty("user.dir") + "/inputs/p067_triangle.txt";
		int[][] input = buildInput(path);

		System.out.println(maxPathSum(input));
	}
}
