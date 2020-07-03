package euler;

/*
	+-----------------------------------+
	|	Counting Sundays 				|
	+-----------------------------------+
	https://projecteuler.net/problem=19

	You are given the following information, but you may prefer to do some research for yourself.

	1 Jan 1900 was a Monday.
	Thirty days has September,
	April, June and November.
	All the rest have thirty-one,
	Saving February alone,
	Which has twenty-eight, rain or shine.
	And on leap years, twenty-nine.

	A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
	How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?

	+-----------------------------------+
	|	Strategy 						|
	+-----------------------------------+
	Written 7/2/2020. @author Gooby

	Choosing to ignore the odd assertion that 2000 is part of the 20th c...

	You could brute force this by counting every day from Jan 1, 1901 to Dec 31, 2000.
	Better would be to count the Sundays in a given year from the start day (e.g. years
	starting with Sunday have 53 Sundays, as do leap years starting on Saturday, all 
	others are common years with 52 Sundays). 

	Alternatively just use the built in time/date classes to check if the first of
	each month of each year is a Sunday. This is probably a better general solution,
	as it becomes easier to update for other years.

	So it is number of years * 52 + number of years with Sunday starts + number of leap
	years starting with Saturday. Starting with a year that begins on a Tuesday (1901),
	we can calculate the number of Sundays in the year for each year that way.

	Same logic applies to finding months with a Sunday start. Either consider each
	month for each year, or do a bit of math ahead of time to sort out our 14 cases:
		one case for a common year starting with each day
			-Sunday -> 2 months start on Sunday
			-Monday -> 2 months start on Monday
			-Tues -> 2 months starts on Sunday
			-Wed -> 1 months start on Sunday
			-Thurs -> 3 months start on Sunday
			-Fri -> 1 month starts on Sunday
			-Sat -> 1 month starts on Sunday
		one case for a leap year starting with that day
			-Sunday -> 3 months start on Sunday
			-Monday -> 2 months start on Monday
			-Tues -> 1 months start on Sunday
			-Wed -> 2 month starts on Sunday
			-Thurs -> 2 months start on Sunday
			-Fri -> 1 month starts on Sunday
			-Sat -> 1 month starts on Sunday
*/

public class Problem19 {
	
	public static int countSundays(int endYear) {
		int startingDay = 1; // 1900 starts on Monday

		int sum = 0;

		int[] commonYears = {2, 2, 2, 1, 3, 1, 1};
		int[] leapYears =	{3, 2, 1, 2, 2, 1, 1};
	

		for (int yr = 1901; yr <= endYear; yr++) {
			startingDay = (startingDay + 1) % 7;
			
			// leap years skip a starting day and - if starting with Sat.- add a Sunday
			if (yr % 4 == 0 && (yr % 100 != 0 || yr % 400 == 0)) {
				sum += leapYears[startingDay];
				startingDay++;
			}
			else {
				sum += commonYears[startingDay];
			}
		}
		return sum;
	}
	
	public static void main(String[] args) {



		System.out.println(countSundays(1901));
		System.out.println(countSundays(1910));
		System.out.println(countSundays(1920));
		System.out.println(countSundays(1930));
		System.out.println(countSundays(1940));
		System.out.println(countSundays(1950));
		System.out.println(countSundays(1960));
		System.out.println(countSundays(1970));
		System.out.println(countSundays(1980));
		System.out.println(countSundays(1990));
		System.out.println(countSundays(2000));
	}
}
