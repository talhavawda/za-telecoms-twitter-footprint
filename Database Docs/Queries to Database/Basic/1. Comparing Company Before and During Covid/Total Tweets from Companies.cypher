MATCH (c:company)-[:TWEETED]->(t:tweet)
RETURN c.username AS Username, Count(t.tweet) AS NumberOfTweets