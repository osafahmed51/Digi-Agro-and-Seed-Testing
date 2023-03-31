package com.example.digiagro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class records extends AppCompatActivity {
    EditText c_name, amount, quantity, year;
    Button add, update, dlt, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        c_name = findViewById(R.id.name);
        amount = findViewById(R.id.email);
        quantity = findViewById(R.id.address);
        year = findViewById(R.id.bg);
        add = findViewById(R.id.add);
        update = findViewById(R.id.update);
        dlt = findViewById(R.id.dlt);
        view = findViewById(R.id.view);
        DB = new DBHelper(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = c_name.getText().toString();
                String amountText = amount.getText().toString();
                String quantityText = quantity.getText().toString();
                String yearText = year.getText().toString();
                boolean checkinsertdata = DB.insertData(nameText, amountText, quantityText, yearText);
                if (checkinsertdata == true) {
                    Toast.makeText(records.this, "New Entry is Recorded", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(records.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = c_name.getText().toString();
                String amountText = amount.getText().toString();
                String quantityText = quantity.getText().toString();
                String yearText = year.getText().toString();
                boolean checkupdatedata = DB.updateData(nameText, amountText, quantityText, yearText);
                if (checkupdatedata == true) {
                    Toast.makeText(records.this, "Entry is Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(records.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = c_name.getText().toString();
                boolean checkdltdata = DB.delete(nameText);
                if (checkdltdata == true) {
                    Toast.makeText(records.this, "Entry is Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(records.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(records.this, "No Entry Exist", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Crop Name: " + res.getString(0) + "\n");
                    buffer.append("Quantity: " + res.getString(1) + "\n");
                    buffer.append("Amount of Sold: " + res.getString(2) + "\n");
                    buffer.append("year: " + res.getString(3) + "\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(records.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });


    }
}