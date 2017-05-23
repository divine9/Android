package com.fixpocket.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fixpocket.R;

/**
 * Created by divine on 24/4/17.
 */
public class NotificationAdepter extends ArrayAdapter {
    Fragment selectedFragment = null;
    Context context;
    String [] notificationText;

    int [] notificationImg;


    public NotificationAdepter(Context c, String[] text, int[] Img) {
        // TODO Auto-generated constructor stub
        super(c, R.layout.notification_single_row,text);
        context=c;
        notificationText=text;
        notificationImg=Img;
       // date=date;


    }
    public class Holder
    {
        TextView notificationText;
       // TextView Date;
        ImageView notificationImg;
        Holder(View view){
            notificationText = (TextView) view.findViewById(R.id.notification_text);
          //  Date = (TextView)view.findViewById(R.id.notification_date);
            notificationImg = (ImageView)view.findViewById(R.id.notificationIMG);

        }
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        View rowView = convertView;
        NotificationAdepter.Holder holder = null;
        if(rowView==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.notification_single_row, null);
            holder = new NotificationAdepter.Holder(rowView);
            rowView.setTag(holder);
        }
        else {

            holder = (NotificationAdepter.Holder) rowView.getTag();
        }


        holder.notificationText.setText(notificationText[position]);
//        holder.Date.setText(date[position]);
        holder.notificationImg.setImageResource(notificationImg[position]);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+notificationText[position], Toast.LENGTH_LONG).show();




            }
        });

        return rowView;
    }
}
