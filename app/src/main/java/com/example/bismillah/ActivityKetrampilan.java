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

public class ActivityKetrampilan extends AppCompatActivity {

    TextView txtNPMKetrampilan;
    ListView listView;
    private ProgressDialog progressDialog;
    Button btnTambahKetrampilan;
    ArrayList<HashMap<String, String>> JsonList;
    String url = "https://pajuts.000webhostapp.com/ketrampilan/readketrampilan.php";
    TextView noKetrampilan,namaKetrampilan,jenisKetrampilan,levelKetrampilan;
    String npm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketrampilan);

        txtNPMKetrampilan = (TextView) findViewById(R.id.npmKetrampilan);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("NPM_Ketrampilan");

        JsonList = new ArrayList<>();
        txtNPMKetrampilan.setText(npm);

        noKetrampilan = (TextView) findViewById(R.id.noKetrampilan);
        namaKetrampilan = (TextView) findViewById(R.id.txtNamaKetrampilan);
        jenisKetrampilan = (TextView) findViewById(R.id.txtJenisKetrampilan);
        levelKetrampilan = (TextView) findViewById(R.id.txtLevel);
        listView = (ListView) findViewById(R.id.listViewKetrampilan);

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
                        String namaKetrampilan = c.getString("namaKetrampilan");
                        String jenisKetrampilan = c.getString("jenisKetrampilan");
                        String levelKetrampilan = c.getString("levelKetrampilan");

                        final HashMap<String, String> resultx = new HashMap<>();

                        resultx.put("no", no);
                        resultx.put("namaKetrampilan", namaKetrampilan);
                        resultx.put("jenisKetrampilan", jenisKetrampilan);
                        resultx.put("levelKetrampilan", levelKetrampilan);

                        JsonList.add(resultx);

                        ListAdapter adapter = new SimpleAdapter(
                                ActivityKetrampilan.this, JsonList, R.layout.listview_ketrampilan,
                                new String[]{"no","namaKetrampilan","jenisKetrampilan","levelKetrampilan"},
                                new int[]{R.id.noKetrampilan,R.id.txtNamaKetrampilan,R.id.txtJenisKetrampilan
                                        ,R.id.txtLevel}
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

        RequestQueue requestQueue = Volley.newRequestQueue(ActivityKetrampilan.this);
        requestQueue.add(request);

        Button btnBackHome = (Button) findViewById(R.id.btn_backHomeKetrampilan);
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtNPMKetrampilan.getText().toString();
                Intent intent = new Intent(ActivityKetrampilan.this, MainActivity.class);
                intent.putExtra("NPM", npm);
                startActivity(intent);
            }
        });

        btnTambahKetrampilan= (Button) findViewById(R.id.btnTambahKetrampilan);
        btnTambahKetrampilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityKetrampilan.this, ActivityTambahKetrampilan.class);
                intent.putExtra("npmKetrampilan", npm);
                startActivity(intent);
            }
        });
    }

}

