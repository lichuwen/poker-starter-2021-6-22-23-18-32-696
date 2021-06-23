package com.thoughtworks.refactor;

import java.util.*;

public class Poker {

    public String compareResult(String blackHand, String whiteHand) {
        String winResult = "";
        String blackHandCategory = PokerUtils.judgeHandCategory(blackHand);
        String whiteHandCategory = PokerUtils.judgeHandCategory(whiteHand);
        String[] category = PokerUtils.CATEGORY;
        int[] blackHands = PokerUtils.convertHandAndSortDesc(blackHand);
        int[] whiteHands = PokerUtils.convertHandAndSortDesc(whiteHand);
        int blackRanking = judgeHandRanking(blackHandCategory);
        int whiteRanking = judgeHandRanking(whiteHandCategory);
        int[] blackHandSort = sortHandNumber(blackHands);
        int[] whiteHandSort = sortHandNumber(whiteHands);
        int[] blackHandRepeat = getHandRepeat(blackHands);
        int[] whiteHandRepeat = getHandRepeat(whiteHands);
        int[] blackHandNoRepeat = getHandNoRepeat(blackHands);
        int[] whiteHandNoRepeat = getHandNoRepeat(whiteHands);
        if (blackRanking < whiteRanking) {
            winResult = "black wins - " + category[blackRanking];
        } else if (blackRanking > whiteRanking) {
            winResult = "white wins - " + category[whiteRanking];
        } else {
            if (blackRanking == 0) { //同花顺
                if (blackHands[0] < whiteHands[0]) {
                    String sig = intNumber(whiteHands[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackHands[0] > whiteHands[0]) {
                    String sig = intNumber(blackHands[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    winResult = "tie";
                }
            } else if (blackRanking == 1) { //铁支
                if (blackHandSort[0] < whiteHandSort[0]) {
                    String sig = intNumber(whiteHandSort[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = intNumber(blackHandSort[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackRanking == 2) { //葫芦
                if (blackHandSort[0] < whiteHandSort[0]) {
                    String sig = intNumber(whiteHandSort[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = intNumber(blackHandSort[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackRanking == 3) { //同花
                for (int i = 0; i < 5; i++) {
                    if (blackHands[i] < whiteHands[i]) {
                        String sig = intNumber(whiteHands[i]);
                        winResult = "white wins - high card:" + sig;
                        break;
                    } else if (blackHands[i] > whiteHands[i]) {
                        String sig = intNumber(blackHands[i]);
                        winResult = "black wins - high card:" + sig;
                        break;
                    } else {
                        winResult = "tie";
                    }
                }
            } else if (blackRanking == 4) { //顺子
                if (blackHands[0] < whiteHands[0]) {
                    String sig = intNumber(whiteHands[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackHands[0] > whiteHands[0]) {
                    String sig = intNumber(blackHands[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    winResult = "tie";
                }
            } else if (blackRanking == 5) { //三条
                if (blackHandRepeat[0] < whiteHandRepeat[0]) {
                    String sig = intNumber(whiteHandRepeat[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = intNumber(blackHandRepeat[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackRanking == 6) { //两对
                for (int i = 0; i < 2; i++) {
                    if (blackHandRepeat[i] < whiteHandRepeat[i]) {
                        String sig = intNumber(whiteHandRepeat[i]);
                        winResult = "white wins - high card:" + sig;
                        break;
                    } else if (blackHandRepeat[i] > whiteHandRepeat[i]) {
                        String sig = intNumber(blackHandRepeat[i]);
                        winResult = "black wins - high card:" + sig;
                        break;
                    }
                }
                if (winResult.equals("")) {
                    if (blackHandNoRepeat[0] < whiteHandNoRepeat[0]) {
                        String sig = intNumber(whiteHandNoRepeat[0]);
                        winResult = "white wins - high card:" + sig;
                    } else if (blackHandNoRepeat[0] > whiteHandNoRepeat[0]) {
                        String sig = intNumber(blackHandNoRepeat[0]);
                        winResult = "black wins - high card:" + sig;
                    } else {
                        winResult = "tie";
                    }
                }
            } else if (blackRanking == 7) { //对子
                if (blackHandRepeat[0] < whiteHandRepeat[0]) {
                    String sig = intNumber(whiteHandRepeat[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackHandRepeat[0] > whiteHandRepeat[0]) {
                    String sig = intNumber(blackHandRepeat[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    for (int i = 0; i < 3; i++) {
                        if (blackHandNoRepeat[i] < whiteHandNoRepeat[i]) {
                            String sig = intNumber(whiteHandNoRepeat[i]);
                            winResult = "white wins - high card:" + sig;
                            break;
                        } else if (blackHandNoRepeat[i] > whiteHandNoRepeat[i]) {
                            String sig = intNumber(blackHandNoRepeat[i]);
                            winResult = "black wins - high card:" + sig;
                            break;
                        } else {
                            winResult = "tie";
                        }
                    }
                }
            } else { //散牌
                for (int i = 0; i < 5; i++) {
                    if (blackHands[i] < whiteHands[i]) {
                        String sig = intNumber(whiteHands[i]);
                        winResult = "white wins - high card:" + sig;
                        break;
                    } else if (blackHands[i] > whiteHands[i]) {
                        String sig = intNumber(blackHands[i]);
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

    private int[] getHandNoRepeat(int[] blackHands) {
        return noOrRepeatHandNumber(blackHands, 1);
    }

    private int[] getHandRepeat(int[] blackHands) {
        return noOrRepeatHandNumber(blackHands, 0);
    }

    private String intNumber(int i) {
        String[] strNumber = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
        return strNumber[i - 2];
    }

    private int[] sortHandNumber(int[] number) {
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
    private int[] noOrRepeatHandNumber(int[] number, int flag) {
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

    private int judgeHandRanking(String strType) {
        int index = -1;
        String[] type = {"StraightFlush", "FourOfAKind", "FullHouse", "Flush", "Straight", "ThreeOfAKind", "TwoPair", "OnePair", "HighCard"};
        for (int i = 0; i < 9; i++) {
            if (type[i].equals(strType)) {
                index = i;
            }
        }
        return index;
    }

}
