package com.example.bismillah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DataDiri extends AppCompatActivity {

    String npm, out;
    TextView txtNpm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_diri);

        txtNpm = (TextView) findViewById(R.id.NPM);
        Bundle extras = getIntent().getExtras();
        npm = extras.getString("NPM_dtdiri");
        txtNpm.setText(npm);
        Button btnback = (Button) findViewById(R.id.btn_backdtdr);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataDiri.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}