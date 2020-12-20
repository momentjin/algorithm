package programmers.level_4;

import java.util.Arrays;

/**
 * #이분탐색
 * #실패
 *
 * Q. 하나를 기준으로 이분 탐색하는 건 쉬운데, 여러개를 선택하면서 이분 탐색하는건 뭐지.
 * -> 애초에 생각을 잘못했음. 기준값은 1개임
 * -> 만약에 여러개가 기준이 되어야 한다면, 선행 기준이 후행 기준에 영향을 미치지 않아야함.
 *
 * Q. 바위 선택은 어떻게 해야하는가? 전수 조사할 경우, 조합이라 너무 범위가 커진다.
 * -> 기준값을 거리로 둔다. --- x
 * -> 인접한 두 바위사이의 거리가 기준값 x보다 작을 때 지운다
 * -> 만약 삭제한 횟수가 n을 초과활 경우 해당 기준값으론 n개 만큼 바위를 지울 수 없으므로, break;
 * -> 덜제거했거나, 딱 맞게 제거했을 경우 left를 mid+1로 조정
 */
public class 징검다리 {

    public static void main(String[] args) {
        int d = 25;
        int a[] = {2, 14, 11, 21, 17};
        int n = 2;

        int x = new 징검다리().solution(d, a, n);
        System.out.println(x);
    }

    public int solution(int distance, int[] rocks, int n) {

        Arrays.sort(rocks);

        int answer = 0;
        int left = 0;
        int right = distance;
        int mid; // 각 바위 사이의 거리 중 최대값

        while (left <= right) {

            mid = (left + right) / 2;
            int start = 0;
            int removeCnt = 0;

            for (int rock : rocks) {
                if (rock - start < mid) {
                    removeCnt++;
                    if (removeCnt > n) {
                        break;
                    }
                } else {
                    start = rock;
                }
            }

            if (removeCnt > n) {
                right = mid - 1;
            } else {
                left = mid + 1;
                answer = Math.max(answer, mid);
            }
        }

        return answer;
    }
}
