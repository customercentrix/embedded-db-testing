
import org.mapdb.*;

import java.io.File;
import java.util.concurrent.ConcurrentNavigableMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MapDbInitialTests
{
	private static final Integer FIVE_MINUTES_MILLIS = 5 * 60 * 1000;
	private static final Integer NUMBER_OF_THREADS = 10;
	private DB db;
	private File file;
	
	@Before
	public void setupDb()
	{
		file = new File("testdb");
		db = DBMaker.newFileDB(file).closeOnJvmShutdown().compressionEnable().deleteFilesAfterClose().make();
	}
	
	@Test
	public void speedTest()
	{
		
		ConcurrentNavigableMap<MapDbKey, MapDbValue> testMap = db.createTreeMap("testMap").make();
		fiveMinuteMapEntry(testMap);
	}
	
	@Test
	public void concurrentWritesTest()
	{
		for(int i = 0; i < NUMBER_OF_THREADS; i++)
		{
			TestThread testThread = new TestThread(i);
			testThread.start();
		}	
	}

	public void fiveMinuteMapEntry(ConcurrentNavigableMap<MapDbKey, MapDbValue> testMap)
	{
		long startTime = System.currentTimeMillis();
		int intervalSize = 0;
		int currentInterval = 0;
		
		while ((System.currentTimeMillis() - startTime) < FIVE_MINUTES_MILLIS)
		{
			testMap.put(new MapDbKey(), new MapDbValue());
			intervalSize++;
			
			if (testMap.size() % 100 == 0)
				db.commit();

			if (Math.round((System.currentTimeMillis() - startTime)/1000) % 15 == 0 && 
					Math.round((System.currentTimeMillis() - startTime)/1000)/15 > currentInterval)
			{
				System.out.println("Total Items: " + testMap.size());
				System.out.println("Total Time: " + (System.currentTimeMillis()
						- startTime)/1000 + " s");
				System.out.println("Total Items per Second: " + ((testMap.size() * 1000)/
						(System.currentTimeMillis() - startTime)));
				System.out.println("Interval Items per Second: " + (intervalSize/
						15) + "\n");
				intervalSize = 0;
				currentInterval++;
			}
		}
	}
	
	@After
	public void afterTest()
	{
		System.out.println("Total file size: " + file.length() + " bytes\n");
		db.close();
	}
	
	/**
	 * Thread to test the thread safety and concurrency for the map being tested
	 * 
	 * @author Shannon Gallagher <shannong@loadstorm.com>
	 * @since Aug 14, 2014
	 */
	private class TestThread extends Thread
	{
		private ConcurrentNavigableMap<MapDbKey, MapDbValue> testMap;
		
		public TestThread(int threadNumber)
		{
			super();
			this.testMap = db.getTreeMap("test map " + threadNumber);
		}
		
		@Override
		public void run()
		{
			fiveMinuteMapEntry(testMap);
		}
	}
}
