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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fixpocket.Adapter.UpgradeOrderAddepter;
import com.fixpocket.R;

public class UpgradeOrder extends Fragment {
    private TextView toolBarTitle;
    private LinearLayout backButton;
    private Button upgradeOrderPlaceOrder;
    private ListView upgradeOrderListView;
    Fragment selectedFragment = null;

    public static String[] upgradeOrderName={"Extra fast 1 day delivery","Additional Revision","Additional Revision","Additional Revision","Additional Revision","Additional Revision","Additional Revision","Additional Revision","Additional Revision","Additional Revision","Additional Revision","Additional Revision","Additional Revision"};
    public static String[] upgradeOrderPrice={"99","99","99","99","99","99","99","99","99","99","99","99","99"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.upgrade_order, container, false);
        init(view);
        toolBarTitle.setText("UPGRADE YOUR ORDER");

        //list view
        UpgradeOrderAddepter UpgradeOrderAddepter = new UpgradeOrderAddepter(getContext(),upgradeOrderName,upgradeOrderPrice);
        upgradeOrderListView.setAdapter(UpgradeOrderAddepter);
        setListViewHeightBasedOnChildren(upgradeOrderListView);

        // tool bar and Menu bar clicks

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();

            }
        });

        upgradeOrderPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = OrderSummery.newInstance();
                FragmentTransaction transactionOrderSummery = getActivity().getSupportFragmentManager().beginTransaction();
                transactionOrderSummery.replace(R.id.frame_layout, selectedFragment);
                transactionOrderSummery.addToBackStack(null);
                transactionOrderSummery.commit();

            }
        });

        return view;
    }
    public static Fragment newInstance() {
        UpgradeOrder fragment = new UpgradeOrder();
        return fragment;
    }
    public void init(View v){
        toolBarTitle = (TextView) v.findViewById(R.id.toolBarTitle);
        backButton = (LinearLayout)v.findViewById(R.id.back_button);

        upgradeOrderListView =(ListView)v.findViewById(R.id.upgrade_order_list_view);
        upgradeOrderPlaceOrder = (Button)v.findViewById(R.id.upgrade_order_place_order);



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
