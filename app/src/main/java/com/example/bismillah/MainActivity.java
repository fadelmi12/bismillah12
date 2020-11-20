package com.example.bismillah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    LinearLayout layout_tab;
    TabLayout tab;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        layout_tab=(LinearLayout)findViewById(R.id.layout_tab);
        frameLayout=(FrameLayout)findViewById(R.id.frame_layout);
        tab=(TabLayout)findViewById(R.id.tab);
        Tab_adapter tab_adapter = new Tab_adapter(getSupportFragmentManager(), tab.getTabCount());
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(tab_adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        BottomNavigationView navigationView = findViewById(R.id.btn_nav);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.home){
                    layout_tab.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.VISIBLE);
                    HomeFragment fragment = new HomeFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, fragment);
                    fragmentTransaction.commit();
                }
                if (id == R.id.favorite){
                    layout_tab.setVisibility(View.VISIBLE);
                    frameLayout.setVisibility(View.GONE);
                    FavoriteFragment fragment = new FavoriteFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, fragment);
                    fragmentTransaction.commit();
                }
                if (id == R.id.profile){
                    layout_tab.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.VISIBLE);
                    ProfileFragment fragment = new ProfileFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, fragment);
                    fragmentTransaction.commit();
                }
                if (id == R.id.wisuda){
                    layout_tab.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.VISIBLE);
                    WisudaFragment fragment = new WisudaFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, fragment);
                    fragmentTransaction.commit();
                }
                return true;
            }
        });

        navigationView.setSelectedItemId(R.id.home);
    }
}
class Tab_adapter extends FragmentStatePagerAdapter{

    int jumlahTab;
    public Tab_adapter(@NonNull FragmentManager fm, int jmlTab) {
        super(fm);
        this.jumlahTab=jmlTab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                tab1 Tab1=new tab1();
                return Tab1;
            case 1:
                tab2 Tab2=new tab2();
                return Tab2;
        }
        return null;
    }

    @Override
    public int getCount() {
        return jumlahTab;
    }
}