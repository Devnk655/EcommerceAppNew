package com.example.ecommerceappnew.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.ecommerceappnew.Fragments.HomeFragment;
import com.example.ecommerceappnew.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
Fragment homefragment;

FirebaseAuth auth;
    ActionBar actionBar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        homefragment = new HomeFragment();
        actionBar = getSupportActionBar();
        loadFragment(homefragment);
        ColorDrawable colorDrawable= new ColorDrawable(Color.parseColor("#0041C2"));
        actionBar.setBackgroundDrawable(colorDrawable);
    }


    // Define ColorDrawable object and parse color
    // using parseColor method
    // with color hash code as its parameter

    private void loadFragment(Fragment homefragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container,homefragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.menu_logout){
            auth.signOut();
            startActivity(new Intent(MainActivity.this,RegistartionActivity.class));
            finish();
        } else if(id==R.id.menu_my_cart){
            startActivity(new Intent(MainActivity.this,CartActivity.class));
        }
        return true;
    }
}