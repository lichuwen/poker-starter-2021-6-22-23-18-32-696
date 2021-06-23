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
            if (blackHand.getCategory().getRanking() == 0) { //同花顺
                winResult = compareStraightFlush(blackHand.getDescendingHandsNumbers(), whiteHand.getDescendingHandsNumbers());
            } else if (blackHand.getCategory().getRanking() == 1) { //铁支
                winResult = compareFourOfAKind(blackHand.getDistinctDescendingHandsNumbers(), whiteHand.getDistinctDescendingHandsNumbers());
            } else if (blackHand.getCategory().getRanking() == 2) { //葫芦
                winResult = compareFullHouse(blackHand.getDistinctDescendingHandsNumbers(), whiteHand.getDistinctDescendingHandsNumbers());
            } else if (blackHand.getCategory().getRanking() == 3) { //同花
                winResult = compareFlush(blackHand.getDescendingHandsNumbers(), whiteHand.getDescendingHandsNumbers());
            } else if (blackHand.getCategory().getRanking() == 4) { //顺子
                winResult = compareStraight(blackHand.getDescendingHandsNumbers(), whiteHand.getDescendingHandsNumbers());
            } else if (blackHand.getCategory().getRanking() == 5) { //三条
                winResult = compareThreeOfAKind(blackHand.getDescendingRepeatNumbers(), whiteHand.getDescendingRepeatNumbers());
            } else if (blackHand.getCategory().getRanking() == 6) { //两对
                winResult = compareTwoPair(winResult, blackHand, whiteHand);
            } else if (blackHand.getCategory().getRanking() == 7) { //对子
                winResult = compareOnePair(winResult, blackHand, whiteHand);
            } else { //散牌
                winResult = compareHighCard(winResult, 5, blackHand.getDescendingHandsNumbers(), whiteHand.getDescendingHandsNumbers());
            }
        }
        return winResult;
    }

    private String compareHighCard(String winResult, int i2, int[] descendingHandsNumbers, int[] descendingHandsNumbers2) {
        for (int i = 0; i < i2; i++) {
            if (descendingHandsNumbers[i] < descendingHandsNumbers2[i]) {
                String sig = intNumber(descendingHandsNumbers2[i]);
                winResult = "white wins - high card:" + sig;
                break;
            } else if (descendingHandsNumbers[i] > descendingHandsNumbers2[i]) {
                String sig = intNumber(descendingHandsNumbers[i]);
                winResult = "black wins - high card:" + sig;
                break;
            } else {
                winResult = "tie";
            }
        }
        return winResult;
    }

    private String compareOnePair(String winResult, Hand blackHand, Hand whiteHand) {
        if (blackHand.getDescendingRepeatNumbers()[0] < whiteHand.getDescendingRepeatNumbers()[0]) {
            String sig = intNumber(whiteHand.getDescendingRepeatNumbers()[0]);
            winResult = "white wins - high card:" + sig;
        } else if (blackHand.getDescendingRepeatNumbers()[0] > whiteHand.getDescendingRepeatNumbers()[0]) {
            String sig = intNumber(blackHand.getDescendingRepeatNumbers()[0]);
            winResult = "black wins - high card:" + sig;
        } else {
            for (int i = 0; i < 3; i++) {
                if (blackHand.getDescendingNoRepeatNumbers()[i] < whiteHand.getDescendingNoRepeatNumbers()[i]) {
                    String sig = intNumber(whiteHand.getDescendingNoRepeatNumbers()[i]);
                    winResult = "white wins - high card:" + sig;
                    break;
                } else if (blackHand.getDescendingNoRepeatNumbers()[i] > whiteHand.getDescendingNoRepeatNumbers()[i]) {
                    String sig = intNumber(blackHand.getDescendingNoRepeatNumbers()[i]);
                    winResult = "black wins - high card:" + sig;
                    break;
                } else {
                    winResult = "tie";
                }
            }
        }
        return winResult;
    }

    private String compareTwoPair(String winResult, Hand blackHand, Hand whiteHand) {
        for (int i = 0; i < 2; i++) {
            if (blackHand.getDescendingRepeatNumbers()[i] < whiteHand.getDescendingRepeatNumbers()[i]) {
                String sig = intNumber(whiteHand.getDescendingRepeatNumbers()[i]);
                winResult = "white wins - high card:" + sig;
                break;
            } else if (blackHand.getDescendingRepeatNumbers()[i] > whiteHand.getDescendingRepeatNumbers()[i]) {
                String sig = intNumber(blackHand.getDescendingRepeatNumbers()[i]);
                winResult = "black wins - high card:" + sig;
                break;
            }
        }
        if (winResult == "") {
            if (blackHand.getDescendingNoRepeatNumbers()[0] < whiteHand.getDescendingNoRepeatNumbers()[0]) {
                String sig = intNumber(whiteHand.getDescendingNoRepeatNumbers()[0]);
                winResult = "white wins - high card:" + sig;
            } else if (blackHand.getDescendingNoRepeatNumbers()[0] > whiteHand.getDescendingNoRepeatNumbers()[0]) {
                String sig = intNumber(blackHand.getDescendingNoRepeatNumbers()[0]);
                winResult = "black wins - high card:" + sig;
            } else {
                winResult = "tie";
            }
        }
        return winResult;
    }

    private String compareThreeOfAKind(int[] descendingRepeatNumbers, int[] descendingRepeatNumbers2) {
        String winResult;
        if (descendingRepeatNumbers[0] < descendingRepeatNumbers2[0]) {
            String sig = intNumber(descendingRepeatNumbers2[0]);
            winResult = "white wins - high card:" + sig;
        } else {
            String sig = intNumber(descendingRepeatNumbers[0]);
            winResult = "black wins - high card:" + sig;
        }
        return winResult;
    }

    private String compareStraight(int[] descendingHandsNumbers, int[] descendingHandsNumbers2) {
        String winResult;
        if (descendingHandsNumbers[0] < descendingHandsNumbers2[0]) {
            String sig = intNumber(descendingHandsNumbers2[0]);
            winResult = "white wins - high card:" + sig;
        } else if (descendingHandsNumbers[0] > descendingHandsNumbers2[0]) {
            String sig = intNumber(descendingHandsNumbers[0]);
            winResult = "black wins - high card:" + sig;
        } else {
            winResult = "tie";
        }
        return winResult;
    }

    private String compareFlush(int[] descendingHandsNumbers, int[] descendingHandsNumbers2) {
        String winResult = null;
        for (int i = 0; i < 5; i++) {
            if (descendingHandsNumbers[i] < descendingHandsNumbers2[i]) {
                String sig = intNumber(descendingHandsNumbers2[i]);
                winResult = "white wins - high card:" + sig;
                break;
            } else if (descendingHandsNumbers[i] > descendingHandsNumbers2[i]) {
                String sig = intNumber(descendingHandsNumbers[i]);
                winResult = "black wins - high card:" + sig;
                break;
            } else {
                winResult = "tie";
            }
        }
        return winResult;
    }

    private String compareFullHouse(int[] distinctDescendingHandsNumbers, int[] distinctDescendingHandsNumbers2) {
        String winResult;
        if (distinctDescendingHandsNumbers[0] < distinctDescendingHandsNumbers2[0]) {
            String sig = intNumber(distinctDescendingHandsNumbers2[0]);
            winResult = "white wins - high card:" + sig;
        } else {
            String sig = intNumber(distinctDescendingHandsNumbers[0]);
            winResult = "black wins - high card:" + sig;
        }
        return winResult;
    }

    private String compareFourOfAKind(int[] distinctDescendingHandsNumbers, int[] distinctDescendingHandsNumbers2) {
        String winResult;
        if (distinctDescendingHandsNumbers[0] < distinctDescendingHandsNumbers2[0]) {
            String sig = intNumber(distinctDescendingHandsNumbers2[0]);
            winResult = "white wins - high card:" + sig;
        } else {
            String sig = intNumber(distinctDescendingHandsNumbers[0]);
            winResult = "black wins - high card:" + sig;
        }
        return winResult;
    }

    private String compareStraightFlush(int[] descendingHandsNumbers, int[] descendingHandsNumbers2) {
        String winResult;
        if (descendingHandsNumbers[0] < descendingHandsNumbers2[0]) {
            String sig = intNumber(descendingHandsNumbers2[0]);
            winResult = "white wins - high card:" + sig;
        } else if (descendingHandsNumbers[0] > descendingHandsNumbers2[0]) {
            String sig = intNumber(descendingHandsNumbers[0]);
            winResult = "black wins - high card:" + sig;
        } else {
            winResult = "tie";
        }
        return winResult;
    }

    private String intNumber(int i) {
        String[] strNumber = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
        return strNumber[i - 2];
    }

}
