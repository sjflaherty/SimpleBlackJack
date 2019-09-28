/*
/ Authors: Sarah Flaherty, Patrick Sacchet
/ Date: 9/21/19
/ CS 482
 */

package com.example.simpleblackjack;
import android.widget.ImageView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.*;

/*
/ Class: Game - class will represent a instance of a game of blackjack, that is either ongoing or finished
 */
public class Game {
    // Each game will be completed or not, have a count of players, deck size, pot value, minimum bet, max score, a current player, lsit of players, and a deck of cards
    protected static boolean Completed;
    protected static int PlayerCount;
    protected static int CardDeckSize;
    protected static int PotValue;
    protected static int MinBet;
    protected static int playerHits;
    protected static int cardValue;
    protected static final int MaxScore = 21;
    protected static ArrayList<Card> Deck = new ArrayList<Card>();
    protected static Player CurrentPlayer;
    protected static ArrayList<Player> PlayerList = new ArrayList<Player>();

    /*
     / Game constructor
     / Parameters - numplayers - number of players playing
     / Returns : None
      */
    Game() {
        Completed = false;
        CardDeckSize = 52;
        PotValue = 0;
        MinBet = 25;
        GenerateDeck();
        playerHits = 1;
    }

    /*
    / Checks how many times the player has clicked the "Hit" button
    / Parameters - None
    / Returns : Number of hits
  */
    public int getPlayerHits() {
        return playerHits;
    }

    /*
    / Increments the count of hits every time the hit button is clicked
    / Parameters - None
    / Returns : Number of hits+ 1
  */
    public void incPlayerHits() {
        playerHits = playerHits + 1;
    }


    /*
     / Deck generator (creates the deck)
     / Parameters - None
     / Returns : None
      */
    public void GenerateDeck() {
        // Keep track of cards we've made so far
        int cardCount = 0;
        // For all numbers two to ten, create a card with each suite type and add it to the deck
        for (int i = 2; i <= 10; i++) {
            for (String suite : Card.Suites) {
                    Card card = new Card(i, suite);
                    card.updateValue(i);
                    card.updateSuite(suite);
                    String filename =  Card.Values.get(i-2).toLowerCase() + suite.toLowerCase();
                    card.updateFilename(filename);
                    cardCount++;
                    this.Deck.add(card);
                    Log.v("Game", filename);
                }
            }
        // For all face card possibilities, create and add a new card with each suite and value ten to add to deck
        for (String facename : Card.FaceCards) {
            for (String suite : Card.Suites) {
                Card card = new Card(10, suite);
                card.updateValue(10);
                card.updateSuite(suite);
                String filename =  facename.toLowerCase() + suite.toLowerCase();
                card.updateFilename(filename);
                cardCount++;
                this.Deck.add(card);
                Log.v("Game", filename);
            }
        }
        String decksize = Integer.toString(Deck.size());
        Log.v("Game", decksize);
    }





    /*
     / Checks status of all players to see if any won
     / Parameters - None
     / Returns : Player that won that round
      */
    public Player CheckWin() {
        Player winningplayer = CurrentPlayer;
        int maxscore = winningplayer.CalculateScore();
        for(Player player : Game.PlayerList) {
            if (player.CalculateScore() > maxscore) {
                maxscore = player.CalculateScore();
                winningplayer = player;
            }
        }
        Completed = true;
        winningplayer.Won = true;
        return winningplayer;
    }
    /*
     / Checks to see if any players have lost
     / Parameters - None
     / Returns : None
      */
    public void CheckLose () {
        for(Player player : Game.PlayerList) {
            if ( player.CalculateScore() > MaxScore) {
                player.Playing = false;
            }
        }

    }

    /*
     / Main function
     / Parameters - None
     / Returns : None
      */
    public static void main(String[] args) {
        // Initialize game and all players
    }
}
