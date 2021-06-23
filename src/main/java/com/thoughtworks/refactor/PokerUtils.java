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

    //判断是什么牌.
    static String judgeHandCategory(String hand) {
        String type;
        if (convertHandAndSortDesc(hand)[0] - convertHandAndSortDesc(hand)[4] == 4 && getSuits(hand).size() == 1 && getDistinctNumbers(hand).size() == 5) { //五个相邻的数字且花色一样——同花顺
            type = "StraightFlush";
        } else if (convertHandAndSortDesc(hand)[0] - convertHandAndSortDesc(hand)[4] == 4 && getDistinctNumbers(hand).size() == 5) { //五个相邻数字——顺子
            type = "Straight";
        } else if (getSuits(hand).size() == 1 && getDistinctNumbers(hand).size() == 5) { //同一花色——同花
            type = "Flush";
        } else if(getDistinctNumbers(hand).size() == 5){ //五个不相邻的数字——散牌
            type = "HighCard";
        } else if (getDistinctNumbers(hand).size() == 4) { //一对相同，其余三个数字不同——对子
            type = "OnePair";
        } else if (getDistinctNumbers(hand).size() == 3) {
            if ((convertHandAndSortDesc(hand)[0] == convertHandAndSortDesc(hand)[1] && convertHandAndSortDesc(hand)[2] == convertHandAndSortDesc(hand)[3]) || (convertHandAndSortDesc(hand)[1] == convertHandAndSortDesc(hand)[2] && convertHandAndSortDesc(hand)[3] == convertHandAndSortDesc(hand)[4]) || (convertHandAndSortDesc(hand)[0] == convertHandAndSortDesc(hand)[1] && convertHandAndSortDesc(hand)[3] == convertHandAndSortDesc(hand)[4])) { //两对
                type = "TwoPair";
            } else { //三个数字相同，另外两个数字不同——三条
                type = "ThreeOfAKind";
            }
        } else {
            if (convertHandAndSortDesc(hand)[0] != convertHandAndSortDesc(hand)[1] || convertHandAndSortDesc(hand)[3] != convertHandAndSortDesc(hand)[4]) { //三个数字相同，另外两个数字相同——葫芦
                type = "FourOfAKind";
            } else { //四个数字相同——铁支
                type = "FullHouse";
            }
        }
        return type;
    }

    private static HashSet<Integer> getDistinctNumbers(String hand) {
        return getDistinctNumbers(convertHandAndSortDesc(hand));
    }

    private static HashSet<String> getSuits(String hand) {
        int i;
        String[] splitHands = hand.split("");
        String[] suit = new String[5];
        for (i = 0; i < 5; i++) {
            suit[i] = splitHands[i * 3 + 1];
        }
        HashSet<String> suitTypes = new HashSet<>();
        for (i = 0; i < 5; i++) {
            suitTypes.add(suit[i]);
        }
        return suitTypes;
    }

    private static HashSet<Integer> getDistinctNumbers(int[] numbers) {
        int i;
        HashSet<Integer> distinctNumbers = new HashSet<>();
        for (i = 0; i < 5; i++) {
            distinctNumbers.add(numbers[i]);
        }
        return distinctNumbers;
    }
}
