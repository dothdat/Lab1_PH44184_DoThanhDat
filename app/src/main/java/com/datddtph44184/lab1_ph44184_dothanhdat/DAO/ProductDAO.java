package com.datddtph44184.lab1_ph44184_dothanhdat.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.datddtph44184.lab1_ph44184_dothanhdat.DTO.CatDTO;
import com.datddtph44184.lab1_ph44184_dothanhdat.DTO.ProductDTO;
import com.datddtph44184.lab1_ph44184_dothanhdat.DbHelper.MyDbHelper;

import java.util.ArrayList;

public class ProductDAO {
    MyDbHelper dbHelper;
    SQLiteDatabase db;
    ProductDTO objCurrentCat = null;
    public ProductDAO(Context context){
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //ham them du lieu
    public int addRow (ProductDTO objProduct){
        ContentValues v = new ContentValues();
        v.put("name", objProduct.getName());
        v.put("price", objProduct.getPrice());
        v.put("id_cat", objProduct.getId_cat());
        int kq = (int)db.insert("tb_product",null,v);
        //kq: a nếu kq>0 thì đó là ID của bản ghi mới sinh ra do cơ chế tự động tăng
        return kq;
    }
    //hàm lấy ds
    public ArrayList<ProductDTO> getList(){
        ArrayList<ProductDTO> listProduct = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM tb_product JOIN tb_cat ON tb_product.id_cat = tb_cat.id" , null);
        if(c != null && c.getCount()>0){
            //lay dc du lieu
            c.moveToFirst();
            do {
                //duyet vong lap
                //thu tu cot : id la 0, name la 1
                int id = c.getInt(0);
                String name = c.getString(1);
                Float price = c.getFloat(2);
                int id_cat = c.getInt(3);
                String name_cat = c.getString(5);
                ProductDTO objProduct = new ProductDTO();
                objProduct.setId(id);
                objProduct.setName(name);
                objProduct.setPrice(price);
                objProduct.setId_cat(id_cat);
                objProduct.setTenTheLoai(name_cat);
                //cho vao list
                listProduct.add(objProduct);
            }while (c.moveToNext());



        }else{
            //log: khong lay dc du lieu
            Log.d("zzzzzz","CatDAO::getList: Khong lay duoc du lieu");
        }
        return  listProduct;
    }

    public ProductDTO getOneById(int id){
        String [] params = {String.valueOf(id)};
        ProductDTO objProduct = null;
        Cursor c = db.rawQuery("SELECT * FROM tb_product WHERE id = ?" , params);
        if(c != null && c.getCount()==1){
            objProduct = new ProductDTO();//khởi tạo đối tượng
            objProduct.setId( c.getInt(0));
            objProduct.setName(c.getString(1));
            objProduct.setPrice(c.getFloat(2));
            objProduct.setId_cat(c.getInt(3));
        }
        return objProduct;
    }

    public boolean updateRow(ProductDTO objProductDTO){
        String [] dieu_kien = {String.valueOf(objProductDTO.getId())};

        ContentValues v = new ContentValues();
        v.put("name", objProductDTO.getName());
        v.put("price", objProductDTO.getPrice());
        v.put("id_cat", objProductDTO.getId_cat());
        //thực hiện lệnh cập nhật
        long kq = db.update("tb_product ", v , "id = ?", dieu_kien);
        return kq > 0 ;//nếu update thành công thì kq >0
    }
public boolean deleteProduct(int productId) {
    int result = db.delete("tb_product", "id = ?", new String[]{String.valueOf(productId)});
    return result > 0; // Trả về true nếu xóa thành công
}
}
