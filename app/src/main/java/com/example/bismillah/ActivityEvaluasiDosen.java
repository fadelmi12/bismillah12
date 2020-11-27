package com.example.bismillah;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class ActivityEvaluasiDosen extends AppCompatActivity {
    ListView lvDosen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluasi_dosen);
        lvDosen = (ListView)findViewById(R.id.lvdosen);

    }
}