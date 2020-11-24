package com.example.bismillah;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    public static String KEY_ACTIVITY = "msg_activity";
    TextView txtuser, txtNamaHome;
    String url = "https://pajuts.000webhostapp.com/readbynpm.php";



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "params";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        txtuser = (TextView) view.findViewById(R.id.txtuser);
        txtNamaHome = (TextView) view.findViewById(R.id.txtNamaHome);
        final String getArgument = getArguments().getString("npm");
        txtuser.setText(getArgument);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response;
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray a = jsonObject.getJSONArray("result");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject c = a.getJSONObject(i);
                        String namaLengkap = c.getString("namaLengkap");

                        HashMap<String, String> resultx = new HashMap<>();
                        txtNamaHome.setText(namaLengkap);
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

                params.put("npm", getArgument);
                return params;

            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);


        Button btndtdiri = (Button) view.findViewById(R.id.btndtdiri);
        Button btndtortu = (Button) view.findViewById(R.id.btndtortu);
        Button btnRekapPresensi = (Button) view.findViewById(R.id.btn_rekappresensi);
        Button btnHasilStudi = (Button) view.findViewById(R.id.btnHasilStudi);
        btndtdiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtuser.getText().toString();
                Intent intent = new Intent(getActivity(), DataDiri.class);
                intent.putExtra("NPM_dtdiri", npm);
                startActivity(intent);
            }
        });
        btndtortu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtuser.getText().toString();
                Intent intent = new Intent(getActivity(), DataOrtu.class);
                intent.putExtra("NPM_dtortu", npm);
                startActivity(intent);
            }
        });
        btnRekapPresensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtuser.getText().toString();
                Intent intent = new Intent(getActivity(), RekapPresensi.class);
                intent.putExtra("NPM_rekapPresensi", npm);
                startActivity(intent);
            }
        });
        btnHasilStudi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HasilStudi.class);
                startActivity(intent);
            }
        });
        return view;
    }
}