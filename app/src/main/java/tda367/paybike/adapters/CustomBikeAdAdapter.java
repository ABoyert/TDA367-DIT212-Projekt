package tda367.paybike.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;

import tda367.paybike.R;
import tda367.paybike.model.Bike;
import tda367.paybike.model.Rentable;

/*
 * Created by Julia Gustafsson on 2018-09-23.
 *
 * This class works as an adapter for Bike objects to be displayed in advertisement views
 */

public class CustomBikeAdAdapter extends ArrayAdapter<Rentable> {

    private int layout;
    private ArrayList<Rentable> rentables;
    private Filter bikeFilter;

    public CustomBikeAdAdapter(@NonNull Context context, int layout, @NonNull ArrayList<Rentable> rentables) {
        super(context, layout, rentables);
        this.rentables = rentables;
        this.layout = layout;
    }

    private String formatPrice(double price) {
        return "$" + price + "/h";
    }

    @Override
    public Rentable getItem(int position){
        return rentables.get(position);
    }

    @Override
    public int getCount() {
        return rentables.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lsuInflator = LayoutInflater.from(getContext());
        View bikeView = lsuInflator.inflate(layout, parent, false);

        // TODO Implement imageView and update method to reflect correct bike attributes
        // ImageView image = (ImageView) ad.findViewById(R.id.bikeImage);
        TextView city = (TextView) bikeView.findViewById(R.id.city);
        TextView name = (TextView) bikeView.findViewById(R.id.bikeName);
        TextView price = (TextView) bikeView.findViewById(R.id.price);

        Rentable rentable = rentables.get(position);

        city.setText(rentable.getPosition().toString());
        name.setText(rentable.getName());
        price.setText(formatPrice(rentable.getPrice()));

        return bikeView;
    }

    public void updateBikeView(ArrayList<Rentable> newList) {
        rentables = new ArrayList<>();
        rentables.addAll(newList);
        notifyDataSetChanged();
    }

}
