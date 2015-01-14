package com.example.michael.guidebookchallenge;

/**
 * Created by Michael on 1/13/2015.
 */
public class Venue
{
    private String city;
    private String state;

    public Venue(String city, String state)
    {
        this.city = city;
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String toString()
    {
        return this.city + ", " + this.getState();
    }
}
