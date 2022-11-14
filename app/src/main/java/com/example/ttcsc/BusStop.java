package com.example.ttcsc;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BusStop implements Comparable {

    String busID;
    String stopID;
    String[] address = new String[2];
    JsonClass jsonClass;
    Boolean direction;

    public BusStop(String busID, String stopID, Boolean direction) {
        this.busID = busID;
        this.stopID = stopID;
        this.setJson();
        this.direction = direction;
        //this.findAddress();
    }




    public void setJson() {
        String url = "https://www.ttc.ca/ttcapi/routedetail/GetNextBuses?routeId=";
        url += busID;
        url += "&stopCode=";
        url+= stopID;
        JsonClass newJson = new JsonClass(url);
        jsonClass = newJson;
    }


    public String[] findAddress() throws JSONException {

        Integer arrayLength = jsonClass.jsonArray.length();
        Integer innerArrayLength;
        Boolean found = false;

        for (int i = 0 ; i<arrayLength; i++) {

            JSONArray outerShell = jsonClass.jsonArray.getJSONObject(i).getJSONArray("routeBranchStops");
            innerArrayLength = outerShell.length();
            for (int n = 0; n<innerArrayLength; n++) {
                JSONObject innerShell = outerShell.getJSONObject(n);
                if(innerShell.getString("code").equals(stopID)) {
                    address[0] = String.valueOf(i);
                    address[1] = String.valueOf(n);
                    found = true;
                    break;
                }
            }
            if(found == true)break;
        }

        if(found.equals(false)) {
            System.out.println("Stop Code Not Found");
            return null;
        }

        System.out.println(address[0]);
        System.out.println(address[1]);
        return address;
    }

    public void displayTime2()throws JSONException {
        JSONArray timeArray = jsonClass.jsonArray.getJSONObject(Integer.valueOf(address[0])).getJSONArray("routeBranchStops").getJSONObject(Integer.valueOf(address[1])).getJSONArray("routeStopTimes");
        for(int i = 0; i<timeArray.length();i++) {
            System.out.println(timeArray.get(i).toString());
        }
    }

    public String displayTime() throws JSONException {
        String timeDetails = "";
        for (int i = 0; i<jsonClass.jsonArray.length(); i++) {

            JSONObject timeUnit = new JSONObject( jsonClass.jsonArray.get(i).toString());

            String timeArrival = timeUnit.getString("nextBusMinutes");
            if (timeArrival.equals("D")){
                timeArrival = "-1";
            }
            Integer crowdIndex = Integer.valueOf(timeUnit.getString("crowdingIndex"));
            String crowdMark = "";
            for(int n = 0; n<crowdIndex; n++) {
                crowdMark += ">";
            }
            String spacer = "";
            if(Integer.valueOf(timeArrival)<10) spacer = "  ";

            timeDetails += timeArrival + spacer + " minutes     "  + crowdMark + "\n";
        }
        return timeDetails;

    }

    @Override
    public boolean equals(Object o) {
        BusStop other = (BusStop) o;
        if(this.busID.equals(other.busID)&&this.stopID.equals(other.stopID)&&this.direction.equals(other.direction)) {
            return true;
        }else {
            return false;
        }

    }


    @Override
    public int compareTo(Object o) {
        if(this.direction ==true){
            return -1;
        }else{
            return 1;
        }
    }
}
