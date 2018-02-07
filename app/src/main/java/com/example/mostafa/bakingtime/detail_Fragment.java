package com.example.mostafa.bakingtime;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.zip.Inflater;


public class detail_Fragment extends Fragment {
    String x=null;
    Context context;
    String s,ss,sss;
    ImageView imageView;
    SimpleExoPlayer player=null;
   static long postion=C.TIME_UNSET;
   static boolean AutoPlay=true;
    SimpleExoPlayerView V_view;

    public detail_Fragment() {

        // Required empty public constructor
    }
public void setfragment(Context context)
{
 this.context=context;

}


    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            AutoPlay=player.getPlayWhenReady();
            postion = player.getCurrentPosition();


            player.stop();
            player.release();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if(savedInstanceState==null) {
            x = loadJson.myString;
        }
        else
        {

            x=savedInstanceState.getString("myString");
            postion=savedInstanceState.getLong("postion");
            AutoPlay=savedInstanceState.getBoolean("Autoplay");
        }
        JSONArray array= null;
        try {
            array = new JSONArray(x);
            JSONObject k = array.getJSONObject(loadJson.index);
            JSONArray jsonArray = k.getJSONArray("steps");
            JSONObject child;
            if(loadJson.index2>=jsonArray.length()){
                child=jsonArray.getJSONObject(jsonArray.length()-1);
                loadJson.index2=0;
            }
            else
            {
                child=jsonArray.getJSONObject(loadJson.index2-1);}
            s=child.getString("videoURL");
            ss=child.getString("description");
            sss=child.getString("thumbnailURL");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        View view= inflater.inflate(R.layout.fragment_detail_, container, false);
        V_view=(SimpleExoPlayerView) view.findViewById(R.id.VV);


        final TextView textView=(TextView)view.findViewById(R.id.TV_steps);
        textView.setText(ss);
        if(!sss.equals(""))
        {
            imageView=(ImageView)view.findViewById(R.id.image_details);
            imageView.setVisibility(View.VISIBLE);
            Uri myUri = Uri.parse(sss).buildUpon().build();
            Picasso.with(getContext()).load(myUri).into(imageView);
        }
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            if(!sss.equals(""))
            imageView.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.INVISIBLE);

        }
        if(savedInstanceState==null) {
            if (s.isEmpty()) {
                player=null;
                V_view.setForeground(ContextCompat.getDrawable(getContext(), starting.getImages().get(loadJson.index)));
            }
                else {
                initializePlayer();
                V_view.setPlayer(player);
            }
        }
        else
        {
            initializePlayer();

           V_view.setPlayer(player);
        }

        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(player!=null) {
            player.stop();
            player.release();
           // player=null;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("myString",x);
        outState.putLong("postion",postion);
        outState.putBoolean("Autoplay",AutoPlay);
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    private void initializePlayer() {
        if (player == null) {
            player = ExoPlayerFactory.newSimpleInstance(
                    new DefaultRenderersFactory(getContext()),
                    new DefaultTrackSelector(), new DefaultLoadControl());
            V_view.setPlayer(player);

            MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(s), new DefaultHttpDataSourceFactory("ua"),
                    new DefaultExtractorsFactory(), null, null);

            player.prepare(mediaSource);

            if (postion != C.TIME_UNSET)
                player.seekTo(postion);

            player.setPlayWhenReady(AutoPlay);
        }
        }

}
