package com.example.ashwani.redditwallpaper;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.IOException;

public class ImageActivity extends AppCompatActivity {
    String url = "";
    PhotoView photoView;
    Button setWallButton;
    Bitmap bitmap;
    private final String TAG = "Image activity";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        setWallButton = findViewById(R.id.set_wall_button);
        photoView = findViewById(R.id.image_view_single_activity);
        progressBar=findViewById(R.id.progress_bar);

        url = getIntent().getStringExtra("url");



        Glide.with(this).load(url).into(photoView);
//        progressBar.setVisibility(View.GONE);


//
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                bitmap = ((BitmapDrawable) photoView.getDrawable()).getBitmap();
//                Toast.makeText(ImageActivity.this, "staring to set wallpaper", Toast.LENGTH_SHORT).show();
//                setWallpaperFun(bitmap);
//            }
//        }, 1000);

        setWallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmap = ((BitmapDrawable) photoView.getDrawable()).getBitmap();
                Toast.makeText(ImageActivity.this, "staring to set wallpaper", Toast.LENGTH_SHORT).show();
                setWallpaperFun(bitmap);
            }
        });
    }

    void setWallpaperFun(Bitmap theBitmap) {
        WallpaperManager myWallpaperManager
                = WallpaperManager.getInstance(getApplicationContext());
        try {
            myWallpaperManager.setBitmap(theBitmap);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
