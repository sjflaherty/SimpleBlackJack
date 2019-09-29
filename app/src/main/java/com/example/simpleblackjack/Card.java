/**
 * @author Sarah Flaherty
 * @author Patrick Sacchet
 * @version 1.0
 */

package com.example.simpleblackjack;
import java.util.*;

/**
 * Class will represent one card of the current game of blackjack
 */
public class Card {
    // Each card will be one of these suites, and will have a value assigned to it
    protected static final List<String> Suites = Collections.unmodifiableList(Arrays.asList("Heart", "Club", "Diamond", "Spade"));
    protected static final List<String> FaceCards = Collections.unmodifiableList(Arrays.asList("King", "Queen", "Jack", "Ace"));
    protected static final List<String> Values = Collections.unmodifiableList(Arrays.asList("Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"));
    protected int Value;
    protected String Suite;
    // Need to be able to assign filenames for each card depending on value and suite
    protected String Filename;
    // Card will either be in play or in the deck
    protected boolean InPlay;

    /**
     * Card constructor
     * @param value score value assigned to the card
     * @param suite suite that the card belongs to
     */
    Card(int value, String suite) {
        this.Suite = suite;
        this.Value = value;
        this.InPlay = false;
    }

    /**
     * Updates the current value of the card
     * @param value new value for the card to have
     */
    public void updateValue(int value) {
        Value = value;
    }

    /**
     * Updates the current suite of the card
     * @param suite new suite to be assigned to card
     */
    public void updateSuite(String suite) {
        Suite = suite;
    }

    /**
     * Updates the current filename belonging to the card
     * @param filename new filename for card
     */
    public void updateFilename(String filename) {
        Filename = filename;
    }

    /**
     * Checks the filename belonging to the card
     * @return the filename of the card
     */
    public String checkFilename () {
        return Filename;
    }

    /**
     * Checks the current value of the card
     * @return the value of the card
     */
    public int checkValue() {
        return Value;
    }
}
