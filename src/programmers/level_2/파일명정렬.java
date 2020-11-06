package programmers.level_2;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 종류 : #구현 #정렬
 * 시작일시 : 2020-10-27 13:49
 * 성공/실패 : 성공
 * 소요시간 :
 */
class 파일명정렬 {
    public String[] solution(String[] files) {

        List<Item> items = new ArrayList<>();
        for (String file : files) {
            items.add(new Item(file));
        }

        Collections.sort(items);

        List<String> result = items.stream().map(item -> item.originTitle).collect(Collectors.toList());

        String[] naswer = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            naswer[i] = result.get(i);
        }

        return naswer;
    }


    static class Item implements Comparable<Item> {

        String originTitle;
        String head = "";
        int number;

        public Item(String originTitle) {
            this.originTitle = originTitle;

            int i = 0;
            while (i < originTitle.length() && !Character.isDigit(originTitle.charAt(i))) {
                head = head + originTitle.charAt(i++);
            }

            String tempNumber = "";
            int count = 0;
            while (i < originTitle.length() && Character.isDigit(originTitle.charAt(i))) {
                tempNumber += originTitle.charAt(i++);
            }
            this.number = Integer.parseInt(tempNumber);
        }

        @Override
        public int compareTo(Item o) {

            int s1 = head.toLowerCase().compareTo(o.head.toLowerCase());
            if (s1 != 0)
                return s1;

            int s2 = Integer.compare(number, o.number);
            if (s2 != 0) {
                return s2;
            }

            return 0;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "originTitle='" + originTitle + '\'' +
                    ", head='" + head + '\'' +
                    ", number=" + number +
                    '}';
        }
    }
}