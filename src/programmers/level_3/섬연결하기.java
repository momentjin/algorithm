package programmers.level_3;


import java.util.Arrays;
import java.util.Comparator;

/**
 * lv3
 * <p>
 * 종류 : #그래프, #다시풀기(완)
 * 시작일시 : 2020-11-13 19:24
 * 성공/실패 :
 * 소요시간 :
 * <p>
 * Q. 모든 섬이 연결되었다는 것을 증명할 수 있는 방법?
 * A. Union-Find 알고리즘.. 하나 배웠다
 */
public class 섬연결하기 {


    public static void main(String[] args) {
        int[][] arr = {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}};
        int n = 5;

        int min = new 섬연결하기().solution(n, arr);
        System.out.println();
        System.out.println(min);
    }

    public int solution(int n, int[][] costs) {


        int[] nodes = new int[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = i;
        }

        Arrays.sort(costs, Comparator.comparingInt(o -> o[2]));

        int answer = 0;
        int i = 0; // costIdx

        while (!isAllEquals(nodes)) {

            int start = costs[i][0];
            int end = costs[i][1];
            int cost = costs[i][2];

            // 두 다리가 연결되지 않았으면
            if (nodes[start] != nodes[end]) {
                updateNodes(nodes, start, end);
                answer += cost;
            }

            i++;
        }

        return answer;
    }

    boolean isAllEquals(int[] nodes) {

        for (int i = 0; i < nodes.length - 1; i++) {
            if (nodes[i] != nodes[i + 1]) return false;
        }

        return true;
    }

    void updateNodes(int[] nodes, int start, int end) {

        int startV = Math.min(nodes[start], nodes[end]);
        int endV = Math.max(nodes[start], nodes[end]);

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == endV) nodes[i] = startV;
        }
    }
}
