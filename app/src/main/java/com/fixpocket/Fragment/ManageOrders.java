package com.fixpocket.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fixpocket.Adapter.ManageOrderAdapter;
import com.fixpocket.R;


public class ManageOrders extends Fragment {

    private TextView toolBarTitle;
    private LinearLayout backButton;
    private ListView manageOrders;
    public static int [] BeatImg ={R.drawable.te1,R.drawable.te1,R.drawable.te1,R.drawable.te1,R.drawable.te1,R.drawable.te1};
    public static String[] beatName= {"Create Professional Infographics with your...","Create Professional Infographics with your...",
            "Create Professional Infographics with your...","Create Professional Infographics with your...","Create Professional Infographics with your...",
            "Create Professional Infographics with your..."};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manage_orders, container, false);
        init(view);
        toolBarTitle.setText("ORDERS");


        // tool bar and Menu bar clicks
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();

            }
        });
        ManageOrderAdapter manageOrderAdapter =new ManageOrderAdapter(getActivity(),beatName,BeatImg);
        manageOrders.setAdapter(manageOrderAdapter);



        return view;
    }

    public void init(View v) {

        toolBarTitle = (TextView) v.findViewById(R.id.toolBarTitle);
        backButton = (LinearLayout) v.findViewById(R.id.back_button);
        manageOrders= (ListView)v.findViewById(R.id.manage_order_ListView);


    }

    public static Fragment newInstance() {
        ManageOrders fragment = new ManageOrders();
        return fragment;

    }
}

