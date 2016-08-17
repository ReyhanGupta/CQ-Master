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

//import com.example.sql.ValidateEmail.BackgroundWorker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

public class ValidateTwitterID extends Activity{
	String name, email,id,link,userImageURL;
	SharedPreferences getPref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.validate_email);
		
		
		getPref= PreferenceManager.getDefaultSharedPreferences(getBaseContext());

		String got= getPref.getString("Key", "Default");
		Toast.makeText(ValidateTwitterID.this, "final:"+got, 1000).show();

		int pos1= got.indexOf("iii");
		String ima= got.substring(6,pos1 );

		int pos2= pos1+8;
		int nameEnd= got.indexOf("nnn");
		String name= got.substring(pos2, nameEnd);

		int urlEnd= got.indexOf("uuu");

		String url= got.substring(nameEnd+7,urlEnd );

		int end= got.indexOf("UserID");
		id= got.substring(urlEnd+7,end );
		Toast.makeText(this, "newid is: "+id, Toast.LENGTH_LONG).show();

		//String got= getPref.getString("Key", "Default");



		//int pos1= got.indexOf("iii");
		//String ima= got.substring(6,pos1 );

		//int pos2= pos1+8;
		//int nameEnd= got.indexOf("nnn");
		//String name= got.substring(pos2, nameEnd);

		//int urlEnd= got.indexOf("uuu");

		//String url= got.substring(nameEnd+7,urlEnd );

		//int end= got.indexOf("UserID");
		//String id= got.substring(urlEnd+7,end );

	//	Toast.makeText(this, got, 1000).show();
		
		
		//name= getIntent().getExtras().getString("name");
		//email= getIntent().getExtras().getString("email");
	//////////////////////////////////////////////	id= getIntent().getExtras().getString("id");
		//link= getIntent().getExtras().getString("link");
		//userImageURL= getIntent().getExtras().getString("userImageURL");

		
		BackgroundWorker backgroundWorker= new BackgroundWorker(this);
	///////////////////////////////////////////
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
		String twit_validate= "http://192.168.1.103/twit_validate.php";


	URL url= new URL(twit_validate);
	HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
	httpURLConnection.setRequestMethod("POST");
	httpURLConnection.setDoOutput(true);
	httpURLConnection.setDoInput(true);
	
	OutputStream outputStream = httpURLConnection.getOutputStream();
	BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

	String post_data= URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8")
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
			
			
			String got= getPref.getString("Key", "Default");
		Toast.makeText(ValidateTwitterID.this, "final:"+got, 1000).show();

		 int pos1= got.indexOf("iii");
         String ima= got.substring(6,pos1 );
         
         int pos2= pos1+8;
         int nameEnd= got.indexOf("nnn");
         String name= got.substring(pos2, nameEnd);

         int urlEnd= got.indexOf("uuu");
         
         String url= got.substring(nameEnd+7,urlEnd );         
        
         int end= got.indexOf("UserID");
         String id= got.substring(urlEnd+7,end );
         
         
			
			email="";

			Intent i= new Intent(ValidateTwitterID.this, ExistingTwitter.class);
	    	i.putExtra("name", name);
	    	i.putExtra("email", email);
	    	i.putExtra("id", id);
	    	i.putExtra("link", url);
	    	i.putExtra("userImageURL", ima);

	    	startActivity(i);

		}
		if(result.equals("Creating Account..."))
		{
			String got= getPref.getString("Key", "Default");
			
			
			
			 int pos1= got.indexOf("iii");
	         String ima= got.substring(6,pos1 );
	         
	         int pos2= pos1+8;
	         int nameEnd= got.indexOf("nnn");
	         String name= got.substring(pos2, nameEnd);

	         int urlEnd= got.indexOf("uuu");
	         
	         String url= got.substring(nameEnd+7,urlEnd );         
	        
	         int end= got.indexOf("UserID");
	         String id= got.substring(urlEnd+7,end );
	         
	         
			//Toast.makeText(ValidateTwitterID.this, "final:"+got, 1000).show();

			email="";
		Intent i= new Intent(ValidateTwitterID.this, ExtraTwitter.class);
	    	i.putExtra("name", name);
	    	i.putExtra("email", email);
	    	i.putExtra("id", id);
	    	i.putExtra("link", url);
	    	i.putExtra("userImageURL", ima);

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
