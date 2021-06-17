package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;

import com.kingfisher.easyviewindicator.RecyclerViewIndicator;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewIndicator horizontalIndicator;
    RecyclerAdapter recyclerAdapter;
    Timer timer;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        horizontalIndicator = findViewById(R.id.recycler_view_indicator);
        String[] dataSet = new String[6];

        dataSet[0] = "https://upload.wikimedia.org/wikipedia/ko/d/d4/%ED%8E%AD%EC%88%98.jpg";
        dataSet[1] = "https://upload.wikimedia.org/wikipedia/ko/d/d4/%ED%8E%AD%EC%88%98.jpg";
        dataSet[2] = "https://upload.wikimedia.org/wikipedia/ko/d/d4/%ED%8E%AD%EC%88%98.jpg";
        dataSet[3] = "https://upload.wikimedia.org/wikipedia/ko/d/d4/%ED%8E%AD%EC%88%98.jpg";
        dataSet[4] = "https://upload.wikimedia.org/wikipedia/ko/d/d4/%ED%8E%AD%EC%88%98.jpg";
        dataSet[5] = "https://upload.wikimedia.org/wikipedia/ko/d/d4/%ED%8E%AD%EC%88%98.jpg";

        recyclerAdapter = new RecyclerAdapter(dataSet);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(recyclerAdapter);

        horizontalIndicator.setRecyclerView(recyclerView);


        timer = new Timer();
        timer.scheduleAtFixedRate(new RemidTask(), 0, 2000);

    }

    private class RemidTask extends TimerTask {
        int current = recyclerAdapter.getItemCount();

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (position == recyclerAdapter.getItemCount()) {
                        position = 0;
                        position++;
                    } else {
                        position++;
                    }
                    recyclerView.smoothScrollToPosition(position);
                }
            });
        }
    }
}