package com.example.ecommerceappnew.Adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceappnew.Activities.AddressActivity;
import com.example.ecommerceappnew.Models.AddressModel;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter {
    public AddressAdapter(Context applicationContext, List<AddressModel> addressModelList, AddressActivity addressActivity) {

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
