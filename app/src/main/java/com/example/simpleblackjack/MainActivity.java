package com.example.simpleblackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
    protected static String suit;
    protected static String rank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize the game and both the player and the dealer (also initializes deck)
        game = new Game( );
        Player player = new Player(10000, 0, "Player");
        Player dealer = new Player(10000, 0, "Dealer");
        // Add both players to the player list
        ArrayList<Player> CurrPlayers = new ArrayList<>();
        CurrPlayers.add(player);
        CurrPlayers.add(dealer);
        game.addPlayers(CurrPlayers);
        // Distributes two cards to players
        game.start();
        setContentView(R.layout.activity_main);
    }

    public void onStart( ) {
        super.onStart();
        Log.w( "MainActivity", "MainActivity onStart called"  );
        //displayCards();
        //displayHandCount(0);
    }


    public void displayCards() {
        String[] cardnumbers = new String[]{"R.id.card1", "R.id.card2", "R.id.card6", "R.id.card7"};
        int i = 0;
        for (Player player : game.checkPlayerList())
        {
            // We will go through the player list, going through each hand and displaying the cards they have on the table
            for(Card card : player.checkHand()) {
                String idName = cardnumbers[i];
                int id = getIdFromString(idName);
                ImageView cardImage = (ImageView) findViewById(id);
                String name = card.checkFilename();
                //ImageView cardImage = (ImageView) getResources().getIdentifier(name, "drawable", getPackageName());

                int idImage = getResources().getIdentifier(name, "drawable", getPackageName());
                //int value = getCardsValue(name);
                //displayHandCount(value);
                cardImage.setImageResource(idImage);
                i++;

            }
        }
    }

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



    public void stand( View v ) {
        Log.w("MainActivity", "Inside stand");
        // Create button for stand function
        Button buttonStand = (Button) findViewById(R.id.Stand);
        buttonStand.setClickable(false);
        buttonStand.setBackgroundColor(getResources().getColor(R.color.lightBlue));
        // Create button for hit function
        Button buttonHit = (Button) findViewById(R.id.Hit);
        buttonHit.setClickable(false);
        buttonHit.setBackgroundColor(getResources().getColor(R.color.lightBlue));

        }


    public String getDrawableCard()
    {
        //array of R.drawable...
        Log.w( "MainActivity", "Got Card -- " );
        return "aceclub";
    }

    public void addCard( View v ) {
        Log.w( "MainActivity", "Inside addCard" );
        Button buttonHit = (Button) findViewById(R.id.Hit);

        if (game.getPlayerHits()==1) {
            ImageView cardImage = (ImageView) findViewById(R.id.card8);
            String name = getDrawableCard();
            int id = getResources().getIdentifier(name, "drawable", getPackageName());
            Log.w( "MainActivity", "IN ADD CARD -- "+ id );
            //int value = getCardsValue(name);
            //displayHandCount(value);
            cardImage.setImageResource(id);
            game.incPlayerHits();
        }
        else if (game.getPlayerHits()==2) {
            ImageView cardImage = (ImageView) findViewById(R.id.card9);
            cardImage.setImageResource(R.drawable.threeclub);
            game.incPlayerHits();
        }

        else if (game.getPlayerHits()==3) {
            ImageView cardImage = (ImageView) findViewById(R.id.card10);
            cardImage.setImageResource(R.drawable.jackdiamon);
            game.incPlayerHits();
            buttonHit.setClickable(false);
            buttonHit.setBackgroundColor(getResources().getColor(R.color.lightBlue));
        }
        else
        {
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

