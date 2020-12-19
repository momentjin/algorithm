package programmers.level_4;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * #union-find (비슷하게 계속 부모를 갱신)
 * 성공
 * 소요 시간 ; 50분 정도
 *
 * - 처음에 부모를 계속 갱신하면 되겠구나 싶었는데, 막상 복잡도를 따져보니 최악 조건에서 시간 초과가 날 것 같았다. (알고 보니 제대로 계산하지 않았음..)
 * - 어쨌든 풀었음.
 */
class 호텔방배정 {

    public static void main(String[] args) {
        long[] a = new programmers.재시도_안한_문제.호텔방배정().solution(10, new long[]{1, 3, 4, 1, 3, 1});
        System.out.println(Arrays.toString(a));
    }

    static Map<Long, Long> map;

    public long[] solution(long k, long[] room_number) {

        map = new HashMap<>();
        long[] answer = new long[room_number.length];
        int idx = 0;

        for (long i = 0; i < room_number.length; i++) {

            long curr = room_number[(int) i];

            if (!map.containsKey(curr)) {
                answer[idx++] = curr;
                map.put(curr, find(curr + 1));
            } else {

                long findV = find(curr);
                answer[idx++] = findV;
                map.put(findV, find(findV + 1));
            }
        }

        return answer;
    }

    private long find(long curr) {
        if (!map.containsKey(curr)) return curr;
        map.put(curr, find(map.get(curr)));
        return map.get(curr);
    }
}
