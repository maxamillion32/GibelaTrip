package com.gibelatrip.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Passenger")
public class Passenger extends ParseUser {

    public String getName(){
        return getString("name");
    }

    public void setName(String driver){
        put("name", driver);
    }

    public int getRating(){
        return getInt("passenger_rating");
    }

    public void setRating(String driver){
        put("passenger_rating", driver);
    }

    public String getToken(){
        return getString("card_token");
    }

    public void setToken(String token){
        put("card_token", token);
    }

    public String getPhone(){
        return getString("phone");
    }

    public void setPhone(String token){
        put("phone", token);
    }

}
