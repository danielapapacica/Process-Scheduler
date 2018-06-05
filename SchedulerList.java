//package Tema1;
import java.lang.reflect.Array;
import java.util.*;


/**
 * A class that contains all types of schedulers, each one in a method
 *
 */
public class SchedulerList {

	ProcessStructure[] processes;
	String schedulerType;
	Cache mySchedulerCache;
	
	int size;	// size of the available processes array

	
	/**
	 * The contructor sets the important fields of the scheduler:
	 * the processes available, the type of scheduler and of cache that will be used.
	 * 
	 * @param processes - the list of available processes to be used
	 * @param schedulerType
	 * @param cacheType
	 * @param cacheCapacity
	 */
	public SchedulerList(ProcessStructure[] processes, String schedulerType, String cacheType, int cacheCapacity) {
		size = Array.getLength(processes);	
		this.processes = new ProcessStructure[size];
		System.arraycopy(processes, 0, this.processes, 0, size);	
		this.schedulerType = schedulerType;
		mySchedulerCache = CacheFactory.getCacheType(cacheType, cacheCapacity);
		
	}
	
	
	
	/**
	 *  Chooses between the 3 types of schedulers based on the schedulerType field
	 * @param numbersToBeProcessed - the array of numbers that will pe processed
	 * @return an array of output lines, each containing the result, the process used,
	 * and if the result was "Computed" or extracted "FromCache"
	 */
		public Output[] getScheduler(int numbersToBeProcessed[]) {
			Output output[] = null;

			switch(schedulerType) {
				case "RandomScheduler":
					output = RandomScheduler(processes, numbersToBeProcessed);
					break;
					
				case "RoundRobinScheduler":
					output = RoundRobinScheduler(processes, numbersToBeProcessed);
					break;
					
				case "WeightedScheduler":
					output = WeightedScheduler(processes, numbersToBeProcessed);
					break;
			}
			return output;
		}
	
		/**
		 * Chooses randomly a number lower than the number of processes available and
		 * calls the cache to bring the result for the process which has the index the same as the
		 * random number chosen.
		 * 
		 * @param processes
		 * @param numbersToBeProcessed
		 * @return output lines -- process used + result + "FromCache"/"Computed"
		 */
		public Output[] RandomScheduler(ProcessStructure[] processes, int numbersToBeProcessed[]) {

			Random generator = new Random();
			int limit  = size;    // maximum index for a randomly called process
			int n = Array.getLength(numbersToBeProcessed);
			Output[] output = new Output[n];
			
			for(int i = 0; i < n; i++) {
				int procNr = generator.nextInt()%limit;
				if(procNr < 0)
					procNr = (-1) * procNr;
				output[i] = mySchedulerCache.getResult(processes[procNr], numbersToBeProcessed[i]);
			}	
			return output;
		}
		
		
		
		/**
		 * Calls the cache to bring the result for each process available.
		 * The processes are chosen in the order of their index.
		 * When all the processes are used it starts again from the beginning with the first one.
		 * 
		 * @param processes
		 * @param numbersToBeProcessed
		 * @return output lines -- process used + result + "FromCache"/"Computed"
		 */
		public Output[] RoundRobinScheduler(ProcessStructure[] processes, int numbersToBeProcessed[]) {
			int n = Array.getLength(numbersToBeProcessed);
			
			int last = 0; // memorize the index of last process used
			Output[] output = new Output[n];
			
			for(int i = 0; i < n; i++) {
				output[i] = mySchedulerCache.getResult(processes[last], numbersToBeProcessed[i]);
				last++;
				if(last == size)
					last = 0;
			}
			
			
			return output;	
		}
		
		
		
		
		/**
		 * The greatest common divisor method for 2 numbers
		 * @param a
		 * @param b
		 * @return the greatest common divisor
		 */
		int getCmmdc(int a, int b) {
			int r = a % b;
			while(r != 0) {
				a = b;
				b = r;	
				r = a % b;
			}
			return b;
		}
		
		/**
		 * The greatest common divisor method for an array of numbers,
		 * useful for the Weighted Scheduler type
		 * @param array
		 * @return the greatest common divisor
		 */
		int CmmdcMultipleNumbers(int array[]) {
			
			int n = Array.getLength(array);
			
			if(n < 2)
				return array[0];
			int cmmdc = getCmmdc(array[0], array[1]);
			for(int i = 2; i < n; i++) {
				cmmdc = getCmmdc(cmmdc, array[i]);
			}
			return cmmdc;
		}
		
		
		/**
		 * Calculates the percent of every process to be used.
		 * Repeats calling cache for all the numbers, respecting the percent of every process.
		 * 
		 * @param processes
		 * @param numbersToBeProcessed
		 * @return output lines -- process used + result + "FromCache"/"Computed"
		 */
		public Output[] WeightedScheduler(ProcessStructure[] processes, int numbersToBeProcessed[]) {
			
			int[] array = new int[size];
			for(int i = 0; i < size; i++) {
				array[i] = processes[i].getWeight();
			}
			
			int cmmdc = CmmdcMultipleNumbers(array);	// calculates greatest common dividor

			int n = Array.getLength(numbersToBeProcessed);
			Output[] output = new Output[n];
			
			// calculates the percent of every process to be used
			for(int i = 0; i < size; i++) {
				array[i] = processes[i].getWeight() / cmmdc;
			}
			
			int k = 0;
			// calls cache for every pair process-number, the process is chosen by respecting the proportions
			while(k < n) {
				for(int i = 0; i < size && k < n; i ++) {
					for(int j = 0; j < array[i] && k < n; j++) {
						output[k] = mySchedulerCache.getResult(processes[i], numbersToBeProcessed[k]);
						k++;
					}
				}
			}	
			return output;	
		}
}
