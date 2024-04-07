import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter first player\'s name:");
        String p1_name = in.nextLine();
        System.out.println("Enter second player\'s name:");
        String p2_name = in.nextLine();

        Deck deck = Deck.deckOfCards();
        Player p1 = new Player(p1_name);
        Player p2 = new Player(p2_name);
        WarGame warGame = new WarGame(p1, p2, deck);

        warGame.Play();
    }
}