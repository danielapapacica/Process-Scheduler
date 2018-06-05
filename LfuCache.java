//package Tema1;



public class LfuCache extends Cache{

	int cacheCapacity;
	ProcessList procList = new ProcessList();
	CacheStructure[] cacheArray;	// cache lines array
	
	/**
	 * Also initialize an array of cache lines
	 * @param cacheCapacity
	 */
	public LfuCache(int cacheCapacity) {
		this.cacheCapacity = cacheCapacity;
		cacheArray = new CacheStructure[cacheCapacity];
		for(int i = 0; i < cacheCapacity; i++) {
			cacheArray[i] = new CacheStructure();
		}
	}
	

	/**
	 * Brings the result from cache or calculates it and puts the new result in cache,
	 * erasing the most unused cache lines if needed
	 */
	public Output getResult(ProcessStructure process, int numberToBeProcessed) {
		
		Output output = new Output();
		
		int minUses = cacheArray[0].numberOfUses;
		int indexOfMostUnused = 0;
		
		for(int i = 0; i < cacheCapacity; i++) {
			
		// if the process result is found in cache
			if(cacheArray[i].processType != null 		
					&& cacheArray[i].processType == process.getType() 
					&& cacheArray[i].numberToBeProcessed == numberToBeProcessed ) {

				cacheArray[i].numberOfUses++;
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
				cacheArray[i].numberOfUses++;
				
				return output;
			}
			
		// search for the most unused cache line	
			if(cacheArray[i].numberOfUses < minUses) {
				minUses = cacheArray[i].numberOfUses;
				indexOfMostUnused = i;
			}
		}
		
		// if the cache is full and the most unused cache line must be erased to put the new one
		cacheArray[indexOfMostUnused].numberToBeProcessed = numberToBeProcessed;
		cacheArray[indexOfMostUnused].processType = process.getType();
		cacheArray[indexOfMostUnused].numberOfUses = 1;
		output = procList.getTask(process, numberToBeProcessed);
		cacheArray[indexOfMostUnused].result = output.result;

		return output;
		

	}

}
