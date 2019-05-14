package com.ginix.crossword.crossword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/w")
public class WordSearchRestImpl {

    @Autowired
    private Trie trie;

    @GetMapping(path = "/w/{length}/{pattern}")
    private String getMatchingWords(@PathVariable String length, @PathVariable String pattern) {
        return trie.printMatchingWords(Integer.parseInt(length), pattern);
    }

    @GetMapping(path = "/w/{length}")
    private String getMatchingWords(@PathVariable String length) {
        return trie.printMatchingWords(Integer.parseInt(length), null);
    }
}
