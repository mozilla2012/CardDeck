package cardDeck;

/**
 * Implements a generic card type that can be extended for a variety of games or uses.
 */
public class Card {

    /** The name of the card, e.g. Blue Eyes White Dragon or Ace of Clubs. */
    protected String name;
    /** The CardStatus of the card, e.g. FACE_UP, DISCARDED, etc */
    protected CardStatus status;

    /**
     * Empty constructor initializes name to null and status to UNKNOWN.
     */
    public Card() {
        this("", CardStatus.UNKNOWN);
    }

    /**
     * Constructor that sets the name of the card. Status is UNKNOWN.
     * @param newName the name of the card
     */
    public Card(final String newName) {
        this(newName, CardStatus.UNKNOWN);
    }

    /**
     * Constructor that sets the name and CardStatus of the card.
     * @param newName the name of the card
     * @param newStatus the CardStatus of the card, e.g. FACE_UP, DISCARDED, etc
     */
    public Card(final String newName, final CardStatus newStatus) {
        this.name = newName;
        this.status = newStatus;
    }

    /**
     * Get the name of the card.
     * @return the name of the card.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the CardStatus of the card.
     * @return the CardStatus of the card.
     */
    public CardStatus getStatus() {
        return status;
    }

    /**
     * Set the name of the card.
     * @param name the new name of the card.
     */
    public void setName(final String name)
    {
        this.name = name;
    }

    /**
     * Set the CardStatus of the card.
     * @param status the new CardStatus of the card.
     */
    public void setStatus(final CardStatus status) {
        this.status = status;
    }

    /**
     * Takes an object and returns if it is a card equal to this.
     * @param obj an object that may or may not equal this.
     * @return true if this card is equal, or false if not.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Card)) {
            return false;
        }
        Card card = (Card) obj;
        return (this.name.equals(card.getName()) && this.status.equals(card.getStatus()));
    }
}
