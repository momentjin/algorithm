package programmers.level_3;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 종류 : #구현 #트리
 * 시작일시 : 2020-11-17 00:28
 * 성공/실패 : 성공
 * 소요시간 : 30분
 */
public class 길찾기게임 {

    public static void main(String[] args) {
        int[][] a = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
        a = new 길찾기게임().solution(a);
        System.out.println(Arrays.toString(a[0]));
        System.out.println(Arrays.toString(a[1]));
    }

    public int[][] solution(int[][] nodeinfo) {

        List<Point> pointList = new ArrayList<>();
        int idx = 1;
        for (int[] node : nodeinfo) {
            pointList.add(new Point(idx++, node[0], node[1]));
        }

        pointList.sort((o1, o2) -> {
            if (o1.y == o2.y) {
                return Integer.compare(o1.x, o2.x);
            } else {
                return Integer.compare(o2.y, o1.y);
            }
        });

        Map<Integer, List<Point>> map = pointList
                .stream()
                .collect(Collectors.groupingBy(point -> point.y));

        List<Integer> list = new ArrayList<>(map.keySet());
        list.sort(Comparator.reverseOrder());

        Point root = map.get(list.get(0)).get(0);
        for (int i = 1; i < list.size(); i++) {
            List<Point> childs = map.get(list.get(i));
            for (Point c : childs) {
                insertNode(root, c);
            }
        }

        List<Integer> preList = new ArrayList<>();
        preOrder(root, preList);

        List<Integer> postList = new ArrayList<>();
        postOrder(root, postList);

        int[][] answer = new int[2][nodeinfo.length];
        for (int i=0; i<nodeinfo.length; i++) {
            answer[0][i] = preList.get(i);
            answer[1][i] = postList.get(i);
        }

        return answer;
    }

    public void insertNode(Point root, Point other) {

        if (root.x < other.x) {
            if (root.right != null) {
                insertNode(root.right, other);
            } else {
                root.right = other;
            }
        } else {
            if (root.left != null) {
                insertNode(root.left, other);
            } else {
                root.left = other;
            }
        }
    }

    public void preOrder(Point root, List<Integer> list) {

        list.add(root.no);

        if (root.left != null)
            preOrder(root.left, list);

        if (root.right != null)
            preOrder(root.right, list);
    }

    public void postOrder(Point root, List<Integer> postList) {
        if (root.left != null)
            postOrder(root.left, postList);

        if (root.right != null)
            postOrder(root.right, postList);

        postList.add(root.no);
    }

    class Point {

        Point left;
        Point right;

        public int x;
        public int y;
        int no;

        public Point(int no, int x, int y) {
            this.no = no;
            this.x = x;
            this.y = y;
        }
    }
}
