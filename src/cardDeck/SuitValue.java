package cardDeck;

/**
 * This class represents the suit of a playing card. Can be one of four types, or unknown.
 */
public enum SuitValue {
    /** Spades. */
    SPADES("Spades"),
    /** Hearts. */
    HEARTS("Hearts"),
    /** Diamonds. */
    DIAMONDS("Diamonds"),
    /** Clubs. */
    CLUBS("Clubs"),
    /** Unknown suit. */
    UNKNOWN("Unknown");

    /** The pretty name for the suit. */
    public String name;

    /**
     * Private constructor for the enum.
     * @param name the name of the suit.
     */
    SuitValue(String name) {
        this.name = name;
    }

    /**
     * Return the enum value of a suit given its string name.
     * @param name The string name of the suit, e.g. "Spades"
     * @return the enum value of the suit, e.g. SPADES
     */
    public static SuitValue getSuitValue(String name) {
        for (SuitValue s : SuitValue.values()) {
            if (s.name.equals(name)) {
                return s;
            }
        }
        return UNKNOWN;
    }
}
