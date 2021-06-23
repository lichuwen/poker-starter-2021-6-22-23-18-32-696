package com.thoughtworks.refactor;

import static com.thoughtworks.refactor.SameCategoryHandsComparator.intNumber;

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
            if (blackHand.getCategory().getRanking() == 0) { //同花顺
                winResult = SameCategoryHandsComparator.compareStraightFlush(blackHand.getDescendingHandsNumbers(), whiteHand.getDescendingHandsNumbers());
            } else if (blackHand.getCategory().getRanking() == 1) { //铁支
                winResult = SameCategoryHandsComparator.compareFourOfAKind(blackHand.getDistinctDescendingHandsNumbers(), whiteHand.getDistinctDescendingHandsNumbers());
            } else if (blackHand.getCategory().getRanking() == 2) { //葫芦
                winResult = SameCategoryHandsComparator.compareFullHouse(blackHand.getDistinctDescendingHandsNumbers(), whiteHand.getDistinctDescendingHandsNumbers());
            } else if (blackHand.getCategory().getRanking() == 3) { //同花
                winResult = SameCategoryHandsComparator.compareFlush(blackHand.getDescendingHandsNumbers(), whiteHand.getDescendingHandsNumbers());
            } else if (blackHand.getCategory().getRanking() == 4) { //顺子
                winResult = SameCategoryHandsComparator.compareStraight(blackHand.getDescendingHandsNumbers(), whiteHand.getDescendingHandsNumbers());
            } else if (blackHand.getCategory().getRanking() == 5) { //三条
                winResult = SameCategoryHandsComparator.compareThreeOfAKind(blackHand.getDescendingRepeatNumbers(), whiteHand.getDescendingRepeatNumbers());
            } else if (blackHand.getCategory().getRanking() == 6) { //两对
                winResult = SameCategoryHandsComparator.compareTwoPair(blackHand, whiteHand);
            } else if (blackHand.getCategory().getRanking() == 7) { //对子
                winResult = SameCategoryHandsComparator.compareOnePair(blackHand, whiteHand);
            } else { //散牌
                winResult = SameCategoryHandsComparator.compareHighCard(blackHand.getDescendingHandsNumbers(), whiteHand.getDescendingHandsNumbers());
            }
        }
        return winResult;
    }

}
