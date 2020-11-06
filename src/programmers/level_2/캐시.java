package programmers.level_2;

import java.util.ArrayList;
import java.util.List;

/**
 * 종류 : #구현 #LRU
 * 시작일시 : 2020-10-27 15:08
 * 성공/실패 : 2020-10-27 15:28
 * 소요시간 : 20분
 *
 * LRU만 구현하면 그냥 풀 수 있는 문제.
 */
public class 캐시 {

    public static void main(String[] args) {

        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};

        int n = new 캐시().solution(0, cities);
        System.out.println(n);

    }

    public int solution(int cacheSize, String[] cities) {


        List<String> cache = new ArrayList<>();
        int answer = 0;

        for (String city : cities) {

            city = city.toLowerCase();

            if (cache.contains(city)) {
                answer += 1;
                cache.remove(city);
                cache.add(city);
            } else {
                answer += 5;
                if (cache.size() == cacheSize && !cache.isEmpty()) {
                    cache.remove(0);
                }
                cache.add(city);
            }
        }


        return answer;
    }
}
