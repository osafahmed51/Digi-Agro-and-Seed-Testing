package com.example.digiagro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    private ImageView btnn1;
    private TextView btnn2, btnn3;
    private TextInputLayout textInput, textInput11;
    private FirebaseAuth mAuth;
//    private TextInputLayout txt;
    private ProgressBar progressBar;
    private BottomSheetDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        btnn1 = findViewById(R.id.btnn1);
        btnn2 = findViewById(R.id.btnn2);
        btnn3 = findViewById(R.id.btnn3);
//        txt = findViewById(R.id.emailedtxt);
//        btnn4 = findViewById(R.id.sheet_btn);
        progressBar = findViewById(R.id.progressBar2);
        textInput = findViewById(R.id.textInput);
        textInput11 = findViewById(R.id.textInput11);
        if (mAuth.getCurrentUser() != null) {
            Intent i = new Intent(login.this, dashboard2.class);
            startActivity(i);
            finish();
        }
        dialog = new BottomSheetDialog(this);
//        createDialog();

        btnn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em = textInput.getEditText().getText().toString();
                String pass = textInput11.getEditText().getText().toString();
                if (TextUtils.isEmpty(em)) {
                    textInput.setError("Enter your Email");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    textInput11.setError("Enter your Password");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

//                else{
//
//                    Toast.makeText(login.this, "Successful" , Toast.LENGTH_LONG).show();
//                }
                mAuth.signInWithEmailAndPassword(em, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(login.this, "Login", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(login.this, dashboard2.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(login.this, "Authentication failed..." + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });
            }

        });
        btnn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login.this, MainActivity2.class);
                startActivity(i);
            }
        });
        btnn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText reset = new EditText(view.getContext());
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle("Rest Password");
                alert.setMessage("Enter your Email to reset your Password");
                alert.setView(reset);
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String mail = reset.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(login.this, "Rest link is send to your Email", Toast.LENGTH_SHORT).show();
                            }
                        });
                        mAuth.sendPasswordResetEmail(mail).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(login.this, "Error! " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.create().show();
            }
        });
    }
}
//        btnn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.show();
//            }
//        });
//        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//
//    }
//
//    private void createDialog() {
//        View view = getLayoutInflater().inflate(R.layout.bottom_sheet, null, false);
//
//        btnn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                String mail = txt.getEditText().getText().toString();
//                mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(login.this, "Rest link is send to your Email", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                mAuth.sendPasswordResetEmail(mail).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(login.this, "Error! " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//        dialog.setContentView(view);

//    }
//}
