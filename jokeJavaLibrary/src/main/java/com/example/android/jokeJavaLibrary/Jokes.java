package com.example.android.jokeJavaLibrary;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class Jokes {

    private static Random randomGenerator = new Random();

    private Jokes() {
        //private so class cannot be initialized
    }

    private static List<String> jokeList = Arrays.asList(
            "When I was a boy, I had a disease that required me to eat dirt three times a day in order to survive... \nIt's a good thing my older brother told me about it.",
            "What's the difference between men and pigs? Pigs don't turn into men when they drink.",
            "Alcohol is a perfect solvent: It dissolves marriages, families and careers.",
            "The first computer dates back to Adam and Eve. It was an Apple with limited memory, just one byte.\n And then everything crashed.",
            "Maybe if we start telling people the brain is an app they will start using it.",
            "I asked my North Korean friend how it was there, he said he couldn't complain.",
            "Photons have mass? I didn't even know they were Catholic.",
            "I just asked my husband if he remembers what today is... Scaring men is easy.",
            "She wanted a puppy. But I didn't want a puppy. So we compromised and got a puppy.");

    public static String getRandomJoke() {

        int i = randomGenerator.nextInt(jokeList.size());
        return jokeList.get(i);
    }


}
