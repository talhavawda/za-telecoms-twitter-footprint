import pandas as pd

"""
    Tweets from the 4 Companies that are in keywords.csv are duplicates of the ones in data.csv,
    (they are already  in data.csv) so remove from keywords.csv all tweets that were tweeted by a company 
"""
data_frame = pd.read_csv("old_keywords.csv")
print(data_frame)
accounts = ["telkomza", "rainsouthafrica", "afrihost", "mtnza"]
entries=[]

for index, row in data_frame.iterrows():
    username = row['username']
    if username in accounts:
        entries.append(index)
df = data_frame.drop(entries)
print(df)
#df.to_csv(r'C:\Users\Ahmad Jawaad Shah\Desktop\Twitter Data\updated_keywords.csv')
df.to_csv(r'keywords.csv')