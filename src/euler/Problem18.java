package euler;

/*
	+-----------------------------------+
	|	Maximum path sum I 				|
	+-----------------------------------+
	https://projecteuler.net/problem=18

	By starting at the top of the triangle below and moving to adjacent numbers
	on the row below, the maximum total from top to bottom is 23.

		   3
		  7 4
		 2 4 6
		8 5 9 3

	That is, 3 + 7 + 4 + 9 = 23.

	Find the maximum total from top to bottom of the triangle below:

		              75
		             95 64
		            17 47 82
		           18 35 87 10
		          20 04 82 47 65
		         19 01 23 75 03 34
		        88 02 77 73 07 63 67
		       99 65 04 28 06 16 70 92
		      41 41 26 56 83 40 80 70 33
		     41 48 72 33 47 32 37 16 94 29
		    53 71 44 65 25 43 91 52 97 51 14
		   70 11 33 28 77 73 17 78 39 68 17 57
		  91 71 52 38 17 14 91 43 58 50 27 29 48
		 63 66 04 68 89 53 67 30 73 16 69 87 40 31
		04 62 98 27 23 09 70 98 73 93 38 53 60 04 23

	NOTE: As there are only 16384 routes, it is possible to solve this problem 
	by trying every route. However, Problem 67, is the same challenge with a 
	triangle containing one-hundred rows; it cannot be solved by brute force, 
	and a clever method! ;o)

	+-----------------------------------+
	|	Strategy 						|
	+-----------------------------------+
	Written 6/30/2020. @author Gooby

	The brute force solution is simple. DFS for every path and return the max
	of all traversals. HOWEVER, the explataion indicates that we'll have to 
	find a much more reasonable strategy anyways, since we'll be working with
	a tree of height = 100 (meaning 2^99 possible paths). We should also note
	this is not a binary tree (each node feeds into each adjacent)

	So the basic improvement we can try is obvious; we simply return the max
	path at each iteration. Then, instead of recursing through each path, we
	visit each node once and calc the paths as we recurse up. To prevent
	overlapping paths, we remove the children after we calc it the first time
	and set the value of our current node to the max path.

	e.g. the starting tree would look like

			  03
			 07 04
			10 13 15

	after the first pass.

	A major concern of this strategy is the memory required to build this list
	of arrays into a tree. For h=100, we have sum(1->100) nodes, which is ~5k.
	Not unmanageable, but not great. Instead, we could just treat the inputs as
	arrays and establish the relation that for the a given row, the i-th cell
	has children at i and i + 1 in the next row (if there is a next row).

	So our strategy is to compute the max path from the bottom - 1 row up, just
	saving the max path in that array as we go up.
*/

public class Problem18 {
	
	public static int maxPathSum(int[][] input) {
		for (int i = input.length - 2; i >= 0; i--) 
			for (int j = 0; j < input[i].length; j++) 
				input[i][j] += Math.max(input[i + 1][j], input[i + 1][j + 1]);

			
		return input[0][0];
	}
	
	public static void main(String[] args) {

		// no fancy input processing, just copy-paste this as an int[][]
		int[][] input = {
			{75},
			{95, 64},
			{17, 47, 82},
			{18, 35, 87, 10},
			{20,  4, 82, 47, 65},
			{19,  1, 23, 75,  3, 34},
			{88,  2, 77, 73,  7, 63, 67},
			{99, 65,  4, 28,  6, 16, 70, 92},
			{41, 41, 26, 56, 83, 40, 80, 70, 33},
			{41, 48, 72, 33, 47, 32, 37, 16, 94, 29},
			{53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14},
			{70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57},
			{91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48},
			{63, 66,  4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31},
			{04, 62, 98, 27, 23,  9, 70, 98, 73, 93, 38, 53, 60,  4, 23}
		};

		System.out.println(maxPathSum(input));
	}
}
