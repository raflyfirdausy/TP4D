package id.okvi.tp4d.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.okvi.tp4d.Adapter.PetugasBaruAdapter;
import id.okvi.tp4d.Helper.API;
import id.okvi.tp4d.Helper.Bantuan;
import id.okvi.tp4d.Helper.SharedPreferenceManager;
import id.okvi.tp4d.Model.DaftarPemohonModel;
import id.okvi.tp4d.R;

public class PetugasBaruActivity extends AppCompatActivity {
    private RecyclerView rvKonten;
    private TextView tvKeterangan;
    private List<DaftarPemohonModel> list = new ArrayList<>();
    private Context context = PetugasBaruActivity.this;
    private String URL_API = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petugas_baru);
        init();
    }

    private void init() {
        rvKonten = findViewById(R.id.rvKonten);
        tvKeterangan = findViewById(R.id.tvKeterangan);

        if (getIntent().hasExtra("mode") &&
                getIntent().getStringExtra("mode")
                        .equalsIgnoreCase("tolak")) {
            URL_API = API.GET_PETUGAS_TOLAK + SharedPreferenceManager.getInstance(context).getUser().getJenis();
        } else {
            URL_API = API.GET_PETUGAS_BARU + SharedPreferenceManager.getInstance(context).getUser().getJenis();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        getDataPetugasBaru();
    }

    private void getDataPetugasBaru() {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading ...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_API,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        list.clear();
                        try {
                            JSONObject object = new JSONObject(response);
                            if (object.getInt("status") == 1) {
                                tvKeterangan.setVisibility(View.GONE);
                                rvKonten.setVisibility(View.VISIBLE);

                                JSONArray jsonArray = object.getJSONArray("result");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    DaftarPemohonModel daftarPemohonModel = new DaftarPemohonModel();
                                    daftarPemohonModel.setId_daftar_pemohon(jsonObject.getString("id_daftar_pemohon"));
                                    daftarPemohonModel.setId_pemohon(jsonObject.getString("id_pemohon"));
                                    daftarPemohonModel.setNo_regis(jsonObject.getString("no_regis"));
                                    daftarPemohonModel.setInstansi_pemohon(jsonObject.getString("instansi_pemohon"));
                                    daftarPemohonModel.setAlamat_instansi(jsonObject.getString("alamat_instansi"));
                                    daftarPemohonModel.setNomer_surat(jsonObject.getString("nomer_surat"));
                                    daftarPemohonModel.setTanggal_surat(jsonObject.getString("tanggal_surat"));
                                    daftarPemohonModel.setTanggal_masuk(jsonObject.getString("tanggal_masuk"));
                                    daftarPemohonModel.setJenis_kegiatan(jsonObject.getString("jenis_kegiatan"));
                                    daftarPemohonModel.setPagu_anggaran(jsonObject.getString("pagu_anggaran"));
                                    daftarPemohonModel.setInstansi(jsonObject.getString("instansi"));
                                    daftarPemohonModel.setTahun_anggaran(jsonObject.getString("tahun_anggaran"));
                                    daftarPemohonModel.setPelaksanaan_dengan_cara(jsonObject.getString("pelaksanaan_dengan_cara"));
                                    daftarPemohonModel.setMetode_pembayaran(jsonObject.getString("metode_pembayaran"));
                                    daftarPemohonModel.setLokasi_kegiatan(jsonObject.getString("lokasi_kegiatan"));
                                    daftarPemohonModel.setKonsultan_perencanaan(jsonObject.getString("konsultan_perencanaan"));
                                    daftarPemohonModel.setAwal_pekerjaan(jsonObject.getString("awal_pekerjaan"));
                                    daftarPemohonModel.setAkhir_pekerjaan(jsonObject.getString("akhir_pekerjaan"));
                                    daftarPemohonModel.setUitzet_perencanaan(jsonObject.getString("uitzet_perencanaan"));
                                    daftarPemohonModel.setPcm_persiapan(jsonObject.getString("pcm_persiapan"));
                                    daftarPemohonModel.setMc_pelaksanaan(jsonObject.getString("mc_pelaksanaan"));
                                    daftarPemohonModel.setPho_penyerahan_hasil(jsonObject.getString("pho_penyerahan_hasil"));
                                    daftarPemohonModel.setNama_pegawai_pemohon(jsonObject.getString("nama_pegawai_pemohon"));
                                    daftarPemohonModel.setNomor_hp(jsonObject.getString("nomor_hp"));
                                    daftarPemohonModel.setEmail(jsonObject.getString("email"));
                                    daftarPemohonModel.setFoto_dokumen(jsonObject.getString("foto_dokumen"));
                                    daftarPemohonModel.setStatus(jsonObject.getString("status"));
                                    daftarPemohonModel.setDisposisi(jsonObject.getString("disposisi"));
                                    daftarPemohonModel.setCatatan_disposisi(jsonObject.getString("catatan_disposisi"));

                                    list.add(daftarPemohonModel);
                                }
                                rvKonten.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
                                rvKonten.setAdapter(new PetugasBaruAdapter(context, list));
                            } else {
                                tvKeterangan.setText(object.getString("result"));
                                tvKeterangan.setVisibility(View.VISIBLE);
                                rvKonten.setVisibility(View.GONE);
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
