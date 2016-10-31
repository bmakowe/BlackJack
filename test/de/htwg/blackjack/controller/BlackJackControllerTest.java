package de.htwg.blackjack.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.blackjack.controller.impl.BlackJackController;
import de.htwg.blackjack.model.Card;
import de.htwg.blackjack.model.CardStack;
import de.htwg.blackjack.model.Hand;
import de.htwg.blackjack.model.Rules;
import de.htwg.blackjack.model.Statistic;

public class BlackJackControllerTest {
	private BlackJackController controller = new BlackJackController(new Rules(), new Statistic());;
	
	@Test
	public void testStart() {
		controller.start(300);
		assertEquals("New game has started", controller.getStatus());
	}
	
	@Test
	public void testStop() {
		controller.stop();
		assertEquals("Game over", controller.getStatus());
	}
	
	@Test
	public void testDrawTwoCards() {
		Hand hand = new Hand();
		controller.start(10);
		controller.drawTwoCards(hand);
		assertEquals(2, hand.getSize());
	}
	
	@Test
	public void testStand() {
		controller.start(100);
		controller.bet(10);
		controller.stand();
		assertEquals("round ended", controller.getStatus());
		controller.start(100);
		controller.getPlayer().getHands().add(new Hand());
		controller.stand();
		assertEquals("next hand is active", controller.getStatus());
	}
	
	@Test
	public void testHit() {
		controller.start(100);
		controller.hit();
		assertEquals("player took a card", controller.getStatus());
		controller.getPlayer().takeCard(new Card("jack", "diamonds"));
		controller.getPlayer().takeCard(new Card("jack", "diamonds"));
		controller.getPlayer().takeCard(new Card("jack", "diamonds"));
		controller.hit();
		assertEquals("player unable to hit", controller.getStatus());
	}
	
	@Test
	public void testDoubleDown() {
		controller.start(100);
		controller.getPlayer().takeCard(new Card("5", "clubs"));
		controller.getPlayer().takeCard(new Card("4", "spades"));
		controller.doubleDown();
		assertEquals("player doubled bet", controller.getStatus());
		controller.bet(100);
		controller.doubleDown();
		assertEquals("player was unable to double bet", controller.getStatus());
	}
	
	@Test
	public void testSplit() {
		controller.start(100);
		controller.split();
		assertEquals("player was unable to split", controller.getStatus());
		controller.getPlayer().takeCard(new Card("jack", "diamonds"));
		controller.getPlayer().takeCard(new Card("jack", "spades"));
		controller.split();
		assertEquals("player splited", controller.getStatus());
	}
	
	@Test
	public void testBet() {
		controller.start(100);
		controller.bet(120);
		assertEquals("player unable to bet", controller.getStatus());
		controller.bet(10);
		assertEquals("player bet 10", controller.getStatus());
		controller.bet(-10);
		assertEquals("player unable to bet", controller.getStatus());
	}
	
	@Test
	public void testGetSatistic() {
		assertNotNull(controller.getStatistic());
	}
	
	@Test
	public void testNewCash() {
		controller.start(100);
		controller.getPlayer().setBet(50);
		controller.getPlayer().takeCard(new Card("jack", "diamonds"));
		controller.getPlayer().setCash(50);
		assertEquals(125, controller.getNewCash());
	}
	
	@Test
	public void testStackCheck() {
		controller.setICardStack(new CardStack());
		controller.stackCheck();
		assertEquals("new cardstack", controller.getStatus());
	}
	
	@Test
	public void testGetValidOptions() {
		controller.start(100);
		assertEquals("bet", controller.getValidOptions());
		controller.bet(10);
		controller.getPlayer().getActiveHand().dispose();
		controller.getPlayer().getActiveHand().addCard(new Card("5", "diamonds"));
		controller.getPlayer().getActiveHand().addCard(new Card("4", "diamonds"));
		assertEquals("hit,double,stand", controller.getValidOptions());	
		controller.doubleDown();
		assertEquals("hit,stand", controller.getValidOptions());
		controller.hit();
		assertEquals("hit,stand", controller.getValidOptions());
		controller.start(100);
		controller.bet(10);
		controller.getPlayer().getActiveHand().dispose();
		controller.getPlayer().getActiveHand().addCard(new Card("5", "diamonds"));
		controller.getPlayer().getActiveHand().addCard(new Card("5", "diamonds"));
		assertEquals("hit,double,split,stand", controller.getValidOptions());
		controller.split();
		assertEquals("hit,stand", controller.getValidOptions());
		controller.stand();
		assertEquals("hit,stand", controller.getValidOptions());
		controller.stand();
		assertEquals("bet", controller.getValidOptions());
		controller.start(100);
		controller.bet(10);
		controller.hit();
		controller.getPlayer().getActiveHand().dispose();
		controller.getPlayer().getActiveHand().addCard(new Card("jack", "diamonds"));
		controller.getPlayer().getActiveHand().addCard(new Card("jack", "diamonds"));
		controller.getPlayer().getActiveHand().addCard(new Card("jack", "diamonds"));
		assertEquals("stand", controller.getValidOptions());
		
	}
	
	@Test
	public void testSetHand() {
		controller.setHand("jack-diamonds");
		assertEquals("[jack-diamonds]", controller.getPlayer().getActiveHand().toString());
	}
}
