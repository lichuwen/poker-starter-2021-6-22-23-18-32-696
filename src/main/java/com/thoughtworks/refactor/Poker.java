package com.thoughtworks.refactor;

public class Poker {

    public static final String[] HANDS_CATEGORY = {"StraightFlush", "FourOfAKind", "FullHouse", "Flush", "Straight", "ThreeOfAKind", "TwoPair", "OnePair", "HighCard"};

    public String compareResult(String blackHands, String whiteHands) {
        String winResult = "";
        Hand blackHand = new Hand(blackHands);
        int[] blackDescendingHandsNumbers = blackHand.getDescendingHandsNumbers();
        Category blackCategory = blackHand.getCategory();
        int blackHandsCategoryRanking = blackCategory.getRanking();
        int[] blackDistinctDescendingHandsNumbers = blackHand.getDistinctDescendingHandsNumbers();
        int[] blackRepeatNumbers = blackHand.getDescendingRepeatNumbers();
        int[] blackNoRepeatNumbers = blackHand.getDescendingNoRepeatNumbers();

        Hand whiteHand = new Hand(whiteHands);
        int[] whiteDescendingHandsNumbers = whiteHand.getDescendingHandsNumbers();
        Category whiteCategory = whiteHand.getCategory();
        int whiteHandsCategoryRanking = whiteCategory.getRanking();
        int[] whiteDistinctDescendingHandsNumbers = whiteHand.getDistinctDescendingHandsNumbers();
        int[] whiteRepeatNumbers = whiteHand.getDescendingRepeatNumbers();
        int[] whiteNoRepeatNumbers = whiteHand.getDescendingNoRepeatNumbers();
        if (blackHandsCategoryRanking < whiteHandsCategoryRanking) {
            winResult = "black wins - " + HANDS_CATEGORY[blackHandsCategoryRanking];
        } else if (blackHandsCategoryRanking > whiteHandsCategoryRanking) {
            winResult = "white wins - " + HANDS_CATEGORY[whiteHandsCategoryRanking];
        } else {
            if (blackHandsCategoryRanking == 0) { //同花顺
                if (blackDescendingHandsNumbers[0] < whiteDescendingHandsNumbers[0]) {
                    String sig = intNumber(whiteDescendingHandsNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackDescendingHandsNumbers[0] > whiteDescendingHandsNumbers[0]) {
                    String sig = intNumber(blackDescendingHandsNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    winResult = "tie";
                }
            } else if (blackHandsCategoryRanking == 1) { //铁支
                if (blackDistinctDescendingHandsNumbers[0] < whiteDistinctDescendingHandsNumbers[0]) {
                    String sig = intNumber(whiteDistinctDescendingHandsNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = intNumber(blackDistinctDescendingHandsNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackHandsCategoryRanking == 2) { //葫芦
                if (blackDistinctDescendingHandsNumbers[0] < whiteDistinctDescendingHandsNumbers[0]) {
                    String sig = intNumber(whiteDistinctDescendingHandsNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = intNumber(blackDistinctDescendingHandsNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackHandsCategoryRanking == 3) { //同花
                for (int i = 0; i < 5; i++) {
                    if (blackDescendingHandsNumbers[i] < whiteDescendingHandsNumbers[i]) {
                        String sig = intNumber(whiteDescendingHandsNumbers[i]);
                        winResult = "white wins - high card:" + sig;
                        break;
                    } else if (blackDescendingHandsNumbers[i] > whiteDescendingHandsNumbers[i]) {
                        String sig = intNumber(blackDescendingHandsNumbers[i]);
                        winResult = "black wins - high card:" + sig;
                        break;
                    } else {
                        winResult = "tie";
                    }
                }
            } else if (blackHandsCategoryRanking == 4) { //顺子
                if (blackDescendingHandsNumbers[0] < whiteDescendingHandsNumbers[0]) {
                    String sig = intNumber(whiteDescendingHandsNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackDescendingHandsNumbers[0] > whiteDescendingHandsNumbers[0]) {
                    String sig = intNumber(blackDescendingHandsNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    winResult = "tie";
                }
            } else if (blackHandsCategoryRanking == 5) { //三条
                if (blackRepeatNumbers[0] < whiteRepeatNumbers[0]) {
                    String sig = intNumber(whiteRepeatNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = intNumber(blackRepeatNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackHandsCategoryRanking == 6) { //两对
                for (int i = 0; i < 2; i++) {
                    if (blackRepeatNumbers[i] < whiteRepeatNumbers[i]) {
                        String sig = intNumber(whiteRepeatNumbers[i]);
                        winResult = "white wins - high card:" + sig;
                        break;
                    } else if (blackRepeatNumbers[i] > whiteRepeatNumbers[i]) {
                        String sig = intNumber(blackRepeatNumbers[i]);
                        winResult = "black wins - high card:" + sig;
                        break;
                    }
                }
                if (winResult == "") {
                    if (blackNoRepeatNumbers[0] < whiteNoRepeatNumbers[0]) {
                        String sig = intNumber(whiteNoRepeatNumbers[0]);
                        winResult = "white wins - high card:" + sig;
                    } else if (blackNoRepeatNumbers[0] > whiteNoRepeatNumbers[0]) {
                        String sig = intNumber(blackNoRepeatNumbers[0]);
                        winResult = "black wins - high card:" + sig;
                    } else {
                        winResult = "tie";
                    }
                }
            } else if (blackHandsCategoryRanking == 7) { //对子
                if (blackRepeatNumbers[0] < whiteRepeatNumbers[0]) {
                    String sig = intNumber(whiteRepeatNumbers[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackRepeatNumbers[0] > whiteRepeatNumbers[0]) {
                    String sig = intNumber(blackRepeatNumbers[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    for (int i = 0; i < 3; i++) {
                        if (blackNoRepeatNumbers[i] < whiteNoRepeatNumbers[i]) {
                            String sig = intNumber(whiteNoRepeatNumbers[i]);
                            winResult = "white wins - high card:" + sig;
                            break;
                        } else if (blackNoRepeatNumbers[i] > whiteNoRepeatNumbers[i]) {
                            String sig = intNumber(blackNoRepeatNumbers[i]);
                            winResult = "black wins - high card:" + sig;
                            break;
                        } else {
                            winResult = "tie";
                        }
                    }
                }
            } else { //散牌
                for (int i = 0; i < 5; i++) {
                    if (blackDescendingHandsNumbers[i] < whiteDescendingHandsNumbers[i]) {
                        String sig = intNumber(whiteDescendingHandsNumbers[i]);
                        winResult = "white wins - high card:" + sig;
                        break;
                    } else if (blackDescendingHandsNumbers[i] > whiteDescendingHandsNumbers[i]) {
                        String sig = intNumber(blackDescendingHandsNumbers[i]);
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
