package model;

public class Card {

    private Suit suit;
    private Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getRankNumber() {
        return rank.getNumber();
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return "Card --> " +
                "suit : " + suit +
                ", rank : " + rank;
    }
}
