
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

public class semester2 extends AppCompatActivity {

    String url = "https://pajuts.000webhostapp.com/hasilstudi/reads2.php";
    ArrayList<HashMap<String, String>> JsonList;
    ListView listView;
    TextView txtNPMS2;
    String npm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester2);

        JsonList = new ArrayList<>();
        txtNPMS2 = (TextView) findViewById(R.id.npmS2);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("npmS2");
        txtNPMS2.setText(npm);
        listView = (ListView) findViewById(R.id.listViewS2);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response;




                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray a = jsonObject.getJSONArray("result");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject c = a.getJSONObject(i);
                        String kodeS2 = c.getString("kodeS2");
                        String namaS2 = c.getString("namaS2");
                        String sksS2 = c.getString("sksS2");
                        String nilaiAngkaS2 = c.getString("nilaiAngkaS2");
                        String nilaiHurufS2 = c.getString("nilaiHurufS2");
                        String totalNilaiS2 = c.getString("totalNilaiS2");

                        final HashMap<String, String> resultx = new HashMap<>();

                        resultx.put("kodeS2", kodeS2);
                        resultx.put("namaS2", namaS2);
                        resultx.put("sksS2", sksS2);
                        resultx.put("nilaiAngkaS2", nilaiAngkaS2);
                        resultx.put("nilaiHurufS2", nilaiHurufS2);
                        resultx.put("totalNilaiS2", totalNilaiS2);

                        JsonList.add(resultx);

                        ListAdapter adapter = new SimpleAdapter(

                                semester2.this, JsonList, R.layout.listview_hasilstudismt2,
                                new String[]{"kodeS2","namaS2","sksS2","nilaiAngkaS2","nilaiHurufS2","totalNilaiS2"},
                                new int[]{R.id.txtKodeS2,R.id.txtNamaS2,R.id.txtSKSS2
                                        ,R.id.txtNilaiAngkaS2,R.id.txtNilaiHurufS2,R.id.txtTotalNilaiS2}
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

        RequestQueue requestQueue = Volley.newRequestQueue(semester2.this);
        requestQueue.add(request);
    }
}