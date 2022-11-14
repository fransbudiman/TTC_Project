package com.example.ttcsc;


import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Trip implements Comparable{

    String tripName;
    ArrayList<BusStop> busStopArray;
    String bsaToString;


    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getBusList(){

        ArrayList<String> busList = new ArrayList<String>();
        for (BusStop n : busStopArray){
            if(!busList.contains(n.busID)){
                busList.add(n.busID);
            }
        }

        return busList.toString();

    }

    public Trip(String tripName) {
        this.tripName = tripName;
        busStopArray = new ArrayList<BusStop>();
    }

    public void addBusArray(ArrayList<BusStop> busArray){
        for(BusStop n : busArray){
            busStopArray.add(n);
        }
        this.arrayToString(busArray);
    }

    public void arrayToString(ArrayList<BusStop> busStopArray){
        String bsaBuffer = "";
        for(BusStop n : busStopArray){
            bsaBuffer += n.busID;
            bsaBuffer += "/";
            bsaBuffer += n.stopID;
            bsaBuffer += "|";
            bsaBuffer += n.direction.toString();
            bsaBuffer += ",";
        }

        //bsaBuffer = bsaBuffer.substring(0, bsaBuffer.length()-1);
        bsaToString = bsaBuffer;
    }

    public void addStop(String busID, String stopID, Boolean direction) {

        BusStop obj = new BusStop(busID, stopID, direction);
        if (obj.jsonClass.docString.trim().equals("[]")) {
            System.out.println("Wrong Bus or Stop ID");
        }else {
            System.out.println(obj.jsonClass.docString);
            busStopArray.add(obj);
        }
        this.arrayToString(this.busStopArray);
    }

    public void addStop2(List<BusStop> busStopList){
        busStopArray = new ArrayList<BusStop>(busStopList);
        this.arrayToString(this.busStopArray);
    }

    public void removeStop(String busID, String stopID, Boolean direction) {
        BusStop compare = new BusStop(busID, stopID, direction);
        boolean found = false;
        for(BusStop n: busStopArray) {
            if(n.equals(compare)) {
                busStopArray.remove(n);
                found = true;
            }
        }

        if (found) {
            System.out.println("Delete Successful");
        }else {
            System.out.println("Bus or Stop not found");
        }

    }

//    public void executeTrip() throws WrongInputException, JSONException {
//
//        for (BusStop n : busStopArray) {
//            System.out.println("Bus: " + n.busID + "   |   " + "Stop: " + n.stopID);
//            System.out.println("---------------------------");
//            n.displayTime();
//            System.out.println("\n");
//        }
//
//
//
//    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }



}
