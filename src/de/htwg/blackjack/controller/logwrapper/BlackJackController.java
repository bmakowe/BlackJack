package de.htwg.blackjack.controller.logwrapper;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.model.IRules;
import de.htwg.blackjack.model.IStatistic;
import de.htwg.util.observer.Observable;
import org.apache.log4j.Logger;

/**
 * BlackJackController
 *
 * @author Makowe Bj&ouml;rn Antonio <bjmakowe@htwg-konstanz.de>
 * @author Daniel Jehle <dajehle@htwg-konstanz.de>
 * @version 12th May 2013
 */
@Singleton
public class BlackJackController extends Observable implements IBlackJackController {

    private Logger logger = Logger.getLogger("de.htwg.blackjack.controller.wrappedimpl");
    private IBlackJackController realController;
    private long startTime;

    /**
     * New Dealer
     *
     * @param rules     Rules for the game
     * @param statistic Statistic for the game
     */
    @Inject
    public BlackJackController(IRules rules, IStatistic statistic) {
        realController = new de.htwg.blackjack.controller.impl.BlackJackController(rules, statistic);
        startTime = System.nanoTime();
    }

    private static String getMethodName(final int depth) {
        final StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        return stack[2 + depth].getMethodName();
    }

    private void pre() {
        logger.debug("Controller method " + getMethodName(1) + " was called.");
        startTime = System.nanoTime();
    }

    private void post() {
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        logger.debug("Controller method " + getMethodName(1) + " was finished in " + duration + " nanoSeconds.");
    }

    /**
     * start a new Game
     *
     * @param cash
     */
    public void start(int cash) {
        pre();
        realController.start(cash);
        post();
    }

    /**
     * stops the game
     */
    public void stop() {
        pre();
        realController.stop();
        post();
    }

    /**
     * ends the round and/or switches the active hand of the player
     */
    public void stand() {
        pre();
        realController.stand();
        post();
    }

    /**
     * adds a card to the active hand of the player if the player is able to hit
     */
    public void hit() {
        pre();
        realController.hit();
        post();
    }

    /**
     * doubles the bet if the player is able to
     */
    public void doubleDown() {
        pre();
        realController.doubleDown();
        post();
    }

    /**
     * splits the active hand of the player in two if he is able to split
     */
    public void split() {
        pre();
        realController.split();
        post();
    }
    // set the bet -> if player has the cash...

    /**
     * accept Bet initialize a new Round and sets the bet
     *
     * @param bet
     */
    public void bet(int bet) {
        pre();
        realController.bet(bet);
        post();
    }

    /**
     * returns Status
     *
     * @return status
     */
    public String getStatus() {
        return realController.getStatus();
    }

    /**
     * returns Statistic
     *
     * @return Statistic
     */
    public String getStatistic() {
        return realController.getStatistic();
    }

    /**
     * returns valid Options
     *
     * @return valid Options
     */
    public String getValidOptions() {
        return realController.getValidOptions();
    }

    /**
     * returns Player Hand
     *
     * @return playerHand
     */
    @Override
    public String getPlayerHands() {
        return realController.getPlayerHands();
    }

    /**
     * returns Dealer Hand
     *
     * @return dealerHand
     */
    @Override
    public String getDealerHand() {
        return realController.getDealerHand();
    }

    /**
     * returns Bet
     *
     * @return bet
     */
    @Override
    public int getBet() {
        return realController.getBet();
    }

    /**
     * returns cash
     *
     * @return cash
     */
    @Override
    public int getCash() {
        return realController.getCash();
    }

    /**
     * gets cards in format jack-diamonds, queen-clubs ...
     */
    public void setHand(String cards) {
        pre();
        realController.setHand(cards);
        post();
    }
}
