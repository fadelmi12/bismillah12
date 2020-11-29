package com.example.bismillah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityRekapPresensi extends AppCompatActivity {
    String npm;
    String url = "https://pajuts.000webhostapp.com/dtdiri/readdiribynpm.php";
    String url1 = "https://pajuts.000webhostapp.com/presensi/readpresensi.php";
    TextView txtNPM,txtAngkatan, txtProdi, txtKelas, txtNo, txtKota,txtStatus;
    ArrayList<HashMap<String, String>> JsonList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap_presensi);

        txtNPM = (TextView) findViewById(R.id.NPM3);

        JsonList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listViewPresensi);
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

        RequestQueue requestQueue = Volley.newRequestQueue(ActivityRekapPresensi.this);
        requestQueue.add(request);

        StringRequest request1 = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response;

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray a = jsonObject.getJSONArray("result");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject c = a.getJSONObject(i);

                        String npm = c.getString("npm");
                        String tahun = c.getString("tahun");
                        String semester = c.getString("semester");
                        String hadir = c.getString("hadir");
                        String izin = c.getString("izin");
                        String sakit = c.getString("sakit");
                        String alpha = c.getString("alpha");
                        String kosong = c.getString("kosong");

                        final HashMap<String, String> resultx = new HashMap<>();

                        resultx.put("npm", npm);
                        resultx.put("tahun", tahun);
                        resultx.put("semester", semester);
                        resultx.put("hadir", hadir);
                        resultx.put("izin", izin);
                        resultx.put("sakit", sakit);
                        resultx.put("alpha", alpha);
                        resultx.put("kosong", kosong);

                        JsonList.add(resultx);

                        ListAdapter adapter = new SimpleAdapter(

                                ActivityRekapPresensi.this, JsonList, R.layout.listview_presensi,
                                new String[]{"tahun","semester","hadir","izin","sakit","alpha","kosong"},
                                new int[]{R.id.txtTahunPresensi,R.id.txtSemesterPresensi,R.id.txtHadirPresensi
                                        ,R.id.txtIzinPresensi,R.id.txtSakitPresensi,R.id.txtAlphaPresensi,R.id.txtKosongPresensi}
                        );

                        listView.setAdapter(adapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("npm", npm);
                return params;

            }
        };

        RequestQueue requestQueue1 = Volley.newRequestQueue(ActivityRekapPresensi.this);
        requestQueue1.add(request1);

        Button btnBackHome = (Button) findViewById(R.id.btn_backHomePresensi);
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtNPM.getText().toString();
                Intent intent = new Intent(ActivityRekapPresensi.this, MainActivity.class);
                intent.putExtra("NPM", npm);
                startActivity(intent);
            }
        });
    }
}