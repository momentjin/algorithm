package programmers.level_2;

import java.util.ArrayList;
import java.util.List;

/**
 * 종류 : #정렬
 * 성공/실패: 실패
 * 실패 사유
 * - 기존 나의 풀이(주석)이 맞다고 생각했으나 자꾸 런타임 에러가 떠서 삽질함
 * - 여기서 얻은 교휸은 charAt처럼 인덱스접근은 어지간하면 피하는게 좋겠음
 * - 이유는 문제에 대한 입력 조건이 항상 제대로 들어오지 않은 듯함.
 *
 * Created by momentjin@gmail.com on 2020-10-11
 * Github : http://github.com/momentjin
 */
public class 가장큰수 {

    public static void main(String[] args) {
        int[] numbers = {55, 556};
        String s = new 가장큰수().solution(numbers);


        System.out.println(s);
    }

    public String solution(int[] numbers) {

        List<String> list = new ArrayList<>();
        for (int n : numbers) {
            list.add(String.valueOf(n));
        }

        list.sort((o1, o2) -> {

            String s1 = o1+""+o2;
            String s2 = o2+""+o1;

            return s2.compareTo(s1);
        });

        String result = String.join("", list);

        boolean isAllZero = true;
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) != '0') {
                isAllZero = false;
            }
        }

        if (isAllZero) {
            return "0";
        }

        return result;
    }

//    public String solution(int[] numbers) {
//
//        List<String> list = new ArrayList<>();
//        for (int n : numbers) {
//            list.add(String.valueOf(n));
//        }
//
//        list.sort((o1, o2) -> {
//
//            int minLength = Integer.min(o1.length(), o2.length());
//            for (int i = 0; i < minLength; i++) {
//                if (o1.charAt(i) == o2.charAt(i))
//                    continue;
//                else
//                    return o2.compareTo(o1);
//            }
//
//            return Character.compare(
//                    o2.charAt(o2.length() - 1),
//                    o1.charAt(o1.length() - 1)
//            );
//        });
//
//        String result = String.join("", list);
//
//        boolean isAllZero = true;
//        for (int i = 0; i < result.length(); i++) {
//            if (result.charAt(i) != '0') {
//                isAllZero = false;
//            }
//        }
//
//        if (isAllZero) {
//            return "0";
//        }
//
//        return result;
//    }
}
