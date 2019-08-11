package id.okvi.tp4d.Activity.kajari;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

public class KajariActionDisposisiActivity extends AppCompatActivity {

    private DaftarPemohonModel daftarPemohonModel;
    private Context context = KajariActionDisposisiActivity.this;
    private TextInputLayout etNomerSurat;
    private RadioGroup rgJenisDisposisi;
    private RadioButton rbPokja1;
    private RadioButton rbPokja2;
    private RadioButton rbPokja3;
    private RadioButton rbLegal;
    private TextInputLayout etCatatanDisposisi;
    private Button btnSimpan;
    private String disposisi = "pokja1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kajari_action_disposisi);
        init();
    }

    private void init() {
        daftarPemohonModel = (DaftarPemohonModel) getIntent().getExtras().getSerializable("data");

        etNomerSurat = findViewById(R.id.etNomerSurat);
        rgJenisDisposisi = findViewById(R.id.rgJenisDisposisi);
        rbPokja1 = findViewById(R.id.rbPokja1);
        rbPokja2 = findViewById(R.id.rbPokja2);
        rbPokja3 = findViewById(R.id.rbPokja3);
        rbLegal = findViewById(R.id.rbLegal);
        etCatatanDisposisi = findViewById(R.id.etCatatanDisposisi);
        btnSimpan = findViewById(R.id.btnSimpan);

        rgJenisDisposisi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radioGroup.getCheckedRadioButtonId() == rbPokja1.getId()) {
                    disposisi = "pokja1";
                } else if (radioGroup.getCheckedRadioButtonId() == rbPokja2.getId()) {
                    disposisi = "pokja2";
                } else if (radioGroup.getCheckedRadioButtonId() == rbPokja3.getId()) {
                    disposisi = "pokja3";
                } else if (radioGroup.getCheckedRadioButtonId() == rbLegal.getId()) {
                    disposisi = "legal";
                } else {
                    disposisi = "tidak diketahui";
                }
            }
        });

        etNomerSurat.getEditText().setText(daftarPemohonModel.getNomer_surat());

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prosesSimpan();
            }
        });
    }

    private void prosesSimpan() {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading ...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                API.UPDATE_DISPOSISI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject object = new JSONObject(response);
                            if (object.getInt("status") == 1) {
                                finish();
                            }
                            new Bantuan(context).toastLong(object.getString("result"));
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
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> stringMap = new HashMap<>();
                stringMap.put("id_daftar_pemohon", daftarPemohonModel.getId_daftar_pemohon());
                stringMap.put("disposisi", disposisi);
                if (!TextUtils.isEmpty(etCatatanDisposisi.getEditText().getText())) {
                    stringMap.put("catatan_disposisi", etCatatanDisposisi.getEditText().getText().toString());
                }
                return stringMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}
