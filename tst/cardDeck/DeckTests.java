package cardDeck;

import org.junit.*;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;

public class DeckTests {

    String testDeckName = "testDeck";

    @Test
    public void testNameConstructor() {
        Deck testDeck = new Deck(testDeckName);
        assertEquals(testDeckName, testDeck.getName());
        assertNull(testDeck.getCardNames());
        assertNull(testDeck.getPile());
        assert(testDeck.isFaceDown());
    }

    @Test
    public void testPileConstructor() {
        ArrayList<Card> pile = new ArrayList<>();
        PlayingCard jack = new PlayingCard(CardValue.JACK, SuitValue.CLUBS);
        PlayingCard eight = new PlayingCard(CardValue.EIGHT, SuitValue.CLUBS);
        pile.add(jack);
        pile.add(eight);
        Deck testDeck = new Deck(pile);
        assertNull(testDeck.getName());
        ArrayList<String> cardNames = new ArrayList<>();
        pile.forEach(c->cardNames.add(c.getName()));
        assertEquals(testDeck.getCardNames(), cardNames);
        assertEquals(testDeck.getPile().size(), pile.size());
        assert(testDeck.isFaceDown());
    }
}
