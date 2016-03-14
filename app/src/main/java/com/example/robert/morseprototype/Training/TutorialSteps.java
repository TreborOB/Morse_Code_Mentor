package com.example.robert.morseprototype.Training;

import java.util.ArrayList;




public class TutorialSteps {

    public static ArrayList<MorseTutorial> getIntro() {

        ArrayList<MorseTutorial> intro = new ArrayList<>();


        intro.add(new MorseTutorial("Welcome to Morse code Mentor - in this section you will the basics of Morse code", "", false, true, false, "Start", false, false, false));
        intro.add(new MorseTutorial("Morse code consists of dots and dashes with a dot being one unit and a dash 3 units. First try sending a dot. A dot is represented by the letter E in Morse code. The progress bar above the pad will stay red for a dot and turn blue when you are sending a dash", "E", true, true, true, "Next", true, true, true));
        intro.add(new MorseTutorial("Well done! Now we will send a dash, remember a dash is 3 times the length of a dot. The progress bar will turn light blue when you are sending a dash", "T", true, true, true, "Next", true, true, true));
        intro.add(new MorseTutorial("The time between dots and dashes are important in Morse code. Large spaces mean a new word while small spaces represent a new letter. The gaps between dots and dashes of the same letter are very small. Try and send a dot then a dash with a small gap in between. This tells Morse that it is the same letter", "A", true, true, true, "Next", true, true, true));
        intro.add(new MorseTutorial("Now send a dot then a dash with a space in between, this represents a new word in Morse", "E T", true, true, true, "Next", true, true, true));
        intro.add(new MorseTutorial("We will now send 2 dots then a space to represent a new word and then a dash", "I T", true, false, true, "Next", true, true, true));

        return intro;
    }


    public static ArrayList<MorseTutorial> getListAtoF() {

        ArrayList<MorseTutorial> aTof = new ArrayList<>();

        aTof.add(new MorseTutorial("This section will comprise of the letters A to F", "", false, true, false, "Start", false, false, false));
        aTof.add(new MorseTutorial("Press on the pad to send the letters A: dot dash", "A", true, true, true, "Next", true, true, true));
        aTof.add(new MorseTutorial("Press on the pad to send the letter B: dash dot dot dot", "B", true, true, true, "Next", true, true, true));
        aTof.add(new MorseTutorial("Press on the pad to send the letter C: dash dot dash dot", "C", true, true, true, "Next", true, true, true));
        aTof.add(new MorseTutorial("Press on the pad to send the letter D: dash dot dot", "D", true, true, true, "Next", true, true, true));
        aTof.add(new MorseTutorial("Press on the pad to send the letter E: dot", "E", true, false, true, "Next", true, true, true));

        return aTof;

    }



    public static ArrayList<MorseTutorial> getListGtoK() {

        ArrayList<MorseTutorial> gTok = new ArrayList<>();

        gTok.add(new MorseTutorial("This section will comprise of the letters G to K", "", false, true, false, "Start", false, false, false));
        gTok.add(new MorseTutorial("Press on the pad to send the letter G: dash dash dot", "G", true, true, true, "Next", true, true, true));
        gTok.add(new MorseTutorial("Press on the pad to send the letter H: dot dot dot dot", "H", true, true, true, "Next", true, true, true));
        gTok.add(new MorseTutorial("Press on the pad to send the letter I: dash dot dot", "I", true, true, true, "Next", true, true, true));
        gTok.add(new MorseTutorial("Press on the pad to send the letter J: dot dash dash dash", "J", true, true, true, "Next", true, true, true));
        gTok.add(new MorseTutorial("Press on the pad to send the letter K: dash dot dash", "K", true, false, true, "Next", true, true, true));

        return gTok;

    }

    public static ArrayList<MorseTutorial> getListLtoP() {

        ArrayList<MorseTutorial> lTop = new ArrayList<>();

        lTop.add(new MorseTutorial("This section will comprise of the letters L to P", "", false, true, false, "Start", false, false, false));
        lTop.add(new MorseTutorial("Press on the pad to send the letter L: dot dash dot dot", "L", true, true, true, "Next", true, true, true));
        lTop.add(new MorseTutorial("Press on the pad to send the letter M: dash dash", "M", true, true, true, "Next", true, true, true));
        lTop.add(new MorseTutorial("Press on the pad to send the letter N: dash dot", "I", true, true, true, "Next", true, true, true));
        lTop.add(new MorseTutorial("Press on the pad to send the letter O: dash dash dash", "O", true, true, true, "Next", true, true, true));
        lTop.add(new MorseTutorial("Press on the pad to send the letter P: dot dash dash dot", "O", true, false, true, "Next", true, true, true));

        return lTop;

    }

    public static ArrayList<MorseTutorial> getListQtoU() {

        ArrayList<MorseTutorial> qTou = new ArrayList<>();

        qTou.add(new MorseTutorial("This section will comprise of the letters Q to U", "", false, true, false, "Start", false, false, false));
        qTou.add(new MorseTutorial("Press on the pad to send the letter Q: dot dash dot dot", "Q", true, true, true, "Next" , true, true, true));
        qTou.add(new MorseTutorial("Press on the pad to send the letter R: dot dash dot", "R", true, true, true, "Next", true, true, true));
        qTou.add(new MorseTutorial("Press on the pad to send the letter S: dot dot dot", "S", true, true, true, "Next", true, true, true));
        qTou.add(new MorseTutorial("Press on the pad to send the letter T: dash", "T", true, true, true, "Next", true, true, true));
        qTou.add(new MorseTutorial("Press on the pad to send the letter U: dash", "U", true, false, true, "Next", true, true, true));

        return qTou;

    }


    public static ArrayList<MorseTutorial> getListVtoZ() {

        ArrayList<MorseTutorial> vToz = new ArrayList<>();

        vToz.add(new MorseTutorial("This section will comprise of the letters V to Z", "", false, true, false, "Start", false, false, false));
        vToz.add(new MorseTutorial("Press on the pad to send the letter V: dot dot dot dash", "V", true, true, true, "Next", true, true, true));
        vToz.add(new MorseTutorial("Press on the pad to send the letter W: dot dash dash", "W", true, true, true, "Next", true, true, true));
        vToz.add(new MorseTutorial("Press on the pad to send the letter X: dash dot dot dash", "X", true, true, true, "Next", true, true, true));
        vToz.add(new MorseTutorial("Press on the pad to send the letter Y: dash dot dash dash", "Y", true, true, true, "Next", true, true, true));
        vToz.add(new MorseTutorial("Press on the pad to send the letter Z: dash dash dot dot", "Z", true, false, true, "Next", true, true, true));

        return vToz;

    }



    public static ArrayList<MorseTutorial> getListNumbers() {

        ArrayList<MorseTutorial> numbers = new ArrayList<>();

        numbers.add(new MorseTutorial("This section will comprise of the numbers 1 to 9", "", false, true, false, "Start", false, false, false));
        numbers.add(new MorseTutorial("Press on the pad to send the number 1: dot dash dash dash dash", "1", true, true, true, "Next", true, true, true));
        numbers.add(new MorseTutorial("Press on the pad to send the number 2: dot dot dash dash dash", "2", true, true, true, "Next", true, true, true));
        numbers.add(new MorseTutorial("Press on the pad to send the number 3: dot dot dot dash dash", "3", true, true, true, "Next", true, true, true));
        numbers.add(new MorseTutorial("Press on the pad to send the number 4: dot dot dot dot dash", "4", true, true, true, "Next", true, true, true));
        numbers.add(new MorseTutorial("Press on the pad to send the number 5: dot dot dot dot dot", "5", true, false, true, "Next", true, true, true));

        return numbers;

    }



}



