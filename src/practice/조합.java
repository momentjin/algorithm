package practice;

public class 조합 {

    public static void main(String[] args) {
        func();
    }

    // 이진법으로 조합 구하기
    public static void func() {
        int n = 16; //
        for (int i = 0; i < n; i++) {
            System.out.println(String.format("%04d", Integer.parseInt(Integer.toBinaryString(i))));
        }
    }
}
