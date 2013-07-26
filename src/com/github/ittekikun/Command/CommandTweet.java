package com.github.ittekikun.Command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import twitter4j.TwitterException;

import com.github.ittekikun.Tweet;
import com.github.ittekikun.Uti.Uti;

public class CommandTweet 
{
	void Tweet(CommandSender sender, String[] args) throws TwitterException
    {
    	Player player = (Player)sender;
		String name = player.getName();
			
		String tw = Uti.ArrayUnion(args, 1);

		String Message = "【サーバーから" + name + "さんが投稿】" +  "\n" + tw;
		player.sendMessage(ChatColor.RED + "【TwitterPlugin】" + ChatColor.WHITE + "「" + tw + "」" +  "を投稿しました");
		
		Tweet.Tweet(Message);
	
}
}
