package programmers.level_3;

/**
 * 종류 : #구현, #다시풀기(완)
 */
public class 기지국설치 {

    public static void main(String[] args) {
        int n = new 기지국설치().solution(12, new int[]{1}, 1);
        System.out.println(n);
    }

    public int solution(int n, int[] stations, int w) {


        int answer = 0;
        int location = 1;
        int stationIdx = 0;

        while (location <= n) {

            // 가장 가까운 기지국에 처음 접근시
            if (stationIdx < stations.length && location >= stations[stationIdx] - w) {
                // 기지국을 지을 필요가 없이 기지국의 끝으로 이동만 시켜주면 됌
                location = stations[stationIdx] + w + 1;
                stationIdx++;
            }

            // 기지국 밖에 있는 경우
            else {
                answer++;
                location += 2 * w + 1; // 기지국을 설치
            }
        }

        return answer;
    }
}
