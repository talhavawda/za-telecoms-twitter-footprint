# Meeting Notes

- Wrap up technical risks before looking at what data we actually found


- MVC Design Pattern
  - Structure
    - Model is the database (and APIs used to contain it - Neo4j has it)
    - View is the resulting (generated) HTML
    - Controller is either a **servlet or JSP** that performs the query and then constructs the View (actual webpage)
      - Takes in input from the UI to execute query, get the result, and send the data back
      - Can have 1 Controller for all the queries or a Controller for each query. We have to decide this granularity
  - MVC is more for structure of design, wont necessarily have to code every component
    - Neo4j has components that give you Views
    - MVC is for roles/responsibilities of the components
    
<br>

- Web Interface is a dashboard (a menu / front page) that lets you navigate to the queries you want to view (each of these queries activates a Controller)
  - Can hardcode topic if we got pre-prepared queries
  - Have a front page; Controllers are responsible for moving the users around
  - Only 1 MVC Use Case
    - It's a 1-way MC -> The Views are not updating the Model
    
<br>

- Getting data out of the database: Neo4j has API classes for this
- Most code we writing (if using a nice database, like Neo4j) will be for the Controllers and the actual web pages

- A Use Case must cover all scenarios of a case, and must be complete from beginning to end
  - Dont break things up unnecessairily
  - But since we working as a group, it makes more sense to make each query has its own controller, so we work on them in parallel

<br>
<br>

- Can get extra marks for making it a 'live' site -> automate to get new data into the database every now and then
  - Can also design so topic is not hardcoded -> results in a reusable tool
    - ONLY do these after/if we covered the basics   
- Can get extra marks for having a form where the user can type in a Cypher query and return the result
 
<br>

- Can look at correlations, tweets, retweets, likes, sentiment in a tweet, number of followers a person has, and also who they follow

- Nodes in the database will be users and hashtags
  - The lines could be anything: followers, retweets, likes, (any other interaction between the nodes)
  - Twitter - group is the hashtag
  
 <br>
 
- Preferable to have queries coded into the HTML rather than the business logic
  - ?? Was this only if doing the additional user-entered query 
  - cos we want to separate resuable stuff from hard-coded stuff
  
<br>
<br>

# Todo

- Get data into a Neo4j database
- Look at how to use the Cypher query language
- Formulate list of queries


- See Neo4j demos
<br>

# Queries

- 
  - Queries can inspire questions -> from looking at the data, did we find what we thought we'd find (from our speculations) or the sentiment is not intuitive or the opposite (anomalies)?
  - Get questions that the anaytics answer
    - What we expected and what we didn't expect are all opportunities for these questions
  - For each Query the user can select, provide a User-friendly description of what it does

<br>

Queries:
- Have the ISPs been more active on Twitter since Covid/Lockdown as compared to before?
  - Interactions with users
    - being more responsive, connecting with customers, attending to their questions/queries/complaints via Tweeting
  - More campaigns
    - Do users react well to the new campaigns?
- Over the time period, is there a change in sentiment?
  - This shows whether the ISPs are responding/adapting to the changing requirements due to Covid
  - Sir: The sentiment being the same or getting worse (more complaints) is counterintuitive as you'd expect the ISPs to adapt and exploit the situation with more campains and offers
  - Consider the bias that users are much more likely to express a negative sentiment than a positive sentiment
    - 'No news is good news'
    - If there is a drop in complaints then you can take it as a positive outcome
    - More complaints could also be seen as customers opening up to them and coming forward with their complaints/dissatisfactions
- List of items that the customers complain about

<br>

# Presenting

- 2 ways:
  - Live Zoom meeting with a demo -> screen sharing
    - But sometimes the queries take long to execute
  - Pre-record demo (and also Pptx presentation)
    - Sir can mark it offline
    - Then maybe a Zoom meeting for sir to ask questions
    
- Can do a presentation instead of writing out a report ?? 
    
