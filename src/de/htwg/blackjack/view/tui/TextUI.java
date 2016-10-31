package de.htwg.blackjack.view.tui;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.util.observer.IObserver;
import org.apache.log4j.Logger;

import static java.lang.System.out;

/**
 * TextUI
 *
 * @author Makowe Bj&ouml;rn Antonio <bjmakowe@htwg-konstanz.de>
 * @author Daniel Jehle <dajehle@htwg-konstanz.de>
 * @version 24th June 2013
 */
public class TextUI implements IObserver {

    private IBlackJackController controller;
    private Logger logger = Logger.getLogger("de.htwg.sudoku.view.tui");
    private String newLine = System.getProperty("line.separator");

    /**
     * @param controller
     */
    public TextUI(IBlackJackController controller) {
        this.controller = controller;
        controller.addObserver(this);
    }

    /**
     * prints new update
     */
    public void update() {
        printTUI();
    }

    /**
     * handles input game commands
     *
     * @param line
     * @return true if successful
     */
    public boolean handleInput(String line) {
        // start new Game
        if (line.matches("start[0-9]+")) {
            String param = line;
            param = param.replaceAll("start", "");
            int cash = Integer.parseInt(param);
            controller.start(cash);
        } else if (line.equalsIgnoreCase("stand")) {
            controller.stand();
        } else if (line.equalsIgnoreCase("hit")) {
            controller.hit();
        } else if (line.equalsIgnoreCase("double")) {
            controller.doubleDown();
        } else if (line.equalsIgnoreCase("split")) {
            controller.split();
        } else if (line.equalsIgnoreCase("stat")) {
            out.print(controller.getStatistic());
        } else if (line.equalsIgnoreCase("exit")) {
            return false;
        } else if (line.matches("bet[0-9]+")) {
            String param = line;
            param = param.replaceAll("bet", "");
            int cash = Integer.parseInt(param);
            controller.bet(cash);
        } else if (line.contains("set:")) {
            String param = line;
            param = param.replaceAll("set", "");
            controller.setHand(param);
        }

        return true;
    }

    /**
     * prints Tui
     */
    public void printTUI() {
        logger.info(newLine + newLine + "Dealer: " + controller.getDealerHand()
                + newLine + "Player: " + controller.getPlayerHands()
                + newLine + "Bet   : " + controller.getBet()
                + newLine + "Cash  : " + controller.getCash()
                + newLine + "valid commands: start<int>: start new game with <int> cash, stop: stop the game, stand: end hitting for active hand, hit: take a card, doubleDown: doubles bet, split: splits active hand, bet<int>: bets <int>, exit: exits"
                + newLine);
    }
}
