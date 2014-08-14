import java.io.Serializable;
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
			int randomNumber = random.nextInt((MAX_LENGTH - MIN_LENGTH) + 1) + MIN_LENGTH;
			
			byte[] randomBytes = new byte[randomNumber];
			random.nextBytes(randomBytes);
			String randomString = new String(randomBytes);
			
			return randomString;
		}
		return randomString;
	}

	@Override
	public int compareTo(Object o)
	{
		if (o.toString().length() > randomString.length())
			return -1;
		else if (o.toString().length() < randomString.length())
			return 1;

		return 0;
	}
}
