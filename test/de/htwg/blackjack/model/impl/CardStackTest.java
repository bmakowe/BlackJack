package de.htwg.blackjack.model.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.blackjack.model.Card;
import de.htwg.blackjack.model.CardStack;

public class CardStackTest {
	private CardStack cardstack;
	
	@Before
	public void setUp() {
		cardstack = new CardStack();
	}
	
    @Test
    public void testAddCard() {
    	cardstack.addCard(new Card("jack", "diamonds"));
    	assertEquals(1, cardstack.getSize());
    }
    
    @Test
    public void testDrawCard() {
    	cardstack.addCard(new Card("jack", "diamonds"));
    	Card card = cardstack.drawCard();
    	assertEquals("jack-diamonds", card.toString());
    	assertEquals(0, cardstack.getSize());
    }
    
    @Test
    public void testGetSize() {
    	cardstack.addCard(new Card("jack", "diamonds"));
    	assertEquals(1, cardstack.getSize());
    }
    
    @Test
    public void shuffle(){
    	cardstack.addCard(new Card("jack", "diamonds"));
    	cardstack.addCard(new Card("jack", "hearts"));
    	cardstack.addCard(new Card("jack", "spades"));
    	cardstack.shuffle();
    }
    
    @Test
    public void testIterator() {
    	cardstack.addCard(new Card("jack", "diamonds"));
    	cardstack.addCard(new Card("jack", "hearts"));
    	cardstack.addCard(new Card("jack", "spades"));
    	int i = 0;
    	for (@SuppressWarnings("unused") Card card : cardstack) {
    		++i;
    	}
    	assertEquals(3, i);
    }

}
