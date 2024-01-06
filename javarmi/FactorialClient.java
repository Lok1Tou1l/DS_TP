import java.net.MalformedURLException; 
import java.rmi.Naming; 
import java.rmi.NotBoundException; 
import java.rmi.RemoteException; 

public class FactorialClient implements Factorial { 
	public static void main(String[] args) 
	{ 

		try { 
            // Create an remote object with the same name 
			// Cast the lookup result to the interface 
			Factorial c = (Factorial); 
			Naming.lookup("rmi:// localhost/FactorialService"); 

			// Call the method for the results 
			System.out.println(c.fact(30)); 
		} 

		// If any error occur 
		catch (MalformedURLException murle) { 
			System.out.println("\nMalformedURLException: "
							+ murle); 
		} 

		catch (RemoteException re) { 
			System.out.println("\nRemoteException: "
							+ re); 
		} 

		catch (NotBoundException nbe) { 
			System.out.println("\nNotBoundException: "
							+ nbe); 
		} 

		catch (java.lang.ArithmeticException ae) { 
			System.out.println("\nArithmeticException: " + ae); 
		} 
	} 
} 
