package com.example.rishabhkhanna.letsknown.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.rishabhkhanna.letsknown.R;
import com.example.rishabhkhanna.letsknown.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserCreationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;
    Button createUserButton;
    EditText emailText;
    EditText nameText;
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user_layout);
        mAuth = FirebaseAuth.getInstance();
        emailText = (EditText) findViewById(R.id.emailET);
        nameText = (EditText) findViewById(R.id.nameET);
        passwordText = (EditText) findViewById(R.id.passwordET);
        createUserButton = (Button) findViewById(R.id.signupButton);
        final View rootView = (RelativeLayout)findViewById(R.id.userCreationView);

        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    // code if user is login
                }else{
                // code if user is sign out
                }


            }
        };

        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = emailText.getText().toString();
                final String name = nameText.getText().toString();
                final String password = passwordText.getText().toString();

                if(email == null ){

                    Snackbar snackbar =  Snackbar.make(rootView ," email id cannot be empty" ,Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }

                if(name == null ){
                    Snackbar snackbar = Snackbar.make(rootView ," name cannot be empty" ,Snackbar.LENGTH_SHORT);
                    snackbar.show();

                }

                if(password == null){
                    Snackbar snackbar = Snackbar.make(rootView ," password cannot be empty" ,Snackbar.LENGTH_SHORT);
                    snackbar.show();

                }

                mAuth.createUserWithEmailAndPassword(email , password).addOnCompleteListener(UserCreationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("TAG" , "createUserWithEmail:Complete:" + task.isSuccessful());

                        if(task.isSuccessful()){


                            FirebaseDatabase databaseref = FirebaseDatabase.getInstance();
                            DatabaseReference newUserRef = databaseref.getReference("users");

                            FirebaseUser newUser =  mAuth.getCurrentUser();
                            User user = new User(name, email, newUser.getUid() , password);
                            newUserRef.push().setValue(user);
                            Intent intent = new Intent(UserCreationActivity.this , ChatPage.class);
                            startActivity(intent);

                        }else if(!task.isSuccessful()){
                            Toast.makeText(UserCreationActivity.this, "could not sign upp yet , try again later !!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });






    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListner);
    }
}
