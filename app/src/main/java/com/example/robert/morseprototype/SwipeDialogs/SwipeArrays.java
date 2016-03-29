package com.example.robert.morseprototype.SwipeDialogs;

import com.example.robert.morseprototype.R;

import java.util.ArrayList;

public class SwipeArrays {

    public static ArrayList<MorseSymbols> getLetters() {
        ArrayList<MorseSymbols> res = new ArrayList<>();

        res.add(new MorseSymbols("A", ".-", R.raw.a));
        res.add(new MorseSymbols("B", "-...", R.raw.b));
        res.add(new MorseSymbols("C", "-.-.", R.raw.c));
        res.add(new MorseSymbols("D", "-..", R.raw.d));
        res.add(new MorseSymbols("E", ".", R.raw.e));
        res.add(new MorseSymbols("F", "..-.", R.raw.f));
        res.add(new MorseSymbols("G", "--.", R.raw.g));
        res.add(new MorseSymbols("H", "....", R.raw.h));
        res.add(new MorseSymbols("I", "..", R.raw.i));
        res.add(new MorseSymbols("J", ".---", R.raw.j));
        res.add(new MorseSymbols("K", "-.-", R.raw.k));
        res.add(new MorseSymbols("L", ".-..", R.raw.l));
        res.add(new MorseSymbols("M", "--", R.raw.m));
        res.add(new MorseSymbols("N", "-.", R.raw.n));
        res.add(new MorseSymbols("O", "---", R.raw.o));
        res.add(new MorseSymbols("P", ".--.", R.raw.p));
        res.add(new MorseSymbols("Q", "--.-", R.raw.q));
        res.add(new MorseSymbols("R", ".-.", R.raw.r));
        res.add(new MorseSymbols("S", "...", R.raw.s));
        res.add(new MorseSymbols("T", "-", R.raw.t));
        res.add(new MorseSymbols("U", ".--", R.raw.u));
        res.add(new MorseSymbols("V", "...-", R.raw.v));
        res.add(new MorseSymbols("W", ".--", R.raw.w));
        res.add(new MorseSymbols("X", "-..-", R.raw.x));
        res.add(new MorseSymbols("Y", "-.--", R.raw.y));
        res.add(new MorseSymbols("Z", "--..", R.raw.z));

        return res;
    }

    public static ArrayList<MorseSymbols> getNumbers() {
        ArrayList<MorseSymbols> res = new ArrayList<>();

        res.add(new MorseSymbols("0", "-----", R.raw.zero));
        res.add(new MorseSymbols("1", ".----", R.raw.one));
        res.add(new MorseSymbols("2", "..---", R.raw.two));
        res.add(new MorseSymbols("3", "...--", R.raw.three));
        res.add(new MorseSymbols("4", "....-", R.raw.four));
        res.add(new MorseSymbols("5", ".....", R.raw.five));
        res.add(new MorseSymbols("6", "-....", R.raw.six));
        res.add(new MorseSymbols("7", "--...", R.raw.seven));
        res.add(new MorseSymbols("8", "---..", R.raw.eight));
        res.add(new MorseSymbols("9", "----.", R.raw.nine));

        return res;
    }

    public static ArrayList<MorseSymbols> getQcodes() {
        ArrayList<MorseSymbols> res = new ArrayList<>();

        res.add(new MorseSymbols("QRA", "What is the name of your station?", R.raw.qra));
        res.add(new MorseSymbols("QRB", "How far are you from my station?", R.raw.qrb));
        res.add(new MorseSymbols("QRD", "How far are you from my station?", R.raw.qrd));
        res.add(new MorseSymbols("QRG", "Will you tell me my exact frequency?", R.raw.qrg));
        res.add(new MorseSymbols("QRH", "Does my frequency vary?", R.raw.qrh));
        res.add(new MorseSymbols("QRI", "How is the tone of my transmission?", R.raw.qri));
        res.add(new MorseSymbols("QRJ", "Are you receiving me badly?", R.raw.qrj));
        res.add(new MorseSymbols("QRK", "What is the intelligibility of my signals?", R.raw.qrk));
        res.add(new MorseSymbols("QRL", "Are you busy?", R.raw.qrl));
        res.add(new MorseSymbols("QRM", "Is my transmission being interfered with?", R.raw.qrm));

        return res;
}

    public static ArrayList<MorseSymbols> getZcodes() {
        ArrayList<MorseSymbols> res = new ArrayList<>();

        res.add(new MorseSymbols("ZAE", "What is the name of your station?", R.raw.zae));
        res.add(new MorseSymbols("ZAL", "I am closing down (until...) due to....", R.raw.zal));
        res.add(new MorseSymbols("ZAP", "Shall i work with...", R.raw.zap));
        res.add(new MorseSymbols("ZAX", "You are causing interference", R.raw.zax));
        res.add(new MorseSymbols("ZBA", "What is the cause of the delay?", R.raw.zba));
        res.add(new MorseSymbols("ZBK", "Are you receiving my traffic clear?", R.raw.zbk));
        res.add(new MorseSymbols("ZBO", "Of what precedence is your message?", R.raw.zbo));
        res.add(new MorseSymbols("ZBQ", "When and on what frequency was the message received? ?", R.raw.zbq));
        res.add(new MorseSymbols("ZBR", "Shall i send by...?", R.raw.zbr));
        res.add(new MorseSymbols("ZUJ", "Stand by", R.raw.zuj));

        return res;
    }




    //Spanish Q codes
    public static ArrayList<MorseSymbols> getQcodesSpanish() {
        ArrayList<MorseSymbols> res = new ArrayList<>();

        res.add(new MorseSymbols("QRA", "Cuál es el nombre de su estación?", R.raw.qra));
        res.add(new MorseSymbols("QRB", "Qué tan lejos está de mi estación?", R.raw.qrb));
        res.add(new MorseSymbols("QRD", "Dónde están obligados a?", R.raw.qrd));
        res.add(new MorseSymbols("QRG", "Me dirá mi frecuencia exacta?", R.raw.qrg));
        res.add(new MorseSymbols("QRH", "Varía mi frecuencia?", R.raw.qrh));
        res.add(new MorseSymbols("QRI", "Cómo es el tono de mi transmisión?", R.raw.qri));
        res.add(new MorseSymbols("QRJ", "Me estás recibiendo mal?", R.raw.qrj));
        res.add(new MorseSymbols("QRK", "Cuál es la inteligibilidad de mi transmisión?", R.raw.qrk));
        res.add(new MorseSymbols("QRL", "Estás ocupado?", R.raw.qrl));
        res.add(new MorseSymbols("QRM", "Es mi transmisión interferido?", R.raw.qrm));

        return res;
    }


    //Chinese Q codes
    public static ArrayList<MorseSymbols> getQcodesChinese() {
        ArrayList<MorseSymbols> res = new ArrayList<>();

        res.add(new MorseSymbols("QRA", "什么是你的站的名字吗？", R.raw.qra));
        res.add(new MorseSymbols("QRB", "有多远，你从我站的是谁？", R.raw.qrb));
        res.add(new MorseSymbols("QRD", "你是哪里开往?", R.raw.qrd));
        res.add(new MorseSymbols("QRG", "你能告诉我，我的准确频率?", R.raw.qrg));
        res.add(new MorseSymbols("QRH", "请问我的频率而变化?", R.raw.qrh));
        res.add(new MorseSymbols("QRI", "如何为我传递的基调?", R.raw.qri));
        res.add(new MorseSymbols("QRJ", "你接受我不好?", R.raw.qrj));
        res.add(new MorseSymbols("QRK", "W这是我的信号的可懂度?", R.raw.qrk));
        res.add(new MorseSymbols("QRL", "你忙吗?", R.raw.qrl));
        res.add(new MorseSymbols("QRM", "我正在传输干扰?", R.raw.qrm));

        return res;
    }





    public static ArrayList<MorseSymbols> getZcodesSpanish() {
        ArrayList<MorseSymbols> res = new ArrayList<>();

        res.add(new MorseSymbols("ZAE", "Cuál es el nombre de su estación?", R.raw.zae));
        res.add(new MorseSymbols("ZAL", "Estoy cerrando hacia abajo (hasta ...) debido a ....", R.raw.zal));
        res.add(new MorseSymbols("ZAP", "Debo trabajar con ...", R.raw.zap));
        res.add(new MorseSymbols("ZAX", "Usted está causando la interferencia", R.raw.zax));
        res.add(new MorseSymbols("ZBA", "Cuál es la causa de la demora?", R.raw.zba));
        res.add(new MorseSymbols("ZBK", "Está recibiendo el tráfico de mi clara?", R.raw.zbk));
        res.add(new MorseSymbols("ZBO", "Por lo que prevalece es su mensaje??", R.raw.zbo));
        res.add(new MorseSymbols("ZBQ", "Cuándo y en qué frecuencia se recibió el mensaje?", R.raw.zbq));
        res.add(new MorseSymbols("ZBR", "Debo transmitir por ...?", R.raw.zbr));
        res.add(new MorseSymbols("ZUJ", "Colocarse", R.raw.zuj));

        return res;
    }


    public static ArrayList<MorseSymbols> getZcodesChinese() {
        ArrayList<MorseSymbols> res = new ArrayList<>();

        res.add(new MorseSymbols("ZAE", "什么是你的站名?", R.raw.zae));
        res.add(new MorseSymbols("ZAL", "我关闭了（直到......），由于....", R.raw.zal));
        res.add(new MorseSymbols("ZAP", "要我一起工作...", R.raw.zap));
        res.add(new MorseSymbols("ZAX", "您正在造成干扰", R.raw.zax));
        res.add(new MorseSymbols("ZBA", "是什么原因延误?", R.raw.zba));
        res.add(new MorseSymbols("ZBK", "您是否收到我的流量清楚?", R.raw.zbk));
        res.add(new MorseSymbols("ZBO", "什么是优先级的邮件？", R.raw.zbo));
        res.add(new MorseSymbols("ZBQ", "当和收到什么频率的信息?", R.raw.zbq));
        res.add(new MorseSymbols("ZBR", "要不要我送的...？", R.raw.zbr));
        res.add(new MorseSymbols("ZUJ", "支持", R.raw.zuj));

        return res;
    }


}
