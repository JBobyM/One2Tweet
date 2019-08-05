package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.TweetDetailsActivity;

import org.parceler.Parcels;

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
        holder.tvName.setText(tweet.user.name);
        holder.tvTime.setText(xxx);
        holder.location.setText(tweet.user.location);
        holder.tvScreenName.setText("@"+tweet.user.screenName);
        holder.tvLike.setText(tweet.Favourite);
        holder.tvRetweet.setText(tweet.retweets);
        //holder.tvComment.setText(tweet.Comment);


        /*Glide.with(holder.ivProfileImage).load(tweet.user.profileImageUrl)
                .apply(new RequestOptions().fitCenter()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .override(Target.SIZE_ORIGINAL))
                        .into(holder.ivProfileImage);*/

        //Glide.with(context).load(tweet.user.profileImageUrl).into(holder.ivProfileImage);
        Glide.with(context).load(tweet.user.profilImgBannerUrl).apply(new RequestOptions().transform(new CircleCrop())).into(holder.ivProfileImage);
        //Picasso.with(context).load(tweet.user.profilImgBannerUrl).into(holder.ivProfileImage);

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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView ivProfileImage;
        public TextView tvName;
        public  TextView tvBody;
        public TextView tvTime;
        public  TextView location;
        public TextView tvScreenName;
        public  TextView tvRetweet;
        public  TextView tvLike;
        public  TextView tvComment;





        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage=itemView.findViewById(R.id.ivProfileImage);
            tvName = itemView.findViewById(R.id.tvName);
            tvBody=itemView.findViewById(R.id.tvBody);
            tvTime=itemView.findViewById(R.id.tvTime);
            location=itemView.findViewById(R.id.location);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvRetweet=itemView.findViewById(R.id.tvRetweet);
            tvLike=itemView.findViewById(R.id.tvLike);
            tvComment=itemView.findViewById(R.id.tvComment);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {


            // gets item position
            int position = getAdapterPosition();
            // make sure the position is valid, i.e. actually exists in the view
            if (position!= RecyclerView.NO_POSITION){
                // get the movie at the position, this won't work if the class is static
                Tweet tweet = Tweets.get(position);
                // create intent for the new activity
                Intent intent = new Intent(context, TweetDetailsActivity.class);
                // serialize the movie using parceler, use its short name as a key
                intent.putExtra(Tweet.class.getSimpleName(), Parcels.wrap(tweet));
                // show the activity
                context.startActivity(intent);

            }

        }
    }



}
