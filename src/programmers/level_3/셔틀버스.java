package programmers.level_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 종류 : #구현
 * 시작일시 : 2020-11-16 19:09
 * 성공/실패 : 실패
 * 소요시간 :
 */
public class 셔틀버스 {

    public static void main(String[] args) {
        int n = 10;
        int t = 60;
        int m = 10;
        String[] a = {"17:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"};

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

            if (timeTables.size() < m) {
                return String.format("%02d:%02d", lastTime / 60, lastTime % 60);
            }

            if (i == n - 1) {
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
