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
        setContentView(R.layout.activity_main);

    }

    public void onStart( ) {
        super.onStart();
        Log.w( "MainActivity", "MainActivity onStart called"  );
        //displayHandCount(0);
    }

    public void distributeCard(Player player) {
        // Randomly grab card from deck in the game, then give it to the player and assign it to display
        Card card = game.randomCard();
        player.addCard(card);
    }

    public void generateDeck(ArrayList<Card> Deck) {
        // Want to go through each card in the deck and assign png name according to card name
        for(Card card : Deck) {

        }
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

