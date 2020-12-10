package practice;

import java.util.Arrays;
import java.util.Comparator;

public class Kruskal {

    /**
     * 최소비용 신장 트리
     */

    public static void main(String[] args) {

        int[][] edges = {
                {1, 7, 12},
                {1, 4, 28},
                {1, 2, 67},
                {1, 5, 17},
                {2, 4, 24},
                {2, 5, 62},
                {3, 5, 20},
                {3, 6, 37},
                {4, 7, 13},
                {5, 6, 45},
                {5, 7, 73}
        };

        int nodeNum = 7;

        kruskal(edges, nodeNum);
    }

    private static void kruskal(int[][] edges, int N) {

        // 가중치 오름차순
        Arrays.sort(edges, Comparator.comparingInt(o -> o[2]));

        int[] nodes = new int[N + 1];  // idx 1부터 접근하기 위해 사이즈+1
        for (int i = 1; i <= N; i++) {
            nodes[i] = i;
        }

        int minV = 0;
        int edgeIdx = 0;

        // 사이클이 없어야 한다. (최소 신장 비용 '트리' 이므로)
        // union-find 결과로 모든 nodes 연결되면 트리가 됌
        while (!isAllMatch(nodes)) {

            int[] edge = edges[edgeIdx];
            int startNode = Math.min(edge[0], edge[1]);
            int endNode = Math.max(edge[0], edge[1]);

            // 연결되어 있지 않으면 연결
            if (nodes[startNode] != nodes[endNode]) {
                connectNodes(nodes, nodes[startNode], nodes[endNode]);
                minV += edge[2];
            }

            edgeIdx++;
        }

        System.out.println("minV = " + minV);
    }

    private static boolean isAllMatch(int[] nodes) {
        for (int i = 1; i < nodes.length - 2; i++) {
            if (nodes[i] != nodes[i + 1]) return false;
        }

        return true;
    }

    private static void connectNodes(int[] nodes, int edge1, int edge2) {
        // 노드 연결시, 더 작은 값 쪽으로 합친다.
        int start = Math.min(edge1, edge2);
        int end = Math.max(edge1, edge2);

        for (int i = 1; i <= nodes.length - 1; i++) {
            if (nodes[i] == end) nodes[i] = start;
        }
    }
}
