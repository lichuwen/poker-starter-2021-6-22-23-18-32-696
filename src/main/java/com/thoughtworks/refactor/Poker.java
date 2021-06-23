package com.thoughtworks.refactor;

import java.util.*;

public class Poker {

    public static final String[] HANDS_CATEGORY = {"StraightFlush", "FourOfAKind", "FullHouse", "Flush", "Straight", "ThreeOfAKind", "TwoPair", "OnePair", "HighCard"};

    public String compareResult(String blackHands, String whiteHands) {
        String winResult = "";
        Hand blackHand = new Hand(blackHands);
        String blackHandsCategory = PokerUtil.judgeHandCategory(blackHand);
        int[] blackDescendingHandsNumbers = PokerUtil.getDescendingHandsNumbers(blackHands);
        int blackHandsCategoryRanking = judgeHandsCategoryRanking(blackHandsCategory);
        int[] blackDistinctDescendingHandsNumbers = getDistinctDescendingHandsNumbers(blackDescendingHandsNumbers);
        int[] blackRepeatNumbers = getDescendingRepeatNumbers(blackDescendingHandsNumbers);
        int[] blackNoRepeatNumbers = getDescendingNoRepeatNumbers(blackDescendingHandsNumbers);

        Hand whiteHand = new Hand(whiteHands);
        String whiteHandsCategory = PokerUtil.judgeHandCategory(whiteHand);
        int[] whiteDescendingHandsNumbers = PokerUtil.getDescendingHandsNumbers(whiteHands);
        int whiteHandsCategoryRanking = judgeHandsCategoryRanking(whiteHandsCategory);
        int[] whiteDistinctDescendingHandsNumbers = getDistinctDescendingHandsNumbers(whiteDescendingHandsNumbers);
        int[] whiteRepeatNumbers = getDescendingRepeatNumbers(whiteDescendingHandsNumbers);
        int[] whiteNoRepeatNumbers = getDescendingNoRepeatNumbers(whiteDescendingHandsNumbers);
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

    private int[] getDescendingNoRepeatNumbers(int[] blackDescendingHandsNumbers) {
        return noOrRepeatNumber(blackDescendingHandsNumbers, 1);
    }

    private int[] getDescendingRepeatNumbers(int[] blackDescendingHandsNumbers) {
        return noOrRepeatNumber(blackDescendingHandsNumbers, 0);
    }

    private String intNumber(int i) {
        String[] strNumber = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
        return strNumber[i - 2];
    }

    private int[] getDistinctDescendingHandsNumbers(int[] number) {
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
    private int[] noOrRepeatNumber(int[] number, int flag) {
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

    private int judgeHandsCategoryRanking(String category) {
        int index = -1;
        String[] type = {"StraightFlush", "FourOfAKind", "FullHouse", "Flush", "Straight", "ThreeOfAKind", "TwoPair", "OnePair", "HighCard"};
        for (int i = 0; i < 9; i++) {
            if (type[i].equals(category)) {
                index = i;
            }
        }
        return index;
    }

}
