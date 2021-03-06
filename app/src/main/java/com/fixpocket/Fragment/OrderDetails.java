package com.fixpocket.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fixpocket.R;

/**
 * Created by lakum on 23-04-2017.
 */

public class OrderDetails extends Fragment {

    private TextView toolBarTitle;
    private LinearLayout backButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_detail, container, false);
        init(view);
        toolBarTitle.setText("ORDER DETAILS");


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

        toolBarTitle = (TextView) v.findViewById(R.id.toolBarTitle);
        backButton = (LinearLayout) v.findViewById(R.id.back_button);


    }

    public static Fragment newInstance() {
        OrderDetails fragment = new OrderDetails();
        return fragment;

    }
}