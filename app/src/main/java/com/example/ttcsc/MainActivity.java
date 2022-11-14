package com.example.ttcsc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static ArrayList<Trip> tripList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tripList = new ArrayList<>();
        recyclerView = findViewById(R.id.RV1);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        setUserInfo2();
        String extraData = getIntent().getStringExtra("NEWTRIP_KEY");
        if(extraData!=null){
            tripList.add(new Trip(extraData));
        }
        setAdapter();


        Button addButton = findViewById(R.id.Main_Button_Add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), NewTripEntry.class);
            startActivity(intent);

            }
        });

        Button editButton = findViewById(R.id.Main_Button_Edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TripEditScreen.class);
                startActivity(intent);
            }
        });

    }

    private void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(tripList, this);
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    //14766 for STC stn
    private void setUserInfo() {
        tripList.add(new Trip("UTSC"));
        tripList.add(new Trip("EastCourt BBQ"));
        tripList.add(new Trip("Foody World"));
        tripList.add(new Trip("GFFF"));
        tripList.add(new Trip("Kennedy STN"));
        tripList.add(new Trip("Agincourt STN"));
        tripList.get(0).addStop("95", "7694", true);
        tripList.get(0).addStop("995", "7694", true);
        tripList.get(1).addStop("38", "14766", true);
        tripList.get(1).addStop("38", "7699", false);
        tripList.get(0).addStop("95", "7699", false);
        Collections.sort(tripList);

    }

    private void setUserInfo2() {
        File file = new File(String.valueOf(getApplicationContext().getFilesDir()));
        for (File n: file.listFiles()) {
            String name = n.getName();
            name = name.replaceAll(".txt", "");
            Trip insert = new Trip(name);
            tripList.add(insert);
            Integer index = tripList.indexOf(insert);

            byte[] content = new byte[(int) n.length()];
            try {
                FileInputStream reader = new FileInputStream(n);
                reader.read(content);
                String readData = new String(content);
                tripList.get(index).addStop2(setBusStops(readData));

            } catch (IOException e) {
                e.printStackTrace();
            }

            Collections.sort(tripList);

        }


    }

    private List<BusStop> setBusStops(String readData){

        String[] listData = readData.split(",");
        ArrayList<String> arrayData = new ArrayList<String>(Arrays.asList(listData));
        List<BusStop> busStopList = new ArrayList<>();
        for(String n : arrayData){

            int index1 = n.indexOf("/");
            int index2 = n.indexOf("|");
            System.out.println("TESTTTT");
            System.out.println(n);
            boolean direction = false;
            if(n.substring(index2+1, n.length()).equals("true")){
                direction = true;
            }
            if(index1!=-1){
                busStopList.add(new BusStop(n.substring(0,index1), n.substring(index1+1, index2), direction));}
        }

        Collections.sort(busStopList);
        return busStopList;


    }




}