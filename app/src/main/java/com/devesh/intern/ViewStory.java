package com.devesh.intern;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ViewStory extends AppCompatActivity {
TextView tv1,tv2,tv3;

//    public interface OnPostExecute{
//        public void PostExecute();
//    }
//    private OnPostExecute postExecListener;
//
//    public ViewStory(OnPostExecute postExecListener) {
//        this.postExecListener = postExecListener;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_view_story);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);

        Intent alpha = getIntent();
        tv2.setText(alpha.getStringExtra("Name"));
        tv2.setTextColor(Color.RED);
        tv1.setText(alpha.getStringExtra("Genre"));
        tv3.setText(alpha.getStringExtra("Story"));

    }

    @Override
    public void onBackPressed() {
        Log.d("Back","Press");
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        super.onBackPressed();

    }

    //    @Override
//    protected void onStop() {
//        super.onStop();
//        postExecListener.PostExecute();
//    }
}
