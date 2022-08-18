package model;

public class Card {

    private Suit suit;
    private Rank rank;
    private String name;

    public Card(Suit suit, Rank rank, String name) {
        this.suit = suit;
        this.rank = rank;
        this.name = name;
    }

    public int getRankNumber() {
        return rank.getNumber();
    }

    public Suit getSuit() {
        return suit;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Card --> " +
                "suit : " + suit +
                ", rank : " + rank +
                ", name : " + name;
    }




}
