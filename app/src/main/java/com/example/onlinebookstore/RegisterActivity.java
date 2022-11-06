package com.example.onlinebookstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    EditText username;
    EditText password;

    Button loginAgain;
    Button registerBtn;

    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username=findViewById(R.id.user);
        password=findViewById(R.id.pass);
        registerBtn=findViewById(R.id.Registerbtn);
        loginAgain=findViewById(R.id.loginAgain);

        fAuth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(view -> {
            createUser();
        });

        loginAgain.setOnClickListener(view ->{
            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
        });

    }
    private void createUser(){
        String user = username.getText().toString();
        String pass = password.getText().toString();

        if(TextUtils.isEmpty(user)){
            username.setError("user name can not be empty");
            username.requestFocus();
        }
        else if (TextUtils.isEmpty(pass)){
            password.setError("pass can not be empty");
            password.requestFocus();

        }else{
            fAuth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "User registered sucessfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this,MainActivity.class));

                    }else{
                        Toast.makeText(RegisterActivity.this, "User registered failed" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    
                }
            });
        }
    }


}