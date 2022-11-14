package com.example.ttcsc;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.util.ArrayList;

public class ExecRecyclerAdapter extends RecyclerView.Adapter<ExecRecyclerAdapter.myViewHolder> {

    private Context context;
    private ArrayList<BusStop> busStopList;

    public ExecRecyclerAdapter(ArrayList<BusStop> busStopList, Context context){

        this.busStopList = busStopList;
        this.context = context;

    }



    public class myViewHolder extends RecyclerView.ViewHolder{

        private TextView bustxt;
        private TextView stoptxt;
        private TextView timetxt;
        private ExecRecyclerAdapter.myViewHolder adapter;

        public myViewHolder(final View view, ExecRecyclerAdapter adapter) {
            super(view);
            bustxt = view.findViewById(R.id.Exec_TV_BusName);
            stoptxt = view.findViewById(R.id.Exec_TV_StopName);
            timetxt = view.findViewById(R.id.Exec_TV_Time);

        }
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tripexec, parent, false);
        return new myViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        String busName = busStopList.get(position).busID;
        holder.bustxt.setText(busName);
        String stopName = busStopList.get(position).stopID;
        holder.stoptxt.setText(stopName);
        System.out.println("DONEEEE1");
        if(busStopList.get(position).direction == true){
            holder.itemView.setBackgroundColor(Color.parseColor("#d2fac6"));}
        else{
            holder.itemView.setBackgroundColor(Color.parseColor("#e7b6bc"));
        }
        try {
            String timeDetails = busStopList.get(position).displayTime();
            holder.timetxt.setText(timeDetails);
            System.out.println("DONEEEE2");
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return busStopList.size();
    }
}
