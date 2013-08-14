package com.github.ittekikun.Gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

public class Certify 
{
	public static RequestToken certify(Twitter twitter)
	{
		RequestToken requestToken = null;
        //コンシューマ・キーとコンシューマ・シークレット
        final String m_ConsumerKey = "OUiV57ozWTDBE9RaOJiKkA";
        final String m_ConsumerSecret = "9KaLlaWFDjExpKDLhIYx5B3jLojgpgHApdqLzTlKQ";

        twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(m_ConsumerKey, m_ConsumerSecret);
        //アクセストークンの取得
        requestToken = getOAuthAccessToken(twitter);
		return requestToken;
	 }

	static RequestToken getOAuthAccessToken(Twitter twitter)
	{
		RequestToken requestToken = null;
        Desktop desktop = Desktop.getDesktop();
        
        try 
        {
            requestToken = twitter.getOAuthRequestToken();
        } 
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        
        try 
        {
			URI uri = new URI(requestToken.getAuthorizationURL());
			desktop.browse(uri);
		} 
        catch (URISyntaxException e) 
        {
			e.printStackTrace();
		} 
        catch (IOException e) 
        {
			e.printStackTrace();
		}
		return requestToken;
    }
}
