package com.example.michael.guidebookchallenge;

import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;


public class HomeActivity extends ActionBarActivity {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        linearLayout = (LinearLayout)findViewById(R.id.linearLayout);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String url = "http://guidebook.com/service/v2/upcomingGuides/";
        JSONArray jsonData = getJSONDataFromServer(url);
        Event[] events =  getArrayOfEventsFromJSONObjects(jsonData);
        displayEvents(events);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    //Displays event objects names to the LinearLayout
    private void displayEvents(Event[] events)
    {
        for(int i = 0; i < events.length; i++)
        {
            TextView txt = new TextView(this);
            txt.setLines(2);
            txt.setText(events[i].getName());
            linearLayout.addView(txt);
        }
    }

    /*Creates and returns an array of Event objects from the jsonObjects*/
    public Event[] getArrayOfEventsFromJSONObjects(JSONArray jsonObjects)
    {
        Event[] events = new Event[jsonObjects.length()];
        try
        {
            for(int i = 0; i < jsonObjects.length(); i++)
            {
                events[i] = new Event((JSONObject)jsonObjects.get(i));
            }
        }
        catch(JSONException jsonex)
        {
            jsonex.printStackTrace();
        }
        return events;
    }

   /* Returns a JSONArray with the json data sent back from the server at
    the passed in URL.*/
    private JSONArray getJSONDataFromServer(String url)
    {
        HttpResponse response = getResponseFromURL(url);
        JSONArray jsonData = null;
        if (response != null)
        {
            String responseData = getResponseData(response);
            JSONObject jsonResponse = convertResponseStringToJSON(responseData);
            jsonData = getJSONArrayFromObject(jsonResponse);
        }
        else
        {
            System.out.println("Response error from url: " + url);
        }

        return jsonData;
    }

    /* Returns a JSONArray created from the initial json response given by the server.
    This method uses the objects in  the data field. Returns null if an exception is thrown*/
    private JSONArray getJSONArrayFromObject(JSONObject jsonResponse)
    {
        JSONArray jsonObjects = null;
        try
        {
             jsonObjects= (JSONArray)jsonResponse.get("data");
        }
        catch(JSONException jse)
        {
            jse.printStackTrace();
        }
        return jsonObjects;
    }

    /*Returns the HttpResponse from the passed in string URL. Returns
    null if there was an error with the response*/
    private HttpResponse getResponseFromURL(String url)
    {
        HttpResponse response = null;
        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            URI website = new URI(url);
            request.setURI(website);
            response = httpclient.execute(request);
        }
        catch (IOException | URISyntaxException e)
        {
            e.printStackTrace();
        }
        return response;
    }

    /*Takes a passed in HttpResponse and returns the entity content returned by the
    response as a String*/
    private String getResponseData(HttpResponse response)
    {
        StringBuilder builder = new StringBuilder();
        try
        {
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String line;
            while((line = reader.readLine()) != null)
            {
                builder.append(line);
            }
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        return builder.toString();
    }

    /*Takes a String containing response information from the server and converts it to
    a JSON object. returns null if was not able to convert.
     */
    private JSONObject convertResponseStringToJSON(String responseString)
    {
        JSONObject json = null;
        try
        {
            json = new JSONObject(responseString);
        }
        catch(JSONException jsonex)
        {
            System.out.println("Failed to convert string to JSON");
            jsonex.printStackTrace();
        }
        return json;
    }

    //Prints objects in json array for debugging purposes
    private void printJSONDataToLog(JSONArray jsonData)
    {
        try
        {
           for(int i = 0; i < jsonData.length(); i++)
           {
               System.out.println(jsonData.get(i).toString());
           }
        }
        catch (JSONException jse)
        {
            jse.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
