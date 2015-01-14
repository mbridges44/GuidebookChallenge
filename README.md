# Guidebook Android Challenge

<h2>The Challenge</h2>
<p>My challenge was for the Android Coding Challenge. My task was to perform a GET request to http://guidebook.com/service/v2/upcomingGuides/ and
parse the returned data as a JSON object which I would do a few different things with:

<ol>
  <li>Retrieve the data and print it to the log</li>
  <li>Parse the data into a list of Java objects</li>
  <li>Display the objects in an organized fashion</li>
  <li>Display the image located at each object's URL</li>
</ol>
</p>

<h3>My Android background</h3>
<p>The only experience I have developing in Android was a project I did last winter. I created a card counting trainer that would
aim to teach users how to count cards through a couple different games. This was a pretty simple application, I didn't connect to any 
databases or other servers, just purley a simple app to get me familiar developing in Android.</p>

<h2>Solution</h2>
<p> I have posted my coded solution here in this Github repo. This challenge was fairly straightforward, and the
technical choices I made were what I believe to be pretty orthodox in regards to this challenge. </p>

<p> From the beginning, I did my best effort to create this application as maintainable as possible. In my eyes that meant clear code and
creating methods to do specific tasks. I split up a lot of the HTTP request and JSON parsing into multiple methods in case in the future there are other
servers that we would want data from as well. Putting the functionality for the request, string builder, and JSON parsing in separate methods also made my code more clear than having them all in one method.</p>
<p>Parsing the data to Event objects (I now realize Event might be a poor name for a Java object due to potential collision with Java class) 
was pretty straightforward. I decided to create a venue class because venue came as an object itself.</p>
<p>Displaying the names of the Objects was also pretty simple, I just added a refrence to my ListView in my Activity class
and looped through the array and displayed TextViews of each name. I decided to store the Events in an array and not
ArrayList because in this senario I knew my data would not be changing so this was acceptable in <i>this</i> senario,
however it wouldn't be a bad idea to store the objects in an ArrayList to be able to dynamically display data as it changes.</p>
<p>Due to time constraints, I unfortunatley did not get to part 4, which is to add the image. This would have been pretty cool and I'm
disappointed that I was not able to get to this.</p>

<h3>Things that I would change</h3>
<p>Given more time, I would like to make a few changes to my implementation. I think it would be cool to click on an event
and have more data about it displayed. It would be even better to be able to sort things based on different fields. I would also
aim to make the UI look nicer, different colors or bigger text would like nice but I understand that this challenge was more geared
towards my design and creation of this Android app, not testing my design skills.</p>


<p>Let me know if you have any questions or would like to critique my code. I am not familiar with the best practices in Android
so don't hesitate to inform me if I should have done something better. Thanks!</p>

Michael Bridges<br/>
651-357-0445<br/>
brid9157@stthomas.edu<br/>
