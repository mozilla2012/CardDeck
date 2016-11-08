package cardDeck;

import org.junit.*;

import static junit.framework.TestCase.assertEquals;

public class PlayingCardTests {

    private PlayingCard ace;
    private PlayingCard jack;
    private PlayingCard queen;
    private PlayingCard king;
    private PlayingCard two;
    private PlayingCard ten;
    private PlayingCard wut;

    @Test
    public void printNames(){
        ace = new PlayingCard(1, 1);
        jack = new PlayingCard(11, 1);
        queen = new PlayingCard(12, 1);
        king = new PlayingCard(13, 2);
        two = new PlayingCard(2, 3);
        ten = new PlayingCard(10, 4);
        wut = new PlayingCard(0, 5);
        assertEquals("Ace of Spades", ace.getName());
        assertEquals("Jack of Spades", jack.getName());
        assertEquals("Queen of Spades", queen.getName());
        assertEquals("King of Hearts", king.getName());
        assertEquals("Two of Diamonds", two.getName());
        assertEquals("Ten of Clubs", ten.getName());
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

        //Same suit, same value should be equal
        assert(ace.equals(wut));

        //Change suit, but compare should be the same
        wut.setSuit("Diamonds");
        assert(!ace.equals(wut));
        assertEquals(0, ace.compareTo(wut));

        //Make one trump
        wut.setTrump(true);
        assertEquals(-1, ace.compareTo(wut));
        wut.setTrump(false);

        //Test ace low
        wut.setValue(2);
        assertEquals(-1, ace.compareTo(wut));

        //Test ace high
        ace.setAceHigh(true);
        assertEquals(1, ace.compareTo(wut));
        assertEquals(14, ace.getValue());

        //Affrim setting ace low fixes value
        ace.setAceHigh(false);
        assertEquals(1, ace.getValue());
    }
}