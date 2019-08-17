package id.okvi.tp4d.Activity.petugas;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;

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
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.okvi.tp4d.Adapter.FotoAdapter;
import id.okvi.tp4d.Helper.API;
import id.okvi.tp4d.Helper.Bantuan;
import id.okvi.tp4d.Model.DaftarPemohonModel;
import id.okvi.tp4d.R;

public class PetugasActionProsesLanjutanActivity extends AppCompatActivity {

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
    private Button btnBack;
    private Button btnSerahTerima;
    private Button btnNext;
    private List<String> list = new ArrayList<>();

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
        btnBack = findViewById(R.id.btnBack);
        btnSerahTerima = findViewById(R.id.btnSerahTerima);
        btnNext = findViewById(R.id.btnNext);

        uitzet_perencanaan.getEditText().setText(daftarPemohonModel.getUitzet_perencanaan());
        pcm_persiapan.getEditText().setText(daftarPemohonModel.getPcm_persiapan());
        mc_pelaksanaan.getEditText().setText(daftarPemohonModel.getMc_pelaksanaan());
        pho_penyerahan_hasil.getEditText().setText(daftarPemohonModel.getPho_penyerahan_hasil());

        btnTambahFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });

        get_foto_dokumentasi();
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
                prosesUploadFoto(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                new Bantuan(context).toastLong(e.getMessage());
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
                            } else {
                                new Bantuan(context).alertDialogPeringatan(object.getString("result"));
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
