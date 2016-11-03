package cardDeck;


/**
 * Created by Evan Arnold on 11/3/2016.
 */
public class Card implements Comparable{

    private int value;
    private int suit;
    private CardStatus status;
    private boolean trump;
    private static final int UNKNOWN = -1;
    //Todo: Is ace high or low?

    public Card() {
        this.value = UNKNOWN;
        this.suit = UNKNOWN;
        this.status = CardStatus.UNKNOWN;
        this.trump = false;
    }

    public Card(int value, int suit) {
        this.value = value;
        this.suit = suit;
        this.status = CardStatus.UNKNOWN;
        this.trump = false;
    }

    public Card(int value, int suit, CardStatus status) {
        this.value = value;
        this.suit = suit;
        this.status = status;
        this.trump = false;
    }

    public Card(int value, int suit, boolean trump) {
        this.value = value;
        this.suit = suit;
        this.status = CardStatus.UNKNOWN;
        this.trump = trump;
    }

    public Card(int value, int suit, CardStatus status, boolean trump) {
        this.value = value;
        this.suit = suit;
        this.status = status;
        this.trump = trump;
    }

    public int getValue() {
        return value;
    }

    public int getSuit() {
        return suit;
    }

    public CardStatus getStatus() {
        return status;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public boolean isTrump() {
        return trump;
    }

    public void setTrump(boolean trump) {
        this.trump = trump;
    }

    //Todo:
    public String getStringValue(){
        return "todo";
    }

    public String getStringSuit(){
        switch (this.getSuit()) {
            case -1: return "Unknown_Suit";
            case 1: return "Spades";
            case 2: return "Hearts";
            case 3: return "Diamonds";
            case 4: return "Clubs";
            default: return "Suit_" + String.valueOf(this.getSuit());
        }
    }

    //Todo: Make this print pretty text
    @Override
    public String toString() {
        return "Card{" +
                "value=" + value +
                ", suit=" + suit +
                ", status=" + status +
                ", trump=" + trump +
                '}';
    }

    public String prettyPrint() {
        return this.getStringValue() + " of " + this.getStringSuit();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Card.class){
            return false;
        }
        Card comp = (Card)obj;
        return (comp.getSuit() == this.getSuit() && comp.getValue() == this.getValue());
    }


    //Todo: Is ace high or low?
    @Override
    public int compareTo(Object obj) {
        if (obj.getClass() != Card.class){
            return -2;
        }
        if (this.equals(obj)) {
            return 0;
        }
        Card comp = (Card)obj;
        if (this.isTrump() && !comp.isTrump()) {
            return 1;
        }
        if (!this.isTrump() && comp.isTrump()) {
            return -1;
        }
        if (this.getValue() > comp.getValue()) {
            return 1;
        }
        if (this.getValue() < comp.getValue()) {
            return -1;
        }
        if (this.getValue() == comp.getValue()) {
            return 0;
        }
        return -2;
    }
}
