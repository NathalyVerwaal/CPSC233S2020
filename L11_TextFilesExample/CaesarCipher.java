import java.io.*;

public class CaesarCipher {
	
	public char shiftRight(int shiftAmount, char charToShift) {
		shiftAmount = shiftAmount % 26;
		if (charToShift >= 'a' && charToShift <= 'z') {
			charToShift += shiftAmount;
			if (charToShift > 'z') {
				charToShift -= 26;
			} else if (charToShift < 'a') {
				charToShift += 26;
			}
		}
		if (charToShift >= 'A' && charToShift <= 'Z') {
			charToShift += shiftAmount;
			if (charToShift > 'Z') {
				charToShift -= 26;
			} else if (charToShift < 'A') {
				charToShift += 26;
			}
		}
		return charToShift;
		
	}
	
	public void encrypt(int shiftAmount, String inputFilename, String outputFilename) {
		BufferedReader input = null;
		PrintWriter output = null;
		try {
			input = new BufferedReader(new FileReader(inputFilename));
			output = new PrintWriter(new BufferedWriter(new FileWriter(outputFilename)));
			int current = input.read();
			while (current != -1) {
				output.print(shiftRight(shiftAmount, (char)current));
				current = input.read();
			}
			// close input and output files -> moved to finally
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open input file: " + inputFilename);
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Problems when trying to encrypt the file");
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (output!= null) {
				output.close();
			}
		}
		
	}

	public static void main(String[] args) {
		// needs thorough of shiftRight
		CaesarCipher cc = new CaesarCipher();
		cc.encrypt(10, "src/CaesarCipher.java", "encrypted.txt");
	}

}
