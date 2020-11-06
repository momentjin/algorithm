package programmers.level_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 종류 : #
 * 시작일시 : 2020-10-30 01:55
 * 성공/실패 :
 * 소요시간 :
 */
public class 수식최대화 {

    public static void main(String[] args) {

        new 수식최대화().solution("100-200*300-500+20");
    }

    public long solution(String expression) {

        long answer = 0;
        List<Character> opList = Arrays.asList('*', '-', '+');
        String[] cases = {"-+*", "*+-", "*-+", "-*+", "+-*", "+*-"};

        // 문자열 분리
        ArrayList<Long> nums = new ArrayList<>();
        ArrayList<Character> ops = new ArrayList<>();
        String s = "";
        for (char c : expression.toCharArray()) {
            if (opList.contains(c)) {
                nums.add(Long.parseLong(s));
                ops.add(c);
                s = "";
            } else
                s += String.valueOf(c);
        }
        nums.add(Long.parseLong(s));

        // 계산
        for (String caseOps : cases) {
            ArrayList<Long> tempNums = (ArrayList<Long>) nums.clone();
            ArrayList<Character> tempOps = (ArrayList<Character>) ops.clone();

            for (char c : caseOps.toCharArray()) {
                while (tempOps.contains(c)) {
                    int idx = tempOps.indexOf(c);
                    long result = 0;
                    switch (c) {
                        case '+':
                            result = tempNums.get(idx) + tempNums.get(idx + 1);
                            break;
                        case '-':
                            result = tempNums.get(idx) - tempNums.get(idx + 1);
                            break;
                        case '*':
                            result = tempNums.get(idx) * tempNums.get(idx + 1);
                            break;
                    }
                    tempOps.remove(idx);
                    tempNums.remove(idx);
                    tempNums.remove(idx);
                    tempNums.add(idx, result);
                }
            }

            answer = Math.max(answer, Math.abs(tempNums.get(0)));
        }

        return answer;
    }
}
