package id.okvi.tp4d.Activity.petugas;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

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

import java.util.HashMap;
import java.util.Map;

import id.okvi.tp4d.Helper.API;
import id.okvi.tp4d.Helper.Bantuan;
import id.okvi.tp4d.Model.DaftarPemohonModel;
import id.okvi.tp4d.R;

public class PetugasActionPermohonanActivity extends AppCompatActivity {
    private Context context = PetugasActionPermohonanActivity.this;
    private TextInputLayout etInstansiPemohon;
    private TextInputLayout etAlamatInstansi;
    private TextInputLayout etNomorSurat;
    private TextInputLayout etTanggalSurat;
    private TextInputLayout etJenisKegiatan;
    private TextInputLayout etPaguAnggaran;
    private TextInputLayout etTahunAnggaran;
    private TextInputLayout etCaraPelaksanaan;
    private TextInputLayout etMetodePembayaran;
    private TextInputLayout etLokasiKegiatan;
    private TextInputLayout etKonsultanPerencanaan;
    private TextInputLayout etDisposisiKajari;
    private TextInputLayout etCatatanDariKajari;
    private TextInputLayout etHasilTelaah;
    private TextInputLayout etCatatan;
    private Button btnTolak;
    private Button btnTerima;
    private DaftarPemohonModel daftarPemohonModel;
    private String jenis, jenisFix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petugas_action_permohonan);
        init();

        if (getIntent().getStringExtra("mode").equalsIgnoreCase("tolak")) {
            setModeTolak();
        } else if (getIntent().getStringExtra("mode").equalsIgnoreCase("progress")) {
            setModeProgress();
        }
    }

    private void setModeProgress() {

    }

    private void setModeTolak() {
        etInstansiPemohon.getEditText().setEnabled(false);
        etAlamatInstansi.getEditText().setEnabled(false);
        etNomorSurat.getEditText().setEnabled(false);
        etTanggalSurat.getEditText().setEnabled(false);
        etJenisKegiatan.getEditText().setEnabled(false);
        etPaguAnggaran.getEditText().setEnabled(false);
        etTahunAnggaran.getEditText().setEnabled(false);
        etCaraPelaksanaan.getEditText().setEnabled(false);
        etMetodePembayaran.getEditText().setEnabled(false);
        etLokasiKegiatan.getEditText().setEnabled(false);
        etKonsultanPerencanaan.getEditText().setEnabled(false);
        etDisposisiKajari.getEditText().setEnabled(false);
        etHasilTelaah.getEditText().setEnabled(false);
        etCatatan.getEditText().setEnabled(false);
        btnTolak.setVisibility(View.GONE);
        btnTerima.setVisibility(View.GONE);
    }

    private void init() {
        daftarPemohonModel = (DaftarPemohonModel) getIntent().getExtras().getSerializable("data");

        jenis = daftarPemohonModel.getDisposisi();
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

        etInstansiPemohon = findViewById(R.id.etInstansiPemohon);
        etAlamatInstansi = findViewById(R.id.etAlamatInstansi);
        etNomorSurat = findViewById(R.id.etNomorSurat);
        etTanggalSurat = findViewById(R.id.etTanggalSurat);
        etJenisKegiatan = findViewById(R.id.etJenisKegiatan);
        etPaguAnggaran = findViewById(R.id.etPaguAnggaran);
        etTahunAnggaran = findViewById(R.id.etTahunAnggaran);
        etCaraPelaksanaan = findViewById(R.id.etCaraPelaksanaan);
        etMetodePembayaran = findViewById(R.id.etMetodePembayaran);
        etLokasiKegiatan = findViewById(R.id.etLokasiKegiatan);
        etKonsultanPerencanaan = findViewById(R.id.etKonsultanPerencanaan);
        etDisposisiKajari = findViewById(R.id.etDisposisiKajari);
        etCatatanDariKajari = findViewById(R.id.etCatatanDariKajari);
        etHasilTelaah = findViewById(R.id.etHasilTelaah);
        etCatatan = findViewById(R.id.etCatatan);
        btnTolak = findViewById(R.id.btnTolak);
        btnTerima = findViewById(R.id.btnTerima);

        etInstansiPemohon.getEditText().setText(daftarPemohonModel.getInstansi_pemohon());
        etAlamatInstansi.getEditText().setText(daftarPemohonModel.getAlamat_instansi());
        etNomorSurat.getEditText().setText(daftarPemohonModel.getNomer_surat());
        etTanggalSurat.getEditText().setText(daftarPemohonModel.getTanggal_surat());
        etJenisKegiatan.getEditText().setText(daftarPemohonModel.getJenis_kegiatan());
        etPaguAnggaran.getEditText().setText(daftarPemohonModel.getPagu_anggaran());
        etTahunAnggaran.getEditText().setText(daftarPemohonModel.getTahun_anggaran());
        etCaraPelaksanaan.getEditText().setText(daftarPemohonModel.getPelaksanaan_dengan_cara());
        etMetodePembayaran.getEditText().setText(daftarPemohonModel.getMetode_pembayaran());
        etLokasiKegiatan.getEditText().setText(daftarPemohonModel.getLokasi_kegiatan());
        etKonsultanPerencanaan.getEditText().setText(daftarPemohonModel.getKonsultan_perencanaan());
        etDisposisiKajari.getEditText().setText(jenisFix);
        etCatatanDariKajari.getEditText().setText(daftarPemohonModel.getCatatan_disposisi());

        btnTolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prosesTolak();
            }
        });

        btnTerima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(context, PetugasActionProsesLanjutanActivity.class));
                prosesTerima();
            }
        });
    }

    private void prosesTerima() {
        if (TextUtils.isEmpty(etInstansiPemohon.getEditText().getText().toString())) {
            etInstansiPemohon.getEditText().setError("Harus diisi");
            etInstansiPemohon.requestFocus();
        } else if (TextUtils.isEmpty(etAlamatInstansi.getEditText().getText().toString())) {
            etAlamatInstansi.getEditText().setError("Harus diisi");
            etAlamatInstansi.requestFocus();
        } else if (TextUtils.isEmpty(etNomorSurat.getEditText().getText().toString())) {
            etNomorSurat.getEditText().setError("Harus diisi");
            etNomorSurat.requestFocus();
        } else if (TextUtils.isEmpty(etTanggalSurat.getEditText().getText().toString())) {
            etTanggalSurat.getEditText().setError("Harus diisi");
            etTanggalSurat.requestFocus();
        } else if (TextUtils.isEmpty(etJenisKegiatan.getEditText().getText().toString())) {
            etJenisKegiatan.getEditText().setError("Harus diisi");
            etJenisKegiatan.requestFocus();
        } else if (TextUtils.isEmpty(etPaguAnggaran.getEditText().getText().toString())) {
            etPaguAnggaran.getEditText().setError("Harus diisi");
            etPaguAnggaran.requestFocus();
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
        } else if (TextUtils.isEmpty(etDisposisiKajari.getEditText().getText().toString())) {
            etDisposisiKajari.getEditText().setError("Harus diisi");
            etDisposisiKajari.requestFocus();
        } else if (TextUtils.isEmpty(etHasilTelaah.getEditText().getText().toString())) {
            etHasilTelaah.getEditText().setError("Harus diisi");
            etHasilTelaah.requestFocus();
        } else {
            final ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Loading ...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();

            RequestQueue requestQueue;
            requestQueue = Volley.newRequestQueue(context);
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    API.PETUGAS_TERIMA_PERMOHONAN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONObject object = new JSONObject(response);
                                new Bantuan(context).toastLong(object.getString("result"));
                                if (object.getInt("status") == 1) {
                                    Intent intent = new Intent(context, PetugasBaruProgressSelesaiTolakActivity.class);
                                    intent.putExtra("mode", "baru");
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                            } catch (JSONException e) {
                                new Bantuan(context).toastLong(e.getMessage());
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    new Bantuan(context).toastLong(error.getMessage());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> stringMap = new HashMap<>();
                    stringMap.put("id_daftar_pemohon", daftarPemohonModel.getId_daftar_pemohon());
                    stringMap.put("instansi_pemohon", etInstansiPemohon.getEditText().getText().toString());
                    stringMap.put("alamat_instansi", etAlamatInstansi.getEditText().getText().toString());
                    stringMap.put("nomer_surat", etNomorSurat.getEditText().getText().toString());
                    stringMap.put("tanggal_surat", etTanggalSurat.getEditText().getText().toString());
                    stringMap.put("jenis_kegiatan", etJenisKegiatan.getEditText().getText().toString());
                    stringMap.put("pagu_anggaran", etPaguAnggaran.getEditText().getText().toString());
                    stringMap.put("tahun_anggaran", etTahunAnggaran.getEditText().getText().toString());
                    stringMap.put("pelaksanaan_dengan_cara", etCaraPelaksanaan.getEditText().getText().toString());
                    stringMap.put("metode_pembayaran", etMetodePembayaran.getEditText().getText().toString());
                    stringMap.put("lokasi_kegiatan", etLokasiKegiatan.getEditText().getText().toString());
                    stringMap.put("konsultan_perencanaan", etKonsultanPerencanaan.getEditText().getText().toString());
                    stringMap.put("hasil_telaah", etHasilTelaah.getEditText().getText().toString());
                    if (!TextUtils.isEmpty(etCatatan.getEditText().getText().toString())) {
                        stringMap.put("catatan", etCatatan.getEditText().getText().toString());
                    }
                    return stringMap;
                }
            };
            requestQueue.add(stringRequest);
        }
    }

    private void prosesTolak() {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading ...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                API.PETUGAS_TOLAK_PERMOHONAN + daftarPemohonModel.getId_daftar_pemohon(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject object = new JSONObject(response);
                            new Bantuan(context).toastLong(object.getString("result"));
                            if (object.getInt("status") == 1) {
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                new Bantuan(context).toastLong(error.getMessage());
            }
        });
        requestQueue.add(stringRequest);
    }

}