package programmers.level_2;

import java.util.Arrays;

/**
 * 종류 : #구현
 * 시작일시 : 2020-11-03 00:28
 * 성공/실패 : 실패
 * 소요시간 : 45분..
 * <p>
 * 문제 조건을 잘 못 알았네..
 */
public class HIndex {

    public static void main(String[] args) {

        int[] arr = new int[]{0, 1, 3, 5};
//        int[] arr = new int[]{2, 2, 2, 2, 2};
        int result = new HIndex().solution2(arr);
        System.out.println("정답 : " + result);
    }

    public int solution(int[] citations) {

        Arrays.sort(citations);

        int max = 0;
        int start = citations[0];
        int end = citations[citations.length - 1];

        for (int n = 0; n <= end; n++) {
            int cnt = 0;
            for (int citation : citations) {
                if (n <= citation) cnt++;
            }
            if (n <= cnt) {
                max = Integer.max(max, n);
            }
        }
        return max;
    }

    public int solution2(int[] citations) {
        Arrays.sort(citations);

        int max = 0;
        for (int i = citations.length - 1; i > -1; i--) {
            System.out.println(citations[i] + "," + (citations.length - i));
            int min = Integer.min(citations[i], citations.length - i);
            if (max < min) max = min;
        }

        return max;
    }
}
