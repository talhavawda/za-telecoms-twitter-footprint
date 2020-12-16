match(t:tweet)
WITH t, split(t.tweet, " ") as words
UNWIND words as hashtags
with t, hashtags
where left(hashtags,1) ="#" with t, hashtags
merge(h:hashtag {name: toUpper(hashtags)})
merge(t)-[:USES_HASHTAG]->(h);