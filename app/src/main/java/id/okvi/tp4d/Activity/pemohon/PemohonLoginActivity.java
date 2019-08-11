package id.okvi.tp4d.Activity.pemohon;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import id.okvi.tp4d.Activity.other.VerifikasiActivity;
import id.okvi.tp4d.Helper.API;
import id.okvi.tp4d.Helper.Bantuan;
import id.okvi.tp4d.Helper.SharedPreferenceManager;
import id.okvi.tp4d.Model.UserLoginModel;
import id.okvi.tp4d.R;

public class PemohonLoginActivity extends AppCompatActivity {

    TextInputEditText etEmail;
    TextInputEditText etPassword;
    Button btnLogin;
    Button btnDaftar;
    ImageView ivTimPengawal;
    private Context context = PemohonLoginActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemohon_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnDaftar = findViewById(R.id.btnDaftar);
        ivTimPengawal = findViewById(R.id.ivTimPengawal);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prosesLogin();
            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, PemohonRegistrasiActivity.class));
            }
        });

        ivTimPengawal.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                startActivity(new Intent(context, VerifikasiActivity.class));
                return true;
            }
        });
    }

    private void prosesLogin() {
        if (TextUtils.isEmpty(etEmail.getText().toString()) ||
                TextUtils.isEmpty(etPassword.getText().toString())) {
            new Bantuan(context).toastLong("Masih Ada Data yang kosong !");
        } else {
            final ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Loading ...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();

            RequestQueue requestQueue;
            requestQueue = Volley.newRequestQueue(context);
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    API.LOGIN_PEMOHON,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONObject object = new JSONObject(response);
                                if (object.getInt("status") == 1) {
                                    JSONArray jsonArray = object.getJSONArray("result");
                                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                                    SharedPreferenceManager.getInstance(context).userLogin(
                                            new UserLoginModel(
                                                    jsonObject.getString("id_pemohon"),
                                                    "pemohon",
                                                    jsonObject.getString("email"),
                                                    jsonObject.getString("instansi")
                                            )
                                    );
                                    Intent intent = new Intent(context, PemohonHomeActivity.class);
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
                    stringMap.put("password", etPassword.getText().toString());
                    return stringMap;
                }
            };
            requestQueue.add(stringRequest);
        }
    }
}
