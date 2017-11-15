package bornbaby.materialdesign.activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import bornbaby.materialdesign.R;
import bornbaby.materialdesign.Utils.Utility;
import bornbaby.materialdesign.model.Album;

/**
 * Created by madhu on 14-Nov-17.
 */

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewholder> {


    private ArrayList<Album> albumsList;

    private RecyclerViewActivity recyclerViewActivity;


    public AlbumsAdapter(ArrayList<Album> albumsList, RecyclerViewActivity recyclerViewActivity) {
        this.albumsList = albumsList;
        this.recyclerViewActivity = recyclerViewActivity;
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new MyViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {


        Album album = albumsList.get(position);


        holder.title.setText(album.getName());
        holder.count.setText(album.getNumberOfSongs() + " songs");

        // loading album cover using Glide library
        Glide.with(recyclerViewActivity.getApplicationContext()).load(album.getThumbnail()).into(holder.thumbnail);

       // Utility.setFeedsFrameImage(holder.thumbnail,album.getThumbnail(),null,R.drawable.cat);


        /*if (holder ==null){
            holder =
        }*/


    }

    @Override
    public int getItemCount() {
        return albumsList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        public TextView title, count;
        public ImageView thumbnail;

        public MyViewholder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }
}
