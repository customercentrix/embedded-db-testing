import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


public class MapDbKey implements Serializable, Comparable
{
	private String[] DOMAIN_NAMES = {"google.com", "facebook.com", "yahoo.com", "stackoverflow.com", 
			"vaadin.com", "oracle.com", "twitter.com", "ebay.com", "unm.edu", "youtube.com"};
	private Long CURRENT_TIME = System.currentTimeMillis();
	private String sourceString;
	private String hashString;

	public MapDbKey()
	{
		Long randomLong = (long) 0;
		Random random = new Random();

		for (int i = 0; i < 19; i++)
		{
			long numberToAdd = (long) (random.nextInt(10) * Math.pow(10, i));
			randomLong = randomLong + numberToAdd;
			if (numberToAdd == 0)
				randomLong = randomLong * 10;
		}
		
		sourceString = DOMAIN_NAMES[random.nextInt(10)];
		hashString = randomLong.toString();
	}

	public Long getCurrentTime()
	{
		return CURRENT_TIME;
	}
	
	public void setSourceString(String sourceString)
	{
		this.sourceString = sourceString;
	}
	
	public String getSourceString()
	{
		return sourceString;
	}
	
	public void sethashString(String hashString)
	{
		this.hashString = hashString;
	}
	
	public String getHashString()
	{
		return hashString;
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
