package id.okvi.tp4d.Activity.petugas;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.okvi.tp4d.Adapter.FotoAdapter;
import id.okvi.tp4d.Helper.API;
import id.okvi.tp4d.Helper.Bantuan;
import id.okvi.tp4d.Model.DaftarPemohonModel;
import id.okvi.tp4d.R;

public class PetugasActionProsesLanjutanActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener {

    private Context context = PetugasActionProsesLanjutanActivity.this;
    private DaftarPemohonModel daftarPemohonModel;

    private TextInputLayout etNomorSprint;
    private TextInputLayout etTanggalSprint;
    private TextInputLayout etAnggota1;
    private TextInputLayout etNik1;
    private TextInputLayout etAnggota2;
    private TextInputLayout etNik2;
    private TextInputLayout etAnggota3;
    private TextInputLayout etNik3;
    private TextInputLayout etAnggota4;
    private TextInputLayout etNik4;
    private TextInputLayout etAnggota5;
    private TextInputLayout etNik5;
    private TextInputLayout etAnggota6;
    private TextInputLayout etNik6;
    private TextInputLayout uitzet_perencanaan;
    private TextInputLayout p_uitzet_perencanaan;
    private TextInputLayout pcm_persiapan;
    private TextInputLayout p_pcm_persiapan;
    private TextInputLayout mc_pelaksanaan;
    private TextInputLayout p_mc_pelaksanaan;
    private TextInputLayout pho_penyerahan_hasil;
    private TextInputLayout p_pho_penyerahan_hasil;
    private TextInputLayout share_lokasi;
    private Button btnTambahFoto;
    private RecyclerView rvFoto;
    private TextInputLayout serah_terima;
    private Button btnBack;
    private Button btnSerahTerima;
    private Button btnNext;
    private List<String> list = new ArrayList<>();
    private int tanggal, bulan, tahun;
    private GoogleApiClient googleApiClient;
    private String latitude = null, longitude = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petugas_action_lanjutan);
        init();
    }

    private void init() {

        daftarPemohonModel = (DaftarPemohonModel) getIntent().getExtras().getSerializable("data");

        etNomorSprint = findViewById(R.id.etNomorSprint);
        etTanggalSprint = findViewById(R.id.etTanggalSprint);
        etAnggota1 = findViewById(R.id.etAnggota1);
        etNik1 = findViewById(R.id.etNik1);
        etAnggota2 = findViewById(R.id.etAnggota2);
        etNik2 = findViewById(R.id.etNik2);
        etAnggota3 = findViewById(R.id.etAnggota3);
        etNik3 = findViewById(R.id.etNik3);
        etAnggota4 = findViewById(R.id.etAnggota4);
        etNik4 = findViewById(R.id.etNik4);
        etAnggota5 = findViewById(R.id.etAnggota5);
        etNik5 = findViewById(R.id.etNik5);
        etAnggota6 = findViewById(R.id.etAnggota6);
        etNik6 = findViewById(R.id.etNik6);
        uitzet_perencanaan = findViewById(R.id.uitzet_perencanaan);
        p_uitzet_perencanaan = findViewById(R.id.p_uitzet_perencanaan);
        pcm_persiapan = findViewById(R.id.pcm_persiapan);
        p_pcm_persiapan = findViewById(R.id.p_pcm_persiapan);
        mc_pelaksanaan = findViewById(R.id.mc_pelaksanaan);
        p_mc_pelaksanaan = findViewById(R.id.p_mc_pelaksanaan);
        pho_penyerahan_hasil = findViewById(R.id.pho_penyerahan_hasil);
        p_pho_penyerahan_hasil = findViewById(R.id.p_pho_penyerahan_hasil);
        share_lokasi = findViewById(R.id.share_lokasi);
        btnTambahFoto = findViewById(R.id.btnTambahFoto);
        rvFoto = findViewById(R.id.rvFoto);
        serah_terima = findViewById(R.id.serah_terima);
        btnBack = findViewById(R.id.btnBack);
        btnSerahTerima = findViewById(R.id.btnSerahTerima);
        btnNext = findViewById(R.id.btnNext);

        googleApiClient = new GoogleApiClient
                .Builder(context)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        uitzet_perencanaan.getEditText().setText(daftarPemohonModel.getUitzet_perencanaan());
        pcm_persiapan.getEditText().setText(daftarPemohonModel.getPcm_persiapan());
        mc_pelaksanaan.getEditText().setText(daftarPemohonModel.getMc_pelaksanaan());
        pho_penyerahan_hasil.getEditText().setText(daftarPemohonModel.getPho_penyerahan_hasil());

        etNomorSprint.getEditText().setText(daftarPemohonModel.getNomor_sprint());
        etTanggalSprint.getEditText().setText(daftarPemohonModel.getTanggal_sprint());
        etAnggota1.getEditText().setText(daftarPemohonModel.getAnggota1());
        etAnggota2.getEditText().setText(daftarPemohonModel.getAnggota2());
        etAnggota3.getEditText().setText(daftarPemohonModel.getAnggota3());
        etAnggota4.getEditText().setText(daftarPemohonModel.getAnggota4());
        etAnggota5.getEditText().setText(daftarPemohonModel.getAnggota5());
        etAnggota6.getEditText().setText(daftarPemohonModel.getAnggota6());
        etNik1.getEditText().setText(daftarPemohonModel.getNik1());
        etNik2.getEditText().setText(daftarPemohonModel.getNik2());
        etNik3.getEditText().setText(daftarPemohonModel.getNik3());
        etNik4.getEditText().setText(daftarPemohonModel.getNik4());
        etNik5.getEditText().setText(daftarPemohonModel.getNik5());
        etNik6.getEditText().setText(daftarPemohonModel.getNik6());

        p_uitzet_perencanaan.getEditText().setText(daftarPemohonModel.getP_uitzet_perencanaan());
        p_pcm_persiapan.getEditText().setText(daftarPemohonModel.getP_pcm_persiapan());
        p_mc_pelaksanaan.getEditText().setText(daftarPemohonModel.getP_mc_pelaksanaan());
        p_pho_penyerahan_hasil.getEditText().setText(daftarPemohonModel.getP_pho_penyerahan_hasil());

        share_lokasi.getEditText().setText(daftarPemohonModel.getShare_lokasi());
        share_lokasi.setFocusable(false);


        if (daftarPemohonModel.getJenis_kegiatan().equalsIgnoreCase("Pengadaan Tanah")) {
            uitzet_perencanaan.setHint("Perencanaan");
            pcm_persiapan.setHint("Persiapan");
            mc_pelaksanaan.setHint("Pelaksanaan");
            pho_penyerahan_hasil.setHint("Penyerahan Hasil");
        }

        btnTambahFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSerahTerima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CekInputan(true);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CekInputan(false);
            }
        });

        share_lokasi.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTempat();
            }
        });

        etTanggalSprint.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTanggal("etTanggalSprint");
            }
        });

        p_uitzet_perencanaan.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTanggal("p_uitzet_perencanaan");
            }
        });

        p_pcm_persiapan.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTanggal("p_pcm_persiapan");
            }
        });

        p_mc_pelaksanaan.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTanggal("p_mc_pelaksanaan");
            }
        });

        p_pho_penyerahan_hasil.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTanggal("p_pho_penyerahan_hasil");
            }
        });

        serah_terima.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTanggal("serah_terima");
            }
        });


        get_foto_dokumentasi();
    }

    private void getTempat() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(PetugasActionProsesLanjutanActivity.this), 123);
        } catch (GooglePlayServicesRepairableException e) {
            new Bantuan(context).alertDialogPeringatan(e.getMessage());
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            new Bantuan(context).alertDialogPeringatan(e.getMessage());
            e.printStackTrace();
        }
    }

    private void getTanggal(final String jenis) {
        final Calendar c = Calendar.getInstance();
        tanggal = c.get(Calendar.DAY_OF_MONTH);
        bulan = c.get(Calendar.MONTH);
        tahun = c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        if (jenis.equalsIgnoreCase("etTanggalSprint")) {
                            etTanggalSprint.getEditText().setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } else if (jenis.equalsIgnoreCase("p_uitzet_perencanaan")) {
                            p_uitzet_perencanaan.getEditText().setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } else if (jenis.equalsIgnoreCase("p_pcm_persiapan")) {
                            p_pcm_persiapan.getEditText().setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } else if (jenis.equalsIgnoreCase("p_mc_pelaksanaan")) {
                            p_mc_pelaksanaan.getEditText().setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } else if (jenis.equalsIgnoreCase("p_pho_penyerahan_hasil")) {
                            p_pho_penyerahan_hasil.getEditText().setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } else if (jenis.equalsIgnoreCase("serah_terima")) {
                            serah_terima.getEditText().setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        }
                    }
                }, tahun, bulan, tanggal);
        datePickerDialog.show();
    }

    private void CekInputan(boolean isModeSerahTerima) {
        if (TextUtils.isEmpty(etNomorSprint.getEditText().getText().toString())) {
            etNomorSprint.getEditText().setError("Harus diisi");
            etNomorSprint.requestFocus();
        } else if (TextUtils.isEmpty(etTanggalSprint.getEditText().getText().toString())) {
            etTanggalSprint.getEditText().setError("Harus diisi");
            etTanggalSprint.requestFocus();
        } else if (TextUtils.isEmpty(p_uitzet_perencanaan.getEditText().getText().toString())) {
            p_uitzet_perencanaan.getEditText().setError("Harus diisi");
            p_uitzet_perencanaan.requestFocus();
        } else if (TextUtils.isEmpty(p_pcm_persiapan.getEditText().getText().toString())) {
            p_pcm_persiapan.getEditText().setError("Harus diisi");
            p_pcm_persiapan.requestFocus();
        } else if (TextUtils.isEmpty(p_mc_pelaksanaan.getEditText().getText().toString())) {
            p_mc_pelaksanaan.getEditText().setError("Harus diisi");
            p_mc_pelaksanaan.requestFocus();
        } else if (TextUtils.isEmpty(p_pho_penyerahan_hasil.getEditText().getText().toString())) {
            p_pho_penyerahan_hasil.getEditText().setError("Harus diisi");
            p_pho_penyerahan_hasil.requestFocus();
        } else {
            if (isModeSerahTerima) {
                if (TextUtils.isEmpty(serah_terima.getEditText().getText().toString())) {
                    serah_terima.getEditText().setError("Harus diisi");
                    serah_terima.requestFocus();
                } else {
                    prosesUpdate(true);
                }
            } else {
                prosesUpdate(false);
            }
        }
    }

    private void prosesUpdate(final boolean isModeSerahTerimaLagi) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading ...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                API.UPDATE_PROSES_LANJUTAN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject object = new JSONObject(response);
                            if (object.getInt("status") == 1) {
                                new Bantuan(context).toastLong(object.getString("result"));
                                Intent intent = new Intent(context, PetugasHomeActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                new Bantuan(context).alertDialogPeringatan(object.getString("result"));
                            }
//                            Intent intent = new Intent(context, PetugasHomeActivity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                            startActivity(intent);
                        } catch (JSONException e) {
                            new Bantuan(context).toastLong(e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                new Bantuan(context).alertDialogPeringatan(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> stringMap = new HashMap<>();
                stringMap.put("id_daftar_pemohon", daftarPemohonModel.getId_daftar_pemohon());
                stringMap.put("nomor_sprint_pendampingan", etNomorSprint.getEditText().getText().toString());
                stringMap.put("tanggal_sprint_pendampingan", etTanggalSprint.getEditText().getText().toString());

                stringMap.put("anggota1", !TextUtils.isEmpty(etAnggota1.getEditText().getText().toString()) ? etAnggota1.getEditText().getText().toString() : "");
                stringMap.put("nik1", !TextUtils.isEmpty(etNik1.getEditText().getText().toString()) ? etNik1.getEditText().getText().toString() : "");

                stringMap.put("anggota2", !TextUtils.isEmpty(etAnggota2.getEditText().getText().toString()) ? etAnggota2.getEditText().getText().toString() : "");
                stringMap.put("nik2", !TextUtils.isEmpty(etNik2.getEditText().getText().toString()) ? etNik2.getEditText().getText().toString() : "");

                stringMap.put("anggota3", !TextUtils.isEmpty(etAnggota3.getEditText().getText().toString()) ? etAnggota3.getEditText().getText().toString() : "");
                stringMap.put("nik3", !TextUtils.isEmpty(etNik3.getEditText().getText().toString()) ? etNik3.getEditText().getText().toString() : "");

                stringMap.put("anggota4", !TextUtils.isEmpty(etAnggota4.getEditText().getText().toString()) ? etAnggota4.getEditText().getText().toString() : "");
                stringMap.put("nik4", !TextUtils.isEmpty(etNik4.getEditText().getText().toString()) ? etNik4.getEditText().getText().toString() : "");

                stringMap.put("anggota5", !TextUtils.isEmpty(etAnggota5.getEditText().getText().toString()) ? etAnggota5.getEditText().getText().toString() : "");
                stringMap.put("nik5", !TextUtils.isEmpty(etNik5.getEditText().getText().toString()) ? etNik5.getEditText().getText().toString() : "");

                stringMap.put("anggota6", !TextUtils.isEmpty(etAnggota6.getEditText().getText().toString()) ? etAnggota6.getEditText().getText().toString() : "");
                stringMap.put("nik6", !TextUtils.isEmpty(etNik6.getEditText().getText().toString()) ? etNik6.getEditText().getText().toString() : "");


                stringMap.put("p_uitzet_perencanaan", !TextUtils.isEmpty(p_uitzet_perencanaan.getEditText().getText().toString()) ? p_uitzet_perencanaan.getEditText().getText().toString() : "");
                stringMap.put("p_pcm_persiapan", !TextUtils.isEmpty(p_pcm_persiapan.getEditText().getText().toString()) ? p_pcm_persiapan.getEditText().getText().toString() : "");
                stringMap.put("p_mc_pelaksanaan", !TextUtils.isEmpty(p_mc_pelaksanaan.getEditText().getText().toString()) ? p_mc_pelaksanaan.getEditText().getText().toString() : "");
                stringMap.put("p_pho_penyerahan_hasil", !TextUtils.isEmpty(p_pho_penyerahan_hasil.getEditText().getText().toString()) ? p_pho_penyerahan_hasil.getEditText().getText().toString() : "");
                stringMap.put("share_lokasi", !TextUtils.isEmpty(share_lokasi.getEditText().getText().toString()) ? share_lokasi.getEditText().getText().toString() : "");


                stringMap.put("latitude", (latitude != null && !latitude.isEmpty()) ? latitude : "");
                stringMap.put("longitude", (longitude != null && !longitude.isEmpty()) ? longitude : "");

                if (isModeSerahTerimaLagi) {
                    stringMap.put("serah_terima", serah_terima.getEditText().getText().toString());
                    stringMap.put("is_serah_terima", "1");
                } else {
                    stringMap.put("serah_terima", "0000-00-00");
                    stringMap.put("is_serah_terima", "0");
                }

                return stringMap;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                share_lokasi.getEditText().setText(place.getAddress());
                latitude = String.valueOf(place.getLatLng().latitude);
                longitude = String.valueOf(place.getLatLng().longitude);
            }
        } else if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                try {
                    assert data != null;
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    prosesUploadFoto(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    new Bantuan(context).toastLong(e.getMessage());
                }
            }
        }
    }

    private void prosesUploadFoto(Bitmap selectedImage) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading ...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        selectedImage.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        final String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                API.UPLOAD_DOKUMEN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        get_foto_dokumentasi();
                        try {
                            JSONObject object = new JSONObject(response);
                            new Bantuan(context).toastLong(object.getString("result"));
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
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> stringMap = new HashMap<>();
                stringMap.put("id_daftar_pemohon", daftarPemohonModel.getId_daftar_pemohon());
                stringMap.put("foto", encodedImage);
                return stringMap;
            }
        };
        requestQueue.add(stringRequest);
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        new Bantuan(context).toastLong(connectionResult.getErrorMessage());
    }
}
