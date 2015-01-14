package com.example.michael.guidebookchallenge;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Michael on 1/13/2015.
 */
public class Event
{
    private Date startDate;
    private Date endDate;
    private String name;
    private Venue venue;
    private String url;
    private String iconURL;

    public Event(JSONObject obj)
    {
        createEvent(obj);
    }

    /*This takes the JSON Object and calls the setters for each of the data fields
    in the JSON Object.
     */
    private void createEvent(JSONObject obj)
    {
        try
        {
            setIconURL(obj.get("icon").toString());
        }
        catch(JSONException jsonex)
        {
            System.out.println("Error setting Event icon");
            jsonex.printStackTrace();
        }

        try
        {
            setUrl(obj.get("url").toString());
        }
        catch(JSONException jsonex)
        {
            System.out.println("Error setting url");
            jsonex.printStackTrace();
        }

        try
        {
            setStartDate(obj.get("startDate").toString());
            setEndDate(obj.get("endDate").toString());
        }
        catch(JSONException jsonex)
        {
            System.out.println("Error setting dates");
            jsonex.printStackTrace();
        }

        try
        {
            setName(obj.get("name").toString());
        }
        catch(JSONException jsonex)
        {
            System.out.println("Error setting name");
            jsonex.printStackTrace();
        }

        try
        {
            setVenue((JSONObject) obj.get("venue"));
        }
        catch(JSONException jsonex)
        {
            System.out.println("Error setting name");
            jsonex.printStackTrace();
        }
    }

    public Date getStartDate()
    {
        return startDate;
    }

    private void setStartDate(String startDate)
    {
        this.startDate = getDateFromString(startDate);
    }

    //Uses the date format do convert a date string to a date object
    private Date getDateFromString(String date)
    {
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date dateObj =  null;
        try
        {
            dateObj = format.parse(date);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return dateObj;
    }

    public Date getEndDate() {
        return endDate;
    }

    private void setEndDate(String endDate)
    {
        this.endDate = getDateFromString(endDate);
    }

    public String getName()
    {
        return name;
    }

    private void setName(String name)
    {
        this.name = name;
    }

    public Venue getVenue()
    {
        return venue;
    }

    private void setVenue(JSONObject venue)
    {

        Venue ven = null;
        if(venue.length() > 0)
        {
            try
            {
                ven = new Venue(venue.get("city").toString(), venue.get("state").toString());
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        this.venue = ven;
    }

    public String getIconURL()
    {
        return iconURL;
    }

    private void setIconURL(String iconURL)
    {
        this.iconURL = iconURL;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String toString()
    {
        String ret = "Start Date:\t" + this.getStartDate() + "\n"
                + "End Date:\t" + this.getEndDate() + "\n"
                + "Name:\t" + this.getName() + "\n"
                + "URL:\t" + this.getUrl() + "\n"
                + "Icon:\t" + this.getIconURL();
        if(this.venue != null)
        {
            ret = ret +  "\nVenue:\t" + this.venue.toString();
        }

        return ret;
    }
}
