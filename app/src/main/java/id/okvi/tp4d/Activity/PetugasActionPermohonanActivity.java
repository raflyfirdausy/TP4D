package id.okvi.tp4d.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
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
    private TextInputLayout etHasilTelaah;
    private TextInputLayout etCatatan;
    private Button btnTolak;
    private Button btnTerima;
    private DaftarPemohonModel daftarPemohonModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petugas_action_permohonan);
        init();
    }

    private void init() {
        daftarPemohonModel = (DaftarPemohonModel) getIntent().getExtras().getSerializable("data");
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
        etDisposisiKajari.getEditText().setText(daftarPemohonModel.getDisposisi());

        btnTolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prosesTolak();
            }
        });
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