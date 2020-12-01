LOAD CSV WITH HEADERS FROM 'file:///data.csv' AS row
merge(u:user {username:row.username, userID:row.user_id })
merge(t:tweet {id:row.id, username:row.username, tweet:row.tweet, url:row.urls, date:date(row.date), mentions:row.mentions})<-[:TWEETED]-(u)
//Read in keywords.csv and create User and Tweet Nodes
//Create a relationship between them