package com.fixpocket.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fixpocket.R;

/**
 * Created by divine on 24/4/17.
 */
public class OrderSummeryAddepter extends ArrayAdapter {
    Context context;
    String [] OrderDetail;
    String [] OrderValue;

    public OrderSummeryAddepter(Context c, String[] Name, String[] Price) {
        // TODO Auto-generated constructor stub
        super(c, R.layout.order_summery_single_row,Name);
        context=c;
        OrderDetail=Name;
        OrderValue=Price;

    }
    public class Holder
    {
        TextView OrderDetail;
        TextView OrderValueTextView;
        Holder(View view){
            OrderDetail = (TextView) view.findViewById(R.id.order_deatial);;
            OrderValueTextView = (TextView) view.findViewById(R.id.order_value);

        }
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        View rowView = convertView;
        OrderSummeryAddepter.Holder holder = null;
        if(rowView==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.order_summery_single_row, null);
            holder = new OrderSummeryAddepter.Holder(rowView);
            rowView.setTag(holder);
        }
        else {

            holder = (OrderSummeryAddepter.Holder) rowView.getTag();
        }


        holder.OrderDetail.setText(OrderDetail[position]);
        holder.OrderValueTextView.setText(OrderValue[position]);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

            }
        });

        return rowView;
    }
}
