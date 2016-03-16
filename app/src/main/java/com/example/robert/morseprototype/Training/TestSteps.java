package com.example.robert.morseprototype.Training;

import java.util.ArrayList;



public class TestSteps {

    public static ArrayList<MorseTutorial> getAtoFSteps() {

        ArrayList<MorseTutorial> aToFSteps = new ArrayList<>();


        aToFSteps.add(new MorseTutorial("This test will consist of the letters A to F. There will be 10 questions in total. The pass rate is 80%", "", false, true, false, "Start", false, false, false));
        aToFSteps.add(new MorseTutorial("A", "A", true, false, false, "Start", false, false, false));
        aToFSteps.add(new MorseTutorial("B", "B", true, false, false, null, false, false, false));
        //aToFSteps.add(new MorseTutorial("C", "C", true, false, false, null, false, false, false));
        //aToFSteps.add(new MorseTutorial("D", "D", true, false, false, null, false, false, false));
        //aToFSteps.add(new MorseTutorial("E", "E", true, false, false, null, false, false, false));


        return aToFSteps;
    }


    public static ArrayList<MorseTutorial> getGtoKSteps() {

        ArrayList<MorseTutorial> gToKSteps = new ArrayList<>();


        gToKSteps.add(new MorseTutorial("This test will consist of the letters G to K. There will be 10 questions in total. The pass rate is 80%", "", false, true, false, "Start", false, false, false));
        gToKSteps.add(new MorseTutorial("G", "G", true, false, false, "Start", false, false, false));
        gToKSteps.add(new MorseTutorial("H", "H", true, false, false, "Start", false, false, false));
        //gToKSteps.add(new MorseTutorial("I", "I", true, false, false, "Start", false, false, false));

        return gToKSteps;
    }



    public static ArrayList<MorseTutorial> getLtoPSteps() {

        ArrayList<MorseTutorial> lToPSteps = new ArrayList<>();


        lToPSteps.add(new MorseTutorial("This test will consist of the letters L to P. There will be 10 questions in total. The pass rate is 80%", "", false, true, false, "Start", false, false, false));
        lToPSteps.add(new MorseTutorial("L", "L", true, false, false, "Start", false, false, false));
        //lToPSteps.add(new MorseTutorial("M", "M", true, false, false, "Start", false, false, false));
        //lToPSteps.add(new MorseTutorial("N", "N", true, false, false, "Start", false, false, false));

        return lToPSteps;
    }




    public static ArrayList<MorseTutorial> getQtoUSteps() {

        ArrayList<MorseTutorial> qToUSteps = new ArrayList<>();


        qToUSteps.add(new MorseTutorial("This test will consist of the letters Q to U. There will be 10 questions in total. The pass rate is 80%", "", false, true, false, "Start", false, false, false));
        qToUSteps.add(new MorseTutorial("Q", "Q", true, false, false, "Start", false, false, false));
        //qToUSteps.add(new MorseTutorial("R", "R", true, false, false, "Start", false, false, false));
        //qToUSteps.add(new MorseTutorial("S", "S", true, false, false, "Start", false, false, false));


        return qToUSteps;
    }




    public static ArrayList<MorseTutorial> getVtoZSteps() {

        ArrayList<MorseTutorial> vToZSteps = new ArrayList<>();


        vToZSteps.add(new MorseTutorial("This test will consist of the letters V to Z. There will be 10 questions in total. The pass rate is 80%", "", false, true, false, "Start", false, false, false));
        vToZSteps.add(new MorseTutorial("V", "V", true, false, false, "Start", false, false, false));
        //vToZSteps.add(new MorseTutorial("W", "W", true, false, false, "Start", false, false, false));
        //vToZSteps.add(new MorseTutorial("X", "X", true, false, false, "Start", false, false, false));

        return vToZSteps;
    }


    public static ArrayList<MorseTutorial> numberSteps() {

        ArrayList<MorseTutorial> numberSteps = new ArrayList<>();


        numberSteps.add(new MorseTutorial("This test will consist of the numbers 1 to 9. The pass rate is 80%", "", false, true, false, "Start", false, false, false));
        numberSteps.add(new MorseTutorial("1", "1", true, false, false, "Start", false, false, false));
        //vToZSteps.add(new MorseTutorial("W", "W", true, false, false, "Start", false, false, false));
        //vToZSteps.add(new MorseTutorial("X", "X", true, false, false, "Start", false, false, false));

        return numberSteps;
    }

}



