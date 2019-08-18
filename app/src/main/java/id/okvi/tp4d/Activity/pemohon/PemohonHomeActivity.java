package id.okvi.tp4d.Activity.pemohon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import id.okvi.tp4d.Helper.SharedPreferenceManager;
import id.okvi.tp4d.R;

public class PemohonHomeActivity extends AppCompatActivity {
    private Context context = PemohonHomeActivity.this;
    private TextView tvInstansi;
    private ImageButton btnLogout;
    private ViewFlipper v_flipper;
    private LinearLayout llPermohonan;
    private LinearLayout llProgress;
    private LinearLayout llSelesai;
    private int[] images = {R.drawable.tp3, R.drawable.tp2, R.drawable.tp1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemohon_home);

        tvInstansi = findViewById(R.id.tvInstansi);
        btnLogout = findViewById(R.id.btnLogout);
        v_flipper = findViewById(R.id.v_flipper);
        llPermohonan = findViewById(R.id.llPermohonan);
        llProgress = findViewById(R.id.llProgress);
        llSelesai = findViewById(R.id.llSelesai);

        tvInstansi.setText("Selamat Datang : " + SharedPreferenceManager.getInstance(context).getUser().getInstansi());
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferenceManager.getInstance(context).logout();
            }
        });

        llPermohonan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, PermohonanBaruActivity.class));
            }
        });

        llProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, PemohonProgressSelesaiActivity.class)
                        .putExtra("mode", "progress"));
            }
        });

        llSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, PemohonProgressSelesaiActivity.class)
                        .putExtra("mode", "selesai"));
            }
        });


        for (int i = 0; i < images.length; i++) {
            setFlipperImages(images[i]);
        }
    }

    private void setFlipperImages(int images) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(images);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }
}
