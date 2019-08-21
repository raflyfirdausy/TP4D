package id.okvi.tp4d.Activity.petugas;

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
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import id.okvi.tp4d.Helper.API;
import id.okvi.tp4d.Helper.Bantuan;
import id.okvi.tp4d.Helper.SharedPreferenceManager;
import id.okvi.tp4d.Model.UserLoginModel;
import id.okvi.tp4d.R;

public class LoginPetugasActivity extends AppCompatActivity {
    private TextInputLayout etEmail;
    private TextInputLayout etPassword;
    private Button btnLogin;
    private Context context = LoginPetugasActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_petugas);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prosesLogin();
            }
        });
    }

    private void prosesLogin() {
        if (TextUtils.isEmpty(etEmail.getEditText().getText().toString()) ||
                TextUtils.isEmpty(etPassword.getEditText().getText().toString())) {
            new Bantuan(context).toastLong("Masih Ada Data yang kosong !");
        } else {
            final ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Loading ...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();

            RequestQueue requestQueue;
            requestQueue = Volley.newRequestQueue(context);
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    API.LOGIN_PETUGAS,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONObject object = new JSONObject(response);
//                                new Bantuan(context).toastLong(object.getString("result"));
                                if (object.getInt("status") == 1) {
                                    JSONArray jsonArray = object.getJSONArray("result");
                                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                                    SharedPreferenceManager.getInstance(context).userLogin(
                                            new UserLoginModel(
                                                    jsonObject.getString("id_kajari_petugas"),
                                                    jsonObject.getString("status"),
                                                    jsonObject.getString("username"),
                                                    jsonObject.getString("status"),
                                                    jsonObject.getString("nip"),
                                                    jsonObject.getString("nama")
                                            )
                                    );
                                    new Bantuan(context).toastLong("Berhasil Login");
                                    Intent intent = new Intent(context, PetugasHomeActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                } else {
                                    new Bantuan(context).toastLong(object.getString("result"));
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
                    stringMap.put("email", etEmail.getEditText().getText().toString());
                    stringMap.put("nip", etEmail.getEditText().getText().toString());
                    stringMap.put("password", etPassword.getEditText().getText().toString());
                    return stringMap;
                }
            };
            requestQueue.add(stringRequest);
        }
    }
}
