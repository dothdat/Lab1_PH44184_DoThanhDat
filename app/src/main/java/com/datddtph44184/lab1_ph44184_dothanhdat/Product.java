package com.datddtph44184.lab1_ph44184_dothanhdat;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.datddtph44184.lab1_ph44184_dothanhdat.Adapter.ProductAdapter;
import com.datddtph44184.lab1_ph44184_dothanhdat.DAO.CatDAO;
import com.datddtph44184.lab1_ph44184_dothanhdat.DAO.ProductDAO;
import com.datddtph44184.lab1_ph44184_dothanhdat.DTO.CatDTO;
import com.datddtph44184.lab1_ph44184_dothanhdat.DTO.ProductDTO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Product extends Fragment {

    ProductDTO productDTO;
    ProductAdapter productAdapter;
    ProductDAO productDAO;
    ArrayList<ProductDTO> list = new ArrayList<>();
    RecyclerView recyclerView;
    FloatingActionButton actionButton, actionButton2;
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
        actionButton2 = v.findViewById(R.id.btn_add_dialog);
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
        actionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddProductDialog();
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
    private void showAddProductDialog() {
        // Tạo Dialog và gắn layout
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.layout_dialog_add);
        //anh xa
        Spinner spinnerCategory = dialog.findViewById(R.id.spinnerCategory);
        EditText editTextProductName = dialog.findViewById(R.id.editTextProductName);
        EditText editTextPrice = dialog.findViewById(R.id.editPriceProductName);
        Button buttonSaveProduct = dialog.findViewById(R.id.buttonSaveProduct);
        // Thiết lập adapter cho Spinner với danh sách thể loại sản phẩm từ productDAO
        ArrayList<CatDTO> listCat = new CatDAO(getContext()).getList();
        ArrayAdapter<CatDTO> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listCat);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(spinnerAdapter);
        buttonSaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy thể loại và tên sản phẩm từ Spinner và EditText
                CatDTO selectedCategory = (CatDTO) spinnerCategory.getSelectedItem();
                String productName = editTextProductName.getText().toString().trim();
                Float price = Float.parseFloat(editTextPrice.getText().toString().trim());

                if (!productName.isEmpty()) {
                    // Thêm sản phẩm mới vào database
                    ProductDTO newProduct = new ProductDTO();
                    newProduct.setName(productName);
                    newProduct.setPrice(price);
                    newProduct.setId_cat(selectedCategory.getId());  // Giả sử ProductDTO có trường categoryId

                    // Lưu sản phẩm vào database
                    long result = productDAO.addRow(newProduct);
                    if (result > 0) {
                        Toast.makeText(getContext(), "Product saved", Toast.LENGTH_SHORT).show();
                        loadData();  // Cập nhật RecyclerView
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Failed to save product", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Please enter a product name", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }
}




