package com.example.pankaj;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AutomaticActivity extends AppCompatActivity {
    FirebaseDatabase mDatabase;
    DatabaseReference mGetReference;
    TextView btn1, btn2, btn3;
    ImageView img1, img2, img3;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automatic);
        setTitle("Automatic Mode");
        final Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.purple_700));
        builder = new AlertDialog.Builder(this);
        btn1 = (TextView) findViewById(R.id.btn1);
        btn2 = (TextView) findViewById(R.id.btn2);
        btn3 = (TextView) findViewById(R.id.btn3);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Setting message manually and performing action on button click
                builder.setMessage("If streetlight are installed in residential area than it must be switch ON with maximum intensity till midnight after that its intensity will decrease gradually with time until its lowest intensity. At that time of decreasing intensity if IR sensor detect any object/vehicle it will trigger the led to maximum brightness ")
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Residential Area");
                alert.show();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setMessage("As their is less movement of vehicles in rural areas, Street lights must be ON only when IR detect any object other wise it must be of very low intensity")
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.setTitle("Rural Area");
                alert.show();
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setMessage("Due to continues movement of vehicles in industrial area only LDR sensors will work here to detect natural lights.IR sensors are of no use in these areas.This system also work fine on highway.")
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });


                AlertDialog alert = builder.create();

                alert.setTitle("Industrial Area");
                alert.show();
            }
        });


        mDatabase = FirebaseDatabase.getInstance();
        mGetReference = mDatabase.getReference();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn2.setBackgroundColor(Color.parseColor("#FFBB86FC"));
                btn3.setBackgroundColor(Color.parseColor("#FFBB86FC"));
                btn1.setBackgroundColor(Color.parseColor("#FF3700B3"));
                mGetReference = mDatabase.getReference("valuee");
                mGetReference.setValue(2);
                mGetReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int value = dataSnapshot.getValue(Integer.class);
                        Log.d("TAG", "Value is: " + value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Log.w("TAG", "Failed to read value.", error.toException());
                    }
                });

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setBackgroundColor(Color.parseColor("#FFBB86FC"));
                btn3.setBackgroundColor(Color.parseColor("#FFBB86FC"));
                btn2.setBackgroundColor(Color.parseColor("#FF3700B3"));
                mGetReference = mDatabase.getReference("valuee");
                mGetReference.setValue(3);
                mGetReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int value = dataSnapshot.getValue(Integer.class);
                        Log.d("TAG", "Value is: " + value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Log.w("TAG", "Failed to read value.", error.toException());
                    }
                });

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setBackgroundColor(Color.parseColor("#FFBB86FC"));
                btn2.setBackgroundColor(Color.parseColor("#FFBB86FC"));
                btn3.setBackgroundColor(Color.parseColor("#FF3700B3"));
                mGetReference = mDatabase.getReference("valuee");
                mGetReference.setValue(4);
                mGetReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int value = dataSnapshot.getValue(Integer.class);
                        Log.d("TAG", "Value is: " + value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                        Log.w("TAG", "Failed to read value.", error.toException());
                    }
                });

            }
        });


    }
}