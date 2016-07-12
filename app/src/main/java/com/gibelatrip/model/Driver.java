package com.gibelatrip.model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseUser;

@ParseClassName("Driver")
public class Driver extends ParseUser {

    public String getDriverName(){
        return getString("driver_name");
    }

    public void setDriverName(String driver){
        put("driver_name", driver);
    }

    public int getDriverRating(){
        return getInt("driver_rating");
    }

    public void setDriverRating(String driver){
        put("driver_rating", driver);
    }

    public String getDriverToken(){
        return getString("card_token");
    }

    public void setDriverToken(String token){
        put("card_token", token);
    }

    public String getDriverImage(){
        return getParseFile("image").getUrl();
    }

    public void setDriverImage(ParseFile value){
        put("image", value);
    }

}
