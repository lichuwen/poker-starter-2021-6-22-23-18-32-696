package com.thoughtworks.refactor;

public class Poker {

    public static final String[] HANDS_CATEGORY = {"StraightFlush", "FourOfAKind", "FullHouse", "Flush", "Straight", "ThreeOfAKind", "TwoPair", "OnePair", "HighCard"};

    public String compareResult(String blackHands, String whiteHands) {

        String winResult = "";
        Hand blackHand = new Hand(blackHands);
        Hand whiteHand = new Hand(whiteHands);

        if (blackHand.getCategory().getRanking() < whiteHand.getCategory().getRanking()) {
            winResult = "black wins - " + HANDS_CATEGORY[blackHand.getCategory().getRanking()];
        } else if (blackHand.getCategory().getRanking() > whiteHand.getCategory().getRanking()) {
            winResult = "white wins - " + HANDS_CATEGORY[whiteHand.getCategory().getRanking()];
        } else {
            winResult = SameCategoryHandsComparator.getSameCategory(blackHand, whiteHand);
        }
        return winResult;
    }

}
