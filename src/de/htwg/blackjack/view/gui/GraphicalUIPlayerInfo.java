package de.htwg.blackjack.view.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * GraphicalUIPlyerInfo
 *
 * @author Makowe Bj&ouml;rn Antonio <bjmakowe@htwg-konstanz.de>
 * @author Daniel Jehle <dajehle@htwg-konstanz.de>
 * @version 07th June 2013
 */
@SuppressWarnings("serial")
public class GraphicalUIPlayerInfo extends JPanel {

    private JLabel labelCash;
    private JLabel labelBet;

    /**
     * GraphicalUIPlayerInfo()
     */
    public GraphicalUIPlayerInfo() {
        this.setBorder(BorderFactory.createTitledBorder(new LineBorder(GraphicalUI.BACKGROUNDCOLOR), "Info"));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.labelCash = new JLabel("cash: $0");
        this.labelBet = new JLabel("bet  : $0");
        this.add(labelCash);
        this.add(labelBet);
    }

    /**
     * sets Text in Info
     *
     * @param cash
     * @param bet
     */
    public void setInfo(int cash, int bet) {
        this.labelCash.setText("cash: $" + cash);
        this.labelBet.setText("bet  : $" + bet);
    }
}
