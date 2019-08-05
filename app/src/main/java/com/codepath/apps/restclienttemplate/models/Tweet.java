package com.codepath.apps.restclienttemplate.models;

import com.codepath.apps.restclienttemplate.TimeFormatter;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Tweet {

    public String body;
    public long uid;
    public String createdAt;
    public String retweets;
    public String Favourite;
    public String Comment;

    public User user;

    public Tweet() {
    }

    public static Tweet fromjson(JSONObject jsonObject) throws JSONException {
        Tweet tweet=new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.uid = jsonObject.getLong("id");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.retweets = jsonObject.getString("retweet_count");
        tweet.Favourite = jsonObject.getString("favorite_count");
        //tweet.Comment = jsonObject.getString("statuses_count");


        tweet.user = User.fromjson(jsonObject.getJSONObject("user"));

        return tweet;
    }
    public static String getFormattedTimestamp(String createdAt){
        return TimeFormatter.getTimeDifference(createdAt);

    }

    public String getBody() {
        return body;
    }

    public long getUid() {
        return uid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }
}
