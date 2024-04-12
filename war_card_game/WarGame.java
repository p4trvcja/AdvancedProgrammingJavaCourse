import java.util.ArrayList;
import java.util.List;

public class WarGame extends Game {

    WarGame(Player player1, Player player2, Deck deck) {
        super(player1, player2, deck);
    }

    void Play() {
        deck.Shuffle();
        while (!deck.cards.isEmpty()) {
            try {
                for (Player p : players) {
                    p.cardsInHand.add(deck.takeCard());
                }
            } catch (DeckSizeException e) {
                e.getStackTrace();
            }
        }

        for (int i = 0; ; i++) {
            if (isWinner()) break;
            System.out.println("Turn number: " + i);
            Turn();
        }

        if(players.getFirst().cardsInHand.isEmpty()) System.out.println("Player 2 won!");
        else System.out.println("Player 1 won!");

        System.out.println(players.getFirst().cardsInHand.size() + " : " + players.getLast().cardsInHand.size());
    }

    private boolean isWinner() {
        for(Player p : players) {
            if (p.cardsInHand.isEmpty()) return true;
        }
        return false;
    }

    private void Turn() {
        Card c1 = players.getFirst().cardsInHand.removeFirst();
        Card c2 = players.getLast().cardsInHand.removeFirst();

        ArrayList<Card> cardList = new ArrayList<>();
        cardList.add(c1);
        cardList.add(c2);

        System.out.println("First player\'s card: " + c1.type + " " + c1.suit);
        System.out.println("Second player\'s card: " + c2.type + " " + c2.suit);

        if(c1.type.ordinal() > c2.type.ordinal()) {
            System.out.println("Turn winner: " + players.getFirst().name);
            players.getFirst().cardsInHand.addAll(cardList);
            cardList.clear();
        }
        else if(c1.type.ordinal() < c2.type.ordinal()) {
            System.out.println("Turn winner: " + players.getLast().name);
            players.getLast().cardsInHand.add(c2);
            players.getLast().cardsInHand.add(c1);
            cardList.clear();
        }
        else {
            System.out.println("War!");
            War(cardList);
            cardList.clear();
        }
    }

    private void War(ArrayList<Card> cardList) {
        cardList.add(players.getFirst().cardsInHand.removeFirst());
        cardList.add(players.getLast().cardsInHand.removeFirst());

        Card c1 = players.getFirst().cardsInHand.removeFirst();
        Card c2 = players.getLast().cardsInHand.removeFirst();

        cardList.add(c1);
        cardList.add(c2);

        if(c1.type.ordinal() > c2.type.ordinal()) {
            System.out.println("War winner: " + players.getFirst().name);
            players.getFirst().cardsInHand.addAll(cardList);
        }
        else if(c1.type.ordinal() < c2.type.ordinal()) {
            System.out.println("War winner: " + players.getLast().name);
            players.getLast().cardsInHand.addAll(cardList);
        }
        else {
            System.out.println("War!");
            War(cardList);
        }
    }
}
