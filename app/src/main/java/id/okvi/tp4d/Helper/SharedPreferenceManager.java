package id.okvi.tp4d.Helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import id.okvi.tp4d.Activity.SplashScreenActivity;
import id.okvi.tp4d.Model.UserLoginModel;

public class SharedPreferenceManager {
    private static final String SHARED_PREF_NAME = "tp4d";
    private static final String JENIS = "jenis";
    private static final String EMAIL = "email";
    private static final String INSTANSI = "instansi";
    @SuppressLint("StaticFieldLeak")
    private static SharedPreferenceManager instance;
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private SharedPreferenceManager(Context context) {
        SharedPreferenceManager.context = context;
    }

    public static synchronized SharedPreferenceManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferenceManager(context);
        }
        return instance;
    }

    public void userLogin(UserLoginModel userLoginModel) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(JENIS, userLoginModel.getJenis());
        editor.putString(EMAIL, userLoginModel.getEmail());
        editor.putString(INSTANSI, userLoginModel.getInstansi());
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(EMAIL, null) != null && sharedPreferences.getString(JENIS, null) != null;
    }

    public UserLoginModel getUser() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        UserLoginModel userLoginModel = new UserLoginModel();
        userLoginModel.setEmail(sharedPreferences.getString(EMAIL, null));
        userLoginModel.setJenis(sharedPreferences.getString(JENIS, null));
        userLoginModel.setInstansi(sharedPreferences.getString(INSTANSI, null));
        return userLoginModel;
    }

    public void logout() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        context.startActivity(new Intent(context, SplashScreenActivity.class));
    }
}
