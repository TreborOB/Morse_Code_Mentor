package com.example.robert.morseprototype.Training;

import java.util.ArrayList;




public class TutorialSteps {

    public static ArrayList<MorseTutorial> getIntro() {

        ArrayList<MorseTutorial> intro = new ArrayList<>();


        intro.add(new MorseTutorial("Welcome to Morse code Mentor - in this section you will the basics of Morse code", "", false, true, false, "Start", false, false, false));
        intro.add(new MorseTutorial("Morse code consists of dots and dashes with a dot being one unit and a dash 3 units. First try sending a dot. A dot is represented by the letter E in Morse code. The progress bar will change to light blue for a dash", "E", true, true, true, "Next", true, true, true));
        intro.add(new MorseTutorial("Well done! Now we will send a dash, remember a dash is 3 times the length of a dot. The progress bar will turn light blue when you are sending a dash", "T", true, true, true, "Next", true, true, true));
        intro.add(new MorseTutorial("The time between dots and dashes are important in Morse code. Large spaces mean a new word while small spaces represent a new letter. The gaps between dots and dashes of the same letter are very small. Try and send a dot then a dash with a small gap in between. This tells Morse that it is the same letter", "A", true, true, true, "Next", true, true, true));
        intro.add(new MorseTutorial("Now send a dot then a dash with a space in between, this represents a new word in Morse", "E T", true, true, true, "Next", true, true, true));
        intro.add(new MorseTutorial("We will now send 2 dots then a space to represent a new word and then a dash", "I T", true, true, true, "Next", true, true, true));

        return intro;
    }



    public static ArrayList<MorseTutorial> getListAtoF() {

        ArrayList<MorseTutorial> aTof = new ArrayList<>();

        aTof.add(new MorseTutorial("This section will comprise of the letters A to F", "", false, true, false, "Start", false, false, false));
        aTof.add(new MorseTutorial("Press on the pad to send the letter A: dot dash", "A", true, true, true, "Next", true, true, true));
        aTof.add(new MorseTutorial("Press on the pad to send the letter B: dash dot dot dot", "B", true, true, true, "Next", true, true, true));
        aTof.add(new MorseTutorial("Press on the pad to send the letter C: dash dot dash dot", "C", true, true, true, "Next", true, true, true));
        aTof.add(new MorseTutorial("Press on the pad to send the letter D: dash dot dot", "D", true, true, true, "Next", true, true, true));
        aTof.add(new MorseTutorial("Press on the pad to send the letter E: dot", "E", true, true, true, "Next", true, true, true));
        aTof.add(new MorseTutorial("Press on the pad to send the letter F: dot dash dot dot", "F", true, true, true, "Next", true, true, true));

        return aTof;

    }

    public static ArrayList<MorseTutorial> getListGtoK() {

        ArrayList<MorseTutorial> gTok = new ArrayList<>();

        gTok.add(new MorseTutorial("This section will comprise of the letters G to K", "", false, true, false, "Start", false, false, false));
        gTok.add(new MorseTutorial("Press on the pad to send the letter G: dash dash dot", "G", true, true, true, "Next", true, true, true));
        gTok.add(new MorseTutorial("Press on the pad to send the letter H: dot dot dot dot", "H", true, true, true, "Next", true, true, true));
        gTok.add(new MorseTutorial("Press on the pad to send the letter I: dot dot", "I", true, true, true, "Next", true, true, true));
        gTok.add(new MorseTutorial("Press on the pad to send the letter J: dot dash dash dash", "J", true, true, true, "Next", true, true, true));
        gTok.add(new MorseTutorial("Press on the pad to send the letter K: dash dot dash", "K", true, true, true, "Next", true, true, true));

        return gTok;

    }

    public static ArrayList<MorseTutorial> getListLtoP() {

        ArrayList<MorseTutorial> lTop = new ArrayList<>();

        lTop.add(new MorseTutorial("This section will comprise of the letters L to P", "", false, true, false, "Start", false, false, false));
        lTop.add(new MorseTutorial("Press on the pad to send the letter L: dot dash dot dot", "L", true, true, true, "Next", true, true, true));
        lTop.add(new MorseTutorial("Press on the pad to send the letter M: dash dash", "M", true, true, true, "Next", true, true, true));
        lTop.add(new MorseTutorial("Press on the pad to send the letter N: dash dot", "I", true, true, true, "Next", true, true, true));
        lTop.add(new MorseTutorial("Press on the pad to send the letter O: dash dash dash", "O", true, true, true, "Next", true, true, true));
        lTop.add(new MorseTutorial("Press on the pad to send the letter P: dot dash dash dot", "O", true, true, true, "Next", true, true, true));

        return lTop;

    }

    public static ArrayList<MorseTutorial> getListQtoU() {

        ArrayList<MorseTutorial> qTou = new ArrayList<>();

        qTou.add(new MorseTutorial("This section will comprise of the letters Q to U", "", false, true, false, "Start", false, false, false));
        qTou.add(new MorseTutorial("Press on the pad to send the letter Q: dot dash dot dot", "Q", true, true, true, "Next" , true, true, true));
        qTou.add(new MorseTutorial("Press on the pad to send the letter R: dot dash dot", "R", true, true, true, "Next", true, true, true));
        qTou.add(new MorseTutorial("Press on the pad to send the letter S: dot dot dot", "S", true, true, true, "Next", true, true, true));
        qTou.add(new MorseTutorial("Press on the pad to send the letter T: dash", "T", true, true, true, "Next", true, true, true));
        qTou.add(new MorseTutorial("Press on the pad to send the letter U: dash", "U", true, true, true, "Next", true, true, true));

        return qTou;

    }


    public static ArrayList<MorseTutorial> getListVtoZ() {

        ArrayList<MorseTutorial> vToz = new ArrayList<>();

        vToz.add(new MorseTutorial("This section will comprise of the letters V to Z", "", false, true, false, "Start", false, false, false));
        vToz.add(new MorseTutorial("Press on the pad to send the letter V: dot dot dot dash", "V", true, true, true, "Next", true, true, true));
        vToz.add(new MorseTutorial("Press on the pad to send the letter W: dot dash dash", "W", true, true, true, "Next", true, true, true));
        vToz.add(new MorseTutorial("Press on the pad to send the letter X: dash dot dot dash", "X", true, true, true, "Next", true, true, true));
        vToz.add(new MorseTutorial("Press on the pad to send the letter Y: dash dot dash dash", "Y", true, true, true, "Next", true, true, true));
        vToz.add(new MorseTutorial("Press on the pad to send the letter Z: dash dash dot dot", "Z", true, true, true, "Next", true, true, true));

        return vToz;

    }



    public static ArrayList<MorseTutorial> getListNumbers() {

        ArrayList<MorseTutorial> numbers = new ArrayList<>();

        numbers.add(new MorseTutorial("This section will comprise of the numbers 0 to 9", "", false, true, false, "Start", false, false, false));
        numbers.add(new MorseTutorial("Press on the pad to send the number 0: dot dot dot dot dot", "5", true, true, true, "Next", true, true, true));
        numbers.add(new MorseTutorial("Press on the pad to send the number 1: dot dash dash dash dash", "1", true, true, true, "Next", true, true, true));
        numbers.add(new MorseTutorial("Press on the pad to send the number 2: dot dot dash dash dash", "2", true, true, true, "Next", true, true, true));
        numbers.add(new MorseTutorial("Press on the pad to send the number 3: dot dot dot dash dash", "3", true, true, true, "Next", true, true, true));
        numbers.add(new MorseTutorial("Press on the pad to send the number 4: dot dot dot dot dash", "4", true, true, true, "Next", true, true, true));
        numbers.add(new MorseTutorial("Press on the pad to send the number 5: dot dot dot dot dot", "5", true, true, true, "Next", true, true, true));
        numbers.add(new MorseTutorial("Press on the pad to send the number 6: dash dot dot dot dot", "5", true, true, true, "Next", true, true, true));
        numbers.add(new MorseTutorial("Press on the pad to send the number 7: dash dash dot dot dot", "5", true, true, true, "Next", true, true, true));
        numbers.add(new MorseTutorial("Press on the pad to send the number 8: dash dash dash dot dot", "5", true, true, true, "Next", true, true, true));
        numbers.add(new MorseTutorial("Press on the pad to send the number 9: dash dash dash dash dot", "5", true, true, true, "Next", true, true, true));

        return numbers;

    }



    //Spanish

    public static ArrayList<MorseTutorial> getIntroSpanish() {

        ArrayList<MorseTutorial> intro = new ArrayList<>();


        intro.add(new MorseTutorial("Bienvenido al código Morse Mentor - en esta sección los conceptos básicos de código Morse", "", false, true, false, "Comienzo", false, false, false));
        intro.add(new MorseTutorial("El código Morse consiste en puntos y rayas con un punto de ser una unidad y un guión 3 unidades. En primer lugar tratar el envío de un punto. Un punto está representado por la letra E en el código Morse. La barra de progreso sobre la tecla se mantienen en rojo por un punto y volverá azul cuando está enviando un guión", "E", true, true, true, "Siguiente", true, true, true));
        intro.add(new MorseTutorial("¡Bien hecho! Ahora vamos a enviar un guión, un guión recordamos es 3 veces la longitud de un punto. La barra de progreso se encenderá la luz azul cuando está enviando un guión", "T", true, true, true, "Siguiente", true, true, true));
        intro.add(new MorseTutorial("El tiempo entre puntos y rayas son importantes en el código Morse. Grandes espacios significan una nueva palabra, mientras que los espacios pequeños representan una nueva carta. Los espacios entre los puntos y rayas de la misma letra son muy pequeñas. Tratar de enviar un punto a continuación, un tablero con un pequeño hueco en el medio. Esto le dice Morse que es la misma letra", "A", true, true, true, "Siguiente", true, true, true));
        intro.add(new MorseTutorial("Ahora envía un punto a continuación, un tablero con un espacio en el medio, esto representa una nueva palabra en Morse", "E T", true, true, true, "Siguiente", true, true, true));
        intro.add(new MorseTutorial("Ahora vamos a enviar 2 puntos y luego un espacio para representar una nueva palabra y luego un guión", "I T", true, true, true, "Siguiente", true, true, true));

        return intro;
    }


    public static ArrayList<MorseTutorial> getListAtoFSpanish() {

        ArrayList<MorseTutorial> aTof = new ArrayList<>();

        aTof.add(new MorseTutorial("Esta sección estará compuesta de letras de la A a la F", "", false, true, false, "Comienzo", false, false, false));
        aTof.add(new MorseTutorial("Presione en el disco para enviar las letras A: tablero de puntos", "A", true, true, true, "Siguiente", true, true, true));
        aTof.add(new MorseTutorial("Presione en el disco para enviar la letra B: dash dot dot dot", "B", true, true, true, "Siguiente", true, true, true));
        aTof.add(new MorseTutorial("Presione en el disco para enviar la letra C: punto y raya punto y raya", "C", true, true, true, "Siguiente", true, true, true));
        aTof.add(new MorseTutorial("Presione en el disco para enviar la letra D: dot al tablero", "D", true, true, true, "Siguiente", true, true, true));
        aTof.add(new MorseTutorial("Presione en el disco para enviar la letra E: dot", "E", true, false, true, "Siguiente", true, true, true));
        aTof.add(new MorseTutorial("Presione en el disco para enviar la letra F: dot dash dot dot", "F", true, true, true, "Siguiente", true, true, true));

        return aTof;

    }

    public static ArrayList<MorseTutorial> getListGtoKSpanish() {

        ArrayList<MorseTutorial> gTok = new ArrayList<>();

        gTok.add(new MorseTutorial("En esta sección se comprenderá de las letras G a K", "", false, true, false, "Comienzo", false, false, false));
        gTok.add(new MorseTutorial("Presione en el disco para enviar la letra G: punto raya raya", "G", true, true, true, "Siguiente", true, true, true));
        gTok.add(new MorseTutorial("Presione en el disco para enviar la letra H: dot dot dot dot", "H", true, true, true, "Siguiente", true, true, true));
        gTok.add(new MorseTutorial("Press en el párrafo discoteca enviar la letra I: Tablero punto punto", "I", true, true, true, "Siguiente", true, true, true));
        gTok.add(new MorseTutorial("Presione en el disco para enviar la letra J: tablero de puntos raya raya", "J", true, true, true, "Siguiente", true, true, true));
        gTok.add(new MorseTutorial("Presione en el disco para enviar la letra K: tablero de punto y raya", "K", true, true, true, "Siguiente", true, true, true));

        return gTok;

    }

    public static ArrayList<MorseTutorial> getListLtoPSpanish() {

        ArrayList<MorseTutorial> lTop = new ArrayList<>();

        lTop.add(new MorseTutorial("En esta sección se comprenderá de las letras L a P", "", false, true, false, "Comienzo", false, false, false));
        lTop.add(new MorseTutorial("Presione en el disco para enviar la letra L: dot dot dot tablero", "L", true, true, true, "Siguiente", true, true, true));
        lTop.add(new MorseTutorial("Presione en el disco para enviar la letra M: el tablero de instrumentos", "M", true, true, true, "Siguiente", true, true, true));
        lTop.add(new MorseTutorial("Presione en el disco para enviar la letra N: punto y raya", "I", true, true, true, "Siguiente", true, true, true));
        lTop.add(new MorseTutorial("Presione en el disco para enviar la letra O: tablero tablero de instrumentos", "O", true, true, true, "Siguiente", true, true, true));
        lTop.add(new MorseTutorial("Presione en el disco para enviar la letra P: punto y raya el tablero de puntos", "O", true, true, true, "Siguiente", true, true, true));

        return lTop;

    }

    public static ArrayList<MorseTutorial> getListQtoUSpanish() {

        ArrayList<MorseTutorial> qTou = new ArrayList<>();

        qTou.add(new MorseTutorial("En esta sección se comprenderá de las letras Q U", "", false, true, false, "Comienzo", false, false, false));
        qTou.add(new MorseTutorial("Presione en el disco para enviar la letra Q: dot dot dot tablero", "Q", true, true, true, "Siguiente" , true, true, true));
        qTou.add(new MorseTutorial("Presione en el disco para enviar la letra R: punto y raya de puntos", "R", true, true, true, "Siguiente", true, true, true));
        qTou.add(new MorseTutorial("Press en el párrafo discoteca enviar la letra S: dot dot dot", "S", true, true, true, "Siguiente", true, true, true));
        qTou.add(new MorseTutorial("Presione en el disco para enviar la letra T: tablero", "T", true, true, true, "Siguiente", true, true, true));
        qTou.add(new MorseTutorial("Presione en el disco para enviar la letra U: tablero", "U", true, true, true, "Siguiente", true, true, true));

        return qTou;

    }


    public static ArrayList<MorseTutorial> getListVtoZSpanish() {

        ArrayList<MorseTutorial> vToz = new ArrayList<>();

        vToz.add(new MorseTutorial("En esta sección se comprenderá de las letras V a la Z", "", false, true, false, "Comienzo", false, false, false));
        vToz.add(new MorseTutorial("Presione en el disco para enviar la letra V: punto punto punto y raya", "V", true, true, true, "Siguiente", true, true, true));
        vToz.add(new MorseTutorial("Presione en el disco para enviar la letra W: raya raya del punto", "W", true, true, true, "Siguiente", true, true, true));
        vToz.add(new MorseTutorial("Presione en el disco para enviar la letra X: tablero de punto y raya de puntos", "X", true, true, true, "Siguiente", true, true, true));
        vToz.add(new MorseTutorial("Presione en el disco para enviar la letra Y: raya raya punto y raya", "Y", true, true, true, "Siguiente", true, true, true));
        vToz.add(new MorseTutorial("Presione en el disco para enviar la letra Z: punto punto raya raya", "Z", true, true, true, "Siguiente", true, true, true));

        return vToz;

    }



    public static ArrayList<MorseTutorial> getListNumbersSpanish() {

        ArrayList<MorseTutorial> numbers = new ArrayList<>();

        numbers.add(new MorseTutorial("Esta sección estará compuesta de los números del 1 al 9", "", false, true, false, "Comienzo", false, false, false));
        numbers.add(new MorseTutorial("Presione en el disco para enviar el número 1: tablero de punto y raya raya raya", "1", true, true, true, "Siguiente", true, true, true));
        numbers.add(new MorseTutorial("Presione en el disco para enviar el número 2: punto punto raya raya rociada", "2", true, true, true, "Siguiente", true, true, true));
        numbers.add(new MorseTutorial("Presione en el disco para enviar el número 3: dot dot dot tablero de instrumentos", "3", true, true, true, "Siguiente", true, true, true));
        numbers.add(new MorseTutorial("Presione en el disco para enviar el número 4: dot dot dot dot tablero", "4", true, true, true, "Siguiente", true, true, true));
        numbers.add(new MorseTutorial("Presione en el disco para enviar el número 5: dot dot dot dot dot", "5", true, false, true, "Siguiente", true, true, true));
        numbers.add(new MorseTutorial("Presione en el disco para enviar el número 6: punto y raya dot dot dot", "6", true, false, true, "Siguiente", true, true, true));
        numbers.add(new MorseTutorial("Presione en el disco para enviar el número 7: tablero de instrumentos dot dot dot", "7", true, false, true, "Siguiente", true, true, true));
        numbers.add(new MorseTutorial("Presione en el disco para enviar el número 8: tablero punto punto raya raya", "8", true, false, true, "Siguiente", true, true, true));
        numbers.add(new MorseTutorial("Presione en el disco para enviar el número 8: tablero de punto y raya raya raya", "9", true, true, true, "Siguiente", true, true, true));

        return numbers;

    }


    //Chinese

    public static ArrayList<MorseTutorial> getIntroChinese() {

        ArrayList<MorseTutorial> intro = new ArrayList<>();


        intro.add(new MorseTutorial("欢迎到莫尔斯电码导师 - 在这部分，你会的摩尔斯电码的基础知识", "", false, true, false, "开始", false, false, false));
        intro.add(new MorseTutorial("莫尔斯电码由点和线的一个点代表一个单位和一个破折号3个单位。第一次尝试发送一个点。一个点是由莫尔斯电码字母E表示。垫上面的进度条将保持红色圆点和变成蓝色，当你发送一个破折号", "E", true, true, true, "下一个", true, true, true));
        intro.add(new MorseTutorial("做得好！现在，我们将发送一个破折号，记得有一个破折号是一个点的长度的3倍。进度条会变成淡蓝色，当你发送一个破折号", "T", true, true, true, "下一个", true, true, true));
        intro.add(new MorseTutorial("点和线之间的时间是在莫尔斯电码很重要的。大空间意味着一个新字，而小空间代表了一种新的信。点和相同的字母破折号之间的差距非常小。试着发送一个点，然后一个破折号中间用小的差距。这告诉莫尔斯，它是相同的信", "A", true, true, true, "下一个", true, true, true));
        intro.add(new MorseTutorial("现在送点则划与之间的空间，这代表了莫尔斯一个新词", "E T", true, true, true, "下一个", true, true, true));
        intro.add(new MorseTutorial("现在，我们将派出2个点，然后一个空间，代表一个新字，然后一个破折号", "I T", true, true, true, "下一个", true, true, true));

        return intro;
    }


    public static ArrayList<MorseTutorial> getListAtoFChinese() {

        ArrayList<MorseTutorial> aTof = new ArrayList<>();

        aTof.add(new MorseTutorial("本节将包含字母A至F", "", false, true, false, "开始", false, false, false));
        aTof.add(new MorseTutorial("在垫按下发送字母A：点划线", "A", true, true, true, "下一个", true, true, true));
        aTof.add(new MorseTutorial("在垫按下发送字母B：破折号点点点", "B", true, true, true, "下一个", true, true, true));
        aTof.add(new MorseTutorial("在垫按下发送字母C：破折号，点划线点", "C", true, true, true, "下一个", true, true, true));
        aTof.add(new MorseTutorial("在垫按下发送字母D：破折号点点", "D", true, true, true, "下一个", true, true, true));
        aTof.add(new MorseTutorial("在垫按下发送字母E：点", "E", true, true, true, "下一个", true, true, true));

        return aTof;

    }

    public static ArrayList<MorseTutorial> getListGtoKChinese() {

        ArrayList<MorseTutorial> gTok = new ArrayList<>();

        gTok.add(new MorseTutorial("本节将包括字母G的至K", "", false, true, false, "开始", false, false, false));
        gTok.add(new MorseTutorial("在垫按下发送字母G：破折号破折号，点", "G", true, true, true, "下一个", true, true, true));
        gTok.add(new MorseTutorial("在垫按下发送字母H：点点点点", "H", true, true, true, "下一个", true, true, true));
        gTok.add(new MorseTutorial("在垫按下发送信我：破折号点点", "I", true, true, true, "下一个", true, true, true));
        gTok.add(new MorseTutorial("在垫按下发送字母J：点划线划线划线", "J", true, true, true, "下一个", true, true, true));
        gTok.add(new MorseTutorial("在垫按下发送字母K：破折号，点划线", "K", true, true, true, "下一个", true, true, true));

        return gTok;

    }

    public static ArrayList<MorseTutorial> getListLtoPChinese() {

        ArrayList<MorseTutorial> lTop = new ArrayList<>();

        lTop.add(new MorseTutorial("本节将包括字母L到磷", "", false, true, false, "开始", false, false, false));
        lTop.add(new MorseTutorial("“关于垫按下发送字母L：点划线点点", "L", true, true, true, "下一个", true, true, true));
        lTop.add(new MorseTutorial("在垫按下发送字母M：短跑冲刺", "M", true, true, true, "下一个", true, true, true));
        lTop.add(new MorseTutorial("在垫按下发送字母N：破折号，点", "I", true, true, true, "下一个", true, true, true));
        lTop.add(new MorseTutorial("在垫按下发送字母O：破折号破折号破折号", "O", true, true, true, "下一个", true, true, true));
        lTop.add(new MorseTutorial("在垫按下发送字母P：点划线划线点", "O", true, true, true, "下一个", true, true, true));

        return lTop;

    }

    public static ArrayList<MorseTutorial> getListQtoUChinese() {

        ArrayList<MorseTutorial> qTou = new ArrayList<>();

        qTou.add(new MorseTutorial("本节将包含字母Q以U型", "", false, true, false, "开始", false, false, false));
        qTou.add(new MorseTutorial("在垫按下发送字母Q：点划线点点", "Q", true, true, true, "下一个" , true, true, true));
        qTou.add(new MorseTutorial("在垫按下发送字母R：点划线点", "R", true, true, true, "下一个", true, true, true));
        qTou.add(new MorseTutorial("在垫按下发送字母S：点点点", "S", true, true, true, "下一个", true, true, true));
        qTou.add(new MorseTutorial("在垫按下发送字母T：破折号", "T", true, true, true, "下一个", true, true, true));
        qTou.add(new MorseTutorial("在垫按下发送字母U：破折号", "U", true, true, true, "下一个", true, true, true));

        return qTou;

    }


    public static ArrayList<MorseTutorial> getListVtoZChinese() {

        ArrayList<MorseTutorial> vToz = new ArrayList<>();

        vToz.add(new MorseTutorial("本节将包括字母V至的Z", "", false, true, false, "开始", false, false, false));
        vToz.add(new MorseTutorial("在垫按下发送字母V：点点点划线", "V", true, true, true, "下一个", true, true, true));
        vToz.add(new MorseTutorial("在垫按下发送字母W：点划线划线", "W", true, true, true, "下一个", true, true, true));
        vToz.add(new MorseTutorial("在垫按下发送字母X：破折号，点划线点", "X", true, true, true, "下一个", true, true, true));
        vToz.add(new MorseTutorial("在垫按下发送字母Y：破折号，点划线划线", "Y", true, true, true, "下一个", true, true, true));
        vToz.add(new MorseTutorial("在垫按下发送字母Z：破折号破折号点点", "Z", true, true, true, "下一个", true, true, true));

        return vToz;

    }



    public static ArrayList<MorseTutorial> getListNumbersChinese() {

        ArrayList<MorseTutorial> numbers = new ArrayList<>();

        numbers.add(new MorseTutorial("本节将包括数字1至9的", "", false, true, false, "开始", false, false, false));
        numbers.add(new MorseTutorial("在垫按下发送数字1：点破折号破折号破折号破折号", "1", true, true, true, "下一个", true, true, true));
        numbers.add(new MorseTutorial("在垫按发送号码2：点点破折号破折号破折号", "2", true, true, true, "下一个", true, true, true));
        numbers.add(new MorseTutorial("在垫按发送号码3：点点点划线划线", "3", true, true, true, "下一个", true, true, true));
        numbers.add(new MorseTutorial("在垫按发送号码4：点点点点划线", "4", true, true, true, "下一个", true, true, true));
        numbers.add(new MorseTutorial("在垫按发送号码5：点点点点点", "5", true, false, true, "下一个", true, true, true));
        numbers.add(new MorseTutorial("在垫按发送号码6：破折号点点点点", "6", true, false, true, "下一个", true, true, true));
        numbers.add(new MorseTutorial("在垫按发送号码7：破折号点划线点点", "7", true, false, true, "下一个", true, true, true));
        numbers.add(new MorseTutorial("按下键盘发出8号：破折号破折号破折号点点", "8", true, false, true, "下一个", true, true, true));
        numbers.add(new MorseTutorial("按下键盘发送号码9：短跑冲刺冲刺冲刺点", "9", true, true, true, "下一个", true, true, true));

        return numbers;

    }


}



