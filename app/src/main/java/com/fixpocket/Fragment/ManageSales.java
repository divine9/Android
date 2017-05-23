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

import com.fixpocket.Adapter.ManageSalesAdapater;
import com.fixpocket.R;


public class ManageSales extends Fragment {
    private TextView toolBarTitle;
    private LinearLayout backButton;
    private ListView manageSalesListView;
    public static int [] BeatImg ={R.drawable.te1,R.drawable.te1,R.drawable.te1,R.drawable.te1,R.drawable.te1,R.drawable.te1};
    public static String[] beatName= {"Create Professional Infographics with your...","Create Professional Infographics with your...",
            "Create Professional Infographics with your...","Create Professional Infographics with your...","Create Professional Infographics with your...",
            "Create Professional Infographics with your..."};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.manage_sales, container, false);
        init(view);
        toolBarTitle.setText("SALES");



        // tool bar and Menu bar clicks
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();

            }
        });
        ManageSalesAdapater manageSalesAdaptr =new ManageSalesAdapater(getContext(),beatName,BeatImg);
        manageSalesListView.setAdapter(manageSalesAdaptr);


        return view;
    }

    public void init(View v){

        manageSalesListView =(ListView)v.findViewById(R.id.manage_sales_ListView);
        toolBarTitle = (TextView)v.findViewById(R.id.toolBarTitle);
        backButton = (LinearLayout)v.findViewById(R.id.back_button);


    }
    public static Fragment newInstance() {
        ManageSales fragment = new ManageSales();
        return fragment;
    }


}
// adpter for loding beats data

