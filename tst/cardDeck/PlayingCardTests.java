package cardDeck;

import org.junit.*;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class PlayingCardTests {

    private PlayingCard ace;
    private PlayingCard two;
    private PlayingCard three;
    private PlayingCard four;
    private PlayingCard five;
    private PlayingCard six;
    private PlayingCard seven;
    private PlayingCard eight;
    private PlayingCard nine;
    private PlayingCard ten;
    private PlayingCard jack;
    private PlayingCard queen;
    private PlayingCard king;
    private PlayingCard joker;
    private PlayingCard wut;

    @Test
    public void printNames(){
        ace = new PlayingCard(1, 1);
        two = new PlayingCard(2, 3);
        three = new PlayingCard(3, 2);
        four = new PlayingCard(4, 3);
        five = new PlayingCard(5, 4);
        six = new PlayingCard(6, 1);
        seven = new PlayingCard(7, 3);
        eight = new PlayingCard(8, 4);
        nine = new PlayingCard(9, 3);
        ten = new PlayingCard(10, 4);
        jack = new PlayingCard(11, 1);
        queen = new PlayingCard(12, 1);
        king = new PlayingCard(13, 2);
        joker = new PlayingCard(15, null);
        wut = new PlayingCard(0, 5);
        assertEquals("Ace of Spades", ace.getName());
        assertEquals("Two of Diamonds", two.getName());
        assertEquals("Three of Hearts", three.getName());
        assertEquals("Four of Diamonds", four.getName());
        assertEquals("Five of Clubs", five.getName());
        assertEquals("Six of Spades", six.getName());
        assertEquals("Seven of Diamonds", seven.getName());
        assertEquals("Eight of Clubs", eight.getName());
        assertEquals("Nine of Diamonds", nine.getName());
        assertEquals("Ten of Clubs", ten.getName());
        assertEquals("Jack of Spades", jack.getName());
        assertEquals("Queen of Spades", queen.getName());
        assertEquals("King of Hearts", king.getName());
        assertEquals("Joker", joker.getName());
        assertEquals("0 of Suit_5", wut.getName());
    }

    @Test
    public void printNamesStrings(){
        ace = new PlayingCard(1, "Hearts");
        jack = new PlayingCard(11, "Clubs");
        queen = new PlayingCard(12, "Spades");
        king = new PlayingCard(13, "Diamonds");
        two = new PlayingCard(2, "Stars");
        assertEquals("Ace of Hearts", ace.getName());
        assertEquals("Jack of Clubs", jack.getName());
        assertEquals("Queen of Spades", queen.getName());
        assertEquals("King of Diamonds", king.getName());
        assertEquals("Two of Unknown_Suit", two.getName());
    }

    @Test
    public void setTrump(){
        ace = new PlayingCard(1, "Hearts");
        jack = new PlayingCard(11, "Clubs");
        ace.setTrump(true);
        jack.setTrump("Clubs");
        assert(ace.isTrump());
        assert(jack.isTrump());
        jack.setTrump("Hearts");
        assert(!jack.isTrump());
    }

    @Test
    public void compareCards(){
        ace = new PlayingCard(1, "Hearts");
        wut = new PlayingCard(1, "Hearts");

        // Card should not equal some other object
        try {
            ace.compareTo(5);
            fail();
        } catch (ClassCastException e) {
            // Expected. Continue.
        }

        // Card should not be null
        try {
            ace.compareTo(null);
            fail();
        } catch (NullPointerException e) {
            // Expected. Continue.
        }

        // Card should compare to itself.
        assert(ace.compareTo(ace) == 0);

        // Same suit, same value should be equal
        assert(ace.equals(wut));

        // Assert the same object is equal
        assert(ace.equals(ace));

        // Assert an object is not equal to some other object
        assertFalse(ace.equals(5));

        // Change suit, but compare should be the same
        wut.setSuit("Diamonds");
        assert(!ace.equals(wut));
        assertEquals(0, ace.compareTo(wut));

        // Make one trump
        wut.setTrump(true);
        assertEquals(-1, ace.compareTo(wut));
        wut.setTrump(false);

        // Make the other trump
        ace.setTrump(true);
        assertEquals(1, ace.compareTo(wut));
        ace.setTrump(false);

        // Test ace low
        wut.setValue(2);
        assertEquals(-1, ace.compareTo(wut));

        // Test ace high
        ace.setAceHigh(true);
        assertEquals(1, ace.compareTo(wut));
        assertEquals(14, ace.getValue());

        // Affrim setting ace low fixes value
        ace.setAceHigh(false);
        assertEquals(1, ace.getValue());
    }

    @Test
    public void cardGettersAndSetters() {
        String name = "Test Card Name";
        CardStatus status = CardStatus.ACTIVATED;
        Card card = new Card();
        Card card2 = new Card(name);
        card.setName(name);
        card.setStatus(status);
        assertEquals(card.getName(), name);
        assertEquals(card2.getName(), name);
        assertEquals(card.getStatus(), status);
    }
}