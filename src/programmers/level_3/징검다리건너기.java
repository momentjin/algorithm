package programmers.level_3;

/**
 * 종류 : #이분탐색
 * 시작일시 :
 * 성공/실패 : 실패
 * 소요시간 :
 *
 * 이분탐색..!
 */
public class 징검다리건너기 {

    public static void main(String[] args) {
        int[] s = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;

        int n = new 징검다리건너기().solution(s, k);
        System.out.println(n);
    }

    public int solution(int[] stones, int k) {

        int answer = 0;
        int min = 1, max = 200000000;

        while (min < max) {

            int mid = (min + max) / 2;
            if (isPass(stones, k, mid)) {
                min = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                max = mid - 1;
            }
        }

        return answer;
    }

    boolean isPass(int[] stones, int k, int personNum) {

        int noPassCnt = 0;
        for (int stone : stones) {

            if (stone < personNum) {
                noPassCnt++;
            } else {
                noPassCnt = 0;
            }

            if (noPassCnt >= k) {
                return false;
            }
        }

        return true;
    }
}
