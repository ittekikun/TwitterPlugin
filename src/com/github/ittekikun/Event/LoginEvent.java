package com.github.ittekikun.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import twitter4j.TwitterException;

import com.github.ittekikun.Tweet;
import com.github.ittekikun.TwitterPlugin;

public class LoginEvent implements Listener
{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) throws TwitterException
	{
	    Player player = event.getPlayer(); // ログインしたプレイヤー
	    String name = player.getName();
		
		Player[] Member = TwitterPlugin.plugin.getServer().getOnlinePlayers();
		String number = String.valueOf(Member.length);
		
		String Message = replaceKeywords(TwitterPlugin.I_message_temp, name, number);
		
		Tweet.Tweet(Message);
	}

	@EventHandler
	public void onPlayerLogout(PlayerQuitEvent event) throws TwitterException
	{
	    Player player = event.getPlayer(); // ログアウトした
	    String name = player.getName();

		Player[] Member = TwitterPlugin.plugin.getServer().getOnlinePlayers();
		
		if(Member.length == 1)
		{
			String number = "0";
			String Message = replaceKeywords(TwitterPlugin.O_message_temp, name, number);
			
			Tweet.Tweet(Message);
		}
		else
		{
			String number = String.valueOf(Member.length);
			String Message = replaceKeywords(TwitterPlugin.O_message_temp, name, number);
			
			Tweet.Tweet(Message);
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
