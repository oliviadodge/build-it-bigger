package com.example;

public class Joker {

    private final static String[] mJokes = {
            "Why did the cat go to Minnesota? " +
                    "To get a mini soda!",
            "Why did the cow cross the road?" +
                    "To get to the udder side.",
            "What happens to a frog's car when it breaks down?" +
                    "It gets toad away.",
            "What did the duck say when he bought lipstick" +
                    "put it on my bill",
            "What did the elephant say to the naked man?" +
                    "How do you breathe through something so small?",
            "Did you hear about the old chameleon that couldn't change color?" +
                    "He had a reptile dysfunction",
            "What do you call a fish that needs help with her vocals" +
                    "Autotuna",
            "I invited a teddy bear to dinner yesterday." +
                    "I offered him some food, but he said no thanks I'm stuffed.",
            "Why do fish live in salt water?" +
                    "Because pepper makes them sneeze!",
            "Where do mice park their boats? " +
                    "At the hickory dickory dock",
            "What is black and white and red all over? " +
                    "A sunburnt penguin."
    };

    public String getJoke(Integer index) {
        return mJokes[index];
    }
}
