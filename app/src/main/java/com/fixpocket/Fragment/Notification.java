package com.fixpocket.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fixpocket.Adapter.NotificationAdepter;
import com.fixpocket.R;

public class Notification extends Fragment {
    private TextView toolBarTitle;
    private LinearLayout backButton;
    private ListView notificationListView;
    public static int [] notificationImg ={R.drawable.te1,R.drawable.te1,R.drawable.te1,R.drawable.te1,R.drawable.te1,R.drawable.te1};
    public static String[] notificationtext= {"Create Professional Infographics with your...","Create Professional Infographics with your...",
            "Create Professional Infographics with yourjhfhvzvjzvjveghieysgugiiuguaaauhvhbvhjahvhkhvhakhvkhvkvjhZKVHKHvkjhkhkjkvjhkhkhvkjj","Create Professional Infographics with your...","Create Professional Infographics with your...",
            "Create Professional Infographics with your..."};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notification, container, false);
        init(view);

        NotificationAdepter NotificationAdepter =new NotificationAdepter(getContext(),notificationtext,notificationImg);
        notificationListView.setAdapter(NotificationAdepter);

        return view;
    }
    public static Fragment newInstance() {
        Notification fragment = new Notification();
        return fragment;
    }
    public void init(View view){
        toolBarTitle = (TextView) view.findViewById(R.id.toolBarTitle);
        backButton = (LinearLayout)view.findViewById(R.id.back_button);
        notificationListView = (ListView)view.findViewById(R.id.notification_ListView);

        toolBarTitle.setText("NOTIFICATION");


        // tool bar and Menu bar clicks

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
// adpter for loding beats data

