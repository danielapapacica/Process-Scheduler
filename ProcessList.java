//package Tema1;


/**
 * A class that contains methods which represents each type of process 
 * and chooses one of them for each number to be processed
 *
 */
public class ProcessList {

	
    public int CheckPrime(int num){
    	if(num < 2)
    		return 0;
    	for(int i = 2; i <= num/2; i++)
    		if(num % i == 0)
    			return 0;
    	return 1;
    }
    
    
    public int NextPrime(int num) {
    	if(num < 2)
    		return 2;
    	int i = num + 1;
    	while(true) {
    		if(CheckPrime(i) ==  1)
    			return i;
    		i++;
    	}
    }
    
    
    public int Fibonacci(int n) {
    	if(n < 0)
    		return -1;
    	else if(n == 0)
    		return 0;
    	int a = 1;
    	int b = 1;
    	int c;
    	while(n > 2) {
    		c = a;
    		a = b;
    		b = (a + c) % 9973;
    		n--;
    	}
    	return b;
    }
    
    
    public int Sqrt(int num) {
    	return (int) Math.sqrt(Math.abs(num));
    }
    
    public int Square(int num) {
    	return num * num;
    }
    
    public int Cube(int num) {
    	return num * num * num;
    }
    
    public int Factorial(int num) {
    	if(num < 0)
    		return 0;
    	int p = 1;
    	for(int i = 2; i <= num; i++)
    		p = (p * i) % 9973;
    	return p;
    }
    
    /**
     * The method chooses between all the process methods available in this class,
     * based on the name of the process received.
     * @param process - contains the name of the process that should be used 
     * @param num - the number to be processed
     * @return output - contains the name of the process, the result, and the String "Computed"
     */
    public Output getTask(ProcessStructure process, int num) {

		Output output = new Output();
		switch(process.getType()) {
		
			case "CheckPrime":
				output.result = CheckPrime(num);
				output.ProcessName = "CheckPrime";
				break;
				
			case "NextPrime":
				output.result = NextPrime(num);
				output.ProcessName = "NextPrime";
				break;
				
			case "Fibonacci":
				output.result = Fibonacci(num);
				output.ProcessName = "Fibonacci";				
				break;
			
			case "Sqrt":
				output.result = Sqrt(num);
				output.ProcessName = "Sqrt";				
				break;
			
			case "Square":
				output.result = Square(num);
				output.ProcessName = "Square";				
				break;
				
			case "Cube":
				output.result = Cube(num);
				output.ProcessName = "Cube";				
				break;
				
			case "Factorial":
				output.result = Factorial(num);
				output.ProcessName = "Factorial";				
				break;
				
			default:
				return null;
		}
		output.cacheUsed = "Computed";
		return output;
	}
    
}
