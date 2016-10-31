package de.htwg.blackjack.view.gui;

import de.htwg.blackjack.controller.IBlackJackController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GraphicalUIMenuNewGame
 *
 * @author Makowe Bj&ouml;rn Antonio <bjmakowe@htwg-konstanz.de>
 * @author Daniel Jehle <dajehle@htwg-konstanz.de>
 * @version 24th June 2013
 */
@SuppressWarnings("serial")
public class GraphicalUIMenuNewGame extends JFrame implements ActionListener {

    /**
     * Start Cash if your not tipping it
     */
    private static final int STARTCASH = 500;
    private IBlackJackController controller;
    private JTextField credit = new JTextField("500");
    private JButton buttonSave = new JButton("Speichern");
    private JButton buttonClose = new JButton("Schliessen");

    /**
     * GraphicalUIMenuNewGame
     *
     * @param controller
     */
    public GraphicalUIMenuNewGame(IBlackJackController controller) {
        this.controller = controller;
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 2));

        // Label + Textfield
        mainPanel.add(new JLabel("Kredit:"));
        mainPanel.add(credit);

        // Button Save
        mainPanel.add(buttonSave);
        buttonSave.addActionListener(this);

        // Button Close
        mainPanel.add(buttonClose);
        buttonClose.addActionListener(this);

        // Other Properties
        this.add(mainPanel);
        this.setTitle("Neues Spiel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Action Performed
     */
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source.equals(buttonSave)) {
            // Start Cash initialize
            controller.start(getCredit());
            this.dispose();

        } else if (source.equals(buttonClose)) {
            // Exit
            controller.start(STARTCASH);
            this.dispose();

        }
    }

    /**
     * returns credit
     *
     * @return credit
     */
    public int getCredit() {
        return Integer.parseInt(credit.getText());
    }
}
