package com.example.onlinebookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class Cart extends AppCompatActivity {

    EditText  inputQuantity, inputPrice;
    TextView inputTotal;
    Button calTotal;

    FirebaseAuth fAuth;

    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Objects.requireNonNull(getSupportActionBar()).hide();

        fAuth = FirebaseAuth.getInstance();

        calTotal = findViewById(R.id.calTotal);
        inputQuantity = findViewById(R.id.inputQuantity);
        inputPrice = findViewById(R.id.inputPrice);
        inputTotal=findViewById(R.id.inputTotal);


      //  listView = findViewById(R.id.listView);

    }



    public void calTotal(View v) {

        Integer Quantity = Integer.valueOf(inputQuantity.getText().toString());
        Integer price = Integer.valueOf(inputPrice.getText().toString());

        String answer = String.valueOf(Quantity * price);
        inputTotal.setText(answer);
    }
}

