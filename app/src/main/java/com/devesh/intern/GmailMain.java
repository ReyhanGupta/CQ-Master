package com.devesh.intern;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthUtil;
//import com.google.android.gms.plus.Account;

/**
 * Created by RAJUL on 7/10/2016.
 */
public class GmailMain extends Activity {
    Context mContext= GmailMain.this;
    AccountManager mAccountManager;
    String token;
    int serverCode;
    private static final String SCOPE= "oauth2:https://www.googleapis.com/auth/userinfo.profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gmail_login);
        syncGoogleAccount();
    }


    private String[] getAccountNames()
    {
        mAccountManager= AccountManager.get(this);
        Account[] accounts=mAccountManager.getAccountsByType(GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
        String[] names= new String[accounts.length];
        for(int i=0; i<names.length; i++)
        {
            names[i]= accounts[i].name;

        }
        return  names;
    }


    private AbstractGetNameTask getTask (GmailMain activity, String email, String scope)
    {
        return new GetNameForeground(activity, email, scope);
    }

    public void syncGoogleAccount()
    {
        if(isNetworkAvailable()==true)
        {
            String[] accountarrs= getAccountNames();
            if(accountarrs.length>0)
            {
                getTask(GmailMain.this, accountarrs[0], SCOPE).execute();

            }

            else
            {
                Toast.makeText(GmailMain.this, "No Google Account Sync!", Toast.LENGTH_LONG).show();

            }
        }



        else
        {
            Toast.makeText(GmailMain.this, "No Internet", Toast.LENGTH_LONG).show();

        }
    }

    public boolean isNetworkAvailable()
    {
        ConnectivityManager cm= (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= cm.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected())
        {
            Toast.makeText(GmailMain.this, "Internet Available", Toast.LENGTH_LONG).show();

            return true;
        }
        else
        {
            Toast.makeText(GmailMain.this, "Internet Unavailable", Toast.LENGTH_LONG).show();

            return false;
        }
    }

}
