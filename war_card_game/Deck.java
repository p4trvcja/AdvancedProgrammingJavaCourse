import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    ArrayList<Card> cards = new ArrayList<>();

    static Deck deckOfCards() {
        Deck res = new Deck();
        for(Suit suit : Suit.values()) {
            for(Type type : Type.values()) {
                res.cards.add(new Card(suit, type));
            }
        }
        return res;
    }

    void Shuffle() {
        Collections.shuffle(cards);
    }

    Card takeCard() throws DeckSizeException {
        if(cards.isEmpty()) throw new DeckSizeException("This deck is empty");

        return cards.removeFirst();
    }
}
