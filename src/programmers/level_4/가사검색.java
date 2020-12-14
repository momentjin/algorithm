package programmers.level_4;

import java.util.*;

/**
 * #Trie #이분탐색
 * 실패
 * 사유
 * - 트라이 처음 봄!
 */
public class 가사검색 {

    public static void main(String[] args) {

        String[] a = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] b = {"???????", "????o", "fr???", "fro???", "pro?"};
        int[] c = new 가사검색().solution(a, b);
        System.out.println(Arrays.toString(c));

    }

    public int[] solution(String[] words, String[] queries) {

        // init
        int[] answer = new int[queries.length];
        int aIdx = 0;

        // front trie
        Map<Integer, Trie> front = new HashMap<>();
        for (String word : words) {
            if (front.containsKey(word.length())) {
                Trie temp = front.get(word.length());
                temp.insert(word);
                front.put(word.length(), temp);
            } else {
                front.put(word.length(), new Trie(word));
            }
        }

        // rear trie
        Map<Integer, Trie> rear = new HashMap<>();
        for (String word : words) {
            String reverse = new StringBuilder(word).reverse().toString();
            if (rear.containsKey(reverse.length())) {
                Trie temp = rear.get(reverse.length());
                temp.insert(reverse);
                rear.put(reverse.length(), temp);
            } else {
                rear.put(reverse.length(), new Trie(reverse));
            }
        }

        for (String query : queries) {

            int cnt = 0;

            // 처음과 끝이 물음표면 앞뒤 상관없으므로 하나만 살펴보면 된다.
            if (query.startsWith("?") && query.endsWith("?")) {
                Trie f = front.get(query.length());
                if (f != null)
                    for (Character c : f.root.childs.keySet()) {
                        cnt += f.root.childs.get(c).cnt;
                    }
            } else if (query.startsWith("?")) {
                Trie trie = rear.get(query.length());
                if (trie != null) {
                    cnt = trie.contains(new StringBuilder(query).reverse().toString().replace("?", ""));
                }
            } else {
                Trie trie = front.get(query.length());
                if (trie != null) {
                    cnt = trie.contains(query.replace("?", ""));
                }
            }

            answer[aIdx++] = cnt;
//            System.out.println("query = " + query);
//            System.out.println("cnt = " + cnt);
//            System.out.println();
        }

        return answer;
    }

    class Trie {

        TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public Trie(String word) {
            this();
            insert(word);
        }

        void insert(String word) {

            TrieNode node = this.root;

            for (int i = 0; i < word.length(); i++) {

                TrieNode next = node.childs.get(word.charAt(i));
                if (next == null) {
                    TrieNode newNode = new TrieNode();
                    node.childs.put(word.charAt(i), newNode);
                    next = newNode;
                } else {
                    next.up();
                }

                node = next;
            }

            node.isLast = true;
        }

        int contains(String word) {

            TrieNode node = this.root;
            int cnt = 0;
            for (int i = 0; i < word.length(); i++) {

                node = node.childs.get(word.charAt(i));

                if (node == null)
                    return 0;

                if (i == word.length() - 1) {
                    return node.cnt;
                }
            }

            return cnt;
        }
    }

    class TrieNode {

        Map<Character, TrieNode> childs;
        int cnt;
        boolean isLast;

        public TrieNode() {
            this.childs = new HashMap<>();
            this.cnt = 1;
            this.isLast = false;
        }

        void up() {
            this.cnt++;
        }
    }
}
