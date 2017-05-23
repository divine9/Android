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

import com.fixpocket.Adapter.MyBeatAdapter;
import com.fixpocket.R;


public class MyBeats extends Fragment {

    private TextView toolBarTitle;
    private LinearLayout backButton;
    private ListView myBeatsListView;
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
        View view = inflater.inflate(R.layout.my_beats, container, false);
        init(view);
        toolBarTitle.setText("MY BEATS");
        MyBeatAdapter manageSalesAdaptr =new MyBeatAdapter(getContext(),beatName,BeatImg);
        myBeatsListView .setAdapter(manageSalesAdaptr);

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
        myBeatsListView =(ListView)v.findViewById(R.id.my_beats_ListView);



    }

    public static Fragment newInstance() {
        MyBeats fragment = new MyBeats();
        return fragment;

    }
}
// adpter for loding beats data

