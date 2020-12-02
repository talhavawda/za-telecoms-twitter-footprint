match(t:tweet)
WITH t, split(t.tweet, " ") as words
UNWIND words as at
with t, at
where left(at,1) ="@" with t, at
merge(u:user {username: trim(replace(at,"@", " "))})
merge(u)-[:IS_MENTIONED_IN]->(t);