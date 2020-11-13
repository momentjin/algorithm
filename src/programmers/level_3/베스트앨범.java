package programmers.level_3;

import java.util.*;

/**
 * 종류 : #정렬
 * 시작일시 : 2020-11-13 21:11
 * 성공/실패 : 성공
 * 소요시간 : 20분
 */
public class 베스트앨범 {

    public static void main(String[] args) {
        String[] g = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        int[] r = new 베스트앨범().solution(g, plays);
        System.out.println(Arrays.toString(r));
    }

    public int[] solution(String[] genres, int[] plays) {

        Map<String, List<Item>> map = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            if (map.containsKey(genres[i])) {
                List<Item> s = map.get(genres[i]);
                s.add(new Item(i, genres[i], plays[i]));
                map.put(genres[i], s);
            } else {
                List<Item> s = new ArrayList<>();
                s.add(new Item(i, genres[i], plays[i]));
                map.put(genres[i], s);
            }
        }

        List<String> genreList = new ArrayList<>(map.keySet());
        genreList.sort((o1, o2) -> {
            int s1 = map.get(o1).stream()
                    .map(item -> item.playNum)
                    .reduce(Integer::sum).get();

            int s2 = map.get(o2).stream()
                    .map(item -> item.playNum)
                    .reduce(Integer::sum).get();

            return Integer.compare(s2, s1);
        });

        List<Integer> a = new ArrayList<>();
        for (String s : genreList) {
            List<Item> items = map.get(s);
            Collections.sort(items);

            int cnt = 0;
            for (Item item : items) {

                if (cnt == 2) break;
                a.add(item.no);
                cnt++;
            }
        }

        int[] answer = new int[a.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = a.get(i);
        }

        return answer;
    }

    class Item implements Comparable<Item> {

        int no;
        String type;

        public Item(int no, String type, int playNum) {
            this.no = no;
            this.type = type;
            this.playNum = playNum;
        }

        int playNum;

        @Override
        public int compareTo(Item o) {

            if (this.type.equals(o.type)) {

                if (o.playNum == this.playNum) {
                    return Integer.compare(this.no, o.no);
                } else
                    return Integer.compare(o.playNum, this.playNum);
            }

            return 0;
        }
    }
}
