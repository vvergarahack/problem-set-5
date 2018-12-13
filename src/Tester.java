import java.io.FileNotFoundException;
import java.io.IOException;


public class Tester {
	
	/**
	 * Main method.
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) throws InterruptedException {
		ATM atm;
		try {
			atm = new ATM();
			System.out.println("Welcome to your ATM!");
			atm.run();
			return;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * Rather than hard coding one or more BankAccount objects, you'll need to read them in
		 * from our very primitive database (i.e., a flat-file). After making changes, of course,
		 * you'll need to update the database accordingly.
		 */
		
		
	}
}
