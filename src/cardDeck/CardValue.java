package cardDeck;

/**
 * This class represents the value of a playing card.
 */
public enum CardValue {
    /** 1, if aces are low. */
    ACE_LOW(1, "Ace"),
    /** 2. */
    TWO(2, "Two"),
    /** 3. */
    THREE(3, "Three"),
    /** 4. */
    FOUR(4, "Four"),
    /** 5. */
    FIVE(5, "Five"),
    /** 6. */
    SIX(6, "Six"),
    /** 7. */
    SEVEN(7, "Seven"),
    /** 8. */
    EIGHT(8, "Eight"),
    /** 9. */
    NINE(9, "Nine"),
    /** 10. */
    TEN(10, "Ten"),
    /** Jack. */
    JACK(11, "Jack"),
    /** Queen. */
    QUEEN(12, "Queen"),
    /** King. */
    KING(13, "King"),
    /** HIgh ace, if aces are high. */
    ACE_HIGH(14, "Ace"),
    /** Joker */
    JOKER(15, "Joker"),
    /** Unknown */
    UNKNOWN(-1, "Unknown");

    /** The numeric value of the card's value, for comparisons. */
    public int value;
    /** The String name of the card, for printing. */
    public String name;

    /**
     * Private constructor for the enum.
     * @param value the numeric value of the card's value
     * @param name the String name of the card, for printing
     */
    CardValue(final int value, final String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * Get the name of a value given the numeric value. E.g, 11 returns "Jack"
     * @param value the numeric value to be translated to a String name
     * @return the name of the numeric value for printing
     */
    public static String getValueName(final int value) {
        return getCardValue(value).name;
    }

    /**
     * Return the enum value of a CardValue given its integer value.
     * @param value the numeric value to be translated into a CardValue
     * @return the CardValue related to the given numeric value
     */
    public static CardValue getCardValue(final int value) {
        for (CardValue c : CardValue.values()) {
            if (c.value == value) {
                return c;
            }
        }
        return UNKNOWN;
    }
}

