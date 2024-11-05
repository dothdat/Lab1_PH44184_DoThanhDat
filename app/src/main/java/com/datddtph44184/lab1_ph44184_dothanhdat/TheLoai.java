package com.datddtph44184.lab1_ph44184_dothanhdat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.datddtph44184.lab1_ph44184_dothanhdat.Adapter.CatAdapter;
import com.datddtph44184.lab1_ph44184_dothanhdat.DAO.CatDAO;
import com.datddtph44184.lab1_ph44184_dothanhdat.DTO.CatDTO;

import java.util.ArrayList;


public class TheLoai extends Fragment {

    CatDAO catDAO;
    String TAG = "zzzzzzzz";
    ListView lvCat;
    Button btnAdd, btnUpdate, btnDelete;
    EditText edCatName;
    ArrayList<CatDTO> listCat;
    CatAdapter adapter;
    CatDTO objCurrentCat = null;

    public TheLoai() {
        // Required empty public constructor

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_the_loai, container, false);
        lvCat = v.findViewById(R.id.lv_cat);
        btnAdd = v.findViewById(R.id.btn_add);
        btnUpdate = v.findViewById(R.id.btn_update);
        btnDelete = v.findViewById(R.id.btn_delete);
        edCatName = v.findViewById(R.id.ed_catname);
        catDAO = new CatDAO(getContext());
        CatDTO objCat = new CatDTO();
// ghi vao CSDL
        int kq = catDAO.addRow( objCat );
        if(kq == -1){
            Log.d(TAG, "onCreate: Loi khong them duoc");
        }else{
            Log.d(TAG, "onCreate: Them thanh cong id = " + kq);
        }

        //khởi tạo list
        listCat = catDAO.getList();
        //khởi tạo Adapter
        adapter = new CatAdapter(getContext(), listCat);
        lvCat.setAdapter(adapter);
        lvCat.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                // ghi log để xem 2 tham số i, l là gì
                Log.d(TAG, "onItemLongClick: i = " + i + ", l = " + l );
                // xem log--> i là thứ tự phần tử trong arrayList, l là id trong bảng
                objCurrentCat = listCat.get(i);
                edCatName.setText( objCurrentCat.getName() ); // hiển thị lên màn hình
                return  true;


            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // có thể viết tách riêng code này thành hàm riêng
                //1. Lấy dữ liệu người dùng nhập trên edittext
                String catName = edCatName.getText().toString();
                // kiểm tra hợp lệ dữ liệu truowcs khi thêm, VD:
                if(catName.length()<3){
                    Toast.makeText(getContext(),
                            "Tên cân nhập ít nhất 3 ký tu", Toast.LENGTH_SHORT).show();
                    return; // thoat khoi hàm
                }




                CatDTO objCat = new CatDTO();
                objCat.setName( catName );
                //2. Ghi vao CSDL
                int res = catDAO.addRow(objCat); // ghi vào CSDL
                //3. Cập nhật ds
                if(res>0){ // Ghi thành công
                    listCat.clear(); // xóa hết
                    listCat.addAll( catDAO.getList());
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getContext(),
                            "Lỗi thêm, hãy kiểm tra trùng lặp...", Toast.LENGTH_SHORT).show();
                }
            }




        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1. Lấy dữ lệu nhập
                String catName = edCatName.getText().toString();
                //2. Validate ....


                //3. Gán dữ liệu vao đối tượng
                objCurrentCat.setName(catName);
                //4. Ghi vào CSDL
                boolean res = catDAO.updateRow( objCurrentCat );
                if(res){ // sửa thành cong
                    listCat.clear();
                    listCat.addAll( catDAO.getList());
                    adapter.notifyDataSetChanged();
                    objCurrentCat = null;// xóa dữ liệu tạm
                    edCatName.setText(""); // xóa trống ô text
                }else{
                    Toast.makeText(getContext(),
                            "Lỗi không sửa được, có thể trùng dữ liệu...", Toast.LENGTH_SHORT).show();
                }


            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(objCurrentCat!= null){
                    boolean res = catDAO.deleteRow(objCurrentCat);
                    if(res){
                        listCat.clear();
                        listCat.addAll( catDAO.getList());
                        adapter.notifyDataSetChanged();
                        objCurrentCat = null;// xóa dữ liệu tạm
                        edCatName.setText("");
                    }else{
                        Toast.makeText(getContext(), "Lỗi xóa", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getContext(),
                            "Bấm giữ dòng để chọn bản ghi cần xóa", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Inflate the layout for this fragment
        return v;
    }
}