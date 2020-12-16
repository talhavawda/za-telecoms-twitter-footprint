import pandas as pd

data_frame = pd.read_csv("keywords.csv")
print(data_frame)
accounts = ["telkomza", "rainsouthafrica", "afrihost", "mtnza"]
entries=[]
for index, row in data_frame.iterrows():
    username = row['username']
    if username in accounts:
        entries.append(index)
df = data_frame.drop(entries)
print(df)
df.to_csv(r'C:\Users\Ahmad Jawaad Shah\Desktop\Twitter Data\updated_keywords.csv')