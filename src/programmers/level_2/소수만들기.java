package programmers.level_2;

/**
 * 종류 : #
 * 시작일시 : 2020-11-01 23:25
 * 성공/실패 :
 * 소요시간 :
 */
public class 소수만들기 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 7, 6, 4};
        new 소수만들기().solution(nums);
    }

    static int answer;
    static boolean[] visit;

    public int solution(int[] nums) {

        answer = 0;
        visit = new boolean[nums.length];

        for (int i = 0; i < nums.length - 2; i++) {
            dfs(nums, nums[i], i, 1, nums[i] + ",");
        }

        System.out.println(answer);
        return answer;
    }

    public void dfs(int[] nums, int sum, int idx, int count, String s) {

        if (count == 3) {
            if (isPrime(sum)) {
                answer++;
                System.out.println(s);
            }

            return;
        }

        for (int i = idx + 1; i < nums.length; i++) {
            if (!visit[i]) {
                visit[i] = true;
                dfs(nums, sum + nums[i], i, count + 1, s + "," + nums[i]);
                visit[i] = false;
            }
        }
    }

    public boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) return false;
        }

        return true;
    }
}
