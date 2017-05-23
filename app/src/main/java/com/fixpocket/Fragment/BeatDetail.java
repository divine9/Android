package com.fixpocket.Fragment;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;


import com.fixpocket.Adapter.CollectionBeatsListAdapter;
import com.fixpocket.Adapter.ViewPaggerAdapter;
import com.fixpocket.Model.BeatDetailsModel.BeatDetailsModel;
import com.fixpocket.Model.CollectionsbeatsModel.CollectionsbeatsModel;
import com.fixpocket.R;
import com.fixpocket.Custome.SlidingTabLayout;
import com.fixpocket.Utils.Constant;
import com.fixpocket.Webservice.FixpocketAPI;
import com.fixpocket.Webservice.WebUtil.BaseURL;
import com.squareup.picasso.Picasso;
import com.tuyenmonkey.mkloader.MKLoader;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeatDetail extends Fragment {
    private TextView toolBarTitle;
    private Button placeOrderBeatDetail;
    private LinearLayout backButton;
    private SlidingTabLayout tabLayout;
    private ViewPager viewPager;
    ViewPaggerAdapter viewPagerAdapter;
    Fragment selectedFragment = null;
    String titles[] = {"Description", "Extras", "seller"};
    int numOfTabs = 3;

    String CATNAME, CATID;
    MKLoader mkLoader;

    public static BeatDetailsModel beatDetailsModel;

    ImageView iv_beat;
    CircleImageView iv_cir_icon, toolBarImg;

    TextView name_of_designer, txt_title_count, txt_desc, txt_by, txt_contact_Seller;
    RatingBar ratingBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.beat_detail, container, false);

        init(view);
        toolBarTitle.setText("BEAT");


        Bundle bundle = getArguments();
        if (bundle != null) {
            CATNAME = getArguments().getString("CATNAME");
            CATID = getArguments().getString("CATID");
            toolBarTitle.setText(CATNAME);

            GetBeatDetails();
        }

        placeOrderBeatDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = BeatRequirements.newInstance();
                FragmentTransaction transactionBeatRequirments = getActivity().getSupportFragmentManager().beginTransaction();
                transactionBeatRequirments.replace(R.id.frame_layout, selectedFragment);
                transactionBeatRequirments.addToBackStack(null);
                transactionBeatRequirments.commit();
            }
        });

        // tool bar and Menu bar clicks

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });


        // setCustomColorizer(), sets the color of the tab's indicator
        tabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {

            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorPrimary);
            }

        });

        // initialize the tablayout's viewPager

        return view;
    }

    public static Fragment newInstance() {
        BeatDetail fragment = new BeatDetail();
        return fragment;
    }

    public void init(View v) {

        mkLoader = (MKLoader) v.findViewById(R.id.mlloader);
        iv_beat = (ImageView) v.findViewById(R.id.iv_beat);
        iv_cir_icon = (CircleImageView) v.findViewById(R.id.iv_cir_icon);
        toolBarImg = (CircleImageView) v.findViewById(R.id.toolBarImg);

        name_of_designer = (TextView) v.findViewById(R.id.name_of_designer);
        txt_title_count = (TextView) v.findViewById(R.id.txt_title_count);
        txt_desc = (TextView) v.findViewById(R.id.txt_desc);
        txt_by = (TextView) v.findViewById(R.id.txt_by);
        txt_contact_Seller = (TextView) v.findViewById(R.id.txt_contact_Seller);
        ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);

        toolBarTitle = (TextView) v.findViewById(R.id.toolBarTitle);
        backButton = (LinearLayout) v.findViewById(R.id.back_button);
        placeOrderBeatDetail = (Button) v.findViewById(R.id.place_order_beat_detail_button);

        tabLayout = (SlidingTabLayout) v.findViewById(R.id.tabs1);
        viewPager = (ViewPager) v.findViewById(R.id.pager);
    }


    private void GetBeatDetails() {
        mkLoader.setVisibility(View.VISIBLE);
        FixpocketAPI bookNPlayAPI = BaseURL.getFixpocketAPI();
        Call<BeatDetailsModel> call = bookNPlayAPI.beat_info(Constant.API_AUTH, Constant.AUTH_TOKEN, CATID);
        call.enqueue(new Callback<BeatDetailsModel>() {
            @Override
            public void onResponse(Call<BeatDetailsModel> call, Response<BeatDetailsModel> response) {
                mkLoader.setVisibility(View.GONE);
                if (response.code() == 200) {
                    if (response.body().getStatus() == 1) {
                        Log.e("message", "" + response.body().getMessage());

                        beatDetailsModel = response.body();

                        name_of_designer.setText("" + beatDetailsModel.getData().getSellerInfo().getFirstName() + " " + beatDetailsModel.getData().getSellerInfo().getLastName());
                        txt_title_count.setText("(" + beatDetailsModel.getData().getBeatInfo().getTotFeed() + ")");
                        txt_desc.setText("" + beatDetailsModel.getData().getBeatInfo().getBeatTitle());
                        txt_by.setText("" + beatDetailsModel.getData().getBeatInfo().getShortTitle());

                        Picasso.with(getActivity()).load(beatDetailsModel.getData().getBeatDocs().get(0).getDocTitle()).into(iv_beat);

                        Picasso.with(getActivity()).load(beatDetailsModel.getData().getSellerInfo().getPhoto()).
                                transform(new RoundedCornersTransformation(10, 10)).into(iv_cir_icon);

                        Picasso.with(getActivity()).load(beatDetailsModel.getData().getSellerInfo().getPhoto()).
                                transform(new RoundedCornersTransformation(10, 10)).into(toolBarImg);

                        viewPagerAdapter = new ViewPaggerAdapter(getChildFragmentManager(), titles, numOfTabs);

                        viewPager.setAdapter(viewPagerAdapter);
                        tabLayout.setViewPager(viewPager);

                    } else {
//                        Message("Login Failed.");
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
                } else if (response.code() == 404) {
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setTitle(R.string.app_name);
                    alertDialog.setMessage(response.errorBody().toString());
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

            @Override
            public void onFailure(Call<BeatDetailsModel> call, Throwable t) {
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

}
