package com.example.chodry.pen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView tvemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvemail = (TextView) findViewById(R.id.emailview);
        tvemail.setText(getIntent().getExtras().getString("Email"));
    }
}
