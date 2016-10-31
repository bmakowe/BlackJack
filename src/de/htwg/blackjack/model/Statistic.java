package de.htwg.blackjack.model;

/**
 * Statistic
 *
 * @author Makowe Bj&ouml;rn Antonio <bjmakowe@htwg-konstanz.de>
 * @author Daniel Jehle <dajehle@htwg-konstanz.de>
 * @version 04. April 2013
 */
public class Statistic implements IStatistic {

    private int wins = 0;
    private int looses = 0;
    private int wonCash = 0;
    private int lostCash = 0;
    private int startCash = 0;
    private int endCash = 0;

    /**
     * resets all statistics
     */
    public void reset() {
        wins = 0;
        looses = 0;
        wonCash = 0;
        lostCash = 0;
        startCash = 0;
        endCash = 0;
    }

    /**
     * add one to win
     */
    public void won() {
        wins++;
    }

    /**
     * add one to lost
     */
    public void lost() {
        looses++;
    }

    /**
     * adds won Cash
     *
     * @param cash
     */
    public void wonCash(int cash) {
        wonCash += cash;
    }

    /**
     * adds lost Cash
     *
     * @param cash
     */
    public void lostCash(int cash) {
        lostCash += cash;
    }

    /**
     * retuns Statistic
     *
     * @param format
     * @return Statistic
     */
    public String getStatistic(String format) {
        if (format.equals("std")) {
            return getStandardStatistic();
        } else {
            return "Statistic format doesn't exist";
        }
    }

    private String getStandardStatistic() {
        int playedHands = wins + looses;
        double difference = endCash - startCash;
        double wonCashPerHand = (double) wonCash / playedHands;
        double lostCashPerHand = (double) lostCash / playedHands;
        double overallCashPerHand = difference / playedHands;

        String separator = System.getProperty("line.separator");
        StringBuilder lines = new StringBuilder();

        lines.append(separator);
        lines.append("Played hands         : ").append(playedHands).append(separator);
        lines.append("Winning hands        : ").append(wins).append(separator);
        lines.append("Loosing hands        : ").append(looses).append(separator);
        lines.append("Cash at beginning    : $").append(startCash).append(separator);
        lines.append("Cash at end          : $").append(endCash).append(separator);
        lines.append("Win/lose             : ").append((double) wins / (double) looses).append(separator);
        lines.append("Won cash             : $").append(wonCash).append(separator);
        lines.append("Lost cash            : $").append(lostCash).append(separator);
        lines.append("Won cash per hand    : $").append(wonCashPerHand).append(separator);
        lines.append("Lost cash per hand   : $").append(lostCashPerHand).append(separator);
        lines.append("Overall cash per hand: $").append(overallCashPerHand).append(separator);

        return lines.toString();
    }

    /**
     * returns number of wons games
     *
     * @return wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * returns number of lost games
     *
     * @return
     */
    public int getLooses() {
        return looses;
    }

    /**
     * returns won Cash
     *
     * @return wonCash
     */
    public int getWonCash() {
        return wonCash;
    }

    /**
     * returns lost Cash
     *
     * @return lostCash
     */
    public int getLostCash() {
        return lostCash;
    }

    /**
     * returns start cash
     *
     * @return startCash
     */
    public int getStartCash() {
        return startCash;
    }

    /**
     * sets Start Cash
     *
     * @param cash
     */
    public void setStartCash(int cash) {
        startCash = cash;
    }

    /**
     * rturns end Cash
     *
     * @return endCash
     */
    public int getEndCash() {
        return endCash;
    }

    /**
     * sets the End Cash
     *
     * @param cash
     */
    public void setEndCash(int cash) {
        endCash = cash;
    }
}