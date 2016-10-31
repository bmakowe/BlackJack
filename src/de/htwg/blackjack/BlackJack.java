package de.htwg.blackjack;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.view.gui.GraphicalUI;
import de.htwg.blackjack.view.tui.TextUI;
import org.apache.log4j.PropertyConfigurator;

import java.util.Scanner;

/**
 * BlackJack
 *
 * @author Makowe Bj&ouml;rn Antonio <bjmakowe@htwg-konstanz.de>
 * @author Daniel Jehle <dajehle@htwg-konstanz.de>
 * @version 24th June 2013
 */
public final class BlackJack {
    private static Scanner scanner;

    private BlackJack() {
    }

    /**
     * main
     *
     * @param args
     */
    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.properties");
        Injector injector = Guice.createInjector(new BlackJackModule());

        IBlackJackController controller = injector.getInstance(IBlackJackController.class);

        new GraphicalUI(controller);

        TextUI tui = new TextUI(controller);
        boolean continu = true;
        scanner = new Scanner(System.in);
        while (continu) {
            continu = tui.handleInput(scanner.next());
        }
    }
}
