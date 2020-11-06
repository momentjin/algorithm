package practice;


public class 최대공약수_최소공배수 {

    /**
     * 유클리드 호제법
     */
    int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
