package com.android.okhttpsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(this);
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);

        textView = (TextView)findViewById(R.id.textView);

    }

    // Implement the OnClickListener callback
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                System.out.println("Test:  click button1");
                SynchronousGet.executeSynchronousGet();
                break;

            case R.id.button2:
                System.out.println("Test:  click button2");
                AsynchronousGet.executeAsynchronousGet();
                break;

            case R.id.button3:
                System.out.println("Test:  click button3");
                PostRequest.executePostRequest();
                break;

            default:
                break;
        }
    }
}
