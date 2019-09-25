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

public class MainActivity extends AppCompatActivity {

    public static Game game;
    protected static String suit;
    protected static String rank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new Game( );
        setContentView(R.layout.activity_main);

    }

    public void onStart( ) {
        super.onStart();
        Log.w( "MainActivity", "MainActivity onStart called"  );
        displayHandCount();
    }

    public void stand( View v ) {
        Log.w("MainActivity", "Inside stand");
        Button buttonStand = (Button) findViewById(R.id.Stand);
        buttonStand.setClickable(false);
        buttonStand.setBackgroundColor(getResources().getColor(R.color.grey));

        Button buttonHit = (Button) findViewById(R.id.Hit);
        buttonHit.setClickable(false);
        buttonHit.setBackgroundColor(getResources().getColor(R.color.grey));

        }


    public String getDrawableCard() //eventually will take the suit and rank
    {
        suit= "jack";
        rank = "heart";
        String drawID = "R.drawable."+suit+rank;
        Log.w( "MainActivity", "Got Card -- "+ drawID );
        return drawID;
    }

    public void addCard( View v ) {
        Log.w( "MainActivity", "Inside addCard" );
        Button buttonHit = (Button) findViewById(R.id.Hit);

        if (game.getPlayerHits()==1) {
            ImageView cardImage = (ImageView) findViewById(R.id.card8);
            int id = getResources().getIdentifier(getDrawableCard(), "drawable", getPackageName());
            Log.w( "MainActivity", "IN ADD CARD -- "+ id );
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
            buttonHit.setBackgroundColor(getResources().getColor(R.color.grey));
        }
        else
        {
            buttonHit.setClickable(false);
            buttonHit.setBackgroundColor(getResources().getColor(R.color.grey));
        }
    }



    /*
    / Returns the total sum of the hand
    / Parameters - None
    / Returns : None
    */
    public void displayHandCount() {
        String handsum = getCardsValue();
        //String playerSum = Integer.toString(handsum);
        TextView handCountBox = (TextView) findViewById(R.id.handCount);
        handCountBox.setText(handsum);
    }

    /*
    / Returns the integer value of the card
    / Parameters - None
    / Returns : Int value sum of cards
    */
    public String getCardsValue() {

        int sum =0;

        String[] cardIDs;
        cardIDs = new String[]{"R.id.card6", "R.id.card7", "R.id.card8", "R.id.card9", "R.id.card10"};
        //for (int i = 0; i < cardIDs.length; i++) {
        //int temp = getResources().getIdentifier(cardIDs[i], "id", getPackageName());
        //    ImageView cardImage = (ImageView) findViewById(temp);

        String cardName = String.valueOf(R.id.card5);
            /*if (cardName.contains("two")) {
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
            else */
            if (cardName.contains("seven")) {
                sum += 7;
            }
            /*
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
        //}*/

        return cardName;
    }

    }

