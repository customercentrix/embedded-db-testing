import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;


public class DbValue implements Serializable, Comparable<DbValue>
{

	private static final long serialVersionUID = 8211063993902113245L;
	
	private static Integer MIN_LENGTH = 1024;
	private static Integer MAX_LENGTH = 16000;
	private String data;
	
	public DbValue()
	{
		getData();
	}
	
	public String getData()
	{
		if (data == null)
		{
			Random random = new Random();
			int randomLength = random.nextInt((MAX_LENGTH - MIN_LENGTH) + 1) + MIN_LENGTH;
			
			char[] randomChars = new char[randomLength];
			Arrays.fill(randomChars, 'a');

			data = new String(randomChars);
			return data;
		}
		return data;
	}

	@Override
	public int compareTo(DbValue that)
	{
		return this.data.compareTo(that.data);
	}
}
