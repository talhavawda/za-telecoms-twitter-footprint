MATCH p = (:user{username:"slaylyn_05"})-[IS_MENTIONED_IN]->(:tweet)
RETURN p
LIMIT 10