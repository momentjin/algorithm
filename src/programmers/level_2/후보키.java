package programmers.level_2;

import java.util.*;

/**
 * 종류 : #탐
 * 시작일시 : 2020-10-27 13:55
 * 종료일시 : 2020-10-27 14:49
 * 성공/실패 : 성공
 * 소요시간 : 56분
 * 회고 :
 * - 자꾸 중복없는 집합을 만들어야 하는데, 실수로 중복탐색해서 처음에 틀렸음 -> 고친 후 바로 통
 */
public class 후보키 {

    public static void main(String[] args) {

        String[][] relation = {{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"}, {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}};

        int n = new 후보키().solution(relation);
        System.out.println(n);

    }

    public int solution(String[][] relation) {

        visit = new boolean[relation[0].length];
        map = new HashMap<>();

        for (int i = 1; i <= relation[0].length; i++) {
            for (int j = 0; j < relation[0].length; j++) {
                function(i, j, 1, relation[0].length, relation);
            }
        }

        int answer = result;
        return answer;
    }

    static int result;
    static boolean visit[];
    static Map<Integer, List<String>> map = new HashMap<>();

    public void function(int keySize, int currentIdx, int count, int colSize, String[][] relation) {

        visit[currentIdx] = true;

        // 모두 방문했으면
        if (count == keySize) {

            // 후보키가 될 수 있는지 검사
            if (checkUnique(relation) && checkMin(keySize, relation)) {

                String s = "";
                for (int i = 0; i < visit.length; i++) {
                    if (visit[i])
                        s += i;
                }

                System.out.println(Arrays.toString(visit));

                if (map.containsKey(keySize)) {
                    map.get(keySize).add(s);
                } else {
                    List<String> a = new ArrayList<>();
                    a.add(s);
                    map.put(keySize, a);
                }
                result++;
            }

            // 종료
            visit[currentIdx] = false;
            return;
        }

        for (int i = currentIdx+1; i < colSize; i++) {

            if (!visit[i]) {
                function(keySize, i, count + 1, colSize, relation);
            }
        }

        visit[currentIdx] = false;
    }

    boolean checkUnique(String[][] relation) {

        Set<String> set = new HashSet<>();

        for (int r = 0; r < relation.length; r++) {

            String temp = "";
            for (int v = 0; v < visit.length; v++) {
                if (visit[v])
                    temp += relation[r][v];
            }

            set.add(temp);
        }


        return set.size() == relation.length;
    }

    boolean checkMin(int keySize, String[][] relation) {

        if (keySize == 1)
            return true;

        // 이미 이전 key size에서 만족하는 유일키 집합군 (문자열)
        for (int i = keySize - 1; i >= 1; i--) {

            if (!map.containsKey(i))
                continue;

            List<String> fail = map.get(i);
            for (String f : fail) {
                boolean temp = true;
                String[] s = f.split("");
                for (String ss : s) {
                    int n = Integer.parseInt(ss);
                    if (!visit[n]) {
                        temp = false;
                        break;
                    }
                }

                if (temp) {
                    return false;
                }
            }
        }

        return true;
    }
}
