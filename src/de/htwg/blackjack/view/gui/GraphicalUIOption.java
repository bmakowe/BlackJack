package de.htwg.blackjack.view.gui;

import de.htwg.blackjack.controller.IBlackJackController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GraphicalUIOption
 *
 * @author Makowe Bj&ouml;rn Antonio <bjmakowe@htwg-konstanz.de>
 * @author Daniel Jehle <dajehle@htwg-konstanz.de>
 * @version 07th June 2013
 */
@SuppressWarnings("serial")
public class GraphicalUIOption extends JPanel implements ActionListener {
    /**
     * Chip 10 $
     */
    private static final int BET1 = 10;
    /**
     * Chip 100 $
     */
    private static final int BET2 = 100;
    /**
     * Chip 250 $
     */
    private static final int BET3 = 250;
    private IBlackJackController controller;
    private JPanel mainPanel;
    private JButton buttonChip10 = new JButton("$" + GraphicalUIOption.BET1);
    private JButton buttonChip100 = new JButton("$" + GraphicalUIOption.BET2);
    private JButton buttonChip250 = new JButton("$" + GraphicalUIOption.BET3);
    private JButton buttonHit = new JButton("Hit");
    private JButton buttonStand = new JButton("Stand");
    private JButton buttonSplit = new JButton("Split");
    private JButton buttonDouble = new JButton("Double");
    private JButton buttonBet = new JButton("Bet");
    private JButton buttonReset = new JButton("Reset");
    private JLabel labelBet = new JLabel("0");
    private int bet = 0;

    /**
     * GraphicalUIOption
     *
     * @param controller
     */
    public GraphicalUIOption(IBlackJackController controller) {
        this.controller = controller;

        // Other Properties
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 0));
        mainPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(GraphicalUI.BORDERCOLOR), "Optionen"));
        mainPanel.setBackground(GraphicalUI.BACKGROUNDCOLOR);

        // label Bet
        mainPanel.add(labelBet);

        // Buttons
        mainPanel.add(buttonChip10);
        mainPanel.add(buttonChip100);
        mainPanel.add(buttonChip250);
        mainPanel.add(buttonReset);
        mainPanel.add(buttonBet);
        mainPanel.add(buttonHit);
        mainPanel.add(buttonStand);
        mainPanel.add(buttonSplit);
        mainPanel.add(buttonDouble);

        // ActionListener
        buttonChip10.addActionListener(this);
        buttonChip100.addActionListener(this);
        buttonChip250.addActionListener(this);
        buttonBet.addActionListener(this);
        buttonReset.addActionListener(this);
        buttonHit.addActionListener(this);
        buttonStand.addActionListener(this);
        buttonSplit.addActionListener(this);
        buttonDouble.addActionListener(this);

        this.add(mainPanel);
    }

    /**
     * Action Performed
     */
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source.equals(buttonChip10)) {
            setBet(GraphicalUIOption.BET1);

        } else if (source.equals(buttonChip100)) {
            setBet(GraphicalUIOption.BET2);

        } else if (source.equals(buttonChip250)) {
            setBet(GraphicalUIOption.BET3);

        } else if (source.equals(buttonHit)) {
            controller.hit();

        } else if (source.equals(buttonStand)) {
            controller.stand();

        } else if (source.equals(buttonSplit)) {
            controller.split();

        } else if (source.equals(buttonDouble)) {
            controller.doubleDown();

        } else if (source.equals(buttonBet)) {
            if (controller.getCash() >= bet) {
                controller.bet(bet);
            } else {
                JFrame frame = null;
                JOptionPane.showMessageDialog(frame,
                        "Nicht genug Credits vorhanden.",
                        "Credit Warnung",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (source.equals(buttonReset)) {
            this.bet = 0;
            labelBet.setText("$" + bet);
        }
    }

    /**
     * sets Bet
     */
    private void setBet(int value) {
        this.bet += value;

        labelBet.setText("$" + bet);
    }

    /**
     * sets valid Options active
     *
     * @param validOptions
     */
    public void setOptions(String validOptions) {
        for (Component component : mainPanel.getComponents()) {
            component.setEnabled(false);
        }
        this.labelBet.setEnabled(true);

        if (validOptions.contains("bet")) {
            this.buttonChip10.setEnabled(true);
            this.buttonChip100.setEnabled(true);
            this.buttonChip250.setEnabled(true);
            this.buttonReset.setEnabled(true);
            this.buttonBet.setEnabled(true);
        }
        if (validOptions.contains("hit")) {
            this.buttonHit.setEnabled(true);
        }
        if (validOptions.contains("split")) {
            this.buttonSplit.setEnabled(true);
        }
        if (validOptions.contains("double")) {
            this.buttonDouble.setEnabled(true);
        }
        if (validOptions.contains("stand")) {
            this.buttonStand.setEnabled(true);
        }
    }
}
