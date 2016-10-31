package de.htwg.blackjack.controller.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.model.*;
import de.htwg.util.observer.Observable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * BlackJackController
 *
 * @author Makowe Bj&ouml;rn Antonio <bjmakowe@htwg-konstanz.de>
 * @author Daniel Jehle <dajehle@htwg-konstanz.de>
 * @version 12th May 2013
 */
@Singleton
public class BlackJackController extends Observable implements IBlackJackController {

    private String status = "Welcome!";
    private String validOptions = "bet";
    private CardStack cardstack;
    private Dealer dealer;
    private Player player;
    private IRules rules;
    private IStatistic statistic;

    /**
     * New Dealer
     *
     * @param rules     Rules for the game
     * @param statistic Statistic for the game
     */
    @Inject
    public BlackJackController(IRules rules, IStatistic statistic) {
        dealer = new Dealer();
        player = new Player();
        this.rules = rules;
        this.statistic = statistic;
    }

    /**
     * makes a new Game with cash
     */
    public void start(int cash) {
        cardstack = rules.getCardStack();
        player.setCash(cash);
        statistic.setStartCash(cash);
        status = "New game has started";
        notifyObservers();
    }

    /**
     * stops a game
     */
    public void stop() {
        status = "Game over";
        notifyObservers();
    }

    /**
     * adds two cards to hand if cardStack size is less than 2, the cardStack
     * gets refilled
     *
     * @param hand
     */
    public void drawTwoCards(Hand hand) {
        // when there are less than 2 cards, use a new CardStack
        stackCheck();

        // add two Cards to Hand
        for (int i = 0; i < 2; i++) {
            Card card = cardstack.drawCard();
            hand.addCard(card);
        }
    }

    /**
     * sets up a new Round
     */
    public void newRound() {
        player.reset();
        dealer.reset();
        drawTwoCards(dealer.getActiveHand());
        drawTwoCards(player.getActiveHand());
    }

    /**
     * ends the round and/or switches the active hand of the player
     */
    public void stand() {
        if (player.isActiveLast()) {
            // set first hand active
            player.switchHand();
            endRound();
            status = "round ended";
        } else {
            player.switchHand();
            status = "next hand is active";
        }
        notifyObservers();
    }

    /**
     * adds a card to the active hand of the player if the player is able to hit
     */
    public void hit() {
        if (rules.canHit(player.getActiveHand())) {
            stackCheck();
            player.takeCard(cardstack.drawCard());
            status = "player took a card";
        } else {
            status = "player unable to hit";
        }
        notifyObservers();
    }

    /**
     * doubles the bet if the player is able to
     */
    public void doubleDown() {
        if (rules.canDoubleDown(player)) {
            player.setBet(2 * player.getBet());
            status = "player doubled bet";
        } else {
            status = "player was unable to double bet";
        }
        notifyObservers();
    }

    /**
     * splits the active hand of the player in two if he is able to split
     */
    public void split() {
        if (rules.canSplit(player)) {
            player.split();
            status = "player splited";
        } else {
            status = "player was unable to split";
        }
        notifyObservers();
    }
    // set the bet -> if player has the cash...

    /**
     * accept Bet initialize a new Round and sets the bet
     *
     * @param bet
     */
    public void bet(int bet) {
        if (player.getCash() >= bet && rules.isValidBet(bet)) {
            newRound();
            player.setCash(player.getCash() - bet);
            player.setBet(bet);
            status = "player bet " + bet;
        } else {
            status = "player unable to bet";
        }
        notifyObservers();
    }

    /**
     * returns your status
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * gets Statistic
     *
     * @return statistic
     */
    public String getStatistic() {
        statistic.setEndCash(player.getCash());
        return statistic.getStatistic("std");
    }

    /**
     * ends the Round this method is called by stand()
     */
    public void endRound() {
        dealerHitLoop();
        player.setCash(getNewCash());
        player.setBet(0);
    }

    /**
     * win loose getNewCash
     *
     * @return returns the new cash value of the player
     */
    public int getNewCash() {
        int cash = 0;
        int won = 0;

        for (int i = 0; i < player.numberOfHands(); ++i) {
            if (rules.wins(player.getActiveHand(), dealer.getActiveHand())) {
                cash += rules.getOdds(player.getActiveHand()) * (double) player.getBet();
                won = 1;
                statistic.won();
                status = (i + 1) + ". hand won";
            } else {
                statistic.lost();
                status = (i + 1) + ". hand lost";
            }
            notifyObservers();
            player.switchHand();
        }

        if (won == 0) {
            statistic.lostCash(player.getBet());
        } else {
            statistic.wonCash(cash);
        }

        return cash + player.getCash() + player.getBet() * won;
    }

    /**
     * dealer hit
     */
    public void dealerHitLoop() {
        while (rules.dealerHits(dealer, player)) {
            dealer.getActiveHand().addCard(cardstack.drawCard());
        }
        status = "dealer finished taking cards";
        notifyObservers();
    }

    /**
     * checks if there are more than 2 Cards in cardStack if not it gets a new
     * cardstack
     */
    public void stackCheck() {
        if (cardstack.getSize() < 2) {
            cardstack = rules.getCardStack();
            status = "new cardstack";
            notifyObservers();
        }
    }

    /**
     * gets all of your valid Game options
     *
     * @return valid Options
     */
    public String getValidOptions() {

        // Status
        String newGame = "New game has started";
        String endRound = "round ended";
        String playerBet = "player bet";
        String playerSplit = "player splited";
        String playerTookACard = "player took a card";
        String nextHandIsActive = "next hand is active";
        String cardAdministration = "card administration";
        String playerDoubledBet = "player doubled bet";
        // Valid Options
        String bet = "bet";
        String hitDoubleSplitStand = "hit,double,split,stand";
        String hitStand = "hit,stand";
        String hitSplitStand = "hit,split,stand";
        String betHitStandSplitDouble = "bet,hit,stand,split,double";
        String hitDoubleStand = "hit,double,stand";
        String stand = "stand";

        if (status.equals(newGame) || status.equals(endRound)) {
            validOptions = bet;
        } else if (status.contains(playerBet) && rules.canDoubleDown(player) && rules.canSplit(player)) {
            validOptions = hitDoubleSplitStand;
        } else if (status.contains(playerBet) && rules.canDoubleDown(player)) {
            validOptions = hitDoubleStand;
        } else if (status.contains(playerBet) && rules.canSplit(player)) {
            validOptions = hitSplitStand;
        } else if (status.contains(playerBet)) {
            validOptions = hitStand;
        } else if (status.equals(playerSplit) || status.equals(playerDoubledBet)) {
            validOptions = hitStand;
        } else if (status.equals(playerTookACard) && !rules.canHit(player.getActiveHand())) {
            validOptions = stand;
        } else if (status.equals(nextHandIsActive)) {
            validOptions = hitStand;
        } else if (status.equals(cardAdministration)) {
            validOptions = betHitStandSplitDouble;
        }

        return validOptions;
    }

    /**
     * gets your Player Hand
     *
     * @return player Hand
     */
    @Override
    public String getPlayerHands() {
        return player.getHands().toString();
    }

    /**
     * gets the Dealer Hand
     *
     * @return dealer Hand
     */
    @Override
    public String getDealerHand() {
        return dealer.getActiveHand().toString();
    }

    /**
     * gets your Bet
     *
     * @return your bet
     */
    @Override
    public int getBet() {
        return player.getBet();
    }

    /**
     * gets Cash
     *
     * @return cash
     */
    @Override
    public int getCash() {
        return player.getCash();
    }

    /**
     * gets cards in format jack-diamonds, queen-clubs ...
     */
    public void setHand(String cards) {
        player.getActiveHand().dispose();

        Pattern pat = Pattern.compile("\\w+-\\w+");
        Matcher mat = pat.matcher(cards);
        while (mat.find()) {
            String card = mat.group(0);
            String[] values = card.split("-");
            player.getActiveHand().addCard(new Card(values[0], values[1]));
        }
        status = "card administration";
        notifyObservers();
    }

    /**
     * sets the CardStack
     *
     * @param cardstack
     */
    public void setICardStack(CardStack cardstack) {
        this.cardstack = cardstack;
    }

    /**
     * gets the Player
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }
}
