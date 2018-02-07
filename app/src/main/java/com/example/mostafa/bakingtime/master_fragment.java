package com.example.mostafa.bakingtime;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONException;

/**
 * Created by mostafa on 12/10/2017.
 */

public class master_fragment extends Fragment implements masterAdapter.ListItemClickListener {
    int index=0;

    OnImageClickListener mCallback;

    @Override
    public void OnListItemClick(int index) {
        mCallback.onImageSelected(index);
    }

    public interface OnImageClickListener {
        void onImageSelected(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        try {
            mCallback = (OnImageClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnImageClickListener");
        }
    }
    @SuppressLint("ValidFragment")
    public master_fragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        RecyclerView gridView = (RecyclerView) rootView.findViewById(R.id.G_View);
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        gridView.setLayoutManager(manager);

            index=loadJson.index;

        masterAdapter mAdapter = null;
        try {
            mAdapter = new masterAdapter(getContext(), index,this);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Set the adapter on the GridView
        gridView.setAdapter(mAdapter);

        // Set a click listener on the gridView and trigger the callback onImageSelected when an item is clicked


        // Return the root view
        return rootView;
    }
}
