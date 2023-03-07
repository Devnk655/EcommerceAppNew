package com.example.ecommerceappnew.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.appolica.flubber.Flubber;
import com.example.ecommerceappnew.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistartionActivity extends AppCompatActivity {
   EditText edtname,edtemail,edtpassword;
   private FirebaseAuth firebaseAuth;
   TextView textlogin;
   Button btnsignup;
    SharedPreferences sharedPreferences;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registartion);
        //getSupportActionBar().hide();
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(RegistartionActivity.this,MainActivity.class));
            finish();
        }

        edtname=findViewById(R.id.edtname);
        edtemail=findViewById(R.id.edtemail);
        edtpassword=findViewById(R.id.edtpassword);
        textlogin=findViewById(R.id.txtlogin);
        sharedPreferences=getSharedPreferences("onBoarding",MODE_PRIVATE);
        boolean IsFirstTime=sharedPreferences.getBoolean("firstTime",true);
        if(IsFirstTime){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firstTime",false);
            editor.commit();
            startActivity(new Intent(RegistartionActivity.this,oNBoardActivity.class));
            finish();
        }
        btnsignup=findViewById(R.id.btnlogin);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Flubber.with()
                        .animation(Flubber.AnimationPreset.WOBBLE)
                        .repeatCount(1)
                        .duration(1000)
                        .createFor(view)
                        .start();
                String userName=edtname.getText().toString();
                String userEmail=edtemail.getText().toString();
                String userPassword=edtpassword.getText().toString();
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(RegistartionActivity.this, "Enter the name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(userEmail)){
                    Toast.makeText(RegistartionActivity.this, "Enter the email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(userPassword.length()<6){
                    Toast.makeText(RegistartionActivity.this, "Password Too Short enter minimum 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(userEmail,userPassword)
                        .addOnCompleteListener(RegistartionActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(RegistartionActivity.this, "Successfull Registered", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegistartionActivity.this,MainActivity.class));
                                }else{
                                    Toast.makeText(RegistartionActivity.this, "Registration Failed ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        textlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistartionActivity.this,LogInActivity.class));
            }
        });
    }
}