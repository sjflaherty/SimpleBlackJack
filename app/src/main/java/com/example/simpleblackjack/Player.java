/*
/ Authors: Sarah Flaherty, Patrick Sacchet
/ Date: 9/21/19
/ CS 482
 */

package com.example.simpleblackjack;
import java.util.*;

/*
/ Class: Player - Represents a player in blackjack
 */
public class Player {
    // Each player has either won or not won, has a count of cards, a hand, some money and a current score
    protected static boolean Won;
    protected static boolean Playing;
    protected static int CardCount;
    protected static ArrayList<Card> Hand = new ArrayList<Card>();
    protected static int Money;
    protected static int Score;
    protected static String Name;

    /*
    / Player constructor
    / Parameters - money - money player starts with
    / cardcount - number of cards player starts with
    / Returns : None
     */
    Player(int money, int cardcount, String name) {
        Won = false;
        Money = money;
        CardCount = cardcount;
        // Each player starts with two cards, then calculate score
        for (int i = 0; i <=2; i++) {
            Hand.add(Game.Deck.get(0));
        }
        Score = CalculateScore();
        Playing = true;
        Name = name;
    }

    /*
    / Calculates score based on players current cards
    / Parameters - None
    / Returns : score of the player
     */
    public int CalculateScore() {
        int score = 0;
        // Go through all cards in the players hand and calculate their current score
        for (int i = 0; i <= Hand.size(); i++) {
            Card card = Hand.get(i);
            score += card.Value;
        }
        return score;
    }

    public void addCard(Card card) {
        this.Hand.add(card);
        this.CardCount++;
        this.Score = this.CalculateScore();
    }

    /*
    / Allows player to place bet
    / Parameters - betvalue - money player would like to bet
    / Returns : None
     */
    public void PlaceBet(int betvalue) {
        if (betvalue < Game.MinBet) {
            System.out.println("Bet too small");
            return;
        }
        Money -= betvalue;
        Game.PotValue += betvalue;
    }

    /*
    / Allows player to stand, moving to next player
    / Parameters - None
    / Returns : None
     */
    public void Stand() {
        int currplayerpos = Game.PlayerList.indexOf(Game.CurrentPlayer);
        Game.CurrentPlayer = Game.PlayerList.get(currplayerpos + 1);
    }

    /*
    / Allows player to draw card, adding to their score and number of cards, taking away from deck count
    / Parameters - None
    / Returns : None
     */
    public void DrawCard() {
        Hand.add(Game.Deck.get(0));
        Game.CardDeckSize -= 1;
        CardCount += 1;
        CalculateScore();
    }


}
