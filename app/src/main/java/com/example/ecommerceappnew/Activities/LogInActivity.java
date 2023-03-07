package com.example.ecommerceappnew.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appolica.flubber.Flubber;
import com.example.ecommerceappnew.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {
    EditText edtemail,edtpassword;
    Button btnlogin;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        firebaseAuth=FirebaseAuth.getInstance();
        edtemail=findViewById(R.id.edtemail);
        edtpassword=findViewById(R.id.edtpassword);
        btnlogin=findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Flubber.with()
                        .animation(Flubber.AnimationPreset.WOBBLE)
                        .repeatCount(1)
                        .duration(1000)
                        .createFor(view)
                        .start();
                String userEmail=edtemail.getText().toString();
                String userPassword=edtpassword.getText().toString();
                if (TextUtils.isEmpty(userEmail)) {
                    Toast.makeText(LogInActivity.this, "Enter the email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(userPassword.length()<6){
                    Toast.makeText(LogInActivity.this, "Password Too Short enter minimum 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(userEmail,userPassword)
                        .addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(LogInActivity.this, "Login Sucessfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LogInActivity.this,MainActivity.class));
                                }
                                else{
                                    Toast.makeText(LogInActivity.this, "Error!"+task.getException(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });
    }
}