package com.devesh.intern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

//import com.example.mysqldemo.BackgroundWorker;

public class Registered extends Activity {
    String user_nameE,passwordE, user_name , password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered);

        user_nameE= getIntent().getExtras().getString("username");
        passwordE= getIntent().getExtras().getString("password");

        BackgroundWorker backgroundWorker= new BackgroundWorker(this);
        backgroundWorker.execute();


    }

    public void backR(View v)
    {
        Intent i= new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private class BackgroundWorker extends AsyncTask<String, Void, String> {
        Context context;
        AlertDialog alertDialog;
        View v;


        BackgroundWorker(Context ctx)
        {
            context=ctx;
        }

        @Override
        protected void onPreExecute() {
            alertDialog=new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Account status");
        }

        @Override
        protected String doInBackground(String... params) {

            try
            {
                //String account_url= "http://10.0.2.2/getSingle.php";192.168.1.104
                String account_url= "http://192.168.1.103/getSingle.php";

                user_name= user_nameE;
                password= passwordE;

                URL url= new URL(account_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String post_data= URLEncoder.encode("user_name","UTF-8")+"="+ URLEncoder.encode(user_name,"UTF-8")+"&"
                        + URLEncoder.encode("password","UTF-8")+"="+ URLEncoder.encode(password,"UTF-8");


                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();



                InputStream inputStream= httpURLConnection.getInputStream();
                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line= bufferedReader.readLine())!=null)
                {
                    result+=line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            }
            catch(MalformedURLException e)
            {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }




        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            //super.onPostExecute(result);

            alertDialog.setMessage(result);


            Intent i= new Intent(Registered.this, MainPage.class);
            startActivity(i);

            if(result.equals("login successful, Welcome!"))
            {

                //Registered object= new Registered();
                //object.rec(sendN, passN);
                //Intent i= new Intent(context, Registered.class);
                //i.putExtra("name", sendN);
                //context.startActivity(i);
            }
            if(result.equals("A Recovery mail has been sent to your e-mail ID. See it for more details."))
            {
                //ForgotPassword obj= new ForgotPassword();
                //obj.recieveResult(result);

            }
            alertDialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
        }

    }


}
