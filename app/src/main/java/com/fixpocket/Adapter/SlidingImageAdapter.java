package com.fixpocket.Adapter;

import android.content.Context;
import android.media.Image;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fixpocket.Model.LandPageModel;
import com.fixpocket.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chirag on Current Date
 */

public class SlidingImageAdapter extends PagerAdapter {

    private List<LandPageModel> ImageList;
    private LayoutInflater inflater;
    private Context context;


    public SlidingImageAdapter(Context context, List<LandPageModel> ImageList) {
        this.context = context;
        this.ImageList = ImageList;
        inflater = LayoutInflater.from(context);
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
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slide_image_viewpager, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);
        final TextView landingPageSubTitle = (TextView) imageLayout.findViewById(R.id.landingPageSubTitle);
        final TextView landingPageTitle = (TextView) imageLayout.findViewById(R.id.landingPageTitle);

//        imageView.setImageResource(Integer.parseInt(IMAGES.get(position)));

        Picasso.with(context)
                .load(ImageList.get(position).getImage())
                .into(imageView);

        landingPageTitle.setText(ImageList.get(position).getTitle());
        landingPageSubTitle.setText(ImageList.get(position).getSubTitle());

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
