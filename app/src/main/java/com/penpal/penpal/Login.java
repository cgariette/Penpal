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

        Button loginBtn= (Button)findViewById(R.id.login);
        TextView dontHaveAccount=(TextView)findViewById(R.id.donthaveanaccount);
        DBHelper DB;
        DB= new DBHelper(this);



        //admin and admin

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user= uName.getText().toString();
                String pass= pWord.getText().toString();
                if (user.equals("") || pass.equals(""))
                    Toast.makeText(Login.this,"Please enter all fields",Toast.LENGTH_SHORT).show();
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

    public void goToSignup(View v){

    }



}