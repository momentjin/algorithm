package programmers.level_3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * 종류 : #힙
 * 시작일시 : 20:50
 * 성공/실패 : 성공
 * 소요시간 : 12분
 */
public class 야근지수 {

    public static void main(String[] args) {
        int n = 4;
        int works[] = new int[]{4, 3, 3};

        long a = new 야근지수().solution(4, works);
        System.out.println(a);
    }

    public long solution(int n, int[] works) {

        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
        for (int work : works) {
            q.add(work);
        }

        int sum = Arrays.stream(works).sum();
        if (sum <= n) {
            return 0;
        }

        int inputN = n;
        while (!q.isEmpty() && inputN > 0) {
            int max = q.poll();
            q.add(max - 1);
            inputN--;
        }

        Iterator<Integer> it = q.iterator();
        long answer = 0;
        while (it.hasNext()) {
            int a = it.next();
            answer += a * a;
        }

        return answer;
    }
}
