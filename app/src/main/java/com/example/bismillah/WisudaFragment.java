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
 * Use the {@link WisudaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WisudaFragment extends Fragment {
    TextView txtNpmWisuda;
    Button btnOrganisasi, btnBahasa, btnPrestasi;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WisudaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WisudaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WisudaFragment newInstance(String param1, String param2) {
        WisudaFragment fragment = new WisudaFragment();
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

        View view = inflater.inflate(R.layout.fragment_wisuda, container, false);
        txtNpmWisuda = (TextView) view.findViewById(R.id.txtNpmWisuda);
        final String getArgument = getArguments().getString("npm");
        txtNpmWisuda.setText(getArgument);

        btnOrganisasi = (Button) view.findViewById(R.id.btnOrganisasi);
        btnOrganisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtNpmWisuda.getText().toString();
                Intent intent = new Intent(getActivity(), ActivityOrganisasi.class);
                intent.putExtra("NPM_Organisasi", npm);
                startActivity(intent);
            }
        });

        btnBahasa = (Button) view.findViewById(R.id.btnBahasa);
        btnBahasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtNpmWisuda.getText().toString();
                Intent intent = new Intent(getActivity(), ActivityBahasa.class);
                intent.putExtra("NPM_Bahasa", npm);
                startActivity(intent);
            }
        });


        btnPrestasi = (Button) view.findViewById(R.id.btnPrestasi);
        btnPrestasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = txtNpmWisuda.getText().toString();
                Intent intent = new Intent(getActivity(), ActivityPrestasi.class);
                intent.putExtra("NPM_Prestasi", npm);
                startActivity(intent);
            }
        });
        return view;
    }
}