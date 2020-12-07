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

public class ActivityTambahOrganisasi extends AppCompatActivity {


    String npm;
    Button btnSubmit;
    String snoOrganisasi,snamaOrganisasi,smasukOrganisasi,skeluarOrganisasi,sjabatanOrganisasi;
    TextView npmOrganisasi;
    String url = "https://pajuts.000webhostapp.com/organisasi/inputorganisasi.php";
    EditText txtnoOrganisasi,txtnamaOrganisasi,txtmasukOrganisai,txtkeluarOrganisasi,txtjabatanOrganisasi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_organisasi);


        npmOrganisasi = (TextView) findViewById(R.id.txtNpmOrganisasi);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("npmtor");
        npmOrganisasi.setText(npm);

        txtnoOrganisasi = (EditText) findViewById(R.id.edIdNoOrganisasi);
        txtnamaOrganisasi = (EditText) findViewById(R.id.edNamaOrganisasi);
        txtmasukOrganisai = (EditText) findViewById(R.id.edMasukOrganisasi);
        txtkeluarOrganisasi = (EditText) findViewById(R.id.edKeluarOrganisasi);
        txtjabatanOrganisasi = (EditText) findViewById(R.id.edJabatanOrganisasi);

        btnSubmit = (Button) findViewById(R.id.btnSubmitOrganisasi);
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                snoOrganisasi = txtnoOrganisasi.getText().toString().trim();
                snamaOrganisasi = txtnamaOrganisasi.getText().toString().trim();
                smasukOrganisasi = txtmasukOrganisai.getText().toString().trim();
                skeluarOrganisasi = txtkeluarOrganisasi.getText().toString().trim();
                sjabatanOrganisasi = txtjabatanOrganisasi.getText().toString().trim();

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
                                String namaOrganisasi = c.getString("namaOrganisasi");
                                String masuk = c.getString("masuk");
                                String keluar = c.getString("keluar");
                                String jabatan = c.getString("jabatan");

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

                        params.put("no", snoOrganisasi);
                        params.put("npm", npm);
                        params.put("namaOrganisasi", snamaOrganisasi);
                        params.put("masuk", smasukOrganisasi);
                        params.put("keluar", skeluarOrganisasi);
                        params.put("jabatan", sjabatanOrganisasi);
                        return params;

                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(ActivityTambahOrganisasi.this);
                requestQueue.add(request);


                Intent intent = new Intent(ActivityTambahOrganisasi.this, MainActivity.class);
                intent.putExtra("NPM", npm);
                startActivity(intent);
            }
        });

    }
}