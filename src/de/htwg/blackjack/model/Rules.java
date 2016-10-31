package de.htwg.blackjack.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Rules
 *
 * @author Makowe Bj&ouml;rn Antonio <bjmakowe@htwg-konstanz.de>
 * @author Daniel Jehle <dajehle@htwg-konstanz.de>
 * @version 04. April 2013
 */
public class Rules implements IRules {

    /**
     * ODDS
     */
    public static final double ODDS = 0.5;
    /**
     * ODDS
     */
    public static final double BJ_ODDS = 1.5;
    private static final int NINE = 9;
    private static final int TEN = 10;
    private static final int ELEVEN = 11;
    private static final int TWENTY_ONE = 21;
    private final List<String> validPictures = new ArrayList<String>();
    private final List<String> validTypes = new ArrayList<String>();

    /**
     * sets Rules
     */
    public Rules() {
        String[] pictures = {"2", "3", "4", "5", "6", "7", "8", "9", "10",
                "jack", "queen", "king", "ace"};
        String[] types = {"clubs", "spades", "hearts", "diamonds"};

        for (String picture : pictures) {
            validPictures.add(picture);
        }
        for (String picture : types) {
            validTypes.add(picture);
        }
    }

    /**
     * gets the Odds
     *
     * @param hand
     * @return
     */
    public double getOdds(Hand hand) {
        if (hand.getSize() == 2 && getHandValue(hand) == TWENTY_ONE) {
            return BJ_ODDS;
        }

        return ODDS;
    }

    /**
     * returns a list of the valid card pictures
     *
     * @return list of the valid card pictures
     */
    public List<String> getValidPictures() {
        return validPictures;
    }

    /**
     * returns a list of the valid card types
     *
     * @return list of valid card types
     */
    public List<String> getValidTypes() {
        return validTypes;
    }

    /**
     * returns the CardStack the game starts with as Interface
     *
     * @return CardStack
     */
    public CardStack getCardStack() {
        CardStack cardstack = new CardStack();

        for (String picture : getValidPictures()) {
            for (String type : getValidTypes()) {
                cardstack.addCard(new Card(picture, type));
            }
        }
        cardstack.shuffle();
        return cardstack;
    }

    /**
     * @param picture the picture which value is searched for
     * @return returns the value for the picture
     */
    public int getPictureValue(String picture) {
        List<String> tenTypes = new ArrayList<String>();

        tenTypes.add("jack");
        tenTypes.add("queen");
        tenTypes.add("king");

        if (tenTypes.contains(picture)) {
            return TEN;
        } else if (getValidPictures().contains(picture)) {
            return Integer.parseInt(picture);
        }

        // return 0 if picture isn't valid
        return 0;
    }

    /**
     * returns the value of your hand
     *
     * @param hand
     * @return hand value
     */
    public int getHandValue(Hand hand) {
        // return 0 if hand is null
        if (hand == null) {
            return 0;
        }
        // value of hand and number of aces
        int value = 0;
        int aces = 0;
        // go through all cards, add the values and count the aces
        for (Card c : hand) {
            String picture = c.getPicture();
            if (picture.equals("ace")) {
                ++aces;
            } else {
                value += getPictureValue(picture);
            }
        }
        // add the aces as 1 first
        value += aces;
        // loop to choose the value of the aces (1 or 11)
        for (int i = 0; i < aces; ++i) {
            if ((value + TEN) <= TWENTY_ONE) {
                value += TEN;
            } else {
                break;
            }
        }
        return value;
    }

    /**
     * checks if a Hand is able to hit
     *
     * @param hand
     * @return true if Hand is able to hit
     */
    public boolean canHit(Hand hand) {
        return (hand != null && getHandValue(hand) < TWENTY_ONE);
    }

    /**
     * checks if you're able to split
     *
     * @param player
     * @return true if you're able to split
     */
    public boolean canSplit(Player player) {
        // if player is null
        // if player has != 1 hand
        // if player has != 2 cards in that hand
        if (player == null || player.numberOfHands() != 1 || player.getActiveHand().getSize() != 2) {
            return false;
        }

        List<Card> cards = player.getActiveHand().getCards();
        String picture1 = cards.get(0).getPicture();
        String picture2 = cards.get(1).getPicture();

        // player has pair?
        return (picture1.equals(picture2));
    }

    /**
     * checks if you're able to make double Down
     *
     * @param player
     * @return true if you're able to double Down
     */
    public boolean canDoubleDown(Player player) {
        if (player == null || player.numberOfHands() != 1 || player.getActiveHand().getSize() != 2) {
            return false;
        }

        int value = getHandValue(player.getActiveHand());

        return (value >= NINE && value <= ELEVEN && player.getCash() >= player.getBet());
    }

    /**
     * checks who wins
     *
     * @param hand1
     * @param hand2
     * @return true if player wins
     */
    public boolean wins(Hand hand1, Hand hand2) {
        if (hand1 == null || hand2 == null) {
            return false;
        }

        int value1 = getHandValue(hand1);
        int value2 = getHandValue(hand2);

        if (value1 > value2 && value1 <= TWENTY_ONE) {
            return true;
        } else {
            return (value1 <= TWENTY_ONE && value2 > TWENTY_ONE);
        }
    }

    /**
     * @param dealer
     * @param player
     * @return returns true if dealer should hit, returns false otherwise
     */
    public boolean dealerHits(Dealer dealer, Player player) {
        if (dealer == null || player == null) {
            return false;
        }

        int wins = 0;
        for (int i = 0; i < player.numberOfHands(); ++i) {
            if (wins(dealer.getActiveHand(), player.getActiveHand())) {
                ++wins;
            }
            player.switchHand();
        }
        return (canHit(dealer.getActiveHand()) && wins < 1);
    }

    /**
     * if it is a valid Bet
     *
     * @param bet
     * @return
     */
    public boolean isValidBet(int bet) {
        return (bet % TEN == 0 && bet >= TEN);
    }
}
