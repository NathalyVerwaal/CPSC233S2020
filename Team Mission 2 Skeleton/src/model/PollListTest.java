package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PollListTest {
	class PollMock extends Poll {
		String name;
		PartyMock[] parties;
		int nextIndex = 0;
		public PollMock(String name, int maxNumberOfParties) {
			super(name, maxNumberOfParties);
			this.name = name;
			parties = new PartyMock[maxNumberOfParties];
		}
		public String getName() {return name;}
		public void addParties(PartyMock[] parties) {
			this.parties = parties;
			nextIndex = 0;
		}
		public Party getParty(String partyName) {
			Party next = parties[nextIndex];
			nextIndex = (nextIndex + 1) % parties.length;
			return next;
		}
		
	}
	
	class PartyMock extends Party {
		public PartyMock(String partyName) {
			super(partyName);
		}
		
		public PartyMock(String partyName, float seats, float votes) {
			super(partyName);
			setProjectedNumberOfSeats(seats);
			setProjectedPercentageOfVotes(votes);
		}
	}

	@Test
	public void test_Constructor_zeroPollsAndSeats() {
		PollList pl = new PollList(0,0);
		int expectedListLength = 5;
		int expectedNumOfSeats = 10;
		
		assertEquals("If number of polls is zero or less, expect number of polls to be adjusted to 5", expectedListLength, pl.getPolls().length);
		assertEquals("If number of seats is zero or less, expect number of seats to be adjusted to 10", expectedNumOfSeats, pl.getNumOfSeats());
	}

	@Test
	public void test_Constructor_negativePollsAndSeats() {
		PollList pl = new PollList(-1,-2);
		int expectedListLength = 5;
		int expectedNumOfSeats = 10;
		
		assertEquals("If number of polls is zero or less, expect number of polls to be adjusted to 5", expectedListLength, pl.getPolls().length);
		assertEquals("If number of seats is zero or less, expect number of seats to be adjusted to 10", expectedNumOfSeats, pl.getNumOfSeats());
	}

	@Test
	public void test_Constructor_onePollsAndtwoSeats() {
		PollList pl = new PollList(1,2);
		int expectedListLength = 1;
		int expectedNumOfSeats = 2;
		
		assertEquals("CreatedPollList for 1 poll", expectedListLength, pl.getPolls().length);
		assertEquals("CreatedPollList for 2 seats", expectedNumOfSeats, pl.getNumOfSeats());
	}

	@Test
	public void test_Constructor_tenPollsAndoneSeats() {
		PollList pl = new PollList(10,1);
		int expectedListLength = 10;
		int expectedNumOfSeats = 1;
		
		assertEquals("CreatedPollList for 10 polls", expectedListLength, pl.getPolls().length);
		assertEquals("CreatedPollList for 1 seat", expectedNumOfSeats, pl.getNumOfSeats());
	}
	
	@Test
	public void test_addPoll_toEmptyList() {
		Poll p = new PollMock("test", 5);
		PollList pl = new PollList(5, 20);
		
		pl.addPoll(p);
		
		assertEquals("Added poll to empty list, expected first poll in list to the poll added.", p, pl.getPolls()[0]);
		assertNull("Added poll to empty list, expected second poll in list to be null.", pl.getPolls()[1]);
		assertNull("Added poll to empty list, expected third poll in list to be null.", pl.getPolls()[2]);
		assertNull("Added poll to empty list, expected fourth poll in list to be null.", pl.getPolls()[3]);
		assertNull("Added poll to empty list, expected fifth poll in list to be null.", pl.getPolls()[4]);
		
	}

	@Test
	public void test_addPoll_addingNull() {
		Poll p = new PollMock("test", 5);
		PollList pl = new PollList(5, 20);
		
		pl.addPoll(p);
		pl.addPoll(null);
		
		assertEquals("Added null, should leave list unchanged.  testing index 0.", p, pl.getPolls()[0]);
		assertNull("Added null, should leave list unchanged.  testing index 1.", pl.getPolls()[1]);
		assertNull("Added null, should leave list unchanged.  testing index 2.", pl.getPolls()[2]);
		assertNull("Added null, should leave list unchanged.  testing index 3.", pl.getPolls()[3]);
		assertNull("Added null, should leave list unchanged.  testing index 4.", pl.getPolls()[4]);
		
	}

	@Test
	public void test_addPoll_fillList() {
		Poll p1 = new PollMock("test1", 1);
		Poll p2 = new PollMock("test2", 2);
		Poll p3 = new PollMock("test3", 3);
		PollList pl = new PollList(3, 45);
		
		pl.addPoll(p1);
		pl.addPoll(p2);
		pl.addPoll(p3);
		
		assertEquals("Added three polls to fill the list of size 3.  Testing poll at index 0", p1, pl.getPolls()[0]);
		assertEquals("Added three polls to fill the list of size 3.  Testing poll at index 1", p2, pl.getPolls()[1]);
		assertEquals("Added three polls to fill the list of size 3.  Testing poll at index 2", p3, pl.getPolls()[2]);
	}

	@Test
	public void test_addPoll_pollAlreadyExistsAtIndex0() {
		Poll p1 = new PollMock("duplicate", 1);
		Poll p2 = new PollMock("test2", 2);
		Poll p3 = new PollMock("test3", 3);
		PollList pl = new PollList(3, 45);
		
		pl.addPoll(p1);
		pl.addPoll(p2);
		pl.addPoll(p3);
		
		Poll p4 = new PollMock("duplicate", 234);
		pl.addPoll(p4);
		
		assertEquals("First poll in list should have been replaced when adding duplicate", p4, pl.getPolls()[0]);
		assertEquals("First should have been replaced but second element at index 1 should have been unchanged.  Testing poll at index 1", p2, pl.getPolls()[1]);
		assertEquals("First should have been replaced but third element at index 2 should have been unchanged.  Testing poll at index 2", p3, pl.getPolls()[2]);
	}

	@Test
	public void test_addPoll_pollAlreadyExistsAtIndex1() {
		Poll p1 = new PollMock("test1", 1);
		Poll p2 = new PollMock("duplicate", 2);
		Poll p3 = new PollMock("test3", 3);
		PollList pl = new PollList(3, 45);
		
		pl.addPoll(p1);
		pl.addPoll(p2);
		pl.addPoll(p3);
		
		Poll p4 = new PollMock("duplicate", 234);
		pl.addPoll(p4);
		
		assertEquals("Second poll in list should have been replaced when adding duplicate", p4, pl.getPolls()[1]);
		assertEquals("Second should have been replaced but first element at index 0 should have been unchanged.  Testing poll at index 0", p1, pl.getPolls()[0]);
		assertEquals("Second should have been replaced but third element at index 2 should have been unchanged.  Testing poll at index 2", p3, pl.getPolls()[2]);
	}

	@Test
	public void test_addPoll_pollAlreadyExistsAtLastIndex_DifferentCase() {
		Poll p1 = new PollMock("test1", 1);
		Poll p2 = new PollMock("test2", 2);
		Poll p3 = new PollMock("Duplicate", 3);
		PollList pl = new PollList(3, 45);
		
		pl.addPoll(p1);
		pl.addPoll(p2);
		pl.addPoll(p3);
		
		Poll p4 = new PollMock("duplicate", 234);
		pl.addPoll(p4);
		
		assertEquals("Last poll in list should have been replaced when adding duplicate (if comparing case insensitive)", p4, pl.getPolls()[2]);
		assertEquals("Last should have been replaced but first element at index 0 should have been unchanged.  Testing poll at index 0", p1, pl.getPolls()[0]);
		assertEquals("Last should have been replaced but second element at index 1 should have been unchanged.  Testing poll at index 1", p2, pl.getPolls()[1]);
	}

	@Test
	public void test_addPoll_listIsFull() {
		Poll p1 = new PollMock("test1", 1);
		Poll p2 = new PollMock("test2", 2);
		Poll p3 = new PollMock("test3", 3);
		PollList pl = new PollList(3, 45);
		
		pl.addPoll(p1);
		pl.addPoll(p2);
		pl.addPoll(p3);
		
		Poll p4 = new PollMock("duplicate", 234);
		pl.addPoll(p4);
		
		assertEquals("Adding fourth poll to full list should leave list unchanged.  Testing at index 0.", p1, pl.getPolls()[0]);
		assertEquals("Adding fourth poll to full list should leave list unchanged.  Testing at index 1.", p2, pl.getPolls()[1]);
		assertEquals("Adding fourth poll to full list should leave list unchanged.  Testing at index 2.", p3, pl.getPolls()[2]);
	}
	
	@Test
	public void test_getAveragePartyData_PartyInAllPolls() {
		// setup first poll
		PartyMock[] parties1 = new PartyMock[3];
		parties1[0] = new PartyMock("zero", 100f, .25f);
		parties1[1] = new PartyMock("one", 200f, .5f);
		parties1[2] = new PartyMock("two", 100f, .25f);
		
		PollMock p1 = new PollMock("poll1", 400);
		p1.addParties(parties1);
		
		// setup second poll
		PartyMock[] parties2 = new PartyMock[3];
		parties2[1] = new PartyMock("zero", 50f, .1f);
		parties2[2] = new PartyMock("one", 300f, .75f);
		parties2[0] = new PartyMock("two", 50f, .15f);
		
		PollMock p2 = new PollMock("poll2", 400);
		p2.addParties(parties2);
		p2.nextIndex = 1;
		
		// setup third poll
		PartyMock[] parties3 = new PartyMock[3];
		parties3[2] = new PartyMock("zero", 200f, .1f);
		parties3[0] = new PartyMock("one", 100f, .75f);
		parties3[1] = new PartyMock("two", 100f, .15f);
		
		PollMock p3 = new PollMock("poll3", 400);
		p3.addParties(parties3);
		p3.nextIndex = 2;
		
		PollList pl = new PollList(3,400);
		pl.addPoll(p1);
		pl.addPoll(p2);
		pl.addPoll(p3);
		
		Party actual = pl.getAveragePartyData("zero");
		
		assertEquals("Party was in all polls in the list.  Testing average projected seats.", 116.66666, actual.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("Party was in all polls in the list.  Testing average projected votes.", .45f/3, actual.getProjectedPercentageOfVotes(), 0.0001);		
	}

	@Test
	public void test_getAveragePartyData_PartyInSomePolls() {
		// setup first poll
		PartyMock[] parties1 = new PartyMock[3];
		parties1[0] = new PartyMock("zero", 100f, .25f);
		parties1[1] = new PartyMock("one", 200f, .5f);
		parties1[2] = new PartyMock("two", 100f, .25f);
		
		PollMock p1 = new PollMock("poll1", 400);
		p1.addParties(parties1);
		p1.nextIndex = 1;
		
		// setup second poll
		PartyMock[] parties2 = new PartyMock[3];
		parties2[1] = new PartyMock("zero", 50f, .1f);
		parties2[2] = null;
		parties2[0] = new PartyMock("two", 50f, .15f);
		
		PollMock p2 = new PollMock("poll2", 400);
		p2.addParties(parties2);
		p2.nextIndex = 2;
		
		// setup third poll
		PartyMock[] parties3 = new PartyMock[3];
		parties3[2] = new PartyMock("zero", 200f, .1f);
		parties3[0] = new PartyMock("one", 100f, .75f);
		parties3[1] = new PartyMock("two", 100f, .15f);
		
		PollMock p3 = new PollMock("poll3", 400);
		p3.addParties(parties3);
		p3.nextIndex = 0;
		
		PollList pl = new PollList(3,400);
		pl.addPoll(p1);
		pl.addPoll(p2);
		pl.addPoll(p3);
		
		Party actual = pl.getAveragePartyData("one");
		
		assertEquals("Party was in two out of three polls in the list.  Testing average projected seats.", 150, actual.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("Party was in two out of three polls in the list.  Testing average projected votes.", 1.25f/2, actual.getProjectedPercentageOfVotes(), 0.0001);		
	}

	@Test
	public void test_getAveragePartyData_PartyInNoneOfThePolls() {
		// setup first poll
		PartyMock[] parties1 = new PartyMock[3];
		parties1[0] = null;
		parties1[1] = null;
		parties1[2] = null;
		
		PollMock p1 = new PollMock("poll1", 400);
		p1.addParties(parties1);
		p1.nextIndex = 1;
		
		// setup second poll
		PartyMock[] parties2 = new PartyMock[3];
		parties2[1] = null;
		parties2[2] = null;
		parties2[0] = null;
		
		PollMock p2 = new PollMock("poll2", 400);
		p2.addParties(parties2);
		p2.nextIndex = 2;
		
		// setup third poll
		PartyMock[] parties3 = new PartyMock[3];
		parties3[2] = null;
		parties3[0] = null;
		parties3[1] = null;
		
		PollMock p3 = new PollMock("poll3", 400);
		p3.addParties(parties3);
		p3.nextIndex = 0;
		
		PollList pl = new PollList(3,400);
		pl.addPoll(p1);
		pl.addPoll(p2);
		pl.addPoll(p3);
		
		Party actual = pl.getAveragePartyData("one");
		
		assertEquals("Party was in none of three polls in the list.  Testing average projected seats.", 0, actual.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("Party was in none of three polls in the list.  Testing average projected votes.", 0, actual.getProjectedPercentageOfVotes(), 0.0001);		
	}

	@Test
	public void test_getAggregatePoll_AllPollsSameSize_noNormalizingNeeded() {
		// setup first poll
		PartyMock[] parties1 = new PartyMock[3];
		parties1[0] = new PartyMock("zero", 100f, .25f);
		parties1[1] = new PartyMock("one", 200f, .5f);
		parties1[2] = new PartyMock("two", 100f, .25f);
		
		PollMock p1 = new PollMock("poll1", 400);
		p1.addParties(parties1);
		
		// setup second poll
		PartyMock[] parties2 = new PartyMock[3];
		parties2[1] = new PartyMock("zero", 50f, .1f);
		parties2[2] = new PartyMock("one", 300f, .75f);
		parties2[0] = new PartyMock("two", 50f, .15f);
		
		PollMock p2 = new PollMock("poll2", 400);
		p2.addParties(parties2);
		p2.nextIndex = 1;
		
		// setup third poll
		PartyMock[] parties3 = new PartyMock[3];
		parties3[2] = new PartyMock("zero", 200f, .1f);
		parties3[0] = new PartyMock("one", 100f, .75f);
		parties3[1] = new PartyMock("two", 100f, .15f);
		
		PollMock p3 = new PollMock("poll3", 400);
		p3.addParties(parties3);
		p3.nextIndex = 2;
		
		PollList pl = new PollList(3,400);
		pl.addPoll(p1);
		pl.addPoll(p2);
		pl.addPoll(p3);
		
		String[] partyNames = {"zero","one","two"};
		
		Poll aggregate = pl.getAggregatePoll(partyNames);
		Party[] actualParties = aggregate.getPartiesSortedBySeats();
		Party actualZero = null;
		Party actualOne = null;
		Party actualTwo = null;
		for (Party p : actualParties) {
			if (p.getName().equals("zero")) {
				actualZero = p;
			} else if (p.getName().equals("one")) {
				actualOne = p;
			} else if (p.getName().equals("two")) {
				actualTwo = p;
			}
		}
		
		assertEquals("Aggregate poll over three polls each the same size and same set of parties, testing aggregate poll name", "Aggregate", aggregate.getPollName());
		assertEquals("Aggregate poll over three polls each the same size and same set of parties, testing party 'zero' avg seats (avg of 100,50,200)", 116.6666f, actualZero.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("Aggregate poll over three polls each the same size and same set of parties, testing party 'zero' avg votes (avg of .25,.1,.1)", .15f, actualZero.getProjectedPercentageOfVotes(), 0.0001);
		assertEquals("Aggregate poll over three polls each the same size and same set of parties, testing party 'one' avg seats (avg of 200,300,100)", 200, actualOne.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("Aggregate poll over three polls each the same size and same set of parties, testing party 'one' avg votes (avg of .5,.75,.75)", 0.66667f, actualOne.getProjectedPercentageOfVotes(), 0.0001);
		assertEquals("Aggregate poll over three polls each the same size and same set of parties, testing party 'two' avg seats (avg of 100,50,100)", 83.33333f, actualTwo.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("Aggregate poll over three polls each the same size and same set of parties, testing party 'two' avg votes (avg of .25,.15,.15)", 0.18333f, actualTwo.getProjectedPercentageOfVotes(), 0.0001);
	}

	@Test
	public void test_getAggregatePoll_somePollsMissingParties_noNormalizingNeeded() {
		// setup first poll
		PartyMock[] parties1 = new PartyMock[3];
		parties1[0] = new PartyMock("zero", 100f, .25f);
		parties1[1] = null;
		parties1[2] = new PartyMock("two", 100f, .25f);
		
		PollMock p1 = new PollMock("poll1", 400);
		p1.addParties(parties1);
		
		// setup second poll
		PartyMock[] parties2 = new PartyMock[3];
		parties2[1] = new PartyMock("zero", 50f, .1f);
		parties2[2] = new PartyMock("one", 300f, .5f);
		parties2[0] = new PartyMock("two", 50f, .15f);
		
		PollMock p2 = new PollMock("poll2", 400);
		p2.addParties(parties2);
		p2.nextIndex = 1;
		
		// setup third poll
		PartyMock[] parties3 = new PartyMock[3];
		parties3[2] = null;
		parties3[0] = new PartyMock("one", 100f, .75f);
		parties3[1] = new PartyMock("two", 100f, .15f);
		
		PollMock p3 = new PollMock("poll3", 400);
		p3.addParties(parties3);
		p3.nextIndex = 2;
		
		PollList pl = new PollList(3,400);
		pl.addPoll(p1);
		pl.addPoll(p2);
		pl.addPoll(p3);
		
		String[] partyNames = {"zero","one","two"};
		
		Poll aggregate = pl.getAggregatePoll(partyNames);
		Party[] actualParties = aggregate.getPartiesSortedBySeats();
		Party actualZero = null;
		Party actualOne = null;
		Party actualTwo = null;
		for (Party p : actualParties) {
			if (p.getName().equals("zero")) {
				actualZero = p;
			} else if (p.getName().equals("one")) {
				actualOne = p;
			} else if (p.getName().equals("two")) {
				actualTwo = p;
			}
		}
		
		assertEquals("testing aggregate poll name", "Aggregate", aggregate.getPollName());
		assertEquals("testing party 'zero' avg seats (avg of 100,50)", 75, actualZero.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("testing party 'zero' avg votes (avg of .25,.1)", .175f, actualZero.getProjectedPercentageOfVotes(), 0.0001);
		assertEquals("testing party 'one' avg seats (avg of 300,100) (One poll is missing this party)", 200, actualOne.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("testing party 'one' avg votes (avg of .5,.75)(One poll is missing this party)", 0.625f, actualOne.getProjectedPercentageOfVotes(), 0.0001);
		assertEquals("testing party 'two' avg seats (avg of 100,50,100)", 83.33333f, actualTwo.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("testing party 'two' avg votes (avg of .25,.15,.15)", 0.18333f, actualTwo.getProjectedPercentageOfVotes(), 0.0001);
	}

	@Test
	public void test_getAggregatePoll_normalizingNeededForVotes() {
		// setup first poll
		PartyMock[] parties1 = new PartyMock[4];
		parties1[0] = new PartyMock("zero", 100f, .25f);
		parties1[1] = new PartyMock("one", 150f, .45f);
		parties1[2] = new PartyMock("two", 100f, .25f);
		parties1[3] = new PartyMock("three", 50f, .05f);
		
		PollMock p1 = new PollMock("poll1", 400);
		p1.addParties(parties1);
		
		// setup second poll
		PartyMock[] parties2 = new PartyMock[4];
		parties2[1] = new PartyMock("zero", 50f, .1f);
		parties2[2] = new PartyMock("one", 250f, .70f);
		parties2[3] = new PartyMock("two", 51f, .15f);
		parties2[0] = new PartyMock("three", 22f, .05f);
		
		PollMock p2 = new PollMock("poll2", 400);
		p2.addParties(parties2);
		p2.nextIndex = 1;
		
		// setup third poll
		PartyMock[] parties3 = new PartyMock[4];
		parties3[2] = new PartyMock("zero", 200f, .1f);
		parties3[3] = new PartyMock("one", 100f, .75f);
		parties3[0] = new PartyMock("two", 100f, .15f);
		parties3[1] = new PartyMock("three", 22,0.05f);
		
		PollMock p3 = new PollMock("poll3", 400);
		p3.addParties(parties3);
		p3.nextIndex = 2;

		// setup fourth poll
		PartyMock[] parties4 = new PartyMock[4];
		parties4[0] = new PartyMock("zero", 50f, .1f);
		parties4[1] = new PartyMock("one", 250f, .70f);
		parties4[2] = new PartyMock("two", 51f, .15f);
		parties4[3] = null;
		
		PollMock p4 = new PollMock("poll4", 400);
		p4.addParties(parties4);
		
		
		PollList pl = new PollList(4, 400);
		pl.addPoll(p1);
		pl.addPoll(p2);
		pl.addPoll(p3);
		pl.addPoll(p4);
		
		String[] partyNames = {"zero","one","two","three"};
		
		Poll aggregate = pl.getAggregatePoll(partyNames);
		Party[] actualParties = aggregate.getPartiesSortedBySeats();
		Party actualZero = null;
		Party actualOne = null;
		Party actualTwo = null;
		Party actualThree = null;
		for (Party p : actualParties) {
			if (p.getName().equals("zero")) {
				actualZero = p;
			} else if (p.getName().equals("one")) {
				actualOne = p;
			} else if (p.getName().equals("two")) {
				actualTwo = p;
			} else if (p.getName().equals("three")) {
				actualThree = p;
			}
		}
		
		assertEquals("testing aggregate poll name", "Aggregate", aggregate.getPollName());
		assertEquals("testing party 'zero' avg seats (avg of 100,50,200,50)", 100, actualZero.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("testing party 'zero' avg votes (avg of .25,.1,.1,.1), normalized since aggregate contained 101.25% instead of 100.", .135802f, actualZero.getProjectedPercentageOfVotes(), 0.0001);
		assertEquals("testing party 'one' avg seats (avg of 150,250,100,250)", 187.5, actualOne.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("testing party 'one' avg votes (avg of .45,.7,.75,.7) normalized since aggregate contained 101.25% instead of 100.", 0.641975f, actualOne.getProjectedPercentageOfVotes(), 0.0001);
		assertEquals("testing party 'two' avg seats (avg of 100,51,100,51)", 75.5f, actualTwo.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("testing party 'two' avg votes (avg of .25,.15,.15,.5) normalized since aggregate contained 101.25% instead of 100.", 0.17284f, actualTwo.getProjectedPercentageOfVotes(), 0.0001);
		assertEquals("testing party 'three' avg seats (avg of 50,22,22)", 31.3333333f, actualThree.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("testing party 'three' avg votes (avg of .05,.05, .05) normalized since aggregate contained 101.25% instead of 100.", 0.049383f, actualThree.getProjectedPercentageOfVotes(), 0.0001);
	}

	@Test
	public void test_getAggregatePoll_normalizingNeededForSeats() {
		// setup first poll
		PartyMock[] parties1 = new PartyMock[4];
		parties1[0] = new PartyMock("zero", 100f, .25f);
		parties1[1] = new PartyMock("one", 150f, .45f);
		parties1[2] = new PartyMock("two", 100f, .25f);
		parties1[3] = new PartyMock("three", 50f, .05f);
		
		PollMock p1 = new PollMock("poll1", 400);
		p1.addParties(parties1);
		
		// setup second poll
		PartyMock[] parties2 = new PartyMock[4];
		parties2[1] = new PartyMock("zero", 127f, .1f);
		parties2[2] = new PartyMock("one", 200f, .70f);
		parties2[3] = new PartyMock("two", 51f, .15f);
		parties2[0] = new PartyMock("three", 22f, .05f);
		
		PollMock p2 = new PollMock("poll2", 400);
		p2.addParties(parties2);
		p2.nextIndex = 1;
		
		// setup third poll
		PartyMock[] parties3 = new PartyMock[4];
		parties3[2] = new PartyMock("zero", 100f, .1f);
		parties3[3] = new PartyMock("one", 200f, .75f);
		parties3[0] = new PartyMock("two", 100f, .15f);
		parties3[1] = null;
		
		PollMock p3 = new PollMock("poll3", 400);
		p3.addParties(parties3);
		p3.nextIndex = 2;

		// setup fourth poll
		PartyMock[] parties4 = new PartyMock[4];
		parties4[0] = new PartyMock("zero", 50f, .1f);
		parties4[1] = new PartyMock("one", 250f, .60f);
		parties4[2] = new PartyMock("two", 52f, .15f);
		parties4[3] = new PartyMock("three", 22, 0.05f);
		
		PollMock p4 = new PollMock("poll4", 400);
		p4.addParties(parties4);
		
		
		PollList pl = new PollList(4, 400);
		pl.addPoll(p1);
		pl.addPoll(p2);
		pl.addPoll(p3);
		pl.addPoll(p4);
		
		String[] partyNames = {"zero","one","two","three"};
		
		Poll aggregate = pl.getAggregatePoll(partyNames);
		Party[] actualParties = aggregate.getPartiesSortedBySeats();
		Party actualZero = null;
		Party actualOne = null;
		Party actualTwo = null;
		Party actualThree = null;
		for (Party p : actualParties) {
			if (p.getName().equals("zero")) {
				actualZero = p;
			} else if (p.getName().equals("one")) {
				actualOne = p;
			} else if (p.getName().equals("two")) {
				actualTwo = p;
			} else if (p.getName().equals("three")) {
				actualThree = p;
			}
		}
		
		assertEquals("testing aggregate poll name", "Aggregate", aggregate.getPollName());
		assertEquals("testing party 'zero' avg seats (avg of 100,127,100,50) normalized since aggregate contained 401.22 instead of 400 seats.", 93.93688, actualZero.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("testing party 'zero' avg votes (avg of .25,.1,.1,.1), ", .1375f, actualZero.getProjectedPercentageOfVotes(), 0.0001);
		assertEquals("testing party 'one' avg seats (avg of 150,200,200,250) normalized since aggregate contained 401.22 instead of 400seats.", 199.3355f, actualOne.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("testing party 'one' avg votes (avg of .45,.7,.75,.6)", 0.625f, actualOne.getProjectedPercentageOfVotes(), 0.0001);
		assertEquals("testing party 'two' avg seats (avg of 100,51,100,52) normalized since aggregate contained 401.22 instead of 400 seats.", 75.49834f, actualTwo.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("testing party 'two' avg votes (avg of .25,.15,.15,.15)", 0.175f, actualTwo.getProjectedPercentageOfVotes(), 0.0001);
		assertEquals("testing party 'three' avg seats (avg of 50,22,22) normalized since aggregate contained 401.22 instead of 400 seats.", 31.22924f, actualThree.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("testing party 'three' avg votes (avg of .05,.05, .05)", 0.05f, actualThree.getProjectedPercentageOfVotes(), 0.0001);
	}
	
	@Test
	public void test_toString1() {
		PollList pl = new PollList(5, 50);
		
		assertEquals("Number of seats: 50", pl.toString());
	}

	@Test
	public void test_toString2() {
		PollList pl = new PollList(5, 75);
		
		assertEquals("Number of seats: 75", pl.toString());
	}
}
