package com.devesh.intern;


import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.devesh.intern.Tables.SQL_Commands;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadMode extends Fragment {

// Database
    SQLiteDatabase database;

    // Declaration
    ArrayList<Info> myList;
    ListView myView;
    Adapter listAdapter;

    ProgressBar bar;
    RequestQueue requestQueue;

    // URL to free hosting site .. Could be updated to FIREBASE
    String url = "http://aditisharma.tk/application/cherry11.json";
    public static final String TAG = " ReadMode ";


    public ReadMode() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("Fragment","Oncreate");
        View view = inflater.inflate(R.layout.fragment_read_mode, container, false);

// List Initialization
        myList = new ArrayList<>();

        myView= (ListView) view.findViewById(R.id.list_view);
        listAdapter = new Adapter(myList);

        Log.d(TAG,"Setting Adapter");
        myView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();

        Log.d(TAG,"Request Queue Initialized");
        requestQueue = Volley.newRequestQueue(getContext());

// Downloading JSON
        checkConnection();



        return view;
    }
/*
    public void performSynopsis(View v){
        final int position =
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if((requestCode==1234)&&(resultCode==-1)){
            bar.setProgress(0);
        }
    }

    public void checkConnection() {
        // To check if active network connection exists
        ConnectivityManager connectivityManager = (ConnectivityManager)  getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            Log.d(TAG,"Network Present");
            // Fetching Data
            NetFetchTask();
        } else {
            Toast.makeText(getContext(), "Internet not Connected", Toast.LENGTH_SHORT).show();
        }

    }

    public void performTask(Info content){

        // Called up for updating the List everytime a change occurs

        Log.d(TAG,"PerformTask");
        myList.add(content);

        Log.d(TAG,"Values Added to List");

        Log.d("MYLIST",""+myList.size());

        listAdapter.notifyDataSetChanged();
    }


    public JsonArrayRequest JSONStringBuilder(){
        // Makes use of Volley
        Log.d(TAG,"Request Builder "+url);
        String s1 = url;
        final JsonArrayRequest request = new JsonArrayRequest(s1, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    try {
                        JSONArray array = response;
                        Log.d(TAG, "ArraySize " + array.length());

                        for (int i = 0; i < array.length(); i++) {

                            Log.d(TAG, "Response Size " + response.length());

                            // JSON Object Received from CheeryQuill Database
                            JSONObject object = response.getJSONObject(i);
                            Info info = new Info(object.getString("title"),
                                    object.getString("genre"),
                                    object.getString("txt"),
                                    object.getString("syn"),
                                    object.getString("sdate"),
                                    1);
                            performTask(info);
                        }
                    }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("STORY","wasn't fetched");
            }
        });
        return request;

    }

    public void NetFetchTask(){

        requestQueue.add(JSONStringBuilder());

    }




    public class Adapter extends BaseAdapter{

        private ArrayList<Info> mList;

        public Adapter(ArrayList<Info> mList) {
            this.mList = mList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv1, tv2, tv3, tv4,tv5;

            LayoutInflater inflater =getLayoutInflater(null);
            convertView = inflater.inflate(R.layout.items, null);

            final Info element = getItem(position);

            tv1 = (TextView) convertView.findViewById(R.id.tv1);
            tv2 = (TextView) convertView.findViewById(R.id.tv2);
            tv3 = (TextView) convertView.findViewById(R.id.tv3);
            tv4 = (TextView) convertView.findViewById(R.id.tv4);
            tv5 = (TextView) convertView.findViewById(R.id.tv5);

            bar = (ProgressBar) convertView.findViewById(R.id.bar1);

            tv1.setText(element.name);
            tv2.setText(element.genre);
            tv3.setText(element.date);
            tv4.setText(String.valueOf(element.time)+" read");

            tv5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setCancelable(true);
                    builder.setTitle(element.name);
                    builder.setMessage(element.synopsis);
                    builder.setPositiveButton("READ NOW", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            long startTime= SystemClock.uptimeMillis();

                            bar.setProgress(100);
                            while((SystemClock.uptimeMillis()-startTime)<1000){
                                if((SystemClock.uptimeMillis()-startTime)%500==0){
                                    Log.d("HELL","GATE");
                                }
                            }
                            Intent intent = new Intent(getContext(),ViewStory.class);
                            //    Info object = myList.get(position);
                            intent.putExtra("Name",element.name);
                            intent.putExtra("Genre",element.genre);
                            intent.putExtra("Story",element.story);
                            startActivityForResult(intent,1234);
                        }
                    }).setNegativeButton("READ LATER", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                              database = MyOfflineDatabase.openWritableDatabase(getContext());

                            ContentValues values = new ContentValues();
                            values.put(SQL_Commands.Columns.NAME,element.getName());
                            values.put(SQL_Commands.Columns.GENRE,element.getGenre());
                            values.put(SQL_Commands.Columns.STORY,element.getStory());
                            values.put(SQL_Commands.Columns.SYNOPSIS,element.getSynopsis());
                            values.put(SQL_Commands.Columns.DATE,element.getDate());
                            values.put(SQL_Commands.Columns.TIME,element.getTime());

                            database.insert(SQL_Commands.TABLE_NAME,null,values);
                        }
                    });
                    builder.show();

                }
            });

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Log.d(TAG,"XYZ Clicked");
                        long startTime= SystemClock.uptimeMillis();
                        bar = (ProgressBar) v.findViewById(R.id.bar1);
                        bar.setProgress(100);
                        while((SystemClock.uptimeMillis()-startTime)<1000){
                            if((SystemClock.uptimeMillis()-startTime)%500==0){
                                Log.d("HELL","GATE");
                            }
                        }
                        Intent intent = new Intent(getContext(),ViewStory.class);
                    //    Info object = myList.get(position);
                        intent.putExtra("Name",element.name);
                        intent.putExtra("Genre",element.genre);
                        intent.putExtra("Story",element.story);
                        startActivityForResult(intent,1234);
                    }
            });

            return convertView;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Info getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();

        }



    }
}
