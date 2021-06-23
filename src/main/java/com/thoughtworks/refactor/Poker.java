package com.thoughtworks.refactor;

import java.util.*;

public class Poker {

    public static final String[] CATEGORY = {"StraightFlush", "FourOfAKind", "FullHouse", "Flush", "Straight", "ThreeOfAKind", "TwoPair", "OnePair", "HighCard"};

    public String compareResult(String blackCard, String whiteCard) {
        String winResult = "";
        String blackCardType = judgeCardType(blackCard);
        String whiteCardType = judgeCardType(whiteCard);
        String[] category = CATEGORY;
        int[] blackCardNumber = convertCard(blackCard);
        int[] whiteCardNumber = convertCard(whiteCard);
        int blackCardIndex = judgeCardIndex(blackCardType);
        int whiteCardIndex = judgeCardIndex(whiteCardType);
        int[] blackCardSort = sortCardNumber(blackCardNumber);
        int[] whiteCardSort = sortCardNumber(whiteCardNumber);
        int[] blackCardRepeat = noOrRepeatCardNumber(blackCardNumber, 0);
        int[] whiteCardRepeat = noOrRepeatCardNumber(whiteCardNumber, 0);
        int[] blackCardNoRepeat = noOrRepeatCardNumber(blackCardNumber, 1);
        int[] whiteCardNoRepeat = noOrRepeatCardNumber(whiteCardNumber, 1);
        if (blackCardIndex < whiteCardIndex) {
            winResult = "black wins - " + category[blackCardIndex];
        } else if (blackCardIndex > whiteCardIndex) {
            winResult = "white wins - " + category[whiteCardIndex];
        } else {
            if (blackCardIndex == 0) { //同花顺
                if (blackCardNumber[0] < whiteCardNumber[0]) {
                    String sig = intNumber(whiteCardNumber[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackCardNumber[0] > whiteCardNumber[0]) {
                    String sig = intNumber(blackCardNumber[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    winResult = "tie";
                }
            } else if (blackCardIndex == 1) { //铁支
                if (blackCardSort[0] < whiteCardSort[0]) {
                    String sig = intNumber(whiteCardSort[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = intNumber(blackCardSort[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackCardIndex == 2) { //葫芦
                if (blackCardSort[0] < whiteCardSort[0]) {
                    String sig = intNumber(whiteCardSort[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = intNumber(blackCardSort[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackCardIndex == 3) { //同花
                for (int i = 0; i < 5; i++) {
                    if (blackCardNumber[i] < whiteCardNumber[i]) {
                        String sig = intNumber(whiteCardNumber[i]);
                        winResult = "white wins - high card:" + sig;
                        break;
                    } else if (blackCardNumber[i] > whiteCardNumber[i]) {
                        String sig = intNumber(blackCardNumber[i]);
                        winResult = "black wins - high card:" + sig;
                        break;
                    } else {
                        winResult = "tie";
                    }
                }
            } else if (blackCardIndex == 4) { //顺子
                if (blackCardNumber[0] < whiteCardNumber[0]) {
                    String sig = intNumber(whiteCardNumber[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackCardNumber[0] > whiteCardNumber[0]) {
                    String sig = intNumber(blackCardNumber[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    winResult = "tie";
                }
            } else if (blackCardIndex == 5) { //三条
                if (blackCardRepeat[0] < whiteCardRepeat[0]) {
                    String sig = intNumber(whiteCardRepeat[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = intNumber(blackCardRepeat[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackCardIndex == 6) { //两对
                for (int i = 0; i < 2; i++) {
                    if (blackCardRepeat[i] < whiteCardRepeat[i]) {
                        String sig = intNumber(whiteCardRepeat[i]);
                        winResult = "white wins - high card:" + sig;
                        break;
                    } else if (blackCardRepeat[i] > whiteCardRepeat[i]) {
                        String sig = intNumber(blackCardRepeat[i]);
                        winResult = "black wins - high card:" + sig;
                        break;
                    }
                }
                if (winResult.equals("")) {
                    if (blackCardNoRepeat[0] < whiteCardNoRepeat[0]) {
                        String sig = intNumber(whiteCardNoRepeat[0]);
                        winResult = "white wins - high card:" + sig;
                    } else if (blackCardNoRepeat[0] > whiteCardNoRepeat[0]) {
                        String sig = intNumber(blackCardNoRepeat[0]);
                        winResult = "black wins - high card:" + sig;
                    } else {
                        winResult = "tie";
                    }
                }
            } else if (blackCardIndex == 7) { //对子
                if (blackCardRepeat[0] < whiteCardRepeat[0]) {
                    String sig = intNumber(whiteCardRepeat[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackCardRepeat[0] > whiteCardRepeat[0]) {
                    String sig = intNumber(blackCardRepeat[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    for (int i = 0; i < 3; i++) {
                        if (blackCardNoRepeat[i] < whiteCardNoRepeat[i]) {
                            String sig = intNumber(whiteCardNoRepeat[i]);
                            winResult = "white wins - high card:" + sig;
                            break;
                        } else if (blackCardNoRepeat[i] > whiteCardNoRepeat[i]) {
                            String sig = intNumber(blackCardNoRepeat[i]);
                            winResult = "black wins - high card:" + sig;
                            break;
                        } else {
                            winResult = "tie";
                        }
                    }
                }
            } else { //散牌
                for (int i = 0; i < 5; i++) {
                    if (blackCardNumber[i] < whiteCardNumber[i]) {
                        String sig = intNumber(whiteCardNumber[i]);
                        winResult = "white wins - high card:" + sig;
                        break;
                    } else if (blackCardNumber[i] > whiteCardNumber[i]) {
                        String sig = intNumber(blackCardNumber[i]);
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

    private int[] sortCardNumber(int[] number) {
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
        int[] arrayresult = new int[list.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : list) {
            arrayresult[i] = entry.getKey();
            i++;
        }
        return arrayresult;
    }

    //先获得数组中每个元素出现的次数，然后再进行计算出现次数大于1的和出现次数等于1的
    private int[] noOrRepeatCardNumber(int[] number, int flag) {
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
        for (Integer integer : hashSet) {
            result[i] = integer;
            i++;
        }
        int[] reResult = new int[result.length];
        for (i = 0; i < result.length; i++) {
            reResult[i] = result[result.length - i - 1];
        }
        return reResult;
    }

    private int judgeCardIndex(String strType) {
        int index = -1;
        String[] type = {"StraightFlush", "FourOfAKind", "FullHouse", "Flush", "Straight", "ThreeOfAKind", "TwoPair", "OnePair", "HighCard"};
        for (int i = 0; i < 9; i++) {
            if (type[i].equals(strType)) {
                index = i;
            }
        }
        return index;
    }

    //判断是什么牌
    private String judgeCardType(String card) {
        String type;
        String[] strArray = card.split("");
        int[] number = convertCard(card);
        int i;
        String[] color = new String[5];
        for (i = 0; i < 5; i++) {
            color[i] = strArray[i * 3 + 1];
        }
        HashSet<Integer> hashSetNumber = new HashSet<>();
        for (i = 0; i < 5; i++) {
            hashSetNumber.add(number[i]);
        }
        HashSet<String> hashSetType = new HashSet<>();
        for (i = 0; i < 5; i++) {
            hashSetType.add(color[i]);
        }
        if (hashSetNumber.size() == 5) {
            if (number[0] - number[4] == 4 && hashSetType.size() == 1) { //五个相邻的数字且花色一样——同花顺
                type = "StraightFlush";
            } else if (number[0] - number[4] == 4) { //五个相邻数字——顺子
                type = "Straight";
            } else if (hashSetType.size() == 1) { //同一花色——同花
                type = "Flush";
            } else { //五个不相邻的数字——散牌
                type = "HighCard";
            }
        } else if (hashSetNumber.size() == 4) { //一对相同，其余三个数字不同——对子
            type = "OnePair";
        } else if (hashSetNumber.size() == 3) {
            if ((number[0] == number[1] && number[2] == number[3]) || (number[1] == number[2] && number[3] == number[4]) || (number[0] == number[1] && number[3] == number[4])) { //两对
                type = "TwoPair";
            } else { //三个数字相同，另外两个数字不同——三条
                type = "ThreeOfAKind";
            }
        } else {
            if (number[0] != number[1] || number[3] != number[4]) { //三个数字相同，另外两个数字相同——葫芦
                type = "FourOfAKind";
            } else { //四个数字相同——铁支
                type = "FullHouse";
            }
        }
        return type;
    }

    //数字转化并将其从大到小排序
    private int[] convertCard(String card) {
        int[] number = new int[5];
        String[] strArray = card.split("");
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
}
