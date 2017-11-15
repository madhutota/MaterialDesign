package bornbaby.materialdesign;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bornbaby.materialdesign.Utils.Fontfamily;
import bornbaby.materialdesign.Utils.Utility;
import bornbaby.materialdesign.adapters.DogsAdapter;
import bornbaby.materialdesign.model.Dog;

public class MainActivity extends AppCompatActivity {
    private Button btn_click;

    AutoCompleteTextView autocompleteView;
    String[] dogsArr;
    String[] countriesArr;
    List<String> dogList;
    List<String> countryList;
    ArrayAdapter<String> adapter;


    ArrayList<Dog> dogsList;

    private DogsAdapter dogsAdapter;
    Snackbar snackbar;


    private FloatingActionButton floatingActionButton;
    private CoordinatorLayout coordinate_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inItUi();
    }

    private void inItUi() {
        coordinate_layout = findViewById(R.id.coordinate_layout);

//        Snackbar.make(coordinate_layout, Utility.getStrings(this,R.string.connection_offline), Snackbar.LENGTH_INDEFINITE).show();

        floatingActionButton = findViewById(R.id.floatin_action_button);
        floatingActionButton.setImageDrawable(Fontfamily.getDrawableForButton(this, R.string.female_icon));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(floatingActionButton, "Floatng Button Clicked", Snackbar.LENGTH_SHORT);
                snackbar.setActionTextColor(Utility.getColor(getApplicationContext(), R.color.colorAccent));
                snackbar.show();

            }
        });

        btn_click = findViewById(R.id.btn_click);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        /*AUTOCOMPELETE TEXTVIEW*/

        forAutoComplete();

        TextView textView = findViewById(R.id.tv_icon);
        textView.setTypeface(Fontfamily.setFontAwesomeIcons(this));

        /*FOR CUSTOM*/
        //forDogsList();
    }

    private void forDogsList() {

        dogList = new ArrayList<>();
        dogsList.add(new Dog("Pumariam"));
        dogsList.add(new Dog("Tommy"));
        dogsList.add(new Dog("Jimmy"));
        dogsList.add(new Dog("Range Rover"));
        dogsList.add(new Dog("Habibi"));
        dogsAdapter = new DogsAdapter(this, dogsList);


    }

    private void forAutoComplete() {
        int layoutItemId = android.R.layout.simple_dropdown_item_1line;
        countriesArr = getResources().getStringArray(R.array.countries_array);
        dogsArr = getResources().getStringArray(R.array.dogs_list);
        dogList = Arrays.asList(dogsArr);
        countryList = Arrays.asList(countriesArr);
        adapter = new ArrayAdapter<>(this, layoutItemId, countryList);
        autocompleteView = findViewById(R.id.autocompleteView);
        autocompleteView.setAdapter(adapter);
    }


}
