import java.util.Scanner;

// everything in Java is inside a class
// Name of the class and name of the file must be the same
public class HelloWorld {
	// To run this class it must have this main method.
	public static void main(String[] args){
		System.out.print("What is your name? ");
		Scanner keyboard = new Scanner(System.in);
		String name = keyboard.nextLine();
		System.out.println("Hello " + name);
	}
	
}







