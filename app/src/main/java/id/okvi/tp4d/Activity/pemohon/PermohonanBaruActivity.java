package id.okvi.tp4d.Activity.pemohon;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import id.okvi.tp4d.Helper.SharedPreferenceManager;
import id.okvi.tp4d.Model.DaftarPemohonModel;
import id.okvi.tp4d.R;

public class PermohonanBaruActivity extends AppCompatActivity {
    private Context context = PermohonanBaruActivity.this;
    private TextInputEditText etInstansiPemohon;
    private TextInputEditText etAlamatInstansi;
    private TextInputEditText etNomorSurat;
    private TextInputEditText etTanggalSurat;
    private TextInputEditText etTanggalMasuk;
    private ImageButton btnKirim;
    private int tanggal, bulan, tahun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permohonan_baru);
        etInstansiPemohon = findViewById(R.id.etInstansiPemohon);
        etAlamatInstansi = findViewById(R.id.etAlamatInstansi);
        etNomorSurat = findViewById(R.id.etNomorSurat);
        etTanggalSurat = findViewById(R.id.etTanggalSurat);
        etTanggalMasuk = findViewById(R.id.etTanggalMasuk);
        btnKirim = findViewById(R.id.btnKirim);

        etTanggalSurat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTanggal("tanggalSurat");
            }
        });

        etTanggalMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTanggal("tanggalMasuk");
            }
        });

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kirimData();
            }
        });

    }

    private void kirimData() {
        if (TextUtils.isEmpty(etInstansiPemohon.getText().toString())) {
            etInstansiPemohon.setError("Instansi Belum diisi");
        } else if (TextUtils.isEmpty(etAlamatInstansi.getText().toString())) {
            etAlamatInstansi.setError("Alamat Belum diisi");
        } else if (TextUtils.isEmpty(etNomorSurat.getText().toString())) {
            etNomorSurat.setError("Nomor Surat Belum diisi");
        } else if (TextUtils.isEmpty(etTanggalSurat.getText().toString())) {
            etTanggalSurat.setError("Tanggal Surat Belum diisi");
        } else if (TextUtils.isEmpty(etTanggalMasuk.getText().toString())) {
            etTanggalMasuk.setError("Tanggal Masuk Belum diisi");
        } else {
            DaftarPemohonModel daftarPemohonModel = new DaftarPemohonModel();
            daftarPemohonModel.setId_pemohon(SharedPreferenceManager.getInstance(context).getUser().getId_pemohon());
            daftarPemohonModel.setInstansi_pemohon(etInstansiPemohon.getText().toString());
            daftarPemohonModel.setAlamat_instansi(etAlamatInstansi.getText().toString());
            daftarPemohonModel.setNomer_surat(etNomorSurat.getText().toString());
            daftarPemohonModel.setTanggal_surat(etTanggalSurat.getText().toString());
            daftarPemohonModel.setTanggal_masuk(etTanggalMasuk.getText().toString());

            Intent intent = new Intent(context, PermohonanBaruDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("data", daftarPemohonModel);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    private void getTanggal(final String jenis) {
        final Calendar c = Calendar.getInstance();
        tanggal = c.get(Calendar.DAY_OF_MONTH);
        bulan = c.get(Calendar.MONTH);
        tahun = c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        if (jenis.equalsIgnoreCase("tanggalSurat")) {
                            etTanggalSurat.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } else {
                            etTanggalMasuk.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        }
                    }
                }, tahun, bulan, tanggal);
        datePickerDialog.show();
    }
}
