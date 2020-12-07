package com.example.bismillah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

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

public class ActivityUbahDataDiri extends AppCompatActivity {

    String npm;
    String url1 = "https://pajuts.000webhostapp.com/dtdiri/readdiribynpm.php";
    String url2 = "https://pajuts.000webhostapp.com/dtdiri/editdiri.php";
    EditText idEDtDr,npmEDtDr,nikEDtDr,namaEDtDr,lahirEDtDr,jkEDtDr,agamaEDtDr,alamatEDtDr,kotaSEDtDr,kotaAEDtDr,angkatanEDtDr,prodiEDtDr,kelasEDtDr,noHpEDtDr,emailEDtDr;
    EditText idEDtDr1,npmEDtDr1,nikEDtDr1,namaEDtDr1,lahirEDtDr1,jkEDtDr1,agamaEDtDr1,alamatEDtDr1,kotaSEDtDr1,kotaAEDtDr1,angkatanEDtDr1,prodiEDtDr1,kelasEDtDr1,noHpEDtDr1,emailEDtDr1;
    String idEDtDr2,npmEDtDr2,nikEDtDr2,namaEDtDr2,lahirEDtDr2,jkEDtDr2,agamaEDtDr2,alamatEDtDr2,kotaSEDtDr2,kotaAEDtDr2,angkatanEDtDr2,prodiEDtDr2,kelasEDtDr2,noHpEDtDr2,emailEDtDr2;
    TextView statusEDtDr;
    TextView statusEDtDr1;
    String statusEDtDr2;
    Button btnsubmitEDtDr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_data_diri);


        npmEDtDr = (EditText) findViewById(R.id.npmEDtDr);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("npmE");
        npmEDtDr.setText(npm);

        idEDtDr = (EditText) findViewById(R.id.idEDtDr);
        npmEDtDr = (EditText) findViewById(R.id.npmEDtDr);
        nikEDtDr = (EditText) findViewById(R.id.nikEDtDr);
        namaEDtDr = (EditText) findViewById(R.id.namaEDtDr);
        lahirEDtDr = (EditText) findViewById(R.id.lahirEDtDr);
        jkEDtDr = (EditText) findViewById(R.id.jkEDtDr);
        agamaEDtDr = (EditText) findViewById(R.id.agamaEDtDr);
        alamatEDtDr = (EditText) findViewById(R.id.alamatEDtDr);
        kotaAEDtDr = (EditText) findViewById(R.id.asalEDtDr);
        kotaSEDtDr = (EditText) findViewById(R.id.kotaSekarangEDtDr);
        angkatanEDtDr = (EditText) findViewById(R.id.angkatanEDtDr);
        prodiEDtDr = (EditText) findViewById(R.id.prodiEDtDr);
        kelasEDtDr = (EditText) findViewById(R.id.kelasEDtDr);
        noHpEDtDr = (EditText) findViewById(R.id.noHpEDtDr);
        emailEDtDr = (EditText) findViewById(R.id.emailEDtDr);
        statusEDtDr = (TextView) findViewById(R.id.statusEDtDr);

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
                        String nik = c.getString("nik");
                        String namaLengkap = c.getString("namaLengkap");
                        String tanggalLahir = c.getString("tanggalLahir");
                        String kota = c.getString("kota");
                        String kotaAsal = c.getString("kotaAsal");
                        String prodi = c.getString("prodi");
                        String angkatan = c.getString("angkatan");
                        String kelas = c.getString("kelas");
                        String noHp = c.getString("noHp");
                        String agama = c.getString("agama");
                        String jenisKelamin = c.getString("jenisKelamin");
                        String email = c.getString("email");
                        String alamat = c.getString("alamat");
                        String status = c.getString("status");

                        HashMap<String, String> resultx = new HashMap<>();
                        idEDtDr.setText(id);
                        npmEDtDr.setText(npm);
                        nikEDtDr.setText(nik);
                        namaEDtDr.setText(namaLengkap);
                        lahirEDtDr.setText(tanggalLahir);
                        kotaSEDtDr.setText(kota);
                        kotaAEDtDr.setText(kotaAsal);
                        prodiEDtDr.setText(prodi);
                        angkatanEDtDr.setText(angkatan);
                        kelasEDtDr.setText(kelas);
                        noHpEDtDr.setText(noHp);
                        agamaEDtDr.setText(agama);
                        jkEDtDr.setText(jenisKelamin);
                        emailEDtDr.setText(email);
                        alamatEDtDr.setText(alamat);
                        statusEDtDr.setText(status);
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

        RequestQueue requestQueue = Volley.newRequestQueue(ActivityUbahDataDiri.this);
        requestQueue.add(request);

        idEDtDr1 = (EditText) findViewById(R.id.idEDtDr);
        npmEDtDr1 = (EditText) findViewById(R.id.npmEDtDr);
        nikEDtDr1 = (EditText) findViewById(R.id.nikEDtDr);
        namaEDtDr1 = (EditText) findViewById(R.id.namaEDtDr);
        lahirEDtDr1 = (EditText) findViewById(R.id.lahirEDtDr);
        jkEDtDr1 = (EditText) findViewById(R.id.jkEDtDr);
        agamaEDtDr1 = (EditText) findViewById(R.id.agamaEDtDr);
        alamatEDtDr1 = (EditText) findViewById(R.id.alamatEDtDr);
        kotaAEDtDr1 = (EditText) findViewById(R.id.asalEDtDr);
        kotaSEDtDr1 = (EditText) findViewById(R.id.kotaSekarangEDtDr);
        angkatanEDtDr1 = (EditText) findViewById(R.id.angkatanEDtDr);
        prodiEDtDr1 = (EditText) findViewById(R.id.prodiEDtDr);
        kelasEDtDr1 = (EditText) findViewById(R.id.kelasEDtDr);
        noHpEDtDr1 = (EditText) findViewById(R.id.noHpEDtDr);
        emailEDtDr1 = (EditText) findViewById(R.id.emailEDtDr);
        statusEDtDr1 = (TextView) findViewById(R.id.statusEDtDr);
        btnsubmitEDtDr = (Button) findViewById(R.id.btnsubmitEDtDr);

        btnsubmitEDtDr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idEDtDr2 = idEDtDr1.getText().toString().trim();
                npmEDtDr2 = npmEDtDr1.getText().toString().trim();
                nikEDtDr2 = nikEDtDr1.getText().toString().trim();
                namaEDtDr2 = namaEDtDr1.getText().toString().trim();
                lahirEDtDr2 = lahirEDtDr1.getText().toString().trim();
                jkEDtDr2 = jkEDtDr1.getText().toString().trim();
                agamaEDtDr2 = agamaEDtDr1.getText().toString().trim();
                alamatEDtDr2 = alamatEDtDr1.getText().toString().trim();
                kotaAEDtDr2 = kotaAEDtDr1.getText().toString().trim();
                kotaSEDtDr2 = kotaSEDtDr1.getText().toString().trim();
                angkatanEDtDr2 = angkatanEDtDr1.getText().toString().trim();
                prodiEDtDr2 = prodiEDtDr1.getText().toString().trim();
                kelasEDtDr2 = kelasEDtDr1.getText().toString().trim();
                noHpEDtDr2 = noHpEDtDr1.getText().toString().trim();
                emailEDtDr2 = emailEDtDr1.getText().toString().trim();
                statusEDtDr2 = statusEDtDr1.getText().toString().trim();

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
                                String nik = c.getString("nik");
                                String namaLengkap = c.getString("namaLengkap");
                                String tanggalLahir = c.getString("tanggalLahir");
                                String kota = c.getString("kota");
                                String kotaAsal = c.getString("kotaAsal");
                                String prodi = c.getString("prodi");
                                String angkatan = c.getString("angkatan");
                                String kelas = c.getString("kelas");
                                String noHp = c.getString("noHp");
                                String agama = c.getString("agama");
                                String jenisKelamin = c.getString("jenisKelamin");
                                String email = c.getString("email");
                                String alamat = c.getString("alamat");
                                String status = c.getString("status");

                                idEDtDr1.setText(id);
                                npmEDtDr1.setText(npm);
                                nikEDtDr1.setText(nik);
                                namaEDtDr1.setText(namaLengkap);
                                lahirEDtDr1.setText(tanggalLahir);
                                kotaSEDtDr1.setText(kota);
                                kotaAEDtDr1.setText(kotaAsal);
                                prodiEDtDr1.setText(prodi);
                                angkatanEDtDr1.setText(angkatan);
                                kelasEDtDr1.setText(kelas);
                                noHpEDtDr1.setText(noHp);
                                agamaEDtDr1.setText(agama);
                                jkEDtDr1.setText(jenisKelamin);
                                emailEDtDr1.setText(email);
                                alamatEDtDr1.setText(alamat);
                                statusEDtDr1.setText(status);
                                String berhasil = "Data Berhasil Diubah";
                                Toast.makeText(ActivityUbahDataDiri.this, berhasil, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ActivityUbahDataDiri.this, ActivityUbahDataDiri.class);
                                intent.putExtra("npmE", npmEDtDr2);
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

                        params.put("id", idEDtDr2);
                        params.put("npm", npmEDtDr2);
                        params.put("nik", nikEDtDr2);
                        params.put("namaLengkap", namaEDtDr2);
                        params.put("tanggalLahir", lahirEDtDr2);
                        params.put("kota", kotaSEDtDr2);
                        params.put("kotaAsal", kotaAEDtDr2);
                        params.put("prodi", prodiEDtDr2);
                        params.put("angkatan", angkatanEDtDr2);
                        params.put("kelas", kelasEDtDr2);
                        params.put("noHp", noHpEDtDr2);
                        params.put("agama", agamaEDtDr2);
                        params.put("jenisKelamin", jkEDtDr2);
                        params.put("email", emailEDtDr2);
                        params.put("alamat", alamatEDtDr2);
                        params.put("status", statusEDtDr2);
                        return params;

                    }
                };
                RequestQueue requestQ = Volley.newRequestQueue(ActivityUbahDataDiri.this);
                requestQ.add(stringRequest);
            }

        });

        Button btnBackEDtDr = (Button) findViewById(R.id.btn_backEDtDr);
        btnBackEDtDr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = npmEDtDr1.getText().toString();
                Intent intent = new Intent(ActivityUbahDataDiri.this, DataDiri.class);
                intent.putExtra("NPM_dtdiri", npm);
                startActivity(intent);
            }
        });
    }
}