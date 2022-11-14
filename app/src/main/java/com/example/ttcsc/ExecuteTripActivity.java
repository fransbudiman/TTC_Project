package com.example.ttcsc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ExecuteTripActivity extends AppCompatActivity {

    private ArrayList<BusStop> busStopList;
    private RecyclerView execRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execute_trip);
        busStopList = new ArrayList<>();
        execRecyclerView = findViewById(R.id.RV_ExecTrip);
        execRecyclerView.addItemDecoration(new DividerItemDecoration(execRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String extraData = "";
        extraData = getIntent().getStringExtra("DATA_KEY");
        System.out.println(extraData);
        if(extraData == null){
            extraData = "filler";
        }
        setUserInfo(extraData);
        setAdapter();
        ImageButton refreshButton = findViewById(R.id.Refresh_ExecTrip);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeRefresh();
            }
        });

    }

    private void executeRefresh(){
        finish();
        startActivity(getIntent());
    }

    private void setAdapter() {
        ExecRecyclerAdapter adapter = new ExecRecyclerAdapter(busStopList, this);
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(getApplicationContext());
        execRecyclerView.setLayoutManager(layoutManager);
        execRecyclerView.setItemAnimator(new DefaultItemAnimator());
        execRecyclerView.setAdapter(adapter);

    }

    private void setUserInfo(String extraData){

        String[] listData = extraData.split(",");
        ArrayList<String> arrayData = new ArrayList<String>(Arrays.asList(listData));
        for(String n : arrayData){
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



}