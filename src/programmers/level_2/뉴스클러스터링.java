package programmers.level_2;

import java.util.ArrayList;
import java.util.List;

/**
 * 종류 : #구현
 * 시작일시 : 2020-10-27 16:21
 * 성공/실패 : 2020-10-27 16:57
 * 소요시간 : 36분
 * 회고
 * - 무난했음
 * - 그냥 다중집합에 대한 교, 합집합의 규칙을 찾아서 구현하면됌
 */
public class 뉴스클러스터링 {

    public static void main(String[] args) {

        int n = new 뉴스클러스터링().solution("a1b2d3ae", "abcd3");
        System.out.println(n);

    }

    public int solution(String str1, String str2) {

        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < str1.length() - 1; i++) {
            String temp = str1.substring(i, i + 2).toLowerCase();

            boolean isLetter = true;
            for (char c : temp.toCharArray()) {
                if (!Character.isAlphabetic(c)) {
                    isLetter = false;
                    break;
                }
            }

            if (isLetter)
                list1.add(temp);
        }
        System.out.println(list1);

        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < str2.length() - 1; i++) {
            String temp = str2.substring(i, i + 2).toLowerCase();

            boolean isLetter = true;
            for (char c : temp.toCharArray()) {
                if (!Character.isAlphabetic(c)) {
                    isLetter = false;
                    break;
                }
            }

            if (isLetter)
                list2.add(temp);
        }
        System.out.println(list2);

        // ----

        List<String> temp1 = new ArrayList<>(list1);
        List<String> temp2 = new ArrayList<>(list2);
        int n = 0;
        for (String t1 : temp1) {
            if (temp2.contains(t1)) {
                n++;
                temp2.remove(t1);
            }
        }

        System.out.println("교집합 : " + n);

        temp1 = new ArrayList<>(list1);
        temp2 = new ArrayList<>(list2);
        int n2 = 0;
        for (String t1 : temp1) {
            if (temp2.contains(t1)) {
                n2++;
                temp2.remove(t1);
            } else {
                n2++;
            }
        }

        if (!temp2.isEmpty()) {
            n2 += temp2.size();
        }

        System.out.println("합집합 : " + n2);

        if (n==0 || n2 ==0) return 65536;

        double sum = 65536 * ((double) n / (double) n2);
        sum = Math.floor(sum);

        return (int) sum;
    }
}
