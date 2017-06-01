package cardDeck;

import org.junit.*;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class StackTests {

    private String testDeckName = "testDeck";

    @Test
    public void testNameConstructor() {
        Stack testDeck = new Stack(testDeckName);
        assertEquals(testDeckName, testDeck.getName());
        assertNull(testDeck.getCardNames());
        assertNull(testDeck.getPile());
        assert(testDeck.isFaceDown());
    }

    @Test
    public void testPileConstructor() {
        ArrayList<Card> pile = createSimplePile();
        Stack testDeck = new Stack(pile);
        assertNull(testDeck.getName());
        // Ensure the pile is the same
        ArrayList<String> cardNames = new ArrayList<>();
        pile.forEach(c->cardNames.add(c.getName()));
        assertEquals(testDeck.getCardNames(), cardNames);
        assertEquals(testDeck.getPile().size(), pile.size());
        assert(testDeck.isFaceDown());
    }

    @Test
    public void testNameAndPileConstructor() {
        ArrayList<Card> pile = createSimplePile();
        Stack testDeck = new Stack(testDeckName, pile);
        assertEquals(testDeckName, testDeck.getName());
        // Ensure the pile is the same
        ArrayList<String> cardNames = new ArrayList<>();
        pile.forEach(c->cardNames.add(c.getName()));
        assertEquals(testDeck.getCardNames(), cardNames);
        assertEquals(testDeck.getPile().size(), pile.size());
        assert(testDeck.isFaceDown());
    }

    @Test
    public void testNamePileAndFaceDownConstructor() {
        ArrayList<Card> pile = createSimplePile();
        Stack testDeck = new Stack(testDeckName, pile, false);
        assertEquals(testDeckName, testDeck.getName());
        // Ensure the pile is the same
        ArrayList<String> cardNames = new ArrayList<>();
        pile.forEach(c->cardNames.add(c.getName()));
        assertEquals(testDeck.getCardNames(), cardNames);
        assertEquals(testDeck.getPile().size(), pile.size());
        assert(!testDeck.isFaceDown());
    }

    @Test
    public void testSetName() {
        Stack testDeck = new Stack(testDeckName);
        assertEquals(testDeckName, testDeck.getName());
        assertNull(testDeck.getCardNames());
        assertNull(testDeck.getPile());
        assert(testDeck.isFaceDown());

        String newName = "NewName";
        testDeck.setName(newName);
        assertEquals(newName, testDeck.getName());
        assertNull(testDeck.getCardNames());
        assertNull(testDeck.getPile());
        assert(testDeck.isFaceDown());
    }

    @Test
    public void testSetPile() {
        Stack testDeck = new Stack(testDeckName);
        assertEquals(testDeckName, testDeck.getName());
        assertNull(testDeck.getCardNames());
        assertNull(testDeck.getPile());
        assert(testDeck.isFaceDown());

        ArrayList<Card> pile = createSimplePile();
        testDeck.setPile(pile);
        assertEquals(testDeckName, testDeck.getName());
        ArrayList<String> cardNames = new ArrayList<>();
        pile.forEach(c->cardNames.add(c.getName()));
        assertEquals(testDeck.getCardNames(), cardNames);
        assertEquals(testDeck.getPile().size(), pile.size());
        assert(testDeck.isFaceDown());
    }

    @Test
    public void testFaceDown() {
        Stack testDeck = new Stack(testDeckName);
        assertEquals(testDeckName, testDeck.getName());
        assertNull(testDeck.getCardNames());
        assertNull(testDeck.getPile());
        assert(testDeck.isFaceDown());

        testDeck.flipDeck();
        assertEquals(testDeckName, testDeck.getName());
        assertNull(testDeck.getCardNames());
        assertNull(testDeck.getPile());
        assert(!testDeck.isFaceDown());

        testDeck.setFaceDown(true);
        assertEquals(testDeckName, testDeck.getName());
        assertNull(testDeck.getCardNames());
        assertNull(testDeck.getPile());
        assert(testDeck.isFaceDown());
    }

    @Test
    public void testShuffle() {
        ArrayList<Card> pile = createSimplePile();
        Stack testDeck = new Stack(testDeckName, pile);
        assertEquals(testDeckName, testDeck.getName());
        assert(testDeck.isFaceDown());

        // First, clone the pile before shuffling
        ArrayList<Card> clonedPile = (ArrayList<Card>) pile.clone();
        testDeck.shuffle();
        ArrayList<Card> shuffledPile = testDeck.getPile();
        // Ensure no cards were lost. TODO: Make sure the same cards exist after the shuffling.
        assertEquals(clonedPile.size(), shuffledPile.size());
        // Assume the deck isn't shuffled, until a shuffled card is found
        boolean shuffled = false;
        for(int i = 0; i < clonedPile.size(); i++) {
            // If a card doesn't match the other of the same index, assume it's shuffled and succeed.
            if (!clonedPile.get(i).equals(shuffledPile.get(i))) {
               shuffled = true;
               break;
            }
        }
        // If the order was the same, fail the test.
        if (!shuffled) {
            fail();
        }
    }

    private ArrayList<Card> createSimplePile() {
        ArrayList<Card> pile = new ArrayList<>();
        PlayingCard jack = new PlayingCard(CardValue.JACK, SuitValue.CLUBS);
        PlayingCard eight = new PlayingCard(CardValue.EIGHT, SuitValue.CLUBS);
        PlayingCard nine = new PlayingCard(CardValue.NINE, SuitValue.CLUBS);
        PlayingCard ten = new PlayingCard(CardValue.TEN, SuitValue.CLUBS);
        PlayingCard two = new PlayingCard(CardValue.TWO, SuitValue.CLUBS);
        PlayingCard four = new PlayingCard(CardValue.FOUR, SuitValue.CLUBS);
        PlayingCard eightOfHearts = new PlayingCard(CardValue.EIGHT, SuitValue.HEARTS);
        pile.add(jack);
        pile.add(eight);
        pile.add(nine);
        pile.add(ten);
        pile.add(two);
        pile.add(four);
        pile.add(eightOfHearts);
        return pile;
    }
}
