package com.fixpocket.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fixpocket.Adapter.BeatsListAdapter;
import com.fixpocket.Adapter.SubCatBeatsListAdapter;
import com.fixpocket.Adapter.TopBeats;
import com.fixpocket.Model.BeatListModel.Beat;
import com.fixpocket.Model.BeatListModel.BeatListModel;
import com.fixpocket.Model.FeaturedModel.Datum;
import com.fixpocket.Model.FeaturedModel.FeaturedModel;
import com.fixpocket.R;
import com.fixpocket.Utils.Constant;
import com.fixpocket.Utils.UtilInterface;
import com.fixpocket.Webservice.FixpocketAPI;
import com.fixpocket.Webservice.WebUtil.BaseURL;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeatsList extends Fragment implements UtilInterface {
    private ListView beatListView;
    private LinearLayout backButton;

    TextView toolBarTitle;

    String CATNAME, CATID;
    MKLoader mkLoader;

    List<Beat> FeaturedList;
    SubCatBeatsListAdapter subCatBeatsListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.beats_list, container, false);

        init(view);
        toolBarTitle.setText("BeatList");
        Bundle bundle = getArguments();
        if (bundle != null) {
            CATNAME = getArguments().getString("CATNAME");
            CATID = getArguments().getString("CATID");
            toolBarTitle.setText(CATNAME);

            GetSubBeatList();
        }

        // tool bar and Menu bar clicks
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });

        return view;
    }

    public void init(View v) {

        mkLoader = (MKLoader) v.findViewById(R.id.mlloader);
        toolBarTitle = (TextView) v.findViewById(R.id.toolBarTitle);
        beatListView = (ListView) v.findViewById(R.id.beatsListView);
        backButton = (LinearLayout) v.findViewById(R.id.back_button);

    }

    public static Fragment newInstance() {
        BeatsList fragment = new BeatsList();
        return fragment;
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

    private void GetSubBeatList() {
        mkLoader.setVisibility(View.VISIBLE);
        FixpocketAPI bookNPlayAPI = BaseURL.getFixpocketAPI();
        Call<BeatListModel> call = bookNPlayAPI.subcat_beats(Constant.API_AUTH, Constant.AUTH_TOKEN, CATID);
        call.enqueue(new Callback<BeatListModel>() {
            @Override
            public void onResponse(Call<BeatListModel> call, Response<BeatListModel> response) {
                mkLoader.setVisibility(View.GONE);
                if (response.code() == 200) {
                    if (response.body().getStatus() == 1) {
                        Log.e("message", "" + response.body().getMessage());

                        FeaturedList = response.body().getData().getBeats();
                        //loadin featured beats
                        subCatBeatsListAdapter = new SubCatBeatsListAdapter(getContext(), FeaturedList, BeatsList.this);
                        beatListView.setAdapter(subCatBeatsListAdapter);
                        setListViewHeightBasedOnChildren(beatListView);

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
            public void onFailure(Call<BeatListModel> call, Throwable t) {
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

    @Override
    public void Refresh(String one) {
        Log.e("==>", "" + one);
        GetSubBeatList();
    }
}

