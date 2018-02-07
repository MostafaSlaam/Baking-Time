package com.example.mostafa.bakingtime;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mostafa on 12/20/2017.
 */

public class ListViewWidgetService extends RemoteViewsService{

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListViewRemoteFactory(this.getApplicationContext());
    }
}

class ListViewRemoteFactory implements RemoteViewsService.RemoteViewsFactory {

    Context context;
    JSONArray jsonArray;

    public ListViewRemoteFactory(Context context)
    {

        this.context=context;


    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        String x=loadJson.myString;
        JSONArray array= null;
        try {
            if (loadJson.widget_index!=-1){
                array = new JSONArray(x);
            JSONObject k = array.getJSONObject(loadJson.widget_index);
            jsonArray = k.getJSONArray("ingredients");
             }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (loadJson.widget_index==-1){
            return 0;
        }
        else
        {return jsonArray.length();}
    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.item_widget);
        JSONObject child = null;
        try {
            if (loadJson.widget_index!=-1) {
                child = jsonArray.getJSONObject(position);
                views.setTextViewText(R.id.TV_widget, child.getString("quantity") +
                        " " + child.getString("measure") +
                        "  " + child.getString("ingredient"));

            }} catch (JSONException e) {
            e.printStackTrace();
        }



        return views;



    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
