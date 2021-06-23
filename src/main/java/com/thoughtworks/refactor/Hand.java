package com.thoughtworks.refactor;

public class Hand {
    private final String hands;

    public Hand(String hands) {
        this.hands = hands;
    }

    public String getHands() {
        return hands;
    }

    int[] getDescendingHandsNumbers() {
        return PokerUtil.getDescendingHandsNumbers(new Hand(getHands()));
    }

    Category getCategory() {
        return new Category(PokerUtil.judgeHandCategory(this));
    }
}
