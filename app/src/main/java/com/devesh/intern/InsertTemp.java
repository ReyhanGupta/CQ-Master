package com.devesh.intern;

//import com.example.sql.Login.BackgroundWorker;

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

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

public class InsertTemp extends Activity {
	String user_email, password, name,id,link, userImageURL;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.creating_account);
		
		user_email= getIntent().getExtras().getString("username");
		password= getIntent().getExtras().getString("password");
		name= getIntent().getExtras().getString("name");

		id= getIntent().getExtras().getString("id");
		link= getIntent().getExtras().getString("link");
		userImageURL= getIntent().getExtras().getString("userImageURL");

		
		BackgroundWorker backgroundWorker= new BackgroundWorker(this);
		backgroundWorker.execute();
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
		// TODO Auto-generated method stub
		//super.onPreExecute();
		alertDialog=new AlertDialog.Builder(context).create();
		alertDialog.setTitle("Resistration status");
		}
	
	
	
	
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
	//	String login_url= "http://10.0.2.2/login.php";
String extra_url= "http://192.168.1.103/extra.php";
		
			try
			{
				
			URL url= new URL(extra_url);
			HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			
			OutputStream outputStream = httpURLConnection.getOutputStream();
			BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

			String post_data= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
					+ URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(user_email,"UTF-8")+"&"
					+ URLEncoder.encode("penname","UTF-8")+"="+URLEncoder.encode("","UTF-8")+"&"

					+ URLEncoder.encode("agenew","UTF-8")+"="+URLEncoder.encode("0","UTF-8")+"&"
					+ URLEncoder.encode("country","UTF-8")+"="+URLEncoder.encode("","UTF-8")+"&"
					
					
					
						+ URLEncoder.encode("uid","UTF-8")+"="+URLEncoder.encode(id,"UTF-8")+"&"
			+ URLEncoder.encode("link","UTF-8")+"="+URLEncoder.encode(link,"UTF-8")+"&"
			+ URLEncoder.encode("userImageURL","UTF-8")+"="+URLEncoder.encode(userImageURL,"UTF-8")


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

			Intent i= new Intent(InsertTemp.this, Login.class);
			i.putExtra("username", user_email);
			i.putExtra("password", "");
			startActivity(i);
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
