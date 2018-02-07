package com.example.mostafa.bakingtime;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ingredients_Fragment extends Fragment {
    public static final String MYADAPTER = "ADAPTER";
 ArrayAdapter<String> adapter=null;
Context context;


    public ingredients_Fragment() {
        // Required empty public constructor


    }
public void myFunction(Context context)
{
    this.context=context;

}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_ingredients_, container, false);

        if (savedInstanceState==null) {
            adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1);
            String x = loadJson.myString;
            JSONArray array = null;
            try {
                array = new JSONArray(x);
                JSONObject k = array.getJSONObject(loadJson.index);
                JSONArray jsonArray = k.getJSONArray("ingredients");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject child = jsonArray.getJSONObject(i);
                    adapter.add(child.getString("quantity") + " " + child.getString("measure") + "  " + child.getString("ingredient"));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            GridView gridView = (GridView) view.findViewById(R.id.GV_ingr);
            gridView.setAdapter(adapter);
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
