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

//import com.example.sql.ExistingInsert.BackgroundWorker;
//import com.example.sql.ExistingInsert.PenWorker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ExistingInsertTwitter extends Activity {
	SharedPreferences getPref;

	
	String id;
	EditText p, a, c,e;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.extra_twitter);
		
		
		getPref= PreferenceManager.getDefaultSharedPreferences(getBaseContext());

		String got= getPref.getString("Key", "Default");
        //Toast.makeText(this, "kkkk:"+got, Toast.LENGTH_SHORT).show();

		
		 String email="";
		 int pos1= got.indexOf("iii");
		String userImageURL = got.substring(6,pos1 );
         
         int pos2= pos1+8;
         int nameEnd= got.indexOf("nnn");
         String name= got.substring(pos2, nameEnd);

         int urlEnd= got.indexOf("uuu");
         
         String link= got.substring(nameEnd+7,urlEnd );         
        
         int end= got.indexOf("UserID");
         id= got.substring(urlEnd+7,end );
         
		
		

		p=(EditText)findViewById(R.id.et_pen);
		a=(EditText)findViewById(R.id.et_age);
		c=(EditText)findViewById(R.id.et_country);
		e=(EditText)findViewById(R.id.emat_twit);
		
		

	}
	
	public void skip(View v)
	{
		//Intent i= new Intent(this, Login.class); //earlier went to loginclass
		//i.putExtra("username", email);
	//i.putExtra("password", "");
//		startActivity(i);

	}
	
	public void saveExtra(View v)
	{
		PenWorker pen= new PenWorker(this);
		pen.execute();
		
		BackgroundWorker backgroundWorker= new BackgroundWorker(this);
		backgroundWorker.execute();
	}
	
	
	
private class BackgroundWorker extends AsyncTask<String, Void, String> {
		
		
		Context context;

		AlertDialog alertDialog;
		
		View v;
		
		String	penname=p.getText().toString();
		String	agenew	= a.getText().toString();
		String	country= c.getText().toString();
		String	email= e.getText().toString();


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
		String existing_insert_twitter_url= "http://192.168.1.103/existing_insert_twitter.php";


	URL url= new URL(existing_insert_twitter_url);
	HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
	httpURLConnection.setRequestMethod("POST");
	httpURLConnection.setDoOutput(true);
	httpURLConnection.setDoInput(true);
	
	OutputStream outputStream = httpURLConnection.getOutputStream();
	BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

	String post_data= //URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
			 URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8")+"&"
			+ URLEncoder.encode("penname","UTF-8")+"="+URLEncoder.encode(penname,"UTF-8")+"&"
			+ URLEncoder.encode("agenew","UTF-8")+"="+URLEncoder.encode(agenew,"UTF-8")+"&"
						+ URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"

			+ URLEncoder.encode("country","UTF-8")+"="+URLEncoder.encode(country,"UTF-8")

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
		
		if(result.equals("Update successful!"))
		{

			//Intent i= new Intent(Extra.this, Login.class);
			//i.putExtra("username", email);
			//i.putExtra("password", "");
			//startActivity(i);
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

private class PenWorker extends AsyncTask<String, Void, String> {
	
	
	Context context;

	AlertDialog alertDialog;
	
	View v;
	
	String	penname=p.getText().toString();
	
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
	String pen_url= "http://192.168.1.102/pen_validate.php";


URL url= new URL(pen_url);
HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
httpURLConnection.setRequestMethod("POST");
httpURLConnection.setDoOutput(true);
httpURLConnection.setDoInput(true);

OutputStream outputStream = httpURLConnection.getOutputStream();
BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

String post_data= URLEncoder.encode("penname","UTF-8")+"="+URLEncoder.encode(penname,"UTF-8")
		
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
	
	//alertDialog.setMessage(result);
	
	//if(result.equals("Pen Name already exixts. Please enter a unique Pen Name"))
	//{
Toast.makeText(ExistingInsertTwitter.this, result, 2000).show();
		
	//}
	//if(result.equals("Loading..."))
	//{
	//	Toast.makeText(ExistingInsertTwitter.this, result, 2000).show();

		//BackgroundWorker backgroundWorker= new BackgroundWorker(ExistingInsertTwitter.this);
		//backgroundWorker.execute();
//	}
	//alertDialog.show();
}

@Override
protected void onProgressUpdate(Void... values) {
	// TODO Auto-generated method stub
	super.onProgressUpdate(values);
}
	
}
	


}
