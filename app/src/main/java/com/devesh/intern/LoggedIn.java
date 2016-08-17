package com.devesh.intern;

import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.Twitter;
import twitter4j.auth.RequestToken;

public class LoggedIn extends Activity{
	String done, url, ima, name, email,got;
	SharedPreferences sharedpreferences;
	//String recieved;
	public static final String MyPREFERENCES = "MyPrefs" ;
		
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twit_log);


        Uri uri= getIntent().getData();
        String verifier= uri.getQueryParameter("oauth_verifier");

        TwitterGet get= new TwitterGet();
        get.execute(verifier);
		sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);

        
        //sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);
		load();
    }
	
	public void load() {
		// TODO Auto-generated method stub
 		got= sharedpreferences.getString("Key", "Default");

	}

    class TwitterGet extends AsyncTask<String, String, String>
    {

        @Override
        protected String doInBackground(String... params) {


            AccessToken accessToken ;
            Twitter twitter= TwitterUtil.getInstance().getTwitter();
            RequestToken requestToken= TwitterUtil.getInstance().getRequestToken();
            String name="",screen_name="",desc="",image="",image2="";
            try
            {
            	
                accessToken= twitter.getOAuthAccessToken(requestToken, params[0]);
                
                name+="UserID:"+accessToken.getUserId();

              //  name=twitter.showUser(accessToken.getUserId()).getName();
                  name="Image:"+twitter.showUser(accessToken.getUserId()).getProfileImageURL();
                  name=name+"iii";
                  
                  
                  name=name+"Name:"+twitter.showUser(accessToken.getUserId()).getName();
                  name=name+"nnn";
                  
                // name=name+"Description:"+ twitter.showUser(accessToken.getUserId()).getDescription();
                             name=name+"URL:"+ twitter.showUser(accessToken.getUserId()).getURL();
                   name=name+"uuu";
                   
                   
                   
                   
                   
                   
                   
                   
                    name=name+"ID:"+ twitter.showUser(accessToken.getUserId()).getId();
                name+="UserID:"+accessToken.getUserId();
                name=name+"ddd";
                
                
                
                

              //  name=twitter.showUser(accessToken.getUserId()).getProfileImageURL();
                //twitter.
               //// name+=accessToken.getScreenName();
              //  User user= twitter.showUser(screen_name);
           //    name= user.getName();
                //long userID = accessToken.getUserId();
              //  User user = twitter.showUser(userID);
              ///  String screenname = user.getName();
             //   name=name+screen_name;

              //  screen_name=twitter.showUser(accessToken.getUserId()).getScreenName();
               // name=twitter.showUser(accessToken.getUserId()).getBiggerProfileImageURLHttps();
              //  desc=twitter.showUser(accessToken.getUserId()).getDescription();
              //  image=twitter.showUser(accessToken.getUserId()).getOriginalProfileImageURL();
              //  image2=twitter.showUser(accessToken.getUserId()).getProfileImageURL();
               // name=twitter.showUser(accessToken.getUserId()).getId();
              //  name=twitter.showUser(accessToken.getUserId()).

           // name= name+"\n"+screen_name+"\n"+desc+"\n"+image+"\n"+image2;
            }

            catch (Exception e)
            {
                e.printStackTrace();
            }
            return name;

        }

        @Override
        protected void onPostExecute(String s) {
            Log.e("kkkk", "Reached PostExec");
            Toast.makeText(LoggedIn.this,"here it goes:"+ s, Toast.LENGTH_SHORT).show();
            
          //  if(s.length()>0)
          //  {
            SharedPreferences.Editor editor = sharedpreferences.edit();
			editor.putString("Key", s);
			editor.commit();
			
			SharedPreferences getPref= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		got= getPref.getString("Key", "Default");
          Toast.makeText(LoggedIn.this, "Got: "+got, Toast.LENGTH_SHORT).show();

          //  }

            
           int pos1= got.indexOf("iii");
            //String 
            ima= got.substring(6,pos1 );
            Toast.makeText(LoggedIn.this, ima, Toast.LENGTH_SHORT).show();
            
            int pos2= pos1+8;
            int nameEnd= got.indexOf("nnn");
           // String
            name= got.substring(pos2, nameEnd);
            Toast.makeText(LoggedIn.this, name, Toast.LENGTH_SHORT).show();

           // int pos3= pos2+8;
            int urlEnd= got.indexOf("uuu");
            
           // String
            url= got.substring(nameEnd+7,urlEnd );
            Toast.makeText(LoggedIn.this, url, Toast.LENGTH_SHORT).show();
            
           
            int end= got.indexOf("UserID");
            
            //String 
            done= got.substring(urlEnd+7,end );
            Toast.makeText(LoggedIn.this, done, Toast.LENGTH_SHORT).show();
            
            
            
          //  SharedPreferences.Editor editor = sharedpreferences.edit();
		//	editor.putString("Key", name);
		//	editor.commit();
            email="";
            Intent i= new Intent(LoggedIn.this, ValidateTwitterID.class);
        	i.putExtra("id", done);
        	i.putExtra("link", url);
        	i.putExtra("userImageURL", ima);


        	i.putExtra("name", name);
        	i.putExtra("email", email);
        	startActivity(i);

         //   String pos2= s.indexOf(c);

        }
    }

}
