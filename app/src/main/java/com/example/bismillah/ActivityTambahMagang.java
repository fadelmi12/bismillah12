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

public class ActivityTambahMagang extends AppCompatActivity {

    TextView npmMagang;
    String npm;
    Button btnSubmit;
    String url = "https://pajuts.000webhostapp.com/magang/inputmagang.php";
    String snoMagang,sJudul,sTempat,sProvinsi,sKota,sMulai,sSelesai,sRingkasan;
    EditText txtnoMagang,txtJudul,txtTempat,txtProvinsi,txtKota,txtMulai,txtSelesai,txtRingkasan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_magang);

        npmMagang = (TextView) findViewById(R.id.txtNpmMagang);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("npmMagang");
        npmMagang.setText(npm);


        txtnoMagang = (EditText) findViewById(R.id.edIdNoMagang);
        txtJudul = (EditText) findViewById(R.id.edJudulMagang);
        txtTempat = (EditText) findViewById(R.id.edTempatMagang);
        txtProvinsi = (EditText) findViewById(R.id.edProvinsiMagang);
        txtKota =  (EditText) findViewById(R.id.edKotaMagang);
        txtMulai =  (EditText) findViewById(R.id.edMulaiMagang);
        txtSelesai =  (EditText) findViewById(R.id.edSelesaiMagang);
        txtRingkasan =  (EditText) findViewById(R.id.edRingkasanMagang);


        btnSubmit = (Button) findViewById(R.id.btnSubmitMagang);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                snoMagang = txtnoMagang.getText().toString().trim();
                sJudul = txtJudul.getText().toString().trim();
                sTempat = txtTempat.getText().toString().trim();
                sProvinsi = txtProvinsi.getText().toString().trim();
                sKota = txtKota.getText().toString().trim();
                sMulai = txtMulai.getText().toString().trim();
                sSelesai = txtSelesai.getText().toString().trim();
                sRingkasan = txtRingkasan.getText().toString().trim();

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String s = response;
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray a = jsonObject.getJSONArray("result");
                            for (int i = 0; i < a.length(); i++) {
                                JSONObject c = a.getJSONObject(i);
                                String judulMagang = c.getString("judulMagang");
                                String no = c.getString("no");
                                String npm = c.getString("npm");
                                String tempatMagang = c.getString("tempatMagang");
                                String provinsiMagang = c.getString("provinsiMagang");
                                String kotaMagang = c.getString("kotaMagang");
                                String tanggalMulai = c.getString("tanggalMulai");
                                String tanggalSelesai = c.getString("tanggalSelesai");
                                String ringkasan = c.getString("ringkasan");
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
                        params.put("no", snoMagang);
                        params.put("judulMagang", sJudul);
                        params.put("tempatMagang", sTempat);
                        params.put("provinsiMagang", sProvinsi);
                        params.put("kotaMagang", sKota);
                        params.put("tanggalMulai", sMulai);
                        params.put("tanggalSelesai", sSelesai);
                        params.put("ringkasan", sRingkasan);
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(ActivityTambahMagang.this);
                requestQueue.add(request);

                Intent intent = new Intent(ActivityTambahMagang.this, MainActivity.class);
                intent.putExtra("NPM", npm);
                startActivity(intent);
            }
        });
    }
}