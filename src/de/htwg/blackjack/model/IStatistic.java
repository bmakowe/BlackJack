package de.htwg.blackjack.model;

/**
 * IStatistic
 *
 * @author Makowe Bj&ouml;rn Antonio <bjmakowe@htwg-konstanz.de>
 * @author Daniel Jehle <dajehle@htwg-konstanz.de>
 * @version 04. April 2013
 */
public interface IStatistic {

    /**
     * resets game
     */
    void reset();

    /**
     * sets won
     */
    void won();

    /**
     * get number of wons
     *
     * @return wons
     */
    int getWins();

    /**
     * adds a lost game
     */
    void lost();

    /**
     * get number of lost games
     *
     * @return lost games
     */
    int getLooses();

    /**
     * @param cash
     */
    void wonCash(int cash);

    /**
     * get won cash
     *
     * @return won cash
     */
    int getWonCash();

    /**
     * sets lost cash
     *
     * @param cash
     */
    void lostCash(int cash);

    /**
     * gets lost cash
     *
     * @return lost cash
     */
    int getLostCash();

    /**
     * gets start cash
     *
     * @return
     */
    int getStartCash();

    /**
     * set start cash
     *
     * @param cash
     */
    void setStartCash(int cash);

    /**
     * gets end cash
     *
     * @return
     */
    int getEndCash();

    /**
     * sets end cash
     *
     * @param cash
     */
    void setEndCash(int cash);

    /**
     * gets statistic
     *
     * @param format
     * @return
     */
    String getStatistic(String format);
}
