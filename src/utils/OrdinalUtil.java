package utils;

public class OrdinalUtil {
	public static String ordinalize(int x) {
		if (x == 1) return "1st";
		if (x == 2) return "2nd";
		if (x == 3) return "3rd";
		return Integer.toString(x) + "th";
	}
}
