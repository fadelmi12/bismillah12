package com.example.bismillah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class ActivityTambahBahasa extends AppCompatActivity {

    String npm;
    Button btnSubmit;
    String snoBahasa,snamaBahasa,sPeriodeBahasa,sTahunBahasa,sSkorBahasa,sTanggalBahasa;
    TextView npmBahasa;
    String url = "https://pajuts.000webhostapp.com/bahasa/inputbahasa.php";
    EditText txtnoBahasa,txtnamaBahasa,txtperiodeBahasa,txttahunBahasa,txtskorBahasa,txttanggalBahasa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_bahasa);


        npmBahasa = (TextView) findViewById(R.id.txtNpmBahasa);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("npmBahasa");
        npmBahasa.setText(npm);

        txtnoBahasa = (EditText) findViewById(R.id.edIdNoBahasa);
        txtnamaBahasa = (EditText) findViewById(R.id.edNamaBahasa);
        txtperiodeBahasa = (EditText) findViewById(R.id.edPeriodeBahasa);
        txttahunBahasa = (EditText) findViewById(R.id.edTahunBahasa);
        txtskorBahasa = (EditText) findViewById(R.id.edSkorBahasa);
        txttanggalBahasa = (EditText) findViewById(R.id.edTanggalBahasa);

        btnSubmit = (Button) findViewById(R.id.btnSubmitBahasa);
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                snoBahasa = txtnoBahasa.getText().toString().trim();
                snamaBahasa = txtnamaBahasa.getText().toString().trim();
                sPeriodeBahasa = txtperiodeBahasa.getText().toString().trim();
                sTahunBahasa = txttahunBahasa.getText().toString().trim();
                sSkorBahasa = txtskorBahasa.getText().toString().trim();
                sTanggalBahasa = txttanggalBahasa.getText().toString().trim();

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String s = response;
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray a = jsonObject.getJSONArray("result");
                            for (int i = 0; i < a.length(); i++) {
                                JSONObject c = a.getJSONObject(i);
                                String id = c.getString("id");
                                String no = c.getString("no");
                                String npm = c.getString("npm");
                                String namaBahasa = c.getString("namaBahasa");
                                String periode = c.getString("periode");
                                String tahun = c.getString("tahun");
                                String skor = c.getString("skor");
                                String tanggal = c.getString("tanggal");

                                HashMap<String, String> resultx = new HashMap<>();

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

                        params.put("no", snoBahasa);
                        params.put("npm",npm);
                        params.put("namaBahasa", snamaBahasa);
                        params.put("periode", sPeriodeBahasa);
                        params.put("tahun", sTahunBahasa);
                        params.put("skor", sSkorBahasa);
                        params.put("tanggal", sTanggalBahasa);
                        return params;

                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(ActivityTambahBahasa.this);
                requestQueue.add(request);


                Intent intent = new Intent(ActivityTambahBahasa.this, MainActivity.class);
                intent.putExtra("NPM", npm);
                startActivity(intent);
            }
        });

    }
}