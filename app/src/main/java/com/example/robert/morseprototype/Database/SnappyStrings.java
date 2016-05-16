package com.example.robert.morseprototype.Database;


import android.content.Context;

import com.example.robert.morseprototype.Misc.Logger;
import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

import java.util.ArrayList;


public class SnappyStrings {


    private static DB snappyDBStrings;
    private static Context context;
    static ArrayList<String> list;

    public static void SnappyStringsInit(Context context){
        SnappyStrings.context = context;

    }


    public static void initSnappy(){

        try {
            snappyDBStrings = DBFactory.open(context, "receiveStrings");


            if (snappyStringsExist()) {
                list = getSnappy();

            } else {

                list = new ArrayList<>();

                populateList();

            }

        }catch(SnappydbException e){
            Logger.log("Init snappy String exception");
        }

    }


    @SuppressWarnings("unchecked")
    public static ArrayList<String> getSnappy(){
        try {

            if(snappyStringsExist()){
                list = snappyDBStrings.getObject("receiveStrings", ArrayList.class);}
        }catch(SnappydbException e){
            Logger.log("Get snappy String exception");
        }

        return list;

    }


    private static void saveSnappyStrings(){
        try {
            snappyDBStrings.put("receiveStrings", list);
        }catch(SnappydbException e){
            Logger.log("Save snappy Strings exception");
        }
    }


    public static boolean snappyStringsExist(){

        boolean exists = false;
        try {
            exists = snappyDBStrings.exists("receiveStrings");
        }catch(SnappydbException e){
            Logger.log("Exist snappy String exception");
        }

        return exists;
    }




    public static void closeSnappyStrings(){
        try {
            snappyDBStrings.close();
        }catch(SnappydbException e)
        {
            Logger.log("SnappyString close exception");
        }
    }


    private static void populateList(){

        String s1  = "Hello";
        String s2  = "Good bye";
        String s3  = "Morse";
        String s4  = "SOS";
        String s5  = "Message";
        String s6  = "Turn back";
        String s7  = "Iceberg";
        String s8  = "Captain";
        String s9  = "Land ahead";
        String s10 = "Morse is fun";
        String s11 = "Resend";
        String s12 = "Flag";
        String s13 = "Please send again";
        String s14 = "Transmit";
        String s15 = "Fire on board";
        String s16 = "Water on board";
        String s17 = "Need a tow";
        String s18 = "Light house";
        String s19 = "Trawler";
        String s20 = "Port";
        String s21 = "Break";
        String s22 = "Lands end";
        String s23 = "Unreadable";
        String s24 = "Weak Signal";
        String s25 = "Flagstaff ";
        String s26 = "Mainstay ";
        String s27 = "Ahoy";
        String s28 = "Astern";
        String s29 = "Signal";
        String s30 = "Starboard ";



        String s31  = "Buenos dias";
        String s32  = "Adios";
        String s33  = "Gracías";
        String s34  = "Si";
        String s35  = "Yo no comprendo";
        String s36  = "Bueno";
        String s37  = "Hola";
        String s38  = "Encontrar";
        String s39  = "Primero";
        String s40  = "Entre";


        String s41  = "Xie";
        String s42  = "Ni hao";
        String s43  = "Ying yuv";
        String s44  = "Ling";
        String s45  = "Liu";
        String s46  = "Shi";
        String s47  = "Kai";
        String s48  = "Guan";
        String s49  = "Zuov";
        String s50  = "Di tu";

        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);
        list.add(s6);
        list.add(s7);
        list.add(s8);
        list.add(s9);
        list.add(s10);
        list.add(s11);
        list.add(s12);
        list.add(s13);
        list.add(s14);
        list.add(s15);
        list.add(s16);
        list.add(s17);
        list.add(s18);
        list.add(s19);
        list.add(s20);
        list.add(s21);
        list.add(s22);
        list.add(s23);
        list.add(s24);
        list.add(s25);
        list.add(s26);
        list.add(s27);
        list.add(s28);
        list.add(s29);
        list.add(s30);
        list.add(s31);
        list.add(s32);
        list.add(s33);
        list.add(s34);
        list.add(s35);
        list.add(s36);
        list.add(s37);
        list.add(s38);
        list.add(s39);
        list.add(s40);
        list.add(s41);
        list.add(s42);
        list.add(s43);
        list.add(s44);
        list.add(s45);
        list.add(s46);
        list.add(s47);
        list.add(s48);
        list.add(s49);
        list.add(s50);


        saveSnappyStrings();

    }

}