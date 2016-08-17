package com.devesh.intern;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.GooglePlayServicesAvailabilityException;
import com.google.android.gms.auth.UserRecoverableAuthException;

import java.io.IOException;

/**
 * Created by RAJUL on 7/10/2016.
 */
public class GetNameForeground extends AbstractGetNameTask {


    public GetNameForeground(GmailMain mActivity, String mEmail, String mScope) {
        super(mActivity, mEmail, mScope);
    }

    @Override
    protected String fetchToken() throws IOException {

        try
        {
            return GoogleAuthUtil.getToken(mActivity, mEmail, mScope);

        }

        catch (GooglePlayServicesAvailabilityException e)
        {

        }

        catch(UserRecoverableAuthException e)
        {
            mActivity.startActivityForResult(e.getIntent(),mRequest);
        }

        catch (GoogleAuthException fatal)
        {

        }
        return null;
    }
}
