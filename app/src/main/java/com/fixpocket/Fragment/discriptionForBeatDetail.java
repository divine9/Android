package com.fixpocket.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fixpocket.R;
import com.ms.square.android.expandabletextview.ExpandableTextView;


public class discriptionForBeatDetail extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discription_for_beat_detail, container, false);
        // sample code snippet to set the text content on the ExpandableTextView
        TextView expandable_text = (TextView) view.findViewById(R.id.expandable_text);

// IMPORTANT - call setText on the ExpandableTextView to set the text content to display
        expandable_text.setText("" + BeatDetail.beatDetailsModel.getData().getBeatInfo().getDescription());
        return view;

    }

}
