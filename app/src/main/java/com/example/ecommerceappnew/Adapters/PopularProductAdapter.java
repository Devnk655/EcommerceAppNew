package com.example.ecommerceappnew.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerceappnew.Activities.DetailedActivity;
import com.example.ecommerceappnew.Models.NewProductModel;
import com.example.ecommerceappnew.Models.PopularProductModel;
import com.example.ecommerceappnew.R;

import java.util.List;

public class PopularProductAdapter extends RecyclerView.Adapter<PopularProductAdapter.ViewHolder> {
    private Context context ;
    private List<PopularProductModel> list;
    public PopularProductAdapter(Context context, List<PopularProductModel>list) {
        this.context=context;
        this.list=list;
    }
    public Context getContext() {

        return context;
    }

    public void setContext(Context context) {

        this.context = context;
    }

    public List<PopularProductModel> getList() {

        return list;
    }
    public void setList(List<PopularProductModel> list) {
        this.list = list;
    }
    @NonNull
    @Override
    public PopularProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularProductAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.allimg);
        holder.all_product_name.setText(list.get(position).getName());
        holder.dollar1.setText(String.valueOf(list.get(position).getPrice()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detailed",list.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView allimg;
        TextView all_product_name,new_roduct_name,dollar1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            allimg=itemView.findViewById(R.id.all_img);
            all_product_name=itemView.findViewById(R.id.all_product_name);
            new_roduct_name=itemView.findViewById(R.id.new_product_name);
            dollar1=itemView.findViewById(R.id.dollar1);
        }
    }
}
