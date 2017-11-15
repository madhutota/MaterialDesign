package bornbaby.materialdesign.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bornbaby.materialdesign.MainActivity;
import bornbaby.materialdesign.R;
import bornbaby.materialdesign.filters.DogsFilter;
import bornbaby.materialdesign.model.Dog;

/**
 * Created by madhu on 11-Nov-17.
 */

public class DogsAdapter extends ArrayAdapter<Dog> {

    private MainActivity mainActivity;

    public List<Dog> dogs = new ArrayList<>();
    public List<Dog> filteredDogs = new ArrayList<>();


    public DogsAdapter(MainActivity mainActivity,final List<Dog> dogs ) {
        super(mainActivity,0,dogs);
        this.dogs = dogs;
    }

    @Override
    public int getCount() {
        return filteredDogs.size();
    }

    @Override
    public Filter getFilter() {
        return new DogsFilter(this, dogs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item from filtered list.
        Dog dog = filteredDogs.get(position);

        // Inflate your custom row layout as usual.
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.row_dog, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.tv_breed);
        ImageView ivIcon = (ImageView) convertView.findViewById(R.id.iv_dog);
        tvName.setText(dog.getBreed());
        // ivIcon.setImageResource(dog.drawable);

        return convertView;
    }


}
