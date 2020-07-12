
public class Circle {
	private Point centre = new Point(0,0);
	private int radius = 5;
	
	public double diameter() {
		return 2 * radius;
	}
	
	public double circumference(){
		return 2 * Math.PI * radius;
	}
	
	public void moveDown(int amount){
		centre.moveDown(amount);
	}
	
	public String toString() {
		return centre.toString() + " " + radius;
	}
}
