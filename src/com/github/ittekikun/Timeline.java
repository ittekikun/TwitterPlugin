package com.github.ittekikun;

import twitter4j.DirectMessage;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.UserStreamListener;
import twitter4j.auth.AccessToken;
public final class Timeline 
{
	public static void ShowTimeline() 
	{
		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
		twitterStream.setOAuthConsumer(TwitterPlugin.consumerKey,TwitterPlugin.consumerSecret);
		twitterStream.setOAuthAccessToken(new AccessToken(TwitterPlugin.accessToken, TwitterPlugin.accessTokenSecret));
		
        twitterStream.addListener(listener);
        twitterStream.user();
    }

    static UserStreamListener listener = new UserStreamListener() 
    {
        @Override
        public void onStatus(Status status)
        {
        	String Message = replaceKeywords(TwitterPlugin.T_message_temp, status.getUser().getScreenName(), status.getText());
        	TwitterPlugin.plugin.getServer().broadcastMessage(Message);
            //System.out.println("onStatus @" + status.getUser().getScreenName() + " - " + status.getText());
        }

        @Override
        public void onException(Exception ex)
        {
            ex.printStackTrace();
            System.out.println("onException:" + ex.getMessage());
        }
        
        private String replaceKeywords(String source,String name, String message)
    	{
    		String result = source;
            if ( result.contains(TwitterPlugin.KEYWORD_USER) )
            {
                result = result.replace(TwitterPlugin.KEYWORD_USER, name);
            }
            if ( result.contains(TwitterPlugin.KEYWORD_MESSAGE) )
            {
                result = result.replace(TwitterPlugin.KEYWORD_MESSAGE, message);
            }
            return result;
        }
		@Override
		public void onDeletionNotice(StatusDeletionNotice arg0) {
			// TODO 自動生成されたメソッド・スタブ
			
		}


		@Override
		public void onScrubGeo(long arg0, long arg1) {
			// TODO 自動生成されたメソッド・スタブ
			
		}


		@Override
		public void onStallWarning(StallWarning arg0) {
			// TODO 自動生成されたメソッド・スタブ
			
		}


		@Override
		public void onTrackLimitationNotice(int arg0) {
			// TODO 自動生成されたメソッド・スタブ
			
		}


		@Override
		public void onBlock(User arg0, User arg1) {
			// TODO 自動生成されたメソッド・スタブ
			
		}


		@Override
		public void onDeletionNotice(long arg0, long arg1) {
			// TODO 自動生成されたメソッド・スタブ
			
		}


		@Override
		public void onDirectMessage(DirectMessage arg0) {
			// TODO 自動生成されたメソッド・スタブ
			
		}


		@Override
		public void onFavorite(User arg0, User arg1, Status arg2) {
			// TODO 自動生成されたメソッド・スタブ
			
		}


		@Override
		public void onFollow(User arg0, User arg1) {
			// TODO 自動生成されたメソッド・スタブ
			
		}


		@Override
		public void onFriendList(long[] arg0) {
			// TODO 自動生成されたメソッド・スタブ
			
		}


		@Override
		public void onUnblock(User arg0, User arg1) {
			// TODO 自動生成されたメソッド・スタブ
			
		}


		@Override
		public void onUnfavorite(User arg0, User arg1, Status arg2) {
			// TODO 自動生成されたメソッド・スタブ
			
		}


		@Override
		public void onUserListCreation(User arg0, UserList arg1) {
			// TODO 自動生成されたメソッド・スタブ
			
		}


		@Override
		public void onUserListDeletion(User arg0, UserList arg1) {
			// TODO 自動生成されたメソッド・スタブ
			
		}


		@Override
		public void onUserListMemberAddition(User arg0, User arg1, UserList arg2) {
			// TODO 自動生成されたメソッド・スタブ
			
		}


		@Override
		public void onUserListMemberDeletion(User arg0, User arg1, UserList arg2) {
			// TODO 自動生成されたメソッド・スタブ
			
		}


		@Override
		public void onUserListSubscription(User arg0, User arg1, UserList arg2) {
			// TODO 自動生成されたメソッド・スタブ
			
		}


		@Override
		public void onUserListUnsubscription(User arg0, User arg1, UserList arg2) {
			// TODO 自動生成されたメソッド・スタブ
			
		}


		@Override
		public void onUserListUpdate(User arg0, UserList arg1) {
			// TODO 自動生成されたメソッド・スタブ
			
		}


		@Override
		public void onUserProfileUpdate(User arg0) {
			// TODO 自動生成されたメソッド・スタブ
			
		}
    };
}