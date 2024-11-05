package com.datddtph44184.lab1_ph44184_dothanhdat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.datddtph44184.lab1_ph44184_dothanhdat.DAO.ProductDAO;
import com.datddtph44184.lab1_ph44184_dothanhdat.DTO.ProductDTO;

import java.util.ArrayList;

public class ThemSP extends AppCompatActivity {
    private ProductDTO productDTO;
    private ProductDAO productDAO;
    private ArrayList<ProductDTO> list;
    Button btnSub,btnCan;
    EditText edtid, edtname, edtprice, edtcate;
    int id;
    String name;
    float price;
    int id_cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_them_sp);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.themsp), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnCan = findViewById(R.id.buttonCancel);
        btnSub = findViewById(R.id.buttonSubmit);
        edtid = findViewById(R.id.editTextId);
        edtname = findViewById(R.id.editTextName);
        edtprice = findViewById(R.id.editTextPrice);
        edtcate = findViewById(R.id.editTextIdCat);
        productDTO = new ProductDTO();
        productDAO = new ProductDAO(ThemSP.this);
        if(getIntent().hasExtra("id")){
        nhanData();
        }
        btnCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThemSP.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int id = Integer.parseInt(edtid.getText().toString());
                String name = edtname.getText().toString();
                float price = Float.parseFloat(edtprice.getText().toString());
                int int_cat = Integer.parseInt(edtcate.getText().toString());
                productDTO.setId(id);
                productDTO.setName(name);
                productDTO.setPrice(price);
                productDTO.setId_cat(int_cat);

                if(getIntent().hasExtra("id")){
                   boolean isUpdate = productDAO.updateRow(productDTO);
                   if(isUpdate){
                       Toast.makeText(ThemSP.this, "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                       finish();
                   }else {
                       Toast.makeText(ThemSP.this, "Cap nhat that bai", Toast.LENGTH_SHORT).show();
                   }
                }else{
                    int isAdd = productDAO.addRow(productDTO);
                    if(isAdd>0){
                        Toast.makeText(ThemSP.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                        finish();

                    }else {
                        Toast.makeText(ThemSP.this, "Them that bai", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });

    }
    private void nhanData(){
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        name = intent.getStringExtra("name");
        price = intent.getFloatExtra("price",0);
        id_cat = intent.getIntExtra("id_cat",0);
        edtid.setText(String.valueOf(id));
        edtname.setText(name);
        edtprice.setText(String.valueOf(price));
        edtcate.setText(String.valueOf(id_cat));

//        intent.getStringExtra("id");
//        intent.getStringExtra("name");
//        intent.getStringExtra("price");
//        intent.getStringExtra("id_cat");


    }
}