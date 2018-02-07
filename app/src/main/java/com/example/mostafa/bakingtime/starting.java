package com.example.mostafa.bakingtime;

import com.example.mostafa.bakingtime.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mostafa on 12/9/2017.
 */

public class starting {
    private static final List<Integer> images=new ArrayList<Integer>() {
        {
            add(R.drawable.i1);
            add(R.drawable.i2);
            add(R.drawable.i3);
            add(R.drawable.i4);

        }
    };
    private static final List<Integer> names=new ArrayList<Integer>()
    {
        {
            add(R.string.firstCard);
            add(R.string.secondCard);
            add(R.string.thirdCard);
            add(R.string.fourthCard);
        }

    };
    public static List<Integer> getImages() {
        return images;
    }
    public static List<Integer> getNames() {
        return names;
    }
}
