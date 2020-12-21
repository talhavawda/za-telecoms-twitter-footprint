LOAD CSV WITH HEADERS FROM 'file:///data.csv' AS row
merge(c:company {username:row.username, companyID:row.user_id })
merge(t:tweet {id:row.id, username:row.username, tweet:row.tweet, url:row.urls, date:date(row.date), mentions:row.mentions})<-[:TWEETED]-(c)
//Read in data.csv and create Company and Tweet Nodes
//Create a relationship between them