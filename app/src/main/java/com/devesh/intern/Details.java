package com.devesh.intern;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//import com.example.mysqldemo.Information;
//import com.example.mysqldemo.R;
//import com.example.mysqldemo.Details.BackgroundTask;
//import com.example.mysqldemo.InformationAdapter;
//import com.example.mysqldemo.R;
//import com.example.mysqldemo.InformationAdapter;

public class Details extends Activity {
    String json_string;
    String json_string2;
    JSONObject jsonObject;
    JSONArray jsonArray;
    InformationAdapter informationAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        listView=(ListView)findViewById(R.id.listView1);
        //listView.setAdapter(informationAdapter);
        informationAdapter= new InformationAdapter(this, R.layout.row);
        listView.setAdapter(informationAdapter);

        String name=getIntent().getExtras().getString("name");
        String password=getIntent().getExtras().getString("password");

    }


    public void parse_json(View v)
    {
        if(json_string2==null)
        {
            Toast.makeText(this, "First get JSON by pressing the above button", 1999).show();
        }

        else
        {
            try {
                //jsonObject= new JSONObject(json_string2);
                //jsonArray=jsonObject.getJSONArray("students");
                //JSONObject hu=jsonObject.getJSONObject("students");

                int count=0;
                //String id,name,surname, age,username, password, email;
                //while(count<jsonArray.length())
                //{
                //	JSONObject jo= jsonArray.getJSONObject(count);
                //name=jo.getString("name");
                //	email=jo.getString("email");
                ////	JSONObject getObject = jsonObject.getJSONObject("students");
                ////	JSONArray getArray = getObject.getJSONArray("students");
                JSONObject jsonRootObject= new JSONObject(json_string2);
                JSONArray jsonArray = jsonRootObject.optJSONArray("acdata");
                while(count<jsonArray.length())
                {

                    JSONObject jsonObject = jsonArray.getJSONObject(count);
                    int id=jsonObject.optInt("id");
                    String name= jsonObject.optString("name");
                    String surname= jsonObject.optString("surname");
                    int age=jsonObject.optInt("age");
                    String username= jsonObject.optString("username");
                    String password= jsonObject.optString("password");
                    String email=jsonObject.optString("email");


                    Information information= new Information(""+id,name,surname, ""+age,username, password, email);
                    informationAdapter.add(information);

                    count++;
                }



            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }
    }



    public void show(View v)
    {
        new BackgroundTask().execute();
    }


    class BackgroundTask extends AsyncTask<Void, Void, String>
    {
        String json_url;

        @Override
        protected void onPreExecute() {

            //json_url="http://10.0.2.2/getData.php";
            json_url="http://192.168.1.102/getData2.php";

        }

        @Override
        protected String doInBackground(Void... arg0) {

            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                InputStream inputStream= httpURLConnection.getInputStream();
                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder= new StringBuilder();
                while((json_string=bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(json_string+"\n");

                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();



            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            TextView tv= (TextView)findViewById(R.id.textView111);
            tv.setText(result);
            json_string2=result;

        }

    }


}
