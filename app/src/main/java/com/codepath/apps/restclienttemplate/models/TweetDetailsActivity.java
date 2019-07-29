package com.codepath.apps.restclienttemplate.models;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;

import org.parceler.Parcels;

public class TweetDetailsActivity extends AppCompatActivity {
    Tweet tweet;
    ImageView ivProfile2;
    TextView tvName;
    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);

        ivProfile2 = findViewById(R.id.ivProfile2);
        tvName = findViewById(R.id.tvName);
        tv2=findViewById(R.id.tv2);

        tweet = Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));

        tvName.setText(tweet.getUser().screenName);
        tv2.setText(tweet.body);
        Glide.with(this).load(tweet.user.profileImageUrl).into(ivProfile2);



    }
}