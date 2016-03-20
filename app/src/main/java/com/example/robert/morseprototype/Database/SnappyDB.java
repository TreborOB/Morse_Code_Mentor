package com.example.robert.morseprototype.Database;


import android.content.Context;

import com.example.robert.morseprototype.Misc.Logger;
import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

import java.util.HashMap;

public class SnappyDB {


    private static DB snappyDB;
    private static Context context;


    static HashMap<String, Boolean> map;



    public SnappyDB(Context context){
        SnappyDB.context = context;

    }


    public static void initSnappy(){

        try {
            snappyDB = DBFactory.open(context, "testProgress");



            if (snappyExist()) {
                map = getSnappy();
            } else {
                map = new HashMap<>();

            }

        }catch(SnappydbException e){
            Logger.log("Init snappy exception");
        }

     }


    private static void saveSnappy(){
        try {
            snappyDB.put("testProgress", map);
        }catch(SnappydbException e){
           Logger.log("Save snappy exception");
        }
    }


    @SuppressWarnings("unchecked")
    public static HashMap<String, Boolean> getSnappy(){
        try {

            if(snappyExist()){
                map = snappyDB.getObject("testProgress", HashMap.class);}
        }catch(SnappydbException e){
            Logger.log("Get snappy exception");
        }

        return map;

    }


    public static void insertElement(String letterProgress){
        map.put(letterProgress, true);
        saveSnappy();
    }



    public static boolean snappyExist(){

          boolean exists = false;
          try {
              exists = snappyDB.exists("testProgress");
          }catch(SnappydbException e){
              Logger.log("Exist snappy exception");
          }

          return exists;
      }








}
