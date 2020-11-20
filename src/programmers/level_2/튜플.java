package programmers.level_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 종류 : #구현
 * 시작일시 : 2020-11-02 20:09
 * 성공/실패 :
 * 소요시간 :
 */
public class 튜플 {

    public static void main(String[] args) {
        String s = "{{123}}";
        int[] arr = new 튜플().solution(s);
        System.out.println(Arrays.toString(arr));
    }

    public int[] solution(String s) {

        List<String> result = new ArrayList<>();
        List<String> list = new ArrayList<>();
        String[] str = s.split("},\\{");
        for (int i = 0; i < str.length; i++) {
            StringBuilder sb = new StringBuilder(str[i]);
            if (i == 0) {
                sb.deleteCharAt(0);
                sb.deleteCharAt(0);
            } else if (i == str.length - 1) {
                sb.deleteCharAt(sb.length() - 1);
                sb.deleteCharAt(sb.length() - 1);
            }

            if (str.length == 1) {
                sb.deleteCharAt(sb.length()-1);
                sb.deleteCharAt(sb.length()-1);
            }

            list.add(sb.toString());
        }

        list.sort(Comparator.comparingInt(String::length));

        for (String tuple : list) {
            String[] nums = tuple.split(",");
            for (String n : nums) {
                if (result.contains(n)) {
                    continue;
                } else {
                    result.add(n);
                }
            }
        }

        int[] answer = new int[result.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = Integer.parseInt(result.get(i));
        }

        return answer;
    }
}
