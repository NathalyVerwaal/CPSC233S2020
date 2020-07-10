import java.math.BigInteger;

public class Fraction {
	// class is responsible for making sure that a Fraction object is always
	// in a valid state.
	private int numerator = 0;
	private int denominator = 1;  // this should never be zero.
	
	public Fraction(int aNumerator, int aDenominator) {
		setNumerator(aNumerator);
		denominator = aDenominator;
	}
	
	public int getDenominator() {
		return denominator;
	}
	
	public void setDenominator(int denom) {
		if (denom != 0)	denominator = denom;
	}
	
	public Fraction add(Fraction other) {
		int newDenominator = denominator * other.denominator;
		int newNumerator = getNumerator() * other.denominator +
			other.getNumerator() * denominator;
		Fraction solution = new Fraction(newNumerator, newDenominator);
		solution.simplify();
		return solution;
	}
	
	public void simplify() {
		BigInteger num = BigInteger.valueOf(getNumerator());
		BigInteger den = BigInteger.valueOf(denominator);
		BigInteger gcd = num.gcd(den);
		setNumerator(getNumerator()/gcd.intValue());
		denominator = denominator/gcd.intValue();
	}
	
	public boolean equals(Fraction other) {
		return other.getNumerator() == getNumerator() && 
			other.denominator == denominator;
	}
	
	public String toString(){
		return getNumerator() + "/" + denominator;
	}

	public int getNumerator() {
		return numerator;
	}

	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}
	
}