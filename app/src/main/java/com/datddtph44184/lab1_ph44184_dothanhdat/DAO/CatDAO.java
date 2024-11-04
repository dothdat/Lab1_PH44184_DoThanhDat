package com.datddtph44184.lab1_ph44184_dothanhdat.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.datddtph44184.lab1_ph44184_dothanhdat.DTO.CatDTO;
import com.datddtph44184.lab1_ph44184_dothanhdat.DbHelper.MyDbHelper;

import java.util.ArrayList;

public class CatDAO {
    MyDbHelper dbHelper;
    SQLiteDatabase db;

    public CatDAO(Context context){
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //ham them du lieu
    public int AddRow (CatDTO objCat){
        ContentValues v = new ContentValues();
        v.put("name", objCat.getName());
        int kq = (int)db.insert("tb_cat",null,v);
        //kq: a nếu kq>0 thì đó là ID của bản ghi mới sinh ra do cơ chế tự động tăng
        return kq;
    }
    //hàm lấy ds
    public ArrayList<CatDTO> getList(){
        ArrayList<CatDTO> listCat = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT id, name FROM tb_cat", null);
        if(c != null && c.getCount()>0){
            //lay dc du lieu
            c.moveToFirst();
            do {
                //duyet vong lap
                //thu tu cot : id la 0, name la 1
                int id = c.getInt(0);
                String name = c.getString(1);
                CatDTO objCat = new CatDTO();
                objCat.setId(id);
                objCat.setName(name);
                //cho vao list
                listCat.add(objCat);
            }while (c.moveToNext());



        }else{
            //log: khong lay dc du lieu
            Log.d("zzzzzz","CatDAO::getList: Khong lay duoc du lieu");
        }
        return  listCat;
    }
    public boolean deleteRow (CatDTO objCat){
        // tạo đk update
        String [] dieu_kien = { String.valueOf(  objCat.getId()  ) };
        long kq = db.delete("tb_cat", "id = ?", dieu_kien );
        return kq>0;
    }


}
