package application;

import model.Factory;
import model.PollList;

public abstract class PollTrackerController {
	private PollTrackerApp app;
	
	public abstract void refresh();
	
	public void setPollTrackerApp(PollTrackerApp app) {
		this.app = app;
		refresh();
	}
	
	protected PollList getPollList() {
		return app.getPolls();
	}
	
	protected Factory getFactory() {
		return app.getFactory();
	}
	
	protected void setPollList(PollList polls) {
		app.setPolls(polls);
	}
	
	protected void setFactory(Factory aFactory) {
		app.setFactory(aFactory);
	}
}
