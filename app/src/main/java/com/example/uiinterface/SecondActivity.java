package com.example.uiinterface;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        TextView textView=(TextView) findViewById(R.id.txt_bundle);
        Bundle bundle=getIntent().getExtras();
        String data=bundle.get("data").toString();
        textView.setText(data);

        TextView textView2=(TextView) findViewById(R.id.txt_bundle);
        Bundle bundle2=getIntent().getExtras();
        String data2=bundle.get("data").toString();
        textView.setText(data2);


    }
}
