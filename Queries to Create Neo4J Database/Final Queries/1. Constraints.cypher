CREATE CONSTRAINT ON (u: user) ASSERT u.uid IS UNIQUE;
CREATE CONSTRAINT ON (t: tweet) ASSERT t.uid IS UNIQUE;
CREATE CONSTRAINT ON (c:company) ASSERT c.cid IS UNIQUE;
create index on :user(username);
create index on :tweet(tweet);
create index on :company(username);
