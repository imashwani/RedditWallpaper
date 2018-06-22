package com.example.ashwani.redditwallpaper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.example.ashwani.redditwallpaper.Data.Child;
import com.example.ashwani.redditwallpaper.Data.Data_;
import com.example.ashwani.redditwallpaper.Data.RedditAPI;
import com.example.ashwani.redditwallpaper.Data.RedditResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private String TAG="MainActivity";
    private TextView mTextMessage;
    private RecyclerView recyclerView;
    private RedditAdapter redditAdapter;
    List<Child> childList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getBaseContext(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        final Call<RedditResponse> jsonData = RedditAPI.getRedditService().getPostList("iwallpaper", "75");
        jsonData.enqueue(new Callback<RedditResponse>() {
            @Override
            public void onResponse(Call<RedditResponse> call, Response<RedditResponse> response) {
                childList = response.body().getData().getChildren();

                Log.d("", "onResponse: " + childList);

                String imageUrl = childList.get(0).getData().getUrl();
               // ImageView imageView=findViewById(R.id.mainimage);
                Log.d(TAG, "onResponse: the first image url"+imageUrl);

//                Glide.with(getBaseContext()).load(imageUrl).into(imageView);

                StringBuilder stringBuilder=new StringBuilder("");
                for (int i = 0; i <childList.size() ; i++) {
                    stringBuilder.append(i+".  "+childList.get(i).getData().getUrl()+"\n");
                }
//                TextView tv=findViewById(R.id.debugData);
//                tv.setText(stringBuilder);
                Log.d(TAG, "onResponse: list of imag url"+stringBuilder);

                redditAdapter=new RedditAdapter(childList);
                recyclerView.setAdapter(redditAdapter);
            }
            @Override
            public void onFailure(Call<RedditResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Nahi huaa", Toast.LENGTH_SHORT).show();
                Log.d("main activity", "onFailure: "+t.getMessage());

            }
        });
//initRecyclerView();

    }

    private void initRecyclerView() {
        redditAdapter = new RedditAdapter(childList);
    }





}
