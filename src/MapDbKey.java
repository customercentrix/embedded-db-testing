import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MapDbKey implements Serializable, Comparable
{
	private Long CURRENT_TIME = System.currentTimeMillis();
	private String sourceString;
	private String hashString;
	
	public MapDbKey()
	{
		sourceString = "test domain";
		hashString = calculateHash(sourceString);
	}
	
	public String calculateHash(String input)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			hashString = md.digest(input.getBytes()).toString();
			return hashString;
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
			return null;
		}
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
		if (o.toString().length() > sourceString.length())
			return -1;
		else if (o.toString().length() < sourceString.length())
			return 1;

		return 0;
	}
}
