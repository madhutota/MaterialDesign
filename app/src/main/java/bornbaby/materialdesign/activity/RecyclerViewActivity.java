package bornbaby.materialdesign.activity;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.util.ArrayList;

import bornbaby.materialdesign.R;
import bornbaby.materialdesign.Utils.ApiConfiguration;
import bornbaby.materialdesign.Utils.Utility;
import bornbaby.materialdesign.aynctask.JSONResult;
import bornbaby.materialdesign.aynctask.JSONTask;
import bornbaby.materialdesign.model.Album;

public class RecyclerViewActivity extends BaseActivity implements JSONResult {
    private RecyclerView recycler_view;
    private ArrayList<Album> albumList;


    private JSONTask weatherTask;

    int[] covers;

    private AlbumsAdapter albumsAdapter;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initCollapsingToolbar();
        getPictures();
        getWeatherData();


       /* Locale[] availableLocales = Locale.getAvailableLocales();
        List<String> locales = new ArrayList<>();
        for (Locale locale : availableLocales) {
            locales.add(locale.getDisplayName());
        }*/


        try {
            Glide.with(this).load(R.drawable.lions).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }


        recycler_view = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recycler_view.setItemAnimator(new DefaultItemAnimator());


        albumsAdapter = new AlbumsAdapter(albumList, this);
        recycler_view.setAdapter(albumsAdapter);


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void getWeatherData() {
        if (weatherTask != null) {
            weatherTask.cancel(true);
        }

        weatherTask = new JSONTask(this, this);
        weatherTask.setMethod(JSONTask.METHOD.GET);
        weatherTask.setCode(5);
        weatherTask.setServerUrl(ApiConfiguration.URL);
        weatherTask.setErrorMessage(ApiConfiguration.ERROR_RESPONSE_CODE);
        weatherTask.setConnectTimeout(ApiConfiguration.TIMEOUT);
        weatherTask.execute();

    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    @Override
    public void successJSONResult(int code, Object result) {
        if (code == 5) {

            JSONObject jsonObject = (JSONObject) result;

            Utility.showLog("result", "result  >>>>>>>" + result);
        }

    }

    @Override
    public void failedJSONResult(int code) {

    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    private void getPictures() {
        albumList = new ArrayList<>();

        covers = new int[]{
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
