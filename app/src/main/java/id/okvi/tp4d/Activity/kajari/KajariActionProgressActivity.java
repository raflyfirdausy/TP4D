package id.okvi.tp4d.Activity.kajari;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.okvi.tp4d.Activity.pemohon.BarcodeActivity;
import id.okvi.tp4d.Adapter.FotoAdapter;
import id.okvi.tp4d.Helper.API;
import id.okvi.tp4d.Helper.Bantuan;
import id.okvi.tp4d.Model.DaftarPemohonModel;
import id.okvi.tp4d.R;

public class KajariActionProgressActivity extends AppCompatActivity {

    private Context context = KajariActionProgressActivity.this;
    private DaftarPemohonModel daftarPemohonModel;
    private TextInputLayout etInstansiPemohon;
    private TextInputLayout etAlamatInstansi;
    private TextInputLayout etNomorSurat;
    private TextInputLayout etTanggalSurat;
    private TextInputLayout etJenisKegiatan;
    private TextInputLayout etInstansi;
    private TextInputLayout etPaguAnggaran;
    private TextInputLayout etTahunAnggaran;
    private TextInputLayout etCaraPelaksanaan;
    private TextInputLayout etMetodePembayaran;
    private TextInputLayout etLokasiKegiatan;
    private TextInputLayout etKonsultanPerencanaan;
    private TextInputLayout etAwalPekerjaan;
    private TextInputLayout etAkhirPekerjaan;
    private TextInputLayout uitzet_perencanaan;
    private TextInputLayout p_uitzet_perencanaan;
    private TextInputLayout pcm_persiapan;
    private TextInputLayout p_pcm_persiapan;
    private TextInputLayout mc_pelaksanaan;
    private TextInputLayout p_mc_pelaksanaan;
    private TextInputLayout pho_penyerahan_hasil;
    private TextInputLayout p_pho_penyerahan_hasil;
    private TextInputLayout share_lokasi;
    private Button btnOpenGoogleMaps;
    private Button btnLihatBarcode;
    private RecyclerView rvFoto;
    private ImageView ivSurat;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_proses_kajari);
        init();
    }

    private void init() {
        daftarPemohonModel = (DaftarPemohonModel) getIntent().getExtras().getSerializable("data");

        etInstansiPemohon = findViewById(R.id.etInstansiPemohon);
        etAlamatInstansi = findViewById(R.id.etAlamatInstansi);
        etNomorSurat = findViewById(R.id.etNomorSurat);
        etTanggalSurat = findViewById(R.id.etTanggalSurat);
        etJenisKegiatan = findViewById(R.id.etJenisKegiatan);
        etInstansi = findViewById(R.id.etInstansi);
        etPaguAnggaran = findViewById(R.id.etPaguAnggaran);
        etTahunAnggaran = findViewById(R.id.etTahunAnggaran);
        etCaraPelaksanaan = findViewById(R.id.etCaraPelaksanaan);
        etMetodePembayaran = findViewById(R.id.etMetodePembayaran);
        etLokasiKegiatan = findViewById(R.id.etLokasiKegiatan);
        etKonsultanPerencanaan = findViewById(R.id.etKonsultanPerencanaan);
        etAwalPekerjaan = findViewById(R.id.etAwalPekerjaan);
        etAkhirPekerjaan = findViewById(R.id.etAkhirPekerjaan);
        uitzet_perencanaan = findViewById(R.id.uitzet_perencanaan);
        p_uitzet_perencanaan = findViewById(R.id.p_uitzet_perencanaan);
        pcm_persiapan = findViewById(R.id.pcm_persiapan);
        p_pcm_persiapan = findViewById(R.id.p_pcm_persiapan);
        mc_pelaksanaan = findViewById(R.id.mc_pelaksanaan);
        p_mc_pelaksanaan = findViewById(R.id.p_mc_pelaksanaan);
        pho_penyerahan_hasil = findViewById(R.id.pho_penyerahan_hasil);
        p_pho_penyerahan_hasil = findViewById(R.id.p_pho_penyerahan_hasil);
        rvFoto = findViewById(R.id.rvFoto);
        ivSurat = findViewById(R.id.ivSurat);
        share_lokasi = findViewById(R.id.share_lokasi);
        btnOpenGoogleMaps = findViewById(R.id.btnOpenGoogleMaps);
        btnLihatBarcode = findViewById(R.id.btnLihatBarcode);

        etInstansiPemohon.getEditText().setFocusable(false);
        etAlamatInstansi.getEditText().setFocusable(false);
        etNomorSurat.getEditText().setFocusable(false);
        etTanggalSurat.getEditText().setFocusable(false);
        etJenisKegiatan.getEditText().setFocusable(false);
        etInstansi.getEditText().setFocusable(false);
        etPaguAnggaran.getEditText().setFocusable(false);
        etTahunAnggaran.getEditText().setFocusable(false);
        etCaraPelaksanaan.getEditText().setFocusable(false);
        etMetodePembayaran.getEditText().setFocusable(false);
        etLokasiKegiatan.getEditText().setFocusable(false);
        etKonsultanPerencanaan.getEditText().setFocusable(false);
        etAwalPekerjaan.getEditText().setFocusable(false);
        etAkhirPekerjaan.getEditText().setFocusable(false);
        uitzet_perencanaan.getEditText().setFocusable(false);
        p_uitzet_perencanaan.getEditText().setFocusable(false);
        pcm_persiapan.getEditText().setFocusable(false);
        p_pcm_persiapan.getEditText().setFocusable(false);
        mc_pelaksanaan.getEditText().setFocusable(false);
        p_mc_pelaksanaan.getEditText().setFocusable(false);
        pho_penyerahan_hasil.getEditText().setFocusable(false);
        p_pho_penyerahan_hasil.getEditText().setFocusable(false);
        share_lokasi.getEditText().setFocusable(false);

        if (daftarPemohonModel.getJenis_kegiatan().equalsIgnoreCase("Pengadaan Tanah")) {
            uitzet_perencanaan.setHint("Perencanaan");
            pcm_persiapan.setHint("Persiapan");
            mc_pelaksanaan.setHint("Pelaksanaan");
            pho_penyerahan_hasil.setHint("Penyerahan Hasil");
        }

        etInstansiPemohon.getEditText().setText(daftarPemohonModel.getInstansi_pemohon());
        etAlamatInstansi.getEditText().setText(daftarPemohonModel.getAlamat_instansi());
        etNomorSurat.getEditText().setText(daftarPemohonModel.getNomer_surat());
        etTanggalSurat.getEditText().setText(daftarPemohonModel.getTanggal_surat());
        etJenisKegiatan.getEditText().setText(daftarPemohonModel.getJenis_kegiatan());
        etInstansi.getEditText().setText(daftarPemohonModel.getInstansi());
        etPaguAnggaran.getEditText().setText(daftarPemohonModel.getPagu_anggaran());
        etTahunAnggaran.getEditText().setText(daftarPemohonModel.getTahun_anggaran());
        etCaraPelaksanaan.getEditText().setText(daftarPemohonModel.getPelaksanaan_dengan_cara());
        etMetodePembayaran.getEditText().setText(daftarPemohonModel.getMetode_pembayaran());
        etLokasiKegiatan.getEditText().setText(daftarPemohonModel.getLokasi_kegiatan());
        etKonsultanPerencanaan.getEditText().setText(daftarPemohonModel.getKonsultan_perencanaan());
        etAwalPekerjaan.getEditText().setText(daftarPemohonModel.getAwal_pekerjaan());
        etAkhirPekerjaan.getEditText().setText(daftarPemohonModel.getAkhir_pekerjaan());
        uitzet_perencanaan.getEditText().setText(daftarPemohonModel.getUitzet_perencanaan());
        p_uitzet_perencanaan.getEditText().setText(daftarPemohonModel.getP_uitzet_perencanaan());
        pcm_persiapan.getEditText().setText(daftarPemohonModel.getPcm_persiapan());
        p_pcm_persiapan.getEditText().setText(daftarPemohonModel.getP_pcm_persiapan());
        mc_pelaksanaan.getEditText().setText(daftarPemohonModel.getMc_pelaksanaan());
        p_mc_pelaksanaan.getEditText().setText(daftarPemohonModel.getP_mc_pelaksanaan());
        pho_penyerahan_hasil.getEditText().setText(daftarPemohonModel.getPho_penyerahan_hasil());
        p_pho_penyerahan_hasil.getEditText().setText(daftarPemohonModel.getP_pho_penyerahan_hasil());
        share_lokasi.getEditText().setText(daftarPemohonModel.getShare_lokasi());

        btnLihatBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BarcodeActivity.class);
                intent.putExtra("no_regis", daftarPemohonModel.getNo_regis());
                intent.putExtra("mode", "back");
                startActivity(intent);
            }
        });

        btnOpenGoogleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!daftarPemohonModel.getLatitude().equalsIgnoreCase("null") &&
                        !daftarPemohonModel.getLongitude().equalsIgnoreCase("null")) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?daddr=" +
                                    daftarPemohonModel.getLatitude() + "," + daftarPemohonModel.getLongitude())));
                } else {
                    new Bantuan(context).alertDialogPeringatan("Alamat Lokasi Belum di atur");
                }
            }
        });

        Picasso.get().load(daftarPemohonModel.getFoto_dokumen())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.img_error)
                .into(ivSurat);

        get_foto_dokumentasi();
    }

    private void get_foto_dokumentasi() {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading ...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                API.GET_FOTO_DOKUMENTASI + daftarPemohonModel.getId_daftar_pemohon(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        list.clear();
                        try {
                            JSONObject object = new JSONObject(response);
                            if (object.getInt("status") == 1) {
                                JSONArray jsonArray = object.getJSONArray("result");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    list.add(jsonObject.getString("foto"));
                                }
                                rvFoto.setLayoutManager(new GridLayoutManager(context, 4));
                                rvFoto.setAdapter(new FotoAdapter(context, list));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            new Bantuan(context).toastLong(e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        new Bantuan(context).alertDialogInformasi(error.getMessage());
                    }
                }
        );
        requestQueue.add(stringRequest);

    }
}
