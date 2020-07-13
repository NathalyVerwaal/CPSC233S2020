
public class Shape {
	private Point location = new Point(0,0);
	public void moveDown(int amount){
		location.moveDown(amount);
	}
	protected Point getLocation() {
		return location;
	}
}
