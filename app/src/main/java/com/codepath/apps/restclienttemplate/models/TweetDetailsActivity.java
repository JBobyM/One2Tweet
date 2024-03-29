package com.codepath.apps.restclienttemplate.models;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.apps.restclienttemplate.R;

import org.parceler.Parcels;

public class TweetDetailsActivity extends AppCompatActivity {
    Tweet tweet;
    ImageView ivProfile2;
    TextView tvName;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView retweets;
    TextView Favourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);

        ivProfile2 = findViewById(R.id.ivProfile2);
        tvName = findViewById(R.id.tvName);
        tv2=findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4=findViewById(R.id.tv4);
        retweets=findViewById(R.id.retweets);
        Favourite=findViewById(R.id.Fav);

        tweet = Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));
        tv3.setText(tweet.user.location);
        tv4.setText(tweet.createdAt);
        tvName.setText(tweet.getUser().screenName);
        tv2.setText(tweet.body);
        retweets.setText("Retweets: "+tweet.retweets);
        Favourite.setText("Love: "+tweet.Favourite);

        Glide.with(this).load(tweet.user.profileImageUrl).apply(new RequestOptions().transform(new CircleCrop())).into(ivProfile2);
        //Glide.with(this).load(tweet.user.profileImageUrl).into(ivProfile2);

    }
}