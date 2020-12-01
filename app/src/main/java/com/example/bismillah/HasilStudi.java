package com.example.bismillah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HasilStudi extends AppCompatActivity {
    Button btnsmt1, btnsmt2, btnsmt3, btnsmt4, btnsmt5, btnsmt6;
    String npm;
    String url1 = "https://pajuts.000webhostapp.com/dtdiri/readdiribynpm.php";
    TextView txtNPM, txtAngkatan, txtProdi, txtKelas, txtNo, txtKota,txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_studi);


        txtNPM = (TextView) findViewById(R.id.NPMHs);
        txtAngkatan = (TextView) findViewById(R.id.angkatanHs);
        txtProdi = (TextView) findViewById(R.id.prodiHs);
        txtKelas = (TextView) findViewById(R.id.kelasHs);
        txtNo = (TextView) findViewById(R.id.noHs);
        txtKota = (TextView) findViewById(R.id.kotaHs);
        txtStatus = (TextView) findViewById(R.id.statusHs);

        Bundle extras = getIntent().getExtras();
        npm = extras.getString("NPM_HasilStudi");
        txtNPM.setText(npm);

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

        RequestQueue requestQueue = Volley.newRequestQueue(HasilStudi.this);
        requestQueue.add(request);


        btnsmt1 = findViewById(R.id.smt1);
        btnsmt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtNPM.getText().toString();
                Intent intent = new Intent(HasilStudi.this, semester1.class);
                intent.putExtra("npmS1", npm);
                startActivity(intent);
            }
        });
        btnsmt2 = findViewById(R.id.smt2);
        btnsmt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtNPM.getText().toString();
                Intent intent = new Intent(HasilStudi.this, semester2.class);
                intent.putExtra("npmS2", npm);
                startActivity(intent);
            }
        });
        btnsmt3 = findViewById(R.id.smt3);
        btnsmt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtNPM.getText().toString();
                Intent intent = new Intent(HasilStudi.this, semester3.class);
                intent.putExtra("npmS3", npm);
                startActivity(intent);
            }
        });
        btnsmt4 = findViewById(R.id.smt4);
        btnsmt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtNPM.getText().toString();
                Intent intent = new Intent(HasilStudi.this, semester4.class);
                intent.putExtra("npmS4", npm);
                startActivity(intent);
            }
        });
        btnsmt5 = findViewById(R.id.smt5);
        btnsmt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtNPM.getText().toString();
                Intent intent = new Intent(HasilStudi.this, semester5.class);
                intent.putExtra("npmS5", npm);
                startActivity(intent);
            }
        });
        btnsmt6 = findViewById(R.id.smt6);
        btnsmt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtNPM.getText().toString();
                Intent intent = new Intent(HasilStudi.this, semester6.class);
                intent.putExtra("npmS6", npm);
                startActivity(intent);
            }
        });



    }

}
