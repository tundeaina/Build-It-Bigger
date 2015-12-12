package com.aina.adnd;

import java.util.Random;

public class Joke {

    public static int nextIndx = 0;

    private String[] jokes = {
            "Q: What kind of music do chiropractors listen to?\r\nA: HIP-POP!",
            "Q: What did the traffic light say to the car?\r\nA: Don't look, I'm changing.",
            "Guy walks into a bar with a slab of asphalt under arm. Says to the bartender: \"I’ll take a beer, and one for the road.\"",
            "Q: Why is there no gambling in Africa?\r\nA: Too many Cheetahs!",
            "Q: What did the worker at the rubber band factory say when he lost his job?\r\nA: OH SNAP",
            "Q: How did Darth Vader know what Luke got him for Christmas?\r\nA: He felt his presents!",
            "Q: What did the elder chimney say to the younger chimney?\r\nA: You're too young to smoke!",
            "Q: How does the man in the moon cut his hair?\r\nA: ECLIPSE IT!",
            "Q: What did the fish say when he ran into the wall?\r\nA: DAM!",
            "Q: What do you call a bear with no teeth?\r\nA: A gummy bear.",
            "Q: Why are all the frogs around here dead?\r\nA: 'Cause they keep croaking!",
            "A neutron walks into a bar and asks \"how much for a beer?\" The bartender says, \"for you? no charge.\"",
            "Two atoms are walking down the street together. The first atom turns and says, \"Hey, you just stole an electron from me!" +
                    "\"\r\n\"Are you sure?\" asks the second atom.\r\nTo which the first atom replies, \"Yeah, I'm positive!\""
    };

    public Joke() {
    }

    public String getNext() {

        String nextJoke =  jokes[nextIndx];

        nextIndx++;

        if(nextIndx > jokes.length-1)
            nextIndx = 0;

        return nextJoke;
    }

    public String getRandom() {

        return jokes[randInt(0,jokes.length-1)];
    }

    private static int randInt(int min, int max) {
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
