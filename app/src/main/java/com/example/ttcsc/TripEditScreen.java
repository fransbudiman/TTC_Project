package com.example.ttcsc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class TripEditScreen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Trip> editTripList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_edit_screen);
        editTripList = new ArrayList<>();
        recyclerView = findViewById(R.id.RV3);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        if(MainActivity.tripList!= null) {
            for (Trip n : MainActivity.tripList) {
                editTripList.add(n);
            }
        }

        setAdapter();

    }

    private void setAdapter() {
        EditRecyclerAdapter adapter = new EditRecyclerAdapter(editTripList, this);
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }
}