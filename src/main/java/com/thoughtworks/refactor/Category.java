package com.thoughtworks.refactor;

public class Category {
    private final String category;

    public Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    int getRanking() {
        int index = -1;
        String[] type = {"StraightFlush", "FourOfAKind", "FullHouse", "Flush", "Straight", "ThreeOfAKind", "TwoPair", "OnePair", "HighCard"};
        for (int i = 0; i < 9; i++) {
            if (type[i].equals(getCategory())) {
                index = i;
            }
        }
        return index;
    }
}
