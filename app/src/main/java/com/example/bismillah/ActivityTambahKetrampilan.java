package com.example.bismillah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ActivityTambahKetrampilan extends AppCompatActivity {

    String npm;
    Button btnSubmit;
    String snoKetrampilan,snamaKetrampilan,sjenisKetrampilan,slevelKetrampilan;
    TextView npmKetrampilan;
    Spinner spinnerJenisKeterampilan;
    String url = "https://pajuts.000webhostapp.com/ketrampilan/inputketrampilan.php";
    EditText txtnoKetrampilan,txtnamaKetrampilan,txtjenisKetrampilan,txtlevelKetrampilan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_ketrampilan);
        npmKetrampilan = (TextView) findViewById(R.id.txtNpmKetrampilan);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("npmKetrampilan");
        npmKetrampilan.setText(npm);

        txtnoKetrampilan = (EditText) findViewById(R.id.edIdNoKetrampilan);
        txtnamaKetrampilan = (EditText) findViewById(R.id.edNamaKetrampilan);
        txtjenisKetrampilan = (EditText) findViewById(R.id.edJenisKetrampilan);
        txtlevelKetrampilan = (EditText) findViewById(R.id.edLevelKetrampilan);
        spinnerJenisKeterampilan =  (Spinner) findViewById(R.id.spinnerJenisKeterampilan);
        btnSubmit = (Button) findViewById(R.id.btnSubmitKetrampilan);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                snoKetrampilan = txtnoKetrampilan.getText().toString().trim();
                snamaKetrampilan = txtnamaKetrampilan.getText().toString().trim();
                sjenisKetrampilan = spinnerJenisKeterampilan.getSelectedItem().toString().trim();
                slevelKetrampilan = txtlevelKetrampilan.getText().toString().trim();

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String s = response;
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray a = jsonObject.getJSONArray("result");
                            for (int i = 0; i < a.length(); i++) {
                                JSONObject c = a.getJSONObject(i);
                                String idKetrampilan = c.getString("idKetrampilan");
                                String no = c.getString("no");
                                String npm = c.getString("npm");
                                String namaKetrampilan = c.getString("namaKetrampilan");
                                String jenisKetrampilan = c.getString("jenisKetrampilan");
                                String leveKetrampilan = c.getString("levelKetrampilan");
                                HashMap<String, String> resultx = new HashMap<>();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("npm",npm);
                        params.put("no", snoKetrampilan);
                        params.put("namaKetrampilan", snamaKetrampilan);
                        params.put("jenisKetrampilan", sjenisKetrampilan);
                        params.put("levelKetrampilan", slevelKetrampilan);
                        return params;

                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(ActivityTambahKetrampilan.this);
                requestQueue.add(request);

                Intent intent = new Intent(ActivityTambahKetrampilan.this, MainActivity.class);
                intent.putExtra("NPM", npm);
                startActivity(intent);
            }
        });

    }
}