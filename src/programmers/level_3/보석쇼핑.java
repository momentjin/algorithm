package programmers.level_3;


import java.util.*;
import java.util.stream.Collectors;

/**
 * lv3
 * 종류 : #투포인터, #다시풀기(완)
 * 시작일시 : 2020-11-09 23:14
 * 성공/실패 : 실패
 * 소요시간 :
 * <p>
 * 이분탐색으로 풀 수 있을 것이라고 생각했지만 오산이었다. 최대 200억이므로 N * logN도 불가능하다.
 */
public class 보석쇼핑 {

    public static void main(String[] args) {

        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        int[] a = new 보석쇼핑().solution(gems);
        System.out.println(Arrays.toString(a));
    }

    static Set<String> gemSet;

    public int[] solution(String[] gems) {

        gemSet = Arrays.stream(gems).collect(Collectors.toSet());

        Map<String, Integer> countMap = new HashMap<>();
        int startIdx = 0, endIdx = 0;
        int answer = Integer.MAX_VALUE;
        int[] result = new int[2];

        while (true) {

            if (countMap.size() == gemSet.size()) {

                if (countMap.containsKey(gems[startIdx])) {
                    countMap.put(gems[startIdx], countMap.get(gems[startIdx]) - 1);
                    if (countMap.get(gems[startIdx]) == 0) {
                        countMap.remove(gems[startIdx]);
                    }

                } else {
                    countMap.put(gems[startIdx], 1);
                }

                startIdx++;

            } else if (endIdx == gems.length) {
                break;
            } else {
                if (countMap.containsKey(gems[endIdx])) {
                    countMap.put(gems[endIdx], countMap.get(gems[endIdx]) + 1);
                } else {
                    countMap.put(gems[endIdx], 1);
                }

                endIdx++;
            }

            if (countMap.size() == gemSet.size()) {
                if (Math.abs(endIdx - startIdx) < answer) {
                    answer = Math.abs(endIdx - startIdx);
                    result[0] = startIdx + 1;
                    result[1] = endIdx;
                }
            }
        }


        return result;
    }
}
