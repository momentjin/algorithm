package programmers.level_2;

/**
 * 종류 : #구현
 * 시작일시 : 2020-10-27 13:51
 * 성공/실패 : 실패
 * 소요시간 :
 * 실패 이유 : 구현을 좀 복잡하게 했음 ;;
 */
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class 압 {
    public int[] solution(String msg) {

        List<Integer> result = new ArrayList<>();
        Map<String, Integer> dict = new LinkedHashMap<>();
        int z = 1;
        for (char c = 'A'; c <= 'Z'; c++) {
            dict.put(String.valueOf(c), z++);
        }

        for (int i = 0; i < msg.length(); i++) {

            int start = i;
            int cnt = 1;
            String before = "";

            while (true) {

                int end = start + cnt >= msg.length() ? msg.length() : start + cnt;
                String sub = msg.substring(start, end);

                if (sub.equals(before) && dict.containsKey(sub)) {
                    result.add(dict.get(sub));
                    i = i + cnt - 2;
                    break;
                }

                else if (!dict.containsKey(sub)) {

                    dict.put(sub, z++);
                    result.add(dict.get(before));
                    i = i + cnt - 2;
                    break;
                } else {
                    before = sub;
                }

                cnt++;
            }
        }

        System.out.println(result);

        int[] answer = new int[result.size()];
        for (int i=0;i<answer.length; i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}