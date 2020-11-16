package programmers.level_3;

import java.util.*;

/**
 * 종류 : #힙
 * 시작일시 : 2020-11-16 18:12
 * 성공/실패 : 성공
 * 소요시간 : 20분
 */
public class 이중우선순위큐 {

    public static void main(String[] args) {
        String[] s = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        new 이중우선순위큐().solution(s);
    }

    public int[] solution(String[] operations) {

        PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> min = new PriorityQueue<>();

        for (String op : operations) {
            String o = op.split(" ")[0];
            int n = Integer.parseInt(op.split(" ")[1]);

            if (o.equals("I")) {
                max.add(n);
                min.add(n);
            } else if (n == 1 && !max.isEmpty()) {
                int v = max.poll();
                min.remove(v);
            } else if (n == -1 && !min.isEmpty()) {
                int v = min.poll();
                max.remove(v);
            }
        }

        int minV = 0;
        int maxV = 0;

        if (max.isEmpty() && !min.isEmpty()) {
            minV = min.poll();
            maxV = minV;
            for (Iterator<Integer> it = min.iterator(); it.hasNext(); ) {
                maxV = it.next();
            }
        } else if (!max.isEmpty() && min.isEmpty()) {
            maxV = max.poll();
            minV = maxV;
            for (Iterator<Integer> it = max.iterator(); it.hasNext(); ) {
                minV = it.next();
            }
        } else if (!max.isEmpty() && !min.isEmpty()) {
            maxV = max.poll();
            minV = min.poll();
        }

        return new int[]{maxV, minV};
    }
}
