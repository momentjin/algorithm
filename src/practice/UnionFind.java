package practice;

import java.util.Arrays;

public class UnionFind {

    /**
     * 그래프 내 합집합 찾기
     */

    public static void main(String[] args) {

        int N = 5;
        int[][] edges = {
                {0, 1},
                {0, 2},
                {1, 2},
                {1, 3}
        };

        unionFind(edges, N);
    }

    public static void unionFind(int[][] edges, int N) {

        // init
        int[] nodes = new int[N];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = i;
        }

        // union-find
        for (int[] edge : edges) {
            int edge1 = Math.min(edge[0], edge[1]);
            int edge2 = Math.max(edge[0], edge[1]);

            // node가 연결되어 있지 않다면
            if (nodes[edge1] != nodes[edge2]) {
                connectNodes(nodes, nodes[edge1], nodes[edge2]);
            }
        }

        System.out.println(Arrays.toString(nodes));

        // node값이 같은 것 끼리 하나의 집합에 속함
        // 즉, 모두 같은 경우 집합은 1개 (모두 연결)
    }

    private static void connectNodes(int[] nodes, int edge1, int edge2) {

        // 노드 연결시, 더 작은 값 쪽으로 합친다.
        int start = Math.min(edge1, edge2);
        int end = Math.max(edge1, edge2);

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == end) nodes[i] = start;
        }
    }
}
