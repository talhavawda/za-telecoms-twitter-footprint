MATCH (c:company)-[:IS_MENTIONED_IN]->(t:tweet)
RETURN c.username AS CompanyBeingTalkedAbout, t.tweet AS TweetTowardsCompany