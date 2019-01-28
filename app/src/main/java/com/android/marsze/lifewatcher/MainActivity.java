package com.android.marsze.lifewatcher;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

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
    private EditText editText;
    private SeekBar seekBar;
    private Button buttonDetails;
    private EditText editText3;
    private Button buttonPlus;
    private Button buttonMinus;
    private Button buttonDB;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Time time = new Time(Time.getCurrentTimezone());
        time.setToNow();
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,time.year);
        calendar.set(Calendar.MONTH,time.month);
        calendar.set(Calendar.DAY_OF_MONTH,time.monthDay);
        button2 = findViewById(R.id.button2);
        button = findViewById(R.id.button);
        buttonDB = findViewById(R.id.buttonDB);
        spinner = findViewById(R.id.spinner);
        editText = findViewById(R.id.editText);
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
        buttonDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spinner.getVisibility()==View.INVISIBLE)
                    spinner.setVisibility(View.VISIBLE);
                else
                    spinner.setVisibility(View.INVISIBLE);
            }
        });
        /// TO BE DEPRECIATED cause of RecyclerView
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
}
