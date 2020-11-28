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

public class ActivityTambahPrestasi extends AppCompatActivity {

    String npm;
    Button btnSubmit;
    String snoPrestasi,snamaPrestasi,sTahunPrestasi,sJuaraPrestasi,sTingkatPrestasi,sJenisPrestasi;
    TextView npmPrestasi;
    String url = "https://pajuts.000webhostapp.com/prestasi/inputprestasi.php";
    EditText txtnoPrestasi,txtnamaPrestasi,txttahunPrestasi,txtjuaraPrestasi,txttingkatPrestasi,txtjenisPrestasi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_prestasi);


        npmPrestasi = (TextView) findViewById(R.id.txtNpmPrestasi);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("npmPrestasi");
        npmPrestasi.setText(npm);

        txtnoPrestasi = (EditText) findViewById(R.id.edIdNoPrestasi);
        txtnamaPrestasi = (EditText) findViewById(R.id.edNamaPrestasi);
        txttahunPrestasi = (EditText) findViewById(R.id.edTahunPrestasi);
        txtjuaraPrestasi = (EditText) findViewById(R.id.edJuaraPrestasi);
        txttingkatPrestasi = (EditText) findViewById(R.id.edTingkatPrestasi);
        txtjenisPrestasi = (EditText) findViewById(R.id.edJenisPrestasi);

        btnSubmit = (Button) findViewById(R.id.btnSubmitPrestasi);
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                snoPrestasi = txtnoPrestasi.getText().toString().trim();
                snamaPrestasi = txtnamaPrestasi.getText().toString().trim();
                sTahunPrestasi = txttahunPrestasi.getText().toString().trim();
                sJuaraPrestasi = txtjuaraPrestasi.getText().toString().trim();
                sTingkatPrestasi = txttingkatPrestasi.getText().toString().trim();
                sJenisPrestasi = txtjenisPrestasi.getText().toString().trim();

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
                                String namaPrestasi = c.getString("namaPrestasi");
                                String tahun = c.getString("tahun");
                                String juara = c.getString("juara");
                                String tingkat = c.getString("tingkat");
                                String jenis = c.getString("jenis");

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

                        params.put("no", snoPrestasi);
                        params.put("npm",npm);
                        params.put("namaPrestasi", snamaPrestasi);
                        params.put("tahun", sTahunPrestasi);
                        params.put("juara", sJuaraPrestasi);
                        params.put("tingkat", sTingkatPrestasi);
                        params.put("jenis", sJenisPrestasi);
                        return params;

                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(ActivityTambahPrestasi.this);
                requestQueue.add(request);


                Intent intent = new Intent(ActivityTambahPrestasi.this, MainActivity.class);
                intent.putExtra("NPM", npm);
                startActivity(intent);
            }
        });

    }
}