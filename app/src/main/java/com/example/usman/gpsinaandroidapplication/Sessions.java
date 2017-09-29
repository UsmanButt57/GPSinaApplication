package com.example.usman.gpsinaandroidapplication;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Usman on 9/27/17.
 */

public class Sessions {

    //    public final static String SANDBOX_PAYPAL_ID = "AagcaqBBLoGCNno2O8gZXG_M-Ud5NX8XfG7tXuVLjkkhBSG6KzxYTEZ3g2adlhtm4fDLzrto5VSM7vTg";
//    public final static String LIVE_PAYPAL_ID = "AFcWxV21C7fd0v3bYYYRCpSSRl31AACDTrz2XoItuYR.j4t9V9qmBrLV";

    public static final String LOGIN_URL = "http://apitest.gpsina.com/account/MobileOwnerlogin";

    public static final String SignUpCheck_URL = "http://apitest.gpsina.com/account/CheckUserExist";

    public static final String SignUpRegister_URL = "http://apitest.gpsina.com/account/OwnerRegister";

    public static final String SHARED_PREF_NAME = "foodies";

    public static void putPref(String key, String value, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getPref(String key, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString(key, null);
    }
}