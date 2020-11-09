package programmers.level_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 종류 : #구현
 * 시작일시 : 2020-11-06 16:56
 * 성공/실패 : 실패
 * 소요시간 :
 * <p>
 * Q. 이동과 회전을 함께 탐색하는 방법?
 * - 상하좌우 이동: 4번
 * - 90, 180, 270 회전: 3번
 * - 총 12번?
 * <p>
 * 실패 사유
 * - 완전탐색했으나 시관 초과 발생
 *
 * <p>
 * 문제를 나눠서 생각해보면 로직 자체는 간단했다.
 * 1. 0, 90, 180, 270도 돌린 배열 만들기
 * -> 애초에 재귀가 필요 없었다.
 * <p>
 * 2. 각 배열을 순회하며 자물쇠에 맞는지 검사하기
 * -> 이것 또한 재귀가 불필요하다.
 * -> key의 끝원소(m,m)를 lock의 첫원소(0,0) 부터 비교하여 겹치는 것만 보면 되기 때문.
 * <p>
 * 3. 조건을 하나 뺴먹었다. 자물쇠 돌기와 열쇠 돌기가 만나면 안된다.
 *
 * 여기에서 배울 것은, 배열 내 원소를 움직인다고 해서 반드시 탐색을 이용할 필요는 없다는 것!
 */
public class 자물쇠와열쇠 {

    static int N;
    static int M;

    public boolean solution(int[][] key, int[][] lock) {

        M = key.length;
        N = lock.length;

        // 자물쇠를 탐색할 최대 맵을 만든다.
        int fullSize = key.length * 2 - 2 + lock.length;
        int[][] map = new int[fullSize][fullSize];
        for (int i = 0; i < fullSize; i++) {
            Arrays.fill(map[i], 2);
        }

        for (int i = key.length - 1, x = 0; i <= lock.length + key.length - 2; i++, x++) {
            for (int j = key.length - 1, y = 0; j <= lock.length + key.length - 2; j++, y++) {
                map[i][j] = lock[x][y];
            }
        }

        // print
        for (int i = 0; i < fullSize; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();


        List<int[][]> rotateKeys = new ArrayList<>();
        rotateKeys.add(rotate90(key));
//        rotateKeys.add(key);
//        rotateKeys.add(rotate90(rotate90(key)));
//        rotateKeys.add(rotate90(rotate90(rotate90(key))));

        for (int[][] rKey : rotateKeys) {
            if (equals(rKey, map))
                return true;
        }

        return false;
    }

    public int[][] rotate90(int[][] key) {

        int[][] temp = new int[key.length][key.length];

        for (int i = 0, y = 0; i < temp.length; i++, y++) {
            for (int j = 0, x = key.length - 1; j < temp.length; j++, x--) {
                temp[i][j] = key[x][y];
            }
        }

        return temp;
    }

    public boolean equals(int[][] key, int[][] lock) {


        for (int x = 0; x < N + M - 1; x++) {
            for (int y = 0; y < N + M - 1; y++) {

                int[][] tempLock = new int[lock.length][lock.length];
                for (int i = 0; i < tempLock.length; i++) {
                    for (int j = 0; j < tempLock.length; j++) {
                        tempLock[i][j] = lock[i][j];
                    }
                }

                boolean success = true;

                for (int i = 0; i < key.length; i++) {
                    for (int j = 0; j < key.length; j++) {

                        int v1 = key[i][j];
                        int v2 = lock[i + x][j + y];

                        if (v2 == 2) continue;

                        if (v1 == 1 && v2 == 1) {
                            success = false;
                            break;
                        }

                        if (v1 == 1 && v2 == 0) {
                            tempLock[i + x][j + y] = 1;
                        }
                    }
                }


                if (success)
                    for (int i = 0; i < tempLock.length; i++) {
                        for (int j = 0; j < tempLock.length; j++) {
                            if (tempLock[i][j] == 0) {
                                success = false;
                                break;
                            }
                        }
                    }

                if (success) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {

        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        boolean is = new 자물쇠와열쇠().solution(key, lock);
        System.out.println(is);

    }
}
