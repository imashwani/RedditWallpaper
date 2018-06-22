package com.example.ashwani.redditwallpaper.Data;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RedditAPI {
    private static final String baseUrl = "http://reddit.com/";

    public static RedditService redditService = null;

    //to implement singleton pattern
    public static RedditService getRedditService() {

        if (redditService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            redditService = retrofit.create(RedditService.class);
        }
        return redditService;
    }

    public interface RedditService {

//        @Headers("Content-type: application/json")
        @GET("r/{subreddit}/hot.json")
        Call<RedditResponse> getPostList(@Path("subreddit") String subreddit, @Query("limit") String limit);

    }
}
