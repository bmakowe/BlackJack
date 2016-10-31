package de.htwg.blackjack.model.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.blackjack.model.Card;
import de.htwg.blackjack.model.Hand;

public class HandTest {
	private Hand testHand;
	
	@Before
	public void setUp() {
		testHand = new Hand();
	}
	
	@Test
	public void testConstructor() {
		testHand = new Hand(true);
		assertEquals(true, testHand.isActive());
	}
	
	@Test
	public void testAddCards() {
		testHand.addCard(new Card("jack","diamonds"));
		assertEquals(1, testHand.getSize());
	}
	
	@Test
	public void testDispose() {
		testHand.dispose();
		assertEquals(0, testHand.getSize());
	}
	
	@Test
	public void testDrawCard() {
		testHand.addCard(new Card("jack","diamonds"));
		Card card = testHand.drawCard();
		assertEquals("jack-diamonds", card.toString());
		assertEquals(0, testHand.getSize());
	}
	
	@Test
	public void testGetSize() {
		assertEquals(testHand.getCards().size(), testHand.getSize());
	}
	
	@Test
	public void testToString() {
		testHand.addCard(new Card("jack","diamonds"));
		testHand.addCard(new Card("ace","spades"));
		assertEquals("[jack-diamonds, ace-spades]", testHand.toString());
	}
	
	@Test
	public void testIsSetActive() {
		testHand.setActive(true);
		assertTrue(testHand.isActive());
	}
	
	@Test
	public void testIterator() {
		testHand.addCard(new Card("jack","diamonds"));
		testHand.addCard(new Card("ace","spades"));
		int i = 0;
		for (Card card : testHand) {
			card.getPicture();
			++i;
		}
		assertEquals(2, i);
	}
}
