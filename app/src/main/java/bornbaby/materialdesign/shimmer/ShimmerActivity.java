package bornbaby.materialdesign.shimmer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import bornbaby.materialdesign.R;
import bornbaby.materialdesign.activity.RecyclerViewActivity;
import bornbaby.materialdesign.swiperefresh.SwipeRefreshActivity;

public class ShimmerActivity extends AppCompatActivity {
    private static final String TAG = ShimmerActivity.class.getSimpleName();
    private ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_view);
        Log.e(TAG, "onCreate");
        inItUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart");
    }

    private void inItUI() {

        final LinearLayout lm = (LinearLayout) findViewById(R.id.linear_main);
        lm.setOrientation(LinearLayout.VERTICAL);

        // create the layout params that will be used to define how your
        // button will be displayed
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        //Create four
        for (int j = 0; j <= 4; j++) {
            // Create LinearLayout
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);

            // Create TextView
            TextView product = new TextView(this);
            product.setText(" Product" + j + "    ");
            ll.addView(product);

            // Create TextView
            TextView price = new TextView(this);
            price.setText("  $" + j + "     ");
            ll.addView(price);

            // Create Button
            final Button btn = new Button(this);
            // Give button an ID
            btn.setId(j + 1);
            btn.setText("Add To Cart");
            // set the layoutParams on the button
            btn.setLayoutParams(params);

            final int index = j;
            // Set click listener for button
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.i("TAG", "index :" + index);

                    startActivity(new Intent(ShimmerActivity.this, RecyclerViewActivity.class));

                    Toast.makeText(getApplicationContext(),
                            "Clicked Button Index :" + index,
                            Toast.LENGTH_LONG).show();

                }
            });

            //Add button to LinearLayout
            ll.addView(btn);
            //Add button to LinearLayout defined in XML
            lm.addView(ll);
        }

        // adding anothe layout
        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.your_layout, null);


        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.your_layout, null
                , false);


        TextView textView = (TextView) view.findViewById(R.id.tv_name);
        textView.setLayoutParams(params);

        ImageView imageView = (ImageView) view.findViewById(R.id.iv_sample);
        imageView.setLayoutParams(params);
        imageView.setImageResource(R.drawable.horrortwo);

        lm.addView(view);



      /*  shimmerFrameLayout = findViewById(R.id.fb_shimmer);
        shimmerFrameLayout.startShimmerAnimation();*/



       /* LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.your_layout, null);

      // fi
        TextView textView = (TextView) v.findViewById(R.id.a_text_view);
        textView.setText("your text");

      // in
        ViewGroup insertPoint = (ViewGroup) findViewById(R.id.insert_point);
        insertPoint.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));*/


       /* String [] names = {"one","two","three"};

        RelativeLayout relativeLayout = new RelativeLayout(this);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);


        for (int i = 0; i <names.length ; i++) {

            TextView  textView = new TextView(this);
            textView.setText(names[i]);

            layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM,textView.getId());

            relativeLayout.addView(textView,layoutParams);

        }*/


    }
}
