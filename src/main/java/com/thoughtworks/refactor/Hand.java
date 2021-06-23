package com.thoughtworks.refactor;

public class Hand {
    private final String hands;

    public Hand(String hands) {
        this.hands = hands;
    }

    static int[] getDistinctDescendingHandsNumbers(int[] blackDescendingHandsNumbers) {
        return PokerUtil.getDistinctDescendingHandsNumbers(blackDescendingHandsNumbers);
    }

    static int[] getDescendingRepeatNumbers(int[] blackDescendingHandsNumbers) {
        return PokerUtil.noOrRepeatNumber(blackDescendingHandsNumbers, 0);
    }

    static int[] getDescendingNoRepeatNumbers(int[] blackDescendingHandsNumbers) {
        return PokerUtil.noOrRepeatNumber(blackDescendingHandsNumbers, 1);
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

    int[] getDistinctDescendingHandsNumbers() {
        return getDistinctDescendingHandsNumbers(getDescendingHandsNumbers());
    }

    int[] getDescendingRepeatNumbers() {
        return getDescendingRepeatNumbers(getDescendingHandsNumbers());
    }

    int[] getDescendingNoRepeatNumbers() {
        return getDescendingNoRepeatNumbers(getDescendingHandsNumbers());
    }
}
