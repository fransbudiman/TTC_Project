package com.example.ttcsc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.util.ArrayList;

public class BERecyclerAdapter extends RecyclerView.Adapter<BERecyclerAdapter.myViewHolder> {

    private Context context;
    private ArrayList<BusStop> busStopList;

    public BERecyclerAdapter(ArrayList<BusStop> busStopList, Context context){

        this.busStopList = busStopList;
        this.context = context;

    }


    public class myViewHolder extends RecyclerView.ViewHolder{

        private TextView bustxt;
        private TextView stoptxt;
        //private BERecyclerAdapter.myViewHolder adapter;
        private Button removeStop;
        private int position;

        public myViewHolder(final View view, BERecyclerAdapter adapter) {
            super(view);
            bustxt = view.findViewById(R.id.BE_TV_BusName);
            stoptxt = view.findViewById(R.id.BE_TV_StopName);
            removeStop = view.findViewById(R.id.BE_Button_DelStop);
            removeStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    BusStop toRemove = new BusStop(busStopList.get(position).busID, busStopList.get(position).stopID, busStopList.get(position).direction);
                    busStopList.remove(toRemove);
                    adapter.notifyItemRemoved(position);
                }
            });


        }
    }




    @NonNull
    @Override
    public BERecyclerAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_be, parent, false);
        return new BERecyclerAdapter.myViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull BERecyclerAdapter.myViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String busName = busStopList.get(position).busID;
        holder.bustxt.setText(busName);
        String stopName = busStopList.get(position).stopID;
        Integer positionExtra = position;
        holder.position = position;
        holder.stoptxt.setText(stopName);
        if(busStopList.get(position).direction == true){
            holder.itemView.setBackgroundColor(Color.parseColor("#d2fac6"));}
        else{
            holder.itemView.setBackgroundColor(Color.parseColor("#e7b6bc"));
        }



    }

    @Override
    public int getItemCount() {
        return busStopList.size();
    }
}
