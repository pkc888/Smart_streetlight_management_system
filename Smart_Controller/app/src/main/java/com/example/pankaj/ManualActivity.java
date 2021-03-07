package com.example.pankaj;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManualActivity extends AppCompatActivity {
    TextView textView1, textView2;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Manual Mode");
        setContentView(R.layout.activity_manual);
        textView1 = (TextView) findViewById(R.id.text0);
        textView2 = (TextView) findViewById(R.id.text1);

        final Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.purple_700));

        textView1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                textView1.setBackgroundColor(Color.parseColor("#00FF0A"));
                textView1.setTextColor(Color.parseColor("#FFFFFF"));
                textView2.setBackgroundResource(R.drawable.button_selected);
                textView2.setTextColor(Color.parseColor("#000000"));

                Toast.makeText(ManualActivity.this, "LED is On ", Toast.LENGTH_SHORT).show();
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("valuee");

                myRef.setValue(1);
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        int value = dataSnapshot.getValue(Integer.class);
                        Log.d("TAG", "Value is: " + value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("TAG", "Failed to read value.", error.toException());
                    }
                });
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                textView2.setBackgroundColor(Color.parseColor("#FF0000"));
                textView2.setTextColor(Color.parseColor("#FFFFFF"));
                textView1.setBackgroundResource(R.drawable.button_selected);
                textView1.setTextColor(Color.parseColor("#000000"));
                Toast.makeText(ManualActivity.this, "LED is OFF ", Toast.LENGTH_SHORT).show();
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("valuee");

                myRef.setValue(0);
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        int value = dataSnapshot.getValue(Integer.class);
                        Log.d("TAG", "Value is: " + value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("TAG", "Failed to read value.", error.toException());
                    }
                });
            }

        });
    }
}