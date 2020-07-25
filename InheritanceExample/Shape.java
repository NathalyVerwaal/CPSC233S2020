
public abstract class Shape {
	private Point location = new Point(0,0);
	
	public Shape(Point aLocation) {
		location = aLocation;
	}
	
	public void moveDown(int amount){
		location.moveDown(amount);
	}
	protected Point getLocation() {
		return location;
	}
	public abstract double circumference();
}
