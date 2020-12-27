package programmers.level_4;

import java.util.Arrays;

/**
 * #이분탐색 #ParametricSearch
 * 성공
 * 소요시간 40분
 * <p>
 * - 이분탐색이 안될 줄 알았는데, 생각해보니 된다.
 * ㄴ 기준값이 1개라면, 범위를 어느쪽으로 좁혀야 하는지 알 수 없다.
 * ㄴ 따라서 이분 탐색의 검색 조건 mid를 하나 더 추가 (mid+1)
 * ㄴ mid일 떄와, mid+1일 떄의 비용을 조사, mid가 최소비용인경우 right의 범위를 좁힌다 (반대의 경우는 left를)
 * - 높이를 탐색의 기준으로 정한다.
 */
public class 지형편집 {

    public long solution(int[][] land, int P, int Q) {

        long mid = 0;
        long cost = Long.MAX_VALUE;
        long left = 1_000_000_000;
        long right = 0;

        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                left = Math.min(left, land[i][j]);
                right = Math.max(right, land[i][j]);
            }
        }

        while (left <= right) {

            mid = (left + right) / 2;

            long cost1 = calculateCost(mid, land, P, Q);
            long cost2 = calculateCost(mid + 1, land, P, Q);

            if (cost1 <= cost2) {
                right = mid - 1;
                cost = Long.min(cost, cost1);
            } else {
                left = mid + 1;
                cost = Long.min(cost, cost2);
            }
        }

        return cost;
    }

    long calculateCost(long baseHeight, int[][] land, int P, int Q) {

        long result = 0;
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {

                if (land[i][j] > baseHeight) {
                    result += (land[i][j] - baseHeight) * Q;
                } else if (land[i][j] < baseHeight) {
                    result += (baseHeight - land[i][j]) * P;
                }
            }
        }

        return result;
    }

    // 다른 방법
    public long solution2(int[][] land, int P, int Q) {
        long answer = Long.MAX_VALUE;
        int n = land.length;

        int[] land2 = new int[n * n];

        long totalBlocks = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                land2[n * i + j] = land[i][j];
                totalBlocks += land[i][j];
            }
        }
        Arrays.sort(land2);

        long accumulateBlocks = 0;
        for (int i = 0; i < n * n; i++) {
            accumulateBlocks += land2[i]; // 현재까지의 블록 수를 누적시킨다.

            // 현재 위치의 높이를 목표로 평탄화 시킨다고 가정한다.

            // - 추가해야할 블록 수를 구한다. addBlocks = 현재 목표로 뒤 덮은 영역의 블록 수 - 현재까지 누적된 영역
            long addBlocks = (land2[i] * (long) (i + 1)) - accumulateBlocks;

            // - 제거해야할 블록 수를 구한다. deleteBlocks =  (전체 블록 수 - 누적 블록 수) - 현재 목표로 뒤 덮은 영역의 블록 수
            long deleteBlocks = (totalBlocks - accumulateBlocks) - ((n * n - (i + 1)) * land2[i]);

            // 계산 및 answer 갱신
            long result = addBlocks * P + deleteBlocks * Q;
            answer = Math.min(answer, result);
        }

        return answer;
    }
}
