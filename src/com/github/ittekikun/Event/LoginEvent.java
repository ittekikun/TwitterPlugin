package com.github.ittekikun.Event;

import java.util.Date;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import com.github.ittekikun.TwitterPlugin;
import com.github.ittekikun.Uti.Uti;

public class LoginEvent implements Listener
{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) throws TwitterException
	{
	    Player player = event.getPlayer(); // ログインしたプレイヤー
	    String name = player.getName();
	    
	    TwitterFactory factory = new TwitterFactory();
		Twitter twitter = factory.getInstance();
		twitter.setOAuthConsumer(TwitterPlugin.consumerKey,TwitterPlugin.consumerSecret);
		twitter.setOAuthAccessToken(new AccessToken(TwitterPlugin.accessToken, TwitterPlugin.accessTokenSecret));
		
		Date Time = Uti.TimeGet();  
		
		Player[] Member = TwitterPlugin.plugin.getServer().getOnlinePlayers();
		String number = String.valueOf(Member.length);
		
		String Message = replaceKeywords(TwitterPlugin.I_message_temp, name, number);
		
		twitter.updateStatus(Message + "\n" + Time +  "\n");
	}

	@EventHandler
	public void onPlayerLogout(PlayerQuitEvent event) throws TwitterException
	{
	    Player player = event.getPlayer(); // ログアウトした
	    String name = player.getName();
	    
	    TwitterFactory factory = new TwitterFactory();
		Twitter twitter = factory.getInstance();
		twitter.setOAuthConsumer(TwitterPlugin.consumerKey,TwitterPlugin.consumerSecret);
		twitter.setOAuthAccessToken(new AccessToken(TwitterPlugin.accessToken, TwitterPlugin.accessTokenSecret));
		
		Date Time = Uti.TimeGet();  
		
		Player[] Member = TwitterPlugin.plugin.getServer().getOnlinePlayers();
		
		if(Member.length == 1)
		{
			String number = "0";
			String Message = replaceKeywords(TwitterPlugin.O_message_temp, name, number);
			
			twitter.updateStatus(Message + "\n" + Time);
		}
		else
		{
			String number = String.valueOf(Member.length);
			String Message = replaceKeywords(TwitterPlugin.O_message_temp, name, number);
			
			twitter.updateStatus(Message + "\n" + Time);
		}
	}

	private String replaceKeywords(String source,String name, String number)
	{
		String result = source;
        if ( result.contains(TwitterPlugin.KEYWORD_USER) )
        {
            result = result.replace(TwitterPlugin.KEYWORD_USER, name);
        }
        if ( result.contains(TwitterPlugin.KEYWORD_NUMBER) )
        {
            result = result.replace(TwitterPlugin.KEYWORD_NUMBER, number);
        }
        return result;
    }
}
