package com.fixpocket.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fixpocket.Fragment.BeatDetail;
import com.fixpocket.R;

/**
 * Created by divine on 24/4/17.
 */
public class MyBeatAdapter extends ArrayAdapter {
    Fragment selectedFragment = null;
    Context context;
    String [] beatTitle;
    int [] beatImg;


    public MyBeatAdapter(Context c, String[] Name, int[] Img) {
        // TODO Auto-generated constructor stub
        super(c, R.layout.my_beat_single_row,Name);
        context=c;
        beatTitle=Name;
        beatImg=Img;


    }
    public class Holder
    {
        TextView beatName;
        ImageView beatImg;
        Holder(View view){
            beatName = (TextView) view.findViewById(R.id.my_beat_title);;
            beatImg = (ImageView)view.findViewById(R.id.my_beat_pic);

        }
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        View rowView = convertView;
        MyBeatAdapter.Holder holder = null;
        if(rowView==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.my_beat_single_row, null);
            holder = new MyBeatAdapter.Holder(rowView);
            rowView.setTag(holder);
        }
        else {

            holder = (MyBeatAdapter.Holder) rowView.getTag();
        }


        holder.beatName.setText(beatTitle[position]);
        holder.beatImg.setImageResource(beatImg[position]);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                selectedFragment = BeatDetail.newInstance();
                FragmentTransaction transactionbeatDetail = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                transactionbeatDetail .replace(R.id.frame_layout, selectedFragment);
                transactionbeatDetail.addToBackStack(null);
                transactionbeatDetail .commit();



            }
        });

        return rowView;
    }
}
