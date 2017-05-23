package com.fixpocket.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fixpocket.Fragment.BeatDetail;
import com.fixpocket.Fragment.CollectionBeatsList;
import com.fixpocket.Model.CollectionsModel.CollectionsModel;
import com.fixpocket.Model.CollectionsModel.Datum;
import com.fixpocket.Model.LandPageModel;
import com.fixpocket.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by chirag on Current Date
 */

public class SlidingImage_Home_Adapter extends PagerAdapter {

    private List<Datum> ImageList;
    private LayoutInflater inflater;
    private Context context;
    Fragment selectedFragment = null;

    public SlidingImage_Home_Adapter(Context context, List<Datum> ImageList) {
        this.context = context;
        this.ImageList = ImageList;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return ImageList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {

        inflater = LayoutInflater.from(context);
        View imageLayout = inflater.inflate(R.layout.slide_image_viewpager_home, view, false);

        assert imageLayout != null;
        final TextView txt_title = (TextView) imageLayout.findViewById(R.id.txt_title);
        final TextView txt_Desc = (TextView) imageLayout.findViewById(R.id.txt_Desc);
        final TextView txt_title_count = (TextView) imageLayout.findViewById(R.id.txt_title_count);
        ImageView iv_icon = (ImageView) imageLayout.findViewById(R.id.iv_icon);

        Picasso.with(context)
                .load(ImageList.get(position).getImg1())
                .into(iv_icon);

        txt_title.setText(ImageList.get(position).getTitle());
        txt_Desc.setText(ImageList.get(position).getShortTitle());
        txt_title_count.setText("+" + ImageList.get(position).getCount());

        iv_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = CollectionBeatsList.newInstance();
                FragmentTransaction transactionTopBeat = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                transactionTopBeat.replace(R.id.frame_layout, selectedFragment);
                transactionTopBeat.addToBackStack(null);
                Bundle args = new Bundle();
                args.putString("CATNAME", ImageList.get(position).getTitle());
                args.putString("CATID", ImageList.get(position).getId());
                selectedFragment.setArguments(args);
                transactionTopBeat.commit();
            }
        });

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
