package programmers.level_4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 실패
 * 실패 사유
 * - 시간에 따라 처리할 떄, 어떤 식으로 처리해야 할지 모르겠음.
 */
public class 무지의먹방라이브 {


    public static void main(String[] args) {
        int n = new 무지의먹방라이브().solution(new int[]{8, 6, 4}, 15);
        System.out.println(n);
    }

    public int solution(int[] food_times, long k) {

        PriorityQueue<Item> q = new PriorityQueue<>(Comparator.comparingLong(item -> item.time));

        long totalTimeForEating = 0;

        for (int i = 0; i < food_times.length; i++) {
            q.add(new Item(i, food_times[i]));
            totalTimeForEating += food_times[i];
        }

        if (totalTimeForEating <= k) {
            return -1;
        }

        long beforeTime = 0;

        while (!q.isEmpty()) {

            int itemSize = q.size();
            Item item = q.peek();

            long timeForEating = (item.time - beforeTime) * itemSize;
            if (k - timeForEating > 0) {
                beforeTime = item.time;
                k = k - timeForEating;
                q.poll();
            } else {
                break;
            }
        }

        List<Item> list = new ArrayList<>(q);
        list.sort(Comparator.comparingInt(o -> o.idx));

        int idx = (int) (k % q.size());
        return list.get(idx).idx + 1;
    }

    class Item {

        int idx;
        long time;

        public Item(int idx, long time) {
            this.idx = idx;
            this.time = time;
        }
    }
}
