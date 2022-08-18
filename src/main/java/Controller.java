import model.Card;

import java.util.List;
import java.util.Scanner;

public class Controller {

    Makao makao = new Makao();
    private List<Card> allCards = makao.getAllCards();
    private List<Card> myCards = makao.getMyCards();
    private List<Card> player2Cards = makao.getPlayer2Cards();
    private boolean running = true;
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        scanner = new Scanner(System.in);
        System.out.println("Gra w Makao!!!\n");
        Card card = makao.getOneCard(allCards);
        System.out.println("Pierwsza karta");
        System.out.println(card);
        System.out.println();
        do {
            card = putPlayers2Card(card);
            if (makao.getPlayer2Cards().isEmpty()) {
                running = false;
            }
            card = putMyCard(card);

            if (makao.getMyCards().isEmpty()) {
                running = false;
            }
        } while (running);
        printWinner();
    }

    private Card putPlayers2Card(Card card) {
        System.out.println("\nKarty przeciwnika");
        makao.showCards(player2Cards);
        System.out.println();
        System.out.println("\nAktualna karta:");
        card = makao.putTheCardPlayer2(card, player2Cards);
        System.out.println(card);
        return card;
    }

    public Card putMyCard(Card card) {
        System.out.println("\nTwoje karty:");
        makao.showCards(myCards);
        System.out.println("\nWybierz kartę");
        System.out.println("Jeśli żadna nie pasuje, wybierz 0");
        int cartNumber = scanner.nextInt();
        int index = cartNumber - 1;
        if (index == -1) {
            Card newCard = makao.getOneCard(allCards);
            myCards.add(newCard);
            System.out.println("Dobieram kartę");
        } else if (index >= 0 && index < myCards.size()) {
            card = makao.putTheCardFromMyCards(card, myCards, index);
            System.out.println("\nAktualna karta:");
            System.out.println(card);
        } else {
            System.out.println("Błędny wybór");
            putMyCard(card);
        }
        return card;
    }

    private void printWinner() {
        System.out.println("Koniec!");
        if (myCards.isEmpty()) {
            System.out.println("Gratulacje! Wygrałeś!");
        } else if (player2Cards.isEmpty()) {
            System.out.println("Przegrałeś ;(");
        }
    }
}
