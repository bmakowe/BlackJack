package de.htwg.blackjack.model.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.blackjack.model.Card;
import de.htwg.blackjack.model.Hand;
import de.htwg.blackjack.model.Player;

public class PlayerTest {
	private Player player;
	
	@Before
	public void setUp() {
		player = new Player();
	}
	
	@Test
	public void testTakeCard() {
		player.takeCard(new Card("jack", "diamonds"));
		Card card = player.getActiveHand().drawCard();
		assertEquals("jack-diamonds", card.toString());
	}
	
	@Test
	public void testGetAktiveHand() {
		Hand hand = new Hand(true);
		hand.addCard(new Card("jack", "diamonds"));
		player.getHands().clear();
		player.getHands().add(new Hand(false));
		player.getHands().add(hand);
		player.getHands().add(new Hand(false));
		Hand active = player.getActiveHand();
		assertEquals(hand, active);
		
		// no hands
		player.getHands().clear();
		assertEquals(null, player.getActiveHand());
	}
	
	@Test
	public void testGetNumberOfHands() {
		player.takeCard(null);
		player.split();
		assertEquals(2, player.numberOfHands());
	}
	
	@Test
	public void testSwitchHand() {
		player.getHands().add(new Hand(true));
		player.getHands().add(new Hand(false));
		Hand hand1 = player.getActiveHand();
		player.switchHand();
		Hand hand2 = player.getActiveHand();
		assertFalse(hand1.isActive());
		assertTrue(hand2.isActive());
		
		player.getHands().clear();
		player.switchHand();
		
		Hand hand = new Hand();
		player.getHands().add(hand);
		player.getHands().add(new Hand(true));
		player.switchHand();
		assertTrue(hand.isActive());
	}
	
	@Test
	public void testReset() {
		player.getHands().add(new Hand());
		player.reset();
		assertEquals(1, player.numberOfHands());
	}
	
	@Test
	public void testIsActiveLast() {
		player.getHands().add(new Hand(true));
		player.getHands().add(new Hand(false));
		assertFalse(player.isActiveLast());
		
		player.getHands().clear();
		player.getHands().add(new Hand(false));
		player.getHands().add(new Hand(true));
		assertTrue(player.isActiveLast());
	}
	
	@Test
	public void testSetGetBet() {
		// regular
		player.setBet(100);
		assertEquals(100, player.getBet());
		// > bet
		player.setBet(-1);
		assertEquals(0, player.getBet());
	}

	@Test
	public void testSetGetCash() {
		player.setCash(100);
		assertEquals(100, player.getCash());
	}
	
	@Test
	public void testSplit() {
		player.takeCard(new Card("jack", "diamonds"));
		player.split();
		assertEquals(2, player.numberOfHands());
	}
	
	@Test
	public void getHands() {
		player.getHands();
	}
}
