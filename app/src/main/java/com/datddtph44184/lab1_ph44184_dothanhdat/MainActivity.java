package com.datddtph44184.lab1_ph44184_dothanhdat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;


import com.datddtph44184.lab1_ph44184_dothanhdat.Adapter.CatAdapter;
import com.datddtph44184.lab1_ph44184_dothanhdat.DAO.CatDAO;
import com.datddtph44184.lab1_ph44184_dothanhdat.DTO.CatDTO;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //1. Khai báo các đối tượng
    CatDAO catDAO;
    String TAG = "zzzzzzzz";
    ListView lvCat;
    Button btnAdd, btnUpdate, btnDelete;
    EditText edCatName;
    ArrayList<CatDTO> listCat;
    CatAdapter adapter;
    CatDTO objCurrentCat = null;
    ViewPage viewPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Ánh xạ
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager2 = findViewById(R.id.pager);
        viewPage = new ViewPage(MainActivity.this);
        viewPager2.setAdapter(viewPage);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("The Loai");
                        break;
                    case 1:
                        tab.setText("Product");
                        break;
                }
            }
        }).attach();

    }

    }
