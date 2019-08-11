package id.okvi.tp4d.Activity.petugas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import id.okvi.tp4d.Activity.kajari.LoginKajariActivity;
import id.okvi.tp4d.R;

public class PilihLoginPetugasActivity extends AppCompatActivity {

    private ImageButton btnKajari;
    private ImageButton btnPetugas;
    private Context context = PilihLoginPetugasActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_login_petugas);

        btnKajari = findViewById(R.id.btnKajari);
        btnPetugas = findViewById(R.id.btnPetugas);

        btnKajari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, LoginKajariActivity.class));
            }
        });

        btnPetugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, LoginPetugasActivity.class));
            }
        });
    }
}
