//package Tema1;


abstract class Cache {
	
	/**
	 * @param process - the process chosen by the scheduler
	 * @param numberToBeProcessed
	 * @return the output line -- process used + result + "FromCache"/"Computed"
	 */
	abstract Output getResult(ProcessStructure process, int numberToBeProcessed);
}

/**
 * Creates an instance of the needed type of cache, using a factory.
 *
 */
public class CacheFactory {

	public static Cache getCacheType(String cacheType, int cacheCapacity) {
		
		 switch(cacheType) {
		 	case("NoCache"):
		 		 return new NoCache();
		 	case("LruCache"):
		 		 return new LruCache(cacheCapacity);
		 	case("LfuCache"):
		 		 return new LfuCache(cacheCapacity);
		 	default:
				 throw new IllegalArgumentException("The cache type is not recognized.");

		 }
		 
	 }
}
