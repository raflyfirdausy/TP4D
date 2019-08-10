package id.okvi.tp4d.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import id.okvi.tp4d.Helper.SharedPreferenceManager;
import id.okvi.tp4d.R;

public class PetugasHomeActivity extends AppCompatActivity {
    protected Context context = PetugasHomeActivity.this;
    private ImageButton btnLogout;
    private TextView tvKeterangan;
    private LinearLayout llPermohonan;
    private LinearLayout llProgress;
    private LinearLayout llSelesai;
    private LinearLayout llDiTolak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petugas_home);
        init();
    }

    @SuppressLint("SetTextI18n")
    private void init() {
        btnLogout = findViewById(R.id.btnLogout);
        tvKeterangan = findViewById(R.id.tvKeterangan);
        llPermohonan = findViewById(R.id.llPermohonan);
        llProgress = findViewById(R.id.llProgress);
        llSelesai = findViewById(R.id.llSelesai);
        llDiTolak = findViewById(R.id.llDiTolak);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferenceManager.getInstance(context).logout();
            }
        });

        tvKeterangan.setText(
                SharedPreferenceManager.getInstance(context).getUser().getNama() + " | " +
                        SharedPreferenceManager.getInstance(context).getUser().getNip() + " | " +
                        SharedPreferenceManager.getInstance(context).getUser().getJenis()
        );
    }
}
