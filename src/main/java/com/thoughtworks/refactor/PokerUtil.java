package com.thoughtworks.refactor;

import java.util.Arrays;
import java.util.HashSet;

public class PokerUtil {
    //数字转化并将其从大到小排序
    static int[] getDescendingHandsNumbers(String str) {
        int[] number = new int[5];
        String[] strArray = str.split("");
        int i;
        for (i = 0; i < 5; i++) {
            String c = strArray[i * 3];
            switch (c) {
                case "T":
                    number[i] = 10;
                    break;
                case "J":
                    number[i] = 11;
                    break;
                case "Q":
                    number[i] = 12;
                    break;
                case "K":
                    number[i] = 13;
                    break;
                case "A":
                    number[i] = 14;
                    break;
                default:
                    number[i] = Integer.valueOf(c);
                    break;
            }
        }

        Arrays.sort(number);
        int[] renumber = new int[number.length];
        for (i = 0; i < number.length; i++) {
            renumber[i] = number[number.length - i - 1];
        }
        return renumber;
    }

    //判断是什么牌
    static String judgeHandCategory(String hands) {
        String type = "";
        if (isStraightFlush(hands)) {
            type = "StraightFlush";
        } else if (isStraight(hands)) {
            type = "Straight";
        } else if (isFlush(hands)) {
            type = "Flush";
        } else if (isHighCard(hands)) {
            type = "HighCard";
        } else if (isOnePair(hands)) {
            type = "OnePair";
        } else if (isTwoPair(hands)) {
            type = "TwoPair";
        } else if (isThreeOfAKind(hands)) {
            type = "ThreeOfAKind";
        } else if (isFourOfAKind(hands)) {
            type = "FourOfAKind";
        } else { //四个数字相同——铁支
            type = "FullHouse";
        }

        return type;
    }

    private static boolean isFourOfAKind(String hands) {
        final int[] descendingCardNumbers = getDescendingHandsNumbers(hands);
        return descendingCardNumbers[0] != descendingCardNumbers[1] || descendingCardNumbers[3] != descendingCardNumbers[4];
    }

    private static boolean isThreeOfAKind(String hands) {
        return countDistinctNumbers(hands) == 3;
    }

    private static boolean isTwoPair(String hands) {
        final int distinctNumbersCount = countDistinctNumbers(hands);
        final int[] descendingCardNumbers = getDescendingHandsNumbers(hands);
        return distinctNumbersCount == 3 && ((descendingCardNumbers[0] == descendingCardNumbers[1] && descendingCardNumbers[2] == descendingCardNumbers[3]) || (descendingCardNumbers[1] == descendingCardNumbers[2] && descendingCardNumbers[3] == descendingCardNumbers[4]) || (descendingCardNumbers[0] == descendingCardNumbers[1] && descendingCardNumbers[3] == descendingCardNumbers[4]) && distinctNumbersCount == 3);
    }

    private static boolean isOnePair(String hands) {
        return countDistinctNumbers(hands) == 4;
    }

    private static boolean isHighCard(String hands) {
        return countDistinctNumbers(hands) == 5;
    }

    private static boolean isFlush(String hands) {
        return countSuits(hands) == 1 && countDistinctNumbers(hands) == 5;
    }

    private static boolean isStraight(String hands) {
        final int[] descendingCardNumbers = getDescendingHandsNumbers(hands);
        return descendingCardNumbers[0] - descendingCardNumbers[4] == 4 && (countDistinctNumbers(hands) == 5);
    }

    private static boolean isStraightFlush(String hands) {
        final int[] descendingCardNumbers = getDescendingHandsNumbers(hands);
        return (descendingCardNumbers[0] - descendingCardNumbers[4] == 4) && (countSuits(hands) == 1) && (countDistinctNumbers(hands) == 5);
    }

    private static int countSuits(String hands) {
        return getSuits(hands).size();
    }

    private static int countDistinctNumbers(String hands) {
        int i;
        HashSet<Integer> distinctNumbers = new HashSet<Integer>();
        for (i = 0; i < 5; i++) {
            distinctNumbers.add(getDescendingHandsNumbers(hands)[i]);
        }
        return distinctNumbers.size();
    }

    private static HashSet<String> getSuits(String hands) {
        String[] strArray = hands.split("");
        int i;
        String[] color = new String[5];
        for (i = 0; i < 5; i++) {
            color[i] = strArray[i * 3 + 1];
        }
        HashSet<String> suits = new HashSet<String>();
        for (i = 0; i < 5; i++) {
            suits.add(color[i]);
        }
        return suits;
    }

}
