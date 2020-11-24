package programmers.level_2;

/**
 * 종류 : #그리디 #다시풀기
 * 시작일시 : 2020-11-04 20:32
 * 성공/실패 : 실패
 * 소요시간 :
 * 실패 사유
 * - 그리디 알고리즘은 뭐가 탐욕인지 알아야하는데 나의 경우에는 몰랐음.
 * - 이 문제는 현 위치가 오른쪽으로 가는 경우 vs 현위치까지 오른촉으로 갓다가 다시 역으로 갈 때 A가 아닌 곳까지 가는 경우를 비교함.
 * > 위 사항이 그리디, 즉 매 순간 최선의 선택을 하기 위해 최대 2가지 선택지 중 하나를 선택하는 것. 그 외에 선택지는 다 최선이 아니기 떄문에 고려하지 않은 것
 */
public class 조이스틱 {

    public static void main(String[] args) {

        int n = new 조이스틱().solution("AZAABAAAAAAC");
        System.out.println(n);
    }

    public int solution(String name) {

        int answer = 0;

        // 위, 아래 최솟값
        for (char c : name.toCharArray()) {

            int n1 = c - 'A';
            int n2 = 'Z' - c + 1;
            answer += Integer.min(n1, n2);
        }

        // 좌, 우 최솟값
        int len = name.length();
        int minMove = len - 1; // 최대 움직임 횟수: 전체-1
        for (int i = 0; i < len; i++) {
            if (name.charAt(i) != 'A') {
                int next = i + 1;
                while (next < len && name.charAt(next) == 'A') {
                    next++;
                }

                int back = len - next + i + i;
                minMove = Integer.min(minMove, back);
                System.out.println("i: " + i +" / " + minMove);
            }
        }

        System.out.println(minMove);
        return answer + minMove;
    }
}
