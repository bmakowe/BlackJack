package de.htwg.blackjack.model.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.blackjack.model.Card;
import de.htwg.blackjack.model.Dealer;
import de.htwg.blackjack.model.Hand;

public class DealerTest {
	    private Dealer dealer;
	    
	    @Before
	    public void setUp() {
	    	dealer = new Dealer();
	    }
	    
	    @Test
		public void testTakeCard() {
			dealer.takeCard(new Card("jack", "diamonds"));
			assertEquals(1, dealer.getActiveHand().getSize());
		}
	    
	    @Test
		public void testGetActiveHand() {
	    	dealer.takeCard(new Card("jack", "diamonds"));
	    	Hand hand = dealer.getActiveHand();
	    	Card card = hand.drawCard();
	    	assertEquals("jack-diamonds", card.toString());
		}

	    @Test
	    public void testReset() {
	    	dealer.takeCard(new Card("jack", "diamonds"));
	    	dealer.reset();
	    	assertEquals(0, dealer.getActiveHand().getSize());
	    }
}
