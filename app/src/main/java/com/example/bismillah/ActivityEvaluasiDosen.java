package com.example.bismillah;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityEvaluasiDosen extends AppCompatActivity {
    ListView lvDosen;
    String mNamaDosen[] = {"Susilo Veri Yulianto, S.Kom., M.T."};
    String mNamaMK[] = {"Pemrograman Berbasis Mobile"};
    String mNIDN[] = {"0025078601"};
    int images[] = {R.drawable.ic_baseline_person_24};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluasi_dosen);
        lvDosen = (ListView)findViewById(R.id.lvdosen);
        MyAdapter adapter = new MyAdapter(this, mNamaDosen, mNamaMK, mNIDN, images);
        lvDosen.setAdapter(adapter);

        lvDosen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position ==  0) {
                    Intent intent = new Intent(ActivityEvaluasiDosen.this, ActivityEvaluasiDosen2.class);
                    startActivity(intent);
                }
            }
        });
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rNamaDosen[];
        String rNamaMK[];
        String rNIDN[];
        int rImgs[];

        MyAdapter (Context c, String namaDosen[], String namaMK[], String NIDN[], int imgs[]) {
            super(c, R.layout.listview_dosen, R.id.tvNamaDosen, namaDosen);
            this.context = c;
            this.rNamaDosen = namaDosen;
            this.rNamaMK = namaMK;
            this.rNIDN = NIDN;
            this.rImgs = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.listview_dosen, parent, false);
            ImageView images = row.findViewById(R.id.fotodosen);
            TextView myNamaDosen = row.findViewById(R.id.tvNamaDosen);
            TextView myNamaMK = row.findViewById(R.id.tvNamaMK);
            TextView myNIDN = row.findViewById(R.id.tvNIDN);

            // now set our resources on views
            images.setImageResource(rImgs[position]);
            myNamaDosen.setText(rNamaDosen[position]);
            myNamaMK.setText(rNamaMK[position]);
            myNIDN.setText(rNIDN[position]);

            return row;
        }
    }
}