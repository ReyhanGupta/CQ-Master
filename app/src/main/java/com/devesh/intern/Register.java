package com.devesh.intern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
//import com.example.mysqldemo.R;

public class Register extends Activity {
    EditText nameE,surnameE,ageE,usernameE, passwordE,emailE;
    String name,surname,age,username,password,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register);
        nameE=(EditText)findViewById(R.id.et_name);
        surnameE=(EditText)findViewById(R.id.et_surname);
        ageE=(EditText)findViewById(R.id.et_age);
        usernameE=(EditText)findViewById(R.id.et_username);
        passwordE=(EditText)findViewById(R.id.et_password);
        emailE=(EditText)findViewById(R.id.emails);



    }


    public void onReg(View v)
    {

      ///  BackgroundWorker backgroundWorker= new BackgroundWorker(this);
        //backgroundWorker.execute();

        PenWorker pen= new PenWorker(this);
        pen.execute();

      //  BackgroundWorker backgroundWorker= new BackgroundWorker(this);
      //  backgroundWorker.execute();


    }





    private class BackgroundWorker extends AsyncTask<String, Void, String> {
        Context context;
        AlertDialog alertDialog;
        View v;

        String name=nameE.getText().toString();
        String surname= surnameE.getText().toString();
        String age	= ageE.getText().toString();
        String username= usernameE.getText().toString();
        String password= passwordE.getText().toString();
        String email= emailE.getText().toString();

        BackgroundWorker(Context ctx)
        {
            context=ctx;
        }

        @Override
        protected void onPreExecute() {
            alertDialog=new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Register status");
        }

        @Override
        protected String doInBackground(String... params) {

            try
            {
                //String register_url= "http://10.0.2.2/register.php";192.168.1.104
                String register_url= "http://192.168.1.103/register.php";


                URL url= new URL(register_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String post_data= URLEncoder.encode("name","UTF-8")+"="+ URLEncoder.encode(name,"UTF-8")+"&"
                        + URLEncoder.encode("surname","UTF-8")+"="+ URLEncoder.encode(surname,"UTF-8")+"&"
                        + URLEncoder.encode("age","UTF-8")+"="+ URLEncoder.encode(age,"UTF-8")+"&"
                        + URLEncoder.encode("username","UTF-8")+"="+ URLEncoder.encode(username,"UTF-8")+"&"
                        + URLEncoder.encode("password","UTF-8")+"="+ URLEncoder.encode(password,"UTF-8")+"&"
                        + URLEncoder.encode("email","UTF-8")+"="+ URLEncoder.encode(email,"UTF-8")

                        ;

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

            if(result.equals("Insert successful!"))
            {

                Intent i= new Intent(Register.this, MainPage.class);
                startActivity(i);

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


    public class PenWorker extends AsyncTask<String, Void, String> {


        Context context;

        AlertDialog alertDialog;

        View v;


        PenWorker(Context ctx)
        {
            context=ctx;
        }


        @Override
        protected void onPreExecute() {
            alertDialog=new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Register status");
        }

        @Override
        protected String doInBackground(String... params) {

            try
            {
                String pen_url= "http://192.168.1.103/reg_pen_val.php";
                String penname= surnameE.getText().toString();

                URL url= new URL(pen_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String post_data= URLEncoder.encode("penname", "UTF-8")+"="+URLEncoder.encode(penname,"UTF-8")

                       ;

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

            if (result.equals("Pen Name already exixts. Please enter a unique Pen Name")) {
                 Toast.makeText(Register.this, result, Toast.LENGTH_LONG).show();

            }
            if (result.equals("Loading...")) {
                //	Toast.makeText(ExistingInsertTwitter.this, result, 2000).show();

                BackgroundWorker backgroundWorker = new BackgroundWorker(Register.this);
                backgroundWorker.execute();
//	}
                alertDialog.show();
            }

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
        }

    }


}
