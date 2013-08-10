package com.github.ittekikun.Command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import com.github.ittekikun.TwitterPlugin;

public class CommandToggleTweet implements Listener 
{
	List<String> PlayerList = new ArrayList<String>();

	void ToggleTweet(CommandSender sender, String[] args)
    {
		Player player = (Player)sender;
		String name = player.getName();
		
		if(PlayerList.contains(name))
		{
			PlayerList .remove(PlayerList.indexOf(name));
			System.out.println("name1" +name +PlayerList.contains(name));
			
		}
		else
		{
			PlayerList .add(name);
			System.out.println("name2" +name + PlayerList.contains(name));
		}
	}
	
	@EventHandler
	public void onPlayerChat(PlayerChatEvent event) throws TwitterException
	{
		Player player = event.getPlayer(); 
	    String name = player.getName();
	    
	    String Ms = event.getMessage();
	    
	    System.out.println("name3" +name+ PlayerList.contains(name));
	    
	    if(PlayerList.contains(name))
		{
	    	TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(TwitterPlugin.consumerKey,TwitterPlugin.consumerSecret);
			twitter.setOAuthAccessToken(new AccessToken(TwitterPlugin.accessToken, TwitterPlugin.accessTokenSecret));
	    	//twitter.updateStatus("【サーバーから" + name + "さんが投稿】" +  "\n" + tw);
			player.sendMessage(ChatColor.RED + "【TwitterPlugin】" + ChatColor.WHITE + "「" + Ms + "」" +  "を投稿しました");
			
			System.out.println("name4" +name+ PlayerList.contains(name));
			event.setCancelled (true);
		}
	    
	}
}
