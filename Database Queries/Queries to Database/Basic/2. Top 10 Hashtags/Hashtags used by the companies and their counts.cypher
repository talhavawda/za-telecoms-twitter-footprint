MATCH (c:company)-[TWEETED]->(t:tweet)-[:USES_HASHTAG]->(h:hashtag)
RETURN c.username AS Company, h.name AS Hashtag, Count(h.name) AS NumberOfTimesUsed
Order by NumberOfTimesUsed DESC