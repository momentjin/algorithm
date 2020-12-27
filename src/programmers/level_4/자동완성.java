package programmers.level_4;

import java.util.HashMap;
import java.util.Map;

/**
 * #TRIE
 * 성공
 * 소요 시간 : 30분
 *
 * 이전에 배운 Trie 자료구조를 통해 쉽게 해결할 수 있었음.
 */
public class 자동완성 {

    public static void main(String[] args) {

        String[] a = {
                "abc", "def", "ghi", "jklm"
        };

        int n = new 자동완성().solution(a);
        System.out.println(n);
    }

    public int solution(String[] words) {

        Trie trie = new Trie();

        for (String word : words) {
            trie.insert(word);
        }

        int sum = 0;
        for (String word : words) {
            int n = trie.contains(word);
            System.out.println(n);

            sum += n;
        }

        System.out.println();
        return sum;
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

                if (node.cnt == 1) {
                    return i + 1;
                }

                if (i == word.length() - 1) {
                    return i + 1;
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
