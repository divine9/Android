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
import android.widget.Toast;

import com.fixpocket.R;
import com.fixpocket.Fragment.OrderDetails;

/**
 * Created by divine on 24/4/17.
 */
public class ManageOrderAdapter extends ArrayAdapter {
    Fragment selectedFragment = null;
    Context context;
    String [] beatTitle;
    int [] beatImg;


    public ManageOrderAdapter(Context c, String[] Name, int[] Img) {
        // TODO Auto-generated constructor stub
        super(c, R.layout.manage_sales_single_row,Name);
        context=c;
        beatTitle=Name;
        beatImg=Img;


    }
    public class Holder
    {
        TextView beatName;
        ImageView beatImg;
        Holder(View view){
            beatName = (TextView) view.findViewById(R.id.beat_title_manage_sales);;
            beatImg = (ImageView)view.findViewById(R.id.manage_sales_beat_pic);

        }
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        View rowView = convertView;
        ManageOrderAdapter.Holder holder = null;
        if(rowView==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.manage_sales_single_row, null);
            holder = new ManageOrderAdapter.Holder(rowView);
            rowView.setTag(holder);
        }
        else {

            holder = (ManageOrderAdapter.Holder) rowView.getTag();
        }


        holder.beatName.setText(beatTitle[position]);
        holder.beatImg.setImageResource(beatImg[position]);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+beatTitle[position], Toast.LENGTH_LONG).show();

                selectedFragment = OrderDetails.newInstance();
                FragmentTransaction transactionbeatDetail = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                transactionbeatDetail .replace(R.id.frame_layout, selectedFragment);
                transactionbeatDetail.addToBackStack(null);
                transactionbeatDetail .commit();



            }
        });

        return rowView;
    }
}
