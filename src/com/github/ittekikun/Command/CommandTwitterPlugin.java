package com.github.ittekikun.Command;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import twitter4j.TwitterException;

import com.github.ittekikun.TwitterPlugin;

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
    				CommandItemTweet IT = new CommandItemTweet();
        			IT.ItemTweet(sender,args);
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
    				CommandTweet T = new CommandTweet();
        			try
        			{
						T.Tweet(sender,args);
					} 
        			catch (TwitterException e)
					{
						e.printStackTrace();
					}
                }
    			else
    			{
    				player.sendMessage(ChatColor.RED + "【TwitterPlugin】" + ChatColor.WHITE + "あなたは権限を持っていません。");
    			}
    		}
    		else if (args[0].equals("twt"))
    		{
    			if(sender.hasPermission("TwitterPlugin.ttweet"))
    			{
    				CommandToggleTweet TT = new CommandToggleTweet();
        			TT.ToggleTweet(sender,args);
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
        plugin.reloadConfig();
        sender.sendMessage(ChatColor.RED + "【TwitterPlugin】" + ChatColor.WHITE + "configをリロードしました。");
    }

    private void ShowHelp(CommandSender sender)
    {
        sender.sendMessage(plugin.getDescription().getName() + " ヘルプ");
        sender.sendMessage(" ");
        sender.sendMessage("Website: http://forum.minecraftuser.jp/viewtopic.php?f=26&t=8950&start=40#p72180");
        sender.sendMessage(" ");
    }
    
}
