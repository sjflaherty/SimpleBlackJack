/**
 * Black Jack Game Simulator
 * @author Sarah Flaherty
 * @author Patrick Sacchet
 * @version 1.0
 */
package com.example.simpleblackjack;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Necessary libraries
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import java.util.Random;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static Game game;

    /**
     * Creates the game's app instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new Game( );
        /**
         * Initializes player
         */
        Player player = new Player( 0, "Player");
        game.addPlayer(player);
        /**
         * Initializes Dealer
         */
        Player dealer = new Player( 0, "Dealer");
        game.addPlayer(dealer);
        // Distributes two cards to players
        game.start();
        setContentView(R.layout.activity_main);
    }

    /**
     * Checks that the app has started and displays 2 cards for each player
     */
    public void onStart( ) {
        super.onStart();
        Log.w( "MainActivity", "MainActivity onStart called"  );
        displayCards();
    }

    /**
     * Goes through the first two distributed cards of each player then displays them on the table
     */
    public void displayCards() {
        ArrayList<String> cardnumbers = new ArrayList<>();
        cardnumbers.add("R.id.card6");
        cardnumbers.add("R.id.card7");
        cardnumbers.add("R.id.card1");
        cardnumbers.add("R.id.card2");
        int i = 0;
        ArrayList<Player> playerList= game.checkPlayerList();
        Log.v("MainActivity", "Size of player list " + playerList.size());
        for (Player player : playerList)
        {
            Log.v("MainActivity", "player name " + player.checkPlayerName());
            // We will go through the player list, going through each hand and displaying the cards they have on the table
            ArrayList<Card> playerHand = player.checkHand();
            Log.v("MainActivity", "size of player hand " + playerHand.size());
            for(Card card : playerHand) {
                String idName = cardnumbers.get(i);
                Log.v("MainActivity", idName);
                Log.v("MainActivity", "value of i " + i);
                Log.v("MainActivity", card.checkFilename());
                int id = getIdFromString(idName);
                ImageView cardImage = (ImageView) findViewById(id);
                // Name will give us the name of the png for that particular card
                String name = card.checkFilename();
                int idImage = getResources().getIdentifier(name, "drawable", getPackageName());
                // Display the card and move on to the next card
                cardImage.setImageResource(idImage);
                i++;
            }
            // Now that we added both cards calculate the players initial scores
           if(player.checkPlayerName().compareTo("Player") == 0) {
               int playerScore = player.calculateScore();
               TextView playerScoreChange = (TextView) findViewById(R.id.handCount);
               playerScoreChange.setText(Integer.toString(playerScore));
               if (playerScore ==21)
               {
                   disableButtons();
                   wonAlert();
               }
           }
           else {
               int dealerScore = player.calculateScore();
               TextView dealerScoreChange = (TextView) findViewById(R.id.handCountdealer);
               dealerScoreChange.setText(Integer.toString(dealerScore));
               if (dealerScore == 21)
               {
                   disableButtons();
                   lostAlert();
               }
           }
        }
    }

    /**
     * Displays a card generated from the deck as an ImageView
     * @param idString accesses the id for the card as named as a string
     * @param cardname accesses the name of the card to be used to view corresponding image
     */
    public void displayCard(String idString, String cardname) {
        int id = getIdFromString(idString);
        ImageView cardImage = (ImageView) findViewById(id);
        int idImage = getResources().getIdentifier(cardname, "drawable", getPackageName());
        cardImage.setImageResource(idImage);
    }

    /**
     * Displays the score in a TextView for the current hand of each player
     * @param score is the current score of the player
     * @param currentPlayer is the player for which the score correlates to
     */
    public void displayScore(int score, Player currentPlayer) {
        // If we are updating the score of the player, we chose the appropriate text view
        if (currentPlayer.checkPlayerName().compareTo("Player") == 0) {
            TextView playerScoreChange = (TextView) findViewById(R.id.handCount);
            playerScoreChange.setText(Integer.toString(score));
        }
        // Otherwise we are changing the score of the dealer
        else {
            TextView dealerScoreChange = (TextView) findViewById(R.id.handCountdealer);
            dealerScoreChange.setText(Integer.toString(score));
        }
    }

    /**
     * Gets the id value depending on which card we are looking for
     * @param idString string with card number attached (R.id.cardx)
     * @return the id of the card looked for
     */
    public int getIdFromString (String idString) {
        // Need to take in string form of card ids and get the proper id value depending on the card (so we can display images)
        int id;
        if(idString.equals("R.id.card1")) {
            id = R.id.card1;
        }
        else if(idString.equals("R.id.card2")) {
            id = R.id.card2;
        }
        else if(idString.equals("R.id.card3")) {
            id = R.id.card3;
        }
        else if(idString.equals("R.id.card4")) {
            id = R.id.card4;
        }
        else if(idString.equals("R.id.card5")) {
            id = R.id.card5;
        }
        else if(idString.equals("R.id.card6")) {
            id = R.id.card6;
        }
        else if(idString.equals("R.id.card7")) {
            id = R.id.card7;
        }
        else if(idString.equals("R.id.card8")) {
            id = R.id.card8;
        }
        else if(idString.equals("R.id.card9")) {
            id = R.id.card9;
        }
        else {
            id = R.id.card10;
        }
        return id;
    }

    /**
     * Creates the stand button to display
     * @param v view of card being passed
     */
    public void stand( View v ) {
        // Create button for stand function
        Button buttonStand = (Button) findViewById(R.id.Stand);
        buttonStand.setClickable(false);
        buttonStand.setBackgroundColor(getResources().getColor(R.color.lightBlue));
        // Create button for hit function
        Button buttonHit = (Button) findViewById(R.id.Hit);
        buttonHit.setClickable(false);
        buttonHit.setBackgroundColor(getResources().getColor(R.color.lightBlue));
        // Dealer plays
        game.changetoDealer();
        game.resetPlayerHits();
        dealerHand();
        // Want to switch to user before we check the result
        game.changetoUser();
        Player user = game.checkCurrentPlayer();
        if (game.checkWin() == user)
        {
            wonAlert();
        }
        else
        {
            lostAlert();
        }
    }

    /**
     * The dealer plays his hand, chooses to hit or stand
     */
    public void dealerHand() {
        Player currentPlayer = game.checkCurrentPlayer();
        // Dealer decides what highest will be
        int[] choices = {15, 16, 17, 18, 19};
        Random rn = new Random();
        int randVal = rn.nextInt(5);
        // Determines whether or not to hit or stand
        if (currentPlayer.calculateScore() < choices[randVal])
        {
            // Then add a card
            addCard(null);
        }
    }

    /**
     * Disable both the hit and stand buttons
     */
    public void disableButtons() {
         // Create button for stand function
         Button buttonStand = (Button) findViewById(R.id.Stand);
         buttonStand.setClickable(false);
         buttonStand.setBackgroundColor(getResources().getColor(R.color.lightBlue));
         // Create button for hit function
         Button buttonHit = (Button) findViewById(R.id.Hit);
         buttonHit.setClickable(false);
         buttonHit.setBackgroundColor(getResources().getColor(R.color.lightBlue));
     }

    /**
     * Button tied to hit functionality, will add card to players hand and recalculate score to display afterwords
     * @param v view for hit button
     */
    public void addCard( View v ) {
        Log.w( "MainActivity", "Inside addCard" );
        Button buttonHit = (Button) findViewById(R.id.Hit);
        Player currentPlayer = game.checkCurrentPlayer();
        // For each hit, randomly get a card from deck, add it to players hand, then display it
        if (game.getPlayerHits()==1) {
            ImageView cardImage = (ImageView) findViewById(R.id.card8);
            Card card = game.randomCard();
            currentPlayer.addCard(card);
            String cardname = card.checkFilename();
            int id = getResources().getIdentifier(cardname, "drawable", getPackageName());
            Log.w( "MainActivity", "adding card " + cardname );
            cardImage.setImageResource(id);
            // Calculate new score after drawing new card and display the card and new score
            int newScore = currentPlayer.calculateScore();
            displayCard("R.id.card8", cardname);
            displayScore(newScore, currentPlayer);
            // Check if the player went over 21, if they did they lost
            if(game.checkLose()) {
                disableButtons();
                lostAlert();
            }
            game.incPlayerHits();
            if (newScore == 21 && currentPlayer==game.PlayerList.get(0))
            {
                disableButtons();
                wonAlert();
            }
            else if (newScore == 21 && currentPlayer==game.PlayerList.get(1))
            {
                disableButtons();
                lostAlert();
            }
        }
        else if (game.getPlayerHits()==2) {
            ImageView cardImage = (ImageView) findViewById(R.id.card9);
            Card card = game.randomCard();
            currentPlayer.addCard(card);
            String cardname = card.checkFilename();
            int id = getResources().getIdentifier(cardname, "drawable", getPackageName());
            Log.w( "MainActivity", "adding card " + cardname );
            cardImage.setImageResource(id);
            // Calculate new score after drawing new card and display the card and new score
            int newScore = currentPlayer.calculateScore();
            displayCard("R.id.card9", cardname);
            displayScore(newScore, currentPlayer);
            // Check if the player went over 21, if they did they lost
            if(game.checkLose()) {
                disableButtons();
                lostAlert();
            }
            if (newScore == 21 && currentPlayer==game.PlayerList.get(0))
            {
                disableButtons();
                wonAlert();
            }
            else if (newScore == 21 && currentPlayer==game.PlayerList.get(1))
            {
                disableButtons();
                lostAlert();
            }
            game.incPlayerHits();
        }
        else if (game.getPlayerHits()==3) {
            ImageView cardImage = (ImageView) findViewById(R.id.card10);
            Card card = game.randomCard();
            currentPlayer.addCard(card);
            String cardname = card.checkFilename();
            int id = getResources().getIdentifier(cardname, "drawable", getPackageName());
            Log.w( "MainActivity", "adding card " + cardname );
            cardImage.setImageResource(id);
            // Calculate new score after drawing new card and display the card and new score
            int newScore = currentPlayer.calculateScore();
            displayCard("R.id.card10", cardname);
            displayScore(newScore, currentPlayer);
            // Check if the player went over 21, if they did they lost
            if(game.checkLose()) {
                disableButtons();
                lostAlert();
            }
            game.incPlayerHits();
            if (newScore == 21 && currentPlayer==game.PlayerList.get(0))
            {
                disableButtons();
                wonAlert();
            }
            else if (newScore == 21 && currentPlayer==game.PlayerList.get(1))
            {
                disableButtons();
                lostAlert();
            }
            buttonHit.setClickable(false);
            buttonHit.setBackgroundColor(getResources().getColor(R.color.lightBlue));
        }
        else {
            buttonHit.setClickable(false);
            buttonHit.setBackgroundColor(getResources().getColor(R.color.lightBlue));
        }
    }

    /**
     *  Restarts with new game if the game is over
    */
    public void isOver(View v) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    /**
     *Shows dialog box declaring that the player has lost
     */
    public void lostAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Loss");
        alertDialog.setMessage("I'm sorry you lost the game.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "New Game",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
        alertDialog.show();
    }

    /**
     * Shows a dialog box alerting the player that they won
    */
    public void wonAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Win!");
        alertDialog.setMessage("You won the game.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "New Game",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
        alertDialog.show();
    }
}

