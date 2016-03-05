package com.mayank.relation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class LoginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClick1(View view)
    {
        EditText editText1= (EditText)findViewById(R.id.editText);
        EditText editText2=(EditText)findViewById(R.id.editText2);
        String email=editText1.getText().toString();
        String password=editText2.getText().toString();

        Firebase.setAndroidContext(getApplicationContext());
        Firebase ref = new Firebase("https://relationalmanac1.firebaseio.com/");
        ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Intent g = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(g);
                finish();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Toast.makeText(LoginActivity.this, "Wrong Email or password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClick3(View view)
    {
        Intent g=new Intent(this,SignUp.class);
        startActivity(g);
        finish();
    }
}