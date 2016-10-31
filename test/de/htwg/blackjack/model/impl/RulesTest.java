package de.htwg.blackjack.model.impl;

import static org.junit.Assert.*;

import java.util.List;

import de.htwg.blackjack.model.*;

import org.junit.Before;
import org.junit.Test;

public class RulesTest {
	private Rules rules;
	
	@Before
	public void setUp() {
		rules = new Rules();
	}

	@Test
	public void testGetOdds() {
		Hand hand = new Hand();
		hand.addCard(new Card("jack", "diamonds"));
		hand.addCard(new Card("ace", "diamonds"));
		
		Hand hand2 = new Hand();
		hand2.addCard(new Card("jack", "diamonds"));
		hand2.addCard(new Card("9", "diamonds"));
		hand2.addCard(new Card("2", "diamonds"));

		Hand hand3 = new Hand();
		hand3.addCard(new Card("jack", "diamonds"));
		hand3.addCard(new Card("9", "diamonds"));

		assertTrue(1.5 == rules.getOdds(hand));
		assertTrue(0.5 == rules.getOdds(new Hand()));
		assertTrue(0.5 == rules.getOdds(hand2));
		assertTrue(0.5 == rules.getOdds(hand3));
	}
	
	@Test
	public void testGetValidPictures() {
		List<String> valid = rules.getValidPictures();
		assertTrue(valid.contains("2"));
		assertTrue(valid.contains("3"));
		assertTrue(valid.contains("4"));
		assertTrue(valid.contains("5"));
		assertTrue(valid.contains("6"));
		assertTrue(valid.contains("7"));
		assertTrue(valid.contains("8"));
		assertTrue(valid.contains("9"));
		assertTrue(valid.contains("10"));
		assertTrue(valid.contains("jack"));
		assertTrue(valid.contains("queen"));
		assertTrue(valid.contains("king"));
	}
	
	@Test
	public void testGetValidTypes() {
		List<String> valid = rules.getValidTypes();
		assertTrue(valid.contains("clubs"));
		assertTrue(valid.contains("spades"));
		assertTrue(valid.contains("hearts"));
		assertTrue(valid.contains("diamonds"));
	}
	
	@Test
	public void testGetICardStack() {
		CardStack cards = rules.getCardStack();
		assertEquals(52, cards.getSize());
	}
	
	@Test
	public void testGetPictureValue() {
		assertEquals(10, rules.getPictureValue("king"));
		assertEquals(9, rules.getPictureValue("9"));
		assertEquals(0, rules.getPictureValue("false"));
	}
	
	@Test
	public void testGetHandValue() {
		Hand hand = new Hand();
		hand.addCard(new Card("jack", "diamonds"));
		hand.addCard(new Card("ace", "diamonds"));
		
		Hand hand2 = new Hand();
		hand2.addCard(new Card("jack", "diamonds"));
		hand2.addCard(new Card("10", "diamonds"));
		hand2.addCard(new Card("2", "diamonds"));
		hand2.addCard(new Card("ace", "diamonds"));
		
		assertEquals(21, rules.getHandValue(hand));
		assertEquals(23, rules.getHandValue(hand2));
		assertEquals(0, rules.getHandValue(null));
	}
	
	@Test
	public void testCanHit() {
		Hand hand = new Hand();
		hand.addCard(new Card("jack", "diamonds"));
		hand.addCard(new Card("2", "diamonds"));
		
		Hand hand2 = new Hand();
		hand2.addCard(new Card("jack", "diamonds"));
		hand2.addCard(new Card("10", "diamonds"));
		hand2.addCard(new Card("2", "diamonds"));
		hand2.addCard(new Card("ace", "diamonds"));
		
		assertTrue(rules.canHit(hand));
		assertFalse(rules.canHit(hand2));
		assertFalse(rules.canHit(null));
	}
	
	@Test
	public void testCanSplit() {
		Player player = new Player();
		player.getHands().add(new Hand());
		
		Player player2 = new Player();
		player2.takeCard(new Card("jack", "diamonds"));
		
		assertFalse(rules.canSplit(player2));
		assertFalse(rules.canSplit(player));
		assertFalse(rules.canSplit(null));
		
		Player player3 = new Player();
		player3.takeCard(new Card("jack", "diamonds"));
		player3.takeCard(new Card("jack", "spades"));
		
		Player player4 = new Player();
		player4.takeCard(new Card("jack", "diamonds"));
		player4.takeCard(new Card("9", "spades"));
		
		assertTrue(rules.canSplit(player3));
		assertFalse(rules.canSplit(player4));
	}
	
	@Test
	public void testCanDoubleDown() {
		Player player = new Player();
		player.getHands().add(new Hand());
		
		Player player2 = new Player();
		Hand active = player2.getActiveHand();
		active.addCard(new Card("jack", "diamonds"));
		
		assertFalse(rules.canDoubleDown(player2));
		assertFalse(rules.canDoubleDown(player));
		assertFalse(rules.canDoubleDown(null));
		
		Player player3 = new Player();
		player3.takeCard(new Card("4", "diamonds"));
		player3.takeCard(new Card("5", "diamonds"));
		
		Player player4 = new Player();
		player4.takeCard(new Card("5", "spades"));
		player4.takeCard(new Card("10", "spades"));
		
		Player player5 = new Player();
		player5.takeCard(new Card("5", "diamonds"));
		player5.takeCard(new Card("2", "diamonds"));
		
		Player player6 = new Player();
		player6.takeCard(new Card("4", "diamonds"));
		player6.takeCard(new Card("5", "diamonds"));
		player6.setBet(100);
		
		assertFalse(rules.canDoubleDown(player6));
		assertFalse(rules.canDoubleDown(player5));
		assertTrue(rules.canDoubleDown(player3));
		assertFalse(rules.canDoubleDown(player4));
	}
	
	@Test
	public void testWins() {
		assertFalse(rules.wins(null, null));
		assertFalse(rules.wins(new Hand(), null));
		
		Hand hand = new Hand();
		hand.addCard(new Card("5", "spades"));
		Hand hand2 = new Hand();
		hand2.addCard(new Card("4", "diamonds"));
		
		assertTrue(rules.wins(hand, hand2));
		assertFalse(rules.wins(hand, hand));
		
		Hand hand3 = new Hand();
		hand3.addCard(new Card("jack", "diamonds"));
		Hand hand4 = new Hand();
		hand4.addCard(new Card("jack", "spades"));
		hand4.addCard(new Card("10", "hearts"));
		hand4.addCard(new Card("10", "clubs"));
		
		assertFalse(rules.wins(hand4, hand3));
		assertTrue(rules.wins(hand3, hand4));
	}
	
	@Test
	public void testDealerHits() {
		assertFalse(rules.dealerHits(null, new Player()));
		assertFalse(rules.dealerHits(new Dealer(), null));
		
		Player player = new Player();
		Dealer dealer = new Dealer();
		
		dealer.takeCard(new Card("jack", "diamonds"));
		player.takeCard(new Card("ace", "spades"));
		player.split();
		player.takeCard(new Card("ace", "spades"));
		
		assertTrue(rules.dealerHits(dealer, player));
		
		dealer.takeCard(new Card("2", "hearts"));
		
		assertFalse(rules.dealerHits(dealer, player));
		
		dealer.takeCard(new Card("king", "clubs"));
		
		assertFalse(rules.dealerHits(dealer, player));
	}
	
	@Test
	public void testIsValidBet() {
		assertFalse(rules.isValidBet(1));
		assertFalse(rules.isValidBet(-10));
		assertTrue(rules.isValidBet(20));
	}
}
