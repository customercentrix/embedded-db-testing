import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;


public class MapDbValue implements Serializable, Comparable
{
	private Integer MIN_LENGTH = 1024;
	private Integer MAX_LENGTH = 16000;
	private String randomString;
	
	public MapDbValue()
	{
		getRandomString();
	}
	
	public String getRandomString()
	{
		if (randomString == null)
		{
			Random random = new Random();
			int randomLength = random.nextInt((MAX_LENGTH - MIN_LENGTH) + 1) + MIN_LENGTH;
			
			char[] randomChars = new char[randomLength];
			Arrays.fill(randomChars, 'a');

			randomString = new String(randomChars);
			return randomString;
		}
		return randomString;
	}

	@Override
	public int compareTo(Object o)
	{
		//random comparison
		Random random = new Random();
		int compare = random.nextInt(2);
		if (compare == 0)
			return 0;
		else if (compare == 1)
			return 1;
		else
			return -1;
	}
}
