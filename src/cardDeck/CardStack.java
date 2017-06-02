package cardDeck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This can be used to represent any stack of cards... A draw deck, a kitty, a hand, a trash pile, etc.
 */
public class CardStack {

    private String name;
    private ArrayList<Card> pile; // 0 is the top of the deck, face down.
    private boolean faceDown;

    public CardStack(String newName) {
        this(newName, null, true);
    }

    public CardStack(final ArrayList<Card> newPile) {
        this(null, newPile, true);
    }

    public CardStack(final String newName, final ArrayList<Card> newPile) {
        this(newName, newPile, true);
    }

    public CardStack(final String newName, final ArrayList<Card> newPile, boolean newFacedown) {
        this.name = newName;
        this.pile = (newPile == null) ? null : (ArrayList<Card>) newPile.clone();
        this.faceDown = newFacedown;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ArrayList<Card> getPile() {
        return pile;
    }

    public void setPile(final ArrayList<Card> pile) {
        this.pile = (ArrayList<Card>) pile.clone();
    }

    public boolean isFaceDown() {
        return faceDown;
    }

    public void setFaceDown(final boolean faceDown) {
        this.faceDown = faceDown;
    }

    public void flipDeck() {
        this.faceDown = !faceDown;
    }

    public void shuffle() {
        Collections.shuffle(pile);
    }

    public Card draw() {
        return draw(true);
    }

    public Card draw(final boolean fromTop) {
        return drawN(1, fromTop).get(0);
    }

    public ArrayList<Card> drawN(final int numCards) {
        return drawN(numCards, true);
    }

    public ArrayList<Card> drawN(final int numCards, final boolean fromTop) {
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

    public Card peek() {
        return peek(true);
    }

    public Card peek(final boolean fromTop) {
        return peekN(1, fromTop).get(0);
    }

    public ArrayList<Card> peekN(final int numCards) {
        return peekN(numCards, true);
    }

    public ArrayList<Card> peekN(final int numCards, final boolean fromTop) {
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

    public void insert(final Card card, final boolean onTop) {
        ArrayList<Card> array = new ArrayList<>();
        array.add(card);
        insert(array, onTop);
    }

    /**
     * Adds the cards in the list to the top (or bottom) of the deck. Adds the first card in the list to the top, then
     * puts the second on the top, and keeps adding cards to the top of the pile.
     * @param cards the list of cards to be added
     * @param onTop true if adding cards to the top, false if adding to the bottom.
     */
    public void insert(final ArrayList<Card> cards, final boolean onTop) {
        if (onTop) {
            for (Card c : cards) {
                pile.add(0, c);
            }
        }
        else {
            pile.addAll(cards);
        }
    }

    public void insertRandomly(final Card card) {
        if (pile.size() >= 3) {
            pile.add(new Random().nextInt(pile.size() - 2) + 1, card);
        } else if (pile.size() != 0) {
            pile.add(1, card);
        } else {
            pile.add(card);
        }
    }

    public void insertRandomly(final ArrayList<Card> cards) {
        for (Card c : cards) {
            insertRandomly(c);
        }
    }

    public void insertAndShuffle(final Card card) {
        ArrayList<Card> array = new ArrayList<>();
        array.add(card);
        insertAndShuffle(array);
    }

    public void insertAndShuffle(final ArrayList<Card> cards) {
        pile.addAll(cards);
        this.shuffle();
    }

    public ArrayList<String> getCardNames() {
        if (this.getPile() == null) {
            return null;
        }
        ArrayList<String> cardNames = new ArrayList<>();
        for (Card c : this.getPile()) {
            cardNames.add(c.getName());
        }
        return cardNames;
    }

    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder();
        if(name != null && !name.isEmpty()) {
            toString = new StringBuilder(name + ":");
        }
        for (Card c : pile) {
            toString.append(c.getName()).append("\n");
        }
        return toString.toString();
    }

    //This belongs in a playing card deck class
//    public static CardStack generate52CardDesk() {
//
//    }

    //Also, setTrump and setAceHigh
}
