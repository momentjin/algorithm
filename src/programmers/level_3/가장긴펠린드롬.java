package programmers.level_3;

/**
 * 종류 : #구현
 * 시작일시 :
 * 성공/실패 : 성공
 */
public class 가장긴펠린드롬 {

    public static void main(String[] args) {

        int n = new 가장긴펠린드롬().solution("aaaaabbababbaaaaa");
        System.out.println(n);
    }

    public int solution(String s) {

        int answer = 1;

        // 한 글자 기준 대칭
        for (int i = 0; i < s.length(); i++) {
            int left = i;
            int right = i;
            int cnt = 1;

            while (left - 1 >= 0 && right + 1 < s.length()) {
                if (s.charAt(--left) == s.charAt(++right)) {
                    cnt += 2;
                } else {
                    break;
                }
            }

            answer = Integer.max(answer, cnt);
        }

        // 두 글자 기준 대칭
        for (int i = 0; i < s.length() - 1; i++) {
            int left = i;
            int right = i + 1;
            int cnt = 2;

            if (s.charAt(left) != s.charAt(right))
                continue;

            while (left - 1 >= 0 && right + 1 < s.length()) {
                if (s.charAt(--left) == s.charAt(++right)) {
                    cnt += 2;
                } else {
                    break;
                }
            }

            answer = Integer.max(answer, cnt);
        }


        return answer;
    }
}
