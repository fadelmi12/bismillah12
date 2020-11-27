package com.example.bismillah;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ActivityEvaluasiDosen2 extends AppCompatActivity {
    TextView txtNpmEval2,txtId2;
    String npm,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluasi_dosen2);

        txtNpmEval2 = (TextView) findViewById(R.id.npmEval2);
        txtId2 = (TextView) findViewById(R.id.idMk2);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("npmEval2");
        id = extras.getString("id");
        txtNpmEval2.setText(npm);
        txtId2.setText(id);
    }
}