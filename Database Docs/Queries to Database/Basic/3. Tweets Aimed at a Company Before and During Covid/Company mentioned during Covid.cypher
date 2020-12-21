MATCH (c:company)-[:IS_MENTIONED_IN]->(t:tweet)
where t.date>date('2020-03-01')
RETURN c.username AS Username, Count(t.tweet) AS NumberOfTweets