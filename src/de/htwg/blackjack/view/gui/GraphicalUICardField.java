package de.htwg.blackjack.view.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * GraphicalUICardField
 *
 * @author Makowe Bj&ouml;rn Antonio <bjmakowe@htwg-konstanz.de>
 * @author Daniel Jehle <dajehle@htwg-konstanz.de>
 * @version 22th June 2013
 */
@SuppressWarnings("serial")
public class GraphicalUICardField extends JPanel {

    private static String carddir = "src/de/htwg/blackjack/view/gui/images/tangram/";
    private JPanel dealerCards;
    private JPanel playerCards;

    /**
     * GraphicalUICardField()
     */
    public GraphicalUICardField() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.dealerCards = new JPanel();
        this.playerCards = new JPanel();

        dealerCards.setBorder(BorderFactory.createTitledBorder(new LineBorder(GraphicalUI.BORDERCOLOR), "Dealer"));
        playerCards.setBorder(BorderFactory.createTitledBorder(new LineBorder(GraphicalUI.BORDERCOLOR), "Player"));

        this.add(dealerCards);
        this.add(playerCards);

        for (Component component : this.getComponents()) {
            component.setBackground(GraphicalUI.BACKGROUNDCOLOR);
        }
    }

    /**
     * @param dealerHand
     * @param playerHands
     */
    public void setCards(String dealerHand, String playerHands) {
        dealerCards.removeAll();
        playerCards.removeAll();

        Pattern pat = Pattern.compile("\\w+-\\w+");
        Matcher mat = pat.matcher(dealerHand);

        while (mat.find()) {
            String pic = mat.group(0);
            pic = pic.replace("-", "_");
            pic += ".png";
            ImageIcon icon = new ImageIcon(carddir + pic);
            JLabel card = new JLabel(icon);
            dealerCards.add(card);
        }
        dealerCards.repaint();
        dealerCards.revalidate();

        if (playerHands.contains("], [")) {
            playerHands = playerHands.replace("], [", ",1-barr,");
        }

        mat = pat.matcher(playerHands);
        while (mat.find()) {
            String pic = mat.group(0);
            pic = pic.replace("-", "_");
            pic += ".png";
            ImageIcon icon = new ImageIcon(carddir + pic);
            JLabel card = new JLabel(icon);
            playerCards.add(card);
        }
        playerCards.repaint();
        playerCards.revalidate();
    }
}
