package com.example.onlinebookstore;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity3 extends AppCompatActivity {

   FirebaseAuth fAuth;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ArrayList<UsersItem> usersItemArrayList;
    UsersRecyclerAdapter adapter;

    Button buttonAdd,cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

       fAuth = FirebaseAuth.getInstance();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).hide();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        usersItemArrayList = new ArrayList<>();
        buttonAdd = findViewById(R.id.buttonAdd);
        cart=findViewById(R.id.moveCart);

        //cart button
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity3.this,"successfully added to cart",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity3.this,Cart.class);
                startActivity(i);
            }
        });


        //add book botton
        buttonAdd.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                MainActivity3.ViewDialogAdd viewDialogAdd = new MainActivity3.ViewDialogAdd();
                viewDialogAdd.showDialog(MainActivity3.this);
            }
        });

        readData();
    }



    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = fAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(MainActivity3.this, MainActivity.class));
        }
    }




    private void readData() {

        databaseReference.child("USERS").orderByChild("userName").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usersItemArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    UsersItem users = dataSnapshot.getValue(UsersItem.class);
                    usersItemArrayList.add(users);
                }
                adapter = new UsersRecyclerAdapter(MainActivity3.this, usersItemArrayList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public class ViewDialogAdd {
        public void showDialog(Context context) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert_dialog_add_delete_new_user);

            EditText textName = dialog.findViewById(R.id.textName);
            EditText textEmail = dialog.findViewById(R.id.textEmail);
            EditText textPrice = dialog.findViewById(R.id.textPrice);


            Button buttonAdd = dialog.findViewById(R.id.buttonAdd);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

            buttonAdd.setText("ADD");
            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id = "user" + new Date().getTime();
                    String name = textName.getText().toString();
                    String email = textEmail.getText().toString();
                    String price = textPrice.getText().toString();

                    if (name.isEmpty() || email.isEmpty() || price.isEmpty()) {
                        Toast.makeText(context, "Please Enter All data...", Toast.LENGTH_SHORT).show();
                    } else {
                        databaseReference.child("USERS").child(id).setValue(new UsersItem(id, name, email, price));
                        Toast.makeText(context, "DONE!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            });


            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }
}



