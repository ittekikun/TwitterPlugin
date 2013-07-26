package com.github.ittekikun.Event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import twitter4j.TwitterException;

import com.github.ittekikun.Tweet;
import com.github.ittekikun.TwitterPlugin;
import com.mcbans.firestar.mcbans.events.PlayerBannedEvent;
import com.mcbans.firestar.mcbans.events.PlayerKickEvent;

public class McbansEvent implements Listener {
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent event) throws TwitterException
	{
	    String Player = event.getPlayer(); // KICKされた人
	    String Sender =  event.getSender(); // KICKした人
	    String Reason = event.getReason(); // KICK理由
	    
		String Message = replaceKeywords(TwitterPlugin.K_message_temp, Player,Reason,Sender);

		Tweet.Tweet(Message);
	}

	@EventHandler
    public void onPlayerBanned(PlayerBannedEvent event) throws TwitterException
    {
        if (!event.isGlobalBan()) return;
        
        String Player = event.getPlayerName(); // BANされた人
	    String Sender =  event.getSenderName(); // BANした人
	    String Reason = event.getReason(); // BAN理由

		String Message = replaceKeywords(TwitterPlugin.B_message_temp, Player,Reason,Sender);

		Tweet.Tweet(Message);
    }
	
	private String replaceKeywords(String source,String name, String reason, String sender)
	{
		String result = source;
        if ( result.contains(TwitterPlugin.KEYWORD_USER) )
        {
            result = result.replace(TwitterPlugin.KEYWORD_USER, name);
        }
        if ( result.contains(TwitterPlugin.KEYWORD_REASON) )
        {
            result = result.replace(TwitterPlugin.KEYWORD_REASON, reason);
        }
        if ( result.contains(TwitterPlugin.KEYWORD_SENDER) )
        {
            result = result.replace(TwitterPlugin.KEYWORD_SENDER, sender);
        }
        return result;
    }
}
