/**
 * @author Sarah Flaherty
 * @author Patrick Sacchet
 * @version 1.0
 */

package com.example.simpleblackjack;
import android.util.Log;
import java.util.*;

/**
 * Represents a game instance of blackjack
 */
public class Game {
    /**
     * Game finished or not
     */
    protected boolean Completed;

    /**
     * Deck size constant of 52 cards
     */
    protected int CardDeckSize;

    /**
     * How many times the player has chosen to "hit"
     */
    protected int playerHits;

    /**
     * Constant value of 21 as max score
     */
    protected static final int MaxScore = 21;

    /**
     * An array of the cards in the deck
     */
    protected ArrayList<Card> Deck;

    /**
     * The current player of type Player
     */
    protected Player CurrentPlayer;

    /**
     * An array of the players ( the user and the dealer)
     */
    protected ArrayList<Player> PlayerList;

    /**
     * Game constructor
     */
    Game() {
        this.Completed = false;
        this.Deck = new ArrayList<Card>();
        GenerateDeck();
        this.PlayerList =  new ArrayList<Player>();
        this.CardDeckSize = Deck.size();
        this.playerHits = 1;
    }

    /**
     * Returns the number of times the player has hit
     * @return the number of times the player has hit
     */
    public int getPlayerHits() {
        return playerHits;
    }


    /**
     * Returns the number of times the player has hit
     * @return the number of times the player has hit
     */
    public void resetPlayerHits() {
        playerHits = 1;
    }

    /**
     * Increments the count for the number of times the player hit
     */
    public void incPlayerHits() {
        playerHits = playerHits + 1;
    }

    /**
     * Generates a deck for the current instance of the game
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
                card.updateFace(facename);
                card.updateSuite(suite);
                String filename =  facename.toLowerCase() + suite.toLowerCase();
                card.updateFilename(filename);
                this.Deck.add(card);
            }
        }
        for(int i = 0; i< Deck.size(); i++) {
            Log.v("Game", "Adding card to deck " + this.Deck.get(i).checkFilename());
        }
    }

    /**
     * Adds a player to the current game instance
     * @param player the player to be added to the game
     */
    public void addPlayer(Player player) {
        this.PlayerList.add(player);
        for(int i = 0; i < this.PlayerList.size(); i ++) {
            Log.v("Game", "included now in list " + this.PlayerList.get(i).checkPlayerName());
        }
    }

    /**
     * Returns the entire current player list
     * @return the arraylist of players in the current game
     */
    public ArrayList<Player> checkPlayerList() {
        return PlayerList;
    }

    /**
     * Begins the game by adding two cards to each player's hand
     */
    public void start() {
        // Will distribute first two cards to players and calculate scores
        for(Player player : this.PlayerList) {
            Log.v("Game", "Player list " + this.PlayerList.get(0).checkPlayerName() + " " + this.PlayerList.get(1).checkPlayerName());
            Log.v("Game", "Name of player " + player.checkPlayerName());
            for (int j =0; j <=1; j++) {
                Card card = randomCard();
                player.addCard(card);
                Log.v("Game", "Adding " + card.checkFilename() + " to " + player.checkPlayerName() + " size of hand " + player.checkHand().size());
            }
        }
        // Then set the current player to the actual player
        this.CurrentPlayer = this.PlayerList.get(0);
        Log.v("Game", "current player is " + this.CurrentPlayer.checkPlayerName());
    }

    /**
     * Picks a card out of the current deck at random to be given  to a player
     * @return the card randomly chosen from the deck
     */
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

    /**
     * Checks to see who is currently playing
     * @return the player currently playing
     */
    public Player checkCurrentPlayer() {
        return this.CurrentPlayer;
    }

    public void changetoDealer() {
        // Grab the next player when current player stands
        this.CurrentPlayer = this.PlayerList.get(1);
    }

    public void changetoUser() {
        // Grab the next player when current player stands
        this.CurrentPlayer = this.PlayerList.get(0);
    }

    /**
     * Checks status of all players to see if any won
     * @return  Player that won that round
     */
    public Player checkWin() {
        Player winningplayer = CurrentPlayer;
        int maxscore = winningplayer.calculateScore();
        for(Player player : this.PlayerList) {
            if (player.calculateScore() > maxscore && player.calculateScore() <22) {
                maxscore = player.calculateScore();
                winningplayer = player;
            }
        }
        Completed = true;
        winningplayer.Won = true;
        return winningplayer;
    }


    /**
     * Checks to see if any players have lost
     * @return True or False
     */
    public boolean checkLose () {
        // If the player went over 21 they lost
        if(this.CurrentPlayer.calculateScore() > MaxScore) {
            //System.exit(0);
            this.CurrentPlayer.updatePlaying();
            Log.v("Game", this.CurrentPlayer.checkPlayerName() + " has lost the game");
            return true;
        }
        return false;
    }

}
