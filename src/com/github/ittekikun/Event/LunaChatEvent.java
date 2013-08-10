package com.github.ittekikun.Event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import twitter4j.TwitterException;

import com.github.ittekikun.Tweet;
import com.github.ittekikun.TwitterPlugin;
import com.github.ucchyocean.lc.event.LunaChatChannelCreateEvent;
import com.github.ucchyocean.lc.event.LunaChatChannelRemoveEvent;

public class LunaChatEvent implements Listener 
{
	@EventHandler
	public void onChannelCreate(LunaChatChannelCreateEvent event) throws TwitterException 
	{
	    String Channel = event.getChannelName(); // 作られたチャンネル
	    
	    String Message = replaceKeywords(TwitterPlugin.CC_message_temp, Channel);

	    Tweet.Tweet(Message);
	}

	@EventHandler
	public void onChannelRemove(LunaChatChannelRemoveEvent event) throws TwitterException 
	{
	    String Channel = event.getChannelName(); // 削除されたチャンネル
	    
	    String Message = replaceKeywords(TwitterPlugin.CD_message_temp, Channel);

	    Tweet.Tweet(Message);
	}
	
	private String replaceKeywords(String source,String channel)
	{
		String result = source;
        if (result.contains(TwitterPlugin.KEYWORD_CHANNEL) )
        {
            result = result.replace(TwitterPlugin.KEYWORD_CHANNEL, channel);
        }
        return result;
    }
}
