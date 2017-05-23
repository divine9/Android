package com.fixpocket.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fixpocket.R;

public class BeatRequirements extends Fragment {
    private TextView toolBarTitle;
    LinearLayout backButton;
    private ImageView bottomMenuHome,notification;
    private Button beatRequirmentNext;
    Fragment selectedFragment = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.beat_requirements, container, false);
        init(view);
        return view;
    }
    public void init(View v){
        toolBarTitle = (TextView) v.findViewById(R.id.toolBarTitle);
        backButton = (LinearLayout)v.findViewById(R.id.back_button);

        beatRequirmentNext = (Button)v.findViewById(R.id.beat_requirements_next);


        toolBarTitle.setText("BEAT REQUIREMENTS");

        // tool bar and Menu bar clicks

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });
        beatRequirmentNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = UpgradeOrder.newInstance();
                FragmentTransaction transactionUpgradeOrder = getActivity().getSupportFragmentManager().beginTransaction();
                transactionUpgradeOrder.replace(R.id.frame_layout, selectedFragment);
                transactionUpgradeOrder.addToBackStack(null);
                transactionUpgradeOrder.commit();
            }
        });


    }
    public static Fragment newInstance() {
        BeatRequirements fragment = new BeatRequirements();
        return fragment;
    }
}
