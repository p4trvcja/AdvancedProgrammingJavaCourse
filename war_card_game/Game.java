import java.util.ArrayList;

public class Game {
    ArrayList<Player> players = new ArrayList<>();
    Deck deck = new Deck();

    Game(Player player1, Player player2, Deck deck) {
        this.players.add(player1);
        this.players.add(player2);
        this.deck = deck;
    }
}
