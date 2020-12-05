package programmers.level_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * lv3
 * #구현 #다시풀기(완)
 * 실패
 * 실패 사유
 * - DFS로 풀었으나 시간초과 발생
 * - 더 효율적으로 어떻게 풀어야할지 모르겠음
 *
 * 내가 잘못 생각한 것
 * - 완전탐색시 정방향, 역방향을 나눠 탐색했는데 이게 중복된 호출이 많아 시간초과 발생했던 것.
 */
public class 외벽점검 {


    public static void main(String[] args) {
        System.out.println(1 << 5);
        int n = 12;
        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};
        int s = new 외벽점검().solution2(n, weak, dist);
    }

    static boolean[] distVisit;
    static List<List<Integer>> distP;
    static int n;
    static int answer;
    static boolean[] visit;

    public int solution2(int n, int[] weak, int[] dist) {

        외벽점검.n = n;
        answer = Integer.MAX_VALUE;
        distP = new ArrayList<>();
        distVisit = new boolean[dist.length];

        List<Integer> dist2 = Arrays.stream(dist)
                .boxed()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());

        // ------------------ 원형 dist를 선형으로 변경한다.
        List<List<Integer>> newWeak = new ArrayList<>();
        for (int k = 0; k < weak.length; k++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = k; j < weak.length; j++) {
                temp.add(weak[j]);
            }
            for (int j = 0; j < k; j++) {
                temp.add(weak[j] + n);
            }

            newWeak.add(temp);
        }


        // dist 선택
        for (int d = 1; d <= dist2.size(); d++) {

            // 친구를 한명씩 늘려가면서 dist를 정한다
            // idx: 1
            // idx: 1, 2
            // idx: 1, 2, 3 ...
            List<Integer> subDist = dist2.subList(0, d);

            // 선택한 dist들로 순서를 정한 순열리스트를 구한다
            distP.clear();
            p(subDist, new ArrayList<>());

            // 순서를 정했으니 이제 검사를 해야함
            for (List<Integer> p : distP) {
                boolean isSuccess = check(p, newWeak);
                if (isSuccess) return d;
            }
        }

        return -1;
    }

    public boolean check(List<Integer> dist, List<List<Integer>> newWeak) {

        for (List<Integer> weak : newWeak) {

            visit = new boolean[n];

            for (int d : dist) {
                for (int w : weak) {

                    int v = w >= n ? w - n : w;
                    if (visit[v]) continue;

                    int start = w;
                    int end = w + d;

                    for (int j = start; j <= end; j++) {
                        v = j >= n ? j - n : j;
                        visit[v] = true;
                    }

                    break;
                }
            }

            if (isAllVisit(weak)) {
                return true; // true
            }
        }

        return false;
    }

    public void p(List<Integer> arr, List<Integer> result) {

        if (result.size() == arr.size()) {
            distP.add(new ArrayList<>(result));
            return;
        }

        for (int i = 0; i < arr.size(); i++) {
            if (!distVisit[i]) {

                distVisit[i] = true;
                result.add(arr.get(i));

                p(arr, result);

                distVisit[i] = false;
                result.remove(arr.get(i));
            }
        }
    }

    private boolean isAllVisit(List<Integer> weak) {

        for (int j=0; j<weak.size(); j++) {
            int v = weak.get(j) >= n ? weak.get(j) - n : weak.get(j);
            if (!visit[v]) return false;
        }

        return true;
    }
}
