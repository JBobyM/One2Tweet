package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {

    private Context context;
    private List<Tweet> Tweets;

    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        Tweets = tweets;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tweet tweet = Tweets.get(position);
        String xxx = tweet.getFormattedTimestamp(tweet.createdAt);
        holder.tvBody.setText(tweet.body);
        holder.tvScreenName.setText(tweet.user.screenName);
        holder.tvTime.setText(xxx);
        Glide.with(context).load(tweet.user.profileImageUrl).into(holder.ivProfileImage);



    }

    @Override
    public int getItemCount() {
        return Tweets.size();
    }

// Clean all elements of the recycler

    public void clear(){
        Tweets.clear();
        notifyDataSetChanged();
    }
// Add a list of items
    public void AddTweets(List<Tweet> tweetList){
        Tweets.addAll(tweetList);
        notifyDataSetChanged();
    }

    // Pass in contexts and list of tweets
    // for each row, inflate the layout
    // bind values based on the position of the element
    //Define the viewholder

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivProfileImage;
        public TextView tvScreenName;
        public  TextView tvBody;
        public TextView tvTime;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage=itemView.findViewById(R.id.ivProfileImage);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvBody=itemView.findViewById(R.id.tvBody);
            tvTime=itemView.findViewById(R.id.tvTime);


        }
    }



}
