package com.example.mostafa.bakingtime;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements FirstAdapter.ListItemClickListener {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.re)
        {
            recyclerView.removeAllViewsInLayout();
            new getData().execute();
            recyclerView.setVisibility(View.VISIBLE);
            adapter = new FirstAdapter(starting.getNames(),starting.getImages(),getApplicationContext(),this);

        }
        return false;
    }

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ProgressBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.RView);
        if(getResources().getConfiguration().screenWidthDp>=600)
        {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
            recyclerView.setLayoutManager(gridLayoutManager);
        }

        else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            //Do some stuff
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
            recyclerView.setLayoutManager(gridLayoutManager);
        }

        else {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
            recyclerView.setLayoutManager(gridLayoutManager);
        }
        bar=(ProgressBar)findViewById(R.id.pb_loading_indicator);
        recyclerView.setHasFixedSize(true);
        adapter = new FirstAdapter(starting.getNames(),starting.getImages(),getApplicationContext(),this);
        new getData().execute();
    }

    public class getData extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bar.setVisibility(View.VISIBLE);
        }
        @Override
        protected Void doInBackground(Void... voids) {
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

                    loadJson.myString=new String(scanner.next());

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }  finally {
                connection.disconnect();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(!loadJson.myString.equals(""))
            {
                bar.setVisibility(View.INVISIBLE);
                recyclerView.setAdapter(adapter);
            }
            else
            {
                bar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),"no internet ",Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void OnListItemClick(int index) {

        loadJson.index=index;
        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
        //intent.putExtra("index",String.valueOf(index));
        startActivity(intent);

    }
}
