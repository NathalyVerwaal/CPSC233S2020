package model;

public class PollList {
	private Poll[] polls;
	private int numOfSeats;
	
	public PollList(int numOfPolls, int numOfSeats) {
		polls = new Poll[numOfPolls];
		this.numOfSeats = numOfSeats;
	}
	
	public int getNumOfSeats() {
		return numOfSeats;
	}
	
	public Poll[] getPolls() {
		return polls;
	}

	public void addPoll(Poll aPoll) {
		int index = 0;
		for (; index < polls.length && polls[index] != null; index++) {	
		}
		polls[index] = aPoll;
	}
	
	public Poll getAggregatePoll(String[] partyNames) {
		return polls[0];
	}
	
	public Party getAveragePartyData(String partyName) {
		return null;
	}
	
	public Poll adjustPollToMaximums(Poll aPoll) {
		return aPoll;
	}
	
	@Override
	public String toString() {
		return "";
	}
}
