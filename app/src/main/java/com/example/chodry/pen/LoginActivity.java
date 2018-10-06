package com.example.chodry.pen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText loemail;
    EditText lopassword;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loemail = (EditText) findViewById(R.id.emaillogin);
        lopassword = (EditText) findViewById(R.id.passwordlogin);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void btnlo(View view) {

        final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "Please wait....",
                "Processing...",true);
        String txemail = loemail.getText().toString();
        String txpassword = lopassword.getText().toString();

        (firebaseAuth.signInWithEmailAndPassword(txemail, txpassword)).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Signing in Successfull", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(LoginActivity.this, ProfileActivity.class);
                    i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                    startActivity(i);

                }else {
                    Log.e("Error", task.getException().toString());
                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                }

            }
        });
    }
}
