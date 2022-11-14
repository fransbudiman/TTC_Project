package com.example.ttcsc;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EditRecyclerAdapter extends RecyclerView.Adapter<EditRecyclerAdapter.myViewHolder>{

    private ArrayList<Trip> editTripList;
    private Context context;

    public EditRecyclerAdapter(ArrayList<Trip> editTripList, Context context){
        this.editTripList = editTripList;
        this.context = context;
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        private TextView nametxt;
        private TextView bustxt;
        private Button editButton;
        private Trip tripInstance;


        public myViewHolder(final View view, EditRecyclerAdapter adapter) {
            super(view);
            nametxt = view.findViewById(R.id.Edit_TV_Name);
            bustxt = view.findViewById(R.id.Edit_TV_BusList);
            editButton = view.findViewById(R.id.Edit_Button_Edit);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String simplifiedBusStop ;
                    simplifiedBusStop = tripInstance.bsaToString;
                    System.out.println("BITCHWHYYY");
                    System.out.println(tripInstance.bsaToString);
                    Intent intent = new Intent(adapter.context, BusEditScreen.class);
                    intent.putExtra("DATA_KEY", simplifiedBusStop);
                    intent.putExtra("TRIP_NAME", nametxt.getText());
                    context.startActivity(intent);

                }
            });
        }
    }

    @NonNull
    @Override
    public EditRecyclerAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_edit, parent, false);
        return new EditRecyclerAdapter.myViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull EditRecyclerAdapter.myViewHolder holder, int position) {
        String name = editTripList.get(position).getTripName();
        holder.nametxt.setText(name);
        String busList = editTripList.get(position).getBusList();
        holder.bustxt.setText(busList);
        holder.tripInstance = editTripList.get(position);
    }

    @Override
    public int getItemCount() {
        return editTripList.size();
    }
}
