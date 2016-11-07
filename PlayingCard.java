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
        new PlayingCard(UNKNOWN, UNKNOWN, CardStatus.UNKNOWN, false);
    }

    public PlayingCard(int newValue, int newSuit) {
        new PlayingCard(newValue, newSuit, CardStatus.UNKNOWN, false);
    }

    public PlayingCard(int newValue, int newSuit, boolean newAceHigh) {
        new PlayingCard(newValue, newSuit, CardStatus.UNKNOWN, newAceHigh);
    }

    public PlayingCard(int newValue, int newSuit, CardStatus newStatus) {
        new PlayingCard(newValue, newSuit, newStatus, false);
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
    }

    //Todo:
    public String getStringValue(){
        switch (this.getValue()) {

        }
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

    public String prettyPrint() {
        return this.getStringValue() + " of " + this.getStringSuit();
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
     * This may need to be overwriten depending on the game. This allows for a trump suit and ace high/low comparison
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
        return getStringSuit();
    }
}
