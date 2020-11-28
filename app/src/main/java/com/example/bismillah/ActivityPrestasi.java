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

public class ActivityPrestasi extends AppCompatActivity {
    TextView txtNpmPrestasi;
    ListView listView;
    private ProgressDialog progressDialog;
    Button btnTambahPrestasi;
    ArrayList<HashMap<String, String>> JsonList;
    String url = "https://pajuts.000webhostapp.com/prestasi/readprestasi.php";
    TextView noPrestasi,namaPrestasi,tahunPrestasi,juaraPrestasi,tingkatPrestasi,jenisPrestasi;
    String npm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestasi);

        txtNpmPrestasi = (TextView) findViewById(R.id.npmPrestasi);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("NPM_Prestasi");

        JsonList = new ArrayList<>();
        txtNpmPrestasi.setText(npm);

        noPrestasi = (TextView) findViewById(R.id.noBahasa);
        namaPrestasi = (TextView) findViewById(R.id.txtNamaBahasa);
        tahunPrestasi= (TextView) findViewById(R.id.txtPeriodeBahasa);
        juaraPrestasi = (TextView) findViewById(R.id.txtTahunBahasa);
        tingkatPrestasi = (TextView) findViewById(R.id.txtSkorBahasa);
        jenisPrestasi = (TextView) findViewById(R.id.txtTanggalBahasa);
        listView = (ListView) findViewById(R.id.listViewPrestasi);




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
                        String namaPrestasi = c.getString("namaPrestasi");
                        String tahun = c.getString("tahun");
                        String juara = c.getString("juara");
                        String tingkat = c.getString("tingkat");
                        String jenis = c.getString("jenis");

                        final HashMap<String, String> resultx = new HashMap<>();

                        resultx.put("no", no);
                        resultx.put("namaPrestasi", namaPrestasi);
                        resultx.put("tahun", tahun);
                        resultx.put("juara", juara);
                        resultx.put("tingkat", tingkat);
                        resultx.put("jenis", jenis);

                        JsonList.add(resultx);

                        ListAdapter adapter = new SimpleAdapter(

                                ActivityPrestasi.this, JsonList, R.layout.listview_prestasi,
                                new String[]{"no","namaPrestasi","tahun","juara","tingkat","jenis"},
                                new int[]{R.id.noPrestasi,R.id.txtNamaPrestasi,R.id.txtTahunPrestasi,
                                        R.id.txtJuaraPrestasi,R.id.txtTingkatPrestasi,R.id.txtJenisPrestasi}
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

        RequestQueue requestQueue = Volley.newRequestQueue(ActivityPrestasi.this);
        requestQueue.add(request);

        Button btnBackHome = (Button) findViewById(R.id.btn_backHomePrestasi);
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtNpmPrestasi.getText().toString();
                Intent intent = new Intent(ActivityPrestasi.this, MainActivity.class);
                intent.putExtra("NPM", npm);
                startActivity(intent);
            }
        });

        btnTambahPrestasi = (Button) findViewById(R.id.btnTambahPrestasi);
        btnTambahPrestasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityPrestasi.this, ActivityTambahPrestasi.class);
                intent.putExtra("npmPrestasi", npm);
                startActivity(intent);
            }
        });


    }
}