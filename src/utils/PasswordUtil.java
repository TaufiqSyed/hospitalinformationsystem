package utils;
import java.util.Random;

public class PasswordUtil {
	private static Random rand = new Random(); 
	public static String generatePassword() {
		String result = "";
		for (int i = 0; i < 10; i++) {
			result += (char) (rand.nextInt(26) + 'a');
		}
		return result;
	}
}
