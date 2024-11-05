package com.datddtph44184.lab1_ph44184_dothanhdat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.datddtph44184.lab1_ph44184_dothanhdat.Adapter.ProductAdapter;
import com.datddtph44184.lab1_ph44184_dothanhdat.DAO.ProductDAO;
import com.datddtph44184.lab1_ph44184_dothanhdat.DTO.ProductDTO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Product extends Fragment {

    ProductDTO productDTO;
    ProductAdapter productAdapter;
    ProductDAO productDAO;
    ArrayList<ProductDTO> list = new ArrayList<>();
    RecyclerView recyclerView;
    FloatingActionButton actionButton;
    public Product() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_product, container, false);
        // Inflate the layout for this fragment
        AnhXa(v);
//        loadData();
        return v;
    }
    private void AnhXa(View  v){
        recyclerView = v.findViewById(R.id.lv_cat);
        actionButton = v.findViewById(R.id.btn_themsp);
        productDTO = new ProductDTO();
        productDAO = new ProductDAO(getContext());
        list = productDAO.getList();
        productAdapter = new ProductAdapter(list, getContext());
        recyclerView.setAdapter(productAdapter);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getContext(), ThemSP.class) ;
                startActivity(intent);
            }
        });

    }
    private void loadData(){
        list.clear();
        list.addAll(productDAO.getList());
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();

    }
}