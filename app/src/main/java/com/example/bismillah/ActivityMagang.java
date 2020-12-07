package com.example.bismillah;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class ActivityMagang extends AppCompatActivity {
    TextView txtNpmMagang;
    ListView listView;
    private ProgressDialog progressDialog;
    Button btnTambahMagang;
    ArrayList<HashMap<String, String>> JsonList;
    String url = "https://pajuts.000webhostapp.com/magang/readmagang.php";
    TextView noMagang,judulMagang,tempatMagang,provinsiMagang,kotaMagang,tanggalMulai,tanggalSelesai,ringkasan;
    String npm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magang);
        txtNpmMagang = (TextView) findViewById(R.id.npmMagang);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("NPM_Magang");

        JsonList = new ArrayList<>();
        txtNpmMagang.setText(npm);

        noMagang = (TextView) findViewById(R.id.npmMagang);
        judulMagang = (TextView) findViewById(R.id.txtJudulMagang);
        tempatMagang= (TextView) findViewById(R.id.txtTempatMagang);
        provinsiMagang = (TextView) findViewById(R.id.txtProvinsi);
        kotaMagang = (TextView) findViewById(R.id.txtKota);
        tanggalMulai = (TextView) findViewById(R.id.txtTanggalMulai);
        tanggalSelesai = (TextView) findViewById(R.id.txtTanggalSelesai);
        ringkasan = (TextView) findViewById(R.id.txtRingkasan);
        listView = (ListView) findViewById(R.id.listViewMagang);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response;

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray a = jsonObject.getJSONArray("result");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject c = a.getJSONObject(i);

                        String no = c.getString("no");
                        String judulMagang = c.getString("judulMagang");
                        String tempatMagang = c.getString("tempatMagang");
                        String provinsiMagang = c.getString("provinsiMagang");
                        String kotaMagang = c.getString("kotaMagang");
                        String tanggalMulai = c.getString("tanggalMulai");
                        String tanggalSelesai = c.getString("tanggalSelesai");
                        String ringkasan = c.getString("ringkasan");

                        final HashMap<String, String> resultx = new HashMap<>();

                        resultx.put("no", no);
                        resultx.put("judulMagang", judulMagang);
                        resultx.put("tempatMagang", tempatMagang);
                        resultx.put("provinsiMagang", provinsiMagang);
                        resultx.put("kotaMagang", kotaMagang);
                        resultx.put("tanggalMulai", tanggalMulai);
                        resultx.put("tanggalSelesai", tanggalSelesai);
                        resultx.put("ringkasan", ringkasan);
                        JsonList.add(resultx);

                        ListAdapter adapter = new SimpleAdapter(

                                ActivityMagang.this, JsonList, R.layout.listview_magang,
                                new String[]{"no","judulMagang","tempatMagang","provinsiMagang","kotaMagang","tanggalMulai","tanggalSelesai","ringkasan"},
                                new int[]{R.id.noMagang,R.id.txtJudulMagang,R.id.txtTempatMagang,
                                        R.id.txtProvinsi,R.id.txtKota,R.id.txtTanggalMulai, R.id.txtTanggalSelesai, R.id.txtRingkasan}
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
        RequestQueue requestQueue = Volley.newRequestQueue(ActivityMagang.this);
        requestQueue.add(request);

        Button btnTambahMagang = (Button) findViewById(R.id.btnTambahOrganisasi);
        btnTambahMagang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMagang.this, ActivityTambahMagang.class);
                intent.putExtra("npmMagang", npm);
                startActivity(intent);
            }
        });

    }
}