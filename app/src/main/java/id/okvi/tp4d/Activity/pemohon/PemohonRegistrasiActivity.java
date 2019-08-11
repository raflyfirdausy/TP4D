package id.okvi.tp4d.Activity.pemohon;

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
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import id.okvi.tp4d.Helper.API;
import id.okvi.tp4d.Helper.Bantuan;
import id.okvi.tp4d.R;

public class PemohonRegistrasiActivity extends AppCompatActivity {
    TextInputEditText etEmail;
    TextInputEditText etInstansi;
    TextInputEditText etPassword;
    TextInputEditText etKonfirmasiPassword;
    Button btnDaftar;
    private Context context = PemohonRegistrasiActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemohon_registrasi);

        etEmail = findViewById(R.id.etEmail);
        etInstansi = findViewById(R.id.etInstansi);
        etPassword = findViewById(R.id.etPassword);
        etKonfirmasiPassword = findViewById(R.id.etKonfirmasiPassword);
        btnDaftar = findViewById(R.id.btnDaftar);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prosesDaftar();
            }
        });
    }

    private void prosesDaftar() {
        if (TextUtils.isEmpty(etEmail.getText().toString()) ||
                TextUtils.isEmpty(etEmail.getText().toString()) ||
                TextUtils.isEmpty(etEmail.getText().toString()) ||
                TextUtils.isEmpty(etEmail.getText().toString())) {
            new Bantuan(context).toastLong("Masih ada data yang belum diisi, silahkan dilengkapi terlebih dahulu");
        } else if (!etPassword.getText().toString().equals(etKonfirmasiPassword.getText().toString())) {
            new Bantuan(context).alertDialogPeringatan("Konfirmasi Password Salah !");
        } else if (etPassword.getText().length() < 6) {
            new Bantuan(context).alertDialogPeringatan("Password minimal 6 karakter !");
        } else {
            final ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Loading ...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();

            RequestQueue requestQueue;
            requestQueue = Volley.newRequestQueue(context);
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    API.REGISTER_PEMOHON,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONObject object = new JSONObject(response);
                                if (object.getInt("status") == 1) {
                                    new Bantuan(context).toastLong(object.getString("result"));
                                    Intent intent = new Intent(context, PemohonLoginActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                } else {
                                    new Bantuan(context).alertDialogPeringatan(object.getString("result"));
                                }
                            } catch (JSONException e) {
                                new Bantuan(context).alertDialogPeringatan(e.getMessage());
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
                    stringMap.put("email", etEmail.getText().toString());
                    stringMap.put("instansi", etInstansi.getText().toString());
                    stringMap.put("password", etPassword.getText().toString());
                    return stringMap;
                }
            };
            requestQueue.add(stringRequest);
        }
    }
}
