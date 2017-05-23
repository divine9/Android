package com.fixpocket.Fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fixpocket.Adapter.TopCategories;
import com.fixpocket.Model.CategoryModel.Datum;
import com.fixpocket.R;

import java.util.List;

public class Categories extends Fragment {

    private ListView CategoryListView;
    private TextView toolBarTitle;
    private LinearLayout backButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.categories, container, false);

        toolBarTitle = (TextView) view.findViewById(R.id.toolBarTitle);
        toolBarTitle.setText("CATEGORIES");

        // loading Categories

        CategoryListView = (ListView) view.findViewById(R.id.categoryListView);
        CategoriesAdpter categoriesAdpter = new CategoriesAdpter(getContext(), TopCategories.CategoryList);
        CategoryListView.setAdapter(categoriesAdpter);
        backButton = (LinearLayout) view.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });


        return view;
    }

    public static Fragment newInstance() {
        Categories fragment = new Categories();
        return fragment;
    }

    public class CategoriesAdpter extends BaseAdapter {
        Fragment selectedFragment = null;
        private final String CATNAME = null;
        Context context;

        List<Datum> CategoryList;
        public int[] CategoryIcon = {R.drawable.ic_graphic_design, R.drawable.ic_writing, R.drawable.ic_digitalmarketing, R.drawable.ic_cat_video_icon,
                R.drawable.ic_category_music, R.drawable.ic_cat_programming, R.drawable.ic_cat_advertising, R.drawable.ic_cat_business, R.drawable.ic_cat_funlifestyle};

        public CategoriesAdpter(Context c, List<Datum> CategoryList) {
            // TODO Auto-generated constructor stub

            context = c;
            this.CategoryList = CategoryList;

        }

        public class Holder {
            TextView categoryName;
            ImageView iconCategories;

            Holder(View view) {
                categoryName = (TextView) view.findViewById(R.id.categoryName);
                iconCategories = (ImageView) view.findViewById(R.id.iconCategories);

            }
        }

        @Override
        public int getCount() {
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


            View rowView = convertView;
            Holder holder = null;
            if (rowView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowView = inflater.inflate(R.layout.top_categories_single_row, null);
                holder = new Holder(rowView);
                rowView.setTag(holder);
            } else {

                holder = (Holder) rowView.getTag();
            }


            holder.categoryName.setText(CategoryList.get(position).getTitle());

            if (position < 9)
                holder.iconCategories.setImageResource(CategoryIcon[position]);
            else
                holder.iconCategories.setImageResource(0);

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    selectedFragment = SubCategories.newInstance();
                    FragmentTransaction transactionBeatList = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                    transactionBeatList.replace(R.id.frame_layout, selectedFragment);
                    transactionBeatList.addToBackStack(null);
                    Bundle args = new Bundle();
                    args.putString("CATNAME", CategoryList.get(position).getTitle());
                    args.putString("CATID", CategoryList.get(position).getId());
                    selectedFragment.setArguments(args);
                    transactionBeatList.commit();
                }
            });

            return rowView;
        }
    }
}

