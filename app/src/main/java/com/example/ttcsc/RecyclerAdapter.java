package com.example.ttcsc;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.myViewHolder> {

    private ArrayList<Trip> tripList;
    private Context context;


    public RecyclerAdapter(ArrayList<Trip> tripList, Context context){
        this.tripList = tripList;
        this.context = context;
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        private TextView nametxt;
        private TextView bustxt;
        private Button goButton;
        private Trip tripInstance;


        public myViewHolder(final View view, RecyclerAdapter adapter) {
            super(view);
            nametxt = view.findViewById(R.id.Main_TV_TripName);
            bustxt = view.findViewById(R.id.Main_TV_BusList);
            goButton = view.findViewById(R.id.LisItems_button);
            goButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String simplifiedBusStop ;
                    simplifiedBusStop = tripInstance.bsaToString;
                    System.out.println("BITCHWHYYY");
                    System.out.println(tripInstance.bsaToString);
                    Intent intent = new Intent(adapter.context, ExecuteTripActivity.class);
                    intent.putExtra("DATA_KEY", simplifiedBusStop);
                    context.startActivity(intent);

                }
            });
        }
    }
    @NonNull
    @Override
    public RecyclerAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new myViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        String name = tripList.get(position).getTripName();
        holder.nametxt.setText(name);
        String busList = tripList.get(position).getBusList();
        holder.bustxt.setText(busList);
        holder.tripInstance = tripList.get(position);

    }

    @Override
    public int getItemCount() {
        return tripList.size();
    }
}
