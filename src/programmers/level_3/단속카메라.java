package programmers.level_3;

import java.util.Arrays;
import java.util.Comparator;

/**
 * #구현, #다시풀기(완)
 *
 * 실패
 * 실패 사유
 * - 단속카메라 배치를 어떻게 해야할지 아예 모르겠다.
 * - 이분탐색으로 한다고 해도 최소가 되는 경우의 수는 전체탐색 아니면 찾을 수 없는거 아닌가..?
 */
public class 단속카메라 {

    public static void main(String[] args) {
        int n = new 단속카메라().solution(new int[][]{{-20, 15}, {-14, -5}, {-18, -13}, {-5, -3}});
        System.out.println(n);
    }


    public int solution(int[][] routes) {

        int cameraN = 1;
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        int cposition = routes[0][1];
        for (int i = 1; i < routes.length; i++) {
            System.out.println(cposition);

            if (routes[i][1] < cposition) {
                cposition = routes[i][1];
            }
            if (routes[i][0] > cposition) {
                cameraN++;
                cposition = routes[i][1];

            }
        }

        return cameraN;
    }

    public int solution2(int[][] routes) {

        Arrays.sort(routes, Comparator.comparingInt(a -> a[1]));

        int ans = 0;

        int last_camera = Integer.MIN_VALUE;

        for (int[] a : routes) {
            if (last_camera < a[0]) {
                ++ans;
                last_camera = a[1];
            }
        }
        return ans;
    }

}
