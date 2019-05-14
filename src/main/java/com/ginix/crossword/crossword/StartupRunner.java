package com.ginix.crossword.crossword;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements ApplicationRunner {

    @Autowired
    private Trie trie;

    static int total_words_inserted = 0;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/words.txt"));
            while (scanner.hasNextLine()) {
                String dictWord = scanner.next();
                // System.out.println("Going to insert:" + dictWord);
                total_words_inserted++;
                trie.insert(dictWord.toUpperCase());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Total words inserted:" + total_words_inserted);
    }

    public static void main(String[] str) {

        StartupRunner s = new StartupRunner();
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/words.txt"));
            while (scanner.hasNextLine()) {
                String dictWord = scanner.next();
                s.trie.insert(dictWord.toUpperCase());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        s.trie.printMatchingWords(11, null);
    }
}
