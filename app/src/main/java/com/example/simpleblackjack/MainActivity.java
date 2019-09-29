/**
 * @author Sarah Flaherty
 * @author Patrick Sacchet
 * @version 1.0
 */
package com.example.simpleblackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import java.util.Random;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize the game and both the player and the dealer (also initializes deck)
        game = new Game( );
        Player player = new Player(10000, 0, "Player");
        game.addPlayer(player);
        Player dealer = new Player(10000, 0, "Dealer");
        game.addPlayer(dealer);
        // Distributes two cards to players
        game.start();
        setContentView(R.layout.activity_main);
    }

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
                String name = card.checkFilename();
                int idImage = getResources().getIdentifier(name, "drawable", getPackageName());
                cardImage.setImageResource(idImage);
                i++;

            }
            // Now that we added both cards calculate the players initial scores
           if(player.checkPlayerName().compareTo("Player") == 0) {
               int playerScore = player.calculateScore();
               //int id = getIdFromString("R.id.handCount");
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
               //int id = getIdFromString("R.id.Countdealer");
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


    public void displayCard(String idString, String cardname) {
        int id = getIdFromString(idString);
        ImageView cardImage = (ImageView) findViewById(id);
        int idImage = getResources().getIdentifier(cardname, "drawable", getPackageName());
        cardImage.setImageResource(idImage);
    }

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

        // dealer plays
        dealerHand();

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

    /*
/ Dealer plays
/ Parameters - None
/ Returns : none
*/
    public void dealerHand() {
        game.changetoDealer();
        game.resetPlayerHits();
        Player currentPlayer = game.checkCurrentPlayer();

        //dealer decides what highest will be
        int[] choices = {15, 16, 17, 18, 19};
        Random rn = new Random();
        int randVal = rn.nextInt(5);

        //to hit or to stand
        if (currentPlayer.calculateScore() < choices[randVal])
        {
            //then add a card
            addCard(null);
        }

    }


    /*
    Disable both the hit and stand buttons
    params: None
    Return: none
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
     * Returns drawable card string
     * @return string representing card filename
     */
    public String getDrawableCard()
    {
        //array of R.drawable...
        Log.w( "MainActivity", "Got Card -- " );
        return "aceclub";
    }

    /**
     * Button tied to hit functionality, will add card to players hand
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
                // Throw up you lost screen
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

    /*
    / Returns the total sum of the hand
    / Parameters - None
    / Returns : None
    */
    public void displayHandCount(int value) {
        //String handsum = getCardsValue();
        TextView handCountBox = (TextView) findViewById(R.id.handCount);
        handCountBox.setText(value);
    }


    /*
/ Restarts with new game if the game is over
/ Parameters - None
/ Returns : none
*/
    public void isOver(View v) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }


    /*
/ Shows dialog box declaring that the player has lost
/ Parameters - None
/ Returns : none
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

    /*
/ Shows a dialog box alerting the player that they won
/ Parameters - None
/ Returns : none
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


    /*
    / Returns the integer value of the card
    / Parameters - None
    / Returns : Int value sum of cards
    */
    public int getCardsValue(String cardName) {

        int sum =0;

            if (cardName.contains("two")) {
                sum += 2;
            }
            else if (cardName.contains("three")) {
                sum += 3;
            }
            else if (cardName.contains("four")) {
                sum += 4;
            }
            else if (cardName.contains("five")) {
                sum += 5;
            }
            else if (cardName.contains("six")) {
                sum += 6;
            }
            else if (cardName.contains("seven")) {
                sum += 7;
            }

            else if (cardName.contains("eight")) {
                sum += 8;
            }
            else if (cardName.contains("nine")) {
                sum += 9;
            }
            else if (cardName.contains("ten")) {
                sum += 10;
            }
            else if (cardName.contains("jack")) {
                sum += 10;
            }
            else if (cardName.contains("queen")) {
                sum += 10;
            }
            else if (cardName.contains("king")) {
                sum += 10;
            }
            else if (cardName.contains("ace")) {
                sum += 1;
            }
            else {
                sum += 1000;
            }

        return sum;
    }

    }

