package com.example.akanksh.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ThreadCreate extends AppCompatActivity {

    private ImageView imageView;
    private TextView create1,create2;
    private EditText ed1,ed2;
    private Button button;

    private String name;
    private String relation;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference threadReference;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    /*private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText("You are a part of these threads");
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText("Create new threads here");
                    create1.setText("Thread name");
                    create2.setText("Related to");
                    return true;
            }
            return false;
        }

    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_thread);
        create1=(TextView)findViewById(R.id.textView2);
        create2=(TextView)findViewById(R.id.textView3);
        ed1=(EditText)findViewById(R.id.name_thread);
        ed2=(EditText)findViewById(R.id.to_relation);
        button=(Button)findViewById(R.id.button);

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mFirebaseAuth=FirebaseAuth.getInstance();

        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                name=ed1.getText().toString();
                relation=ed2.getText().toString();
                threadReference=FirebaseDatabase.getInstance().getReference().child("Thread").child(name);
                threadReference.setValue(new ThreadStruct(name,relation));
                Toast.makeText(getApplicationContext(),"Your Thread "+name+" has been created",Toast.LENGTH_SHORT).show();
            }

        });
    }

}
