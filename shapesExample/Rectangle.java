
public class Rectangle {
	private Point topLeft = new Point(0,0);
	private int width = 5;
	private int height = 5;

	public void moveDown(int amount){
		topLeft.moveDown(amount);
	}
	public double circumference(){
		return 2*width + 2*height;
	}
	public double diameter(){
		Point bottomRight = new Point(topLeft.getXCoord()+width, topLeft.getYCoord()+height);
		return topLeft.distance(bottomRight);
	}
	
	public String toString() {
		return topLeft.toString() + " " + width + " " + height;
	}
	
}
