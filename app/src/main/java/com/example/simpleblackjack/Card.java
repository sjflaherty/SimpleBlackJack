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
    protected static final List<String> Suites = Collections.unmodifiableList(Arrays.asList("Heart", "Club", "Diamond", "Spade"));
    protected static final List<String> FaceCards = Collections.unmodifiableList(Arrays.asList("King", "Queen", "Jack", "Ace"));
    protected static final List<String> Values = Collections.unmodifiableList(Arrays.asList("Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"));
    protected static int Value;
    protected static String Suite;
    // Need to be able to assign filenames for each card depending on value and suite
    protected static String Filename;
    // Card will either be in play or in the deck
    protected static boolean InPlay;

    /*
    / Card constructor
    / Parameters - value - value of card
    / suite - suite of card
    / Returns : None
     */
    Card(int value, String suite) {

        this.Suite = suite;
        this.Value = value;
        this.InPlay = false;
    }

    public void updateValue(int value) {
        this.Value = value;
    }

    public void updateSuite(String suite) {
        this.Suite = suite;
    }

    public void updateFilename(String filename) {
        this.Filename = filename;
    }

    public String checkFilename () {
        return this.Filename;
    }



}
