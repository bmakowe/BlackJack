BlackJack Version 4.1
===================================================================

authores: Björn Makowe, Daniel Jehle

This is a short introduction to our software for detailed
informations read the Javadoc or the markdown file in this folder.

Programm Features
-------------------------------------------------------------------
This Programm includes:
- JUnit 4 Tests
- Google Guice Dependency Injection
- Log4j Logger

Game features
-------------------------------------------------------------------
- General Black Jack game mechanics like betting, hitting, splitting.
- Switchable set of Rules
- Switchable Statistic format
- Text User Interface
- Graphical User Interface

Installation
-------------------------------------------------------------------
notes: the game is currently running as a maven Project

This is a full Eclipse Project therefore it can be opened in Eclipse.
follow this Link for further Information about Eclipse

http://help.eclipse.org/juno/index.jsp

You can import this project via eGit

http://help.eclipse.org/juno/index.jsp?topic=%2Forg.eclipse.egit.doc%2Fhelp%2FEGit%2FUser_Guide%2FUser-Guide.html&cp=20

Game
--------------------------------------------------------------------
The concept of this game is to start with an amount of money at a
dealers desk. Then you bet an amount of money and hit for a card.
Now you decide wether you hit again, split, double the bet or stand
if you can (determined by the Rules). if you beat the dealer (your 
hand > dealer hand and your hand <= 21) you win a part of your bet,
if not you loose the money you bet.

Component structure
--------------------------------------------------------------------
![Component structure](/doc/component_structure.png)

UML class diagrams
--------------------------------------------------------------------
###blackjack

![blackjack](/doc/blackjack_class.png)

###gui

![gui](/doc/gui_class.png)

###tui

![tui](/doc/tui_class.png)

###controller

![controller](/doc/controller_class.png)

###model

![model](/doc/model_class.png)

Hotspots and patterns
-------------------------------------------------------------------
- Observer Pattern
    
Metric Dashboard from Sonar
-------------------------------------------------------------------
![Sonar Dashboard](/doc/sonar.png)
    
Test plan breakdown from Eclipse JUnit
------------------------------------------------------------------
This chapter contains Information regarding the testing of this software.
The classes to be tested are the Classes of the model package.

- Dealer
- Player
- Hand
- CardStack
- Card
- IRules
- IStatistic
- Rules
- Statistic

![Test plan breakdown](/doc/junit_breakdown.png)
    
Guice module files
-------------------------------------------------------------------
BlackJackModule.java

~~~
package de.htwg.blackjack;
import com.google.inject.AbstractModule;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.model.IRules;
import de.htwg.blackjack.model.IStatistic;
/**
 * BlackJackModule
 *
 * @author Makowe Bj&ouml;rn Antonio
 * @author Daniel Jehle
 *
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
~~~
    
Screenshots from TUI and GUI
-------------------------------------------------------------------
![bet](/doc/Gui/bet.png)
![hit](/doc/Gui/hit.png)
![split](/doc/Gui/split.png)
![start](/doc/Gui/start.png)
![started](/doc/Gui/started.png)
![statistic](/doc/Gui/statistic.png)
