package com.fixpocket.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fixpocket.Adapter.SlidingImageAdapter;
import com.fixpocket.Model.LandPageModel;
import com.fixpocket.R;
import com.fixpocket.Utils.Util;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class LandingPages extends AppCompatActivity {
    private ImageView landingPageSignInImg, landingPageSignUpImg;
    private TextView landingPageTitle, landingPageSubTitle, landingPageSignIn, landingPageSignUp;
    private int ImgNo = 0;
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    ViewPager mPager_product;
    CirclePageIndicator indicator;
    RelativeLayout rlViewpager;

    private static int NUM_PAGES_product = 0;
    private static int currentPage_product = 0;

    List<LandPageModel> ImageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_pages);

        init();


        landingPageSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPages.this, Login.class);
                startActivity(intent);
            }
        });
        landingPageSignInImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPages.this, Login.class);
                startActivity(intent);
            }
        });
        landingPageSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPages.this, Signup.class);
                startActivity(intent);
            }
        });
        landingPageSignUpImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPages.this, Signup.class);
                startActivity(intent);
            }
        });


    }


    public void init() {

        landingPageSignInImg = (ImageView) findViewById(R.id.landingpageSigninImg);
        landingPageSignUpImg = (ImageView) findViewById(R.id.landingpageSignUpImg);
        landingPageTitle = (TextView) findViewById(R.id.landingPageTitle);
        landingPageSubTitle = (TextView) findViewById(R.id.landingPageSubTitle);
        landingPageSignIn = (TextView) findViewById(R.id.landingpageSignin);
        landingPageSignUp = (TextView) findViewById(R.id.landingpageSignUp);

        mPager_product = (ViewPager) findViewById(R.id.pager);
        rlViewpager = (RelativeLayout) findViewById(R.id.rlViewpager);
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);


        ImageList = new ArrayList<>();

        LandPageModel landPageModel = new LandPageModel("Get things done", "Get instant access to millions of creative and professional services.", R.drawable.screen2);
        ImageList.add(landPageModel);

        landPageModel = new LandPageModel("Real Time Messaging", "Our in-app Messenger allows buyer and sellers to connect 24*7.", R.drawable.screen3);
        ImageList.add(landPageModel);

        landPageModel = new LandPageModel("Endless Variety", "Find what you need with smart search or explore the 120+ Categories available.", R.drawable.screen1);
        ImageList.add(landPageModel);


        SlidingImageAdapter slidingImageAdapter = new SlidingImageAdapter(LandingPages.this, ImageList);
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
        }, 3000, 3000);

    }
}
