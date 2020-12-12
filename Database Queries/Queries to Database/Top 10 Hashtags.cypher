MATCH (t:tweet)-[:USES_HASHTAG]->(h:hashtag)
RETURN h.name AS hashtag, Count(t) AS tweets
Order by tweets DESC
LIMIT 10