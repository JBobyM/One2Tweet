package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {
    private TweetsAdapter adapter;
    private List<Tweet> tweets;
    private SwipeRefreshLayout swipeContainer;
    private EndlessRecyclerViewScrollListener scrollListener;


    private TwitterClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        // Find the RecyclerView
        RecyclerView rvTweets =  findViewById(R.id.rvTweets);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvTweets.setLayoutManager(linearLayoutManager);
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                client.getNextPageOfTweets(new JsonHttpResponseHandler(){

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        List<Tweet> tweetsToAdd =new ArrayList<>();
                        for (int i=0;i<response.length();i++){
                            try {

                                // convert each jsonobject into tweet object
                                JSONObject jsonTweetObject =response.getJSONObject(i);
                                Tweet tweet = Tweet.fromjson(jsonTweetObject);

                                //Add the tweet into the data source
                                tweetsToAdd.add(tweet);
                            } catch (JSONException e) {
                                e.printStackTrace();

                            }
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                    }
                });

            }
        };

        rvTweets.addOnScrollListener(scrollListener);


        client = TwitterApp.getRestClient(this);
        swipeContainer=findViewById(R.id.swipeContainer);
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);



        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("Twitterclient", "Content is being refreshed");
                populateHomeTimeline();
            }
        });




        populateHomeTimeline();


        // Initialize list of tweets from the data source
        tweets= new ArrayList<>();
        adapter=new TweetsAdapter(this,tweets);
        //RecyclerView: layout manager and setup the adapter
        rvTweets.setLayoutManager(new LinearLayoutManager(this));
        rvTweets.setAdapter(adapter);
    }

    private void loadNextDataFromAPI(int offset) {
        client.getHomeTimeLine(new JsonHttpResponseHandler());
        
    }

    private void populateHomeTimeline() {
        client.getHomeTimeLine(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
              //  Log.d("TwitterClient", response.toString() );
                // Iterate through the list of tweets
                List<Tweet> tweetsToAdd =new ArrayList<>();
                for (int i=0;i<response.length();i++){
                    try {

                        // convert each jsonobject into tweet object
                       JSONObject jsonTweetObject =response.getJSONObject(i);
                       Tweet tweet = Tweet.fromjson(jsonTweetObject);

                        //Add the tweet into the data source
                        tweetsToAdd.add(tweet);
                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
                // clear existing data
                adapter.clear();
                // show the data we just receive
                adapter.AddTweets(tweetsToAdd);
                // Now we call setRefreshing(false) to signal refresh has finished
                swipeContainer.setRefreshing(false);





            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
               // Log.e("TwitterClient", errorResponse.toString() );
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
               // Log.e("TwitterClient", responseString );

            }
        });
    }



}
