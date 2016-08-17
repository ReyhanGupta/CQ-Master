package com.devesh.intern;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by RAJUL on 7/10/2016.
 */
public class HomeActivity extends Activity {
    ImageView imageProfile;
    TextView textViewName,textViewEmail,textViewGender;
    String textName, textEmail,textGender,userImageURL, id,link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        textViewName=(TextView)findViewById(R.id.textViewNameValue);
        textViewEmail=(TextView)findViewById(R.id.textViewEmailValue);
        textViewGender=(TextView)findViewById(R.id.textViewGenderValue);
        imageProfile= (ImageView)findViewById(R.id.imageView);

        Intent intent = getIntent();
        textEmail=intent.getStringExtra("email_id");
        textViewEmail.setText(textEmail);

        try
        {
            JSONObject profileData= new JSONObject(AbstractGetNameTask.GOOGLE_USER_DATA);
            if(profileData.has("picture"))
            {
                userImageURL=profileData.getString("picture");
                new GetImageFromUrl().execute(userImageURL);

            }
            if(profileData.has("name"))
            {
                textName=profileData.getString("name");
                textViewName.setText(textName);

            }

            if(profileData.has("gender"))
            {
                textGender=profileData.getString("gender");
                textViewGender.setText(textGender);

            }


            id= profileData.getString("id");
            link= profileData.getString("link");

        }

        catch(JSONException e)
        {

        }


    }

    public void enter(View v)
    {
        textViewName=(TextView)findViewById(R.id.textViewNameValue);
        String name= textViewName.getText().toString();
        textViewEmail=(TextView)findViewById(R.id.textViewEmailValue);
        String email= textViewEmail.getText().toString();

        Intent i= new Intent(this, ValidateEmail.class);
        i.putExtra("id", id);
        i.putExtra("link", link);
        i.putExtra("userImageURL", userImageURL);
        i.putExtra("name", name);
        i.putExtra("email", email);

        startActivity(i);

    }




    public class GetImageFromUrl extends AsyncTask<String, Void, Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map= null;

            for(String url:urls)
            {
                map=downloadImage(url);
            }
            return  map;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageProfile.setImageBitmap(bitmap);


        }

        private Bitmap downloadImage(String url)
        {
            Bitmap bitmap= null;
            InputStream stream = null;
            BitmapFactory.Options bmoptions= new  BitmapFactory.Options();
            bmoptions.inSampleSize=1;


            try
            {
                stream= getHTTPConnection(url);
                bitmap = BitmapFactory.decodeStream(stream, null, bmoptions);
                stream.close();

            }
            catch (IOException e)
            {

            }

            return  bitmap;
        }


        private InputStream getHTTPConnection(String urlString) throws IOException
        {
            InputStream stream = null;
            URL url= new URL(urlString);
            URLConnection connection=  url.openConnection();


            try
            {
                HttpURLConnection httpURLConnection  = (HttpURLConnection)connection;
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();


                if(httpURLConnection.getResponseCode()== HttpURLConnection.HTTP_OK)
                {
                    stream= httpURLConnection.getInputStream();
                }



            }


            catch (Exception e)
            {

            }


            return  stream;
        }



    }


}
