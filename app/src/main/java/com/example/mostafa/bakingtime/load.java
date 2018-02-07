package com.example.mostafa.bakingtime;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by mostafa on 12/10/2017.
 */

public class load extends AsyncTask <Void,Void,JSONArray> {
    public AsyncResponse delegate = null;
    public interface AsyncResponse {
        void processFinish(JSONArray output);
    }

    static JSONArray object =null;
    @Override
    protected void onPostExecute(JSONArray jsonObject) {
        delegate.processFinish(jsonObject);
    }

    @Override
    protected JSONArray doInBackground(Void... voids) {
        URL url1 = null;
        JSONArray array = null;
        HttpURLConnection connection = null;
        try {
            url1 = new URL("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");
            connection = (HttpURLConnection) url1.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
               // Log.d("ww","hi");
                //parentObject = new JSONObject(scanner.next());
                array=new JSONArray(scanner.next());
                Log.d("ww",String.valueOf(array.length()));


            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        Log.d("ww",String.valueOf(array.length()));

        return array;
    }
}