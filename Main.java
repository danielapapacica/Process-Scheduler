//package Tema1;

import java.lang.reflect.Array;

public class Main {
	
	/**
	 * Reads data from file, calls the Scheduler, receives the output
	 * lines and prints them.
	 * @param args
	 * 	args[0] is the input file,
	 *  args[1] is the output file
	 */
	public static void main(String[] args) {
		
		// open input file and read data
		HomeworkReader HR = new HomeworkReader(args[0]);
		ProblemData data = HR.readData();
		HR.close();
		
		// creating a scheduer instance
		SchedulerList scheduler = new SchedulerList(data.getProcesses(),
													data.getSchedulerType(),
													data.getCacheType(),
													data.getCacheCapacity());
		
		// perform operations on each number from the array using the scheduler instance
	    // return the result in the output instance
		Output[] output = scheduler.getScheduler(data.getNumbersToBeProcessed());

		HomeworkWriter HW = new HomeworkWriter(args[1]);		
		int size = Array.getLength(data.getNumbersToBeProcessed());


		for(int i = 0; i < size; i++) {
			// print each output from the output array
			HW.println(data.getNumbersToBeProcessed()[i] 
					+ " " + output[i].ProcessName 
					+ " " + output[i].result 
					+ " " + output[i].cacheUsed);
			
		}
		
		HW.close();
	}
	
	
	
}
