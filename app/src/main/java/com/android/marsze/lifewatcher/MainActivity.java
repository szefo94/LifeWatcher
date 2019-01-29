package com.android.marsze.lifewatcher;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Animatable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

//    LinerLayout item = (LinerLayout)findViewById(R.id.item);
//    View child = getLayoutInflater().inflate(R.layout.child, null);
//        item.addView(child);
//
//    Button details = child.findViewById(R.id.btn_details);
//    Button plus = child.findViewById(R.id.btn_plus);
//    Button minus = child.findViewById(R.id.btn_minus);
    private Button button;
    private Button button2;
    private Button buttonSave;
    private EditText editText;
    private SeekBar seekBar;
    private Button buttonDetails;
    private EditText editText3;
    private Button buttonPlus;
    private Button buttonMinus;
    private TextView textView;
    private Button buttonDB;
    private Button buttonAddAct;
    DatabaseHelper mDatabaseHelper;

    private ArrayList<String> mActNames = new ArrayList<>();
    private ArrayList<String> mProgresBars = new ArrayList<>();
    private ArrayList<String> mDetailsButtons = new ArrayList<>();
    private ArrayList<String> mPlusButtons = new ArrayList<>();
    private ArrayList<String> mMinusButtons = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabaseHelper = new DatabaseHelper(this);
        final Time time = new Time(Time.getCurrentTimezone());
        time.setToNow();
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,time.year);
        calendar.set(Calendar.MONTH,time.month);
        calendar.set(Calendar.DAY_OF_MONTH,time.monthDay);
        button2 = findViewById(R.id.button2);
        buttonSave = findViewById(R.id.buttonSave);
        button = findViewById(R.id.button);
        buttonAddAct = findViewById(R.id.buttonAddAct);
        buttonDB = findViewById(R.id.buttonDB);
        editText = findViewById(R.id.editText);

        textView = findViewById(R.id.textView);
        editText.setText(time.monthDay+"-"+(time.month+1)+"-"+time.year);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.DAY_OF_MONTH,1);
                editText.setText(calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR));
                //todo fill data from database
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.DAY_OF_MONTH,-1);
                editText.setText(calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR));


                //todo fill data from database
            }
        });
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String newEntry = editText.getText().toString();
                if (editText.length() != 0) {
                    AddData(calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.YEAR),textView.getText().toString(),"details",seekBar.getProgress());
                    editText.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }
                Cursor data = mDatabaseHelper.getData();
                ArrayList<String> listData = new ArrayList<>();
                while(data.moveToNext()){
                    //get the value from the database in column 1
                    //then add it to the ArrayList
                    listData.add(data.getString(1));
                    listData.add(data.getString(2));
                    listData.add(data.getString(3));
                    listData.add(data.getString(4));
                    listData.add(data.getString(5));
                    listData.add(data.getString(6));
                    //ListAdapter adapter = new ArrayAdapter<String>();
                    //ListAdapter adapter = new ArrayAdapter<>(this,R.layout.)
                    //listView.setAdapter(adapter);
                    editText3.setText("");
                    for(int i=0;i<listData.size();i++)
                    editText3.setText(editText3.getText()+"\n"+i+" "+listData.get(i));
                }
            }
        });
        buttonAddAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mDatabaseHelper.deleteTab();
            }
        });
        buttonDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor data = mDatabaseHelper.wipeData();
            }
        });


        // TODO DEPRECIATED cause of RecyclerView
        seekBar = findViewById(R.id.seekBar2);
        buttonDetails = findViewById(R.id.buttonDetails);
        editText3 = findViewById(R.id.editText3);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonDetails.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(editText3.getVisibility()==View.INVISIBLE)
                editText3.setVisibility(View.VISIBLE);
            else
                editText3.setVisibility(View.INVISIBLE);
        }
        });
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(seekBar.getProgress()!=seekBar.getMax())
                seekBar.setProgress(seekBar.getProgress()+1);
            }
        });
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seekBar.getProgress()!=0)
                seekBar.setProgress(seekBar.getProgress()-1);

            }
        });
    }

    public void AddData(int dayOfMonth, int month, int year, String text, String details, int progress){
        boolean insertData = mDatabaseHelper.addData(dayOfMonth,month,year,text,details,progress);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }


}
