package com.example.ttcsc;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsonClass {

    String url;
    String docString;
    JSONArray jsonArray;


    public JsonClass(String url)  {
        this.url = url;
        this.setDocString();
        this.setJsonArray();
    }



    public void setDocString() {
        try {
            System.out.println(url.toString());
            final Document docs = Jsoup.connect(url).ignoreContentType(true).get();
            System.out.println(docs.toString());
            docString = docs.html();


        }catch(Exception ex) {
            System.out.println("Error at Scraping");
            System.out.println(docString);
            ex.printStackTrace();
        }
    }


    public void setJsonArray() {
        this.cleanDocString();

        try {

            JSONArray object = new JSONArray(docString);
//		    //System.out.println("Object: " + object.toString());
            jsonArray = object;



        }
        catch(Exception e) {
            System.out.println("Error at JSON Processing or Format");
            System.out.println(e.getMessage());
        }
    }


    public void cleanDocString() {

        if (docString == null){
            return;
        }
        docString = docString.replace("</body>", "");
        docString = docString.replace("</html>", "");
        docString = docString.replace("<body>", "");
        docString = docString.replace("<html>", "");
        docString = docString.replace("</head>", "");
        docString = docString.replace("<head>", "");


    }


}
