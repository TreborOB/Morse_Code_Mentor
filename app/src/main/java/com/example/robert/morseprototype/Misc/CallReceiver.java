package com.example.robert.morseprototype.Misc;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.google.common.base.Strings;


import com.example.robert.morseprototype.Hardware.Output;

public class CallReceiver extends BroadcastReceiver {

    Output mOutput;


    @Override
    public void onReceive(final Context context, Intent intent) {
        TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);


                String st;

                String name = getContactName(context, incomingNumber);

                System.out.println("incomingNumber : "+incomingNumber);
                System.out.println("incoming caller name : "+name);

                mOutput = new Output(context, false, true, true, false);

                final MorseTranslations morseTranslations = new MorseTranslations();

                if(Strings.isNullOrEmpty(name))
                {
                    st = "e e e " + morseTranslations.translatedText(incomingNumber);
                }else{
                    st = "e e e " + morseTranslations.translatedText(name);
                }

                if(checkVibrationIsOn(context)) {
                    Logger.log("Vibrate is off");
                    mOutput.outputString(st);
                }

            }
        },PhoneStateListener.LISTEN_CALL_STATE);
    }


    public static String getContactName(Context context, String phoneNumber) {
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
        Cursor cursor = cr.query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null);
        if (cursor == null) {
            return null;
        }
        String contactName = null;
        if(cursor.moveToFirst()) {
            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
        }

        if(!cursor.isClosed()) {
            cursor.close();
        }

        return contactName;
    }


    public boolean checkVibrationIsOn(Context context){
        boolean status = false;
        AudioManager am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        if(am.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE || am.getRingerMode() == AudioManager.RINGER_MODE_NORMAL){
            am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            status = true;
        } else if(am.getRingerMode() == AudioManager.RINGER_MODE_SILENT){
            status = false;
        }
        return status;
    }
}




