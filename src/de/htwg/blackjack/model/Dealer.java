package de.htwg.blackjack.model;

/**
 * Dealer
 *
 * @author Makowe Bj&ouml;rn Antonio <bjmakowe@htwg-konstanz.de>
 * @author Daniel Jehle <dajehle@htwg-konstanz.de>
 * @version 12th May 2013
 */
public class Dealer {

    private Hand hand;

    /**
     * New Dealer
     */
    public Dealer() {
        this.hand = new Hand(true);
    }

    /**
     * takes and adds a card
     *
     * @param card
     */
    public void takeCard(Card card) {
        this.hand.addCard(card);
    }

    /**
     * returns active Hand.
     *
     * @return active Hand
     */
    public Hand getActiveHand() {
        return this.hand;
    }

    /**
     * sets a new Hand
     */
    public void reset() {
        this.hand = new Hand(true);
    }
}
