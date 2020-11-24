package programmers.level_3;

import java.util.*;

/**
 * 종류 : #DP, #DFS, #다시풀기(완) -> DP로
 * 시작일시 : 2020-11-06 16:21
 * 성공/실패 : 실패
 * 소요시간 :
 * 실패 사유
 * - 동적계획법 지식이 부족하다.
 */
public class N으로표현 {

    public static void main(String[] args) {
        int n = new N으로표현().solution_dp(5, 12);
        System.out.println(n);

    }

    public int solution_dp(int N, int number) {

        HashSet<Integer>[] s = new HashSet[8];
        for (int i = 0; i < 8; i++) {
            HashSet<Integer> set = new HashSet<>();
            String n = "";
            for (int j = 0; j <= i; j++) {
                n += N;
            }
            set.add(Integer.parseInt(n));
            s[i] = set;

            System.out.println(n);
        }

        for (int i = 0; i < 8; i++) {

            Set<Integer> temp = new HashSet<>();

            for (int j = 0; j < i; j++) {
                System.out.println("j: ");
                for (Integer n1 : s[j]) {
                    System.out.println("b: " + "" + (i - j - 1));
                    for (Integer n2 : s[i - j - 1]) {
                        temp.add(n1 + n2);
                        temp.add(n1 - n2);
                        temp.add(n1 * n2);

                        if (n2 != 0)
                            temp.add(n1 / n2);
                    }
                }

                System.out.println();
                s[i].addAll(temp);
            }

            if (s[i].contains(number)) {
                return i + 1;
            }
        }

        return -1;
    }

    static int answer;

    public int solution_dfs(int N, int number) {

        answer = 9;


        dfs(N, number, 0, 0);
        if (N == number) answer = 1;
        return answer == 9 ? -1 : answer;
    }


    void dfs(int N, int number, int cnt, int sum) {

        if (cnt > 8) {
            return;
        }

        if (sum == number) {
            answer = Integer.min(answer, cnt);
            return;
        }

        int cntNum = 0;
        for (int i = 1; i <= 8; i++) {
            cntNum = cntNum * 10 + N;
            dfs(N, number, cnt + i, sum + cntNum);
            dfs(N, number, cnt + i, sum - cntNum);
            dfs(N, number, cnt + i, sum * cntNum);
            if (sum != 0)
                dfs(N, number, cnt + i, sum / cntNum);
        }
    }
}
