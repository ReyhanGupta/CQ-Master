package com.devesh.intern;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login , signup;
    EditText email,pass;
    TextView forgot,subscribe,faq,aboutus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);

        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);

        forgot = (TextView) findViewById(R.id.forgot);
        subscribe = (TextView) findViewById(R.id.subscribe);
        faq = (TextView) findViewById(R.id.faq);
        aboutus = (TextView) findViewById(R.id.about_us);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().length()==0||pass.getText().toString().length()==0){
                    perform1("","Incorrect E-mail or Password");
                }
                else{
                    String username= email.getText().toString();
                    String password= pass.getText().toString();
                    Intent intent = new Intent(MainActivity.this,Login.class);
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    startActivity(intent);
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    email.setHint("");
                else
                    email.setHint("E-mail");
            }
        });

        pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    pass.setHint("");
                else
                    pass.setHint("Password");
            }
        });



        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("About Us");
                builder.setMessage("At CherryQuill, we appreciate the value of everlasting literature and introduce a way for everyone to author short stories and spread them across the globe.\n" +
                        "\n" +
                        "You are invited to create and consume stories. We pledge that anyone who writes is presented with the best of opportunities. The readers for sure get unbiased and worthy content without any prejudice.");
                builder.show();
            }
        });

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("FAQ");
                builder.setMessage("\n" +
                        "Connect to us\n" +
                        "Careers\n" +
                        "Contribution\n" +
                        "Errata/Issue\n");
                builder.show();
            }
        });

        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Subscribe");
                builder.setMessage("You will receive Weekly Updates");
                builder.show();
            }
        });

    }

    public void facebook(View v)
    {
        Intent i= new Intent(this, Facebook.class);
        startActivity(i);
    }

    public void gmail(View v)
    {
        Intent i = new Intent(this, GmailMain.class);
        startActivity(i);
    }

    public void twitter(View v)
    {
        Intent i= new Intent(this, TwitterLogin.class);
        startActivity(i);
    }
    public void perform1(String a,String b){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(a);
        builder.setMessage(b);
        builder.show();

    }

    @Override
    protected void onPause() {
        Log.d("MainActivity","OnPause");
        SharedPreferences preferences =getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String id,pass;
        id = email.getText().toString();
        pass = this.pass.getText().toString();
        editor.putString("ID",id);
        editor.putString("Password",pass);
        editor.apply();
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d("MainActivity","OnResume");
        SharedPreferences preferences =getPreferences(MODE_PRIVATE);
        String id =preferences.getString("ID","E-mail"),pass = preferences.getString("Password","Password");
        email.setText(id);
        this.pass.setText(pass);
        super.onResume();
    }
}
