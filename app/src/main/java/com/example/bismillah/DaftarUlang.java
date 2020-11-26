package com.example.bismillah;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class DaftarUlang extends AppCompatActivity {

    String url = "https://pajuts.000webhostapp.com/daftarulang/daftarulang.php";
    ListView listView;
    TextView txtNpmDU;
    String nomor[] = {"1","2","3","4","5","6","7","8","9","10"};
    String npm;
    private ProgressDialog progressDialog;
    ArrayList<HashMap<String, String>> JsonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_ulang);

        listView = findViewById(R.id.listViewDU);
        JsonList = new ArrayList<>();
        txtNpmDU = (TextView) findViewById(R.id.npmDU);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("NPM_DU");
        txtNpmDU.setText(npm);

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
                        String tahunAjaran = c.getString("tahunAjaran");
                        String ukt = c.getString("ukt");
                        String status = c.getString("status");

                        HashMap<String, String> resultx = new HashMap<>();

                        resultx.put("no", no);
                        resultx.put("tahunAjaran", tahunAjaran);
                        resultx.put("ukt", ukt);
                        resultx.put("status", status);

                        JsonList.add(resultx);

                        ListAdapter adapter = new SimpleAdapter(

                                DaftarUlang.this, JsonList, R.layout.listview_daftarulang,
                                new String[]{"no","tahunAjaran","ukt","status"},
                                new int[]{R.id.no,R.id.txtTahunAjaranDU,R.id.txtUktDU,R.id.txtStatusDU});

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
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("npm", npm);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(DaftarUlang.this);
        requestQueue.add(request);

    }

}