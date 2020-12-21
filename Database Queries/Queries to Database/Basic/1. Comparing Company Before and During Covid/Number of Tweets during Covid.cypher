MATCH (c:company)-[:TWEETED]->(t:tweet)
where t.date>=date('2020-03-27') 
RETURN c.username AS Username, Count(t.tweet) AS NumberOfTweets