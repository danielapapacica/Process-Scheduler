//package Tema1;

public class NoCache extends Cache{
	
	ProcessList procList = new ProcessList();
	
	public Output getResult(ProcessStructure process, int numberToBeProcessed) {
		return procList.getTask(process, numberToBeProcessed);

	}


}
