package practice;

import java.util.HashMap;
import java.util.Map;

public class Trie {

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
