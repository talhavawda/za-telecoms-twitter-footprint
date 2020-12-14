MATCH p = (:company{username:"telkomza"})-[TWEETED]->(:tweet)-[:USES_HASHTAG]->(:hashtag)
RETURN p
LIMIT 100