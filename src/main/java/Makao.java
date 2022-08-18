import model.Card;
import model.Rank;
import model.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Makao {

    private List<Card> allCards = createDeck();
    private List<Card> myCards = giveCardsToPlayer(allCards);
    private List<Card> player2Cards = giveCardsToPlayer(allCards);


    public ArrayList<Card> createDeck() {
        ArrayList<Card> cardsList = new ArrayList<>();
        for (Suit suitValue : Suit.values()) {
            for (Rank value : Rank.values()) {
                cardsList.add(new Card(suitValue, value, "zwykła karta"));
            }
        }
        cardsList.add(new Card(Suit.HEART, Rank.ACE, "Joker 1"));
        cardsList.add(new Card(Suit.CLUB, Rank.ACE, "Joker 2"));
        Collections.shuffle(cardsList);
        return cardsList;
    }

    private List<Card> giveCardsToPlayer(List<Card> allCards) {
        Random random = new Random();
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int number = random.nextInt(allCards.size());
            Card card = allCards.get(number);
            cards.add(card);
            removeCard(card, allCards);
        }
        return cards;
    }

    public void showCards(List<Card> cards) {
        int count = 1;
        for (Card card : cards) {
            System.out.println(count + ". " + card);
            count++;
        }
    }

    public Card getOneCard(List<Card> cards) {
        Card card = cards.get(0);
        removeCard(card, cards);
        return card;
    }

    private List<Card> getMatchingCards(Card lastCard, List<Card> player2Cards) {
        return player2Cards.stream()
                .filter(card1 -> card1.getName() == "Joker 1" || card1.getName() == "Joker 2" || card1.getRankNumber() == lastCard.getRankNumber() || card1.getSuit() == lastCard.getSuit())
                .collect(Collectors.toList());
    }


    private void removeCard(Card card, List<Card> cards) {
        cards.remove(card);
    }


    public Card putTheCardPlayer2(Card lastCard, List<Card> player2Cards) {
        Card actualCard = null;
        Card newCard = null;
        List<Card> matchingCards = new ArrayList<>();
        if (lastCard.getName() == "Joker 1" || lastCard.getName() == "Joker 2") {
            matchingCards.addAll(player2Cards);
        } else {
            matchingCards = getMatchingCards(lastCard, player2Cards);
        }
        if (!matchingCards.isEmpty()) {
            actualCard = matchingCards.get(0);
            removeCard(actualCard, player2Cards);
        } else {
            actualCard = lastCard;
            newCard = getOneCard(allCards);
            player2Cards.add(newCard);
        }
        return actualCard;
    }

    private Card getMatchingCartFromMyCards(List<Card> myCards, int number) {
        return myCards.get(number);
    }

    private boolean isCardMatching(Card lastCard, Card card) {
        if (lastCard.getRankNumber() == card.getRankNumber() || lastCard.getSuit() == card.getSuit()
                || lastCard.getName().equals("Joker 1") || lastCard.getName().equals("Joker 2")) {
            return true;
        } else if (lastCard.getRankNumber() != card.getRankNumber() && lastCard.getSuit() != card.getSuit()
                && lastCard.getName() == null) {
            return false;
        }
        return false;
    }

    public Card putTheCardFromMyCards(Card lastCard, List<Card> myCards, int number) {
        Card card = getMatchingCartFromMyCards(myCards, number);
        Card actualCard = null;
        Card cardToAdd = null;
        if (isCardMatching(lastCard, card)) {
            actualCard = card;
            removeCard(card, myCards);
        } else if (!isCardMatching(lastCard, card)) {
            actualCard = lastCard;
            cardToAdd = getOneCard(allCards);
            myCards.add(cardToAdd);
            System.out.println("Karta nie pasuje! Za karę dobierasz kartę :P");
        }
        return actualCard;


    }


    ////////////////////////// gettery ///////////////////////////////////////


    public List<Card> getAllCards() {
        return allCards;
    }

    public List<Card> getMyCards() {
        return myCards;
    }

    public List<Card> getPlayer2Cards() {
        return player2Cards;
    }
}
