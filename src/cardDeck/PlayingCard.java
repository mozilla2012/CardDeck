package cardDeck;

/**
 * Implements a playing card object
 */
public class PlayingCard extends Card implements Comparable {

    private CardValue cardValue;
    private SuitValue suit;
    private int valueInteger;
    private String suitName;
    private boolean trump;
    private boolean aceHigh;

    public PlayingCard() {
        this(CardValue.UNKNOWN, SuitValue.UNKNOWN, CardStatus.UNKNOWN, false);
    }

    public PlayingCard(final int newValue, final SuitValue newSuit) {
        this(newValue, newSuit.name, CardStatus.UNKNOWN, false);
    }

    public PlayingCard(final int newValue, final String newSuit) {
        this(newValue, newSuit, CardStatus.UNKNOWN, false);
    }

    public PlayingCard(final int newValue, final String newSuit, boolean newAceHigh) {
        this(newValue, newSuit, CardStatus.UNKNOWN, newAceHigh);
    }

    public PlayingCard(final int newValue, final String newSuit, CardStatus newStatus) {
        this(newValue, newSuit, newStatus, false);
    }

    public PlayingCard(final int newValue, final String newSuit, CardStatus newStatus, boolean newAceHigh) {
        this.status = newStatus;
        this.aceHigh = newAceHigh;
        this.setCardValue(CardValue.getCardValue(newValue));
        this.suit = SuitValue.getSuitValue(newSuit);
        this.valueInteger = newValue;
        this.suitName = newSuit;

        if (this.getCardValue() == CardValue.JOKER) {
            this.name = this.getStringValue();
        } else {
            this.name = this.getStringValue() + " of " + this.getStringSuit();
        }
    }

    public PlayingCard(final CardValue newValue, final String newSuit) {
        this(newValue.value, newSuit, CardStatus.UNKNOWN, false);
    }

    public PlayingCard(final CardValue newValue, final SuitValue newSuit) {
        this(newValue, newSuit, CardStatus.UNKNOWN, false);
    }

    public PlayingCard(final CardValue newValue, final SuitValue newSuit, CardStatus newStatus, boolean newAceHigh) {
        this.status = newStatus;
        this.aceHigh = newAceHigh;
        this.setCardValue(newValue);
        this.suit = newSuit;
        this.valueInteger = newValue.value;
        this.suitName = newSuit.name;

        if (this.getCardValue() == CardValue.JOKER) {
            this.name = this.getStringValue();
        } else {
            this.name = this.getStringValue() + " of " + this.getStringSuit();
        }
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public SuitValue getSuit() {
        return suit;
    }

    public boolean isTrump() {
        return trump;
    }

    public boolean isAceHigh() {
        return aceHigh;
    }

    public void setCardValue(final CardValue newValue) {
        if (aceHigh && newValue == CardValue.ACE_LOW) {
            this.cardValue = CardValue.ACE_HIGH;
        } else if(!aceHigh && newValue == CardValue.ACE_HIGH) {
            this.cardValue = CardValue.ACE_LOW;
        } else {
            this.cardValue = newValue;
        }
        this.valueInteger = this.cardValue.value;
    }

    public void setCardValue(final int newValue) {
        if (aceHigh && newValue == CardValue.ACE_LOW.value) {
            this.cardValue = CardValue.ACE_HIGH;
        } else if(!aceHigh && newValue == CardValue.ACE_HIGH.value) {
            this.cardValue = CardValue.ACE_LOW;
        } else {
            this.cardValue = CardValue.getCardValue(newValue);
        }
        this.valueInteger = newValue;
    }

    public void setSuit(final SuitValue newSuit) {
        this.suit = newSuit;
        this.suitName = newSuit.name;
    }

    public void setSuit(final String newSuit) {
        if (newSuit != null) {
            setSuit(SuitValue.getSuitValue(newSuit));
        }
        if (this.suit == SuitValue.UNKNOWN) {
            this.suitName = newSuit;
        }
    }

    public void setTrump(final boolean newTrump) {
        this.trump = newTrump;
    }

    public void setTrump(final String trumpSuit) {
        this.setTrump(this.getStringSuit().equals(trumpSuit));
    }

    public void setTrump(final SuitValue trumpSuit) {
        this.setTrump(this.getSuit() == trumpSuit);
    }

    public void setAceHigh(final boolean newAceHigh) {
        this.aceHigh = newAceHigh;
        if (this.getCardValue() == CardValue.ACE_LOW && newAceHigh) {
            this.setCardValue(CardValue.ACE_HIGH);
        }
        else if (this.getCardValue() == CardValue.ACE_HIGH && !newAceHigh) {
            this.setCardValue(CardValue.ACE_LOW);
        }
    }

    public String getStringValue(){
        return (this.getCardValue() == CardValue.UNKNOWN)
                ? String.format("%s", this.valueInteger)
                : this.getCardValue().name;
    }

    public String getStringSuit(){
        return (this.getSuit() == SuitValue.UNKNOWN)
                ? this.suitName
                : this.getSuit().name;
    }

    @Override
    public String toString() {
        return "PlayingCard{" +
                "cardValue=" + cardValue +
                ", suit=" + suit +
                ", status=" + status +
                ", trump=" + trump +
                ", aceHigh=" + aceHigh +
                '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj.getClass() != PlayingCard.class){
            return false;
        }
        PlayingCard comp = (PlayingCard)obj;
        return (comp.getSuit() == this.getSuit() && comp.getCardValue() == this.getCardValue());
    }

    /**
     * This may need to be overwritten depending on the game. This allows for a trump suit and ace high/low comparison.
     * @param obj A playing card object to be compared to
     * @return 0 if equal, -1 if this is lower than obj, 1 if this is greater than obj
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException if the specified object's type prevents it from being compared to this object.
     */
    @Override
    public int compareTo(final Object obj) throws NullPointerException, ClassCastException {
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
        if (this.getCardValue().value > comp.getCardValue().value) {
            return 1;
        }
        if (this.getCardValue().value < comp.getCardValue().value) {
            return -1;
        }
        return 0;
    }
}
