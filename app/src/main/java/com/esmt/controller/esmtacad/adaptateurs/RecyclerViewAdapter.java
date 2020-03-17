package com.esmt.controller.esmtacad.adaptateurs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.esmt.controller.esmtacad.R;
import com.esmt.controller.esmtacad.activites.LoginActivity;
import com.esmt.controller.esmtacad.models.UserActivity;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private static Context context ;
    private List<UserActivity> mData ;
    public RecyclerViewAdapter(Context context, List<UserActivity> mData) {
        this.context = context;
        this.mData = mData;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater= LayoutInflater.from(context);
        view = inflater.inflate(R.layout.activity_home, parent,false);
        return new MyViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){

        final UserActivity  rvdata = mData.get(position);
        holder.tv_id.setText(String.valueOf(mData.get(position).getId()));
        holder.tv_username.setText(mData.get(position).getUsername());
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_email.setText(mData.get(position).getEmail());
        holder.tv_phone.setText(mData.get(position).getPhone());
        holder.tv_website.setText(mData.get(position).getWebsite());


        Glide.with(context).load(mData.get(position).getImg()).into(holder.itemImg);
        //Picasso.with(context).load(mData.get(position).getImg()).into(holder.itemImg);
        Log.d("", "onBindViewHolder: " + mData.get(position).getImg());



        holder.llItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView tv_id ;
        TextView tv_name ;
        TextView tv_username ;
        TextView tv_email ;
        TextView tv_phone ;
        TextView tv_website ;
        LinearLayout llItem;
        ImageView itemImg;
        public MyViewHolder(View itemView){
            super(itemView);
            llItem = (LinearLayout) itemView.findViewById(R.id.ll_item);
            tv_id = (TextView) itemView.findViewById(R.id.id_user);
            tv_name = (TextView) itemView.findViewById(R.id.u_name);
            tv_username = (TextView) itemView.findViewById(R.id.username);
            tv_email = (TextView) itemView.findViewById(R.id.email);
            tv_phone = (TextView) itemView.findViewById(R.id.phone);
            tv_website = (TextView) itemView.findViewById(R.id.website);
            itemImg = (ImageView) itemView.findViewById(R.id.image);

        }
    }
}
