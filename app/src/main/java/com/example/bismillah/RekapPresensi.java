package com.example.bismillah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class RekapPresensi extends AppCompatActivity {
    String npm;
    TextView txtNPM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rekap_presensi);

        txtNPM = (TextView) findViewById(R.id.NPM3);
        Bundle extras = getIntent().getExtras();
        npm = extras.getString("NPM_rekapPresensi");
        txtNPM.setText(npm);
        Button btnback = (Button) findViewById(R.id.btn_backdtdr);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RekapPresensi.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}