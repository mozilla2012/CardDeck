package cardDeck;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This can be used to represent any stack of cards... A draw deck, a kitty, a hand, a trash pile, etc.
 */
public class Deck {

    private String name;
    private ArrayList<Card> pile; // 0 is the top of the deck, face down.
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
        Collections.shuffle(pile);
    }

    public Card draw(boolean fromTop) {
        return drawN(1, fromTop).get(0);
    }

    public ArrayList<Card> drawN(int numCards, boolean fromTop) {
        ArrayList<Card> subset = new ArrayList<>();
        if (fromTop == faceDown) {
            for (int i = 0; i < numCards; i++) {
                subset.add(pile.remove(0));
            }
        }
        else {
            for (int i = 0; i < numCards; i++) {
                subset.add(pile.remove(pile.size()-1));
            }
        }
        return subset;
    }

    public Card peek(boolean fromTop) {
        return peekN(1, fromTop).get(0);
    }

    public ArrayList<Card> peekN(int numCards, boolean fromTop) {
        ArrayList<Card> subset = new ArrayList<>();
        if (fromTop == faceDown) {
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

    public void insert(Card card, boolean onTop) {
        ArrayList<Card> array = new ArrayList<>();
        array.add(card);
        insert(array, onTop);
    }

    public void insert(ArrayList<Card> cards, boolean onTop) {
        if (onTop) {
            for (Card c : cards) {
                pile.add(0, c);
            }
        }
        else {
            pile.addAll(cards);
        }
    }

    public void insertAndShuffle(Card card) {
        ArrayList<Card> array = new ArrayList<>();
        array.add(card);
        insertAndShuffle(array);
    }

    public void insertAndShuffle(ArrayList<Card> cards) {
        pile.addAll(cards);
        this.shuffle();
    }

    public ArrayList<String> getCardNames() {
        ArrayList<String> cards = new ArrayList<>();
        for (Card c : pile) {
            cards.add(c.getName());
        }
        return cards;
    }

    @Override
    public String toString() {
        String toString = "";
        if(name != null && !name.isEmpty()) {
            toString = name + ":";
        }
        for (Card c : pile) {
            toString += c.getName() + "\n";
        }
        return toString;
    }
}
