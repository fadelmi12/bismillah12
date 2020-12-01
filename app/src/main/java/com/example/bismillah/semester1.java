package com.example.bismillah;

import androidx.appcompat.app.AppCompatActivity;

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

public class semester1 extends AppCompatActivity {

    String url = "https://pajuts.000webhostapp.com/hasilstudi/reads1.php";
    ArrayList<HashMap<String, String>> JsonList;
    ListView listView;
    TextView txtNPMS1;
    String npm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester1);

        JsonList = new ArrayList<>();
        txtNPMS1 = (TextView) findViewById(R.id.npmS1);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("npmS1");
        txtNPMS1.setText(npm);
        listView = (ListView) findViewById(R.id.listViewS1);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response;




                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray a = jsonObject.getJSONArray("result");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject c = a.getJSONObject(i);
                        String kodeS1 = c.getString("kodeS1");
                        String namaS1 = c.getString("namaS1");
                        String sksS1 = c.getString("sksS1");
                        String nilaiAngkaS1 = c.getString("nilaiAngkaS1");
                        String nilaiHurufS1 = c.getString("nilaiHurufS1");
                        String totalNilaiS1 = c.getString("totalNilaiS1");

                        final HashMap<String, String> resultx = new HashMap<>();

                        resultx.put("kodeS1", kodeS1);
                        resultx.put("namaS1", namaS1);
                        resultx.put("sksS1", sksS1);
                        resultx.put("nilaiAngkaS1", nilaiAngkaS1);
                        resultx.put("nilaiHurufS1", nilaiHurufS1);
                        resultx.put("totalNilaiS1", totalNilaiS1);

                        JsonList.add(resultx);

                        ListAdapter adapter = new SimpleAdapter(

                                semester1.this, JsonList, R.layout.listview_hasilstudismt1,
                                new String[]{"kodeS1","namaS1","sksS1","nilaiAngkaS1","nilaiHurufS1","totalNilaiS1"},
                                new int[]{R.id.txtKodeS1,R.id.txtNamaS1,R.id.txtSKSS1
                                        ,R.id.txtNilaiAngkaS1,R.id.txtNilaiHurufS1,R.id.txtTotalNilaiS1}
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

        RequestQueue requestQueue = Volley.newRequestQueue(semester1.this);
        requestQueue.add(request);
    }
}