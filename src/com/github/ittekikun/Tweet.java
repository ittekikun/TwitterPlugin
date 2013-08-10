package com.github.ittekikun;

import java.util.Date;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import com.github.ittekikun.Uti.Uti;

public class Tweet 
{
	public static void Tweet(String Message) throws TwitterException
	{
		TwitterFactory factory = new TwitterFactory();
		Twitter twitter = factory.getInstance();
		twitter.setOAuthConsumer(TwitterPlugin.consumerKey,TwitterPlugin.consumerSecret);
		twitter.setOAuthAccessToken(new AccessToken(TwitterPlugin.accessToken, TwitterPlugin.accessTokenSecret));

		Date Time = Uti.TimeGet();

        twitter.updateStatus(Message + "\n" + Time);
    }
}
