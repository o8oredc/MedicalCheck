package com.example.medicalcheck;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


public class MyHolder extends RecyclerView.ViewHolder {
    ImageView mImageView;
    TextView mTitle, mDes;
    View mView;

    MyHolder(@NonNull View itemView) {
        super(itemView);
        mView=itemView;




    }
    public void setDetails(Context ctx, String title, String description,String image)
    {
         mTitle=mView.findViewById(R.id.titleTv);
         mDes=mView.findViewById(R.id.descriptionTv);
         mImageView=mView.findViewById(R.id.imageTv);
         mTitle.setText(title);
         mDes.setText(description);
        Picasso.get().load(image).into(mImageView);

    }
}



