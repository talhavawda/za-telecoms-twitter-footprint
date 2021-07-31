# South African Telecoms' Covid-19 Twitter Footprint
### Quantifying, classifying, and contrasting the Twitter Footprint of South African Telecommunications companies before and during the Covid-19 pandemic

- Cornerstone Data Science group project for the COMP301 (Software Design) module
- (Previously project name: Covid19-Digital-Footprint)

<br>

## Execution Instructions

#### Database
1. Set up your local database using Neo4j Desktop
  - See the Readme.txt in the Database Docs folder to create your local database
  - The default user is 'neo4j' -> this is what the Neo4j Browser uses in displaying the database
    - So for the Web Interface to connect to the database, we need to create another user for it
      - Open Neo4j Browser and create a new user with username 'java_application' and password '12345', with reader permission
        - This is the username and password specified in the Web Interface connection to the database)
  
The database needs to be running for the Web Interface to connect to it

<br>

#### Web Interface
The WebInterface project was developed in IntelliJ IDEA. 
To run the Web Interface, have the database running in Neo4j Desktop, click Run in the IDE, wait for the Drive instance to be created, and then go to the URL http://localhost:8080/

<br>
