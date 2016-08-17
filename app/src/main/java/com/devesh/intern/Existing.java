package com.devesh.intern;

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

//import com.example.sql.InsertTemp.BackgroundWorker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Existing extends Activity {
	String email,name,id,link, userImageURL;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.creating_account);
	//	TextView tv= (TextView)findViewById(R.id.textViewCA);
		//tv.setText("Loading");
		
		email= getIntent().getExtras().getString("email");
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
String existing_validate= "http://192.168.1.103/existing_validate.php";
		
			try
			{
				
			URL url= new URL(existing_validate);
			HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			
			OutputStream outputStream = httpURLConnection.getOutputStream();
			BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

			String post_data= URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")
					
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
		
		if(result.equals("Please Fill Extra Details"))
		{

			Intent i= new Intent(Existing.this, ExistingInsert.class);
			i.putExtra("email", email);
			startActivity(i);
		}
		if(result.equals("Loading..."))
		{
			Intent i= new Intent(Existing.this, MainPage.class);
			startActivity(i);


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
