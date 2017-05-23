package com.fixpocket.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fixpocket.Model.SubCategoryModel.Datum;
import com.fixpocket.Model.SubCategoryModel.SubCategoryModel;
import com.fixpocket.R;
import com.fixpocket.Utils.Constant;
import com.fixpocket.Webservice.FixpocketAPI;
import com.fixpocket.Webservice.WebUtil.BaseURL;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategories extends Fragment {

    private ListView CategoryListView;
    private TextView toolBarTitle;
    private LinearLayout backButton;

    String CATNAME, CATID;
    MKLoader mkLoader;
    List<Datum> SubCategoryList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subcategories, container, false);

        toolBarTitle = (TextView) view.findViewById(R.id.toolBarTitle);
        mkLoader = (MKLoader) view.findViewById(R.id.mlloader);

        Bundle bundle = getArguments();
        if (bundle != null) {
            CATNAME = getArguments().getString("CATNAME");
            CATID = getArguments().getString("CATID");
            toolBarTitle.setText(CATNAME);
            GetAllSubCategory();
        }

        // loading Categories

        CategoryListView = (ListView) view.findViewById(R.id.categoryListView);

        backButton = (LinearLayout) view.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });


        return view;
    }

    private void GetAllSubCategory() {
        mkLoader.setVisibility(View.VISIBLE);
        FixpocketAPI bookNPlayAPI = BaseURL.getFixpocketAPI();
        Call<SubCategoryModel> call = bookNPlayAPI.subcategories(Constant.API_AUTH, CATID);
        call.enqueue(new Callback<SubCategoryModel>() {
            @Override
            public void onResponse(Call<SubCategoryModel> call, Response<SubCategoryModel> response) {
                mkLoader.setVisibility(View.GONE);
                if (response.code() == 200) {
                    if (response.body().getStatus() == 1) {
                        Log.e("message", "" + response.body().getMessage());

                        SubCategoryList = response.body().getData();

                        SubCategoriesAdpter categoriesAdpter = new SubCategoriesAdpter(getContext(), SubCategoryList);
                        CategoryListView.setAdapter(categoriesAdpter);

                    } else {
                        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                        alertDialog.setTitle(R.string.app_name);
                        alertDialog.setMessage(response.body().getMessage());
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        getFragmentManager().popBackStackImmediate();
                                    }
                                });
                        alertDialog.show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SubCategoryModel> call, Throwable t) {
                mkLoader.setVisibility(View.GONE);
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle(R.string.app_name);
                alertDialog.setMessage(t.getMessage());
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                getFragmentManager().popBackStackImmediate();
                            }
                        });
                alertDialog.show();
            }
        });
    }

    public static Fragment newInstance() {
        SubCategories fragment = new SubCategories();
        return fragment;
    }

    public class SubCategoriesAdpter extends BaseAdapter {
        Fragment selectedFragment = null;
        private final String CATNAME = null;
        Context context;

        List<Datum> CategoryList;
        public int[] CategoryIcon = {R.drawable.ic_graphic_design, R.drawable.ic_writing, R.drawable.ic_digitalmarketing, R.drawable.ic_cat_video_icon,
                R.drawable.ic_category_music, R.drawable.ic_cat_programming, R.drawable.ic_cat_advertising, R.drawable.ic_cat_business, R.drawable.ic_cat_funlifestyle};

        public SubCategoriesAdpter(Context c, List<Datum> CategoryList) {
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
                rowView = inflater.inflate(R.layout.top_categories_sub_item, null);
                holder = new Holder(rowView);
                rowView.setTag(holder);
            } else {
                holder = (Holder) rowView.getTag();
            }

            holder.categoryName.setText(CategoryList.get(position).getTitle());

            holder.iconCategories.setImageResource(0);

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    selectedFragment = BeatsList.newInstance();
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

