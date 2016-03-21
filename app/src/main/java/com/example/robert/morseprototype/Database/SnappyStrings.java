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

    public SnappyStrings(Context context){
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
        String s10 = "Signal";

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


        saveSnappyStrings();

    }


}
