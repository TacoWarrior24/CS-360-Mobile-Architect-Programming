package com.example.inventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.edit_text_username);
        password = (EditText) findViewById(R.id.edit_text_password);
        loginButton = (Button) findViewById(R.id.loginButton);

        //Username = admin & Password = admin1

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //As of now credentials are hard codded, in the future these should be encrypted and called from a database of logins
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin1")){
                    Toast.makeText(LoginScreen.this, "LOGIN SUCCESSFULL", Toast.LENGTH_SHORT).show();
                    //Launch Inventory Screen (Main Screen)
                    Intent intent = new Intent(LoginScreen.this, InventoryScreen.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginScreen.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}