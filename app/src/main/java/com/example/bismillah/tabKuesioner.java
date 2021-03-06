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
 * Use the {@link tabKuesioner#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tabKuesioner extends Fragment {
    TextView txtNpmT2;
    Button btnVisiMisi, btnEvalDosen;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public tabKuesioner() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tabKuesioner.
     */
    // TODO: Rename and change types and number of parameters
    public static tabKuesioner newInstance(String param1, String param2) {
        tabKuesioner fragment = new tabKuesioner();
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
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);
        txtNpmT2 = (TextView) view.findViewById(R.id.txtNpmT2);
        final String getArgument = getArguments().getString("npm");
        txtNpmT2.setText(getArgument);

        btnVisiMisi = (Button) view.findViewById(R.id.btnVisiMisi);
        btnVisiMisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtNpmT2.getText().toString();
                Intent intent = new Intent(getActivity(), ActivityPemahaman.class);
                intent.putExtra("NPM_VisiMisi", npm);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        btnEvalDosen = (Button) view.findViewById(R.id.btnEvalDosen);
        btnEvalDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtNpmT2.getText().toString();
                Intent intent = new Intent(getActivity(), ActivityEvaluasiDosen.class);
                intent.putExtra("NPM_Eval", npm);
                startActivity(intent);
            }
        });
        return view;
    }
}