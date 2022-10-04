package com.penpal.penpal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText email,pWord;
    Button loginBtn;
    TextView dontHaveAccount;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Login();

    }
   public void Login(){
       email=(EditText)findViewById(R.id.username);
        pWord=(EditText)findViewById(R.id.password);

        loginBtn= (Button)findViewById(R.id.login);
        dontHaveAccount=(TextView)findViewById(R.id.donthaveanaccount);

        DB= new DBHelper(this);



        //admin and admin

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user= email.getText().toString();
                String pass= pWord.getText().toString();
                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(Login.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkUserPass = DB.checkUsernamePassword(user, pass);
                    if(checkUserPass==true) {
                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Login.this,"Invalid credentials",Toast.LENGTH_SHORT).show();
                    }
                }
                handleLogin();

            }
        });
        dontHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });
    }

    public void handleLogin(){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email.getText().toString(), pWord.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Login.this,"Login successful!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Login.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}