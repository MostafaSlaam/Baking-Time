package com.example.mostafa.bakingtime;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class Main3Activity extends AppCompatActivity {

    detail_Fragment fragment;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                Toast.makeText(getApplicationContext(),String.valueOf(getSupportFragmentManager().getBackStackEntryCount()),Toast.LENGTH_SHORT).show();
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                   loadJson.index2--;
                    return true;
                }
                else
                    {
                        NavUtils.navigateUpFromSameTask(this);
                        return true;
                    }
                }

            case R.id.f:
                {
                    loadJson.widget_index=loadJson.index;
                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
                    int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, bakingWidget.class));
                    appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.LV_widget);
                    Toast.makeText(getApplicationContext(),"Added to Widget",Toast.LENGTH_SHORT).show();

                   // appWidgetManager.notify();
                }
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        Button button=(Button)findViewById(R.id.b_act);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            button.setVisibility(View.INVISIBLE);
        }


            if (loadJson.index2 == 0) {

            ingredients_Fragment fragment=new ingredients_Fragment();
            fragment.myFunction(this);
            FragmentManager manager=getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.add(R.id.continer, fragment);
            fragmentTransaction.commit();

        } else {

                if (savedInstanceState == null) {
                    fragment = new detail_Fragment();
                }
                else
                {
                    fragment = (detail_Fragment)
                            getSupportFragmentManager().getFragment(savedInstanceState, "my_fragment");
                }
                    fragment.setfragment(this);
                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.continer, fragment,"my_fragment");
                    fragmentTransaction.commit();

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     fragment = new detail_Fragment();
                    fragment.setfragment(getApplicationContext());
                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.continer, fragment);
                    fragmentTransaction.addToBackStack("myBack").commit();
                    loadJson.index2++;
            }
        });
    }



    @Override
    public void onSaveInstanceState(Bundle currentState) {
        super.onSaveInstanceState(currentState);
        getSupportFragmentManager().putFragment(currentState, "my_fragment", fragment);
    }
}
