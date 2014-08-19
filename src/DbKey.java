import java.io.Serializable;
import java.util.Random;


public class DbKey implements Serializable, Comparable<DbKey>
{

	private static final long serialVersionUID = 6566644359269630026L;
	
	private static String[] DOMAIN_NAMES = {"google.com", "facebook.com", "yahoo.com", "stackoverflow.com", 
			"vaadin.com", "oracle.com", "twitter.com", "ebay.com", "unm.edu", "youtube.com"};
	private Long time = System.currentTimeMillis();
	private String source;
	private String hash;

	public DbKey()
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
		
		source = DOMAIN_NAMES[random.nextInt(10)];
		hash = randomLong.toString();
	}

	public Long getTime()
	{
		return time;
	}
	
	public void setSource(String source)
	{
		this.source = source;
	}
	
	public String getSource()
	{
		return source;
	}
	
	public void sethash(String hash)
	{
		this.hash = hash;
	}
	
	public String getHash()
	{
		return hash;
	}

	@Override
	public int compareTo(DbKey that)
	{
		int c = this.time.compareTo(that.time);
		if (c != 0) return c;
		
		c = this.source.compareTo(that.source);
		if (c != 0) return c;
		
		c = this.hash.compareTo(that.hash);
		if (c != 0) return c;
		
		return 1;
	}
}
