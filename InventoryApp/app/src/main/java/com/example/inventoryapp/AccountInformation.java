package com.example.inventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccountInformation extends AppCompatActivity {
    EditText phoneNum;
    Button accept;
    Button deny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_information);

        phoneNum = (EditText) findViewById(R.id.editTextPhone);
        accept = (Button) findViewById(R.id.acceptButton);
        deny = (Button) findViewById(R.id.denyButton);



        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phoneNum.getText().toString().trim().length() > 0) {
                    String number = phoneNum.getText().toString();
                    String sms = "on";

                    //Toast.makeText(AccountInformation.this, "SMS TURNED ON", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AccountInformation.this, InventoryScreen.class);

                    intent.putExtra("keynumber",number);
                    intent.putExtra("keyaccept",sms);
                    setResult(1000,intent);
                    //Returns to Inventory Screen
                    finish();
                }
                else {
                    //If no phone number is entered do not proceed
                    Toast.makeText(AccountInformation.this, "PLEASE ENTER A PHONE NUMBER ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deny.setOnClickListener(new View.OnClickListener() {
            @Override
            //Returns to Inventory Screen
            public void onClick(View view) {
                finish();
            }
        });
    }
}