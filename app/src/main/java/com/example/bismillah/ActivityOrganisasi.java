package com.example.bismillah;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityOrganisasi extends AppCompatActivity {

    TextView txtNpmOrganisasi;
    ListView listView;
    private ProgressDialog progressDialog;
    Button btnTambahOrganisasi;
    ArrayList<HashMap<String, String>> JsonList;
    String url = "https://pajuts.000webhostapp.com/organisasi/readorganisasi.php";
    TextView noOrganisasi,namaOrganisasi,masukOrganisai,keluarOrganisasi,jabatanOrganisasi;
    String npm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisasi);

        txtNpmOrganisasi = (TextView) findViewById(R.id.npmOrganisasi);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("NPM_Organisasi");

        JsonList = new ArrayList<>();
        txtNpmOrganisasi.setText(npm);

        noOrganisasi = (TextView) findViewById(R.id.noOrganisasi);
        namaOrganisasi = (TextView) findViewById(R.id.txtNamaOrganisasi);
        masukOrganisai = (TextView) findViewById(R.id.txtMasukOrganisasi);
        keluarOrganisasi = (TextView) findViewById(R.id.txtKeluarOrganisasi);
        jabatanOrganisasi = (TextView) findViewById(R.id.txtJabatanOrganisasi);
        listView = (ListView) findViewById(R.id.listViewOrganisasi);




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
                        String namaOrganisasi = c.getString("namaOrganisasi");
                        String masuk = c.getString("masuk");
                        String keluar = c.getString("keluar");
                        String jabatan = c.getString("jabatan");

                        final HashMap<String, String> resultx = new HashMap<>();

                        resultx.put("no", no);
                        resultx.put("namaOrganisasi", namaOrganisasi);
                        resultx.put("masuk", masuk);
                        resultx.put("keluar", keluar);
                        resultx.put("jabatan", jabatan);

                        JsonList.add(resultx);

                        ListAdapter adapter = new SimpleAdapter(

                                ActivityOrganisasi.this, JsonList, R.layout.listview_organisasi,
                                new String[]{"no","namaOrganisasi","masuk","keluar","jabatan"},
                                new int[]{R.id.noOrganisasi,R.id.txtNamaOrganisasi,R.id.txtMasukOrganisasi
                                        ,R.id.txtKeluarOrganisasi,R.id.txtJabatanOrganisasi}
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

        RequestQueue requestQueue = Volley.newRequestQueue(ActivityOrganisasi.this);
        requestQueue.add(request);

        Button btnBackHome = (Button) findViewById(R.id.btn_backHomeOrganisasi);
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtNpmOrganisasi.getText().toString();
                Intent intent = new Intent(ActivityOrganisasi.this, MainActivity.class);
                intent.putExtra("NPM", npm);
                startActivity(intent);
            }
        });

        btnTambahOrganisasi = (Button) findViewById(R.id.btnTambahOrganisasi);
        btnTambahOrganisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityOrganisasi.this, ActivityTambahOrganisasi.class);
                intent.putExtra("npmtor", npm);
                startActivity(intent);
            }
        });


    }
}