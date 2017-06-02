package cardDeck;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
        ace = new PlayingCard(1, "Spades");
        two = new PlayingCard(2, "Diamonds");
        three = new PlayingCard(3, "Hearts");
        four = new PlayingCard(4, "Diamonds");
        five = new PlayingCard(5, "Clubs");
        six = new PlayingCard(6, "Spades");
        seven = new PlayingCard(7, "Diamonds");
        eight = new PlayingCard(8, "Clubs");
        nine = new PlayingCard(9, "Diamonds");
        ten = new PlayingCard(10, "Clubs");
        jack = new PlayingCard(11, "Spades");
        queen = new PlayingCard(12, "Spades");
        king = new PlayingCard(13, "Hearts");
        joker = new PlayingCard(15, "");
        wut = new PlayingCard(0, "Stars");
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
        assertEquals("0 of Stars", wut.getName());
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
        assertEquals("Two of Stars", two.getName());
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
        ace.setTrump(SuitValue.DIAMONDS);
        assert(!ace.isTrump());
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
        wut.setCardValue(2);
        assertEquals(-1, ace.compareTo(wut));

        // Test ace high
        ace.setAceHigh(true);
        assertEquals(1, ace.compareTo(wut));
        assertEquals(14, ace.getCardValue().value);

        // Affirm setting ace low fixes value
        ace.setAceHigh(false);
        assertEquals(1, ace.getCardValue().value);
    }

    @Test
    public void cardGettersAndSetters() {
        String name = "Test Card Name";
        CardStatus status = CardStatus.ACTIVATED;

        Card card = new Card();
        card.setName(name);
        card.setStatus(status);
        assertEquals(card.getName(), name);
        assertEquals(card.getStatus(), status);

        card = new Card(name);
        assertEquals(card.getName(), name);

        PlayingCard card2 = new PlayingCard(1, "Spades", true);
        card2.setSuit("Hearts");
        assertEquals(SuitValue.HEARTS, card2.getSuit());
        assertEquals(card2.getStringSuit(), "Hearts");

        card2.setSuit("Stars");
        assertEquals(SuitValue.UNKNOWN, card2.getSuit());
        assertEquals("Stars", card2.getStringSuit());
    }

    @Test
    public void testToString() {
        ace = new PlayingCard(1, "Spades", true);
        assertEquals("PlayingCard{cardValue=ACE_HIGH, suit=SPADES, status=UNKNOWN, trump=false, aceHigh=true}",
                ace.toString());
    }

    @Test
    public void testJoker() {
        joker = new PlayingCard(15, SuitValue.UNKNOWN);
        assertEquals("Joker", joker.getName());
        assertEquals(SuitValue.UNKNOWN, joker.getSuit());

        joker = new PlayingCard(15, "lol fake suits");
        assertEquals("Joker", joker.getName());
        assertEquals("lol fake suits", joker.getStringSuit());

        joker = new PlayingCard(CardValue.JOKER, SuitValue.UNKNOWN);
        assertEquals("Joker", joker.getName());
        assertEquals("Unknown", joker.getStringSuit());
    }

    @Test
    public void testAceHigh() {
        ace = new PlayingCard(1,"Spades",true);
        assert(ace.isAceHigh());
        ace.setCardValue(1);
        assertEquals(CardValue.ACE_HIGH, ace.getCardValue());

        ace = new PlayingCard(1,"Hearts",true);
        assert(ace.isAceHigh());
        assertEquals(CardValue.ACE_HIGH, ace.getCardValue());

        ace = new PlayingCard(14,"Hearts", false);
        assertEquals(CardValue.ACE_LOW, ace.getCardValue());

        ace = new PlayingCard(1,"Hearts", false);
        ace.setCardValue(14);
        assertEquals(CardValue.ACE_LOW, ace.getCardValue());
    }

    @Test
    public void testConstructors() {
        PlayingCard card = new PlayingCard();
        assertEquals(-1, card.getCardValue().value);
        assertEquals(SuitValue.UNKNOWN, card.getSuit());

        card = new PlayingCard(1, "Hearts", false);
        assertEquals(1, card.getCardValue().value);
        assertEquals("Hearts", card.getStringSuit());
        assertEquals(SuitValue.HEARTS, card.getSuit());
        assertEquals(false, card.isAceHigh());

        card = new PlayingCard(1, "Hearts", CardStatus.ACTIVATED);
        assertEquals(1, card.getCardValue().value);
        assertEquals("Hearts", card.getStringSuit());
        assertEquals(SuitValue.HEARTS, card.getSuit());
        assertEquals(CardStatus.ACTIVATED, card.getStatus());

        card = new PlayingCard(1, "Hearts", CardStatus.ACTIVATED);
        assertEquals(1, card.getCardValue().value);
        assertEquals("Hearts", card.getStringSuit());
        assertEquals(SuitValue.HEARTS, card.getSuit());
        assertEquals(CardStatus.ACTIVATED, card.getStatus());

        card = new PlayingCard(CardValue.TEN, "Stars");
        assertEquals(10, card.getCardValue().value);
        assertEquals("Stars", card.getStringSuit());
        assertEquals("Stars", card.getStringSuit());
    }

    @Test
    public void testCardValueNames() {
        assertEquals("Jack", CardValue.getValueName(11));
    }

    @Test
    public void testCardEquals() {
        Card card1 = new Card("Test card", CardStatus.ACTIVATED);
        Card card2 = new Card("Test card", CardStatus.DEACTIVATED);
        Card card3 = new Card("Test card2", CardStatus.ACTIVATED);
        Card card4 = new Card("Test card2", CardStatus.DEACTIVATED);
        Card card5 = new Card("Test card", CardStatus.ACTIVATED);

        assertTrue(card1.equals(card5));
        assertFalse(card1.equals(null));
        assertFalse(card1.equals("Nope"));
        assertFalse(card1.equals(card2));
        assertFalse(card1.equals(card3));
        assertFalse(card1.equals(card4));
    }
}
