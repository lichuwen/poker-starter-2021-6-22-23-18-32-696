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
                if (blackHand.getDescendingHandsNumbers()[0] < whiteHand.getDescendingHandsNumbers()[0]) {
                    String sig = intNumber(whiteHand.getDescendingHandsNumbers()[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackHand.getDescendingHandsNumbers()[0] > whiteHand.getDescendingHandsNumbers()[0]) {
                    String sig = intNumber(blackHand.getDescendingHandsNumbers()[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    winResult = "tie";
                }
            } else if (blackHand.getCategory().getRanking() == 1) { //铁支
                if (blackHand.getDistinctDescendingHandsNumbers()[0] < whiteHand.getDistinctDescendingHandsNumbers()[0]) {
                    String sig = intNumber(whiteHand.getDistinctDescendingHandsNumbers()[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = intNumber(blackHand.getDistinctDescendingHandsNumbers()[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackHand.getCategory().getRanking() == 2) { //葫芦
                if (blackHand.getDistinctDescendingHandsNumbers()[0] < whiteHand.getDistinctDescendingHandsNumbers()[0]) {
                    String sig = intNumber(whiteHand.getDistinctDescendingHandsNumbers()[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = intNumber(blackHand.getDistinctDescendingHandsNumbers()[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackHand.getCategory().getRanking() == 3) { //同花
                for (int i = 0; i < 5; i++) {
                    if (blackHand.getDescendingHandsNumbers()[i] < whiteHand.getDescendingHandsNumbers()[i]) {
                        String sig = intNumber(whiteHand.getDescendingHandsNumbers()[i]);
                        winResult = "white wins - high card:" + sig;
                        break;
                    } else if (blackHand.getDescendingHandsNumbers()[i] > whiteHand.getDescendingHandsNumbers()[i]) {
                        String sig = intNumber(blackHand.getDescendingHandsNumbers()[i]);
                        winResult = "black wins - high card:" + sig;
                        break;
                    } else {
                        winResult = "tie";
                    }
                }
            } else if (blackHand.getCategory().getRanking() == 4) { //顺子
                if (blackHand.getDescendingHandsNumbers()[0] < whiteHand.getDescendingHandsNumbers()[0]) {
                    String sig = intNumber(whiteHand.getDescendingHandsNumbers()[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackHand.getDescendingHandsNumbers()[0] > whiteHand.getDescendingHandsNumbers()[0]) {
                    String sig = intNumber(blackHand.getDescendingHandsNumbers()[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    winResult = "tie";
                }
            } else if (blackHand.getCategory().getRanking() == 5) { //三条
                if (blackHand.getDescendingRepeatNumbers()[0] < whiteHand.getDescendingRepeatNumbers()[0]) {
                    String sig = intNumber(whiteHand.getDescendingRepeatNumbers()[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = intNumber(blackHand.getDescendingRepeatNumbers()[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackHand.getCategory().getRanking() == 6) { //两对
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
            } else if (blackHand.getCategory().getRanking() == 7) { //对子
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
            } else { //散牌
                for (int i = 0; i < 5; i++) {
                    if (blackHand.getDescendingHandsNumbers()[i] < whiteHand.getDescendingHandsNumbers()[i]) {
                        String sig = intNumber(whiteHand.getDescendingHandsNumbers()[i]);
                        winResult = "white wins - high card:" + sig;
                        break;
                    } else if (blackHand.getDescendingHandsNumbers()[i] > whiteHand.getDescendingHandsNumbers()[i]) {
                        String sig = intNumber(blackHand.getDescendingHandsNumbers()[i]);
                        winResult = "black wins - high card:" + sig;
                        break;
                    } else {
                        winResult = "tie";
                    }
                }
            }
        }
        return winResult;
    }

    private String intNumber(int i) {
        String[] strNumber = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
        return strNumber[i - 2];
    }

}
