package bornbaby.materialdesign.swiperefresh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import bornbaby.materialdesign.R;
import bornbaby.materialdesign.model.Album;

public class SwipeRefreshActivity extends AppCompatActivity {

    private ArrayList<Album> albumList;

    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);

        inITUI();
    }

    private void inITUI() {
        listview = findViewById(R.id.listview);
        getPictures();


    }

    private void getPictures() {
        albumList = new ArrayList<>();


        int[] covers = new int[]{
                R.drawable.battels,
                R.drawable.cat,
                R.drawable.eye,
                R.drawable.horrortwo,
                R.drawable.horrorthree,
                R.drawable.lions,
                R.drawable.nyce,
                R.drawable.red,
                R.drawable.sqauare,
                R.drawable.stylesgun,
                R.drawable.triangel,
                R.drawable.waterball};


        Album a = new Album("True Romance", 13, covers[0]);
        albumList.add(a);

        a = new Album("Xscpae", 8, covers[1]);
        albumList.add(a);

        a = new Album("Maroon 5", 11, covers[2]);
        albumList.add(a);

        a = new Album("Born to Die", 12, covers[3]);
        albumList.add(a);

        a = new Album("Honeymoon", 14, covers[4]);
        albumList.add(a);

        a = new Album("I Need a Doctor", 1, covers[5]);
        albumList.add(a);

        a = new Album("Loud", 11, covers[6]);
        albumList.add(a);

        a = new Album("Legend", 14, covers[7]);
        albumList.add(a);

        a = new Album("Hello", 11, covers[8]);
        albumList.add(a);

        a = new Album("Greatest Hits", 17, covers[9]);
        albumList.add(a);

        a = new Album("Greatest Hits", 17, covers[10]);
        albumList.add(a);

        a = new Album("Greatest Hits", 17, covers[11]);
        albumList.add(a);


       /* albumsAdapter.notifyDataSetChanged();*/

    }
}
