package com.ginix.crossword.crossword;

import org.springframework.stereotype.Component;

@Component
public class Trie {

    String allChars = "&'-123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    static int total_wrods_printed = 0;

    private TrieNode root;

    /* Constructor */
    public Trie() {
        root = new TrieNode(' ');
    }

    /* Function to insert word */
    public void insert(String word) {
        if (search(word) == true)
            return;
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            TrieNode child = current.subNode(ch);
            if (child != null)
                current = child;
            else {
                current.childList.add(new TrieNode(ch));
                current = current.subNode(ch);
            }
            current.count++;
        }
        current.isEnd = true;
    }

    /* Function to search for word */
    public boolean search(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            if (current.subNode(ch) == null)
                return false;
            else
                current = current.subNode(ch);
        }
        if (current.isEnd == true)
            return true;
        return false;
    }

    public void printWords(TrieNode root, String soFar, int length, String pattern, StringBuilder builder) {
        if (root == null) {
            // System.out.println(soFar);
            total_wrods_printed++;
            checkWordMatchesCrossword(soFar, length, pattern, builder);
            return;
        }
        if (soFar == null) {
            soFar = "" + root.content;
        } else {
            soFar += root.content;
        }
        if (root.isEnd) {
            // System.out.println(soFar);
            total_wrods_printed++;
            checkWordMatchesCrossword(soFar, length, pattern, builder);
            if (root.childList == null)
                return;
        }
        for (TrieNode node : root.childList) {
            printWords(node, soFar, length, pattern, builder);
        }
    }

    public void checkWordMatchesCrossword(String dicWord, int length, String pattern, StringBuilder builder) {
        if (dicWord.length() == length) {
            if (pattern == null) {
                System.out.println("Matched word is:" + dicWord);
                builder.append(dicWord).append("\n");
                return;
            }
            String[] splitPattern = pattern.split(",");
            boolean flag = true;
            for (String eachMatch : splitPattern) {
                String[] individualMatches = eachMatch.split(":");
                if (dicWord
                        .charAt(Integer.parseInt(individualMatches[0]) - 1) == individualMatches[1].toCharArray()[0]) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println("Matched word is:" + dicWord);
                builder.append(dicWord).append("\n");
            }
        }
    }

    public String printMatchingWords(int length, String pattern) {
        StringBuilder builder = new StringBuilder();

        TrieNode current = root;
        for (char ch : allChars.toCharArray()) {
            if (current.subNode(ch) == null) {
                continue;
            } else {
                printWords(current.subNode(ch), null, length, pattern, builder);
            }
        }

        System.out.println("Total words printed:" + total_wrods_printed);
        return builder.toString();
    }

}
