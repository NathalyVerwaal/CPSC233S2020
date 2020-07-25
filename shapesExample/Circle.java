import javafx.event.Event;
import javafx.event.EventHandler;

public class Circle extends Shape implements Measurable, EventHandler<Event> {
	private int radius = 5;
	
	public double diameter() {
		return 2 * radius;
	}
	
	public double circumference(){
		return 2 * Math.PI * radius;
	}
	
	public String toString() {
		return getLocation().toString() + " " + radius;
	}

	@Override
	public double getMeasure() {
		return radius;
	}

	@Override
	public void handle(Event event) {
		radius++;
		
	}
}
