package com.example.rishabhkhanna.letsknown;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rishabhkhanna.letsknown.view.LoginActivity;
import com.example.rishabhkhanna.letsknown.view.UserCreationActivity;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button signupButton;
    Button signinButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        signinButton = (Button) findViewById(R.id.takesignin);
        signupButton = (Button) findViewById(R.id.takesignup);

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this , LoginActivity.class);
                startActivity(i);

            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this , UserCreationActivity.class);
                startActivity(i);
            }
        });

    }
}
