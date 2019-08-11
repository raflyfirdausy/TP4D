package id.okvi.tp4d.Activity.other;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import id.okvi.tp4d.Activity.petugas.PilihLoginPetugasActivity;
import id.okvi.tp4d.Helper.Bantuan;
import id.okvi.tp4d.R;

public class VerifikasiActivity extends AppCompatActivity {
    private Context context = VerifikasiActivity.this;
    private TextInputLayout etKodeMasuk;
    private TextInputLayout etPassword;
    private Button btnVerifikasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifikasi);
        init();
    }

    private void init() {
        etKodeMasuk = findViewById(R.id.etKodeMasuk);
        etPassword = findViewById(R.id.etPassword);
        btnVerifikasi = findViewById(R.id.btnVerifikasi);

        btnVerifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prosesVerifikasi();
            }
        });
    }

    private void prosesVerifikasi() {
        if (etKodeMasuk.getEditText().getText().toString().equals("Bawor2310") &&
                etPassword.getEditText().getText().toString().equals("indonesia")) {
            new Bantuan(context).toastLong("Verifikasi Sukses");
            startActivity(new Intent(context, PilihLoginPetugasActivity.class));
        } else {
            new Bantuan(context).alertDialogPeringatan("Kode masuk atau password salah!");
        }
    }
}
