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

public class RekapPresensi extends AppCompatActivity {
    String npm;
    String url = "https://pajuts.000webhostapp.com/dtdiri/readdiribynpm.php";
    TextView txtNPM,txtAngkatan, txtProdi, txtKelas, txtNo, txtKota,txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap_presensi);

        txtNPM = (TextView) findViewById(R.id.NPM3);
        txtAngkatan = (TextView) findViewById(R.id.angkatanRekap);
        txtProdi = (TextView) findViewById(R.id.prodiRekap);
        txtKelas = (TextView) findViewById(R.id.kelasRekap);
        txtNo = (TextView) findViewById(R.id.noRekap);
        txtKota = (TextView) findViewById(R.id.kotaRekap);
        txtStatus = (TextView) findViewById(R.id.statusRekap);
        Bundle extras = getIntent().getExtras();
        npm = extras.getString("NPM_rekapPresensi");
        txtNPM.setText(npm);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
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

        RequestQueue requestQueue = Volley.newRequestQueue(RekapPresensi.this);
        requestQueue.add(request);

        Button btnBackHome = (Button) findViewById(R.id.btn_backHomePresensi);
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtNPM.getText().toString();
                Intent intent = new Intent(RekapPresensi.this, MainActivity.class);
                intent.putExtra("NPM", npm);
                startActivity(intent);
            }
        });
    }
}