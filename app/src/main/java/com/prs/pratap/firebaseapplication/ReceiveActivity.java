package com.prs.pratap.firebaseapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class ReceiveActivity extends AppCompatActivity {

    //private TextView value;
    private Firebase reference;

    private ArrayList<String> username;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        //value = (TextView)findViewById(R.id.value);
        reference = new Firebase("https://fir-application-2f3f0.firebaseio.com/FirebaseTest/Users");

        /*reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map<String, String> data = dataSnapshot.getValue(Map.class);
                String name = data.get("name");
                String age = data.get("age");
                String college = data.get("college");

                String val = name + "\n" + age + "\n" + college;
                value.setText(val);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        */

        listView = (ListView)findViewById(R.id.listView);
        username = new ArrayList<>();

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, username);
        listView.setAdapter(arrayAdapter);

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String users = dataSnapshot.getValue(String.class);
                username.add(users);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }
}
