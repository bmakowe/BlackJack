package de.htwg.blackjack.controller;

import de.htwg.util.observer.IObservable;

/**
 * IBlackJackController
 *
 * @author Makowe Bj&ouml;rn Antonio <bjmakowe@htwg-konstanz.de>
 * @author Daniel Jehle <dajehle@htwg-konstanz.de>
 * @version 24th June 2013
 */
public interface IBlackJackController extends IObservable {

    /**
     * starts a new game with cash
     *
     * @param cash
     */
    void start(int cash);

    /**
     * stops a running game
     */
    void stop();

    /**
     * makes a stand
     */
    void stand();

    /**
     * makes a hit
     */
    void hit();

    /**
     * makes a doubleDown
     */
    void doubleDown();

    /**
     * splits a hand
     */
    void split();

    /**
     * sets a bet
     *
     * @param bet
     */
    void bet(int bet);

    /**
     * returns the PlayerHand
     *
     * @return playerHand
     */
    String getPlayerHands();

    /**
     * returns the DealerHand
     *
     * @return dealerHand
     */
    String getDealerHand();

    /**
     * gets the Bet
     *
     * @return the bet
     */
    int getBet();

    /**
     * returns the cash
     *
     * @return cash
     */
    int getCash();

    /**
     * sets the players Hand
     *
     * @param cards
     */
    void setHand(String cards);

    /**
     * returns the Status
     *
     * @return Status
     */
    String getStatus();

    /**
     * returns Statistics
     *
     * @return Statistic
     */
    String getStatistic();

    /**
     * returns valid Options
     *
     * @return valid options
     */
    String getValidOptions();
}
