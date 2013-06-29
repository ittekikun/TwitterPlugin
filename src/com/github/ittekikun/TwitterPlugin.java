package com.github.ittekikun;

import java.util.Date;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import com.github.ittekikun.Event.LoginEvent;
import com.github.ittekikun.Event.McbansEvent;
import com.github.ittekikun.Uti.Uti;


public class TwitterPlugin extends JavaPlugin{
	
	Logger logger = Logger.getLogger("minecraft");
	public static TwitterPlugin plugin; 
	
	public static String consumerKey;
	public static String consumerSecret;
	public static String accessToken;
	public static String accessTokenSecret;
	
	public static String I_message_temp;
	public static String O_message_temp;
	
	public static String K_message_temp;
	public static String B_message_temp;
	
	public static final String KEYWORD_USER = "$user";
	public static final String KEYWORD_REASON = "$reason";
	public static final String KEYWORD_SENDER = "$sender";
	public static final String KEYWORD_NUMBER = "$number";

	@Override
	public void onEnable()
	{
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
			getServer().getPluginManager().registerEvents(new McbansEvent(), this);
		}
		
		//getCommand("tw").setExecutor(new CommandTweet());
		getCommand("TwitterPlugin").setExecutor(new CommandTwitterPlugin());
		getCommand("twp").setExecutor(new CommandTwitterPlugin());
		
		
		consumerKey = this.getConfig().getString("consumerKey","xxxxxxxxxx");
		consumerSecret = this.getConfig().getString("consumerSecret","xxxxxxxxxx");
		accessToken = this.getConfig().getString("accessToken","xxxxxxxxxx");
		accessTokenSecret = this.getConfig().getString("accessTokenSecret","xxxxxxxxxx");
		
		I_message_temp = this.getConfig().getString("LoginMessageTemplate", "$userさんがサーバーにログインしました。現在$number人がログインしています。【自動投稿】");
		O_message_temp = this.getConfig().getString("LogoutMessageTemplate", "$userさんがサーバーからログアウトしました。現在$number人がログインしています。【自動投稿】");
		
		K_message_temp = this.getConfig().getString("KickMessageTemplate", "$userが、「$reason」という理由で、$senderによってKICKされました。【自動投稿】");
		B_message_temp = this.getConfig().getString("BanMessageTemplate", "$userが、「$reason」という理由で、$senderによってグローバルBANされました。【自動投稿】");
		
		ServerStartTweet();
	}

	@Override
	public void onDisable()
	{
		ServerStopTweet();
	}
	
	private void ServerStartTweet()
    {
		if (this.getConfig().getBoolean("ServerStartTweet"))
		{
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(TwitterPlugin.consumerKey,TwitterPlugin.consumerSecret);
			twitter.setOAuthAccessToken(new AccessToken(TwitterPlugin.accessToken, TwitterPlugin.accessTokenSecret));
			
			Date Time = Uti.TimeGet();  
			
			try {
				twitter.updateStatus("サーバーを起動しました。【自動投稿】" + "\n" +Time);
			} catch (TwitterException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
    }
	
	private void ServerStopTweet()
    {
		if (this.getConfig().getBoolean("ServerStopTweet"))
		{
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(TwitterPlugin.consumerKey,TwitterPlugin.consumerSecret);
			twitter.setOAuthAccessToken(new AccessToken(TwitterPlugin.accessToken, TwitterPlugin.accessTokenSecret));
			
			Date Time = Uti.TimeGet();  
			
			try {
				twitter.updateStatus("サーバーを終了しました。【自動投稿】" + "\n" +Time);
			} catch (TwitterException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
    }
	

	public static TwitterPlugin getPlugin()
	{
		return plugin;
	}
	
	
}
