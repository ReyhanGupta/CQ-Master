package com.devesh.intern;

//import com.example.sql.Extra.BackgroundWorker;

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

public class ValidateEmail extends Activity{
	String name, email,id,link,userImageURL;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.validate_email);
		
		name= getIntent().getExtras().getString("name");
		email= getIntent().getExtras().getString("email");
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
		alertDialog=new AlertDialog.Builder(context).create();
		alertDialog.setTitle("Validation status");
		}
	
	@Override
	protected String doInBackground(String... params) {

	try
	{
		String email_validate= "http://192.168.1.103/email_validate.php";


	URL url= new URL(email_validate);
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
		
		if(result.equals("Welcome!"))
		{

			Intent i= new Intent(ValidateEmail.this, Existing.class);
	    	i.putExtra("name", name);
	    	i.putExtra("email", email);
	    	i.putExtra("id", id);
	    	i.putExtra("link", link);
	    	i.putExtra("userImageURL", userImageURL);

	    	startActivity(i);

		}
		if(result.equals("Creating Account..."))
		{
			Intent i= new Intent(ValidateEmail.this, Extra.class);
	    	i.putExtra("name", name);
	    	i.putExtra("email", email);
	    	i.putExtra("id", id);
	    	i.putExtra("link", link);
	    	i.putExtra("userImageURL", userImageURL);

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
