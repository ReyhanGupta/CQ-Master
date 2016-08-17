package com.devesh.intern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by RAJUL on 7/12/2016.
 */
public class Facebook extends Activity {
    private CallbackManager mcallbackManager;


   /* private FacebookCallback<LoginResult> mcallBack= new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken= loginResult.getAccessToken();
            Profile profile= Profile.getCurrentProfile();
            if(profile!=null)
            {
                //  Toast.makeText(Facebook.this, ""+profile.getFirstName(),Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        mcallbackManager=CallbackManager.Factory.create();
        setContentView(R.layout.facebook);



        LoginButton fb= (LoginButton)findViewById(R.id.login_button);
        fb.setReadPermissions("user_friends");
       // fb.registerCallback(mcallbackManager,mcallBack);


        fb.registerCallback(mcallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        mcallbackManager.onActivityResult(requestCode, resultCode,data);
    }
}
