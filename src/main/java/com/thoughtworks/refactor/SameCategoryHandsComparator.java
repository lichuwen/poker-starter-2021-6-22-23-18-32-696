package com.thoughtworks.refactor;

public class SameCategoryHandsComparator {

    public static String getSameCategory(Hand blackHand, Hand whiteHand) {
        String winResult;
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
        return winResult;
    }

    public static String intNumber(int i) {
        String[] strNumber = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
        return strNumber[i - 2];
    }

    public static String compareStraightFlush(int[] descendingHandsNumbers, int[] descendingHandsNumbers2) {
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

    public static String compareHighCard(int[] descendingHandsNumbers, int[] descendingHandsNumbers2) {
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

    public static String compareOnePair(Hand blackHand, Hand whiteHand) {
        String winResult = "";
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

    public static String compareTwoPair( Hand blackHand, Hand whiteHand) {
        String winResult = "";
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
        if (winResult.equals("")) {
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

    public static String compareThreeOfAKind(int[] descendingRepeatNumbers, int[] descendingRepeatNumbers2) {
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

    public static String compareStraight(int[] descendingHandsNumbers, int[] descendingHandsNumbers2) {
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

    public static String compareFlush(int[] descendingHandsNumbers, int[] descendingHandsNumbers2) {
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

    public static String compareFullHouse(int[] distinctDescendingHandsNumbers, int[] distinctDescendingHandsNumbers2) {
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

    public static String compareFourOfAKind(int[] distinctDescendingHandsNumbers, int[] distinctDescendingHandsNumbers2) {
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

}
