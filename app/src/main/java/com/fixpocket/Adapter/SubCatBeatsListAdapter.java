package com.fixpocket.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fixpocket.Fragment.BeatDetail;
import com.fixpocket.Model.BeatListModel.Beat;
import com.fixpocket.Model.FeaturedModel.Datum;
import com.fixpocket.Model.SetFavouriteModel.SetFavouriteModel;
import com.fixpocket.R;
import com.fixpocket.Utils.Constant;
import com.fixpocket.Utils.UtilInterface;
import com.fixpocket.Webservice.FixpocketAPI;
import com.fixpocket.Webservice.WebUtil.BaseURL;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by divine on 24/4/17.
 */
public class SubCatBeatsListAdapter extends BaseAdapter {
    Fragment selectedFragment = null;
    Context context;
    public static List<Beat> FeaturedList;

    private UtilInterface mCallback;

    public SubCatBeatsListAdapter(Context c, List<Beat> FeaturedList,UtilInterface mCallback) {
        // TODO Auto-generated constructor stub

        this.FeaturedList = FeaturedList;
        context = c;
        this.mCallback = mCallback;
    }


    @Override
    public int getCount() {
        return FeaturedList.size();
    }

    @Override
    public Object getItem(int position) {
        return FeaturedList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class Holder {
        TextView beatName, txt_by, txt_total_rating, txt_price, txt_level4, txt_level, txt_featured;
        ImageView beatImg, iv_beat_fav;
        RatingBar ratingBar;

        Holder(View view) {
            beatName = (TextView) view.findViewById(R.id.beat_title);
            beatImg = (ImageView) view.findViewById(R.id.beatImg);
            iv_beat_fav = (ImageView) view.findViewById(R.id.iv_beat_fav);
            txt_by = (TextView) view.findViewById(R.id.txt_by);
            txt_total_rating = (TextView) view.findViewById(R.id.txt_total_rating);
            txt_price = (TextView) view.findViewById(R.id.txt_price);
            txt_level = (TextView) view.findViewById(R.id.txt_level);
            txt_level4 = (TextView) view.findViewById(R.id.txt_level4);
            txt_featured = (TextView) view.findViewById(R.id.txt_featured);

            ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        View rowView = convertView;
        SubCatBeatsListAdapter.Holder holder = null;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.featured_beats_single_row, null);
            holder = new SubCatBeatsListAdapter.Holder(rowView);
            rowView.setTag(holder);
        } else {

            holder = (SubCatBeatsListAdapter.Holder) rowView.getTag();
        }


        holder.beatName.setText(FeaturedList.get(position).getBeatTitle());
        Picasso.with(context).load(FeaturedList.get(position).getDoc()).transform(new RoundedCornersTransformation(10, 10)).into(holder.beatImg);

        if (!FeaturedList.get(position).getFirstName().isEmpty())
            holder.txt_by.setText("By " + FeaturedList.get(position).getFirstName() + " " + FeaturedList.get(position).getLastName());
        else
            holder.txt_by.setText("By " + FeaturedList.get(position).getUsername());

        holder.ratingBar.setRating(Float.parseFloat("" + FeaturedList.get(position).getAvgRating()));

        holder.txt_total_rating.setText("(" + FeaturedList.get(position).getTotFeed() + ")");

        holder.txt_price.setText("\u20B9 " + FeaturedList.get(position).getPrice());

        if (FeaturedList.get(position).getLevelID().equalsIgnoreCase("4")) {
            holder.txt_level4.setVisibility(View.VISIBLE);
            holder.txt_level.setVisibility(View.GONE);
        } else if (FeaturedList.get(position).getLevelID().equalsIgnoreCase("1")) {
            holder.txt_level4.setVisibility(View.GONE);
            holder.txt_level.setVisibility(View.VISIBLE);
            holder.txt_level.setBackgroundResource(R.drawable.round_corner_green);
            holder.txt_level.setText("" + FeaturedList.get(position).getLevelID());
        } else if (FeaturedList.get(position).getLevelID().equalsIgnoreCase("2") ||
                FeaturedList.get(position).getLevelID().equalsIgnoreCase("3")) {
            holder.txt_level4.setVisibility(View.GONE);
            holder.txt_level.setVisibility(View.VISIBLE);
            holder.txt_level.setBackgroundResource(R.drawable.round_corner_blue);
            holder.txt_level.setText("" + FeaturedList.get(position).getLevelID());
        }

        if (FeaturedList.get(position).getIsFeatured().equalsIgnoreCase("1"))
            holder.txt_featured.setVisibility(View.VISIBLE);
        else
            holder.txt_featured.setVisibility(View.GONE);


        if (FeaturedList.get(position).getIsFav() == 0)
            holder.iv_beat_fav.setImageResource(R.drawable.ic_beat_fav_white);
        else
            holder.iv_beat_fav.setImageResource(R.drawable.ic_beat_fav_red);

        holder.iv_beat_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FixpocketAPI bookNPlayAPI = BaseURL.getFixpocketAPI();
                Call<SetFavouriteModel> call = bookNPlayAPI.setfavourite(Constant.API_AUTH, Constant.AUTH_TOKEN, FeaturedList.get(position).getId());
                call.enqueue(new Callback<SetFavouriteModel>() {
                    @Override
                    public void onResponse(Call<SetFavouriteModel> call, Response<SetFavouriteModel> response) {
                        if (response.code() == 200) {
                            if (response.body().getStatus() == 1) {
                                Log.e("message", "" + response.body().getMessage());
                                mCallback.Refresh("" + response.body().getStatus());
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<SetFavouriteModel> call, Throwable t) {
                        Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                Toast.makeText(context, "You Clicked "+beatTitle[position], Toast.LENGTH_LONG).show();
                /*Intent intent = new Intent(context,BeatsListAdapter.class);
                context.startActivity(intent);*/
                selectedFragment = BeatDetail.newInstance();
                FragmentTransaction transactionTopBeat = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                transactionTopBeat.replace(R.id.frame_layout, selectedFragment);
                transactionTopBeat.addToBackStack(null);
                Bundle args = new Bundle();
                args.putString("CATNAME", FeaturedList.get(position).getShortTitle());
                args.putString("CATID", FeaturedList.get(position).getId());
                selectedFragment.setArguments(args);
                transactionTopBeat.commit();
            }
        });

        return rowView;
    }
}
