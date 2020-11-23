package com.example.bismillah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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

public class DataDiri extends AppCompatActivity {

    JSONObject jsonObject;
    String url = "https://pajuts.000webhostapp.com/readbynpm.php";
    String npm, out;
    TextView txtNpm,txtNIK,txtNama,txtLahir,txtAgama,txtJk,txtAlamat,txtAsal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_data_diri);

        txtNpm = (TextView) findViewById(R.id.NPM);
        txtNIK = (TextView) findViewById(R.id.nikDtDr);
        txtNama = (TextView) findViewById(R.id.namaDtDr);
        txtLahir = (TextView) findViewById(R.id.lahirDtDr);
        txtAgama = (TextView) findViewById(R.id.agamaDtDr);
        txtJk = (TextView) findViewById(R.id.jkDtDr);
        txtAlamat = (TextView) findViewById(R.id.alamatDtDr);
        txtAsal = (TextView) findViewById(R.id.asalDtDr);

        Bundle extras = getIntent().getExtras();
        npm = extras.getString("NPM_dtdiri");
        txtNpm.setText(npm);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response;
                try {
                    jsonObject = new JSONObject(s);
                    JSONArray a = jsonObject.getJSONArray("result");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject c = a.getJSONObject(i);
                        String nik = c.getString("nik");
                        String namaLengkap = c.getString("namaLengkap");
                        String tanggalLahir = c.getString("tanggalLahir");
                        String agama = c.getString("agama");
                        String jenisKelamin = c.getString("jenisKelamin");
                        String alamat = c.getString("alamat");
                        String kotaAsal = c.getString("kotaAsal");
                        HashMap<String, String> resultx = new HashMap<>();
                        txtNIK.setText(nik);
                        txtNama.setText(namaLengkap);
                        txtLahir.setText(tanggalLahir);
                        txtAgama.setText(agama);
                        txtJk.setText(jenisKelamin);
                        txtAlamat.setText(alamat);
                        txtAsal.setText(kotaAsal);
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

        RequestQueue requestQueue = Volley.newRequestQueue(DataDiri.this);
        requestQueue.add(request);



        Button btnback = (Button) findViewById(R.id.btn_backdtdr);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataDiri.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}