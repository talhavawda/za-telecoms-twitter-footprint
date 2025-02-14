import twint

def search_account(username):
    c = twint.Config()
    c.Username = username
    c.Since = '2019-10-01'
    c.Until = "2020-06-30"
    c.Store_csv = True
    c.Output = "data.csv"
    twint.run.Search(c)

def search_keywords(word_phrase):
    t = twint.Config()  
    t.Search = word_phrase
    t.Since = '2019-10-01'
    t.Until = '2020-06-30'
    t.Store_csv = True
    t.Output = "keywords.csv"
    twint.run.Search(t)

accounts = ["TelkomZA", "rainSouthAfrica", "Afrihost", "MTNza"]
for i in range(len(accounts)):
    search_account(accounts[i])

keywords_phrases = ["working from home ", "#WFH", "#workfromhome", "WiFi", "wifi", "isp", "ISP", 
                    "university data", "data", "studying", "study",  "university", "exam", "online",
                    "exams", "online learning", "zoom", "zero rated", "tests", "virtual", "assessments", 
                    "assignment", "reception", "mobile data", "lockdown", "telecom", "Telkom", "MTN", 
                    "Afrihost", "Rain", "slow internet", "cant study", "UKZN", "UCT", "WITS", "UJ", "UP", 
                    "Stellenbosch", "covid", "coronavirus", "covid-19"]


for i in range(len(keywords_phrases)):
    key = keywords_phrases[i]
    for j in range(len(accounts)):
        account = accounts[j]
        statement = key +" @"+account
        search_keywords(statement)