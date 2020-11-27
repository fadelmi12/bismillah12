package com.example.bismillah;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class ActivityPemahaman extends AppCompatActivity {
    String url = "https://pajuts.000webhostapp.com/pemahaman/editpemahaman.php";
    String url1 = "https://pajuts.000webhostapp.com/pemahaman/readpemahaman.php";
    RadioGroup rg1,rg2,rg3,rg4,rg5,rg6,rg7,rg8,rg9,rg10;
    RadioButton rb1,rb2,rb3,rb4,rb5,rb6,rb7,rb8,rb9,rb10;
    Button btnSubmitPengumuman;
    String status="Terisi";
    TextView txtNpmVisiMisi,txtstatus;
    String npm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemahaman);

        txtNpmVisiMisi = (TextView) findViewById(R.id.npmVisiMisi);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("NPM_VisiMisi");
        txtNpmVisiMisi.setText(npm);

        rg1 = (RadioGroup) findViewById(R.id.rg1);
        rg2 = (RadioGroup) findViewById(R.id.rg2);
        rg3 = (RadioGroup) findViewById(R.id.rg3);
        rg4 = (RadioGroup) findViewById(R.id.rg4);
        rg5 = (RadioGroup) findViewById(R.id.rg5);
        rg6 = (RadioGroup) findViewById(R.id.rg6);
        rg7 = (RadioGroup) findViewById(R.id.rg7);
        rg8 = (RadioGroup) findViewById(R.id.rg8);
        rg9 = (RadioGroup) findViewById(R.id.rg9);
        rg10 = (RadioGroup) findViewById(R.id.rg10);
        txtstatus = (TextView) findViewById(R.id.statusPemahaman);

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
                        txtNpmVisiMisi.setText(npm);
                        txtstatus.setText(status);
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

        RequestQueue requestQueue = Volley.newRequestQueue(ActivityPemahaman.this);
        requestQueue.add(request);

        btnSubmitPengumuman = (Button) findViewById(R.id.btnsubmitPengumuman);
        btnSubmitPengumuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId1 = rg1.getCheckedRadioButtonId();
                int selectedId2 = rg2.getCheckedRadioButtonId();
                int selectedId3 = rg3.getCheckedRadioButtonId();
                int selectedId4 = rg4.getCheckedRadioButtonId();
                int selectedId5 = rg5.getCheckedRadioButtonId();
                int selectedId6 = rg6.getCheckedRadioButtonId();
                int selectedId7 = rg7.getCheckedRadioButtonId();
                int selectedId8 = rg8.getCheckedRadioButtonId();
                int selectedId9 = rg9.getCheckedRadioButtonId();
                int selectedId10 = rg10.getCheckedRadioButtonId();
                rb1 = (RadioButton) findViewById(selectedId1);
                rb2 = (RadioButton) findViewById(selectedId2);
                rb3 = (RadioButton) findViewById(selectedId3);
                rb4 = (RadioButton) findViewById(selectedId4);
                rb5 = (RadioButton) findViewById(selectedId5);
                rb6 = (RadioButton) findViewById(selectedId6);
                rb7 = (RadioButton) findViewById(selectedId7);
                rb8 = (RadioButton) findViewById(selectedId8);
                rb9 = (RadioButton) findViewById(selectedId9);
                rb10 = (RadioButton) findViewById(selectedId10);


                rb1.getText().toString().trim();

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String npm = txtNpmVisiMisi.getText().toString();
                        String berhasil = "Terimakasih Telah Mengisi";
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        Toast.makeText(ActivityPemahaman.this, berhasil, Toast.LENGTH_SHORT).show();
                        intent.putExtra("NPM", npm);
                        startActivity(intent);
                    }
                },new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String eror="Di isi kabeh sek bro";
                        Toast.makeText(ActivityPemahaman.this, eror, Toast.LENGTH_SHORT).show();
                    }
                }

                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();

                        params.put("npm",txtNpmVisiMisi.getText().toString().trim());
                        params.put("jawaban1",rb1.getText().toString().trim());
                        params.put("jawaban2",rb2.getText().toString().trim());
                        params.put("jawaban3",rb3.getText().toString().trim());
                        params.put("jawaban4",rb4.getText().toString().trim());
                        params.put("jawaban5",rb5.getText().toString().trim());
                        params.put("jawaban6",rb6.getText().toString().trim());
                        params.put("jawaban7",rb7.getText().toString().trim());
                        params.put("jawaban8",rb8.getText().toString().trim());
                        params.put("jawaban9",rb9.getText().toString().trim());
                        params.put("jawaban10",rb10.getText().toString().trim());
                        params.put("status",status);
                        return params;

                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(ActivityPemahaman.this);
                requestQueue.add(request);
            }
        });

    }

    private void Daftar() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Konfirmasi Data");
        alertDialogBuilder
                .setMessage("\nPilihan 1 : "+rb1.getText()+
                        "\nPilihan 2 : "+rb2.getText()+
                        "\nPilihan 3 : "+rb3.getText()+
                        "\nPilihan 4 : "+rb4.getText()+
                        "\nPilihan 5 : "+rb5.getText()+
                        "\nPilihan 6 : "+rb6.getText()+
                        "\nPilihan 7 : "+rb7.getText()+
                        "\nPilihan 8 : "+rb8.getText()+
                        "\nPilihan 9 : "+rb9.getText()+
                        "\nPilihan 10 : "+rb10.getText())
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ActivityPemahaman.this, ActivityPemahaman.class);
                        startActivity(intent);
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}