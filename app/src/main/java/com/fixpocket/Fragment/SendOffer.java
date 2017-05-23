package com.fixpocket.Fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fixpocket.R;


public class SendOffer extends android.support.v4.app.Fragment {

    private TextView toolBarTitle;
    private LinearLayout backButton;
    private Button sendOffer;
    android.support.v4.app.Fragment selectedFragment = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.send_offer, container, false);
        init(view);
        toolBarTitle.setText("SEND OFFERS");


        // tool bar and Menu bar clicks
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();

            }
        });

        sendOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedFragment = BuyerRequest.newInstance();
                FragmentTransaction transactionbeatDetail = (getActivity().getSupportFragmentManager().beginTransaction());
                transactionbeatDetail.replace(R.id.frame_layout, selectedFragment);
                transactionbeatDetail.addToBackStack(null);
                transactionbeatDetail.commit();
            }
        });
        return view;
    }

    public void init(View v) {

        toolBarTitle = (TextView) v.findViewById(R.id.toolBarTitle);
        backButton = (LinearLayout) v.findViewById(R.id.back_button);
        sendOffer = (Button) v.findViewById(R.id.send_offer);

    }

    public static android.support.v4.app.Fragment newInstance() {
        SendOffer fragment = new SendOffer();
        return fragment;

    }
}
