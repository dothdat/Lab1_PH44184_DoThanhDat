package com.datddtph44184.lab1_ph44184_dothanhdat.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.datddtph44184.lab1_ph44184_dothanhdat.DTO.CatDTO;
import com.datddtph44184.lab1_ph44184_dothanhdat.R;

import java.util.ArrayList;

public class CatAdapter extends BaseAdapter {
    Context context;
    ArrayList<CatDTO> listCat;

    public CatAdapter(Context context, ArrayList<CatDTO> listCat) {
        this.context = context;
        this.listCat = listCat;
    }

    @Override
    public int getCount() {
        return listCat.size();
    }

    @Override
    public Object getItem(int i) {
        return listCat.get(i).getId();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View v, ViewGroup viewGroup) {
        View row;
        if(v != null)
            row = v;
        else {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.row_list_cat,null);
        }
        // chú y dấu { }
        CatDTO objCat = listCat.get(i);
        TextView tvId = row.findViewById(R.id.tv_id);
        TextView tvName = row.findViewById(R.id.tv_name);
        tvId.setText(objCat.getId() +"");
        tvName.setText(objCat.getName());


        return row;
    }

}
