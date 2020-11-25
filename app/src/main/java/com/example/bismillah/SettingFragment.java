package com.example.bismillah;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mobapphome.mahencryptorlib.MAHEncryptor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {
    private EditText namaSetting, passwordSetting, npmSetting, namaSetting1, passwordSetting1, npmSetting1;
    String namaSetting2, passwordSetting2, npmSetting2;
    private CheckBox showPass;
    Button btnSetting;
    String url1 = "https://pajuts.000webhostapp.com/login/readloginbynpm.php";
    String url2 = "https://pajuts.000webhostapp.com/login/editlogin.php";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
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

    public static String md5(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return digest;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        npmSetting = (EditText) view.findViewById(R.id.npmSetting);
        final String getArgument = getArguments().getString("npm");
        npmSetting.setText(getArgument);


        namaSetting = (EditText) view.findViewById(R.id.namaSetting);
        npmSetting = (EditText) view.findViewById(R.id.npmSetting);
        passwordSetting = (EditText) view.findViewById(R.id.passwordSetting);
        showPass = (CheckBox) view.findViewById(R.id.showPass);


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
                        String nama = c.getString("nama");

                        HashMap<String, String> resultx = new HashMap<>();
                        npmSetting.setText(npm);
                        namaSetting.setText(nama);
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


        namaSetting1 = (EditText) view.findViewById(R.id.namaSetting);
        npmSetting1 = (EditText) view.findViewById(R.id.npmSetting);
        passwordSetting1 = (EditText) view.findViewById(R.id.passwordSetting);
        btnSetting = (Button) view.findViewById(R.id.btnSetting);

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namaSetting2 = namaSetting1.getText().toString().trim();
                npmSetting2 = npmSetting1.getText().toString().trim();
                passwordSetting2 = md5(passwordSetting1.getText().toString().trim());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String s = response;
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray a = jsonObject.getJSONArray("result");
                            for (int i = 0; i < a.length(); i++) {
                                JSONObject c = a.getJSONObject(i);
                                String npm = c.getString("npm");
                                String nama = c.getString("nama");

                                npmSetting.setText(npm);
                                namaSetting.setText(nama);
                                passwordSetting.setText("");
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

                        params.put("npm", npmSetting2);
                        params.put("nama", namaSetting2);
                        params.put("password", passwordSetting2);
                        return params;

                    }
                };


                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.detach(SettingFragment.this).attach(SettingFragment.this).commit();
                passwordSetting.setText("");
                String berhasil = "Password Berhasil Diubah";
                Toast.makeText(getActivity(), berhasil, Toast.LENGTH_SHORT).show();
                RequestQueue requestQ = Volley.newRequestQueue(getActivity());
                requestQ.add(stringRequest);
            }

        });

        showPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showPass.isChecked()){
                    passwordSetting.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    passwordSetting.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        return view;
    }
}