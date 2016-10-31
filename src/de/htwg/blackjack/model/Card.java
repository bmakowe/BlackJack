package de.htwg.blackjack.model;

/**
 * Card
 *
 * @author Makowe Bj&ouml;rn Antonio <bjmakowe@htwg-konstanz.de>
 * @author Daniel Jehle <dajehle@htwg-konstanz.de>
 * @version 04. April 2013
 */
public class Card {

    private final String type;
    private final String picture;

    /**
     * New Card
     *
     * @param t
     * @param p
     */
    public Card(String p, String t) {
        this.picture = p;
        this.type = t;
    }

    /**
     * returns the Picture of the Card
     *
     * @return the Picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * returns the Card Type
     *
     * @return the Card Type
     */
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return picture + "-" + type;
    }
}
