package programmers.level_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 종류 : #구현, #다시풀기(완)
 * 시작일시 : 2020-11-16 19:09
 * 성공/실패 : 실패
 * 소요시간 :
 * 실패사유 :
 * - 이 문제는 진짜 구현 문제, 주어진 요구사항에 맞춰 구현을 하느냐 마느냐의 문제임
 * - 상황에 맞는 조건부 로직을 작성하고, 처리하는 것이 핵심
 * - 상황이란 무엇일까?
 * (1) 셔틀버스에 탈 수 있는 최대 인원수가 남은 인원보다 큰 경우 -> 이 때는 마지막 시간이 콘이 제일 늦게 도착하는 경우
 * (2) (1)과 반대인 경우, 셔틀버스 막차시간을 초과한 사람들을 제외한 나머지 사람 중 제일 늦게 도착하는 사람보다 -1분 도착하는 경우가 콘이 제일 늦게 도착하는 경우
 */
public class 셔틀버스 {

    public static void main(String[] args) {
        int n = 2;
        int t = 10;
        int m = 3;
        String[] a = {"09:09", "09:13", "09:15"};

        String s = new 셔틀버스().solution(n, t, m, a);
        System.out.println(s);
    }

    public String solution(int n, int t, int m, String[] timetable1) {

        int lastTime = (60 * 9) + (n - 1) * t;

        List<Integer> timeTables = Arrays.stream(timetable1)
                .map(s -> {
                    int hour = Integer.parseInt(s.split(":")[0]);
                    int minu = Integer.parseInt(s.split(":")[1]);
                    return hour * 60 + minu;
                })
                .sorted()
                .collect(Collectors.toList());

        for (int i = 0; i < n; i++) {

            // 남은 사람이 m보다 작으면, 셔틀에 모두 탈 수 있으므로 마지막 시간을 반환해서 종료한다. (마지막 시간에 콘이 셔틀을 탈 수 있기 때문)
            if (timeTables.size() < m) {
                return String.format("%02d:%02d", lastTime / 60, lastTime % 60);
            }

            // 남은 사람이 셔틀버스에 모두 탈 수 없는 상황
            // + 마지막 셔틀버스인 상황
            else if (i == n - 1) {

                // 셔틀에 타야하는 남은 사람 중 제일 빨리 도착하는 첫 번째 사람이 막차 도착 시간 이하에 도착시
                // 9시 10분 막차 3명까지 탈 수 있다.
                // - 9시 8분, 9시 9분, 9시 10분 인경우 .... 9시 9분에는 타야한다.
                // - 9시 9분, 9시 13분, 9시 15분 인경우 //
                if (timeTables.get(0) <= lastTime) {
                    lastTime = timeTables.get(m - 1) - 1;
                }



                return String.format("%02d:%02d", lastTime / 60, lastTime % 60);
            }


            List<Integer> removeIdx = new ArrayList<>();
            for (int j = m - 1; j > -1; j--) {
                int arrivedAt = (60 * 9) + i * t;
                if (timeTables.get(j) <= arrivedAt) {
                    removeIdx.add(j);
                }
            }

            List<Integer> newTT = new ArrayList<>();
            for (int j = 0; j < timeTables.size(); j++) {
                if (!removeIdx.contains(j)) {
                    newTT.add(timeTables.get(j));
                }
            }

            timeTables = newTT;
        }

        return "";
    }
}
