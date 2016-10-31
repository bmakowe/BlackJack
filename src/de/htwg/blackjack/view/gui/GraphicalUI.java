package de.htwg.blackjack.view.gui;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.util.observer.IObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * GraphicalUI
 *
 * @author Makowe Bj&ouml;rn Antonio <bjmakowe@htwg-konstanz.de>
 * @author Daniel Jehle <dajehle@htwg-konstanz.de>
 * @version 24th June 2013
 */
@SuppressWarnings("serial")
public class GraphicalUI extends JFrame implements IObserver {

    /**
     * Constant for Background Color
     */
    protected static final Color BACKGROUNDCOLOR = new Color(151, 195, 10);
    /**
     * Constant for BorderLine Color
     */
    protected static final Color BORDERCOLOR = Color.WHITE;
    /**
     * Constant for window with
     */
    private static final int WITDH = 1000;
    /**
     * Constant for window height
     */
    private static final int HEIGHT = 600;

    private IBlackJackController controller;
    private GraphicalUICardField cardField;
    private GraphicalUIPlayerInfo playerInfo;
    private GraphicalUIOption options;

    /**
     * GraphicalUI
     *
     * @param controller
     */
    public GraphicalUI(IBlackJackController controller) {
        this.controller = controller;
        this.cardField = new GraphicalUICardField();
        this.playerInfo = new GraphicalUIPlayerInfo();
        this.options = new GraphicalUIOption(controller);

        // observable
        controller.addObserver(this);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(playerInfo, BorderLayout.WEST);
        mainPanel.add(cardField, BorderLayout.CENTER);
        mainPanel.add(options, BorderLayout.SOUTH);

        // Menu
        this.setJMenuBar(new GraphicalUIMenu(controller));

        // Sonstige Eigenschaften
        this.setSize(WITDH, HEIGHT);
        this.setContentPane(mainPanel);

        // color the stuff
        for (Component component : this.getContentPane().getComponents()) {
            component.setBackground(GraphicalUI.BACKGROUNDCOLOR);
        }

        this.setTitle("BlackJack");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {

                if (JOptionPane.showConfirmDialog(null,
                        "Wollen sie diese Anwendung wirklich beenden?",
                        "Beenden", JOptionPane.YES_NO_OPTION) == 0) {
                    System.exit(0);
                }
            }
        });

        // Neues Spiel mit benutzerdefinierten Credit starten
        new GraphicalUIMenuNewGame(controller);
    }

    /**
     * updates Statistic
     */
    @Override
    public void update() {
        this.options.setOptions(controller.getValidOptions());
        this.playerInfo.setInfo(controller.getCash(), controller.getBet());
        this.cardField.setCards(controller.getDealerHand(), controller.getPlayerHands());
    }
}
