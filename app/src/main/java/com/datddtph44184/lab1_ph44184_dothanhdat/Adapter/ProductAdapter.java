package com.datddtph44184.lab1_ph44184_dothanhdat.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.datddtph44184.lab1_ph44184_dothanhdat.DAO.ProductDAO;
import com.datddtph44184.lab1_ph44184_dothanhdat.DTO.ProductDTO;
import com.datddtph44184.lab1_ph44184_dothanhdat.Product;
import com.datddtph44184.lab1_ph44184_dothanhdat.R;
import com.datddtph44184.lab1_ph44184_dothanhdat.ThemSP;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    private ArrayList<ProductDTO> list ;
    private Context context;
    private ProductDAO productDAO;


    public ProductAdapter(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public ProductAdapter(ArrayList<ProductDTO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductDTO productDTO = list.get(position);
        holder.tvidproduct.setText("ID: " + productDTO.getId()+ "");
        holder.tvnameproduct.setText("Name: " +productDTO.getName());
        holder.tvpriceproduct.setText("Price: "+productDTO.getPrice()+ "");
        holder.tvid_catproduct.setText("Category: "+ productDTO.getTenTheLoai()+"");
        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy vị trí của item trong RecyclerView
                int position = holder.getAdapterPosition();

                // Kiểm tra vị trí hợp lệ
                if (position != RecyclerView.NO_POSITION) {
                    // Lấy id của sản phẩm muốn xóa
                    ProductDTO product = list.get(position);
                    int productId = product.getId();

                    // Tạo instance của DAO để xóa sản phẩm
                    ProductDAO productDAO = new ProductDAO(view.getContext());
                    boolean isDeleted = productDAO.deleteProduct(productId);

                    if (isDeleted) {
                        // Xóa item khỏi danh sách và thông báo cho RecyclerView
                        list.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, list.size());
                        Toast.makeText(view.getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(view.getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        holder.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ThemSP.class);
                intent.putExtra("id",productDTO.getId());
                intent.putExtra("name",productDTO.getName());
                intent.putExtra("price",productDTO.getPrice());
                intent.putExtra("id_cat",productDTO.getId_cat());

                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        if(list.size() == 0){
            return 0;
        }
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvnameproduct,tvpriceproduct,tvid_catproduct,tvidproduct;
        ImageButton btnedit,btndelete;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            tvnameproduct = itemView.findViewById(R.id.tvnameproduct);
            tvpriceproduct= itemView.findViewById(R.id.tvpriceproduct);
            tvidproduct= itemView.findViewById(R.id.tvidproduct);
            tvid_catproduct= itemView.findViewById(R.id.tvid_catproduct);
            btndelete = itemView.findViewById(R.id.btn_delete);
            btnedit = itemView.findViewById(R.id.btnedit);
        }
    }
}
