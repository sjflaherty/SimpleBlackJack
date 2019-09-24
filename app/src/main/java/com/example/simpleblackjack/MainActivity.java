package com.example.simpleblackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new Game( );
        setContentView(R.layout.activity_main);

    }

    public void addCard( View v ) {
        Log.w( "MainActivity", "Inside addCard" );
        Button buttonHit = (Button) findViewById(R.id.Hit);
        // go to 2nd screen

        if (game.getPlayerHits()==1) {
            ImageView cardImage = (ImageView) findViewById(R.id.card8);
            cardImage.setImageResource(R.drawable.twoheart);
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
        }
        else
        {
            buttonHit.setClickable(false);
            buttonHit.setBackgroundColor(android.R.color.transparent);
        }
    }

    public void onStart( ) {
        super.onStart();
        Log.w( "MainActivity", "MainActivity onStart called"  );
    }
}