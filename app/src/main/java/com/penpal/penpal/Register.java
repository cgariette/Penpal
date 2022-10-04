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

public class Register extends AppCompatActivity {

    EditText username, email, password, re_password;
    Button register;
    TextView already_have_an_account;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.inputUsername);
        email = (EditText) findViewById(R.id.inputEmail);
        password = (EditText) findViewById(R.id.inputPassword);
        re_password = (EditText) findViewById(R.id.inputConfirmPassword);
        register =(Button) findViewById(R.id.btnRegister);
        already_have_an_account = (TextView) findViewById(R.id.alreadyHaveAccount);
        DB= new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user= username.getText().toString();
                String mail= email.getText().toString();
                String pass= password.getText().toString();
                String repass=re_password.getText().toString();

                if(user.equals("") || mail.equals("") || pass.equals("") || repass.equals("") )
                    Toast.makeText(Register.this,"Please fill all fields",Toast.LENGTH_SHORT).show();
                else {
                    if(pass.equals(repass)){
                        Boolean checkUser= DB.checkUsername(user);
                        if(checkUser==false){
                            Boolean insert = DB.insertData(user,mail,pass);
                            if(insert==true){
                                Toast.makeText(Register.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Login.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(Register.this,"Registered Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Register.this,"User already exists! Please sign in",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(Register.this,"Passwords not matching",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        already_have_an_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),Login.class);
                startActivity(intent);

            }
        });
    }

    private void handleRegister(){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if (task.isSuccessful()){
                  Toast.makeText(Register.this,"Signed up successfully!",Toast.LENGTH_SHORT).show();
              }
              else{
                  Toast.makeText(Register.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
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