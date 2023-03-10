package com.example.ecommerceappnew.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.ecommerceappnew.Adapters.ShowAllAdapter;
import com.example.ecommerceappnew.Models.CategoryModel;
import com.example.ecommerceappnew.Models.ShowAllModel;
import com.example.ecommerceappnew.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShowAllActivity extends AppCompatActivity {
RecyclerView mRecyclerView;
ShowAllAdapter showAllAdapter;
List<ShowAllModel> showAllModellist;
FirebaseFirestore firestore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);
        getSupportActionBar().hide();

        firestore=FirebaseFirestore.getInstance();
        mRecyclerView=findViewById(R.id.show_all_rec);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        showAllModellist = new ArrayList<>();
        showAllAdapter = new ShowAllAdapter(this,showAllModellist);
        mRecyclerView.setAdapter(showAllAdapter);
        firestore.collection("ShowAll")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (DocumentSnapshot document : task.getResult().getDocuments()) {
                                ShowAllModel showAllModel = document.toObject(ShowAllModel.class);
                                showAllModellist.add(showAllModel);
                                showAllAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(ShowAllActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                               }
                    }});
    }
}