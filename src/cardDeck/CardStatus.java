package cardDeck;

/**
 * The status of a card. It's location, activation, etc. Really depends on the game.
 */
public enum CardStatus {
    /** The card is face up. */
    FACE_UP,
    /** The card is face down. */
    FACE_DOWN,
    /** The card is in the deck. */
    IN_DECK,
    /** The card is held by a player. */
    HELD,
    /** The card is discarded. */
    DISCARDED,
    /** The card is removed from the game. */
    OUT_OF_PLAY,
    /** The card is played. */
    PLAYED,
    /** The card is activated. */
    ACTIVATED,
    /** The card is deactivated. */
    DEACTIVATED,
    /** The card is face up. */
    UNKNOWN
}
