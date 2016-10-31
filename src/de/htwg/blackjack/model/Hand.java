package de.htwg.blackjack.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Hand
 *
 * @author Makowe Bj&ouml;rn Antonio <bjmakowe@htwg-konstanz.de>
 * @author Daniel Jehle <dajehle@htwg-konstanz.de>
 * @version 04. April 2013
 */
public class Hand implements Iterable<Card> {

    private static final int ZERO = 0;
    private List<Card> cards;
    private boolean active = false;

    /**
     * New Hand
     */
    public Hand() {
        this.cards = new ArrayList<Card>();
    }

    /**
     * New Hand with active status
     *
     * @param active
     */
    public Hand(boolean active) {
        this.cards = new ArrayList<Card>();
        this.active = active;
    }

    /**
     * adds Card to your Hand
     *
     * @param card
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * clears your Hand
     */
    public void dispose() {
        cards.clear();
    }

    /**
     * removes a Card from the list
     *
     * @return returns the removed Card
     */
    public Card drawCard() {
        return cards.remove(ZERO);
    }

    /**
     * returns how many Cards are on your Hand
     *
     * @return the size of your Hand
     */
    public int getSize() {
        return cards.size();
    }

    @Override
    public String toString() {
        return cards.toString();
    }

    /**
     * return if this Hand Object is active
     *
     * @return true if it's active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * set your Hand Active Status
     *
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * returns a list of the cards in the hand
     *
     * @return cards in the hand
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * @return returns an iterator for cards
     */
    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }
}