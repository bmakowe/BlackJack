package de.htwg.blackjack.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Player
 *
 * @author Makowe Bj&ouml;rn Antonio <bjmakowe@htwg-konstanz.de>
 * @author Daniel Jehle <dajehle@htwg-konstanz.de>
 * @version 04. April 2013
 */
public class Player {

    private List<Hand> hands;
    private int bet;
    private int cash;

    /**
     * New Player
     */
    public Player() {
        this.hands = new ArrayList<Hand>();
        this.hands.add(new Hand(true));
    }

    /**
     * adds a card to the active hand.
     *
     * @param card card to add
     */
    public void takeCard(Card card) {
        getActiveHand().addCard(card);
    }

    /**
     * returns active Hand or null if there is no active Hand
     *
     * @return active Hand
     */
    public Hand getActiveHand() {
        for (Hand hand : hands) {
            if (hand.isActive()) {
                return hand;
            }
        }
        return null;
    }

    /**
     * returns the number of hands the player has.
     *
     * @return number of hands
     */
    public int numberOfHands() {
        return hands.size();
    }

    /**
     * isActiveLast() returns true if the active Hand is the last in the list
     * returns false otherwise
     *
     * @return
     */
    public boolean isActiveLast() {
        return (hands.indexOf(getActiveHand()) == hands.size() - 1);
    }

    /**
     * sets the current active Hand inactive, then sets the next Hand in the
     * list to active. if the current Hand is the last in the list then
     */
    public void switchHand() {
        int index;
        for (Hand hand : hands) {
            if (hand.isActive()) {
                hand.setActive(false);
                index = hands.indexOf(hand);

                if (index == numberOfHands() - 1) {
                    Hand next = hands.get(0);
                    next.setActive(true);
                    break;
                } else {
                    Hand next = hands.get(index + 1);
                    next.setActive(true);
                    break;
                }
            }
        }
    }

    /**
     * resets the player
     */
    public void reset() {
        hands.clear();
        hands.add(new Hand(true));
        bet = 0;
    }

    /**
     * returns your bet
     *
     * @return value of bet
     */
    public int getBet() {
        return bet;
    }

    /**
     * set your bet
     *
     * @param bet
     */
    public void setBet(int bet) {
        if (bet >= 0) {
            this.bet = bet;
        } else {
            this.bet = 0;
        }
    }

    /**
     * returns the value of your cash
     *
     * @return value of your cash
     */
    public int getCash() {
        return cash;
    }

    /**
     * set your cash
     *
     * @param cash
     */
    public void setCash(int cash) {
        this.cash = cash;
    }

    /**
     * splits the active hand of the player.
     */
    public void split() {
        Hand activeHand = getActiveHand();
        Card card = activeHand.drawCard();
        Hand splitHand = new Hand(false);
        splitHand.addCard(card);
        hands.add(splitHand);
    }

    /**
     * return hands
     *
     * @return hand
     */
    public List<Hand> getHands() {
        return hands;
    }
}
