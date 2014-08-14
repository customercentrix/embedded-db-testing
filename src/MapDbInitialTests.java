
import org.mapdb.*;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MapDbInitialTests
{
	private Integer FIVE_MINUTES_MILLIS = 5 * 60 * 1000;
	private ConcurrentNavigableMap<MapDbKey, MapDbValue> testMap;
	
	@Before
	public void setupDb()
	{
		//instantiate db
		testMap = DBMaker.newMemoryDB().make().getTreeMap("testMap");
	}
	
	@Test
	public void speedTest()
	{
		long startTime = System.currentTimeMillis();
		long intervalSize = 0;
		
		while ((System.currentTimeMillis() - startTime) < FIVE_MINUTES_MILLIS)
		{
			MapDbKey key = new MapDbKey();
			MapDbValue value = new MapDbValue();
			testMap.put(key, value);
			intervalSize++;
			
			//if (testMap.size() % 100 == 0)
			//	System.out.println(intervalSize);//db.commit()
			
			if ((System.currentTimeMillis() - startTime) % (15 * 1000) == 0)
			{
				System.out.print(intervalSize);
				System.out.println("Total Items = " + testMap.size());
				System.out.println("Total Time = " + (System.currentTimeMillis()
						- startTime) + " ms");
				System.out.println("Total Items per Second = " + ((testMap.size() * 1000)/
						(System.currentTimeMillis() - startTime)));
				System.out.println("Interval Items per Second = " + (intervalSize/
						15));
				System.out.println("test map size = " + testMap.size());
				System.out.println("time in seconds (hopefully) " + (System.currentTimeMillis() - startTime) + "\n\n");
				intervalSize = 0;
			}	
		}
	}
}
