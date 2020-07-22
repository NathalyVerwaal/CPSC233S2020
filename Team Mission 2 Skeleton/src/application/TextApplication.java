package application;

import model.Party;
import model.Poll;
import model.PollList;

public class TextApplication {
	public static final int MAX_NUMBER_OF_STARS = 25;
	private PollList polls;
	
	public TextApplication() {
	}
	
	public TextApplication(PollList polls) {
		this.polls = polls;
	}
	
	public void displayPollsBySeat(String[] partyNames) {
		if (polls == null) {
			System.out.println("No polls to display");
		} else {
			Poll[] allPolls = polls.getPolls();
			for (int index = 0; index < allPolls.length && allPolls[index] != null; index++) {
				displayPollDataBySeat(allPolls[index]);
			}
			displayPollDataBySeat(polls.getAggregatePoll(partyNames));
		}
	}

	public void displayPollDataBySeat(Poll aPoll) {
		if (aPoll == null) {
			System.out.println("Nothing to display for a null poll");
			return;
		}
		
		double numOfSeatsPerStar = polls.getNumOfSeats()/MAX_NUMBER_OF_STARS; 
		
		System.out.println(textVisualizationBySeat(aPoll, numOfSeatsPerStar));
	}
	
	private String textVisualizationBySeat(Poll aPoll, double numOfSeatsPerStar) {
		//TODO: this should really also include undecided votes
		Party[] parties = aPoll.getPartiesSortedBySeats();
		StringBuilder builder = new StringBuilder();
		int starsNeededForMajority = (int)Math.ceil(polls.getNumOfSeats()/2.0/numOfSeatsPerStar);

		
		builder.append("\n" + aPoll.getPollName() + "\n");
		for (Party p : parties) {
			if (p != null) {
				builder.append(p.textVisualizationBySeats(MAX_NUMBER_OF_STARS, starsNeededForMajority, numOfSeatsPerStar));
				builder.append("\n");
			}
		}		
		return builder.toString();
	}
	
	public void run() {
	}
	
	public static void main(String[] args) {
		TextApplication app = new TextApplication(null);
		app.run();
		
	}
}
