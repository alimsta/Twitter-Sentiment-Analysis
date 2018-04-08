from textblob import TextBlob
import tweepy

#Authenticate application with Twitter
consumer_key = ''
consumer_secret = ''
access_token = ''
access_secret = ''

authentication = tweepy.OAuthHandler(consumer_key, consumer_secret)
authentication.set_access_token(access_token, access_secret)

#Load API
api = tweepy.API(authentication)

#Get word from user
word = input("Give a word to search for: ")

#Retrieves 100 Tweets (in packages of 15) containing given word
tweets = api.search(q=word, count=100 )

#TextBlob performs sentiment analysis on each tweet including polarity and subjectivity.
total_sentiment = 0
tweet_count = 0
for tweet in tweets:
    print(tweet.text)
    analysis = TextBlob(tweet.text)
    print(analysis.sentiment)
    total_sentiment += analysis.sentiment.polarity
    tweet_count += 1

#Mean sentiment calculation
avg_sentiment = total_sentiment/tweet_count
print("The average sentiment for the word '{}' is {}".format(word, avg_sentiment))

