match(t:tweet)
WITH t, split(t.tweet, " ") as words
UNWIND words as at
with t, at
where left(at,1) ="@" and (at="@telkomza" or at="@rainsouthafrica" or at="@mtnza" or at="@afrihost" ) with t, at 
merge(c:company {username: trim(replace(at,"@", " "))})
merge(c)-[:IS_MENTIONED_IN]->(t);