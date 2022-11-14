package com.example.ttcsc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class NewTripEntry extends AppCompatActivity {

    EditText inputName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip_entry);

        inputName = (EditText) findViewById(R.id.NewTrip_ET_TripName);

        Button submitButton = findViewById(R.id.NewTrip_Button_Submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewTrip();
            }
        });



    }

    public void addNewTrip(){
        String tripName = inputName.getText().toString();
        try {
            FileOutputStream writer = new FileOutputStream(new File(getApplicationContext().getFilesDir(), tripName+".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

       Intent intent = new Intent(this, MainActivity.class);
       //intent.putExtra("NEWTRIP_KEY", tripName);
       startActivity(intent);

    }


}