package com.fixpocket.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fixpocket.Model.SubCategoryModel.Datum;
import com.fixpocket.R;
import com.fixpocket.Fragment.BeatDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by divine on 24/4/17.
 */
public class BeatsAdapter extends BaseAdapter {
    Fragment selectedFragment = null;
    Context context;
    List<Datum> SuCategoryList;

    public BeatsAdapter(Context c, List<Datum> SuCategoryList) {
        // TODO Auto-generated constructor stub
        context = c;
        this.SuCategoryList = SuCategoryList;
    }

    public class Holder {
        TextView beatName;
        ImageView beatImg;
        RelativeLayout ry_top;

        Holder(View view) {

            ry_top = (RelativeLayout) view.findViewById(R.id.ry_top);
            beatName = (TextView) view.findViewById(R.id.beat_title);
            beatImg = (ImageView) view.findViewById(R.id.beatImg);

        }

    }


    @Override
    public int getCount() {
        return SuCategoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return SuCategoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        Holder holder = null;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.featured_beats_single_row, null);
            holder = new Holder(rowView);
            rowView.setTag(holder);
        } else {

            holder = (Holder) rowView.getTag();
        }

        if (position == 0)
            holder.ry_top.setVisibility(View.VISIBLE);
        else
            holder.ry_top.setVisibility(View.GONE);

        holder.beatName.setText(SuCategoryList.get(position).getTitle());

        Picasso.with(context).load(SuCategoryList.get(position).getImg()).into(holder.beatImg);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                selectedFragment = BeatDetail.newInstance();
                FragmentTransaction transactionbeatDetail = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                transactionbeatDetail.replace(R.id.frame_layout, selectedFragment);
                transactionbeatDetail.addToBackStack(null);
                transactionbeatDetail.commit();


            }
        });

        return rowView;
    }
}
