package model;

public class Poll {
	private String name = "Unnamed Poll";
	private Party[] parties;
	private int numberOfParties = 0;

	public Poll(String name, int maxNumberOfParties) {
		this.name = name; 
		parties = new Party[maxNumberOfParties];
	}
	
	public String getPollName() {
		return name;
	}
	
	public int getNumberOfParties() {
		return numberOfParties;
	}
	
	public void addParty(Party aParty) {
		parties[numberOfParties++] = aParty;
	}

	public Party getParty(String name) {
		return null;
	}
	
	public Party[] getPartiesSortedBySeats() {
		return parties;
	}

	public Party[] getPartiesSortedByVotes() {
		return parties;
	}
	
	@Override
	public String toString() {
		return "";
	}
}
