package com.github.ittekikun.Gui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.bukkit.plugin.Plugin;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import com.github.ittekikun.TwitterPlugin;

public class Register 
{
	static Plugin plugin = TwitterPlugin.plugin;

	public static AccessToken register(Twitter twitter, JTextField field1, RequestToken  requestToken) 
	{
		AccessToken accessToken = null;
		String pin = field1.getText();
		Boolean IsInt = isInteger(pin);
		 try
		 {
			 if(requestToken != null)
			 {
				 if(pin.length() == 7)
	             {
	            	 if(IsInt)
	            	 {
	            		 accessToken = twitter.getOAuthAccessToken(requestToken, pin);
	                	 storeAccessToken(accessToken);
	                	 JOptionPane.showMessageDialog(null, "登録しました。");
	            	 }
	            	 else
	            	 {
	            		 JOptionPane.showMessageDialog(null, "何も入力されていません。\r\nもしくは入力されているPINコードが間違っています。");
	            	 }
	             }
	             else
	             {
	            	 JOptionPane.showMessageDialog(null, "何も入力されていません。\r\nもしくは入力されているPINコードが間違っています。");
	             }
			 }
			 else
			 {
				 JOptionPane.showMessageDialog(null, "まだWEBで認証していません。");
			 }
		 }
		 catch (TwitterException te) 
		 {
			 if(401 == te.getStatusCode())
			{
				 JOptionPane.showMessageDialog(null, "アクセストークンを取得できませんでした。\r\nもしくは既に取得済みか、\r\n入力されているPINコードが間違っています。\r\nもう一度WEBで認証してください。");
			}
			 else
			 {
				 JOptionPane.showMessageDialog(null, "例外が発生しました。\r\n恐らくPINコードが正しく入力されていません。\r\nもう一度WEBで認証してください。");
				 te.printStackTrace();
			 }
         }
		return accessToken;
	}

	 private static void storeAccessToken(AccessToken accessToken)
	 {
	        //ファイル名の生成
	        File f = createAccessTokenFileName();

	        //親ディレクトリが存在しない場合，親ディレクトリを作る．
	        File d = f.getParentFile();
	        if (!d.exists()) 
	        {
	        	d.mkdirs();
	        }

	        //ファイルへの書き込み
	        ObjectOutputStream os = null;
	        try 
	        {
	            os = new ObjectOutputStream(new FileOutputStream(f));
	            os.writeObject(accessToken);
	        } 
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        } 
	        finally 
	        {
	            if(os != null){
	                try 
	                {
	                    os.close();
	                }
	                catch (IOException e) 
	                {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }

	    // アクセストークンを保存するファイル名を生成する
	    static File createAccessTokenFileName() 
	    {
	    	System.out.println(plugin.getDataFolder());
	    	String s = plugin.getDataFolder() + "/AccessToken.yml";
	        return new File(s);
	    }

	    static boolean isInteger(String num)
	    {
	    	try 
	    	{
	    	int n = Integer.parseInt(num);
	    	return true;
	    	}
	    	catch (NumberFormatException e) 
	    	{
	    	return false;
	    	}
	    }
}
