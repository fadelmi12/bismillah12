package com.example.bismillah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextClock;
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

import java.util.HashMap;
import java.util.Map;

public class ActivityEvaluasiDosen2 extends AppCompatActivity {
    String url = "https://pajuts.000webhostapp.com/dosen/editdosen.php";
    String url1 = "https://pajuts.000webhostapp.com/dosen/readkuesioner.php";
    TextView tvstatus;
    TextView txtNpmEval2,txtId2;
    Button btnSubmitEval;
    EditText txtKesan, txtPesan;
    RadioGroup Rg1_1, Rg1_2, Rg1_3, Rg1_4, Rg2_1, Rg2_2, Rg2_3, Rg2_4, Rg3_1, Rg3_2, Rg3_3, Rg3_4;
    RadioButton Rb1_1, Rb1_2, Rb1_3, Rb1_4, Rb2_1, Rb2_2, Rb2_3, Rb2_4, Rb3_1, Rb3_2, Rb3_3, Rb3_4;
    String status="Terisi";
    String npm,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluasi_dosen2);

        txtNpmEval2 = (TextView) findViewById(R.id.npmEval2);
        tvstatus = (TextView) findViewById(R.id.statusEval);
        txtId2 = (TextView) findViewById(R.id.idMk2);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("npmEval2");
        id = extras.getString("id");
        txtNpmEval2.setText(npm);
        txtId2.setText(id);

        Rg1_1 = (RadioGroup) findViewById(R.id.R1_1);
        Rg1_2 = (RadioGroup) findViewById(R.id.R1_2);
        Rg1_3 = (RadioGroup) findViewById(R.id.R1_3);
        Rg1_4 = (RadioGroup) findViewById(R.id.R1_4);
        Rg2_1 = (RadioGroup) findViewById(R.id.R2_1);
        Rg2_2 = (RadioGroup) findViewById(R.id.R2_2);
        Rg2_3 = (RadioGroup) findViewById(R.id.R2_3);
        Rg2_4 = (RadioGroup) findViewById(R.id.R2_4);
        Rg3_1 = (RadioGroup) findViewById(R.id.R3_1);
        Rg3_2 = (RadioGroup) findViewById(R.id.R3_2);
        Rg3_3 = (RadioGroup) findViewById(R.id.R3_3);
        Rg3_4 = (RadioGroup) findViewById(R.id.R3_4);
        txtKesan = (EditText) findViewById(R.id.kesan);
        txtPesan = (EditText) findViewById(R.id.pesan);

        StringRequest request = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response;
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray a = jsonObject.getJSONArray("result");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject c = a.getJSONObject(i);
                        String npm = c.getString("npm");
                        String status = c.getString("status");

                        HashMap<String, String> resultx = new HashMap<>();
                        txtNpmEval2.setText(npm);
                        tvstatus.setText(status);
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
                params.put("idMk", id);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ActivityEvaluasiDosen2.this);
        requestQueue.add(request);

        btnSubmitEval = (Button) findViewById(R.id.btnSubmitEval);
        btnSubmitEval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId1 = Rg1_1.getCheckedRadioButtonId();
                int selectedId2 = Rg1_2.getCheckedRadioButtonId();
                int selectedId3 = Rg1_3.getCheckedRadioButtonId();
                int selectedId4 = Rg1_4.getCheckedRadioButtonId();
                int selectedId5 = Rg2_1.getCheckedRadioButtonId();
                int selectedId6 = Rg2_2.getCheckedRadioButtonId();
                int selectedId7 = Rg2_3.getCheckedRadioButtonId();
                int selectedId8 = Rg2_4.getCheckedRadioButtonId();
                int selectedId9 = Rg3_1.getCheckedRadioButtonId();
                int selectedId10 = Rg3_2.getCheckedRadioButtonId();
                int selectedId11 = Rg3_3.getCheckedRadioButtonId();
                int selectedId12 = Rg3_4.getCheckedRadioButtonId();
                Rb1_1 = (RadioButton) findViewById(selectedId1);
                Rb1_2 = (RadioButton) findViewById(selectedId2);
                Rb1_3 = (RadioButton) findViewById(selectedId3);
                Rb1_4 = (RadioButton) findViewById(selectedId4);
                Rb2_1 = (RadioButton) findViewById(selectedId5);
                Rb2_2 = (RadioButton) findViewById(selectedId6);
                Rb2_3 = (RadioButton) findViewById(selectedId7);
                Rb2_4 = (RadioButton) findViewById(selectedId8);
                Rb3_1 = (RadioButton) findViewById(selectedId9);
                Rb3_2 = (RadioButton) findViewById(selectedId10);
                Rb3_3 = (RadioButton) findViewById(selectedId11);
                Rb3_4 = (RadioButton) findViewById(selectedId12);



                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String npm = txtNpmEval2.getText().toString();
                        String berhasil = "Terimakasih Telah Mengisi";
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        Toast.makeText(ActivityEvaluasiDosen2.this, berhasil, Toast.LENGTH_SHORT).show();
                        intent.putExtra("NPM", npm);
                        startActivity(intent);
                    }
                },new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String eror = "Isi Kuesioner disek broo";
                        Toast.makeText(ActivityEvaluasiDosen2.this, eror, Toast.LENGTH_SHORT).show();
                    }
                }

                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();

                        params.put("npm",txtNpmEval2.getText().toString().trim());
                        params.put("idMk",txtId2.getText().toString().trim());
                        params.put("jawaban1_1",Rb1_1.getText().toString().trim());
                        params.put("jawaban1_2",Rb1_2.getText().toString().trim());
                        params.put("jawaban1_3",Rb1_3.getText().toString().trim());
                        params.put("jawaban1_4",Rb1_4.getText().toString().trim());
                        params.put("jawaban2_1",Rb2_1.getText().toString().trim());
                        params.put("jawaban2_2",Rb2_2.getText().toString().trim());
                        params.put("jawaban2_3",Rb2_3.getText().toString().trim());
                        params.put("jawaban2_4",Rb2_4.getText().toString().trim());
                        params.put("jawaban3_1",Rb3_1.getText().toString().trim());
                        params.put("jawaban3_2",Rb3_2.getText().toString().trim());
                        params.put("jawaban3_3",Rb3_3.getText().toString().trim());
                        params.put("jawaban3_4",Rb3_4.getText().toString().trim());
                        params.put("kesan",txtKesan.getText().toString().trim());
                        params.put("pesan",txtPesan.getText().toString().trim());
                        params.put("status",status);
                        return params;

                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(ActivityEvaluasiDosen2.this);
                requestQueue.add(request);
            }
        });
    }
}