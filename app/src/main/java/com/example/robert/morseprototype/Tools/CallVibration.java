package com.example.robert.morseprototype.Tools;


import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.example.robert.morseprototype.Misc.TinyDB;
import com.example.robert.morseprototype.Misc.CallReceiver;
import com.example.robert.morseprototype.R;
import com.gc.materialdesign.views.Switch;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CallVibration extends AppCompatActivity {


    @Bind(R.id.textView18)       TextView enableDisable;
    @Bind(R.id.vibrateSwitchCall)Switch switchCall;

    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_vibration);
        ButterKnife.bind(this);

        tinyDB = new TinyDB(getApplicationContext());


        boolean tf = tinyDB.getBoolean("vibrate");

        if(tf){
            enableDisable.setText("Enabled");
            switchCall.setChecked(true);
        }else{
            enableDisable.setText("Disabled");
            switchCall.setChecked(false);
        }


        switchCall.setOncheckListener(new Switch.OnCheckListener() {
            @Override
            public void onCheck(Switch view, boolean check) {

                if(check){
                    enableDisable.setText("Enabled");
                    tinyDB.putBoolean("vibrate", true);

                    PackageManager pm  = CallVibration.this.getPackageManager();
                    ComponentName componentName = new ComponentName(CallVibration.this, CallReceiver.class);
                    pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                            PackageManager.DONT_KILL_APP);
                    Toast.makeText(getApplicationContext(), "activated", Toast.LENGTH_LONG).show();


                }else{
                    enableDisable.setText("Disabled");
                    tinyDB.putBoolean("vibrate", false);

                    PackageManager pm  = CallVibration.this.getPackageManager();
                    ComponentName componentName = new ComponentName(CallVibration.this, CallReceiver.class);
                    pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                            PackageManager.DONT_KILL_APP);
                    Toast.makeText(getApplicationContext(), "cancelled", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
