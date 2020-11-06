package programmers.level_2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 종류 : #구현
 * 시작일시 : 2020-10-30 00:10
 * 성공/실패 : 성공
 * 소요시간 : 5분도 채 안걸림
 */
public class 최댓값과최솟값 {

    public String solution(String s) {

        List<Integer> list = Arrays
                .stream(s.split(" "))
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());

        return list.get(0) + " " +list.get(list.size()-1);
    }

    public static void main(String[] args) {

        String s = new 최댓값과최솟값().solution("1 2 -1 -3");
        System.out.println(s);
    }
}
