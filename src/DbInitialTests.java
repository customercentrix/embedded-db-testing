

import java.io.File;
import java.util.concurrent.ConcurrentNavigableMap;

import org.h2.mvstore.MVMapConcurrent;
import org.h2.mvstore.MVMapConcurrent.Builder;
import org.h2.mvstore.MVStore;
import org.junit.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;

public class DbInitialTests
{
	private static final int RPT_INTERVAL_SECS = 60;
	private static final Integer TEST_TIME_MS = 15 * 60 * 1000;
	
	
	@Test
	public void mvstorePutSpeedTest()
	{		
		System.out.println("--- H2/MVStore ---\n");
		MVStore h2store = new MVStore.Builder().fileName("databases/H2-test.db")
				.cacheSize(32)
				.compress()
				.open();
		
		Builder<DbKey, DbValue> mapBuilder = new MVMapConcurrent.Builder<DbKey, DbValue>();
		MVMapConcurrent<DbKey, DbValue> testMap = h2store.openMap("myMap", mapBuilder);

		long startTime = System.currentTimeMillis();
		int currentInterval = 0;
		
		while ((System.currentTimeMillis() - startTime) < TEST_TIME_MS)
		{
			DbKey k = new DbKey();
			DbValue v = new DbValue();
			testMap.put(k, v);

			if (Math.round((System.currentTimeMillis() - startTime) / 1000) % RPT_INTERVAL_SECS == 0 && 
					Math.round((System.currentTimeMillis() - startTime) / 1000) / RPT_INTERVAL_SECS > currentInterval)
			{
				System.out.println("Items: " + testMap.size());
				System.out.println("Time: " + 
						(System.currentTimeMillis() - startTime) / 1000 + " s");
				System.out.println("Items per second: "
						 + ((testMap.size() * 1000L) / (System.currentTimeMillis() - startTime)) + "\n");
				currentInterval++;
			}
		}
		File f = new File("databases/H2-test.db");
		System.out.println("File " + f.getAbsolutePath() + ", total file size: " + f.length() /1024/1024 + " MBs");
		System.out.println("Average entry size: " + f.length() / testMap.size() + " bytes\n\n");
		f.delete();
	}
	
	@Test
	public void mapdbPutSpeedTest()
	{		
		System.out.println("--- MapDB ---\n");
		File mapDBfile = new File("databases/MapDB-test.db");
		DB mapDB = DBMaker.newFileDB(mapDBfile).compressionEnable().mmapFileEnable()
				.closeOnJvmShutdown()
				.deleteFilesAfterClose()
				.make();
		
		ConcurrentNavigableMap<DbKey, DbValue> testMap = 
				mapDB.createTreeMap("aMap").counterEnable().make();

		long startTime = System.currentTimeMillis();
		int currentInterval = 0;
		
		while ((System.currentTimeMillis() - startTime) < TEST_TIME_MS)
		{
			DbKey k = new DbKey();
			DbValue v = new DbValue();
			testMap.put(k, v);
			
			mapDB.commit();

			if (Math.round((System.currentTimeMillis() - startTime) / 1000) % RPT_INTERVAL_SECS == 0 && 
					Math.round((System.currentTimeMillis() - startTime) / 1000) / RPT_INTERVAL_SECS > currentInterval)
			{
				System.out.println("Items: " + testMap.size());
				System.out.println("Time: " 
						+ (System.currentTimeMillis() - startTime) / 1000 + " s");
				System.out.println("Items per second: "
						 + ((testMap.size() * 1000L) / (System.currentTimeMillis() - startTime)) + "\n");
				currentInterval++;
			}
		}
		
		System.out.println("File " + mapDBfile.getAbsolutePath() + ", total file size: " + mapDBfile.length() /1024/1024 + " MBs");
		System.out.println("Average entry size: " + mapDBfile.length() / testMap.size() + " bytes\n\n");
		mapDB.close();
	}

}
