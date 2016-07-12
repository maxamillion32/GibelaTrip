package com.gibelatrip.model;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

@ParseClassName("Ride")
public class Ride extends ParseObject {

    public void setPickUp(ParseGeoPoint startLocation){
        put("pick_up", startLocation);
    }

    public ParseGeoPoint getPickUp(){
        return getParseGeoPoint("pick_up");
    }

    public void setDestination(ParseGeoPoint value){
        put("destination", value);
    }

    public ParseGeoPoint getDestination(){
        return getParseGeoPoint("destination");
    }

    public void setDriver(Driver value){
        put("driver", value);
    }

    public Driver getDriver(){
        return (Driver) getParseObject("driver");
    }

    public void setPassenger(Passenger value){
        put("passenger", value);
    }

    public Driver getPassenger(){
        return (Driver) getParseObject("passenger");
    }

    public void setBaseFare(int value){
        put("base_fare", value);
    }

    public int getBaseFare(){
        return getInt("base_fare");
    }

    public void setMinuteFare(int value){
        put("time_fare", value);
    }

    public int getMinuteFare(){
        return getInt("time_fare");
    }

    public void setKilometreFare(int value){
        put("km_fare", value);
    }

    public int getKilometreFare(){
        return getInt("km_fare");
    }

    public void setTripTotal(int value){
        put("trip_total", value);
    }

    public int getTripTotal(){
        return getInt("trip_total");
    }

    public void setRating(int value){
        put("rating", value);
    }

    public int getRating(){
        return getInt("rating");
    }



}
