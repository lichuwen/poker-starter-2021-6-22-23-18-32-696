package com.thoughtworks.refactor;

import java.util.Arrays;
import java.util.HashSet;

public class PokerUtils {
    public static final String[] CATEGORY = {"StraightFlush", "FourOfAKind", "FullHouse", "Flush", "Straight", "ThreeOfAKind", "TwoPair", "OnePair", "HighCard"};

    //数字转化并将其从大到小排序
    static int[] convertHandAndSortDesc(String hand) {
        int[] number = new int[5];
        String[] strArray = hand.split("");
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
                    number[i] = Integer.parseInt(c);
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
    static String judgeHandCategory(String hand) {
        String type;
        String[] splitHands = hand.split("");
        int[] distinctNumbers = convertHandAndSortDesc(hand);
        int i;
        String[] suit = new String[5];
        for (i = 0; i < 5; i++) {
            suit[i] = splitHands[i * 3 + 1];
        }
        HashSet<Integer> suits = new HashSet<>();
        for (i = 0; i < 5; i++) {
            suits.add(distinctNumbers[i]);
        }
        HashSet<String> suitTypes = new HashSet<>();
        for (i = 0; i < 5; i++) {
            suitTypes.add(suit[i]);
        }
        if (suits.size() == 5) {
            if (distinctNumbers[0] - distinctNumbers[4] == 4 && suitTypes.size() == 1) { //五个相邻的数字且花色一样——同花顺
                type = "StraightFlush";
            } else if (distinctNumbers[0] - distinctNumbers[4] == 4) { //五个相邻数字——顺子
                type = "Straight";
            } else if (suitTypes.size() == 1) { //同一花色——同花
                type = "Flush";
            } else { //五个不相邻的数字——散牌
                type = "HighCard";
            }
        } else if (suits.size() == 4) { //一对相同，其余三个数字不同——对子
            type = "OnePair";
        } else if (suits.size() == 3) {
            if ((distinctNumbers[0] == distinctNumbers[1] && distinctNumbers[2] == distinctNumbers[3]) || (distinctNumbers[1] == distinctNumbers[2] && distinctNumbers[3] == distinctNumbers[4]) || (distinctNumbers[0] == distinctNumbers[1] && distinctNumbers[3] == distinctNumbers[4])) { //两对
                type = "TwoPair";
            } else { //三个数字相同，另外两个数字不同——三条
                type = "ThreeOfAKind";
            }
        } else {
            if (distinctNumbers[0] != distinctNumbers[1] || distinctNumbers[3] != distinctNumbers[4]) { //三个数字相同，另外两个数字相同——葫芦
                type = "FourOfAKind";
            } else { //四个数字相同——铁支
                type = "FullHouse";
            }
        }
        return type;
    }
}
