package de.htwg.blackjack.model;

import java.util.List;

/**
 * IRules
 *
 * @author Makowe Bj&ouml;rn Antonio <bjmakowe@htwg-konstanz.de>
 * @author Daniel Jehle <dajehle@htwg-konstanz.de>
 * @version 20. April 2013
 */
public interface IRules {

    /**
     * @param hand
     * @return value of Hand
     */
    double getOdds(Hand hand);

    /**
     * @return List with Valid Pictures
     */
    List<String> getValidPictures();

    /**
     * @return List with valid Types
     */
    List<String> getValidTypes();

    /**
     * returns CardStack
     *
     * @return cardStack
     */
    CardStack getCardStack();

    /**
     * returns your handvalue
     *
     * @param hand
     * @return value of your Hand
     */
    int getHandValue(Hand hand);

    /**
     * if ou can mak a hit
     *
     * @param hand
     * @return true or false
     */
    boolean canHit(Hand hand);

    /**
     * if you can split
     *
     * @param player
     * @return true or false
     */
    boolean canSplit(Player player);

    /**
     * if you can double down
     *
     * @param player
     * @return true or false
     */
    boolean canDoubleDown(Player player);

    /**
     * returns which hand wins
     *
     * @param hand1
     * @param hand2
     * @return true if Player wins
     */
    boolean wins(Hand hand1, Hand hand2);

    /**
     * returns true if dealer should hit, returns false otherwise
     *
     * @param dealer
     * @param player
     * @return returns true if dealer should hit, returns false otherwise
     */
    boolean dealerHits(Dealer dealer, Player player);

    /**
     * if it is a valid Bet
     *
     * @param bet
     * @return
     */
    boolean isValidBet(int bet);
}
