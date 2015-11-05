package com.example.han.newnewnoon;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by CHAE on 2015-09-19.
 */

public class foodDbJson {

    public String HttpPostData(String param) {
        String myResult="";
        ArrayList<String> foodDB = new ArrayList<String>();

        try {
            URL url = new URL("http://192.168.0.65/noon/createJson.jsp?"+param);
            URLConnection conn = (HttpURLConnection) url.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            http.setChunkedStreamingMode(0);
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setRequestMethod("POST");

            BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream(), "UTF-8"));
            //InputStream in = http.getInputStream();

            StringBuffer sb = new StringBuffer();
            try {
                int chr;
                while((chr = in.read()) != -1) {
                    sb.append((char) chr);
                }
                myResult = sb.toString();
                Log.i("ButtonClick",myResult);
            } finally {
                in.close();
            }

            try {
                JSONObject root = new JSONObject(myResult);
                JSONArray jarr = root.getJSONArray("Food");
                String food_name="";
                for(int i=0; i<jarr.length();i++) {
                    JSONObject json = new JSONObject();
                    json = jarr.getJSONObject(i);
                    food_name = json.getString("food_name");
                    foodDB.add(food_name);
                    Log.i("ButtonClick",food_name);
                }

            } catch (JSONException e) {
                Log.e("DBJSON parse","Error");
            }

        } catch (MalformedURLException e) {
            Log.e("DBJSON","error");
        } catch (IOException e) {
            Log.e("DBJSON","IOerror");
        }

        String str = "";
        str = foodDB.get(0);

        return str;
    }

}
