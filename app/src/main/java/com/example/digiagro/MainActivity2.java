package com.example.digiagro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {


    private ImageView btn1;
    private TextView btn2;
    private TextInputLayout textInput1, textInput2, textInput3, textInput4;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        textInput1 = findViewById(R.id.textInput1);
        textInput2 = findViewById(R.id.textInput2);
        textInput3 = findViewById(R.id.textInput3);
        textInput4 = findViewById(R.id.textInput4);
        if (mAuth.getCurrentUser() != null){
            Intent i = new Intent(MainActivity2.this, login.class);
            startActivity(i);
            finish();
        }
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = textInput1.getEditText().getText().toString();
                String ph = textInput2.getEditText().getText().toString();
                String em = textInput3.getEditText().getText().toString();
                String pass = textInput4.getEditText().getText().toString();
                if (TextUtils.isEmpty(name)){
                    textInput1.setError("Enter your Name");
                    return;
                }
                if (TextUtils.isEmpty(ph)){
                    textInput2.setError("Enter your Phone number");
                    return;
                }
                if (TextUtils.isEmpty(em)){
                    textInput3.setError("Enter your Email");
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    textInput4.setError("It can't be empty");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
//                else {
//                    Toast.makeText(MainActivity2.this, "Account created Successfully" , Toast.LENGTH_LONG).show();
//
//                }


                mAuth.createUserWithEmailAndPassword(em,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity2.this, "User Added", Toast.LENGTH_SHORT).show();
                            VlidatePhoneNumber(name, ph, pass,em);

                        } else {
                            Toast.makeText(MainActivity2.this, "Authentication failed..."+ task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity2.this, login.class);
                startActivity(i);
            }
        });
    }
    private void VlidatePhoneNumber(String name, String phone, String password, String email) {
        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();
        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).exists())){

                    HashMap<String,Object> userhashmap = new HashMap<>();
                    userhashmap.put("Phone", phone);
                    userhashmap.put("Name", name);
                    userhashmap.put("Password", password);
                    userhashmap.put("Email", email);

                    Rootref.child("Users").child(FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getUid() )
                            .updateChildren(userhashmap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(MainActivity2.this, "Account Created", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(MainActivity2.this, login.class);
                                startActivity(i);
                            }
                            else{
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(MainActivity2.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
//                else
//                {
//                    Toast.makeText(MainActivity2.this, "This " + id + " already Exist", Toast.LENGTH_SHORT).show();
//                    progressBar.setVisibility(View.GONE);
//                    Toast.makeText(MainActivity2.this, "Try Using another ID", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}