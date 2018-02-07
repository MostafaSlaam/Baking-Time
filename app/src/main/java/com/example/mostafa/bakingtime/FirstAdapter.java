package com.example.mostafa.bakingtime;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mostafa on 12/9/2017.
 */

public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.myViewHolder> {

    private List<Integer> Names;
    private List<Integer> Images;
    private Context context;

    public FirstAdapter(List<Integer> names, List<Integer> images, Context context, ListItemClickListener mOnClickListener) {
        Names = names;
        Images = images;
        this.context = context;
        this.mOnClickListener = mOnClickListener;
    }

    public interface  ListItemClickListener
    {
        void OnListItemClick(int index);
    }
    final private ListItemClickListener mOnClickListener;
    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutIdForListItem = R.layout.card_view;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        myViewHolder viewHolder = new myViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        String s=getImage(position);
        if(s.equals("")) {
            holder.cardView.setBackgroundResource(Images.get(position));
        }
        else
        {
            Uri myUri = Uri.parse(s).buildUpon().build();
            Picasso.with(context).load(myUri).into(holder.imageView);
        }
       holder.textView.setText(Names.get(position));


    }

    @Override
    public int getItemCount() {
        return Names.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        CardView cardView;
        TextView textView;
        public myViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.first_IV);
            textView=(TextView)itemView.findViewById(R.id.TV);
            cardView=(CardView)itemView.findViewById(R.id.cardView);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            int ClickedPosition=getAdapterPosition();
            mOnClickListener.OnListItemClick(ClickedPosition);

        }
    }


    public String getImage(int pos)
    {
       String x = loadJson.myString;
       String ImageUri=null;
        JSONArray array= null;
        try {
            array = new JSONArray(x);
            JSONObject k = array.getJSONObject(pos);
            ImageUri=k.getString("image");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ImageUri;
    }

}
