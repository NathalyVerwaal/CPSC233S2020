public class TestShapes {
	public static void main(String[] args) {
		Circle c = new Circle(new Point(1,2));
		c.moveDown(40);
		System.out.println(c);

		Rectangle r = new Rectangle(new Point(3,4));
		r.moveDown(20);
		System.out.println(r);
		
		Shape s = new Circle(new Point(5,6));
		s.circumference();
	}
}