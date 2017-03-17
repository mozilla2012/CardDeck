package cardDeck;

/**
 * Implements a playing card object
 */
public class PlayingCard extends Card implements Comparable {

    private int value;
    private int suit;
    private boolean trump;
    private boolean aceHigh;
    private static final int UNKNOWN = -1;

    public PlayingCard() {
        this(UNKNOWN, UNKNOWN, CardStatus.UNKNOWN, false);
    }

    public PlayingCard(int newValue, int newSuit) {
        this(newValue, newSuit, CardStatus.UNKNOWN, false);
    }

    public PlayingCard(int newValue, String newSuit) {
        this(newValue, newSuit, CardStatus.UNKNOWN, false);
    }

    public PlayingCard(int newValue, int newSuit, boolean newAceHigh) {
        this(newValue, newSuit, CardStatus.UNKNOWN, newAceHigh);
    }

    public PlayingCard(int newValue, String newSuit, boolean newAceHigh) {
        this(newValue, newSuit, CardStatus.UNKNOWN, newAceHigh);
    }

    public PlayingCard(int newValue, int newSuit, CardStatus newStatus) {
        this(newValue, newSuit, newStatus, false);
    }

    public PlayingCard(int newValue, String newSuit, CardStatus newStatus) {
        this(newValue, newSuit, newStatus, false);
    }

    public PlayingCard(int newValue, int newSuit, CardStatus newStatus, boolean newAceHigh) {
        this.suit = newSuit;
        this.status = newStatus;
        this.aceHigh = newAceHigh;
        if (aceHigh && newValue == 1) {
            this.value = 14;
        }
        else {
            this.value = newValue;
        }
    }

    public PlayingCard(int newValue, String newSuit, CardStatus newStatus, boolean newAceHigh) {
        if (newSuit != null) {
            switch (newSuit) {
                case "Spades": this.suit = 1; break;
                case "Hearts": this.suit = 2; break;
                case "Diamonds": this.suit = 3; break;
                case "Clubs": this.suit = 4; break;
                default: this.suit = -1;
            }
        }
        this.status = newStatus;
        this.aceHigh = newAceHigh;
        if (aceHigh && newValue == 1) {
            this.value = 14;
        }
        else {
            this.value = newValue;
        }
    }

    public int getValue() {
        return value;
    }

    public int getSuit() {
        return suit;
    }

    public boolean isTrump() {
        return trump;
    }

    public boolean isAceHigh() {
        return aceHigh;
    }

    public void setValue(int newValue) {
        if (aceHigh && newValue == 1) {
            this.value = 14;
        }
        else {
            this.value = newValue;
        }
    }

    public void setSuit(int newSuit) {
        this.suit = newSuit;
    }

    public void setSuit(String newSuit) {
        switch(newSuit) {
            case "Spades": this.suit = 1; break;
            case "Hearts": this.suit = 2; break;
            case "Diamonds": this.suit = 3; break;
            case "Clubs": this.suit = 4; break;
            default: this.suit = -1;
        }
    }

    public void setTrump(boolean newTrump) {
        this.trump = newTrump;
    }

    public void setTrump(String trumpSuit) {
        if (this.getStringSuit().equals(trumpSuit)) {
            this.setTrump(true);
        }
        else {
            this.setTrump(false);
        }
    }

    public void setAceHigh(boolean newAceHigh) {
        this.aceHigh = newAceHigh;
        if (getValue() == 1 && newAceHigh) {
            this.setValue(14);
        }
        else if (getValue() == 14 && !newAceHigh) {
            this.setValue(1);
        }
    }

    public String getStringValue(){
        switch (this.getValue()) {
            case 1:
            case 14: return "Ace";
            case 11: return "Jack";
            case 12: return "Queen";
            case 13: return "King";
            case 15: return "Joker";
            case 2: return "Two";
            case 3: return "Three";
            case 4: return "Four";
            case 5: return "Five";
            case 6: return "Six";
            case 7: return "Seven";
            case 8: return "Eight";
            case 9: return "Nine";
            case 10: return "Ten";
            default: return Integer.toString(value);
        }
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

    @Override
    public String toString() {
        return "PlayingCard{" +
                "value=" + value +
                ", suit=" + suit +
                ", status=" + status +
                ", trump=" + trump +
                ", aceHigh=" + aceHigh +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != PlayingCard.class){
            return false;
        }
        PlayingCard comp = (PlayingCard)obj;
        return (comp.getSuit() == this.getSuit() && comp.getValue() == this.getValue());
    }

    /**
     * This may need to be overwritten depending on the game. This allows for a trump suit and ace high/low comparison
     * @param obj A playing card object to be compared to
     * @return 0 if equal, -1 if this is lower than obj, 1 if this is greater than obj, -2 if error
     */
    @Override
    public int compareTo(Object obj) {
        if (obj.getClass() != PlayingCard.class){
            return -2;
        }
        if (this.equals(obj)) {
            return 0;
        }
        PlayingCard comp = (PlayingCard)obj;
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

    @Override
    public String getName() {
        // If this card is the joker
        if (this.getValue() == 15) {
            return this.getStringValue();
        }
        return this.getStringValue() + " of " + this.getStringSuit();
    }
}
