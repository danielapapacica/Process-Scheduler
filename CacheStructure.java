//package Tema1;

/**
 * Structure for each line in cache
 *
 */
public class CacheStructure {
	int numberToBeProcessed;
	String processType;
	int result;
	int timeLastUsed;	// for LruCache
	int numberOfUses;	// for LfuCache
}
