package id.okvi.tp4d.Activity.petugas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
    private String jenis, jenisFix;

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

        jenis = SharedPreferenceManager.getInstance(context).getUser().getJenis();
        if (jenis.equalsIgnoreCase("kajari")) {
            jenisFix = "Kajari";
        } else if (jenis.equalsIgnoreCase("pokja1")) {
            jenisFix = "Pokja 1";
        } else if (jenis.equalsIgnoreCase("pokja2")) {
            jenisFix = "Pokja 2";
        } else if (jenis.equalsIgnoreCase("pokja3")) {
            jenisFix = "Pokja 3";
        } else if (jenis.equalsIgnoreCase("legal")) {
            jenisFix = "Legal Asistance";
        } else {
            jenisFix = "Tidak Diketahui";
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferenceManager.getInstance(context).logout();
            }
        });

        tvKeterangan.setText(
                SharedPreferenceManager.getInstance(context).getUser().getNama() + " | " +
                        SharedPreferenceManager.getInstance(context).getUser().getNip() + " | " +
                        jenisFix
        );

        llPermohonan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, PetugasBaruProgressSelesaiTolakActivity.class)
                        .putExtra("mode", "baru"));
            }
        });

        llProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, PetugasBaruProgressSelesaiTolakActivity.class)
                        .putExtra("mode", "progress"));
            }
        });

        llSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, PetugasBaruProgressSelesaiTolakActivity.class)
                        .putExtra("mode", "selesai"));
            }
        });

        llDiTolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, PetugasBaruProgressSelesaiTolakActivity.class)
                        .putExtra("mode", "tolak"));
            }
        });
    }
}
