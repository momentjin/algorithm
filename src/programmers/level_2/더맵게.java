package programmers.level_2;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 종류 : #
 * 시작일시 : 2020-11-03 01:31
 * 성공/실패 :
 * 소요시간 :
 */
public class 더맵게 {

    public static void main(String[] args) {
        int arr[] = new int[]{1, 2, 3, 9, 10, 12};
        int n = new 더맵게().solution(arr, 5);
        System.out.println(n);
    }

    public int solution(int[] scoville, int K) {

        Queue<Long> q = new PriorityQueue<>();
        for (long i : scoville) {
            q.add(i);
        }

        int cnt = 0;
        while (!isMatch(q, K) && q.size() >= 2) {
            long n1 = q.poll();
            long n2 = q.poll();

            long sum = n1 + n2 * 2;
            q.add(sum);
            cnt++;
        }

        if (q.size() == 1 && q.poll() < K)
            return -1;

        int answer = cnt;
        return answer;
    }

    public boolean isMatch(Queue<Long> q, long k) {
        Iterator<Long> it = q.iterator();
        while (it.hasNext()) {
            long n = it.next();
            if (n < k) return false;
        }

        return true;
    }
}
