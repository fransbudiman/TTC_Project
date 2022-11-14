package com.example.ttcsc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BusEditScreen extends AppCompatActivity {

    private ArrayList<BusStop> busStopList;
    private RecyclerView BERecyclerView;
    public String extraData;
    public String extraTripName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_edit_screen);

        busStopList = new ArrayList<>();
        BERecyclerView = findViewById(R.id.RV_BusEdit);
        BERecyclerView.addItemDecoration(new DividerItemDecoration(BERecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        extraData = getIntent().getStringExtra("DATA_KEY");
        extraTripName = getIntent().getStringExtra("TRIP_NAME");
        System.out.println(extraData);
        System.out.println(extraTripName);
        if (extraData == null) {
            extraData = "filler";
        }
        setUserInfo(extraData);
        setAdapter();
        View doneButton = findViewById(R.id.BE_Button_Done);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickDone();

            }
        });

        View removeTripButton = findViewById(R.id.BE_Button_DelTrip);
        removeTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeTrip();
            }
        });

        View addStopButton = findViewById(R.id.BE_Button_AddStop);
        addStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStop();
            }
        });

    }

    private void saveChangesBE() {
        Context  context = this.getApplicationContext();
        try{
            FileOutputStream writer = new FileOutputStream(new File(getApplicationContext().getFilesDir(), extraTripName+".txt"));
            writer.write(bslToString(busStopList).getBytes());
            System.out.println(getApplicationContext().getFilesDir());
            Toast.makeText(getApplicationContext(), "Changes Saved", Toast.LENGTH_SHORT);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error writing to file");
        }
    }

    private void executeBusEdit() {
        String test = bslToString(this.busStopList);
        System.out.println(test);
    }


    private void setAdapter() {
        BERecyclerAdapter adapter = new BERecyclerAdapter(busStopList, this);
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(getApplicationContext());
        BERecyclerView.setLayoutManager(layoutManager);
        BERecyclerView.setItemAnimator(new DefaultItemAnimator());
        DefaultItemAnimator animator = (DefaultItemAnimator) BERecyclerView.getItemAnimator();
        animator.setSupportsChangeAnimations(false);
        BERecyclerView.setAdapter(adapter);

    }

    private void setUserInfo(String extraData){

        String[] listData = extraData.split(",");
        ArrayList<String> arrayData = new ArrayList<String>(Arrays.asList(listData));
        for(String n : arrayData){
            if(n == null){return;}
            int index1 = n.indexOf("/");
            int index2 = n.indexOf("|");
            System.out.println(n);
            boolean direction = false;
            if(n.substring(index2+1, n.length()).equals("true")){
                direction = true;
            }
            if(index1!=-1){
                busStopList.add(new BusStop(n.substring(0,index1), n.substring(index1+1, index2), direction));}
        }

        Collections.sort(busStopList);

    }

    public String bslToString(ArrayList<BusStop> busStopList){
        String result = "";
        for(BusStop n : busStopList){
            result+= n.busID + "/" + n.stopID + "|" + n.direction.toString() + ",";
        }

        return result;
    }

    public void removeTrip(){

        File toDelete = new File(getApplicationContext().getFilesDir(), extraTripName + ".txt");
        toDelete.delete();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);



    }

    public void addStop(){

        Intent intent = new Intent(this, AddStopScreen.class );
        intent.putExtra("TRIP_NAME", extraTripName);
        intent.putExtra("DATA_KEY", extraData);
        startActivity(intent);

    }

    public void clickDone(){

        executeBusEdit();
        saveChangesBE();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


}