package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PollTest {
	class MockParty extends Party {
		String stringRepresentation;
		public MockParty(String partyName) {
			super(partyName);
			stringRepresentation = partyName;
		}
		public String toString() {
			return stringRepresentation;
		}
	}

	@Test
	public void test_constructor_validPollSize() {
		Poll p = new Poll("Poll Test", 5);
		assertEquals("Created poll with name 'Poll Test'", "Poll Test", p.getPollName());
		assertEquals("Created poll with room for 5 parties but did not add any parties yet", 0, p.getNumberOfParties());
	}

	@Test
	public void test_constructor_invalidZeroParties() {
		Poll p = new Poll("Poll Test 2", 0);
		assertEquals("Created poll with name 'Poll Test'", "Poll Test 2", p.getPollName());
		
		// should be able to add 10 parties
		Party p1 = new MockParty("p1");
		Party p2 = new MockParty("p2");
		Party p3 = new MockParty("p3");
		Party p4 = new MockParty("p4");
		Party p5 = new MockParty("p5");
		Party p6 = new MockParty("p6");
		Party p7 = new MockParty("p7");
		Party p8 = new MockParty("p8");
		Party p9 = new MockParty("p9");
		Party p10 = new MockParty("p10");
		
		p.addParty(p1);
		p.addParty(p2);
		p.addParty(p3);
		p.addParty(p4);
		p.addParty(p5);
		p.addParty(p6);
		p.addParty(p7);
		p.addParty(p8);
		p.addParty(p9);
		p.addParty(p10);
				

		assertEquals("Created poll with room for 0 parties, should be able to add 10 parties", 10, p.getNumberOfParties());
	}

	@Test
	public void test_constructor_invalidNegativeParties() {
		Poll p = new Poll("Poll Test", -1);
		assertEquals("Created poll with name 'Poll Test'", "Poll Test", p.getPollName());
		// should be able to add 10 parties
		Party p1 = new MockParty("p1");
		Party p2 = new MockParty("p2");
		Party p3 = new MockParty("p3");
		Party p4 = new MockParty("p4");
		Party p5 = new MockParty("p5");
		Party p6 = new MockParty("p6");
		Party p7 = new MockParty("p7");
		Party p8 = new MockParty("p8");
		Party p9 = new MockParty("p9");
		Party p10 = new MockParty("p10");
		
		p.addParty(p1);
		p.addParty(p2);
		p.addParty(p3);
		p.addParty(p4);
		p.addParty(p5);
		p.addParty(p6);
		p.addParty(p7);
		p.addParty(p8);
		p.addParty(p9);
		p.addParty(p10);
				

		assertEquals("Created poll with room for -1 parties, should be able to add 10 parties", 10, p.getNumberOfParties());
	}

	@Test
	public void test_addParty_firstAdd() {
		Poll p = new Poll("Poll Test", 4);
		Party party = new MockParty("PartyOne");
		p.addParty(party);
		assertEquals("Created poll with single party, expected number of parties to be one", 1, p.getNumberOfParties());
		assertEquals("Created poll with single party, expected that party to be found in getParty method.", party, p.getParty("PartyOne"));
	}

	@Test
	public void test_addParty_addTwoEachWithTheirOwnName() {
		Poll p = new Poll("Poll Test", 7);
		Party p1 = new MockParty("PartyOne");
		Party p2 = new MockParty("Party Two");
		p.addParty(p1);
		p.addParty(p2);
		assertEquals("Created poll with two unique parties, expected number of parties to be two", 2, p.getNumberOfParties());
		assertEquals("Created poll with two parties, expected party 'PartyOne' to be found in getParty method.", p1, p.getParty("PartyOne"));
		assertEquals("Created poll with two parties, expected party 'Party Two' to be found in getParty method.", p2, p.getParty("Party Two"));
	}

	@Test
	public void test_addParty_addUntilFullAllUnique() {
		Poll p = new Poll("Poll Test", 6);
		Party p1 = new MockParty("PartyOne");
		Party p2 = new MockParty("Party Two");
		Party p3 = new MockParty("Party3");
		Party p4 = new MockParty("Party 4");
		Party p5 = new MockParty("Fifth Party");
		Party p6 = new MockParty("6th Party");
		p.addParty(p1);
		p.addParty(p2);
		p.addParty(p3);
		p.addParty(p4);
		p.addParty(p5);
		p.addParty(p6);
		assertEquals("Created poll with six parties, expected number of parties to be six", 6, p.getNumberOfParties());
		assertEquals("Created poll with six parties, expected 'PartyOne' by getParty method.", p1, p.getParty("PartyOne"));
		assertEquals("Created poll with six parties, expected 'Party Two' by getParty method.", p2, p.getParty("Party Two"));
		assertEquals("Created poll with six parties, expected 'Party3' by getParty method.", p3, p.getParty("Party3"));
		assertEquals("Created poll with six parties, expected 'Party 4' by getParty method.", p4, p.getParty("Party 4"));
		assertEquals("Created poll with six parties, expected 'Fifth Party' by getParty method.", p5, p.getParty("Fifth Party"));
		assertEquals("Created poll with six parties, expected '6th Party' by getParty method.", p6, p.getParty("6th Party"));
	}

	@Test
	public void test_addParty_addOneToManyAllUnique() {
		Poll p = new Poll("Poll Test", 6);
		Party p1 = new MockParty("PartyOne");
		Party p2 = new MockParty("Party Two");
		Party p3 = new MockParty("Party3");
		Party p4 = new MockParty("Party 4");
		Party p5 = new MockParty("Fifth Party");
		Party p6 = new MockParty("6th Party");
		Party p7 = new MockParty("No room for 7th");
				
		p.addParty(p1);
		p.addParty(p2);
		p.addParty(p3);
		p.addParty(p4);
		p.addParty(p5);
		p.addParty(p6);
		p.addParty(p7);;
		
		assertEquals("Created poll with six parties, expected number of parties to be six", 6, p.getNumberOfParties());
		assertEquals("Created poll with six parties, expected 'PartyOne' by getParty method.", p1, p.getParty("PartyOne"));
		assertEquals("Created poll with six parties, expected 'Party Two' by getParty method.", p2, p.getParty("Party Two"));
		assertEquals("Created poll with six parties, expected 'Party3' by getParty method.", p3, p.getParty("Party3"));
		assertEquals("Created poll with six parties, expected 'Party 4' by getParty method.", p4, p.getParty("Party 4"));
		assertEquals("Created poll with six parties, expected 'Fifth Party' by getParty method.", p5, p.getParty("Fifth Party"));
		assertEquals("Created poll with six parties, expected '6th Party' by getParty method.", p6, p.getParty("6th Party"));
		assertNull("Created poll with six parties, expected 7th party add to fail, so getParty should return null", p.getParty("No room for 7th"));
	}

	@Test
	public void test_addParty_addDuplicate() {
		Poll p = new Poll("Poll Test", 7);
		Party p1 = new MockParty("PartyOne");
		Party p2 = new MockParty("PartyOne");
		p.addParty(p1);
		p.addParty(p2);
		assertEquals("Created poll with two parties with the same name, expected number of parties to be one", 1, p.getNumberOfParties());
		assertEquals("Created poll with two parties, expected party 'PartyOne' to be found in getParty method.", p2, p.getParty("PartyOne"));
	}

	@Test
	public void test_addParty_addDuplicateCaseInsensitive() {
		Poll p = new Poll("Poll Test", 7);
		Party p1 = new MockParty("PartyOne");
		Party p2 = new MockParty("partyONE");
		p.addParty(p1);
		p.addParty(p2);
		assertEquals("Created poll with two parties with the same name, expected number of parties to be one", 1, p.getNumberOfParties());
		assertEquals("Created poll with two parties, expected party 'partyONE' (second, duplicate) to be found in getParty method.", p2, p.getParty("PartyOne"));
	}

	@Test
	public void test_addParty_addDuplicateFirst() {
		Poll p = new Poll("Poll Test", 6);
		Party p1 = new MockParty("PartyOne");
		Party p2 = new MockParty("Party Two");
		Party p3 = new MockParty("Party3");
		Party p4 = new MockParty("Party 4");
		Party p5 = new MockParty("Fifth Party");
		Party p6 = new MockParty("6th Party");
		Party p7 = new MockParty("PartyOne");
				
		p.addParty(p1);
		p.addParty(p2);
		p.addParty(p3);
		p.addParty(p4);
		p.addParty(p5);
		p.addParty(p6);
		p.addParty(p7);;
		
		assertEquals("Created poll with six parties, expected number of parties to be six", 6, p.getNumberOfParties());
		assertEquals("Created poll with six parties, expected 'PartyOne' by getParty method to be duplicate.", p7, p.getParty("PartyOne"));
		assertEquals("Created poll with six parties, expected 'Party Two' by getParty method.", p2, p.getParty("Party Two"));
		assertEquals("Created poll with six parties, expected 'Party3' by getParty method.", p3, p.getParty("Party3"));
		assertEquals("Created poll with six parties, expected 'Party 4' by getParty method.", p4, p.getParty("Party 4"));
		assertEquals("Created poll with six parties, expected 'Fifth Party' by getParty method.", p5, p.getParty("Fifth Party"));
		assertEquals("Created poll with six parties, expected '6th Party' by getParty method.", p6, p.getParty("6th Party"));
	}

	@Test
	public void test_addParty_addDuplicateLast() {
		Poll p = new Poll("Poll Test", 6);
		Party p1 = new MockParty("PartyOne");
		Party p2 = new MockParty("Party Two");
		Party p3 = new MockParty("Party3");
		Party p4 = new MockParty("Party 4");
		Party p5 = new MockParty("Fifth Party");
		Party p6 = new MockParty("6th Party");
		Party p7 = new MockParty("6th Party");
				
		p.addParty(p1);
		p.addParty(p2);
		p.addParty(p3);
		p.addParty(p4);
		p.addParty(p5);
		p.addParty(p6);
		p.addParty(p7);;
		
		assertEquals("Created poll with six parties, expected number of parties to be six", 6, p.getNumberOfParties());
		assertEquals("Created poll with six parties, expected 'PartyOne' by getParty method.", p1, p.getParty("PartyOne"));
		assertEquals("Created poll with six parties, expected 'Party Two' by getParty method.", p2, p.getParty("Party Two"));
		assertEquals("Created poll with six parties, expected 'Party3' by getParty method.", p3, p.getParty("Party3"));
		assertEquals("Created poll with six parties, expected 'Party 4' by getParty method.", p4, p.getParty("Party 4"));
		assertEquals("Created poll with six parties, expected 'Fifth Party' by getParty method.", p5, p.getParty("Fifth Party"));
		assertEquals("Created poll with six parties, expected '6th Party' to be replaced by duplicate add.", p7, p.getParty("6th Party"));
	}

	@Test
	public void test_addParty_null() {
		Poll p = new Poll("Poll Test", 6);
		Party p1 = new MockParty("PartyOne");
		assertEquals("Call addParty with value of argument null.  Should leave array unchanged.", 0, p.getNumberOfParties());		
	}
	
	@Test
	public void test_getPartiesSortedBySeats() {
		Poll p = new Poll("poll1", 5);
		Party p1 = new Party("p1", 60,0.5f);
		Party p2 = new Party("p2", 50,0.5f);
		Party p3 = new Party("p3", 40,0.5f);
		Party p4 = new Party("p4", 30,0.5f);
		Party p5 = new Party("p5", 20,0.5f);
		Party[] expectedList = {p1,p2,p3,p4,p5};
		p.addParty(p5);
		p.addParty(p1);
		p.addParty(p3);
		p.addParty(p4);
		p.addParty(p2);
		
		Party[] actualList = p.getPartiesSortedBySeats();
		
		for (int index = 0; index < 5; index++) {
			assertEquals("Expected party " + expectedList[index].getName() + " to be at index " + index, expectedList[index].getName(), actualList[index].getName());
		}
		
	}
	
	@Test
	public void test_getPartiesSortedByVotes() {
		Poll p = new Poll("poll1", 5);
		Party p1 = new Party("p1", 30,0.6f);
		Party p2 = new Party("p2", 30,0.5f);
		Party p3 = new Party("p3", 30,0.4f);
		Party p4 = new Party("p4", 30,0.3f);
		Party p5 = new Party("p5", 30,0.2f);
		Party[] expectedList = {p1,p2,p3,p4,p5};
		p.addParty(p5);
		p.addParty(p1);
		p.addParty(p3);
		p.addParty(p4);
		p.addParty(p2);
		
		Party[] actualList = p.getPartiesSortedByVotes();
		
		for (int index = 0; index < 5; index++) {
			assertEquals("Expected party " + expectedList[index].getName() + " to be at index " + index, expectedList[index].getName(), actualList[index].getName());
		}
	}
	
	@Test
	public void test_toString_emptyPoll() {
		Poll p = new Poll("Poll test toString", 4);
		assertEquals("getting string representation of empty poll with name 'Poll test toString'", "Poll test toString", p.toString().trim());
	}

	@Test
	public void test_toString_oneParty() {
		Poll p = new Poll("Poll name", 4);
		Party p1 = new MockParty("p1");
		p.addParty(p1);
		assertEquals("getting string representation of poll with name 'Poll name' and one party", "Poll name\np1", p.toString().trim());
	}

	@Test
	public void test_toString_manyParties() {
		Poll p = new Poll("Poll name too", 3);
		Party p1 = new MockParty("p1");
		Party p2 = new MockParty("p2");
		Party p3 = new MockParty("p3");

		p.addParty(p1);
		p.addParty(p2);
		p.addParty(p3);

		assertEquals("getting string representation of poll with name 'Poll name' and three parties", "Poll name too\np1\np2\np3", p.toString().trim());
	}

	// no tests for sorting methods.  Note: if comparator is implemented correctly then
	// calling Arrays.sort should mean the sorting is correct.
}
