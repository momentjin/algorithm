package programmers.level_3;

import java.util.*;

/**
 * 종류 : #DFS
 * 시작일시 :
 * 성공/실패 : 성공
 * 소요시간 :
 */
public class 불량사용자 {


    public static void main(String[] args) {
        String[] s1 = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] s2 = {"fr*d*", "*rodo", "******", "******"};

        System.out.println("asd : " + new 불량사용자().solution(s1, s2));
    }

    static Set<Set<String>> set;
    static int result;
    static boolean[] visitUserIds;
    static boolean[] visitBanIds;

    public int solution(String[] user_id, String[] banned_id) {

        set = new HashSet<>();
        result = 0;
        visitUserIds = new boolean[user_id.length];
        visitBanIds = new boolean[banned_id.length];

        dfs(user_id, banned_id, 0, 0, new HashSet<>());
        return set.size();
    }

    void dfs(String[] userIds, String[] banIds, int cnt, int j, Set<String> list) {

        if (cnt == banIds.length) {
            Set<String> dest = new HashSet<>(list);
            dest.addAll(list);
            set.add(dest);
            return;
        }

        if (!visitBanIds[j]) {
            visitBanIds[j] = true;

            for (int i = 0; i < userIds.length; i++) {
                if (!visitUserIds[i] && equal(banIds[j], userIds[i])) {
                    visitUserIds[i] = true;
                    list.add(userIds[i]);
                    dfs(userIds, banIds, j + 1, cnt + 1, list);
                    list.remove(userIds[i]);
                    visitUserIds[i] = false;
                }
            }

            visitBanIds[j] = false;
        }
    }

    public boolean equal(String banId, String userId) {

        if (banId.length() != userId.length())
            return false;

        for (int i = 0; i < banId.length(); i++) {
            char c1 = banId.charAt(i);
            char c2 = userId.charAt(i);

            if (c1 != c2 && c1 != '*') {
                return false;
            }
        }

        return true;
    }
}
