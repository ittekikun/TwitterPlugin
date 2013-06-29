package com.github.ittekikun.Command;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import com.github.ittekikun.TwitterPlugin;
import com.github.ittekikun.Uti.Uti;

public class CommandTweet implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
	if(cmd.getName().equalsIgnoreCase("tw"))
	{
		Player player = (Player)sender;
		player.getPlayer(); 
		PlayerInventory inventory = player.getInventory(); // プレイヤーのインベントリ
		ItemStack diamondstack = new ItemStack(Material.DIAMOND, 5);
		String name = player.getName();
		
		if (inventory.contains(diamondstack))
		{
			inventory.remove(diamondstack); 
			
			String tw = Uti.ArrayUnion(args, 0);
			
			player.sendMessage(ChatColor.RED + "【TwitterPlugin】" + ChatColor.WHITE + tw + "を投稿しました。");

			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(TwitterPlugin.consumerKey,TwitterPlugin.consumerSecret);
			twitter.setOAuthAccessToken(new AccessToken(TwitterPlugin.accessToken, TwitterPlugin.accessTokenSecret));

			try
			{
				twitter.updateStatus("【サーバーから" + name + "さんが投稿】" +  "\n" + tw);
			}
			catch (TwitterException e)
			{
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		else
		{
			
		}
		return true;
	}
	return false; 
	// コマンドが実行されなかった場合は、falseを返して当メソッドを抜ける。
	}
}
