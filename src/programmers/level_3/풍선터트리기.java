package programmers.level_3;


import java.util.Arrays;

/**
 * 종류 : #구현 #다시풀기(완)
 * 시작일시 : 2020-11-09 10:47
 * 성공/실패 : 실패
 * 소요시간 :
 * <p>
 * 실패 사유
 * - 중간 원소를 남길 때 어떻게 계산해야 할지 몰랐음
 * -> Q. 중간 원소를 남기기 위해 찬스를 한 번 쓴다?
 * => 그러면 그 중간 원소를 제외한 양옆원소는 모두 중간 원소보다 작아야 한다
 * => 즉 다시 말해, 찬스를 쓰지 않으면, 마지막에 찬스를 써도 된다는 말과 같다.
 */
public class 풍선터트리기 {

    public static void main(String[] args) {
        int n = new 풍선터트리기().solution(
                new int[]{9, -1, -5}
        );

        System.out.println(n);
    }

    public int solution(int[] a) {

        if (a.length == 1) return 1;

        int answer = 2;

        int[] leftMins = new int[a.length];
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (minValue > a[i]) {
                minValue = a[i];
            }
            leftMins[i] = minValue;
        }
        System.out.println(Arrays.toString(leftMins));

        int[] rightMins = new int[a.length];
        minValue = Integer.MAX_VALUE;
        for (int i = a.length - 1; i >= 0; i--) {
            if (minValue > a[i]) {
                minValue = a[i];
            }
            rightMins[i] = minValue;
        }
        System.out.println(Arrays.toString(rightMins));


        for (int i = 1; i < a.length - 1; i++) {
            if (leftMins[i] < a[i] && rightMins[i] < a[i]) {    //양 쪽의 최솟값보다 크다면 continue
                continue;
            }
            answer++;
        }

        return answer;
    }

    public int solution2(int[] a) {
        int answer = 0;

        int l = Integer.MAX_VALUE, r = Integer.MAX_VALUE;

        for (int fore = 0, back = a.length - fore - 1; fore < a.length; fore++) {

            if (a[fore] < l) {
                answer++;
                l = a[fore];
            }

            if (a[back] < r) {
                answer++;
                r = a[back];
            }

            if (l == r)
                break;
        }

        return answer + (l == r ? -1 : 0);
    }
}
