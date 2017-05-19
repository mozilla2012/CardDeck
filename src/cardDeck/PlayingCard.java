package cardDeck;

/**
 * Implements a playing card object
 */
public class PlayingCard extends Card implements Comparable {

    private int value;
    private int suit;
    private String stringSuit;
    private boolean trump;
    private boolean aceHigh;
    private static final int UNKNOWN_VALUE = -1;

    public PlayingCard() {
        this(UNKNOWN_VALUE, UNKNOWN_VALUE, CardStatus.UNKNOWN, false);
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
        this.setValue(newValue);
        String suitName = SuitValues.getValueName(newSuit);
        this.stringSuit = suitName.equals(SuitValues.UNKNOWN.name) ? "Suit_" + newSuit : suitName;

        if (this.getValue() == CardValues.JOKER.value) {
            this.name = this.getStringValue();
        } else {
            this.name = this.getStringValue() + " of " + this.getStringSuit();
        }
    }

    public PlayingCard(int newValue, String newSuit, CardStatus newStatus, boolean newAceHigh) {
        if (newSuit != null) {
            this.suit = SuitValues.getSuitValue(newSuit).value;
        }
        this.stringSuit = newSuit;
        this.status = newStatus;
        this.aceHigh = newAceHigh;
        this.setValue(newValue);

        if (this.getValue() == CardValues.JOKER.value) {
            this.name = this.getStringValue();
        } else {
            this.name = this.getStringValue() + " of " + this.getStringSuit();
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
        if (aceHigh && newValue == CardValues.ACE_LOW.value) {
            this.value = CardValues.ACE_HIGH.value;
        } else if(!aceHigh && newValue == CardValues.ACE_HIGH.value) {
            this.value = CardValues.ACE_LOW.value;
        } else {
            this.value = newValue;
        }
    }

    public void setSuit(int newSuit) {
        this.suit = newSuit;
        String suitName = SuitValues.getValueName(newSuit);
        this.stringSuit = suitName.equals(SuitValues.UNKNOWN.name) ? "Suit_" + newSuit : suitName;
    }

    public void setSuit(String newSuit) {
        if (newSuit != null) {
            this.suit = SuitValues.getSuitValue(newSuit).value;
        }
        this.stringSuit = newSuit;
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
        if (this.getValue() == CardValues.ACE_LOW.value && newAceHigh) {
            this.setValue(CardValues.ACE_HIGH.value);
        }
        else if (this.getValue() == CardValues.ACE_HIGH.value && !newAceHigh) {
            this.setValue(CardValues.ACE_LOW.value);
        }
    }

    public String getStringValue(){
        String cardValueName = CardValues.getValueName(this.getValue());
        return cardValueName.equals(CardValues.UNKNOWN.name)
                ? Integer.toString(this.getValue())
                : cardValueName;
    }

    public String getStringSuit(){
        return stringSuit;
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
     * This may need to be overwritten depending on the game. This allows for a trump suit and ace high/low comparison.
     * @param obj A playing card object to be compared to
     * @return 0 if equal, -1 if this is lower than obj, 1 if this is greater than obj
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException if the specified object's type prevents it from being compared to this object.
     */
    @Override
    public int compareTo(Object obj) throws NullPointerException, ClassCastException {
        if (obj == null){
            throw new NullPointerException("Comparable object cannot be null.");
        }
        if (obj.getClass() != PlayingCard.class){
            throw new ClassCastException("Comparable object must be of PlayingCard type.");
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
        return 0;
    }

    public enum CardValues {
        ACE_LOW(1, "Ace"),
        TWO(2, "Two"),
        THREE(3, "Three"),
        FOUR(4, "Four"),
        FIVE(5, "Five"),
        SIX(6, "Six"),
        SEVEN(7, "Seven"),
        EIGHT(8, "Eight"),
        NINE(9, "Nine"),
        TEN(10, "Ten"),
        JACK(11, "Jack"),
        QUEEN(12, "Queen"),
        KING(13, "King"),
        ACE_HIGH(14, "Ace"),
        JOKER(15, "Joker"),
        UNKNOWN(UNKNOWN_VALUE, "Unknown");

        public int value;
        public String name;

        CardValues(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public static String getValueName(int value) {
            return getCardValue(value).name;
        }

        public static CardValues getCardValue(int value) {
            for (CardValues c : CardValues.values()) {
                if (c.value == value) {
                    return c;
                }
            }
            return UNKNOWN;
        }
    }

    public enum SuitValues {
        SPADES(1, "Spades"),
        HEARTS(2, "Hearts"),
        DIAMONDS(3, "Diamonds"),
        CLUBS(4, "Clubs"),
        UNKNOWN(UNKNOWN_VALUE, "Unknown");

        public int value;
        public String name;

        SuitValues(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public static String getValueName(int value) {
            return getSuitValue(value).name;
        }

        public static SuitValues getSuitValue(int value) {
            for (SuitValues s : SuitValues.values()) {
                if (s.value == value) {
                    return s;
                }
            }
            return UNKNOWN;
        }

        public static SuitValues getSuitValue(String name) {
            for (SuitValues s : SuitValues.values()) {
                if (s.name.equals(name)) {
                    return s;
                }
            }
            return UNKNOWN;
        }
    }
}
