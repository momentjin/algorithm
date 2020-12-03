package programmers.level_3;

/**
 * 종류 : #이분탐색 #다시풀기(완)
 * 시작일시 :
 * 성공/실패 : 실패
 * 소요시간 :
 *
 * 실패 사유
 * - 이분탐색으로 접근할 생각조차 못함
 */
public class 징검다리건너기 {

    public static void main(String[] args) {
        int[] s = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;

        int n = new 징검다리건너기().solution2(s, k);
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

    // -------------

    // 다른 사람의 솔루션
    // 이분탐색도 안하고, 그냥 이중반복문으로 풀었다.
    // 성능은 이분 탐색에 비해 좋지 않겠다만, 참고는 하자. 로직이 특이하다

    public int solution2(int[] stones, int k) {
        int answer = Integer.MAX_VALUE;


        for(int i = 0; i<=stones.length-k; i++){
            int temp = i;
            int stone = stones[i];
            for(int j = i; j<i+k; j++){
                if(stones[j] > stone){
                    stone = stones[j];
                    temp = j;
                }
            }
            if(answer > stone){
                answer = stone;
            }
            i = temp;

        }
        return answer;
    }
}
