package com.devesh.intern;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.devesh.intern.Tables.SQL_Commands;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class WriteMode extends Fragment {

    ListView listView;
    ListAdapter adapter;
    ArrayList<Info> databaseList;

    SQLiteDatabase database;

    public WriteMode() {
        // Required empty public constructor

        databaseList = new ArrayList<>();
        database = MyOfflineDatabase.openReadableDatabase(getContext());
        listView = (ListView) getActivity().findViewById(R.id.listview_writemode);

        adapter = new ListAdapter(databaseList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        performTask();

    }

    public void performTask(){

        String[] projection = {

                SQL_Commands.Columns.NAME,
                SQL_Commands.Columns.STORY,
                SQL_Commands.Columns.GENRE,
                SQL_Commands.Columns.SYNOPSIS,
                SQL_Commands.Columns.DATE,
                SQL_Commands.Columns.TIME

        };

        Cursor c = database.query(SQL_Commands.TABLE_NAME,projection,null,null,null,null,null);
        // c.moveToFirst();
        while (c.moveToNext()){

            String name = c.getString(c.getColumnIndexOrThrow(SQL_Commands.Columns.NAME));
            String genre = c.getString(c.getColumnIndexOrThrow(SQL_Commands.Columns.GENRE));
            String synop = c.getString(c.getColumnIndexOrThrow(SQL_Commands.Columns.SYNOPSIS));
            String story = c.getString(c.getColumnIndexOrThrow(SQL_Commands.Columns.STORY));
            String date = c.getString(c.getColumnIndexOrThrow(SQL_Commands.Columns.DATE));
            String time = c.getString(c.getColumnIndexOrThrow(SQL_Commands.Columns.TIME));

            databaseList.add(new Info(name,genre,story,synop,date,5));
        }

        adapter.notifyDataSetChanged();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_write_mode, container, false);

        databaseList = new ArrayList<>();

        listView = (ListView) view.findViewById(R.id.listview_writemode);
        adapter = new ListAdapter(databaseList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }

    public class ListAdapter extends BaseAdapter{

        private ArrayList<Info> mList;

        public ListAdapter(ArrayList<Info> mList) {
            this.mList = mList;
        }

        class Holder{

            // Holder being used to decrease the time taken
            // Can be upgraded to Recycler View

            TextView title,genre,synopsis,date,time;
            ProgressBar progressBar;
            ImageView imageView;



        }



        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Info getItem(int i) {
            return mList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            return position==0?0:1;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Holder holder=new Holder();
            LayoutInflater inflater = getActivity().getLayoutInflater();
            if(view==null) {
                if (i == 0)
                    view = inflater.inflate(R.layout.create_new, null);
                else {
                    view = inflater.inflate(R.layout.writemode_items, null);
                    


                    holder.title = (TextView) view.findViewById(R.id.tv1);
                    holder.genre = (TextView) view.findViewById(R.id.tv2);
                    holder.synopsis = (TextView) view.findViewById(R.id.tv5);
                    holder.date = (TextView) view.findViewById(R.id.tv3);
                    holder.time = (TextView) view.findViewById(R.id.tv4);

                    holder.progressBar = (ProgressBar) view.findViewById(R.id.bar1);

                    holder.imageView = (ImageView) view.findViewById(R.id.profile_image);

                    view.setTag(holder);

                }
            }else{

                // For new story upload
                // Aditi will attach Text Editor

                if(i==0)
                    view = inflater.inflate(R.layout.create_new,null);
                else{
                    holder = (Holder) view.getTag();
                }
            }


            if(i!=0){

                final Info item =  getItem(i-1);

                holder.title.setText(item.name);
                holder.genre.setText(item.genre);
                
                holder.date.setText(item.date);
                holder.time.setText(String.valueOf(item.time));

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // Moves to the story
                        Intent intent = new Intent(getContext(),ViewStory.class);
                        intent.putExtra("Name",item.name);
                        intent.putExtra("Genre",item.genre);
                        intent.putExtra("Story",item.story);
                        startActivityForResult(intent,1234);
                    }
                });



            }

            return view;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if((requestCode==1234)&&(resultCode==-1)){
           // bar.setProgress(0);
            // UI changes if required
        }
    }
}
