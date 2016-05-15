package com.example.robert.morseprototype.Misc;

import com.google.common.collect.HashBiMap;



public class MorseTranslations {

    private static final String SYM_NOT_FOUND = "?";

    private static HashBiMap<Character, String> Morse = HashBiMap.create();

    static {
        Morse.put('A', ".-");
        Morse.put('B', "-...");
        Morse.put('C', "-.-.");
        Morse.put('D', "-..");
        Morse.put('E', ".");
        Morse.put('F', "..-.");
        Morse.put('G', "--.");
        Morse.put('H', "....");
        Morse.put('I', "..");
        Morse.put('J', ".---");
        Morse.put('K', "-.-");
        Morse.put('L', ".-..");
        Morse.put('M', "--");
        Morse.put('N', "-.");
        Morse.put('O', "---");
        Morse.put('P', ".--.");
        Morse.put('Q', "--.-");
        Morse.put('R', ".-.");
        Morse.put('S', "...");
        Morse.put('T', "-");
        Morse.put('U', "..-");
        Morse.put('V', "...-");
        Morse.put('W', ".--");
        Morse.put('X', "-..-");
        Morse.put('Y', "-.--");
        Morse.put('Z', "--..");
        Morse.put('1', ".----");
        Morse.put('2', "..---");
        Morse.put('3', "...--");
        Morse.put('4', "....-");
        Morse.put('5', ".....");
        Morse.put('6', "-....");
        Morse.put('7', "--...");
        Morse.put('8', "---..");
        Morse.put('9', "----.");
        Morse.put('0', "-----");
        Morse.put('.', ".-.-.-");
        Morse.put(',', "--..--");
        Morse.put('\'', ".----.");
        Morse.put('"', ".-..-.");
        Morse.put('_', "..--.-");
        Morse.put(':', "---...");
        Morse.put(';', "-.-.-.");
        Morse.put('?', "..--..");
        Morse.put('!', "-.-.--");
        Morse.put('-', "-....-");
        Morse.put('+', ".-.-.");
        Morse.put('/', "-..-.");
        Morse.put('(', "-.--.");
        Morse.put(')', "-.--.-");
        Morse.put('=', "-...-");
        Morse.put('@', ".--.-.");
        Morse.put(null, "|");
        Morse.put(' ', " ");
    }



    public String translatedText(String str) {

        String s = str.toUpperCase();
        StringBuilder morse = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (i != 0) {
                morse.append(' ');
            }

            morse.append(Morse.get(s.charAt(i)));
        }
        return morse.toString();
    }


    public String translateMorse(StringBuilder sbIn) {

        String s = sbIn.toString();

        Character c = Morse.inverse().get(s);

        if (c == null) {
            return SYM_NOT_FOUND;
        } else {
            return c.toString();
        }
    }


    public String convertMorse(String sbIn) {

        Character c = Morse.inverse().get(sbIn);

        if (c == null) {
            return SYM_NOT_FOUND;
        } else {
            return c.toString();
        }
    }
}
