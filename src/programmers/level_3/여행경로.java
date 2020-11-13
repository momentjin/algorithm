package programmers.level_3;

import java.util.*;

/**
 * 종류 : #DFS
 * 시작일시 : 2020-11-13 19:24
 * 성공/실패 : 성공
 * 소요시간 : 20분
 */
public class 여행경로 {

    public static void main(String[] args) {

        String[][] t = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        String[] s = new 여행경로().solution(t);
        System.out.println(Arrays.toString(s));
    }

    static List<List<String>> result;
    static boolean[] visit;

    public String[] solution(String[][] tickets) {

        result = new ArrayList<>();
        visit = new boolean[tickets.length];

        List<String> list = new ArrayList<>();
        list.add("ICN");
        dfs(tickets, "ICN", list);

        result.sort(Comparator.comparing(Object::toString));

        String[] answer = new String[result.get(0).size()];
        for (int i = 0; i < result.get(0).size(); i++) {
            answer[i] = result.get(0).get(i);
        }

        return answer;
    }

    public void dfs(String[][] tickets, String curr, List<String> list) {

        if (list.size() == tickets.length + 1) {
            List<String> newList = new ArrayList<>(list);
            result.add(newList);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            String s = tickets[i][0];
            String e = tickets[i][1];

            if (s.equals(curr) && !visit[i]) {
                list.add(e);
                visit[i] = true;
                dfs(tickets, e, list);
                visit[i] = false;
                list.remove(list.lastIndexOf(e));
            }
        }

    }
}
