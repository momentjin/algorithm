package programmers.level_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 종류 : #구현 #아이디어
 * 시작일시 : 2020-11-06 12:27
 * 성공/실패 : 실패
 * 소요시간 : 60분
 * 실패 사유
 * - 충분히 생각할 수 있음에도 불구하고, 생각하지 못했다. 이게 못하는건가?ㅋ
 * - 매번 전부다 비교하는거말고! 좀 다른 생각으로 방향을 바꾸자 ㅠㅠ
 */
public class 추석트래픽 {

    public static void main(String[] args) {
        int n = new 추석트래픽().solution(new String[]{
                "2016-09-15 20:59:00.000 2s"
        });

        System.out.println(n);
    }

    public int solution(String[] lines) {

        int answer = 0;
        int[][] durations = new int[lines.length][lines.length];
        for (int i = 0; i < lines.length; i++) {

            String[] input = lines[i].split(" ");
            String[] times = input[1].split(":");
            int duration = (int) (Double.parseDouble(input[2].replace("s", "")) * 1000);

            int h = Integer.parseInt(times[0]);
            int m = Integer.parseInt(times[1]);
            int s = Integer.parseInt(times[2].split("\\.")[0]);
            int ms = Integer.parseInt(times[2].split("\\.")[1]);

            int end = h * 3600 * 1000 + m * 60 * 1000 + s * 1000 + ms;
            int start = end - duration + 1;

            durations[i][0] = start;
            durations[i][1] = end;
        }

        // start 기준
        for (int i = 0; i < lines.length; i++) {

            int cnt = 0;
            int startBlock = durations[i][0];
            int endBlock = startBlock + 1000;

            for (int j = 0; j < lines.length; j++) {

                int startTime = durations[j][0];
                int endTime = durations[j][1];

                if (startTime >= startBlock && startTime < endBlock) {
                    cnt++;
                } else if (endTime >= startBlock && endTime < endBlock) {
                    cnt++;
                } else if (startTime <= startBlock && endTime >= endBlock) {
                    cnt++;
                }
            }

            answer = Integer.max(answer, cnt);
        }

        // end 기준
        // start 기준
        for (int i = 0; i < lines.length; i++) {

            int cnt = 0;
            int startBlock = durations[i][1];
            int endBlock = startBlock + 1000;

            for (int j = 0; j < lines.length; j++) {

                int startTime = durations[j][0];
                int endTime = durations[j][1];

                if (startTime >= startBlock && startTime < endBlock) {
                    cnt++;
                } else if (endTime >= startBlock && endTime < endBlock) {
                    cnt++;
                } else if (startTime <= startBlock && endTime >= endBlock) {
                    cnt++;
                }
            }

            answer = Integer.max(answer, cnt);
        }

        return answer;
    }

    private int[][] getTimes(String[] lines) {

        int[][] durations = new int[lines.length][2];

        for (int i = 0; i < lines.length; ++i) {
            String[] log = lines[i].split(" ");
            String[] responseTime = log[1].split(":");
            int processingTime = (int) (Double.parseDouble(log[2].substring(0, log[2].length() - 1)) * 1000);
            int startTime = 0;
            int endTime = 0;

            endTime += Integer.parseInt(responseTime[0]) * 60 * 60 * 1000;
            endTime += Integer.parseInt(responseTime[1]) * 60 * 1000;
            endTime += (int) (Double.parseDouble(responseTime[2]) * 1000);
            startTime = endTime - processingTime + 1;

            durations[i][0] = startTime;
            durations[i][1] = endTime;
        }

        return durations;
    }
}
