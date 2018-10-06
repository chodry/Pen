package com.example.chodry.pen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    EditText txtemail;
    EditText txtpassword;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        txtemail = (EditText) findViewById(R.id.email);
        txtpassword = (EditText) findViewById(R.id.password);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void btnregistreation(View view) {

        final ProgressDialog progressDialog = ProgressDialog.show(RegistrationActivity.this, "Please wait....",
                "Processing...",true);
        String temail = txtemail.getText().toString();
        String tpassword = txtpassword.getText().toString();
        (firebaseAuth.createUserWithEmailAndPassword(temail, tpassword)).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if (task.isSuccessful()){
                    Toast.makeText(RegistrationActivity.this, "Registration Successfull", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(i);
                }
                else {
                    Log.e("Error", task.getException().toString());
                    Toast.makeText(RegistrationActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                }

            }
        });
    }
}
