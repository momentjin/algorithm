package programmers.level_3;

import java.util.*;

public class 가장먼노드 {

    public static void main(String[] args) {
        int n = new 가장먼노드().solution(6, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}});
        System.out.println(n);
    }

    public int solution(int n, int[][] edge) {

        boolean[] visit = new boolean[n + 1];
        int[] table = new int[n + 1];

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(1, 1));
        visit[1] = true;

        while (!q.isEmpty()) {

            Node curr = q.poll();

            for (int[] e : edge) {
                int start = e[0];
                int end = e[1];

                if ((start == curr.no || end == curr.no) ) {

                    int other = curr.no == start ? end : start;
                    if (visit[other]) continue;
                    visit[other] = true;

                    q.add(new Node(other, curr.cnt + 1));

                    if (table[other] == 0) {
                        table[other] = curr.cnt + 1;
                    } else
                        table[other] = Integer.min(table[other], curr.cnt + 1);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(i + " " + table[i]);
        }

        int max = Arrays.stream(table).max().getAsInt();
        return (int) Arrays.stream(table).filter(i -> i ==max).count();
    }

    class Node implements Comparable<Node> {
        int no;
        int cnt;

        public Node(int no, int cnt) {
            this.no = no;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(o.cnt, cnt);
        }

        @Override
        public String toString() {
            return "{" +
                    "no=" + no +
                    ", cnt=" + cnt +
                    '}';
        }
    }


}
