import java.rmi.Naming; 


public class FactorialServer { 

	// Implement the constructor of the class 
	public FactorialServer() 
	{ 
		try { 
			// Create a object reference for the interface 
			Factorial c = new FactorialImpl(); 

			// Bind the localhost with the service 
			Naming.rebind("192.168.8.119", c); 
		} 
		catch (Exception e) { 
			// If any error occur 
			System.out.println("ERR: " + e); 
		} 
	} 

	public static void main(String[] args) 
	{ 
		// Create an object 
		new FactorialServer(); 
	} 
} 
