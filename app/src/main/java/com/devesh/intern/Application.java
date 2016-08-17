package com.devesh.intern;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by RAJUL on 7/12/2016.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        printHashKey();
    }

    public void printHashKey() {

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.rajul.myapplication",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash::::", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

    }

}
