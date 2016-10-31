package de.htwg.blackjack.view.gui;

import de.htwg.blackjack.controller.IBlackJackController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * GUIMenu
 *
 * @author Makowe Bj&ouml;rn Antonio <bjmakowe@htwg-konstanz.de>
 * @author Daniel Jehle <dajehle@htwg-konstanz.de>
 * @version 24th June 2013
 */
@SuppressWarnings("serial")
public class GraphicalUIMenu extends JMenuBar implements ActionListener {
    private IBlackJackController controller;
    private JMenuItem itemShowStats;
    private JMenuItem itemNewGame;
    private JMenuItem itemExitGame;

    /**
     * GraphicalUIMenu
     *
     * @param controller
     */
    public GraphicalUIMenu(IBlackJackController controller) {
        this.controller = controller;

        // Menue Bar
        JMenu menuBar = new JMenu("Spiel");
        JMenu stats = new JMenu("Statistik");
        this.add(menuBar);
        this.add(stats);

        // Button New Game
        this.itemNewGame = new JMenuItem("Neues Spiel", new ImageIcon(getClass().getResource("images/newGame.gif")));
        this.itemNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        this.itemNewGame.addActionListener(this);
        menuBar.add(itemNewGame);

        // Separator line
        menuBar.addSeparator();

        // Button Exit
        this.itemExitGame = new JMenuItem("Spiel beenden", new ImageIcon(getClass().getResource("images/exitGame.gif")));
        this.itemExitGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        this.itemExitGame.addActionListener(this);
        menuBar.add(itemExitGame);

        // show Statistics
        this.itemShowStats = new JMenuItem("Statistik anzeigen");
        this.itemShowStats.addActionListener(this);
        stats.add(itemShowStats);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source.equals(itemNewGame)) {
            // start a new Game
            new GraphicalUIMenuNewGame(controller);

        } else if (source.equals(itemExitGame)) {
            // exit the Game
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Wollen Sie diese Anwendung wirklich beenden?",
                    "Programm beenden", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }

        } else if (source.equals(itemShowStats)) {
            // show Statistics
            JOptionPane.showMessageDialog(null, controller.getStatistic());
        }
    }
}
