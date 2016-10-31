package de.htwg.blackjack.model.impl;

import static org.junit.Assert.*;

import de.htwg.blackjack.model.Card;

import org.junit.Before;
import org.junit.Test;

public class CardTest {
	Card card;
	
	@Before
	public void setUp() throws Exception {
		card = new Card("jack", "spades");
	}
	
	@Test
	public void testConstruktor() {
		// Check if card is initialized correctly
		assertNotNull(card);
		assertEquals("spades", card.getType());
		assertEquals("jack", card.getPicture());
	}
	
	@Test
	public void testToString() {
		// Check if toString() works
		assertEquals("jack-spades", card.toString());
	}

}
