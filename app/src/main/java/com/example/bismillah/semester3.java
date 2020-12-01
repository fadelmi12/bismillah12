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

public class semester3 extends AppCompatActivity {

    String url = "https://pajuts.000webhostapp.com/hasilstudi/reads3.php";
    ArrayList<HashMap<String, String>> JsonList;
    ListView listView;
    TextView txtNPMS3;
    String npm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester3);

        JsonList = new ArrayList<>();
        txtNPMS3 = (TextView) findViewById(R.id.npmS3);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("npmS3");
        txtNPMS3.setText(npm);
        listView = (ListView) findViewById(R.id.listViewS3);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response;




                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray a = jsonObject.getJSONArray("result");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject c = a.getJSONObject(i);
                        String kodeS3 = c.getString("kodeS3");
                        String namaS3 = c.getString("namaS3");
                        String sksS3 = c.getString("sksS3");
                        String nilaiAngkaS3 = c.getString("nilaiAngkaS3");
                        String nilaiHurufS3 = c.getString("nilaiHurufS3");
                        String totalNilaiS3 = c.getString("totalNilaiS3");

                        final HashMap<String, String> resultx = new HashMap<>();

                        resultx.put("kodeS3", kodeS3);
                        resultx.put("namaS3", namaS3);
                        resultx.put("sksS3", sksS3);
                        resultx.put("nilaiAngkaS3", nilaiAngkaS3);
                        resultx.put("nilaiHurufS3", nilaiHurufS3);
                        resultx.put("totalNilaiS3", totalNilaiS3);

                        JsonList.add(resultx);

                        ListAdapter adapter = new SimpleAdapter(

                                semester3.this, JsonList, R.layout.listview_hasilstudismt3,
                                new String[]{"kodeS3","namaS3","sksS3","nilaiAngkaS3","nilaiHurufS3","totalNilaiS3"},
                                new int[]{R.id.txtKodeS3,R.id.txtNamaS3,R.id.txtSKSS3
                                        ,R.id.txtNilaiAngkaS3,R.id.txtNilaiHurufS3,R.id.txtTotalNilaiS3}
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

        RequestQueue requestQueue = Volley.newRequestQueue(semester3.this);
        requestQueue.add(request);
    }
}