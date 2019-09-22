/*
/ Authors: Sarah Flaherty, Patrick Sacchet
/ Date: 9/21/19
/ CS 482
 */

package com.example.simpleblackjack;
import java.util.*;

/*
/ Class: Card - Represents a card object in the game of blackjack
 */
public class Card {
    // Each card will be one of these suites, and will have a value assigned to it
    protected static final List<String> Suites = Collections.unmodifiableList(Arrays.asList("Hearts", "Clubs", "Diamonds", "Spades"));
    protected static final List<String> FaceCards = Collections.unmodifiableList(Arrays.asList("King", "Queen", "Jack", "Ace"));
    protected static int Value;
    protected static String Suite;
    // Card will either be in play or in the deck
    protected static boolean InPlay;

    /*
    / Card constructor
    / Parameters - value - value of card
    / suite - suite of card
    / Returns : None
     */
    Card(int value, String suite) {
        if (Suites.contains(suite)) {
            Suite = suite;
        }
        else {
            // If they didn't pass a valid suite just pick one randomly
            Random rand = new Random();
            Suite = Suites.get(rand.nextInt(Suites.size()));
        }
        if (value > 10) {
            // If value passed in is greater than 10 just randomly assign value
            Random rand = new Random();
            int max = 10;
            int min = 1;
            Value = rand.nextInt((max - min) + 1) + min;
        }
        InPlay = false;
    }



}
