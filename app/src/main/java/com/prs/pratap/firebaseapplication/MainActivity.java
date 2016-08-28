package com.prs.pratap.firebaseapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {

    private final String url = "https://fir-application-2f3f0.firebaseio.com/FirebaseTest/Users";
    private Firebase reference;
    private EditText table, unkey, key,value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        table = (EditText)findViewById(R.id.table);
        unkey = (EditText)findViewById(R.id.uniqueid);
        key = (EditText)findViewById(R.id.key);
        value = (EditText)findViewById(R.id.value);
        value.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if (i == EditorInfo.IME_ACTION_SEND) {
                    sendData();
                    handled = true;
                }
                return handled;
            }
        });

        //for one activity
        //Firebase.setAndroidContext(this);

        reference = new Firebase(url);
    }

    public void onClickSendData(View view){
        sendData();
    }

    public void sendData(){
        String tablename = key.getText().toString();
        String uniqueid = value.getText().toString();
        String k = key.getText().toString();
        String val = value.getText().toString();

        //Firebase refc = new Firebase(url+"/"+tablename+"/"+uniqueid);

        Firebase referenceChild = reference.child(k);
        referenceChild.setValue(val);

        key.setText("");
        value.setText("");

        //random key
        //reference.push().setValue(val);
    }

    public void onClickNext(View v){
        Intent intent = new Intent(MainActivity.this, ReceiveActivity.class);
        startActivity(intent);
    }
}
