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
public class UpgradeOrderAddepter extends ArrayAdapter {
    Context context;
    String[] upgradeOrderName;
    String[] upgradeOrderPrice;

    public UpgradeOrderAddepter(Context c, String[] Name, String[] Price) {
        // TODO Auto-generated constructor stub
        super(c, R.layout.upgrade_order_single_row, Name);
        context = c;
        upgradeOrderName = Name;
        upgradeOrderPrice = Price;

    }

    public class Holder {
        TextView upgradeOrderDetail;
        TextView upgradeOrderPriceTextView;

        Holder(View view) {
            upgradeOrderDetail = (TextView) view.findViewById(R.id.upgarde_order_detail);
            ;
            upgradeOrderPriceTextView = (TextView) view.findViewById(R.id.upgarde_order_price);

        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        View rowView = convertView;
        UpgradeOrderAddepter.Holder holder = null;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.upgrade_order_single_row, null);
            holder = new UpgradeOrderAddepter.Holder(rowView);
            rowView.setTag(holder);
        } else {

            holder = (UpgradeOrderAddepter.Holder) rowView.getTag();
        }


        holder.upgradeOrderDetail.setText(upgradeOrderName[position]);
        holder.upgradeOrderPriceTextView.setText(upgradeOrderPrice[position]);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

            }
        });

        return rowView;
    }
}
