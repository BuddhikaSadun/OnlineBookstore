package com.example.onlinebookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class Payment extends AppCompatActivity {

    Toolbar toolbar;
    TextView subTotal,discount,total;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        fAuth = FirebaseAuth.getInstance();

        toolbar = findViewById(R.id.payment_toolbar);

        subTotal=findViewById(R.id.sub_total);
        discount=findViewById(R.id.discount);
        total = findViewById(R.id.total_amt);



    }
}