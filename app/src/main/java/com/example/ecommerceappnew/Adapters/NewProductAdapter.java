package com.example.ecommerceappnew.Adapters;

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
import com.example.ecommerceappnew.R;

import java.util.List;

public class NewProductAdapter extends RecyclerView.Adapter<NewProductAdapter.ViewHolder> {
    private Context context ;
    private List<NewProductModel> list;
    public NewProductAdapter(Context context, List<NewProductModel> list) {
        this.context=context;
        this.list=list;
    }
    public Context getContext() {

        return context;
    }

    public void setContext(Context context) {

        this.context = context;
    }

    public List<NewProductModel> getList() {

        return list;
    }
    public void setList(List<NewProductModel> list) {
        this.list = list;
    }
    @NonNull
    @Override
    public NewProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.new_products,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull NewProductAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.milk_image);
        holder.new_product_name.setText(list.get(position).getName());
        holder.dollar.setText(String.valueOf(list.get(position).getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailedActivity.class);
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
        ImageView milk_image;
        TextView new_product_name,dollar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            milk_image = itemView.findViewById(R.id.milk_image);
            new_product_name = itemView.findViewById(R.id.new_product_name);
            dollar = itemView.findViewById(R.id.dollar1);
        }
    }
}
