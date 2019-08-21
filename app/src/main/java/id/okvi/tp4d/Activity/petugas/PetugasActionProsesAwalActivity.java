package id.okvi.tp4d.Activity.petugas;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.okvi.tp4d.Helper.API;
import id.okvi.tp4d.Helper.Bantuan;
import id.okvi.tp4d.Helper.SharedPreferenceManager;
import id.okvi.tp4d.Model.DaftarPemohonModel;
import id.okvi.tp4d.R;

public class PetugasActionProsesAwalActivity extends AppCompatActivity {

    private Context context = PetugasActionProsesAwalActivity.this;

    private TextInputLayout etInstansiPemohon;
    private TextInputLayout etAlamatInstansi;
    private TextInputLayout etNomorSurat;
    private TextInputLayout etTanggalSurat;
    private TextInputLayout etTanggalPengajuan;
    private TextInputLayout etJenisKegiatan;
    private TextInputLayout etPaguAnggaran;
    private TextInputLayout etTahunAnggaran;
    private TextInputLayout etCaraPelaksanaan;
    private TextInputLayout etMetodePembayaran;
    private TextInputLayout etLokasiKegiatan;
    private TextInputLayout etKonsultanPerencanaan;
    private TextInputLayout etNomorSprintTelaah;
    private TextInputLayout etTanggalSprintTelaah;
    private TextInputLayout etHasilTelaah;
    private Button btnBack;
    private Button btnNext;
    private DaftarPemohonModel daftarPemohonModel;
    private List<DaftarPemohonModel> list = new ArrayList<>();
    private int tanggal, bulan, tahun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petugas_action_proses_awal);
        init();
    }

    private void init() {
        daftarPemohonModel = (DaftarPemohonModel) getIntent().getExtras().getSerializable("data");

//        new Bantuan(context).alertDialogDebugging(daftarPemohonModel.getId_daftar_pemohon());
        etInstansiPemohon = findViewById(R.id.etInstansiPemohon);
        etAlamatInstansi = findViewById(R.id.etAlamatInstansi);
        etNomorSurat = findViewById(R.id.etNomorSurat);
        etTanggalSurat = findViewById(R.id.etTanggalSurat);
        etTanggalPengajuan = findViewById(R.id.etTanggalPengajuan);
        etJenisKegiatan = findViewById(R.id.etJenisKegiatan);
        etPaguAnggaran = findViewById(R.id.etPaguAnggaran);
        etTahunAnggaran = findViewById(R.id.etTahunAnggaran);
        etCaraPelaksanaan = findViewById(R.id.etCaraPelaksanaan);
        etMetodePembayaran = findViewById(R.id.etMetodePembayaran);
        etLokasiKegiatan = findViewById(R.id.etLokasiKegiatan);
        etKonsultanPerencanaan = findViewById(R.id.etKonsultanPerencanaan);
        etNomorSprintTelaah = findViewById(R.id.etNomorSprintTelaah);
        etTanggalSprintTelaah = findViewById(R.id.etTanggalSprintTelaah);
        etHasilTelaah = findViewById(R.id.etHasilTelaah);
        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });

        etTanggalPengajuan.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTanggal("etTanggalPengajuan");
            }
        });

        etTanggalSprintTelaah.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTanggal("etTanggalSprintTelaah");
            }
        });

        etTanggalSurat.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTanggal("etTanggalSurat");
            }
        });

        getAndSetData();
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
                        if (jenis.equalsIgnoreCase("etTanggalPengajuan")) {
                            etTanggalPengajuan.getEditText().setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } else if (jenis.equalsIgnoreCase("etTanggalSprintTelaah")) {
                            etTanggalSprintTelaah.getEditText().setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } else {
                            etTanggalSurat.getEditText().setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        }
                    }
                }, tahun, bulan, tanggal);
        datePickerDialog.show();
    }

    private void update() {

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
        } else if (TextUtils.isEmpty(etTanggalPengajuan.getEditText().getText().toString())) {
            etTanggalPengajuan.getEditText().setError("Harus diisi");
            etTanggalPengajuan.requestFocus();
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
        } else if (TextUtils.isEmpty(etNomorSprintTelaah.getEditText().getText().toString())) {
            etNomorSprintTelaah.getEditText().setError("Harus diisi");
            etNomorSprintTelaah.requestFocus();
        } else if (TextUtils.isEmpty(etTanggalSprintTelaah.getEditText().getText().toString())) {
            etTanggalSprintTelaah.getEditText().setError("Harus diisi");
            etTanggalSprintTelaah.requestFocus();
        } else if (TextUtils.isEmpty(etHasilTelaah.getEditText().getText().toString())) {
            etHasilTelaah.getEditText().setError("Harus diisi");
            etHasilTelaah.requestFocus();
        } else {
            prosesUpdate();
        }
    }

    private void prosesUpdate() {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading ...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                API.UPDATE_PROSES_TAHAP_PERTAMA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject object = new JSONObject(response);
                            if (object.getInt("status") == 1) {
                                new Bantuan(context).toastLong(object.getString("result"));
                            }
                            Intent intent = new Intent(context, PetugasActionProsesLanjutanActivity.class);
                            intent.putExtra("data", list.get(0));
                            startActivity(intent);
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
                stringMap.put("id_daftar_pemohon", list.get(0).getId_daftar_pemohon());
                stringMap.put("instansi_pemohon", etInstansiPemohon.getEditText().getText().toString());
                stringMap.put("alamat_instansi", etAlamatInstansi.getEditText().getText().toString());
                stringMap.put("nomer_surat", etNomorSurat.getEditText().getText().toString());
                stringMap.put("tanggal_surat", etTanggalSurat.getEditText().getText().toString());
                stringMap.put("tanggal_masuk", etTanggalPengajuan.getEditText().getText().toString());
                stringMap.put("jenis_kegiatan", etJenisKegiatan.getEditText().getText().toString());
                stringMap.put("pagu_anggaran", etPaguAnggaran.getEditText().getText().toString());
                stringMap.put("tahun_anggaran", etTahunAnggaran.getEditText().getText().toString());
                stringMap.put("pelaksanaan_dengan_cara", etCaraPelaksanaan.getEditText().getText().toString());
                stringMap.put("metode_pembayaran", etMetodePembayaran.getEditText().getText().toString());
                stringMap.put("lokasi_kegiatan", etLokasiKegiatan.getEditText().getText().toString());
                stringMap.put("konsultan_perencanaan", etKonsultanPerencanaan.getEditText().getText().toString());
                stringMap.put("nomor_sprint", etNomorSprintTelaah.getEditText().getText().toString());
                stringMap.put("tanggal_sprint", etTanggalSprintTelaah.getEditText().getText().toString());
                stringMap.put("hasil_telaah", etHasilTelaah.getEditText().getText().toString());
                return stringMap;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void getAndSetData() {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading ...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                API.GET_PETUGAS_PROGRESS +
                        SharedPreferenceManager.getInstance(context).getUser().getJenis() +
                        "&id_daftar_pemohon=" + daftarPemohonModel.getId_daftar_pemohon(),
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
                                    daftarPemohonModel.setNomor_sprint(jsonObject.getString("nomor_sprint"));
                                    daftarPemohonModel.setTanggal_sprint(jsonObject.getString("tanggal_sprint"));
                                    daftarPemohonModel.setNomor_sprint_pendampingan(jsonObject.getString("nomor_sprint_pendampingan"));
                                    daftarPemohonModel.setTanggal_sprint_pendampingan(jsonObject.getString("tanggal_sprint_pendampingan"));
                                    daftarPemohonModel.setAnggota1(jsonObject.getString("anggota1"));
                                    daftarPemohonModel.setAnggota2(jsonObject.getString("anggota2"));
                                    daftarPemohonModel.setAnggota3(jsonObject.getString("anggota3"));
                                    daftarPemohonModel.setAnggota4(jsonObject.getString("anggota4"));
                                    daftarPemohonModel.setAnggota5(jsonObject.getString("anggota5"));
                                    daftarPemohonModel.setAnggota6(jsonObject.getString("anggota6"));
                                    daftarPemohonModel.setNik1(jsonObject.getString("nik1"));
                                    daftarPemohonModel.setNik2(jsonObject.getString("nik2"));
                                    daftarPemohonModel.setNik3(jsonObject.getString("nik3"));
                                    daftarPemohonModel.setNik4(jsonObject.getString("nik4"));
                                    daftarPemohonModel.setNik5(jsonObject.getString("nik5"));
                                    daftarPemohonModel.setNik6(jsonObject.getString("nik6"));
                                    daftarPemohonModel.setP_uitzet_perencanaan(jsonObject.getString("p_uitzet_perencanaan"));
                                    daftarPemohonModel.setP_pcm_persiapan(jsonObject.getString("p_pcm_persiapan"));
                                    daftarPemohonModel.setP_mc_pelaksanaan(jsonObject.getString("p_mc_pelaksanaan"));
                                    daftarPemohonModel.setP_pho_penyerahan_hasil(jsonObject.getString("p_pho_penyerahan_hasil"));
                                    daftarPemohonModel.setShare_lokasi(jsonObject.getString("share_lokasi"));

                                    list.add(daftarPemohonModel);
                                }

                                etInstansiPemohon.getEditText().setText(list.get(0).getInstansi_pemohon());
                                etAlamatInstansi.getEditText().setText(list.get(0).getAlamat_instansi());
                                etNomorSurat.getEditText().setText(list.get(0).getNomer_surat());
                                etTanggalSurat.getEditText().setText(list.get(0).getTanggal_surat());
                                etTanggalPengajuan.getEditText().setText(list.get(0).getTanggal_masuk());
                                etJenisKegiatan.getEditText().setText(list.get(0).getJenis_kegiatan());
                                etPaguAnggaran.getEditText().setText(list.get(0).getPagu_anggaran());
                                etTahunAnggaran.getEditText().setText(list.get(0).getTahun_anggaran());
                                etCaraPelaksanaan.getEditText().setText(list.get(0).getPelaksanaan_dengan_cara());
                                etMetodePembayaran.getEditText().setText(list.get(0).getMetode_pembayaran());
                                etLokasiKegiatan.getEditText().setText(list.get(0).getLokasi_kegiatan());
                                etKonsultanPerencanaan.getEditText().setText(list.get(0).getKonsultan_perencanaan());
                                etNomorSprintTelaah.getEditText().setText(list.get(0).getNomor_sprint());
                                etTanggalSprintTelaah.getEditText().setText(list.get(0).getTanggal_sprint());
                                etHasilTelaah.getEditText().setText(list.get(0).getHasil_telaah());

                            } else {
                                new Bantuan(context).toastLong(object.getString("result"));
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
