package com.fixpocket.Fragment;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fixpocket.Adapter.SlidingImage_Home_Adapter;
import com.fixpocket.Adapter.TopBeats;
import com.fixpocket.Adapter.TopCategories;
import com.fixpocket.Model.CategoryModel.CategoryModel;
import com.fixpocket.Model.CategoryModel.Datum;
import com.fixpocket.Model.CollectionsModel.CollectionsModel;
import com.fixpocket.Model.CollectionsbeatsModel.CollectionsbeatsModel;
import com.fixpocket.Model.FeaturedModel.FeaturedModel;
import com.fixpocket.Model.LandPageModel;
import com.fixpocket.R;
import com.fixpocket.Utils.Constant;
import com.fixpocket.Utils.UtilInterface;
import com.fixpocket.Webservice.FixpocketAPI;
import com.fixpocket.Webservice.WebUtil.BaseURL;
import com.tuyenmonkey.mkloader.MKLoader;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends Fragment implements View.OnClickListener, UtilInterface {

    private ListView topCategoryListView, featuredBeatsListView;
    private TextView toolBarTitle, viewAllCategories, viewAllBeats;
    private ImageView iconViewAllCategories, iconViewAllBeats, notification;
    Context context;
    Fragment selectedFragment = null;
    public static int[] BeatImg = {R.drawable.te1, R.drawable.te1, R.drawable.te1};
    public static String[] beatName = {"Create Professional Infographics with your...", "Create Professional Infographics with your...", "Create Professional Infographics with your..."};


    ViewPager mPager_product;
    CirclePageIndicator indicator;
    RelativeLayout rlViewpager;

    private static int NUM_PAGES_product = 0;
    private static int currentPage_product = 0;

    List<com.fixpocket.Model.CollectionsModel.Datum> ImageList;

    List<Datum> CategoryList;
    List<com.fixpocket.Model.FeaturedModel.Datum> FeaturedList;

    MKLoader mkLoader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);
        init(view);
        toolBarTitle.setText("WELCOME");

        //onclick
        viewAllCategories.setOnClickListener(this);
        iconViewAllCategories.setOnClickListener(this);
        viewAllBeats.setOnClickListener(this);
        iconViewAllBeats.setOnClickListener(this);

        GetAllCategory();

        return view;
    }

    public void init(View view) {

        mkLoader = (MKLoader) view.findViewById(R.id.mlloader);

        topCategoryListView = (ListView) view.findViewById(R.id.topCategoryListView);
        featuredBeatsListView = (ListView) view.findViewById(R.id.featuredBeatsListView);
        toolBarTitle = (TextView) view.findViewById(R.id.toolBarTitle);
        viewAllCategories = (TextView) view.findViewById(R.id.viewAllCategories);
        viewAllBeats = (TextView) view.findViewById(R.id.viewAllBeats);
        iconViewAllCategories = (ImageView) view.findViewById(R.id.iconViewAllCategories);
        iconViewAllBeats = (ImageView) view.findViewById(R.id.iconViewAllBeats);

        mPager_product = (ViewPager) view.findViewById(R.id.pager);
        rlViewpager = (RelativeLayout) view.findViewById(R.id.rlViewpager);
        indicator = (CirclePageIndicator) view.findViewById(R.id.indicator);


    }

    //handling Click
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.viewAllCategories:
                selectedFragment = Categories.newInstance();
                FragmentTransaction transactionViewAllCategories = getActivity().getSupportFragmentManager().beginTransaction();
                transactionViewAllCategories.replace(R.id.frame_layout, selectedFragment);
                transactionViewAllCategories.addToBackStack(null);
                transactionViewAllCategories.commit();

                break;
            case R.id.iconViewAllCategories:
                selectedFragment = Categories.newInstance();
                FragmentTransaction transactionViewAllCategoriesIcon = getActivity().getSupportFragmentManager().beginTransaction();
                transactionViewAllCategoriesIcon.replace(R.id.frame_layout, selectedFragment);
                transactionViewAllCategoriesIcon.addToBackStack(null);
                transactionViewAllCategoriesIcon.commit();
                break;
            case R.id.viewAllBeats:
                selectedFragment = FeatureAllBeatsList.newInstance();
                FragmentTransaction transactionViewAllBeat = getActivity().getSupportFragmentManager().beginTransaction();
                transactionViewAllBeat.replace(R.id.frame_layout, selectedFragment);
                transactionViewAllBeat.addToBackStack(null);
                transactionViewAllBeat.commit();
                break;
            case R.id.iconViewAllBeats:
                selectedFragment = FeatureAllBeatsList.newInstance();
                FragmentTransaction transactionViewAllBeatIcon = getActivity().getSupportFragmentManager().beginTransaction();
                transactionViewAllBeatIcon.replace(R.id.frame_layout, selectedFragment);
                transactionViewAllBeatIcon.addToBackStack(null);
                transactionViewAllBeatIcon.commit();
                break;
            case R.id.featuredBeatsListView:

                break;
            case R.id.topCategoryListView:

                break;
        }
    }

    // setting up hight for list view

    private void GetAllCategory() {
        mkLoader.setVisibility(View.VISIBLE);
        FixpocketAPI bookNPlayAPI = BaseURL.getFixpocketAPI();
        Call<CategoryModel> call = bookNPlayAPI.categories(Constant.API_AUTH);
        call.enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                mkLoader.setVisibility(View.GONE);
                if (response.code() == 200) {
                    if (response.body().getStatus() == 1) {
                        Log.e("message", "" + response.body().getMessage());

                        GetAllFeatured();
                        GetAllcollections();

                        CategoryList = response.body().getData();
                        // loadTopCategory;

                        TopCategories topCategories = new TopCategories(getContext(), CategoryList);
                        topCategoryListView.setAdapter(topCategories);
                        setListViewHeightBasedOnChildren(topCategoryListView);

                    } else {
//                        Message("Login Failed.");
                    }
                }
            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {
                mkLoader.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Invalid Password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GetAllFeatured() {
        mkLoader.setVisibility(View.VISIBLE);
        FixpocketAPI bookNPlayAPI = BaseURL.getFixpocketAPI();
        Call<FeaturedModel> call = bookNPlayAPI.featured(Constant.API_AUTH, Constant.AUTH_TOKEN);
        call.enqueue(new Callback<FeaturedModel>() {
            @Override
            public void onResponse(Call<FeaturedModel> call, Response<FeaturedModel> response) {
                mkLoader.setVisibility(View.GONE);
                if (response.code() == 200) {
                    if (response.body().getStatus() == 1) {
                        Log.e("message", "" + response.body().getMessage());

                        FeaturedList = response.body().getData();
                        //loadin featured beats
                        TopBeats topBeats = new TopBeats(getContext(), FeaturedList, Home.this);
                        featuredBeatsListView.setAdapter(topBeats);
                        setListViewHeightBasedOnChildren(featuredBeatsListView);

                    } else {
//                        Message("Login Failed.");
                    }
                }
            }

            @Override
            public void onFailure(Call<FeaturedModel> call, Throwable t) {
                mkLoader.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Invalid Password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GetAllcollections() {
        mkLoader.setVisibility(View.VISIBLE);
        FixpocketAPI bookNPlayAPI = BaseURL.getFixpocketAPI();
        Call<CollectionsModel> call = bookNPlayAPI.collections(Constant.API_AUTH, Constant.AUTH_TOKEN);
        call.enqueue(new Callback<CollectionsModel>() {
            @Override
            public void onResponse(Call<CollectionsModel> call, Response<CollectionsModel> response) {
                mkLoader.setVisibility(View.GONE);
                if (response.code() == 200) {
                    if (response.body().getStatus() == 1) {
                        Log.e("message", "" + response.body().getMessage());

                        ImageList = response.body().getData();

                        SlidingImage_Home_Adapter slidingImageAdapter = new SlidingImage_Home_Adapter(getActivity(), ImageList);
                        mPager_product.setAdapter(slidingImageAdapter);
                        indicator.setViewPager(mPager_product);
                        slidingImageAdapter.notifyDataSetChanged();

                        final float density = getResources().getDisplayMetrics().density;
                        //Set circle indicator radius
                        indicator.setRadius(5 * density);
                        NUM_PAGES_product = ImageList.size();

                        // Auto start of viewpager
                        final Handler handler_product = new Handler();
                        final Runnable Update_product = new Runnable() {
                            public void run() {
                                if (currentPage_product == NUM_PAGES_product) {
                                    currentPage_product = 0;
                                }
                                mPager_product.setCurrentItem(currentPage_product++, true);
                            }
                        };
                        Timer swipeTimer = new Timer();
                        swipeTimer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                handler_product.post(Update_product);
                            }
                        }, 2000, 5000);

                    } else {
//                        Message("Login Failed.");
                    }
                }
            }

            @Override
            public void onFailure(Call<CollectionsModel> call, Throwable t) {
                mkLoader.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Invalid Password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public static Fragment newInstance() {
        Home fragment = new Home();
        return fragment;
    }

    @Override
    public void Refresh(String one) {
        Log.e("==>", "" + one);
        GetAllFeatured();
    }
}


