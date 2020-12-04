match(t:tweet)
WITH t, split(t.tweet, " ") as words
UNWIND words as at
with t, at
where left(tolower(at),1) ="@" and (tolower(at)="@telkomza" or tolower(at)="@rainsouthafrica" or tolower(at)="@mtnza" or tolower(at)="@afrihost" ) with t, at 
merge(c:company {username: trim(replace(tolower(at),"@", " "))})
merge(c)-[:IS_MENTIONED_IN]->(t);
