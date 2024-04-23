package com.example.inventoryapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class InventoryScreen extends AppCompatActivity {

    LinearLayout layoutList;
    Button addItem;
    Button settingsBtn;
    int IdNum = 0;
    public String phoneNumber;
    public boolean smsOn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        layoutList = findViewById(R.id.layout_list);
        addItem = (Button) findViewById(R.id.addItem);
        settingsBtn = (Button) findViewById(R.id.setting);

        //Add items button
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addView();
            }
        });

        //Settings button
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Launch setting screen
                Intent intent = new Intent(InventoryScreen.this, AccountInformation.class);
                startActivityForResult(intent,1000);
            }
        });
    }

    //Retrieves input from settings screen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String phoneNum = data.getStringExtra("keynumber");
        String accept = data.getStringExtra("keyaccept");
        if (accept == "on") {                                                                       //NOTE: This is where the problem is, I can not get these variables to transfer
            smsOn = true;                                                                           //      for Public use. See lines 102 & 107
            phoneNumber = phoneNum;
        }
        Toast.makeText(this, "SMS Active for " + phoneNum,Toast.LENGTH_SHORT).show();
    }

    private void addView(){

        final int[] ItemQuan = {1};

        View itemView = getLayoutInflater().inflate(R.layout.item_row_add,null,false);

        TextView id = (TextView)itemView.findViewById((R.id.item_id));
        TextView quantity = (TextView)itemView.findViewById(R.id.quantity);
        EditText itemName = (EditText)itemView.findViewById(R.id.edit_item_name);
        Button increaseButton = (Button)itemView.findViewById(R.id.increaseQuantity);
        Button decreaseButton = (Button)itemView.findViewById(R.id.decreaseQuantity);
        Button deleteButton = (Button)itemView.findViewById(R.id.deleteButton);

        quantity.setText(""+ ItemQuan[0]);

        //Increment ID number
        IdNum++;
        id.setText(""+ IdNum);


        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemQuan[0]++;
                quantity.setText(""+ ItemQuan[0]);
            }
        });

        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Sends message if inventory is turning 0 or is 0 already
                if (quantity.getText().toString().equals("1")) {
                    ItemQuan[0]--;
                    quantity.setText("" + ItemQuan[0]);
                    if (smsOn){sendSMS(phoneNumber);}
                }

                else if (quantity.getText().toString().equals("0")) {
                    quantity.setText("" + ItemQuan[0]);
                    if (smsOn){sendSMS(phoneNumber);}
                }

                else{
                    ItemQuan[0]--;
                    quantity.setText("" + ItemQuan[0]);
                }
            }
        });

        //Removes View
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(itemView);
            }
        });

        layoutList.addView(itemView);
    }

    private void removeView(View view){
        layoutList.removeView(view);
    }
    //Function to send message
    //NOTE: This portion is incomplete because I couldn't get variables to read properly
    private void sendSMS(String phoneNumber){
        try{
            //SmsManager smsManager = SmsManager.getDefault();
            //smsManager.sendTextMessage(phoneNumber,null,null,null);
            Toast.makeText(this, "Message Sent " + phoneNumber,Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Message Failed to Send",Toast.LENGTH_SHORT).show();
        }
    }
}