package com.github.ittekikun.Command;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import com.github.ittekikun.TwitterPlugin;
import com.github.ittekikun.Uti.Uti;

public class CommandTwitterPlugin implements Listener, CommandExecutor
{
    private Plugin plugin = TwitterPlugin.plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
    	Player player = (Player)sender;
    	if (label.equalsIgnoreCase("TwitterPlugin") || label.equalsIgnoreCase("twp"))
        {
    		if (args[0].equals("reload"))
    		{
    			if(sender.hasPermission("TwitterPlugin.reload"))
    			{
    				ReloadPlugin(sender);
                }
    			else
    			{
    				player.sendMessage(ChatColor.RED + "【TwitterPlugin】" + ChatColor.WHITE + "あなたは権限を持っていません。");
    			}
    		}
    		else if (args[0].equals("help"))
    		{
    			if(sender.hasPermission("TwitterPlugin.help"))
    			{
        			ShowHelp(sender);
                }
    			else
    			{
    				player.sendMessage(ChatColor.RED + "【TwitterPlugin】" + ChatColor.WHITE + "あなたは権限を持っていません。");
    			}
    		}
    		else if (args[0].equals("twi"))
    		{
    			if(sender.hasPermission("TwitterPlugin.itweet"))
    			{
        			ItemTweet(sender,args);
                }
    			else
    			{
    				player.sendMessage(ChatColor.RED + "【TwitterPlugin】" + ChatColor.WHITE + "あなたは権限を持っていません。");
    			}
    		}
    		else if (args[0].equals("tw"))
    		{
    			if(sender.hasPermission("TwitterPlugin.tweet"))
    			{
    				Tweet(sender,args);
                }
    			else
    			{
    				player.sendMessage(ChatColor.RED + "【TwitterPlugin】" + ChatColor.WHITE + "あなたは権限を持っていません。");
    			}
    		}
    		else
    		{
    			player.sendMessage(ChatColor.RED + "【TwitterPlugin】" + ChatColor.WHITE + "そのコマンドは存在しません。");
    		}
    		return true;
    		}
    	return false;
    }

    private void ReloadPlugin(CommandSender sender)
    {
    	Player player = (Player)sender;
    	
        plugin.reloadConfig();
        player.sendMessage(ChatColor.RED + "【TwitterPlugin】" + ChatColor.WHITE + "configをリロードしました。");
    }

    private void ShowHelp(CommandSender sender)
    {
        sender.sendMessage(plugin.getDescription().getName() + " ヘルプ");
        sender.sendMessage(" ");
        sender.sendMessage("Website: http://forum.minecraftuser.jp/viewtopic.php?f=26&t=8950&start=40#p72180");
        sender.sendMessage(" ");
    }
    
    private void ItemTweet(CommandSender sender, String[] args)
    {
    	Player player = (Player)sender;
		player.getPlayer(); 
		PlayerInventory inventory = player.getInventory();
		ItemStack diamondstack = new ItemStack(Material.DIAMOND, TwitterPlugin.Number_of_diamond);
		String name = player.getName();
		
		if (inventory.contains(diamondstack))
		{
			inventory.remove(diamondstack); 
			
			String tw = Uti.ArrayUnion(args, 1);
			
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(TwitterPlugin.consumerKey,TwitterPlugin.consumerSecret);
			twitter.setOAuthAccessToken(new AccessToken(TwitterPlugin.accessToken, TwitterPlugin.accessTokenSecret));
			try
			{
				twitter.updateStatus("【サーバーから" + name + "さんが投稿】" +  "\n" + tw);
				player.sendMessage(ChatColor.RED + "【TwitterPlugin】" + ChatColor.WHITE + "「" + tw + "」" +  "を投稿しました");
			}
			catch (TwitterException e)
			{
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
    }
    
    private void Tweet(CommandSender sender, String[] args)
    {
    	Player player = (Player)sender;
		String name = player.getName();
			
		String tw = Uti.ArrayUnion(args, 1);

		TwitterFactory factory = new TwitterFactory();
		Twitter twitter = factory.getInstance();
		twitter.setOAuthConsumer(TwitterPlugin.consumerKey,TwitterPlugin.consumerSecret);
		twitter.setOAuthAccessToken(new AccessToken(TwitterPlugin.accessToken, TwitterPlugin.accessTokenSecret));
		try
		{
			twitter.updateStatus("【サーバーから" + name + "さんが投稿】" +  "\n" + tw);
			player.sendMessage(ChatColor.RED + "【TwitterPlugin】" + ChatColor.WHITE + "「" + tw + "」" +  "を投稿しました");
		}
		catch (TwitterException e)
		{
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
