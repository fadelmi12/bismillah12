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
 * Use the {@link tabKuliah#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tabKuliah extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView txtNpmT1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public tabKuliah() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tabKuliah.
     */
    // TODO: Rename and change types and number of parameters
    public static tabKuliah newInstance(String param1, String param2) {
        tabKuliah fragment = new tabKuliah();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);
        Button btnLms = (Button) view.findViewById(R.id.btnLms);
        txtNpmT1 = (TextView) view.findViewById(R.id.txtNpmT1);
        final String getArgument = getArguments().getString("npm");
        txtNpmT1.setText(getArgument);

        btnLms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), activityLms.class);
                startActivity(intent);
            }
        });
        Button btnpPengumuman = (Button) view.findViewById(R.id.btnPengumuman);
        btnpPengumuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Pengumuman.class);
                startActivity(intent);
            }
        });
        Button btndaftarUlang = (Button) view.findViewById(R.id.btndaftarulang);
        btndaftarUlang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtNpmT1.getText().toString().trim();
                Intent intent  = new Intent(getActivity(), DaftarUlang.class);
                intent.putExtra("NPM_DU", npm);
                startActivity(intent);
            }
        });

        Button btnJadwal = (Button) view.findViewById(R.id.btnJadwal);
        btnJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtNpmT1.getText().toString().trim();
                Intent intent  = new Intent(getActivity(), ActivityJadwal.class);
                intent.putExtra("NPM_Jadwal", npm);
                startActivity(intent);
            }
        });
        Button btnHS = (Button) view.findViewById(R.id.btnHasilStudiT1);
        btnHS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtNpmT1.getText().toString().trim();
                Intent intent  = new Intent(getActivity(), HasilStudi.class);
                intent.putExtra("NPM_HasilStudi", npm);
                startActivity(intent);
            }
        });

        return view;
    }
}