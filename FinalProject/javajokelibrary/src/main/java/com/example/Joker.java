package com.example;

public class Joker {

    private final static String[] mJokes = {
            "yo mamma",
            "that's what she said",
            "knock knock",
            "who's there?",
            "Banana",
            "Banana who?",
            "knock knock",
            "who's there?",
            "Banana",
            "Banana who?",
            "knock knock",
            "who's there?",
            "Orange",
            "Orange who?",
            "Orange you glad I didn't say banana?"
    };

    public String getJoke(Integer index) {
        return mJokes[index];
    }
}
