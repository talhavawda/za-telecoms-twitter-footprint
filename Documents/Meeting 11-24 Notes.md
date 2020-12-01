# Topic

Quantifying and classifying the Digital (Social Media) Footprint of South African telecommunications companies (ISPs and mobile operators) before and during the Covid-19 pandemic (and correlating it / its relation with the transition to online learning in SA)
- The company’s posts, its interactions with users, and its interactions with other companies
- Users mentioning the company in tweets (Sentiment Analysis), which other companies they follow and/or tweet about (to group related companies together in a cluster)

<br>
<br>

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

- ~~Get data into a Neo4j database~~ [DONE]
  - Note: Everyone has to create their own local database using Neo4j Desktop. Your Web Interface server will connect to this database. 
- Look at how to use the Cypher query language
  - See how to display query results in tabulated/graph form
  - Useful Links:
    - [Cypher count() summary][7]
    - [Cypher Aggregations][8]
- Formulate the list of queries (that will produce the analytics for your report on the topic)
  - A draft of a few have been done in the Queries section below
    - Extend it to  Compile a formal exhaustive list
  - Do some by analysing the data that was collected
- Develop Web Interface/Architecture
  - Integrate the database API into the website so that queries can be visualised by the site
    - ~~Connect the Web Interface to the local database~~
    - Code the Controllers for the Queries
    - Code the Web Interface site
    - **Useful Links** for using Neo4j in IntelliJ with Spring:
        - See the Database class I made to represent the database and handle a query
      - [Spring Data Neo4j - Developing Spring Applications with Neo4j][1]
        - I've already done this (configuring the project)
        - [It's API][2]
      - [CRUD Controller Example][3]
        - See the Database class I made to represent the database and handle a query
          - Thus in our program a Controller just needs to call Database.query(q);
      - [Neo4j Data Driver][4]
        - [Manual][5]
        - [API][6]


[1]: https://docs.spring.io/spring-data/neo4j/docs/current/reference/html/#reference
[2]: https://docs.spring.io/spring-data/neo4j/docs/current/api/
[3]: https://github.com/neo4j/neo4j-java-driver-spring-boot-starter/blob/master/docs/manual/getting-started.adoc#example-crud-controller
[4]: https://github.com/neo4j/neo4j-java-driver/tree/4.0
[5]: https://neo4j.com/docs/driver-manual/4.0/
[6]: https://neo4j.com/docs/api/java-driver/4.0/
[7]: https://neo4j.com/developer/kb/fast-counts-using-the-count-store/
[8]: https://neo4j.com/docs/cypher-manual/current/functions/aggregating/


- See Neo4j demos
- Extend the scraping to see from those users who mentioned (at least) 1 of the 4 companies, see which other ISP Companies they follow
- We can extend the twitter scraping (the Python script) as:
  - Users don’t have to necessarily @ the ISP for the tweet to be included
  - Include more keywords, especially regarding the names of the ISP’s themselves -> E.g.‘covid’, ‘telkom’…..etc.

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
  - Number of tweets of each company before vs after lockdown
    - South Africa went into lockdown on 26 March 2020 so use this date 
  - Interactions with users
    - being more responsive, connecting with customers, attending to their questions/queries/complaints via Tweeting
  - More campaigns
    - Do users react well to the new campaigns?
- Over the time period, is there a change in sentiment (Sentiment Analysis)?
  - the sentiment of the 4 companies we chose and if there’s been a change from before to after lockdown
  - This shows whether the ISPs are responding/adapting to the changing requirements due to Covid
  - Sir: The sentiment being the same or getting worse (more complaints) is counterintuitive as you'd expect the ISPs to adapt and exploit the situation with more campains and offers
  - Consider the bias that users are much more likely to express a negative sentiment than a positive sentiment (as they'll only tweet when they have an issue)
    - 'No news is good news'
    - If there is a drop in complaints then you can take it as a positive outcome
    - More complaints could also be seen as customers opening up to them and coming forward with their complaints/dissatisfactions
- List of items that the customers complain about
- How SA university students have been affected by (their sentiment with respect to) transitioning to online learning/studying and whether they’ve been able to cope
- If a user(s) follows this ISP company, which other companies do they follow?

<br>

# Basic Analysis

From looking at few entries in the csv files, the following trends were seen:
(Formulate queries based on them and add them to the Queries list)

- Users on twitter are complaining/ranting about the service provided by the ISPs/ mobile networks
  - Network connection – no connectivity, v low speed
  - Service delivery
  - Poor customer services
  - Data disappearing
- Asking about products/prices
- Students complaining about network coverage
- A lot of the users’ issues are with Telkom


<br>
<br>

# Presenting

- 2 ways:
  - Live Zoom meeting with a demo -> screen sharing
    - But sometimes the queries take long to execute
  - Pre-record demo (and also Pptx presentation)
    - Sir can mark it offline
    - Then maybe a Zoom meeting for sir to ask questions
    
- Can do a presentation instead of writing out a report ?? 
    
