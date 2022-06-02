package it.mirea.aviafast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class flights_listActivity extends AppCompatActivity {


    ListView flights_listview;

    ArrayList<String> flights_arraylist = new ArrayList<>();
    DatabaseReference DBreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights_list);


        //getting data of airport chosen
        Intent intent = getIntent();
        String airport_name_to_pass = intent.getStringExtra("message key");
        //name of airport in action bar
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle(airport_name_to_pass);
        }

        ArrayAdapter<String> flightArrayAdapter = new ArrayAdapter<>(flights_listActivity.this, android.R.layout.simple_list_item_1,flights_arraylist);

        flights_listview = (ListView) findViewById(R.id.listview_flights1);


        DBreference = FirebaseDatabase.getInstance().getReference();//сюда в референс писать аэропорт который выбрал пользователь

        DBreference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                flights_arraylist.add(snapshot.getValue(String.class));
                flightArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                flightArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                flights_arraylist.remove(snapshot.getValue(String.class));
                flightArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        flights_listview.setAdapter(flightArrayAdapter);
    }
}