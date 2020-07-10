import java.math.BigInteger;

public class Fraction {
	int numerator = 0;
	int denominator = 1;
	
	public Fraction(int aNumerator, int aDenominator) {
		numerator = aNumerator;
		denominator = aDenominator;
	}
	
	public Fraction add(Fraction other) {
		int newDenominator = denominator * other.denominator;
		int newNumerator = numerator * other.denominator +
			other.numerator * denominator;
		Fraction solution = new Fraction(newNumerator, newDenominator);
		solution.simplify();
		return solution;
	}
	
	public void simplify() {
		BigInteger num = BigInteger.valueOf(numerator);
		BigInteger den = BigInteger.valueOf(denominator);
		BigInteger gcd = num.gcd(den);
		numerator = numerator/gcd.intValue();
		denominator = denominator/gcd.intValue();
	}
	
	public boolean equals(Fraction other) {
		return other.numerator == numerator && 
			other.denominator == denominator;
	}
	
	public String toString(){
		return numerator + "/" + denominator;
	}
	
}