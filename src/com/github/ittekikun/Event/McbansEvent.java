package com.github.ittekikun.Event;

import java.util.Date;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import com.github.ittekikun.TwitterPlugin;
import com.github.ittekikun.Uti.Uti;
import com.mcbans.firestar.mcbans.events.PlayerBannedEvent;
import com.mcbans.firestar.mcbans.events.PlayerKickEvent;

public class McbansEvent implements Listener {
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent event) throws TwitterException
	{
	    String Player = event.getPlayer(); // KICKされた人
	    String Sender =  event.getSender(); // KICKした人
	    String Reason = event.getReason(); // KICK理由
	    
	    TwitterFactory factory = new TwitterFactory();
		Twitter twitter = factory.getInstance();
		twitter.setOAuthConsumer(TwitterPlugin.consumerKey,TwitterPlugin.consumerSecret);
		twitter.setOAuthAccessToken(new AccessToken(TwitterPlugin.accessToken, TwitterPlugin.accessTokenSecret));
		
		//Calendar calendar = Calendar.getInstance(); 
		//Date Time = calendar.getTime();  
		
		Date Time = Uti.TimeGet();
		
		String Message = replaceKeywords(TwitterPlugin.K_message_temp, Player,Reason,Sender);

		//twitter.updateStatus("【テスト】"+ Player +"が、「"+ Reason +"」という理由で、"+ Sender +"によってKICKされました。" + "\n" + Time);
		
		twitter.updateStatus(Message + "\n" + Time);
	}

	@EventHandler
    public void onPlayerBanned(PlayerBannedEvent event) throws TwitterException
    {
        if (!event.isGlobalBan()) return;
        
        String Player = event.getPlayerName(); // BANされた人
	    String Sender =  event.getSenderName(); // BANした人
	    String Reason = event.getReason(); // BAN理由
        
        TwitterFactory factory = new TwitterFactory();
		Twitter twitter = factory.getInstance();
		twitter.setOAuthConsumer(TwitterPlugin.consumerKey,TwitterPlugin.consumerSecret);
		twitter.setOAuthAccessToken(new AccessToken(TwitterPlugin.accessToken, TwitterPlugin.accessTokenSecret));
 
        //String ip = event.getPlayerIP();
        //String ipstr = (ip != null && ip.length() > 0) ? "[" + ip + "]" : "";
        //twitter.updateStatus(event.getPlayerName() + ipstr + " has been globally banned by " + event.getSenderName() + "!");
		
		//Calendar calendar = Calendar.getInstance(); 
		//Date Time = calendar.getTime();  
		
		Date Time = Uti.TimeGet();
		
		String Message = replaceKeywords(TwitterPlugin.B_message_temp, Player,Reason,Sender);
		
        //twitter.updateStatus("【テスト】"+ Player +"が、「"+ Reason +"」という理由で、"+ Sender +"によってグローバルBANされました。" + "\n" + Time);
        
        twitter.updateStatus(Message + "\n" + Time);
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
