import java.util.Objects;

public class Card {
    Suit suit;
    Type type;

    Card(Suit suit, Type type) {
        this.suit = suit;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit && type == card.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, type);
    }
}
