package com.example.bismillah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class ActivityUbahDataOrtu extends AppCompatActivity {

    String npm;
    String url1 = "https://pajuts.000webhostapp.com/dtortu/readortubynpm.php";
    String url2 = "https://pajuts.000webhostapp.com/dtortu/editortu.php";
    EditText txtid,txtNPM,txtNamaA,txtNamaI,txtNamaW,txtPekerjaanA,txtPekerjaanI,txtPekerjaanW,txtPerusahaanA, txtPerusahaanI, txtPerusahaanW;
    EditText txtid1,txtNPM1,txtNamaA1,txtNamaI1,txtNamaW1,txtPekerjaanA1,txtPekerjaanI1,txtPekerjaanW1,txtPerusahaanA1, txtPerusahaanI1, txtPerusahaanW1;
    String txtid2,txtNPM2,txtNamaA2,txtNamaI2,txtNamaW2,txtPekerjaanA2,txtPekerjaanI2,txtPekerjaanW2,txtPerusahaanA2, txtPerusahaanI2, txtPerusahaanW2;
    Button btnsubmitEDtDr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_data_ortu);

        txtNPM = (EditText) findViewById(R.id.npmEDtOrtu);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("npmEOrtu");
        txtNPM.setText(npm);

        txtid = (EditText) findViewById(R.id.idEDtOrtu);
        txtNPM = (EditText) findViewById(R.id.npmEDtOrtu);
        txtNamaA = (EditText) findViewById(R.id.namaAyahEDtOrtu);
        txtPekerjaanA = (EditText) findViewById(R.id.pekerjaanEAyahDtOrtu);
        txtPerusahaanA = (EditText) findViewById(R.id.instansiAyahEDtOrtu);
        txtNamaI = (EditText) findViewById(R.id.namaIbuEDtOrtu);
        txtPekerjaanI = (EditText) findViewById(R.id.pekerjaanIbuEDtOrtu);
        txtPerusahaanI = (EditText) findViewById(R.id.instansiIbuEDtOrtu);
        txtNamaW = (EditText) findViewById(R.id.namaWaliEDtOrtu);
        txtPekerjaanW = (EditText) findViewById(R.id.pekerjaanWaliEDtOrtu);
        txtPerusahaanW = (EditText) findViewById(R.id.instansiWaliEDtOrtu);

        StringRequest request = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response;
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray a = jsonObject.getJSONArray("result");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject c = a.getJSONObject(i);
                        String id = c.getString("id");
                        String npm = c.getString("npm");
                        String namaAyah = c.getString("namaAyah");
                        String pekerjaanAyah = c.getString("pekerjaanAyah");
                        String instansiAyah = c.getString("instansiAyah");
                        String namaIbu = c.getString("namaIbu");
                        String pekerjaanIbu = c.getString("pekerjaanIbu");
                        String instansiIbu = c.getString("instansiIbu");
                        String namaWali = c.getString("namaWali");
                        String pekerjaanWali = c.getString("pekerjaanWali");
                        String instansiWali = c.getString("instansiWali");

                        HashMap<String, String> resultx = new HashMap<>();
                        txtid.setText(id);
                        txtNPM.setText(npm);
                        txtNamaA.setText(namaAyah);
                        txtPekerjaanA.setText(pekerjaanAyah);
                        txtPerusahaanA.setText(instansiAyah);
                        txtNamaI.setText(namaIbu);
                        txtPekerjaanI.setText(pekerjaanIbu);
                        txtPerusahaanI.setText(instansiIbu);
                        txtNamaW.setText(namaWali);
                        txtPekerjaanW.setText(pekerjaanWali);
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

        RequestQueue requestQueue = Volley.newRequestQueue(ActivityUbahDataOrtu.this);
        requestQueue.add(request);

        txtid1 = (EditText) findViewById(R.id.idEDtOrtu);
        txtNPM1 = (EditText) findViewById(R.id.npmEDtOrtu);
        txtNamaA1 = (EditText) findViewById(R.id.namaAyahEDtOrtu);
        txtPekerjaanA1 = (EditText) findViewById(R.id.pekerjaanEAyahDtOrtu);
        txtPerusahaanA1 = (EditText) findViewById(R.id.instansiAyahEDtOrtu);
        txtNamaI1 = (EditText) findViewById(R.id.namaIbuEDtOrtu);
        txtPekerjaanI1 = (EditText) findViewById(R.id.pekerjaanIbuEDtOrtu);
        txtPerusahaanI1 = (EditText) findViewById(R.id.instansiIbuEDtOrtu);
        txtNamaW1 = (EditText) findViewById(R.id.namaWaliEDtOrtu);
        txtPekerjaanW1 = (EditText) findViewById(R.id.pekerjaanWaliEDtOrtu);
        txtPerusahaanW1 = (EditText) findViewById(R.id.instansiWaliEDtOrtu);
        btnsubmitEDtDr = (Button) findViewById(R.id.btnsubmitEDtOrtu);

        btnsubmitEDtDr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtid2 = txtid1.getText().toString().trim();
                txtNPM2 = txtNPM1.getText().toString().trim();
                txtNamaA2 = txtNamaA1.getText().toString().trim();
                txtPekerjaanA2 = txtPekerjaanA1.getText().toString().trim();
                txtPerusahaanA2 = txtPerusahaanA1.getText().toString().trim();
                txtNamaI2 = txtNamaI1.getText().toString().trim();
                txtPekerjaanI2 = txtPekerjaanI1.getText().toString().trim();
                txtPerusahaanI2 = txtPerusahaanI1.getText().toString().trim();
                txtNamaW2 = txtNamaW1.getText().toString().trim();
                txtPekerjaanW2 = txtPekerjaanW1.getText().toString().trim();
                txtPerusahaanW2 = txtPerusahaanW1.getText().toString().trim();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String s = response;
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray a = jsonObject.getJSONArray("result");
                            for (int i = 0; i < a.length(); i++) {
                                JSONObject c = a.getJSONObject(i);
                                String id = c.getString("id");
                                String npm = c.getString("npm");
                                String namaAyah = c.getString("namaAyah");
                                String pekerjaanAyah = c.getString("pekerjaanAyah");
                                String instansiAyah = c.getString("instansiAyah");
                                String namaIbu = c.getString("namaIbu");
                                String pekerjaanIbu = c.getString("pekerjaanIbu");
                                String instansiIbu = c.getString("instansiIbu");
                                String namaWali = c.getString("namaWali");
                                String pekerjaanWali = c.getString("pekerjaanWali");
                                String instansiWali = c.getString("instansiWali");

                                txtid1.setText(id);
                                txtNPM1.setText(npm);
                                txtNamaA1.setText(namaAyah);
                                txtPekerjaanA1.setText(pekerjaanAyah);
                                txtPerusahaanA1.setText(instansiAyah);
                                txtNamaI1.setText(namaIbu);
                                txtPekerjaanI1.setText(pekerjaanIbu);
                                txtPerusahaanI1.setText(instansiIbu);
                                txtNamaW1.setText(namaWali);
                                txtPekerjaanW1.setText(pekerjaanWali);
                                txtPerusahaanW1.setText(instansiWali);
                                String berhasil = "Data Berhasil Diubah";
                                Toast.makeText(ActivityUbahDataOrtu.this, berhasil, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ActivityUbahDataOrtu.this, ActivityUbahDataOrtu.class);
                                intent.putExtra("npmEOrtu", txtNPM2);
                                startActivity(intent);

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

                        params.put("id", txtid2);
                        params.put("npm", txtNPM2);
                        params.put("namaAyah", txtNamaA2);
                        params.put("pekerjaanAyah", txtPekerjaanA2);
                        params.put("instansiAyah", txtPerusahaanA2);
                        params.put("namaIbu", txtNamaI2);
                        params.put("pekerjaanIbu", txtPekerjaanI2);
                        params.put("instansiIbu", txtPerusahaanI2);
                        params.put("namaWali", txtNamaW2);
                        params.put("pekerjaanWali", txtPekerjaanW2);
                        params.put("instansiWali", txtPerusahaanW2);
                        return params;

                    }
                };

                RequestQueue requestQ = Volley.newRequestQueue(ActivityUbahDataOrtu.this);
                requestQ.add(stringRequest);
            }

        });

        Button btnBackEDtDr = (Button) findViewById(R.id.btn_backEDtDr);
        btnBackEDtDr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtNPM1.getText().toString();
                Intent intent = new Intent(ActivityUbahDataOrtu.this, DataOrtu.class);
                intent.putExtra("NPM_dtortu", npm);
                startActivity(intent);
            }
        });
    }
}