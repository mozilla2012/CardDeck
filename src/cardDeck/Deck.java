package cardDeck;

import java.util.ArrayList;

/**
 * Created by Evan Arnold on 11/8/2016.
 */
public class Deck {

    private String name;
    private ArrayList<Card> pile;

    // TODO: 11/8/2016
    // Do we even need this?
    private boolean faceDown;

    public Deck(String newName) {
        this(newName, null, true);
    }

    public Deck(ArrayList<Card> newPile) {
        this("", newPile, true);
    }

    public Deck(String newName, ArrayList<Card> newPile) {
        this(newName, newPile, true);
    }

    public Deck(String newName, ArrayList<Card> newPile, boolean newFacedown) {
        this.name = newName;
        this.pile = newPile;
        this.faceDown = newFacedown;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getPile() {
        return pile;
    }

    public void setPile(ArrayList<Card> pile) {
        this.pile = pile;
    }

    public boolean isFaceDown() {
        return faceDown;
    }

    public void setFaceDown(boolean faceDown) {
        this.faceDown = faceDown;
    }

    public void flipDeck() {
        this.faceDown = !faceDown;
    }

    public void shuffle() {
        // TODO: 11/8/2016
    }

    public Card draw(boolean top) {
        return drawN(1, top).get(0);
    }

    public ArrayList<Card> drawN(int numCards, boolean top) {
        ArrayList<Card> subset = new ArrayList<>();
        if (top) {
            for (int i = 0; i < numCards; i++) {
                subset.add(pile.get(i));
            }
        }
        else {
            for (int i = 0; i < numCards; i++) {
                subset.add(pile.get(pile.size()-i-1));
            }
        }
        return subset;
    }

    public void peek(boolean top) {
        // TODO: 11/8/2016
        //return peekN(1, top)
    }

    public void peekN(int numCards, boolean top) {
        // TODO: 11/8/2016
        //return some cards
    }
}
