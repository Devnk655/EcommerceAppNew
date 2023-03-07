package com.example.ecommerceappnew.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ecommerceappnew.Models.NewProductModel;
import com.example.ecommerceappnew.Models.PopularProductModel;
import com.example.ecommerceappnew.Models.ShowAllModel;
import com.example.ecommerceappnew.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {
    ImageView detailedImg;
    TextView rating,name,description,price,quantity;
    Button addtoCart,buyNow;
    ImageView addItems,removeItems;
    int totalQuaninty = 1;
    int totalPrice=0;
    Toolbar toolbar;
    //New Products
    NewProductModel newProductModel = null;
    //PopularProducts
    PopularProductModel popularProductModel = null;
    //ShowAll Activity
    ShowAllModel showAllModel=null;

    FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseFirestore=FirebaseFirestore.getInstance();
        getSupportActionBar().hide();
        auth=FirebaseAuth.getInstance();
        final Object obj = getIntent().getSerializableExtra("detailed");
        if(obj instanceof NewProductModel){
            newProductModel = (NewProductModel) obj;
        }else if(obj instanceof ShowAllModel){
            showAllModel = (ShowAllModel) obj;
        }
        setContentView(R.layout.activity_detailed);
        detailedImg =findViewById(R.id.detailed_img);
        quantity=findViewById(R.id.quaninty);
        rating=findViewById(R.id.rating);
        name=findViewById(R.id.detailed_name);
        description=findViewById(R.id.detailed_description);
        price=findViewById(R.id.detailed_price);
        addtoCart=findViewById(R.id.add_to_cart);
        buyNow=findViewById(R.id.buy_now);
        addItems=findViewById(R.id.addItems);
        removeItems=findViewById(R.id.removeitems);
        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuaninty<10){
                    totalQuaninty++;
                    quantity.setText(String.valueOf(totalQuaninty));
                    if(newProductModel!=null){
                        totalPrice=newProductModel.getPrice()*totalQuaninty;
                    }
                    if(popularProductModel!=null){
                        totalPrice=popularProductModel.getPrice()*totalQuaninty;

                    }if(showAllModel!=null){
                        totalPrice=showAllModel.getPrice()*totalQuaninty;

                    }
                }
            }
        });
        removeItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuaninty<10){
                    totalQuaninty--;
                    quantity.setText(String.valueOf(totalQuaninty));
                }
            }
        });

        //New Products sending data to another activity
        if(newProductModel!=null){
            Glide.with(getApplicationContext()).load(newProductModel.getImg_url()).into(detailedImg);
            name.setText(newProductModel.getName());
            rating.setText(newProductModel.getRating());
            description.setText(newProductModel.getDescription());
            price.setText(String.valueOf(newProductModel.getPrice()));
            name.setText(newProductModel.getName());
            totalPrice=newProductModel.getPrice()*totalQuaninty;

            }

        //Popular Products sending data like New Products
        if(popularProductModel!=null){
            Glide.with(getApplicationContext()).load(popularProductModel.getImg_url()).into(detailedImg);
            name.setText(popularProductModel.getName());
            rating.setText(popularProductModel.getRating());
            description.setText(popularProductModel.getDescription());
            price.setText(String.valueOf(popularProductModel.getPrice()));
            name.setText(popularProductModel.getName());
            totalPrice=popularProductModel.getPrice()*totalQuaninty;

          }
        //ShowAll Products sending data to like New Products
        if(showAllModel!=null){
            Glide.with(getApplicationContext()).load(showAllModel.getImg_url()).into(detailedImg);
            name.setText(showAllModel.getName());
            rating.setText(showAllModel.getRating());
            description.setText(showAllModel.getDescription());
            price.setText(String.valueOf(showAllModel.getPrice()));
            name.setText(showAllModel.getName());
            totalPrice=showAllModel.getPrice()*totalQuaninty;

           }
        //BuyNow
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailedActivity.this,AddressActivity.class);
                if(newProductModel !=null){
                    intent.putExtra("item",newProductModel);
                }
                if(popularProductModel!=null){
                    intent.putExtra("item",popularProductModel);
                }
                if(showAllModel!=null){
                    intent.putExtra("item",showAllModel);
                }
                startActivity(intent);
            }
        });
        //Add to Cart
        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtoCart();
            }
            });
        }

    private void addtoCart() {
        String saveCurrentTime,saveCurrentDate;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd,yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

       //Add to Cart Section Of Code
        final HashMap<String,Object> cartMap = new HashMap<>();
        cartMap.put("productName",name.getText().toString());
        cartMap.put("productPrice",price.getText().toString());
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("totalQuaninty",quantity.getText().toString());
        cartMap.put("totalPrice",totalPrice);


        firebaseFirestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetailedActivity.this, "Added To Cart", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}