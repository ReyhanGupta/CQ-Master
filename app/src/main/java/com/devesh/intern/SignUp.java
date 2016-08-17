package com.devesh.intern;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    EditText tv1,tv2,tv3,tv4,tv5,tv6,tv7;
    Button submit;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        submit = (Button) findViewById(R.id.submit);
        tv1 = (EditText) findViewById(R.id.firstname);
        tv2 = (EditText) findViewById(R.id.lastname);
        tv3 = (EditText) findViewById(R.id.penname);
        tv4 = (EditText) findViewById(R.id.password1);
        tv5 = (EditText) findViewById(R.id.password2);
        tv6 = (EditText) findViewById(R.id.email123);
        tv7 = (EditText) findViewById(R.id.country);

        /*spinner = (Spinner) findViewById(R.id.spinner);

        String[] items = new String[]{"India","USA"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_items,items);
        adapter.setDropDownViewResource(R.layout.spinner_items);
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();
*/
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUp.this,"Registered",Toast.LENGTH_SHORT).show();
                tv1.setText("");
                tv2.setText("");
                tv3.setText("");
                tv4.setText("");
                tv5.setText("");
                tv6.setText("");
                tv7.setText("");
                Long startTime = SystemClock.uptimeMillis();
                while (SystemClock.uptimeMillis()-startTime<=2000){

                }
                Intent intent = new Intent(SignUp.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
