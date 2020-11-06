package programmers.level_2;

import java.util.HashSet;
import java.util.Set;

/**
 * 종류 : #DFS
 * 성공/실패 :  성공
 */
public class 소수찾기 {

    public static void main(String[] args) {

        int n = new 소수찾기().solution("17");
        System.out.println(n);
    }

    static boolean[] visit;
    static Set<Integer> set;

    public int solution(String numbers) {

        set = new HashSet<>();
        visit = new boolean[numbers.length()];

        String[] numberList = numbers.split("");
        for (int i = 0; i < numberList.length; i++) {
            dfs("", numbers);
        }

        System.out.println(set);
        int answer = set.size();
        return answer;
    }

    private void dfs(String result, String numbers) {

        if (result.length() == numbers.length()) {
            return;
        }

        for (int i = 0; i < numbers.length(); i++) {
            if (!visit[i]) {
                visit[i] = true;

                String currText = result + "" + numbers.charAt(i);
                int n = Integer.parseInt(currText);

                if (isPrime(n)) {
                    set.add(n);
                }

                dfs(result + "" + numbers.charAt(i), numbers);

                visit[i] = false;
            }
        }
    }

    static boolean isPrime(int n) {
        for (int j = 2; j < n; j++) {
            if (n % j == 0) {
                return false;
            }
        }

        return n != 0 && n != 1;
    }
}
