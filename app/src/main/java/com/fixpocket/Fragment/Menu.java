package com.fixpocket.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fixpocket.R;


public class Menu extends Fragment {

    private TextView toolBarTitle;
    private RelativeLayout manageSales,promoteYourBeat,buyerRequest,revenues,myBeats,manageOrders;
    private LinearLayout backButton;
    Fragment selectedFragment = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu, container, false);
        init(view);
        toolBarTitle.setText("MENU");

        // tool bar and Menu bar clicks

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });
        manageSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = ManageSales.newInstance();
                FragmentTransaction transactionbeatDetail = ((getActivity().getSupportFragmentManager().beginTransaction()));
                transactionbeatDetail .replace(R.id.frame_layout, selectedFragment);
                transactionbeatDetail.addToBackStack(null);
                transactionbeatDetail .commit();
            }
        });
        buyerRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedFragment = BuyerRequest.newInstance();
                FragmentTransaction transactionbeatDetail = ((getActivity().getSupportFragmentManager().beginTransaction()));
                transactionbeatDetail .replace(R.id.frame_layout, selectedFragment);
                transactionbeatDetail.addToBackStack(null);
                transactionbeatDetail .commit();
            }
        });
        promoteYourBeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedFragment = PromoteYourBeat.newInstance();
                FragmentTransaction transactionbeatDetail = ((getActivity().getSupportFragmentManager().beginTransaction()));
                transactionbeatDetail .replace(R.id.frame_layout, selectedFragment);
                transactionbeatDetail.addToBackStack(null);
                transactionbeatDetail .commit();
            }
        });
        myBeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedFragment = MyBeats.newInstance();
                FragmentTransaction transactionbeatDetail = ((getActivity().getSupportFragmentManager().beginTransaction()));
                transactionbeatDetail .replace(R.id.frame_layout, selectedFragment);
                transactionbeatDetail.addToBackStack(null);
                transactionbeatDetail .commit();
            }
        });
        revenues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedFragment = PromoteYourBeat.newInstance();
                FragmentTransaction transactionbeatDetail = ((getActivity().getSupportFragmentManager().beginTransaction()));
                transactionbeatDetail .replace(R.id.frame_layout, selectedFragment);
                transactionbeatDetail.addToBackStack(null);
                transactionbeatDetail .commit();
            }
        });
        manageOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedFragment = ManageOrders.newInstance();
                FragmentTransaction transactionbeatDetail = ((getActivity().getSupportFragmentManager().beginTransaction()));
                transactionbeatDetail .replace(R.id.frame_layout, selectedFragment);
                transactionbeatDetail.addToBackStack(null);
                transactionbeatDetail .commit();
            }
        });
        return view;

    }
    public void init(View v){
        toolBarTitle = (TextView)v.findViewById(R.id.toolBarTitle);
        backButton = (LinearLayout)v.findViewById(R.id.back_button);
        manageSales = (RelativeLayout) v.findViewById(R.id.menu_manage_sales);
        promoteYourBeat = (RelativeLayout)v.findViewById(R.id.menu_promote_yourBeats);
        buyerRequest = (RelativeLayout)v.findViewById(R.id.menu_buyer_request);
        revenues = (RelativeLayout)v.findViewById(R.id.menu_revenues);
        myBeats = (RelativeLayout)v.findViewById(R.id.menu_my_beats);
        manageOrders = (RelativeLayout)v.findViewById(R.id.menu_manage_orders);

    }
    public static Fragment newInstance() {
        Menu fragment = new Menu();
        return fragment;
    }
}