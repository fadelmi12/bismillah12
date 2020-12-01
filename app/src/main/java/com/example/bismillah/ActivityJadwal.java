package com.example.bismillah;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class ActivityJadwal extends AppCompatActivity {

    String url1 = "https://pajuts.000webhostapp.com/jadwal/senin.php";
    String url2 = "https://pajuts.000webhostapp.com/jadwal/selasa.php";
    String url3 = "https://pajuts.000webhostapp.com/jadwal/rabu.php";
    String url4 = "https://pajuts.000webhostapp.com/jadwal/kamis.php";
    String url5 = "https://pajuts.000webhostapp.com/jadwal/jumat.php";
    ArrayList<HashMap<String, String>> JsonList1,JsonList2,JsonList3,JsonList4,JsonList5;
    ListView listView,listView1,listView2,listView3,listView4;
    TextView txtNpmJadwal;
    String npm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);
        JsonList1 = new ArrayList<>();
        JsonList2 = new ArrayList<>();
        JsonList3 = new ArrayList<>();
        JsonList4 = new ArrayList<>();
        JsonList5 = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listview_senin);
        listView1 = (ListView) findViewById(R.id.listview_selasa);
        listView2 = (ListView) findViewById(R.id.listview_rabu);
        listView3 = (ListView) findViewById(R.id.listview_kamis);
        listView4 = (ListView) findViewById(R.id.listview_jumat);

        txtNpmJadwal = (TextView) findViewById(R.id.txtNpmJadwal);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("NPM_Jadwal");


        StringRequest request = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response;

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray a = jsonObject.getJSONArray("result");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject c = a.getJSONObject(i);

                        String kodeMk = c.getString("kodeMk");
                        String namaMk = c.getString("namaMk");
                        String namaDosen = c.getString("namaDosen");
                        String jam = c.getString("jam");
                        String ruang = c.getString("ruang");

                        final HashMap<String, String> resultx = new HashMap<>();

                        resultx.put("kodeMk", kodeMk);
                        resultx.put("namaMk", namaMk);
                        resultx.put("namaDosen", namaDosen);
                        resultx.put("jam", jam);
                        resultx.put("ruang", ruang);
                        JsonList1.add(resultx);

                        ListAdapter adapter1 = new SimpleAdapter(

                                ActivityJadwal.this, JsonList1, R.layout.listview_senin,
                                new String[]{"kodeMk","namaMk","namaDosen","jam","ruang"},
                                new int[]{R.id.kodeSenin,R.id.txtNamaSenin,R.id.txtDosenSenin,
                                        R.id.txtJamSenin,R.id.txtRuangSenin}
                        );

                        listView.setAdapter(adapter1);

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
        RequestQueue requestQueue = Volley.newRequestQueue(ActivityJadwal.this);
        requestQueue.add(request);

        StringRequest request1 = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response;

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray a = jsonObject.getJSONArray("result");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject c = a.getJSONObject(i);

                        String kodeMk = c.getString("kodeMk");
                        String namaMk = c.getString("namaMk");
                        String namaDosen = c.getString("namaDosen");
                        String jam = c.getString("jam");
                        String ruang = c.getString("ruang");

                        final HashMap<String, String> resultx = new HashMap<>();

                        resultx.put("kodeMk", kodeMk);
                        resultx.put("namaMk", namaMk);
                        resultx.put("namaDosen", namaDosen);
                        resultx.put("jam", jam);
                        resultx.put("ruang", ruang);
                        JsonList2.add(resultx);

                        ListAdapter adapter2 = new SimpleAdapter(

                                ActivityJadwal.this, JsonList2, R.layout.listview_selasa,
                                new String[]{"kodeMk","namaMk","namaDosen","jam","ruang"},
                                new int[]{R.id.kodeSelasa,R.id.txtNamaSelasa,R.id.txtDosenSelasa,
                                        R.id.txtJamSelasa,R.id.txtRuangSelasa}
                        );

                        listView1.setAdapter(adapter2);

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
        RequestQueue requestQueue1 = Volley.newRequestQueue(ActivityJadwal.this);
        requestQueue1.add(request1);

        StringRequest request2 = new StringRequest(Request.Method.POST, url3, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response;

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray a = jsonObject.getJSONArray("result");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject c = a.getJSONObject(i);

                        String kodeMk = c.getString("kodeMk");
                        String namaMk = c.getString("namaMk");
                        String namaDosen = c.getString("namaDosen");
                        String jam = c.getString("jam");
                        String ruang = c.getString("ruang");

                        final HashMap<String, String> resultx = new HashMap<>();

                        resultx.put("kodeMk", kodeMk);
                        resultx.put("namaMk", namaMk);
                        resultx.put("namaDosen", namaDosen);
                        resultx.put("jam", jam);
                        resultx.put("ruang", ruang);
                        JsonList3.add(resultx);

                        ListAdapter adapter3 = new SimpleAdapter(

                                ActivityJadwal.this, JsonList3, R.layout.listview_rabu,
                                new String[]{"kodeMk","namaMk","namaDosen","jam","ruang"},
                                new int[]{R.id.kodeRabu,R.id.txtNamaRabu,R.id.txtDosenRabu,
                                        R.id.txtJamRabu,R.id.txtRuangRabu}
                        );

                        listView2.setAdapter(adapter3);

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
        RequestQueue requestQueue2 = Volley.newRequestQueue(ActivityJadwal.this);
        requestQueue2.add(request2);

        StringRequest request3 = new StringRequest(Request.Method.POST, url4, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response;

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray a = jsonObject.getJSONArray("result");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject c = a.getJSONObject(i);

                        String kodeMk = c.getString("kodeMk");
                        String namaMk = c.getString("namaMk");
                        String namaDosen = c.getString("namaDosen");
                        String jam = c.getString("jam");
                        String ruang = c.getString("ruang");

                        final HashMap<String, String> resultx = new HashMap<>();

                        resultx.put("kodeMk", kodeMk);
                        resultx.put("namaMk", namaMk);
                        resultx.put("namaDosen", namaDosen);
                        resultx.put("jam", jam);
                        resultx.put("ruang", ruang);
                        JsonList4.add(resultx);

                        ListAdapter adapter4 = new SimpleAdapter(

                                ActivityJadwal.this, JsonList4, R.layout.listview_kamis,
                                new String[]{"kodeMk","namaMk","namaDosen","jam","ruang"},
                                new int[]{R.id.kodeKamis,R.id.txtNamaKamis,R.id.txtDosenKamis,
                                        R.id.txtJamKamis,R.id.txtRuangKamis}
                        );

                        listView3.setAdapter(adapter4);

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
        RequestQueue requestQueue3 = Volley.newRequestQueue(ActivityJadwal.this);
        requestQueue3.add(request3);

        StringRequest request4 = new StringRequest(Request.Method.POST, url5, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response;

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray a = jsonObject.getJSONArray("result");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject c = a.getJSONObject(i);

                        String kodeMk = c.getString("kodeMk");
                        String namaMk = c.getString("namaMk");
                        String namaDosen = c.getString("namaDosen");
                        String jam = c.getString("jam");
                        String ruang = c.getString("ruang");

                        final HashMap<String, String> resultx = new HashMap<>();

                        resultx.put("kodeMk", kodeMk);
                        resultx.put("namaMk", namaMk);
                        resultx.put("namaDosen", namaDosen);
                        resultx.put("jam", jam);
                        resultx.put("ruang", ruang);
                        JsonList5.add(resultx);

                        ListAdapter adapter5 = new SimpleAdapter(

                                ActivityJadwal.this, JsonList5, R.layout.listview_jumat,
                                new String[]{"kodeMk","namaMk","namaDosen","jam","ruang"},
                                new int[]{R.id.kodeJumat,R.id.txtNamaJumat,R.id.txtDosenJumat,
                                        R.id.txtJamJumat,R.id.txtRuangJumat}
                        );

                        listView4.setAdapter(adapter5);

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
        RequestQueue requestQueue4 = Volley.newRequestQueue(ActivityJadwal.this);
        requestQueue4.add(request4);

    }
}