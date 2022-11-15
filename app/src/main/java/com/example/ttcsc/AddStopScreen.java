package com.example.ttcsc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.io.File;
import java.io.FileOutputStream;

public class AddStopScreen extends AppCompatActivity {

    String busName;
    String stopName;
    Boolean direction;
    String extraData;
    String extraTripName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stop_screen);

        extraTripName = getIntent().getStringExtra("TRIP_NAME");
        extraData = getIntent().getStringExtra("DATA_KEY");
        Button doneButton = findViewById(R.id.addStop_Button_Done);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commitStop();
            }
        });

        EditText busNameText = findViewById(R.id.addStop_ET_BusName);
        busName = busNameText.getText().toString();

    }

    public void commitStop(){
        EditText busNameText = findViewById(R.id.addStop_ET_BusName);
        busName = busNameText.getText().toString();
        EditText stopNameText = findViewById(R.id.addStop_ACTV_StopName);
        stopName = stopNameText.getText().toString();
        ToggleButton directionToggle = findViewById(R.id.addStop_Tog_Direction);
        if(directionToggle.isChecked()){
            direction = true;
        }else{
            direction = false;
        }

        extraData += busName + "/" + stopName + "|" + direction.toString() + ",";
        saveChangesAddStop();
        Intent intent = new Intent(this, BusEditScreen.class);
        intent.putExtra("TRIP_NAME", extraTripName);
        intent.putExtra("DATA_KEY", extraData);
        startActivity(intent);
    }

    private void saveChangesAddStop() {
        try{
            FileOutputStream writer = new FileOutputStream(new File(getApplicationContext().getFilesDir(), extraTripName+".txt"));
            writer.write(extraData.getBytes());
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error writing to file");
        }

    }

}