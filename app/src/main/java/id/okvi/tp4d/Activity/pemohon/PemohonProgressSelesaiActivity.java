package id.okvi.tp4d.Activity.pemohon;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

import id.okvi.tp4d.Adapter.PemohonAdapter;
import id.okvi.tp4d.Helper.API;
import id.okvi.tp4d.Helper.Bantuan;
import id.okvi.tp4d.Helper.SharedPreferenceManager;
import id.okvi.tp4d.Model.DaftarPemohonModel;
import id.okvi.tp4d.R;

public class PemohonProgressSelesaiActivity extends AppCompatActivity {
    private RecyclerView rvKonten;
    private TextView tvKeterangan;
    private List<DaftarPemohonModel> list = new ArrayList<>();
    private List<DaftarPemohonModel> list_sementara = new ArrayList<>();
    private Context context = PemohonProgressSelesaiActivity.this;
    private String URL_API = null;
    private SearchView searchView;
    private PemohonAdapter pemohonAdapter;
    private SwipeRefreshLayout swRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemohon_progress_selesai);
        init();
    }

    private void init() {
        rvKonten = findViewById(R.id.rvKonten);
        tvKeterangan = findViewById(R.id.tvKeterangan);
        searchView = findViewById(R.id.searchView);
        swRefresh = findViewById(R.id.swRefresh);

        if (getIntent().getStringExtra("mode")
                .equalsIgnoreCase("progress")) {
            URL_API = API.GET_PEMOHON_PROGRESS + SharedPreferenceManager.getInstance(context).getUser().getId_pemohon();
        } else if (getIntent().getStringExtra("mode")
                .equalsIgnoreCase("selesai")) {
            URL_API = API.GET_PEMOHON_SELESAI + SharedPreferenceManager.getInstance(context).getUser().getId_pemohon();
        } else {
            finish();
        }

        swRefresh.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        swRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if (list_sementara.size() > 0) {
                    if (searchView.getQuery().toString().length() > 0) {
                        pemohonAdapter.cariData(query);
                    } else {
                        pemohonAdapter.cariData("");
                    }
                }
                return true;
            }
        });

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        getData();
    }

    private void getData() {
//        final ProgressDialog progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage("Loading ...");
//        progressDialog.setIndeterminate(true);
//        progressDialog.show();
        swRefresh.setRefreshing(true);

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_API,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        progressDialog.dismiss();
                        swRefresh.setRefreshing(false);
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
                                    daftarPemohonModel.setHasil_telaah(jsonObject.getString("hasil_telaah"));
                                    daftarPemohonModel.setCatatan(jsonObject.getString("catatan"));
                                    daftarPemohonModel.setShare_lokasi(jsonObject.getString("share_lokasi"));
                                    daftarPemohonModel.setLatitude(jsonObject.getString("latitude"));
                                    daftarPemohonModel.setLongitude(jsonObject.getString("longitude"));

                                    list.add(daftarPemohonModel);
                                    list_sementara.add(daftarPemohonModel);
                                }
                                pemohonAdapter = new PemohonAdapter(context, list, getIntent().getStringExtra("mode"));
                                rvKonten.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
                                rvKonten.setAdapter(pemohonAdapter);
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
//                progressDialog.dismiss();
                swRefresh.setRefreshing(false);
                new Bantuan(context).toastLong(error.getMessage());
            }
        });
        requestQueue.add(stringRequest);
    }
}
