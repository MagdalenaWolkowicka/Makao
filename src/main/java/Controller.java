import model.Card;

import java.util.List;
import java.util.Scanner;

public class Controller {

    Makao makao = new Makao();
    private List<Card> allCards = makao.getAllCards();
    private List<Card> myCards = makao.getMyCards();
    private List<Card> player2Cards = makao.getPlayer2Cards();
    private boolean running = true;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Gra w Makao!!!\n");
        Card card = makao.getOneCard(allCards);
        System.out.println("Pierwsza karta");
        System.out.println(card);
        System.out.println();
        do {
            System.out.println("Karty przeciwnika");
            makao.showCards(player2Cards);
            System.out.println();
            System.out.println("\nAktualna karta:");
            card = makao.putTheCardPlayer2(card, player2Cards);
            System.out.println(card);
            if (makao.getPlayer2Cards().isEmpty()) {
                running = false;
            }
            System.out.println();
            System.out.println("Twoje karty:");
            makao.showCards(myCards);
            System.out.println();
            System.out.println("Wybierz kartę");
            int cartNumber = scanner.nextInt();
            int index = cartNumber -1;
            card = makao.putTheCardFromMyCards(card, myCards.get(index), myCards, index);
            System.out.println("\nAktualna karta:");
            System.out.println(card);
            if (makao.getMyCards().isEmpty()) {
                running = false;
            }
        } while (running);
        printWinner();


    }

    private void printWinner() {
        System.out.println("Koniec!");
        if (myCards.isEmpty()) {
            System.out.println("Gratulacje! Wygrałeś!");
        } else {
            System.out.println("Przegrałeś ;(");
        }
    }
}
