//package Tema1;

public class LruCache extends Cache{

	int cacheCapacity;
	ProcessList procList = new ProcessList();
	CacheStructure[] cacheArray;
	
	
	/**
	 * Also initialize an array of cache lines
	 * @param cacheCapacity
	 */
	public LruCache(int cacheCapacity) {
		this.cacheCapacity = cacheCapacity;
		cacheArray = new CacheStructure[cacheCapacity];
		for(int i = 0; i < cacheCapacity; i++) {
			cacheArray[i] = new CacheStructure();
		}
	}
	
	int time;  // time contor
	
	
	/**
	 * Brings the result from cache or calculates it and puts the new result in cache,
	 * erasing the oldest cache lines if needed
	 */
	public Output getResult(ProcessStructure process, int numberToBeProcessed) {
		Output output = new Output();
		int oldestTime = cacheArray[0].timeLastUsed;
		int indexOfOldest = 0;
		
		for(int i = 0; i < cacheCapacity; i++) {
			
		// if the process result is found in cache
			if(cacheArray[i].processType != null 		
					&& cacheArray[i].processType == process.getType() 
					&& cacheArray[i].numberToBeProcessed == numberToBeProcessed ) {
				cacheArray[i].timeLastUsed = time;
				time++;
				output.ProcessName = process.getType();
				output.result = cacheArray[i].result;
				output.cacheUsed = "FromCache";
				return output;
			}
		}
		
		for(int i = 0; i < cacheCapacity; i++) {
		// if the process is not found in cache and the cache is not full
			if(cacheArray[i].processType == null) {
				cacheArray[i].numberToBeProcessed = numberToBeProcessed;
				cacheArray[i].processType = process.getType();
				output = procList.getTask(process, numberToBeProcessed);
				cacheArray[i].result = output.result;
				cacheArray[i].timeLastUsed = time;
				time++;
				return output;
			}
			
			// search for the oldest cache line
			if(cacheArray[i].timeLastUsed < oldestTime) {
				oldestTime = cacheArray[i].timeLastUsed;
				indexOfOldest = i;
			}
		}
		
		// if the cache is full and the oldest cache line must be erased to put the new one
		cacheArray[indexOfOldest].numberToBeProcessed = numberToBeProcessed;
		cacheArray[indexOfOldest].processType = process.getType();
		output = procList.getTask(process, numberToBeProcessed);
		cacheArray[indexOfOldest].result = output.result;
		cacheArray[indexOfOldest].timeLastUsed = time;
		time++;
		return output;
		

	}

}
