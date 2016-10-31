package de.htwg.blackjack.model;

import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

/**
 * CardStack
 *
 * @author Makowe Bj&ouml;rn Antonio <bjmakowe@htwg-konstanz.de>
 * @author Daniel Jehle <dajehle@htwg-konstanz.de>
 * @version 04. April 2013
 */
public class CardStack implements Iterable<Card> {

    private Stack<Card> cards;

    /**
     * New CardStack
     */
    public CardStack() {
        this.cards = new Stack<Card>();
    }

    /**
     * adds a Card
     *
     * @param c
     */
    public void addCard(Card c) {
        cards.push(c);
    }

    /**
     * pop a card
     *
     * @return the next card
     */
    public Card drawCard() {
        return cards.pop();
    }

    /**
     * returns size of Stack
     *
     * @return size or number of Cards in Game
     */
    public int getSize() {
        return cards.size();
    }

    /**
     * shuffles the Cards
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * @return returns an iterator for cards
     */
    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }
}
