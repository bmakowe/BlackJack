package de.htwg.blackjack;

import com.google.inject.AbstractModule;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.model.IRules;
import de.htwg.blackjack.model.IStatistic;

/**
 * BlackJackModule
 *
 * @author Makowe Bj&ouml;rn Antonio <bjmakowe@htwg-konstanz.de>
 * @author Daniel Jehle <dajehle@htwg-konstanz.de>
 * @version 24th June 2013
 */
public class BlackJackModule extends AbstractModule {

    /**
     * configures all modules
     */
    @Override
    protected void configure() {

        bind(IBlackJackController.class).to(de.htwg.blackjack.controller.impl.BlackJackController.class);
        bind(IRules.class).to(de.htwg.blackjack.model.Rules.class);
        bind(IStatistic.class).to(de.htwg.blackjack.model.Statistic.class);

    }
}