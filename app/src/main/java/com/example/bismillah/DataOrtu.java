package com.example.bismillah;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DataOrtu extends AppCompatActivity {
    String npm;
    TextView txtNPM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_ortu);

        txtNPM = (TextView) findViewById(R.id.NPM);
        Bundle extras = getIntent().getExtras();
        npm = extras.getString("NPM_dtortu");
        txtNPM.setText(npm);
    }
}