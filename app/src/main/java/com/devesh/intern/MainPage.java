package com.devesh.intern;

import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ListView listView;
    DatabaseClass myDb;

    ProgressBar bar;
    ArrayList<String> selectList;
    TextView select;
    Button syn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//      Initializing Lists
        selectList = new ArrayList<>();
        selectList.add("READ");
        selectList.add("WRITE");
        selectList.add("ACCOUNT");
        selectList.add("SIGN OUT");


        syn=(Button)findViewById(R.id.tv5);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Inflating Fragment ReadMode
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        ReadMode fragRead = new ReadMode();
        transaction.add(R.id.frag_container,fragRead,null);
   //     transaction.addToBackStack(null);
        transaction.commit();


        // Creating List Views
        listView= (ListView) findViewById(R.id.selectionList);


        // Inflating select list
        layoutAdapter adapter2 = new layoutAdapter(selectList);
        listView.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView t1 = (TextView) view.findViewById(R.id.select);
                Log.d("ListView",""+id);
                switch(position){
                    case 0 :
                        Log.d("Select Option","Read Mode ");
                        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        ReadMode fragRead = new ReadMode();
                        transaction.replace(R.id.frag_container,fragRead,null);
     //                   transaction.addToBackStack(null);
                        transaction.commit();
                      //  t1.setTextColor(Color.RED);
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case 1 :
                        Log.d("Select Option","Write Mode ");
                        android.support.v4.app.FragmentManager manager1 = getSupportFragmentManager();
                        FragmentTransaction transaction1 = manager1.beginTransaction();
                      //  transaction1.remove(fragRead);
                        WriteMode fragWrite = new WriteMode();
                        transaction1.replace(R.id.frag_container,fragWrite,null);
       //                 transaction1.addToBackStack(null);
                        transaction1.commit();
                        drawer.closeDrawer(GravityCompat.START);
                      //  t1.setTextColor(Color.RED);

                        break;
                    case 2 :
                        android.support.v4.app.FragmentManager manager2 = getSupportFragmentManager();
                        FragmentTransaction transaction2 = manager2.beginTransaction();
                        AccountPage fragAcc = new AccountPage();
                        transaction2.replace(R.id.frag_container,fragAcc,null);
                        transaction2.commit();

                        drawer.closeDrawer(GravityCompat.START);
                       // t1.setTextColor(Color.RED);

                        break;
                    case 3 :

                        drawer.closeDrawer(GravityCompat.START);
                      //  t1.setTextColor(Color.RED);
                        Intent i = new Intent(MainPage.this,MainActivity.class);
                        startActivity(i);

                        break;
                }
            }
        });


    }

    public void performSynopsis(final String title, final String message)
    {
        syn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getApplicationContext());
                builder.setCancelable(true);
                builder.setTitle(title);
                builder.setMessage(message);
                builder.show();

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( (requestCode==1234) && (requestCode==RESULT_OK) ){
            bar.setProgress(0);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(MainPage.this,MainActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public class layoutAdapter extends BaseAdapter{
        private ArrayList<String> mString;

        public layoutAdapter(ArrayList<String> mString) {
            this.mString = mString;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public String getItem(int position) {
            return mString.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            convertView = inflater.inflate(R.layout.selectitem,null);
            select = (TextView) convertView.findViewById(R.id.select);
            select.setText(getItem(position));

            return convertView;
        }
    }


}
