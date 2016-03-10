package com.example.robert.morseprototype.Hardware;


import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;




public class Light {

    private Camera            mCamera;
    private Camera.Parameters mParameters;
    private boolean           mHasCamera;

    public Light(Context context) {
        mCamera = Camera.open();
        mParameters = mCamera.getParameters();
        mHasCamera = hasCamera(context);
    }

    public void turnOn() {
        if (mHasCamera) {
            mParameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
            mCamera.setParameters(mParameters);
            mCamera.startPreview();
        }
    }

    public void turnOff() {
        if (mHasCamera) {
            mParameters.setFlashMode(Parameters.FLASH_MODE_OFF);
            mCamera.setParameters(mParameters);
            mCamera.stopPreview();
        }
    }

    public void release() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }

    public static boolean hasCamera(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }
}