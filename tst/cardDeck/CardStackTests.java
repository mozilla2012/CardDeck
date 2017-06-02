package cardDeck;

import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class CardStackTests {

    private String testDeckName = "testDeck";

    @Test
    public void testNameConstructor() {
        CardStack testDeck = new CardStack(testDeckName);
        assertEquals(testDeckName, testDeck.getName());
        assertNull(testDeck.getCardNames());
        assertNull(testDeck.getPile());
        assert(testDeck.isFaceDown());
    }

    @Test
    public void testPileConstructor() {
        ArrayList<Card> pile = createSimplePile();
        CardStack testDeck = new CardStack(pile);
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
        CardStack testDeck = new CardStack(testDeckName, pile);
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
        CardStack testDeck = new CardStack(testDeckName, pile, false);
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
        CardStack testDeck = new CardStack(testDeckName);
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
        CardStack testDeck = new CardStack(testDeckName);
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
        CardStack testDeck = new CardStack(testDeckName);
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
        CardStack testDeck = new CardStack(testDeckName, pile);
        assertEquals(testDeckName, testDeck.getName());
        assert(testDeck.isFaceDown());

        // Shuffle the pile
        testDeck.shuffle();
        ArrayList<Card> shuffledPile = testDeck.getPile();
        // Ensure no cards were lost.
        assertEquals(pile.size(), shuffledPile.size());
        assertTrue("Shuffled pile does not contain the same cards.", shuffledPile.containsAll(pile));
        // Assume the deck isn't shuffled, until a shuffled card is found
        boolean shuffled = false;
        for(int i = 0; i < pile.size(); i++) {
            // If a card doesn't match the other of the same index, assume it's shuffled and succeed.
            if (!pile.get(i).equals(shuffledPile.get(i))) {
               shuffled = true;
               break;
            }
        }
        // If the order was the same, fail the test.
        if (!shuffled) {
            fail("Deck was not shuffled. Maybe run tests again?");
        }
    }

    @Test
    public void testDraws() {
        ArrayList<Card> pile = createSimplePile();
        CardStack testDeck = new CardStack(testDeckName, pile);
        assertEquals(testDeckName, testDeck.getName());
        assert(testDeck.isFaceDown());

        // Draw the top card
        Card drawn = testDeck.draw();
        assertTrue(pile.get(0).equals(drawn));
        assertEquals(pile.size() - 1, testDeck.getPile().size());

        // Draw the bottom card
        drawn = testDeck.draw(false);
        assertTrue(pile.get(pile.size() - 1).equals(drawn));
        assertEquals(pile.size() - 2, testDeck.getPile().size());

        // Draw multiple
        ArrayList<Card> drawnCards = testDeck.drawN(2);
        assertEquals(2, drawnCards.size());
        assertTrue(drawnCards.containsAll(pile.subList(1, 3)));
        assertEquals(pile.size() - 4, testDeck.getPile().size());

        // Draw multiple from bottom
        drawnCards = testDeck.drawN(2, false);
        assertEquals(2, drawnCards.size());
        assertTrue(drawnCards.containsAll(pile.subList(pile.size() - 3, pile.size() - 1)));
        assertEquals(pile.size() - 6, testDeck.getPile().size());

    }

    @Test
    public void testPeeks() {
        ArrayList<Card> pile = createSimplePile();
        CardStack testDeck = new CardStack(testDeckName, pile);
        assertEquals(testDeckName, testDeck.getName());
        assert(testDeck.isFaceDown());

        // Draw the top card
        Card drawn = testDeck.peek();
        assertTrue(pile.get(0).equals(drawn));
        assertEquals(pile.size(), testDeck.getPile().size());

        // Draw the bottom card
        drawn = testDeck.peek(false);
        assertTrue(pile.get(pile.size() - 1).equals(drawn));
        assertEquals(pile.size(), testDeck.getPile().size());

        // Draw multiple
        ArrayList<Card> drawnCards = testDeck.peekN(2);
        assertEquals(2, drawnCards.size());
        assertTrue(drawnCards.containsAll(pile.subList(0, 2)));
        assertEquals(pile.size(), testDeck.getPile().size());

        // Draw multiple from bottom
        drawnCards = testDeck.peekN(2, false);
        assertEquals(2, drawnCards.size());
        assertTrue(drawnCards.containsAll(pile.subList(pile.size() - 2, pile.size())));
        assertEquals(pile.size(), testDeck.getPile().size());

    }

    @Test
    public void testInsert() {
        ArrayList<Card> pile = createSimplePile();
        CardStack testDeck = new CardStack(testDeckName, pile);
        assertEquals(testDeckName, testDeck.getName());
        assert(testDeck.isFaceDown());

        // Add a card
        Card newCard = new PlayingCard(CardValue.ACE_HIGH, SuitValue.DIAMONDS);
        pile.add(newCard);
        testDeck.insert(newCard, true);
        assertTrue(newCard.equals(testDeck.getPile().get(0)));
        assertEquals(pile.size(), testDeck.getPile().size());
        assertTrue(pile.containsAll(testDeck.getPile()));

        // Add a card to bottom
        Card newCard2 = new PlayingCard(CardValue.ACE_HIGH, SuitValue.CLUBS);
        pile.add(newCard2);
        testDeck.insert(newCard2, false);
        assertTrue(newCard2.equals(testDeck.getPile().get(testDeck.getPile().size() - 1)));
        assertEquals(pile.size(), testDeck.getPile().size());
        assertTrue(pile.containsAll(testDeck.getPile()));
    }

    @Test
    public void testInsertN() {
        ArrayList<Card> pile = createSimplePile();
        CardStack testDeck = new CardStack(testDeckName, pile);
        assertEquals(testDeckName, testDeck.getName());
        assert(testDeck.isFaceDown());

        // Add two cards to top
        Card newCard = new PlayingCard(CardValue.ACE_HIGH, SuitValue.DIAMONDS);
        Card newCard2 = new PlayingCard(CardValue.ACE_HIGH, SuitValue.CLUBS);
        pile.add(newCard);
        pile.add(newCard2);
        ArrayList<Card> addable = new ArrayList<>();
        addable.add(newCard);
        addable.add(newCard2);
        testDeck.insert(addable, true);
        // Top card in the addable stack should be added first, and thus closer to the bottom.
        assertTrue(newCard.equals(testDeck.getPile().get(1)));
        assertTrue(newCard2.equals(testDeck.getPile().get(0)));
        assertEquals(pile.size(), testDeck.getPile().size());
        assertTrue(pile.containsAll(testDeck.getPile()));

        // Add two cards to bottom
        newCard = new PlayingCard(CardValue.KING, SuitValue.DIAMONDS);
        newCard2 = new PlayingCard(CardValue.KING, SuitValue.CLUBS);
        pile.add(newCard);
        pile.add(newCard2);
        addable = new ArrayList<>();
        addable.add(newCard);
        addable.add(newCard2);
        testDeck.insert(addable, false);
        // Top card in the addable stack should be added first, and thus closer to the top.
        assertTrue(newCard.equals(testDeck.getPile().get(testDeck.getPile().size() - 2)));
        assertTrue(newCard2.equals(testDeck.getPile().get(testDeck.getPile().size() - 1)));
        assertEquals(pile.size(), testDeck.getPile().size());
        assertTrue(pile.containsAll(testDeck.getPile()));
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
