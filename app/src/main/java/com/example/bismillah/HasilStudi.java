package com.example.bismillah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;

public class HasilStudi extends AppCompatActivity {

    TabLayout tab1;
    LinearLayout tabLayout1;
    ViewPager viewpager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_studi);

        tabLayout1 = (LinearLayout)findViewById(R.id.tablayout1);
        tab1=(TabLayout)findViewById(R.id.tab1);
        tab_adapter tab_Adapter = new tab_adapter(getSupportFragmentManager(), tab1.getTabCount());
    }

    class tab_adapter extends FragmentStatePagerAdapter {
        int jumlahTab;
        public tab_adapter(@NonNull FragmentManager fm, int jmlTab) {
            super(fm);
            this.jumlahTab=jmlTab;
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    smt1 Smt1 =new smt1();
                    return Smt1;
                case 1:
                    smt2 Smt2 =new smt2();
                    return Smt2;
                case 2:
                    smt3 Smt3 =new smt3();
                    return Smt3;
                case 3:
                    smt4 Smt4 =new smt4();
                    return Smt4;
                case 4:
                    smt5 Smt5 =new smt5();
                    return Smt5;
                case 5:
                    smt6 Smt6 =new smt6();
                    return Smt6;
            }
            return null;
        }

        @Override
        public int getCount() {
            return jumlahTab;
        }
    }

}
