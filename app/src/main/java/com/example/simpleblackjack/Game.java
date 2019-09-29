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
    protected boolean Completed;
    protected int PlayerCount;
    protected int CardDeckSize;
    protected int PotValue;
    protected static int MinBet;
    protected int playerHits;
    protected int cardValue;
    protected static final int MaxScore = 21;
    protected ArrayList<Card> Deck;
    protected Player CurrentPlayer;
    protected ArrayList<Player> PlayerList;

    /*
     / Game constructor
     / Parameters - numplayers - number of players playing
     / Returns : None
      */
    Game() {
        Completed = false;
        PotValue = 0;
        MinBet = 25;
        Deck = new ArrayList<Card>();
        GenerateDeck();
        PlayerList =  new ArrayList<Player>();
        CardDeckSize = Deck.size();
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
        // For all numbers two to ten, create a card with each suite type and add it to the deck
        for (int i = 2; i <= 10; i++) {
            for (String suite : Card.Suites) {
                    Card card = new Card(i, suite);
                    card.updateValue(i);
                    card.updateSuite(suite);
                    String filename =  Card.Values.get(i-2).toLowerCase() + suite.toLowerCase();
                    card.updateFilename(filename);
                    this.Deck.add(card);
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
                this.Deck.add(card);
            }
        }
        String decksize = Integer.toString(Deck.size());

        for(int i = 0; i< Deck.size(); i++) {
            Log.v("Game", this.Deck.get(i).checkFilename());
        }

    }

    public void addPlayer(Player player) {
        Log.v("Game", "adding this player to player list " + player.checkPlayerName());
        this.PlayerList.add(player);
        for(int i = 0; i < this.PlayerList.size(); i ++) {
            Log.v("Game", "included now in list " + this.PlayerList.get(i).checkPlayerName());
        }
    }

    public ArrayList<Player> checkPlayerList() {
        return PlayerList;
    }

    public void start() {
        // Will distribute first two cards to players and calculate scores
        for(Iterator<Player> iterator = this.PlayerList.iterator(); iterator.hasNext();) {
            Player currentPlayer = iterator.next();
            Log.v("Game", "player list " + this.PlayerList.get(0).checkPlayerName() + this.PlayerList.get(1).checkPlayerName());
            Log.v("Game", "Name of player " + currentPlayer.checkPlayerName());
            for (int j =0; j <=1; j++) {
                Card card = randomCard();
                currentPlayer.addCard(card);
                Log.v("Game", "adding " + card.checkFilename() + "to " + currentPlayer.checkPlayerName() + "size of hand " + currentPlayer.checkHand().size());
            }
        }
    }




    public Card randomCard() {
        int deckSize = this.Deck.size();
        Random r = new Random();
        int pick = r.nextInt(deckSize);
        Card card = this.Deck.get(pick);
        // Want to make sure we remove the card and recalculate the deck size
        this.Deck.remove(pick);
        this.CardDeckSize = this.Deck.size();
        return card;

    }





    /*
     / Checks status of all players to see if any won
     / Parameters - None
     / Returns : Player that won that round
      */
    public Player CheckWin() {
        Player winningplayer = CurrentPlayer;
        int maxscore = winningplayer.CalculateScore();
        for(Player player : this.PlayerList) {
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
        for(Player player : this.PlayerList) {
            if ( player.CalculateScore() > MaxScore) {
                player.Playing = false;
            }
        }

    }

}
