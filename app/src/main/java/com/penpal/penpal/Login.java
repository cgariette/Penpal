package com.penpal.penpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Login();

    }
    void Login(){
        EditText uName=(EditText)findViewById(R.id.username);
        EditText pWord=(EditText)findViewById(R.id.password);

        Button loginbtn= (Button)findViewById(R.id.login);



        //admin and admin

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username="admin", password="admin";
                if(uName.getText().toString().equals(username)
                        && pWord.getText().toString().equals(password))
                {
                    Toast.makeText(Login.this,"LOGIN SUCCESSFUL!!!",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Login.this,Home.class);
                    startActivity(intent);
                }
                else
                {
                    //incorrect
                    Toast.makeText(Login.this,"LOGIN FAILED!!!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void goToSignup(View v){

    }



}