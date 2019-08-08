package id.okvi.tp4d.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import id.okvi.tp4d.Helper.SharedPreferenceManager;
import id.okvi.tp4d.R;

public class PemohonHomeActivity extends AppCompatActivity {
    private Context context = PemohonHomeActivity.this;
    private TextView tvInstansi;
    private ImageButton btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemohon_home);

        tvInstansi = findViewById(R.id.tvInstansi);
        btnLogout = findViewById(R.id.btnLogout);

        tvInstansi.setText("Selamat Datang : " + SharedPreferenceManager.getInstance(context).getUser().getInstansi());
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferenceManager.getInstance(context).logout();
            }
        });
    }
}
