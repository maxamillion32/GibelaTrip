package com.gibelatrip.model;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

@ParseClassName("Car")
public class Car extends ParseObject {

    public ParseGeoPoint getLocation(){
        return getParseGeoPoint("location");
    }

    public void setLocation(ParseGeoPoint geoPoint){
        put("location", geoPoint);
    }

    public void setDriverInformation(Driver driver){
        put("driver", driver);
    }

    public Driver getDriverInformation(){
        return (Driver) getParseObject("driver");
    }

    public String getCarRegistrationNumber(){
        return getString("registration");
    }

    public void setCarRegistrationNumber(String value){
        put("registration", value);
    }

    public String getCarModel(){
        return getString("model");
    }

    public void setCarModel(String value){
        put("model", value);
    }


}
