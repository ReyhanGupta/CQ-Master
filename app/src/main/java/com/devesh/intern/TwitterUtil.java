package com.devesh.intern;

import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.Twitter;
public class TwitterUtil {
	
	 public RequestToken requestToken = null;
	   public TwitterFactory twitterFactory = null;
	    public Twitter twitter;

	    private TwitterUtil()
	    {ConfigurationBuilder cb = new ConfigurationBuilder();
	        cb.setOAuthAuthenticationURL("https://api.twitter.com/oauth/request_token");
	        cb.setOAuthAccessTokenURL("https://api.twitter.com/oauth/access_token");
	        cb.setOAuthAuthorizationURL("https://api.twitter.com/oauth/authorize");
	        cb.setOAuthRequestTokenURL("https://api.twitter.com/oauth/request_token");
	        cb.setRestBaseURL("https://api.twitter.com/1.1/");
	        cb.setOAuthConsumerKey("t3pMg4GFAVhA33ulrngET8duD");
	        cb.setOAuthConsumerSecret("7MK79rGgOGvHCNlJ4oGSKGkRxzMYJ1Yq8xqxVtTMq4FlDTWL9v");


	        Configuration configuration = cb.build();


	        twitterFactory = new TwitterFactory(configuration);
	        twitter = twitterFactory.getInstance();

	        AccessToken accessToken = new AccessToken("TWITTER_OAUTH_TOKEN","TWITTER_OAUTH_TOKEN_SECRET");
	        twitter.setOAuthAccessToken(accessToken);
	    }

	    public TwitterFactory getTwitterFactory()
	    {
	        return twitterFactory;
	    }

	    public void setTwitterFactory(AccessToken accessToken)
	    {
	        twitter = twitterFactory.getInstance(accessToken);
	    }

	    public Twitter getTwitter()
	    {
	        return twitter;
	    }
	    public RequestToken getRequestToken() {
	        if (requestToken == null) {
	            try {
	                requestToken = twitterFactory.getInstance().getOAuthRequestToken("oauth://testing");
	            } catch (TwitterException e) {
	                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	            }
	        }
	        return requestToken;
	    }

	    static TwitterUtil instance = new TwitterUtil();

	    public static TwitterUtil getInstance() {
	        return instance;
	    }


	    public void reset() {
	        instance = new TwitterUtil();
	    }

}
