package com.example.eduguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    Button login_button;
    TextView signup_redirect;
    EditText login_username,login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_username = findViewById(R.id.login_username);
        login_password = findViewById(R.id.login_password);

        signup_redirect = findViewById(R.id.signup_redirect);
        login_button = findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateUsername() | !validatePassword()){

                }else{
                    checkUser();
                }
            }
        });

        signup_redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });

        }

        public Boolean validateUsername(){
            String val = login_username.getText().toString();
            if(val.isEmpty()){
                login_username.setError("username cannot be empty");
                return false;
            }else {
                login_username.setError(null);
                return true;
            }
        }

        public Boolean validatePassword(){
            String val = login_password.getText().toString();
            if(val.isEmpty()){
                login_password.setError("password cannot be empty");
                return false;
            }else {
                login_password.setError(null);
                return true;
            }
        }

        public void checkUser(){
        String userUsername = login_username.getText().toString().trim();
        String userPassword = login_password.getText().toString().trim();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
            Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

            checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        login_username.setError(null);
                        String passwordFromdb = snapshot.child(userUsername).child("password").getValue(String.class);


                        if(passwordFromdb.equals(userPassword)){
                            login_username.setError(null);
                            Intent i = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(i);
                        }else{
                            login_password.setError("invalid credentials");
                            login_password.requestFocus();
                        }
                    }else{
                        login_username.setError("user does not exists");
                        login_username.requestFocus();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

}