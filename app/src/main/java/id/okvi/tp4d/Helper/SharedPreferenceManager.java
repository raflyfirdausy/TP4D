package id.okvi.tp4d.Helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import id.okvi.tp4d.Activity.other.SplashScreenActivity;
import id.okvi.tp4d.Model.UserLoginModel;

public class SharedPreferenceManager {
    private static final String SHARED_PREF_NAME = "tp4d";
    private static final String ID_PEMOHON = "id_pemohon";
    private static final String JENIS = "jenis";
    private static final String EMAIL = "email";
    private static final String INSTANSI = "instansi";
    private static final String NIP = "nip";
    private static final String NAMA = "nama";
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
        editor.putString(ID_PEMOHON, userLoginModel.getId_pemohon());
        editor.putString(JENIS, userLoginModel.getJenis());
        editor.putString(EMAIL, userLoginModel.getEmail());
        editor.putString(INSTANSI, userLoginModel.getInstansi());
        editor.putString(NIP, userLoginModel.getNip());
        editor.putString(NAMA, userLoginModel.getNama());
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(EMAIL, null) != null && sharedPreferences.getString(JENIS, null) != null;
    }

    public UserLoginModel getUser() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        UserLoginModel userLoginModel = new UserLoginModel();
        userLoginModel.setId_pemohon(sharedPreferences.getString(ID_PEMOHON, null));
        userLoginModel.setJenis(sharedPreferences.getString(JENIS, null));
        userLoginModel.setEmail(sharedPreferences.getString(EMAIL, null));
        userLoginModel.setInstansi(sharedPreferences.getString(INSTANSI, null));
        userLoginModel.setNip(sharedPreferences.getString(NIP, null));
        userLoginModel.setNama(sharedPreferences.getString(NAMA, null));
        return userLoginModel;
    }

    public void logout() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(context, SplashScreenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}
