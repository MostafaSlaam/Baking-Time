package com.example.mostafa.bakingtime;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements master_fragment.OnImageClickListener {
    private boolean TwoPane;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {

                    NavUtils.navigateUpFromSameTask(this);
                    return true;

            }


        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //in tablet view
        if(findViewById(R.id.Two_pane_layout)!=null)
        {
            TwoPane=true;
            if (loadJson.index2 == 0) {

                ingredients_Fragment fragment=new ingredients_Fragment();
                fragment.myFunction(this);
                FragmentManager manager=getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.add(R.id.continer, fragment);
                fragmentTransaction.addToBackStack("myBack").commit();

            } else {

                detail_Fragment fragment = new detail_Fragment();
                fragment.setfragment(this);
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.continer, fragment);
                fragmentTransaction.addToBackStack("myBack").commit();
            }
        }
        else
        {
            TwoPane=false;
        }
    }



    @Override
    public void onImageSelected(int position) {
        Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
        loadJson.index2=position;
        if(!TwoPane) {
            startActivity(intent);
        }
        else
        {
            if (position == 0) {

                ingredients_Fragment fragment=new ingredients_Fragment();
                fragment.myFunction(this);
                FragmentManager manager=getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.continer, fragment);
                fragmentTransaction.addToBackStack("myBack").commit();

            } else {

                detail_Fragment fragment = new detail_Fragment();
                fragment.setfragment(this);
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.continer, fragment);
                fragmentTransaction.addToBackStack("myBack").commit();
            }
        }
    }
}
