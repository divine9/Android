package com.fixpocket.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fixpocket.Adapter.OrderSummeryAddepter;
import com.fixpocket.R;

public class OrderSummery extends Fragment {
    private TextView toolBarTitle;
    private LinearLayout backButton;
    private Button OrderSummeryPlaceOrder;
    private ListView OrderSummeryListView;
    private RelativeLayout orderStatus;
    private LinearLayout orderSummery;
    public static String[] OrderDetail = {"Extra fast 1 day delivery", "Additional Revision", "Additional Revision", "Additional Revision",};
    public static String[] OrderValue = {"99", "99", "99", "99"};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_summery, container, false);
        init(view);
        orderStatus.setVisibility(View.GONE);
        toolBarTitle.setText("ORDER SUMMERY");
        //list view
        OrderSummeryAddepter OrderSummeryAddepter = new OrderSummeryAddepter(getContext(), OrderDetail, OrderValue);
        OrderSummeryListView.setAdapter(OrderSummeryAddepter);
        setListViewHeightBasedOnChildren(OrderSummeryListView);

        // tool bar and Menu bar clicks

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });

        OrderSummeryPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderSummery.setVisibility(View.GONE);
                orderStatus.setVisibility(View.VISIBLE);
                toolBarTitle.setText("");


            }
        });


        return view;
    }

    public static Fragment newInstance() {
        OrderSummery fragment = new OrderSummery();
        return fragment;
    }

    public void init(View v) {
        toolBarTitle = (TextView) v.findViewById(R.id.toolBarTitle);
        backButton = (LinearLayout) v.findViewById(R.id.back_button);
        OrderSummeryListView = (ListView) v.findViewById(R.id.order_summery_listView);
        OrderSummeryPlaceOrder = (Button) v.findViewById(R.id.order_summery_place_order);
        orderStatus = (RelativeLayout) v.findViewById(R.id.order_status);
        orderSummery = (LinearLayout) v.findViewById(R.id.order_summery);


    }
    // setting up hight for list view


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
