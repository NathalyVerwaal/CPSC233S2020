
public class Rectangle extends Shape {
	private int width = 5;
	private int height = 5;

	public double circumference(){
		return 2*width + 2*height;
	}
	public double diameter(){
		Point bottomRight = new Point(getLocation().getXCoord()+width, getLocation().getYCoord()+height);
		return getLocation().distance(bottomRight);
	}
	
	public String toString() {
		return getLocation().toString() + " " + width + " " + height;
	}
	
}
