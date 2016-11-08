package cardDeck;

/**
 * Implements a generic card type that can be extended for a variety of games or uses.
 */
public class Card {

    protected String name;
    protected CardStatus status;

    public Card() {
        this("", CardStatus.UNKNOWN);
    }

    public Card(String newName) {
        this(newName, CardStatus.UNKNOWN);
    }

    public Card(String newName, CardStatus newStatus) {
        this.name = newName;
        this.status = newStatus;
    }

    public String getName()
    {
        return name;
    }

    public CardStatus getStatus() {
        return status;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }
}
