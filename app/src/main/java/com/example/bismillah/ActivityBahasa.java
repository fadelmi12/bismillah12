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

public class ActivityBahasa extends AppCompatActivity {
    TextView txtNpmBahasa;
    ListView listView;
    private ProgressDialog progressDialog;
    Button btnTambahBahasa;
    ArrayList<HashMap<String, String>> JsonList;
    String url = "https://pajuts.000webhostapp.com/bahasa/readbahasa.php";
    TextView noBahasa,namaBahasa,periodeBahasa,tahunBahasa,skorBahasa,tanggalBahasa;
    String npm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bahasa);

        txtNpmBahasa = (TextView) findViewById(R.id.npmBahasa);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("NPM_Bahasa");

        JsonList = new ArrayList<>();
        txtNpmBahasa.setText(npm);

        noBahasa = (TextView) findViewById(R.id.noBahasa);
        namaBahasa = (TextView) findViewById(R.id.txtNamaBahasa);
        periodeBahasa= (TextView) findViewById(R.id.txtPeriodeBahasa);
        tahunBahasa = (TextView) findViewById(R.id.txtTahunBahasa);
        skorBahasa = (TextView) findViewById(R.id.txtSkorBahasa);
        tanggalBahasa = (TextView) findViewById(R.id.txtTanggalBahasa);
        listView = (ListView) findViewById(R.id.listViewBahasa);




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
                        String namaBahasa = c.getString("namaBahasa");
                        String periode = c.getString("periode");
                        String tahun = c.getString("tahun");
                        String skor = c.getString("skor");
                        String tanggal = c.getString("tanggal");

                        final HashMap<String, String> resultx = new HashMap<>();

                        resultx.put("no", no);
                        resultx.put("namaBahasa", namaBahasa);
                        resultx.put("periode", periode);
                        resultx.put("tahun", tahun);
                        resultx.put("skor", skor);
                        resultx.put("tanggal", tanggal);

                        JsonList.add(resultx);

                        ListAdapter adapter = new SimpleAdapter(

                                ActivityBahasa.this, JsonList, R.layout.listview_bahasa,
                                new String[]{"no","namaBahasa","periode","tahun","skor","tanggal"},
                                new int[]{R.id.noBahasa,R.id.txtNamaBahasa,R.id.txtPeriodeBahasa
                                        ,R.id.txtTahunBahasa,R.id.txtSkorBahasa,R.id.txtTanggalBahasa}
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

        RequestQueue requestQueue = Volley.newRequestQueue(ActivityBahasa.this);
        requestQueue.add(request);

        Button btnBackHome = (Button) findViewById(R.id.btn_backHomeBahasa);
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtNpmBahasa.getText().toString();
                Intent intent = new Intent(ActivityBahasa.this, MainActivity.class);
                intent.putExtra("NPM", npm);
                startActivity(intent);
            }
        });

        btnTambahBahasa = (Button) findViewById(R.id.btnTambahBahasa);
        btnTambahBahasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityBahasa.this, ActivityTambahBahasa.class);
                intent.putExtra("npmBahasa", npm);
                startActivity(intent);
            }
        });


    }
}