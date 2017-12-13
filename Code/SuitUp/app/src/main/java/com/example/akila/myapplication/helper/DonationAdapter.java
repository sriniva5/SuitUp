package com.example.akila.myapplication.helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.akila.myapplication.R;
import com.example.akila.myapplication.model.Clothing;
import com.example.akila.myapplication.sql.DonationHelper;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.DonationViewHolder>{

    class DonationViewHolder extends RecyclerView.ViewHolder{
        public final TextView donationItemView;

        public DonationViewHolder(View itemView){
            super(itemView);
            donationItemView = (TextView)itemView.findViewById(R.id.search_result);
        }
    }

    private static final String TAG = DonationAdapter.class.getSimpleName();
    public static final String EXTRA_ID = "ID";
    public static final String EXTRA_ITEM = "ITEM";
    public static final String EXTRA_POS = "POSITION";

    private final LayoutInflater inflate;
    DonationHelper helper;
    Context context;

    public DonationAdapter(Context c, DonationHelper d){
        inflate = LayoutInflater.from(c);
        context = c;
        helper = d;
    }

    @Override
    public DonationViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = inflate.inflate(R.layout.donation_list, parent, false);
        return new DonationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DonationViewHolder holder, int pos){
        final DonationViewHolder h = holder;
        Clothing donation = helper.query(pos);
        holder.donationItemView.setText(donation.getItem());
    }

    @Override
    public int getItemCount(){
        return (int)helper.count();
    }
}
