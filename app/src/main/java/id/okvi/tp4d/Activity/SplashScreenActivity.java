package id.okvi.tp4d.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import id.okvi.tp4d.Helper.SharedPreferenceManager;
import id.okvi.tp4d.R;

public class SplashScreenActivity extends AppCompatActivity {
    private Context context = SplashScreenActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedPreferenceManager.getInstance(context).isLoggedIn()) {
                    if (SharedPreferenceManager.getInstance(context).getUser()
                            .getJenis().equalsIgnoreCase("pemohon")) {
                        startActivity(new Intent(context, PemohonHomeActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(context, PemohonLoginActivity.class));
                        finish();
                    }
                } else {
                    Intent home = new Intent(getApplicationContext(), PemohonLoginActivity.class);
                    startActivity(home);
                    finish();
                }
            }
        }, 3000);
    }
}
