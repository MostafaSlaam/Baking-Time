package com.example.mostafa.bakingtime;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mostafa on 12/28/2017.
 */

public class masterAdapter extends RecyclerView.Adapter<masterAdapter.MyViewHolder> {

    private Context context;
    private int index;
    List<String> myList=new ArrayList<>();

    final private ListItemClickListener mOnClickListener;

    public interface  ListItemClickListener
    {
        void OnListItemClick(int index);
    }

    public masterAdapter(Context context, final int index1, ListItemClickListener mOnClickListener) throws JSONException
    {
        this.context = context;
        this.index = index1;
        this.mOnClickListener = mOnClickListener;

        myList.add("Recipe Ingredients");
        String x=loadJson.myString;
        JSONArray array=new JSONArray(x);
        JSONObject k = array.getJSONObject(index);
        JSONArray jsonArray = k.getJSONArray("steps");
        Log.d("wwww",String.valueOf(jsonArray.length()));

        for (int i=0;i<jsonArray.length();i++)
        {
            JSONObject child=jsonArray.getJSONObject(i);
            myList.add(child.getString("shortDescription"));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutIdForListItem = R.layout.master_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(myList.get(position));
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.tV_list_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int ClickedPosition=getAdapterPosition();
            mOnClickListener.OnListItemClick(ClickedPosition);
        }
    }
}
