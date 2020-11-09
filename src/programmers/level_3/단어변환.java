package programmers.level_3;

import java.util.*;

/**
 * 종류 : #DFS
 * 시작일시 : 2020-11-09 19:49
 * 성공/실패 : 성공
 * 소요시간 : 20분
 */
public class 단어변환 {

    public static void main(String[] args) {
        String b = "hit";
        String t = "cog";
        String[] w = {"hot", "dot", "dog", "lot", "log", "cog"};
        new 단어변환().solution(b,t,w);
    }

    static int result;
    static Set<String> visit;

    public int solution(String begin, String target, String[] words) {

        result = Integer.MAX_VALUE;
        visit = new HashSet<>();

        dfs(begin, target, Arrays.asList(words), 0);
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    public void dfs(String b, String t, List<String> words, int cnt) {

        if (b.equals(t)) {
            result = Math.min(cnt, result);
            return;
        }

        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);

            if (!visit.contains(word) && diffOne(b, word)) {
                visit.add(word);
                dfs(word, t, words, cnt + 1);
                visit.remove(word);
            }
        }
    }

    public boolean diffOne(String s1, String s2) {
        int diff = 0;
        for (int j = 0; j < s1.length(); j++) {
            if (s1.charAt(j) != s2.charAt(j)) {
                diff++;
            }
        }

        return diff == 1;
    }
}
