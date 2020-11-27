package com.example.bismillah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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

public class DataOrtu extends AppCompatActivity {
    String npm;
    Button btnubhdtortu;
    String url1 = "https://pajuts.000webhostapp.com/dtdiri/readdiribynpm.php";
    String url2 = "https://pajuts.000webhostapp.com/dtortu/readortubynpm.php";
    TextView txtNPM,txtNamaA,txtNamaI,txtNamaW,txtPekerjaanA,txtPekerjaanI,txtPekerjaanW,txtPerusahaanA, txtPerusahaanI, txtPerusahaanW, txtAngkatan, txtProdi, txtKelas, txtNo, txtKota,txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_ortu);


        txtNPM = (TextView) findViewById(R.id.NPM2);
        txtNamaA = (TextView) findViewById(R.id.namaAyahDtOrtu);
        txtNamaI = (TextView) findViewById(R.id.namaIbuDtOrtu);
        txtNamaW = (TextView) findViewById(R.id.namaWaliDtOrtu);
        txtPekerjaanA = (TextView) findViewById(R.id.pekerjaanAyahDtOrtu);
        txtPekerjaanI = (TextView) findViewById(R.id.pekerjaanIbuDtOrtu);
        txtPekerjaanW = (TextView) findViewById(R.id.pekerjaanWaliDtOrtu);
        txtPerusahaanA = (TextView) findViewById(R.id.instansiAyahDtOrtu);
        txtPerusahaanI = (TextView) findViewById(R.id.instansiIbuDtOrtu);
        txtPerusahaanW = (TextView) findViewById(R.id.instansiWaliDtOrtu);
        txtAngkatan = (TextView) findViewById(R.id.angkatanDtOrtu);
        txtProdi = (TextView) findViewById(R.id.prodiDtOrtu);
        txtKelas = (TextView) findViewById(R.id.kelasDtOrtu);
        txtNo = (TextView) findViewById(R.id.noDtOrtu);
        txtKota = (TextView) findViewById(R.id.kotaDtOrtu);
        txtStatus = (TextView) findViewById(R.id.statusDtOrtu);
        btnubhdtortu = (Button) findViewById(R.id.btnUbahDtDr);

        Bundle extras = getIntent().getExtras();
        npm = extras.getString("NPM_dtortu");
        txtNPM.setText(npm);

        btnubhdtortu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtNPM.getText().toString();
                Intent intent = new Intent(DataOrtu.this, ActivityUbahDataOrtu.class);
                intent.putExtra("npmEOrtu", npm);
                startActivity(intent);
            }
        });

        StringRequest request2 = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String response1 = response;
                try {
                    JSONObject jsonObject = new JSONObject(response1);
                    JSONArray a = jsonObject.getJSONArray("result");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject d = a.getJSONObject(i);
                        String namaAyah = d.getString("namaAyah");
                        String pekerjaanAyah = d.getString("pekerjaanAyah");
                        String instansiAyah = d.getString("instansiAyah");
                        String namaIbu = d.getString("namaIbu");
                        String pekerjaanIbu = d.getString("pekerjaanIbu");
                        String instansiIbu = d.getString("instansiIbu");
                        String namaWali = d.getString("namaWali");
                        String pekerjaanWali = d.getString("pekerjaanWali");
                        String instansiWali = d.getString("instansiWali");

                        HashMap<String, String> resultx = new HashMap<>();
                        txtNamaA.setText(namaAyah);
                        txtNamaI.setText(namaIbu);
                        txtNamaW.setText(namaWali);
                        txtPekerjaanA.setText(pekerjaanAyah);
                        txtPekerjaanI.setText(pekerjaanIbu);
                        txtPekerjaanW.setText(pekerjaanWali);
                        txtPerusahaanA.setText(instansiAyah);
                        txtPerusahaanI.setText(instansiIbu);
                        txtPerusahaanW.setText(instansiWali);
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

                params.put("npm", npm);
                return params;

            }
        };

        RequestQueue requestQueue2 = Volley.newRequestQueue(DataOrtu.this);
        requestQueue2.add(request2);

        StringRequest request = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response;
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray a = jsonObject.getJSONArray("result");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject c = a.getJSONObject(i);
                        String angkatan = c.getString("angkatan");
                        String prodi = c.getString("prodi");
                        String kelas = c.getString("kelas");
                        String noHp = c.getString("noHp");
                        String kota = c.getString("kota");
                        String status = c.getString("status");

                        HashMap<String, String> resultx = new HashMap<>();
                        txtAngkatan.setText(angkatan);
                        txtProdi.setText(prodi);
                        txtKelas.setText(kelas);
                        txtNo.setText(noHp);
                        txtKota.setText(kota);
                        txtStatus.setText(status);
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

                params.put("npm", npm);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(DataOrtu.this);
        requestQueue.add(request);

        Button btnBackHome = (Button) findViewById(R.id.btn_backHomeDtOrtu);
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtNPM.getText().toString();
                Intent intent = new Intent(DataOrtu.this, MainActivity.class);
                intent.putExtra("NPM", npm);
                startActivity(intent);
            }
        });

    }
}