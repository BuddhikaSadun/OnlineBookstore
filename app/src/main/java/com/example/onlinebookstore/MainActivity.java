package com.example.onlinebookstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    EditText username,password;
    Button loginBtn,registerBtn;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create reference of username and password

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        loginBtn = findViewById(R.id.loginbtn);
        registerBtn = findViewById(R.id.Registerbtn);


        fAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(view -> {
            loginUser();
        });

        registerBtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));

        });

    }

        private void loginUser() {
            String user = username.getText().toString();
            String pass = password.getText().toString();

            if (TextUtils.isEmpty(user)) {
                username.setError("user name can not be empty");
                username.requestFocus();
            } else if (TextUtils.isEmpty(pass)) {
                password.setError("pass can not be empty");
                password.requestFocus();

            }else{
                fAuth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "User log in sucessfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,MainActivity3.class));

                        }else{
                            Toast.makeText(MainActivity.this, "Log failed" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }


        }

       /* loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String user=username.getText().toString();
                String pass=password.getText().toString();

                if(user.equals("sadun") && pass.equals("sadun123"))
                {
                    //correct
                    Toast.makeText(MainActivity.this,"Login successful",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,HomePage.class);
                    startActivity(intent);
                }
                //incorrect
                else {
                    Toast.makeText(MainActivity.this,"LOGIN FAILED!!!",Toast.LENGTH_SHORT).show();
                }



            }
        });*/


    }


