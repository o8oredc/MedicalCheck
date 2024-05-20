package com.example.medicalcheck;

import android.content.Context;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalcheck.Workout;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>  {

    Context context;
    ArrayList<Workout> workouts;
    private ItemClickListener itemClickListener;

    public MyAdapter(Context c , ArrayList<Workout> w)
    {
        context = c;
        workouts = w;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.title.setText(workouts.get(position).getTitle());
        holder.description.setText(workouts.get(position).getDescription());
        Picasso.get().load(workouts.get(position).getImage()).into(holder.imageTV);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                String gTitle=workouts.get(position).getTitle();
                String gDesc=workouts.get(position).getDescription();
                Drawable drawable=holder.imageTV.getDrawable();
                BitmapDrawable bitmapDrawable=(BitmapDrawable)holder.imageTV.getDrawable();
                Bitmap bitmap=bitmapDrawable.getBitmap();
                ByteArrayOutputStream stream =new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,50,stream);
                byte[] bytes=stream.toByteArray();




                Intent intent=new Intent(context,AnotherActivity.class);
                intent.putExtra("Title", gTitle );
                intent.putExtra("iImage",bytes );
                intent.putExtra("Description", gDesc);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView title,description;
        ImageView imageTV;
        private ItemClickListener itemClickListener;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.titleTv);
            description = (TextView) itemView.findViewById(R.id.descriptionTv);
            imageTV = (ImageView) itemView.findViewById(R.id.imageTv);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClickListener(v, getLayoutPosition());
        }
        public void setItemClickListener(ItemClickListener ic)
        {
            this.itemClickListener=ic;
        }

    }
}
