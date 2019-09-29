/**
 * @author Sarah Flaherty
 * @author Patrick Sacchet
 * @version 1.0
 */

package com.example.simpleblackjack;
import android.util.Log;

import java.util.*;

/**
 * Class will represent one player playing blackjack
 */
public class Player {
    // Each player has either won or not won, has a count of cards, a hand, some money and a current score
    protected boolean Won;
    protected boolean Playing;
    protected int CardCount;
    protected ArrayList<Card> Hand;
    protected int Money;
    protected int Score;
    protected String Name;

    /**
     * Player constructor
     * @param money money the player starts off with
     * @param cardcount number of cards the player has
     * @param name name of the player
     */
    Player(int money, int cardcount, String name) {
        this.Won = false;
        this.Money = money;
        this.CardCount = cardcount;
        this.Hand = new ArrayList<Card>();
        // Each player starts with two cards, then calculate score
        this.Score = 0;
        this.Playing = true;
        this.Name = name;
    }

    /**
     * Calculates the score of the player
     * @return the score of the player
     */
    public int calculateScore() {
        int score = 0;
        // Go through all cards in the players hand and calculate their current score
        for (int i = 0; i < this.Hand.size(); i++) {
            Card card = this.Hand.get(i);
            // If we have aAce evaluate it accordingly
            Log.v("Player", card.checkFace());
            if (card.checkFace().compareTo("Ace") == 0) {
                // If 11 is too much just add one
                if (score + 11 > 21) {
                    score += 1;
                }
                // If we don't go over then add 11
                else {
                    score += 11;
                }
            }
            // Otherwise just add the score
            else {
                score += card.checkValue();
            }
        }
        return score;
    }

    /**
     * Adds a card to the player's hand
     * @param card card to be added to player's hand
     */
    public void addCard(Card card) {
        // Add the card to the player's hand, increase their card count and recalculate their score
        this.Hand.add(card);
        this.CardCount = this.Hand.size();
        this.Score = this.calculateScore();
    }

    /**
     * Checks the entire hand of the current player
     * @return the arraylist of cards in the player's hand
     */
    public ArrayList<Card> checkHand() {
        return Hand;
    }

    /*
    / Allows player to place bet
    / Parameters - betvalue - money player would like to bet
    / Returns : None
     */
    //public void PlaceBet(int betvalue) {
    //    if (betvalue < Game.MinBet) {
    //        System.out.println("Bet too small");
    //        return;
    //    }
    //    Money -= betvalue;
    //    Game.PotValue += betvalue;
    //}

    /**
     * Checks the player's name
     * @return the player's name
     */
    public String checkPlayerName() {
        return Name;
    }

    /*
    / Allows player to stand, moving to next player
    / Parameters - None
    / Returns : None
     */
    //public void Stand() {
    //    int currplayerpos = this.PlayerList.indexOf(Game.CurrentPlayer);
    //    Game.CurrentPlayer = Game.PlayerList.get(currplayerpos + 1);
    //}

    /*
    / Allows player to draw card, adding to their score and number of cards, taking away from deck count
    / Parameters - None
    / Returns : None
     */
    //public void DrawCard() {
    //   Hand.add(Game.Deck.get(0));
    //    Game.CardDeckSize -= 1;
    //    CardCount += 1;
    //    CalculateScore();
    //}


}
