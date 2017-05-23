package com.fixpocket.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fixpocket.R;


public class BuyerRequest extends Fragment {
    private TextView toolBarTitle;
    private LinearLayout backButton;
    private ListView buyerRequestListView;
    public static int[] BeatImg = {R.drawable.te1, R.drawable.te1};
    public static String[] beatName = {"Dinesh Kalola", "Smith jhon"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buyer_request, container, false);
        init(view);
        toolBarTitle.setText("BUYER REQUESTS");


        // tool bar and Menu bar clicks
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();

            }
        });
        buyerRequestAdapter buyerRequestAdapter = new buyerRequestAdapter(getContext(), beatName, BeatImg);
        buyerRequestListView.setAdapter(buyerRequestAdapter);

        return view;
    }

    public void init(View v) {

        toolBarTitle = (TextView) v.findViewById(R.id.toolBarTitle);
        backButton = (LinearLayout) v.findViewById(R.id.back_button);
        buyerRequestListView = (ListView) v.findViewById(R.id.buyer_request_ListView);


    }

    public static Fragment newInstance() {
        BuyerRequest fragment = new BuyerRequest();
        return fragment;

    }


// adpter for loding beats data

    public class buyerRequestAdapter extends ArrayAdapter {
        Fragment selectedFragment = null;
        Context context;
        String[] beatTitle;
        int[] beatImg;


        public buyerRequestAdapter(Context c, String[] Name, int[] Img) {
            // TODO Auto-generated constructor stub
            super(c, R.layout.buyer_request_single_row, Name);
            context = c;
            beatTitle = Name;
            beatImg = Img;


        }

        public class Holder {
            TextView beatName;
            ImageView beatImg;
            Button buyerRequestSendOffer;

            Holder(View view) {
                beatName = (TextView) view.findViewById(R.id.buyer_request_name);
                beatImg = (ImageView) view.findViewById(R.id.buyer_request_pic);
                buyerRequestSendOffer = (Button) view.findViewById(R.id.buyer_request_send_offer);

            }
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {


            View rowView = convertView;
            Holder holder = null;
            if (rowView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowView = inflater.inflate(R.layout.buyer_request_single_row, null);
                holder = new Holder(rowView);
                rowView.setTag(holder);
            } else {

                holder = (Holder) rowView.getTag();
            }


            holder.beatName.setText(beatTitle[position]);
            holder.beatImg.setImageResource(beatImg[position]);
            holder.buyerRequestSendOffer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    selectedFragment = SendOffer.newInstance();
                    FragmentTransaction transactionbeatDetail = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                    transactionbeatDetail.replace(R.id.frame_layout, selectedFragment);
                    transactionbeatDetail.addToBackStack(null);
                    transactionbeatDetail.commit();
                }
            });


            return rowView;
        }
    }
}