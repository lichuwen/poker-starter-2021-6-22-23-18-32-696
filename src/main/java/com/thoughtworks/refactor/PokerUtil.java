package com.thoughtworks.refactor;

import java.util.*;

public class PokerUtil {
    //数字转化并将其从大到小排序
    static int[] getDescendingHandsNumbers(Hand hand) {
        int[] number = new int[5];
        String[] strArray = hand.getHands().split("");
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
    static String judgeHandCategory(Hand hand) {
        String type = "";
        if (isStraightFlush(hand.getHands())) {
            type = "StraightFlush";
        } else if (isStraight(hand.getHands())) {
            type = "Straight";
        } else if (isFlush(hand.getHands())) {
            type = "Flush";
        } else if (isHighCard(hand.getHands())) {
            type = "HighCard";
        } else if (isOnePair(hand.getHands())) {
            type = "OnePair";
        } else if (isTwoPair(hand.getHands())) {
            type = "TwoPair";
        } else if (isThreeOfAKind(hand.getHands())) {
            type = "ThreeOfAKind";
        } else if (isFourOfAKind(hand.getHands())) {
            type = "FourOfAKind";
        } else { //四个数字相同——铁支
            type = "FullHouse";
        }

        return type;
    }

    private static boolean isFourOfAKind(String hands) {
        final int[] descendingCardNumbers = getDescendingHandsNumbers(new Hand(hands));
        return descendingCardNumbers[0] != descendingCardNumbers[1] || descendingCardNumbers[3] != descendingCardNumbers[4];
    }

    private static boolean isThreeOfAKind(String hands) {
        return countDistinctNumbers(hands) == 3;
    }

    private static boolean isTwoPair(String hands) {
        final int distinctNumbersCount = countDistinctNumbers(hands);
        final int[] descendingCardNumbers = getDescendingHandsNumbers(new Hand(hands));
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
        final int[] descendingCardNumbers = getDescendingHandsNumbers(new Hand(hands));
        return descendingCardNumbers[0] - descendingCardNumbers[4] == 4 && (countDistinctNumbers(hands) == 5);
    }

    private static boolean isStraightFlush(String hands) {
        final int[] descendingCardNumbers = getDescendingHandsNumbers(new Hand(hands));
        return (descendingCardNumbers[0] - descendingCardNumbers[4] == 4) && (countSuits(hands) == 1) && (countDistinctNumbers(hands) == 5);
    }

    private static int countSuits(String hands) {
        return getSuits(hands).size();
    }

    private static int countDistinctNumbers(String hands) {
        int i;
        HashSet<Integer> distinctNumbers = new HashSet<Integer>();
        for (i = 0; i < 5; i++) {
            distinctNumbers.add(getDescendingHandsNumbers(new Hand(hands))[i]);
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

    static int[] getDistinctDescendingHandsNumbers(int[] number) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < number.length; i++) {
            if (map.get(number[i]) != null) {
                map.put(number[i], map.get(number[i]) + 1);
            } else {
                map.put(number[i], 1);
            }
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>();
        list.addAll(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> arg0, Map.Entry<Integer, Integer> arg1) {
                return arg1.getValue().compareTo(arg0.getValue());
            }
        });
        int[] arrayresult = new int[list.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : list) {
            arrayresult[i] = entry.getKey();
            i++;
        }
        return arrayresult;
    }

    //先获得数组中每个元素出现的次数，然后再进行计算出现次数大于1的和出现次数等于1的
    static int[] noOrRepeatNumber(int[] number, int flag) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < number.length; i++) {
            if (map.get(number[i]) != null) {
                map.put(number[i], map.get(number[i]) + 1);
            } else {
                map.put(number[i], 1);
            }
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>();
        list.addAll(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> arg0, Map.Entry<Integer, Integer> arg1) {
                return arg1.getValue().compareTo(arg0.getValue());
            }
        });
        int[] repeatnumber = new int[list.size()];
        int[] norepeatnumber = new int[list.size()];
        int i = 0;
        if (flag == 0) {
            for (Map.Entry<Integer, Integer> entry : list) {
                if (entry.getValue() > 1) {
                    repeatnumber[i] = entry.getKey();
                    i++;
                }
            }
        } else {
            for (Map.Entry<Integer, Integer> entry : list) {
                if (entry.getValue() == 1) {
                    norepeatnumber[i] = entry.getKey();
                    i++;
                }
            }
        }
        HashSet<Integer> hashSet = new HashSet<>();
        if (flag == 0) {
            for (i = 0; i < repeatnumber.length; i++) {
                hashSet.add(repeatnumber[i]);
            }
        } else {
            for (i = 0; i < norepeatnumber.length; i++) {
                hashSet.add(norepeatnumber[i]);
            }
        }
        hashSet.remove(0);
        int[] result = new int[hashSet.size()];
        i = 0;
        Iterator<Integer> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            result[i] = iterator.next();
            i++;
        }
        int[] reResult = new int[result.length];
        for (i = 0; i < result.length; i++) {
            reResult[i] = result[result.length - i - 1];
        }
        return reResult;
    }
}
