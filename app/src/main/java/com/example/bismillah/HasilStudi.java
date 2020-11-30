package com.example.bismillah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

public class HasilStudi extends AppCompatActivity {
    Button btnsmt1, btnsmt2, btnsmt3, btnsmt4, btnsmt5, btnsmt6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_studi);

        btnsmt1 = findViewById(R.id.smt1);
        btnsmt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HasilStudi.this, semester1.class);
                startActivity(intent);
            }
        });
        btnsmt2 = findViewById(R.id.smt2);
        btnsmt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HasilStudi.this, semester2.class);
                startActivity(intent);
            }
        });
        btnsmt3 = findViewById(R.id.smt3);
        btnsmt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HasilStudi.this, semester3.class);
                startActivity(intent);
            }
        });
        btnsmt4 = findViewById(R.id.smt4);
        btnsmt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HasilStudi.this, semester4.class);
                startActivity(intent);
            }
        });
        btnsmt5 = findViewById(R.id.smt5);
        btnsmt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HasilStudi.this, semester5.class);
                startActivity(intent);
            }
        });
        btnsmt6 = findViewById(R.id.smt6);
        btnsmt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HasilStudi.this, semester6.class);
                startActivity(intent);
            }
        });

    }

}
