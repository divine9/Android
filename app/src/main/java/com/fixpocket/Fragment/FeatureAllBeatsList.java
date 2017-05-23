package com.fixpocket.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fixpocket.Adapter.BeatsListAdapter;
import com.fixpocket.Adapter.TopBeats;
import com.fixpocket.R;
import com.tuyenmonkey.mkloader.MKLoader;

public class FeatureAllBeatsList extends Fragment {
    private ListView beatListView;
    private LinearLayout backButton;

    TextView toolBarTitle;

    String CATNAME, CATID;
    MKLoader mkLoader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.beats_list, container, false);

        init(view);

        Bundle bundle = getArguments();
        if (bundle != null) {
            CATNAME = getArguments().getString("CATNAME");
            CATID = getArguments().getString("CATID");
            toolBarTitle.setText(CATNAME);
        }
        toolBarTitle.setText("BeatList");

        //loadin featured beats
        BeatsListAdapter topBeats = new BeatsListAdapter(getContext(), TopBeats.FeaturedList);
        beatListView.setAdapter(topBeats);
        setListViewHeightBasedOnChildren(beatListView);

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
        FeatureAllBeatsList fragment = new FeatureAllBeatsList();
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

}

