package com.example.my_apl_1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_apl_1.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ItemViewHolder> {

    Context context;
    List<Photo> photoList;
    private FavoriteDB favDB;

    public PhotoAdapter(Context context, List<Photo> photoList) {
        this.context = context;
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item,parent,false);
        return new ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Photo photo = photoList.get(position);
        holder.bind(photo);

    }

    @Override
    public int getItemCount() {
            return photoList.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView desc;
        private CardView cardView;
        private Button favBtn;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView_item);
            imageView = itemView.findViewById(R.id.image_item);
            desc = itemView.findViewById(R.id.title_item);
            favBtn = itemView.findViewById(R.id.btn_item);

            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public void bind(final Photo p){
         desc.setText(p.getDescription());

            Picasso.with(context)
                    .load(p.getUrls().getSmall())
                    .into(imageView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,DetailedActivity.class);
                    intent.putExtra("id",p.getId());
                    intent.putExtra("imageUrls", p.getUrls().getSmall());
                    intent.putExtra("description", p.getDescription());
                    context.startActivity(intent);
                }
            });

        }
    }
}
