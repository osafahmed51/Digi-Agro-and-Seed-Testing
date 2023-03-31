package com.example.digiagro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.mariuszgromada.math.mxparser.*;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class calc extends AppCompatActivity {

    private Button more;
    private FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    private calcAdapter calcadapter;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);


        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Fetching Data");
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        more = findViewById(R.id.logout);
        recyclerView = findViewById(R.id.recyclerCalc);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //        Query mquery = FirebaseDatabase.getInstance().getReference("subjects").orderByChild("category").equalTo(getIntent().getStringExtra("id"));
        DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child("fertilizers");

        FirebaseRecyclerOptions<calcModel> options =
                new FirebaseRecyclerOptions.Builder<calcModel>()
                        .setQuery(myref, calcModel.class)
                        .build();
        calcadapter = new calcAdapter(getApplicationContext(), options);
        recyclerView.setAdapter(calcadapter);
        calcadapter.notifyDataSetChanged();
        progressDialog.dismiss();

//        more.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                finish();
//            }
//        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        calcadapter.startListening();


    }

}




