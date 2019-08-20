package id.okvi.tp4d.Activity.pemohon;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import id.okvi.tp4d.Helper.API;
import id.okvi.tp4d.Helper.Bantuan;
import id.okvi.tp4d.Helper.SharedPreferenceManager;
import id.okvi.tp4d.Model.DaftarPemohonModel;
import id.okvi.tp4d.R;

public class PermohonanBaruDetailActivity extends AppCompatActivity {
    private Context context = PermohonanBaruDetailActivity.this;

    private RadioGroup rgJenisKegiatan;
    private RadioButton rbPengadaanFisik;
    private RadioButton rbPengadaanSarpras;
    private RadioButton rbPengadaanTanah;

    private TextInputLayout etNamaKegiatan;
    private TextInputLayout etPaguAnggaran;
    private TextInputLayout etInstansi;
    private TextInputLayout etTahunAnggaran;
    private TextInputLayout etCaraPelaksanaan;
    private TextInputLayout etMetodePembayaran;
    private TextInputLayout etLokasiKegiatan;
    private TextInputLayout etKonsultanPerencanaan;

    private TextInputLayout etUitzetPerencanaan;
    private TextInputLayout etPcmPersiapan;
    private TextInputLayout etMcPelaksanaan;
    private TextInputLayout etPhoPenyerahanHasil;

    private TextInputLayout etNamaPegawaiPemohon;
    private TextInputLayout etNomorHp;
    private TextInputLayout etEmail;
    private TextInputLayout etAwalPekerjaan;
    private TextInputLayout etAkhirPekerjaan;
    private ImageView ivFotoDokumen;

    private ImageButton btnTambah;
    private ImageButton btnBatal;

    private String jenis = "Pengadaan Fisik";
    private int tanggal, bulan, tahun;
    private DaftarPemohonModel daftarPemohonModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permohonan_baru_detail);
        init();
    }

    private void init() {

        daftarPemohonModel = (DaftarPemohonModel) getIntent().getExtras().getSerializable("data");

        rgJenisKegiatan = findViewById(R.id.rgJenisKegiatan);
        rbPengadaanFisik = findViewById(R.id.rbPengadaanFisik);
        rbPengadaanSarpras = findViewById(R.id.rbPengadaanSarpras);
        rbPengadaanTanah = findViewById(R.id.rbPengadaanTanah);

        etNamaKegiatan = findViewById(R.id.etNamaKegiatan);
        etPaguAnggaran = findViewById(R.id.etPaguAnggaran);
        etInstansi = findViewById(R.id.etInstansi);
        etTahunAnggaran = findViewById(R.id.etTahunAnggaran);
        etCaraPelaksanaan = findViewById(R.id.etCaraPelaksanaan);
        etMetodePembayaran = findViewById(R.id.etMetodePembayaran);
        etLokasiKegiatan = findViewById(R.id.etLokasiKegiatan);
        etKonsultanPerencanaan = findViewById(R.id.etKonsultanPerencanaan);
        etUitzetPerencanaan = findViewById(R.id.etUitzetPerencanaan);
        etPcmPersiapan = findViewById(R.id.etPcmPersiapan);
        etMcPelaksanaan = findViewById(R.id.etMcPelaksanaan);
        etPhoPenyerahanHasil = findViewById(R.id.etPhoPenyerahanHasil);
        etNamaPegawaiPemohon = findViewById(R.id.etNamaPegawaiPemohon);
        etNomorHp = findViewById(R.id.etNomorHp);
        etEmail = findViewById(R.id.etEmail);
        etAwalPekerjaan = findViewById(R.id.etAwalPekerjaan);
        etAkhirPekerjaan = findViewById(R.id.etAkhirPekerjaan);

        ivFotoDokumen = findViewById(R.id.ivFotoDokumen);
        btnTambah = findViewById(R.id.btnTambah);
        btnBatal = findViewById(R.id.btnBatal);

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prosesSimpan();
            }
        });


        rgJenisKegiatan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radioGroup.getCheckedRadioButtonId() == rbPengadaanFisik.getId()) {
                    jenis = "Pengadaan Fisik";
                    etUitzetPerencanaan.setHint("Rencana Uitzet");
                    etPcmPersiapan.setHint("Rencana PCM");
                    etMcPelaksanaan.setHint("Rencana MC 50");
                    etPhoPenyerahanHasil.setHint("Rencana PHO");
                } else if (radioGroup.getCheckedRadioButtonId() == rbPengadaanSarpras.getId()) {
                    jenis = "Pengadaan Sarpras";
                    etUitzetPerencanaan.setHint("Rencana Uitzet");
                    etPcmPersiapan.setHint("Rencana PCM");
                    etMcPelaksanaan.setHint("Rencana MC 50");
                    etPhoPenyerahanHasil.setHint("Rencana PHO");
                } else if (radioGroup.getCheckedRadioButtonId() == rbPengadaanTanah.getId()) {
                    jenis = "Pengadaan Tanah";
                    etUitzetPerencanaan.setHint("Perencanaan");
                    etPcmPersiapan.setHint("Persiapan");
                    etMcPelaksanaan.setHint("Pelaksanaan");
                    etPhoPenyerahanHasil.setHint("Penyerahan Hasil");
                } else {
                    jenis = "gatau";
                    etUitzetPerencanaan.setHint("null");
                    etPcmPersiapan.setHint("null");
                    etMcPelaksanaan.setHint("null");
                    etPhoPenyerahanHasil.setHint("null");
                }
            }
        });

        etUitzetPerencanaan.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTanggal("etUitzetPerencanaan");
            }
        });

        etPcmPersiapan.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTanggal("etPcmPersiapan");
            }
        });

        etMcPelaksanaan.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTanggal("etMcPelaksanaan");
            }
        });

        etPhoPenyerahanHasil.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTanggal("etPhoPenyerahanHasil");
            }
        });

        etAwalPekerjaan.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTanggal("etAwalPekerjaan");
            }
        });

        etAkhirPekerjaan.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTanggal("etAkhirPekerjaan");
            }
        });

        ivFotoDokumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });
    }

    private void getTanggal(final String jenisCuy) {
        final Calendar c = Calendar.getInstance();
        tanggal = c.get(Calendar.DAY_OF_MONTH);
        bulan = c.get(Calendar.MONTH);
        tahun = c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        if (jenisCuy.equalsIgnoreCase("etUitzetPerencanaan")) {
                            etUitzetPerencanaan.getEditText().setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } else if (jenisCuy.equalsIgnoreCase("etPcmPersiapan")) {
                            etPcmPersiapan.getEditText().setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } else if (jenisCuy.equalsIgnoreCase("etMcPelaksanaan")) {
                            etMcPelaksanaan.getEditText().setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } else if (jenisCuy.equalsIgnoreCase("etPhoPenyerahanHasil")) {
                            etPhoPenyerahanHasil.getEditText().setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } else if (jenisCuy.equalsIgnoreCase("etAwalPekerjaan")) {
                            etAwalPekerjaan.getEditText().setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } else if (jenisCuy.equalsIgnoreCase("etAkhirPekerjaan")) {
                            etAkhirPekerjaan.getEditText().setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } else {
                            new Bantuan(context).toastLong("Error get tanggal ");
                        }
                    }
                }, tahun, bulan, tanggal);
        datePickerDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                assert data != null;
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                ivFotoDokumen.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                new Bantuan(context).toastLong(e.getMessage());
            }

        }
    }

    private void prosesSimpan() {

        etNamaKegiatan = findViewById(R.id.etNamaKegiatan);
        etPaguAnggaran = findViewById(R.id.etPaguAnggaran);
        etInstansi = findViewById(R.id.etInstansi);
        etTahunAnggaran = findViewById(R.id.etTahunAnggaran);
        etCaraPelaksanaan = findViewById(R.id.etCaraPelaksanaan);
        etMetodePembayaran = findViewById(R.id.etMetodePembayaran);
        etLokasiKegiatan = findViewById(R.id.etLokasiKegiatan);
        etKonsultanPerencanaan = findViewById(R.id.etKonsultanPerencanaan);
        etUitzetPerencanaan = findViewById(R.id.etUitzetPerencanaan);
        etPcmPersiapan = findViewById(R.id.etPcmPersiapan);
        etMcPelaksanaan = findViewById(R.id.etMcPelaksanaan);
        etPhoPenyerahanHasil = findViewById(R.id.etPhoPenyerahanHasil);
        etNamaPegawaiPemohon = findViewById(R.id.etNamaPegawaiPemohon);
        etNomorHp = findViewById(R.id.etNomorHp);
        etEmail = findViewById(R.id.etEmail);

        if (TextUtils.isEmpty(etNamaKegiatan.getEditText().getText().toString())) {
            etNamaKegiatan.getEditText().setError("Harus diisi");
            etNamaKegiatan.requestFocus();
        } else if (TextUtils.isEmpty(etPaguAnggaran.getEditText().getText().toString())) {
            etPaguAnggaran.getEditText().setError("Harus diisi");
            etPaguAnggaran.requestFocus();
        } else if (TextUtils.isEmpty(etInstansi.getEditText().getText().toString())) {
            etInstansi.getEditText().setError("Harus diisi");
            etInstansi.requestFocus();
        } else if (TextUtils.isEmpty(etTahunAnggaran.getEditText().getText().toString())) {
            etTahunAnggaran.getEditText().setError("Harus diisi");
            etTahunAnggaran.requestFocus();
        } else if (TextUtils.isEmpty(etCaraPelaksanaan.getEditText().getText().toString())) {
            etCaraPelaksanaan.getEditText().setError("Harus diisi");
            etCaraPelaksanaan.requestFocus();
        } else if (TextUtils.isEmpty(etMetodePembayaran.getEditText().getText().toString())) {
            etMetodePembayaran.getEditText().setError("Harus diisi");
            etMetodePembayaran.requestFocus();
        } else if (TextUtils.isEmpty(etLokasiKegiatan.getEditText().getText().toString())) {
            etLokasiKegiatan.getEditText().setError("Harus diisi");
            etLokasiKegiatan.requestFocus();
        } else if (TextUtils.isEmpty(etKonsultanPerencanaan.getEditText().getText().toString())) {
            etKonsultanPerencanaan.getEditText().setError("Harus diisi");
            etKonsultanPerencanaan.requestFocus();
        } else if (TextUtils.isEmpty(etUitzetPerencanaan.getEditText().getText().toString())) {
            etUitzetPerencanaan.getEditText().setError("Harus diisi");
            etUitzetPerencanaan.requestFocus();
        } else if (TextUtils.isEmpty(etPcmPersiapan.getEditText().getText().toString())) {
            etPcmPersiapan.getEditText().setError("Harus diisi");
            etPcmPersiapan.requestFocus();
        } else if (TextUtils.isEmpty(etMcPelaksanaan.getEditText().getText().toString())) {
            etMcPelaksanaan.getEditText().setError("Harus diisi");
            etMcPelaksanaan.requestFocus();
        } else if (TextUtils.isEmpty(etPhoPenyerahanHasil.getEditText().getText().toString())) {
            etPhoPenyerahanHasil.getEditText().setError("Harus diisi");
            etPhoPenyerahanHasil.requestFocus();
        } else if (TextUtils.isEmpty(etNamaPegawaiPemohon.getEditText().getText().toString())) {
            etNamaPegawaiPemohon.getEditText().setError("Harus diisi");
            etNamaPegawaiPemohon.requestFocus();
        } else if (TextUtils.isEmpty(etNomorHp.getEditText().getText().toString())) {
            etNomorHp.getEditText().setError("Harus diisi");
            etNomorHp.requestFocus();
        } else if (TextUtils.isEmpty(etEmail.getEditText().getText().toString())) {
            etEmail.getEditText().setError("Harus diisi");
            etEmail.requestFocus();
        } else if (ivFotoDokumen.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.ic_image).getConstantState()) {
            new Bantuan(context).alertDialogPeringatan("Tambahkan foto dokumen terlebih dahulu");
        } else if (TextUtils.isEmpty(etAwalPekerjaan.getEditText().getText().toString())) {
            etAwalPekerjaan.getEditText().setError("Harus diisi");
            etAwalPekerjaan.requestFocus();
        } else if (TextUtils.isEmpty(etAkhirPekerjaan.getEditText().getText().toString())) {
            etAkhirPekerjaan.getEditText().setError("Harus diisi");
            etAkhirPekerjaan.requestFocus();
        } else {
            final ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Loading ...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ((BitmapDrawable) ivFotoDokumen.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
            final String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

            RequestQueue requestQueue;
            requestQueue = Volley.newRequestQueue(context);
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    API.INSERT_PEMOHON,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONObject object = new JSONObject(response);
                                new Bantuan(context).toastLong(object.getString("result"));
                                if (object.getInt("status") == 1) {
                                    Intent intent = new Intent(context, BarcodeActivity.class);
                                    intent.putExtra("no_regis", object.getString("no_regis"));
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                new Bantuan(context).toastLong(e.getMessage());
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    new Bantuan(context).alertDialogInformasi(error.getMessage());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> stringMap = new HashMap<>();
                    stringMap.put("id_pemohon", SharedPreferenceManager.getInstance(context).getUser().getId_pemohon());
                    stringMap.put("instansi_pemohon", daftarPemohonModel.getInstansi_pemohon());
                    stringMap.put("alamat_instansi", daftarPemohonModel.getAlamat_instansi());
                    stringMap.put("nomer_surat", daftarPemohonModel.getNomer_surat());
                    stringMap.put("tanggal_surat", daftarPemohonModel.getTanggal_surat());
                    stringMap.put("tanggal_masuk", daftarPemohonModel.getTanggal_masuk());
                    stringMap.put("jenis_kegiatan", jenis);
                    stringMap.put("pagu_anggaran", etPaguAnggaran.getEditText().getText().toString());
                    stringMap.put("instansi", etInstansi.getEditText().getText().toString());
                    stringMap.put("tahun_anggaran", etTahunAnggaran.getEditText().getText().toString());
                    stringMap.put("pelaksanaan_dengan_cara", etCaraPelaksanaan.getEditText().getText().toString());
                    stringMap.put("metode_pembayaran", etMetodePembayaran.getEditText().getText().toString());
                    stringMap.put("lokasi_kegiatan", etLokasiKegiatan.getEditText().getText().toString());
                    stringMap.put("konsultan_perencanaan", etKonsultanPerencanaan.getEditText().getText().toString());
                    stringMap.put("uitzet_perencanaan", etUitzetPerencanaan.getEditText().getText().toString());
                    stringMap.put("pcm_persiapan", etPcmPersiapan.getEditText().getText().toString());
                    stringMap.put("mc_pelaksanaan", etMcPelaksanaan.getEditText().getText().toString());
                    stringMap.put("pho_penyerahan_hasil", etPhoPenyerahanHasil.getEditText().getText().toString());
                    stringMap.put("nama_pegawai_pemohon", etNamaPegawaiPemohon.getEditText().getText().toString());
                    stringMap.put("nomor_hp", etNomorHp.getEditText().getText().toString());
                    stringMap.put("email", etEmail.getEditText().getText().toString());
                    stringMap.put("foto_dokumen", encodedImage);
                    stringMap.put("awal_pekerjaan", etAwalPekerjaan.getEditText().getText().toString());
                    stringMap.put("akhir_pekerjaan", etAkhirPekerjaan.getEditText().getText().toString());
                    return stringMap;
                }
            };
            requestQueue.add(stringRequest);
        }
    }

}
