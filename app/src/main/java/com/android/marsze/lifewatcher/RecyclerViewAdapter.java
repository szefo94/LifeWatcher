package com.android.marsze.lifewatcher;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> mActNames = new ArrayList<>();
    private ArrayList<String> mProgresBars = new ArrayList<>();
    private ArrayList<String> mDetailsButtons = new ArrayList<>();
    private ArrayList<String> mPlusButtons = new ArrayList<>();
    private ArrayList<String> mMinusButtons = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, ArrayList<String> mActNames, ArrayList<String> mProgresBars, ArrayList<String> mDetailsButtons, ArrayList<String> mPlusButtons, ArrayList<String> mMinusButtons) {
        this.mActNames = mActNames;
        this.mProgresBars = mProgresBars;
        this.mDetailsButtons = mDetailsButtons;
        this.mPlusButtons = mPlusButtons;
        this.mMinusButtons = mMinusButtons;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.actName.setText(mActNames.get(i));
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mActNames.get(i));

                Toast.makeText(mContext, mActNames.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mActNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView actName;
        SeekBar progressBar;
        Button detailsButton;
        Button plusButton;
        Button minusButton;
        RelativeLayout parentLayout;
        //verti layout? hori layout?
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            actName= itemView.findViewById(R.id.textView);
            progressBar= itemView.findViewById(R.id.seekBar2);
            detailsButton = itemView.findViewById(R.id.buttonDetails);
            plusButton = itemView.findViewById(R.id.buttonPlus);
            minusButton = itemView.findViewById(R.id.buttonMinus);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
