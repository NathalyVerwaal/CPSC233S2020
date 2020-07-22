package model;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FactoryTest {

	@Test
	public void testSetPartyNames() {
		Factory f = new Factory(250);
		String[] names = {"one","two","three","four"};
		f.setPartyNames(names);
		
		String[] actualNames = f.getPartyNames();
		assertEquals("set new party names in Factory, testing number of party names.", 4,actualNames.length);
		assertEquals("set new party names in Factory, testing first name.", "one", actualNames[0]);
		assertEquals("set new party names in Factory, testing second name.", "two", actualNames[1]);
		assertEquals("set new party names in Factory, testing third name.", "three", actualNames[2]);
		assertEquals("set new party names in Factory, testing fourth name.", "four", actualNames[3]);	
	}

	@Test
	public void testSetPartyNames_null() {
		Factory f = new Factory(250);
		String[] expected = f.getPartyNames();
		
		f.setPartyNames(null);
		
		String[] actualNames = f.getPartyNames();
		
		assertEquals("set new party names in Factory to null, expected list to be unchanged.", expected.length,actualNames.length);
		assertEquals("set new party names in Factory, testing first name.", expected[0], actualNames[0]);
		assertEquals("set new party names in Factory, testing second name.", expected[1], actualNames[1]);
		assertEquals("set new party names in Factory, testing third name.", expected[2], actualNames[2]);
		assertEquals("set new party names in Factory, testing fourth name.", expected[3], actualNames[3]);	
	}

	@Test
	public void testSetPartyNames_nullAfterChange() {
		Factory f = new Factory(250);
		String[] expected = {"one", "two", "three", "four", "five"};
		f.setPartyNames(expected);
		
		f.setPartyNames(null);
		
		String[] actualNames = f.getPartyNames();
		
		assertEquals("set new party names in Factory to null, expected list to be unchanged.", expected.length,actualNames.length);
		assertEquals("set new party names in Factory, testing first name.", expected[0], actualNames[0]);
		assertEquals("set new party names in Factory, testing second name.", expected[1], actualNames[1]);
		assertEquals("set new party names in Factory, testing third name.", expected[2], actualNames[2]);
		assertEquals("set new party names in Factory, testing fourth name.", expected[3], actualNames[3]);	
		assertEquals("set new party names in Factory, testing fifth name.", expected[4], actualNames[4]);	
	}
	
	@Test
	public void testCreateRandomParty_testingRandomness() {
		Factory f = new Factory(300);
		String partyName = "testingRandomness";
		int maximumSeats = 30;
		int maximumPercent = 20;
		
		int[] seatValues = new int[31];
		int[] voteValues = new int[21];
		
		for (int counter = 0; counter < 100; counter++) {
			Party p = f.createRandomParty(partyName, maximumSeats, maximumPercent);
			assertEquals("Creating 100 random parties, testing name of party " + counter, partyName, p.getName());
			assertTrue("Created random party with maximum 30 seats and maximum 20%. Testing seats <= 30", p.getProjectedNumberOfSeats() <= 30 && p.getProjectedNumberOfSeats() >= 0);
			assertTrue("Created random party with maximum 30 seats and maximum 20%. Testing votes <= .2", p.getProjectedPercentageOfVotes() <= 0.2 && p.getProjectedPercentageOfVotes() >= 0 );
			
			seatValues[(int)p.getProjectedNumberOfSeats()]++;
			voteValues[(int)(p.getProjectedPercentageOfVotes()*100)]++;
		}
		
		// Test randomness of seats
		for (int index = 0; index < seatValues.length; index++) {
			int count = seatValues[index];
			assertTrue("Create 100 random parties with maximum 30 seats.  Expected at most 20 of them to have " + index + " seats but there were " + count, count < 20);
		}
	}

	@Test
	public void testCreateRandomParty_differenceBetweenVotesAndSeatsIsAtMostFivePercent() {
		Factory f = new Factory(300);
		String partyName = "testingRandomness";
		int maximumSeats = 270;
		int maximumPercent = 90;
		
		for (int counter = 0; counter < 100; counter++) {
			Party p = f.createRandomParty(partyName, maximumSeats, maximumPercent);
			float seatsPercent = p.getProjectedNumberOfSeats()/300;
			float votes = p.getProjectedPercentageOfVotes();
			
			assertTrue("Difference between percent of seats and percent of vote should be at most 5%.  Percent of seats is: " + 
			seatsPercent + " and votes is " + votes, Math.abs(seatsPercent - votes) <= 5);
		}
	}

	
	@Test
	public void testCreateRandomPoll_testingTotalSeats300() {
		Factory f = new Factory(300);
		Poll p = f.createRandomPoll("poll1");
		int expectedTotalSeats = 300;
		float actualTotalSeats = 0;
		for (Party party : p.getPartiesSortedBySeats()) {
			actualTotalSeats += party.getProjectedNumberOfSeats();
		}
		
		assertEquals("testing poll name of randomly generated poll", "poll1", p.getPollName());
		assertEquals("Expected all seats to be assigned to the  parties in the random poll", expectedTotalSeats, (int) actualTotalSeats);
	}

	@Test
	public void testCreateRandomPoll_testingTotalSeats50() {
		Factory f = new Factory(50);
		Poll p = f.createRandomPoll("poll2");
		int expectedTotalSeats = 50;
		float actualTotalSeats = 0;
		for (Party party : p.getPartiesSortedBySeats()) {
			actualTotalSeats += party.getProjectedNumberOfSeats();
		}
		assertEquals("testing poll name of randomly generated poll", "poll2", p.getPollName());		
		assertEquals("Expected all seats to be assigned to the parties in the random poll", expectedTotalSeats, (int) actualTotalSeats);
	}

	@Test
	public void testCreateRandomPoll_testingTotalSeats1() {
		Factory f = new Factory(1);
		Poll p = f.createRandomPoll("poll3");
		int expectedTotalSeats = 1;
		float actualTotalSeats = 0;
		for (Party party : p.getPartiesSortedBySeats()) {
			if (party != null) actualTotalSeats += party.getProjectedNumberOfSeats();
		}
		assertEquals("testing poll name of randomly generated poll", "poll3", p.getPollName());		
		assertEquals("Expected all seats to be assigned to the parties in the random poll", expectedTotalSeats, (int) actualTotalSeats);
	}

	@Test
	public void testCreateRandomPoll_testingTotalVotes_5Parties() {
		Factory f = new Factory(200);
		
		String [] names = {"one", "two", "three", "four", "five"};
		f.setPartyNames(names);
		
		Poll p = f.createRandomPoll("poll4");
		
		int expectedTotalVotes = 1;
		float actualTotalVotes = 0;
		
		Party[] partiesInPoll = p.getPartiesSortedBySeats();
		for (Party party : partiesInPoll) {
			if (party != null) actualTotalVotes += party.getProjectedPercentageOfVotes();
		}
		
		for (String name : names) {
			boolean found = false;
			for (Party party  : partiesInPoll) {
				if (party.getName().contentEquals(name)) found = true;
			}
			assertTrue("Testing that all parties are in the poll, testing party " + name, found);
		}
	
		assertEquals("testing poll name of randomly generated poll", "poll4", p.getPollName());		
		assertEquals("Expected 100% of votes to be assigned to the parties in the random poll", expectedTotalVotes, Math.round(actualTotalVotes));
	}
	
	@Test
	public void testCreateRandomPoll_testingTotalVotes_1Party() {
		Factory f = new Factory(200);
		
		String [] names = {"one"};
		f.setPartyNames(names);
		
		Poll p = f.createRandomPoll("poll4");
		
		int expectedTotalVotes = 1;
		float actualTotalVotes = 0;
		
		Party[] partiesInPoll = p.getPartiesSortedBySeats();
		for (Party party : partiesInPoll) {
			actualTotalVotes += party.getProjectedPercentageOfVotes();
		}
		
		for (String name : names) {
			boolean found = false;
			for (Party party  : partiesInPoll) {
				if (party.getName().contentEquals(name)) found = true;
			}
			assertTrue("Testing that all parties are in the poll, testing party " + name, found);
		}
	
		assertEquals("testing poll name of randomly generated poll", "poll4", p.getPollName());		
		assertEquals("Expected 100% of votes to be assigned to the parties in the random poll", expectedTotalVotes, Math.round(actualTotalVotes));
	}
	
	@Test
	public void testCreateRandomPoll_testingWinnerIsRandom() {
		Factory f = new Factory(200);
		
		String [] names = {"one", "two", "three", "four", "five"};
		f.setPartyNames(names);
		
		int[] winningCount = new int[5];
		
		for (int counter = 0; counter < 100; counter++) {
			Poll p = f.createRandomPoll("poll4");
			Party[] parties = p.getPartiesSortedBySeats();
			Party winner = parties[0];
			for (int index = 1; index < parties.length; index++) {
				if (winner.getProjectedNumberOfSeats() < parties[index].getProjectedNumberOfSeats()) {
					winner = parties[index];
				}
			}
			String winnerName = winner.getName();
			for (int index = 0; index < names.length; index++) {
				if (winnerName.equals(names[index])) {
					winningCount[index]++;
				}
			}
			
		}
		for (int index = 0; index < winningCount.length; index++) {
			assertTrue("When creating 100 random polls, expect each of the parties to win about 1/5 of the time.  Checking it won at least 5/100 times  Testing party " + names[index] + " which won " + winningCount[index] + "times", winningCount[index] >= 5);
		}
	}
	
	
	@Test
	public void testCreateRandomPollList_createOnePolls() {
		Factory f = new Factory(300);
		PollList pl = f.createRandomPollList(1);
		
		Poll[] list = pl.getPolls();
		assertEquals("Testing that array in list has correct size.", 1, pl.getPolls().length);
		for (int index = 0; index < list.length; index++) {
			assertFalse("Checking that the list contains a Poll object rather than null at index " + index, list[index] == null);
		}
	}

	@Test
	public void testCreateRandomPollList_createFivePolls() {
		Factory f = new Factory(300);
		PollList pl = f.createRandomPollList(5);
		
		Poll[] list = pl.getPolls();
		assertEquals("Testing that array in list has correct size.", 5, pl.getPolls().length);
		for (int index = 0; index < list.length; index++) {
			assertFalse("Checking that the list contains a Poll object rather than null at index " + index, list[index] == null);
		}
	}

	@Test
	public void testCreateRandomPollList_createTwentyPolls() {
		Factory f = new Factory(300);
		PollList pl = f.createRandomPollList(20);
		
		Poll[] list = pl.getPolls();
		assertEquals("Testing that array in list has correct size.", 20, pl.getPolls().length);
		for (int index = 0; index < list.length; index++) {
			assertFalse("Checking that the list contains a Poll object rather than null at index " + index, list[index] == null);
		}
	}

}
