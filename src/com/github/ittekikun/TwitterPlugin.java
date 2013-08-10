package com.github.ittekikun;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import twitter4j.TwitterException;

import com.github.ittekikun.Command.CommandTwitterPlugin;
import com.github.ittekikun.Event.LoginEvent;
import com.github.ittekikun.Event.LunaChatEvent;
import com.github.ittekikun.Event.McbansEvent;


public class TwitterPlugin extends JavaPlugin{
	
	Logger logger = Logger.getLogger("minecraft");
	public static TwitterPlugin plugin; 
	
	public static String consumerKey;
	public static String consumerSecret;
	public static String accessToken;
	public static String accessTokenSecret;
	
	public static String I_message_temp;
	public static String O_message_temp;
	
	public static String CC_message_temp;
	public static String CD_message_temp;
	
	public static String K_message_temp;
	public static String B_message_temp;
	
	public static String SS_message_temp;
	public static String ST_message_temp;
	
	public static String T_message_temp;
	
	public static int Number_of_diamond;
	
	public static final String KEYWORD_USER = "$user";
	public static final String KEYWORD_REASON = "$reason";
	public static final String KEYWORD_SENDER = "$sender";
	public static final String KEYWORD_NUMBER = "$number";
	public static final String KEYWORD_CHANNEL = "$channel";
	public static final String KEYWORD_MESSAGE = "$message";
	
	public static final String prefix = "【TwitterPlugin】";

	@Override
	public void onEnable()
	{
		consumerKey = this.getConfig().getString("consumerKey","xxxxxxxxxx");
		consumerSecret = this.getConfig().getString("consumerSecret","xxxxxxxxxx");
		accessToken = this.getConfig().getString("accessToken","xxxxxxxxxx");
		accessTokenSecret = this.getConfig().getString("accessTokenSecret","xxxxxxxxxx");
		
		I_message_temp = this.getConfig().getString("LoginMessageTemplate", "$userさんがサーバーにログインしました。現在$number人がログインしています。【自動投稿】");
		O_message_temp = this.getConfig().getString("LogoutMessageTemplate", "$userさんがサーバーからログアウトしました。現在$number人がログインしています。【自動投稿】");
		
		SS_message_temp = this.getConfig().getString("ServerStartTemplate", "サーバーを起動しました。【自動投稿】");
		ST_message_temp = this.getConfig().getString("ServerStopTemplate", "サーバーが停止しました。【自動投稿】");
		
		CC_message_temp = this.getConfig().getString("ChannelCreateTemplate", "チャットチャンネル「$channel」が作成されました。【自動投稿】");
		CD_message_temp = this.getConfig().getString("ChannelDeleteTemplate", "チャットチャンネル「$channel」が削除されました。【自動投稿】");
		
		K_message_temp = this.getConfig().getString("KickMessageTemplate", "$userが、「$reason」という理由で、$senderによってKICKされました。【自動投稿】");
		B_message_temp = this.getConfig().getString("BanMessageTemplate", "$userが、「$reason」という理由で、$senderによってグローバルBANされました。【自動投稿】");
		
		T_message_temp = this.getConfig().getString("TimelineTemplate", "@$user - $message");
		
		Number_of_diamond = this.getConfig().getInt("Number_of_diamond",5);
		
		if (this.getConfig().getBoolean("ShowTimeline"))
		{
			Timeline.ShowTimeline();
		}

		plugin = this;

		this.saveDefaultConfig();
		this.reloadConfig();
		
		if(!getServer().getOnlineMode())
		{
			logger.info("サーバはオフラインモードで動作しています。");
			logger.info("TwitterPluginを無効化します。");
			this.setEnabled(false);
		}
		
		if (this.getConfig().getBoolean("PlayerLoginTweet"))
		{
			getServer().getPluginManager().registerEvents(new LoginEvent(), this);
		}
		
		if (this.getConfig().getBoolean("McbansTweet"))
		{
			if (getServer().getPluginManager().isPluginEnabled("MCBans") ) 
			{
				getServer().getPluginManager().registerEvents(new McbansEvent(), this);
				logger.info(prefix + "MCBansと連携しました。");
			}
			else
			{
				logger.info(prefix + "MCBansが導入されてないので連携を無効化します。");
			}
		}
		
		if (this.getConfig().getBoolean("LunaChatTweet"))
		{
			if (getServer().getPluginManager().isPluginEnabled("LunaChat") ) 
			{
				getServer().getPluginManager().registerEvents(new LunaChatEvent(), this);
				logger.info(prefix + "LunaChatと連携しました。");
			}
			else
			{
				logger.info(prefix + "LunaChatが導入されてないので連携を無効化します。");
			}
		}

			//getServer().getPluginManager().registerEvents(new ToggleTweet(), this);
		
		//getCommand("tw").setExecutor(new CommandTweet());
		getCommand("TwitterPlugin").setExecutor(new CommandTwitterPlugin());
		getCommand("twp").setExecutor(new CommandTwitterPlugin());
		
		
		
		
		try 
		{
			ServerStartTweet();
		} 
		catch (TwitterException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onDisable()
	{
		try
		{
			ServerStopTweet();
		} 
		catch (TwitterException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void ServerStartTweet() throws TwitterException
    {
		if (this.getConfig().getBoolean("ServerStartTweet"))
		{
			Tweet.Tweet(SS_message_temp);
		}
    }
	
	private void ServerStopTweet() throws TwitterException
    {
		if (this.getConfig().getBoolean("ServerStopTweet"))
		{
			Tweet.Tweet(ST_message_temp);
		}
    }
	

	public static TwitterPlugin getPlugin()
	{
		return plugin;
	}
}
