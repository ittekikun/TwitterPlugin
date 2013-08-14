package com.github.ittekikun.Gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.bukkit.plugin.Plugin;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import com.github.ittekikun.TwitterPlugin;

public class OpenGui  implements Runnable
{
	static JButton button1 = new JButton("WEBで認証する");
	static JButton button2 = new JButton("登録");
	static JButton button3 = new JButton("閉じる");
	
	static JTextField field1 = new JTextField("(ここにPINコードを入力)", 1);
	
	static  JFrame mainFrame = new JFrame("Twitter認証");

	Plugin plugin;

	public OpenGui(TwitterPlugin plugin) 
	{
		this.plugin = plugin;
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void run() 
	{
		opengui();
		
		AccessToken accessToken = loadAccessToken();

		
		 if(accessToken != null)
		 {
			 
		 }
		 else
		 {
			 return;
		 }
	}

	private void opengui()
	{
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(400,300);
		mainFrame.setLocationRelativeTo(null);

		Container contentPanel = mainFrame.getContentPane();

		contentPanel.add(button1, BorderLayout.NORTH);
		button1.addActionListener(new ActionAdapter());

		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.LINE_AXIS));
		 panel1.add(field1);	//テキストエリア
		 panel1.add(button2);	//ボタン
		 button2.addActionListener(new ActionAdapter());
		 contentPanel.add(panel1, BorderLayout.CENTER);
		 
		 contentPanel.add(button3, BorderLayout.SOUTH);
		 button3.addActionListener(new ActionAdapter());
		 
		 mainFrame.setVisible(true);
	}

	//アクセストークンの読み込み
    private AccessToken loadAccessToken()
    {
        File f = new File(plugin.getDataFolder() + "/AccessToken.yml");

        ObjectInputStream is = null;
        try 
        {
            is = new ObjectInputStream(new FileInputStream(f));
            AccessToken accessToken = (AccessToken) is.readObject();
            return accessToken;
        }
        catch (IOException e)
        {
            return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        } 
        finally
        {
            if(is != null){
                try 
                {
                    is.close();
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }

	static class ActionAdapter implements ActionListener 
	{
	   static AccessToken accessToken = null;
	   static RequestToken requestToken = null;

		@Override
		public void actionPerformed(ActionEvent event) 
		{
			Twitter twitter;
			   
			final String m_ConsumerKey = "OUiV57ozWTDBE9RaOJiKkA";
			final String m_ConsumerSecret = "9KaLlaWFDjExpKDLhIYx5B3jLojgpgHApdqLzTlKQ";

			twitter = new TwitterFactory().getInstance();
			twitter.setOAuthConsumer(m_ConsumerKey, m_ConsumerSecret);
			
			if (event.getSource() == button1)
			{
				requestToken = Certify.certify(twitter);
			}

			if(event.getSource() == button2)
			{
				Register.register(twitter,field1,requestToken );
			}

			if(event.getSource() == button3)
			{
				Component c = (Component)event.getSource();
				Window w = SwingUtilities.getWindowAncestor(c);
				w.dispose();
			}
		}
		
	}
}
