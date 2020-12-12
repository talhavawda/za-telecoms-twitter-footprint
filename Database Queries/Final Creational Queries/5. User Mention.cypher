match(t:tweet)
WITH t, split(t.tweet, " ") as words
UNWIND words as at
with t, at
where 
left(at,1) ="@" 
and (
	not(
    	   tolower(at)="@telkomza" 
	or tolower(at)="@rainsouthafrica"
        or tolower(at)="@mtnza" 
        or tolower(at)="@afrihost" 
        )
    )
with t, at
merge(u:user {username: trim(replace(toLower(at),"@", " "))})
merge(u)-[:IS_MENTIONED_IN]->(t);

