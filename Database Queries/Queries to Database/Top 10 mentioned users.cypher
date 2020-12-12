MATCH (u:user)-[:IS_MENTIONED_IN]->(t:tweet)
RETURN u.username AS Username, Count(t) AS mentions
Order by mentions DESC
LIMIT 10