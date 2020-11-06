package programmers.level_2;

import java.util.ArrayList;
import java.util.List;

/**
 * 종류 : #DFS, #순열, #버
 * 시작일시 : 2020-10-29 02:19
 * 성공/실패 : 성공 (원래는 문제를 잘못 이해했어서 실패였음 ㄷ , 문제 이해하니까 바로 풀림)
 * 소요시간 : 25분
 *
 * 버그 : solution 함수 내에서 전역변수 cnt2 초기화 0으로 하지 않으면 오답으로 나왔음
 */
public class 단체사진찍기 {

    static boolean[] visit;
    static int cnt2;

    public int solution(int n, String[] data) {

        String[] persons = {"A", "C", "F", "J", "M", "N", "R", "T"};
        visit = new boolean[persons.length];

        dfs(persons, new ArrayList<>(), data);
        return cnt2;
    }

    public static void main(String[] args) {

        int n = 2;
        String[] data = {"N~F=0", "R~T>2"};

        int result = new 단체사진찍기().solution(n, data);
        System.out.println(result);

    }

    public void dfs(String[] persons, List<String> result, String[] data) {

        if (persons.length == result.size()) {
            if (check(result, data)) {
                cnt2++;
            }
            return;
        }

        for (int i = 0; i < persons.length; i++) {
            if (!visit[i]) {
                visit[i] = true;
                result.add(persons[i]);

                dfs(persons, result, data);

                visit[i] = false;
                result.remove(persons[i]);
            }
        }
    }

    public boolean check(List<String> target, String[] conditions) {

        for (String conditionStr : conditions) {

            char[] conditionChars = conditionStr.toCharArray();
            String a1 = Character.toString(conditionChars[0]);
            String a2 = Character.toString(conditionChars[2]);
            char op = conditionChars[3];
            int inputDist = conditionChars[4] - '0';

            int idx1 = target.indexOf(a1);
            int idx2 = target.indexOf(a2);
            int dist = Math.abs(idx1 - idx2) - 1;

            boolean isSuccess;
            switch (op) {
                case '=':
                    isSuccess = dist == inputDist;
                    break;
                case '>':
                    isSuccess = dist > inputDist;
                    break;
                case '<':
                    isSuccess = dist < inputDist;
                    break;
                default:
                    isSuccess = false;
            }

            if (!isSuccess) {
                return false;
            }
        }

        return true;
    }
}
