package com.example.bismillah;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    public static String KEY_ACTIVITY = "msg_activity";
    TextView txtuser;



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
        String getArgument = getArguments().getString("npm");
        txtuser.setText(getArgument);

        Button btndtdiri = (Button) view.findViewById(R.id.btndtdiri);
        Button btndtortu = (Button) view.findViewById(R.id.btndtortu);
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
        return view;
    }
}