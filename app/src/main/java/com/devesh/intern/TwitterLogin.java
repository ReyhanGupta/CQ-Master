package com.devesh.intern;

import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.Twitter;
public class TwitterLogin extends Activity{
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.tweet_btn);

	        Button btn=(Button)findViewById(R.id.tweet_btn);
	        btn.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                TwitterSIgnin twitterSIgnin = new TwitterSIgnin();
	                twitterSIgnin.execute("");
	            }
	        });
	    }



	    class TwitterSIgnin extends AsyncTask<String, String, RequestToken>
	    {
	        @Override
	        protected RequestToken doInBackground(String... params) {
	            return TwitterUtil.getInstance().getRequestToken();
	        }

	        @Override
	       protected void onPostExecute(RequestToken requestToken) {


	           if(requestToken!=null)
	           {
	              // Intent intent= new Intent(Intent.ACTION_SEND, Uri.parse(requestToken.getAuthenticationURL()));
	              // startActivity(intent);
	               Intent i= new Intent(Intent.ACTION_VIEW);
	               i.setData(Uri.parse(requestToken.getAuthenticationURL()));
	               startActivity(i);

	               //String url= Uri.parse(requestToken.getAuthenticationURL());

	           }

	        }
	    }

}
