package com.mayank.relation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class SignUp extends Activity {

    private EditText editText4;
    private EditText editText5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void onClick4(View view)
    {
        editText4= (EditText)findViewById(R.id.editText4);
        editText5=(EditText)findViewById(R.id.editText5);
        String email=editText4.getText().toString();
        String password=editText5.getText().toString();
        Firebase.setAndroidContext(getApplicationContext());
        Firebase ref = new Firebase("https://relationalmanac1.firebaseio.com/");
        ref.createUser(email, password, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                Toast.makeText(SignUp.this, "Account Created Successfully.", Toast.LENGTH_SHORT).show();
                Intent g=new Intent(SignUp.this,LoginActivity.class);
                startActivity(g);
                finish();
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Toast.makeText(SignUp.this, "Sorry Account could not be created.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onClick5(View view)
    {
        Intent g=new Intent(this,LoginActivity.class);
        startActivity(g);
        finish();
    }

}
