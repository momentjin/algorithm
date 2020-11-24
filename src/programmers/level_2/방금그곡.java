
package programmers.level_2;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 종류 : #구현
 * 시작일시 : 2020-10-27 13:43
 * 소요시간 :
 * 성공/실패 : 성공
 * 실패 이유 :
 */
public class 방금그곡 {

    public String solution(String m, String[] musicinfos) {

        // 토크나이징
        List<String> tokens = this.tokenize(m);

        // 재생시간 (m)
        List<Info> infos = Arrays.stream(musicinfos)
                .map(Info::new)
                .sorted()
                .collect(Collectors.toList());

        // 검사
        String answer = "";

        for (Info info : infos) {

            List<String> fullTexts = info.fullText;

            int i = 0;
            int cnt = 0;
            int j = 0;
            int lastJ = j;
            for (; j < fullTexts.size(); ) {
                String s = fullTexts.get(j);
                if (i < tokens.size() && s.equals(tokens.get(i))) {
                    i++;
                    cnt++;
                    j++;
                    if (cnt == tokens.size()) {
                        answer = info.name;
                    }
                } else {
                    i = 0;
                    cnt = 0;
                    j = ++lastJ;
                }

            }

            if (!answer.isEmpty()) {
                return answer;
            }
        }

        return "(None)";
    }

    private List<String> tokenize(String input) {

        List<String> list = new ArrayList<>();

        String[] inputs = input.split("");
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i].equals("#")) {
                list.set(list.size() - 1, list.get(list.size() - 1) + "#");
            } else {
                list.add(inputs[i]);
            }
        }

        System.out.println(list);
        return list;
    }

    class Info implements Comparable<Info> {

        long duration;
        String name;
        List<String> tokens;
        List<String> fullText = new ArrayList<>();

        public Info(String input) {

            String[] infos = input.split(",");

            this.duration = LocalTime.parse(infos[1], DateTimeFormatter.ofPattern("HH:mm")).getLong(ChronoField.MINUTE_OF_DAY) -
                    LocalTime.parse(infos[0], DateTimeFormatter.ofPattern("HH:mm")).getLong(ChronoField.MINUTE_OF_DAY);

            this.name = infos[2];
            this.tokens = tokenize(infos[3]);

            for (int i = 0; i < duration; i++) {
                int idx = i < tokens.size() ? i : i % tokens.size();
                this.fullText.add(tokens.get(idx));
            }
        }

        @Override
        public int compareTo(Info o) {
            return Long.compare(o.duration, this.duration);
        }
    }
}
