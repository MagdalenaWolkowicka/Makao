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
                cardsList.add(new Card(suitValue, value));
            }
        }
        Collections.shuffle(cardsList);
        return cardsList;
    }

    public List<Card> giveCardsToPlayer(List<Card> allCards) {
        Random random = new Random();
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int number = random.nextInt(allCards.size());
            Card card = allCards.get(number);
            cards.add(card);
            removeCard(card, allCards);
            //allCards.remove(number);

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
        //cards.remove(card);
        removeCard(card, cards);
        return card;
    }

    public List<Card> getMatchingCards(Card lastCard, List<Card> player2Cards) {
        return player2Cards.stream()
                .filter(card1 -> card1.getRankNumber() == lastCard.getRankNumber() || card1.getSuit() == lastCard.getSuit())
                .collect(Collectors.toList());
    }

//    public List<Card> getMatchingSuitCards(Card card, List<Card> player2Cards) {
//        return player2Cards.stream()
//                .filter(card1 -> card1.getSuit() == card.getSuit())
//                .collect(Collectors.toList());
//    }

//    public List<Card> getMatchingCards(List<Card> listMatchingSuits, List<Card> listMatchingRank) {
//        List<Card> matchingCards = new ArrayList<>();
//
//        matchingCards.addAll(listMatchingRank);
//        matchingCards.addAll(listMatchingSuits);
//        return matchingCards;
//    }

    public void removeCard(Card card, List<Card> cards) {
        cards.remove(card);
    }


    public Card putTheCardPlayer2(Card lastCard, List<Card> player2Cards) {
        Card actualCard = null;
        Card newCard = null;
        List<Card> matchingCards = getMatchingCards(lastCard, player2Cards);
        if (!matchingCards.isEmpty()) {
         actualCard =  matchingCards.get(0);
         removeCard(actualCard, player2Cards);
        } else{
            actualCard = lastCard;
            newCard = getOneCard(allCards);
            player2Cards.add(newCard);
        }
        return actualCard;
    }

    public Card getMatchingCartFromMyCards(List<Card> myCards, int number) {
        return myCards.get(number);
    }

    public boolean isCardMatching(Card lastCard, Card card) {
        if (card.getRankNumber() == lastCard.getRankNumber() || card.getSuit() == lastCard.getSuit()) {
            return true;
        }
        return false;
    }

    public Card putTheCardFromMyCards(Card lastCard, Card newCard, List<Card> myCards, int number) {
        Card card = getMatchingCartFromMyCards(myCards, number);
        Card actualCard = null;
        Card cardToAdd = null;
        if (isCardMatching(lastCard, card)) {
            actualCard = card;
            removeCard(card, myCards);
        } else {
            actualCard = lastCard;
            cardToAdd = getOneCard(allCards);
            myCards.add(cardToAdd);
            System.out.println("Dupa");
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
