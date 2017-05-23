package com.fixpocket.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fixpocket.Fragment.SubCategories;
import com.fixpocket.Model.CategoryModel.Datum;
import com.fixpocket.R;

import java.util.List;

/**
 * Created by divine on 24/4/17.
 */
public class TopCategories extends BaseAdapter {
    Fragment selectedFragment = null;
    Context context;
    public static List<Datum> CategoryList;
    public static int[] topCategoryIcon = {R.drawable.ic_graphic_design, R.drawable.ic_digitalmarketing, R.drawable.ic_writing};

    public TopCategories(Context c, List<Datum> CategoryList) {
        // TODO Auto-generated constructor stub
        this.CategoryList = CategoryList;
        context = c;

    }


    @Override
    public int getCount() {
        if (CategoryList.size() > 3)
            return 3;
        else
            return CategoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return CategoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.top_categories_single_row, null);
            holder = new ViewHolder();

            holder.categoryName = (TextView) convertView.findViewById(R.id.categoryName);
            holder.iconCategories = (ImageView) convertView.findViewById(R.id.iconCategories);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.categoryName.setText(CategoryList.get(position).getTitle());
//        Picasso.with(context).load(CategoryList.get(position).getImg()).into(holder.imgDashBoard);
        holder.iconCategories.setImageResource(topCategoryIcon[position]);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                selectedFragment = SubCategories.newInstance();
                FragmentTransaction transactionTopCategory = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                transactionTopCategory.replace(R.id.frame_layout, selectedFragment);
                transactionTopCategory.addToBackStack(null);
                Bundle args = new Bundle();
                args.putString("CATNAME", CategoryList.get(position).getTitle());
                args.putString("CATID", CategoryList.get(position).getId());
                selectedFragment.setArguments(args);
                transactionTopCategory.commit();
            }
        });

        return convertView;
    }

    public static class ViewHolder {
        TextView categoryName;
        ImageView iconCategories;

    }
}
