package com.example.akanksh.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.content.Context;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ImageView imageView;
    private TextView create1,create2;
    private ListView mMessageListView;
    private MessageAdapter mMessageAdapter;
    private ChildEventListener mChildEventListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private ArrayList<ThreadStruct> tlist = new ArrayList<>();
    ArrayAdapter<ThreadStruct> adapter;
    final Context context = this;
    ArrayList arrayList = new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText("You are a part of these threads");
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText("Create new threads here");
                    Intent intent = new Intent(context, ThreadCreate.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_people:
                    //mTextMessage.setText("Participating people");
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mMessagesDatabaseReference=mFirebaseDatabase.getReference().child("Thread");
        final List<ThreadStruct> threads = new ArrayList<>();
        //mTextMessage = (TextView) findViewById(R.id.textView);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mMessageListView = (ListView) findViewById(R.id.messageListView);
        mMessageAdapter = new MessageAdapter(this, R.layout.item_message,threads );
        mMessageListView.setAdapter(mMessageAdapter);
        mMessagesDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                tlist.add(dataSnapshot.getValue(ThreadStruct.class)); // it was tlist here
                //Log.d("id", dataSnapshot.getValue(String.class));
                mMessageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                mMessageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }


}
